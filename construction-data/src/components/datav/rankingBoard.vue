<template>
  <div id="ranking-board">
    <div class="ranking-board-title">设备分组列表</div>
    <el-select v-model="defaultValues" @change="changeCompany" placeholder="" size="small" :clearable="true" :filterable="true" popper-class="select-down" :popper-append-to-body="false">
      <el-option v-for="(item, key) in companyList" :key = "key" :value = "item.id" :label="item.companyName"></el-option>
    </el-select>
    <!--<dv-scroll-ranking-board :config="config" />-->
    <el-collapse v-model="activeName" :accordion="true" @change="handleChange" style="margin-top: 50px;">
      <el-collapse-item v-for="(item, key) in gatewayList" :key="key" :title="item.gatewayName" :name="item.id">
        <div v-for="(item, key) in equipmentList" :key="key" @click="handleEquipment(item)"><a>{{item.equipmentName}}</a></div>
      </el-collapse-item>
    </el-collapse>
    <dv-decoration-2 style="width:100%; height:5px;" />
    <div class="program_name">
      <p style="display: flex;">
        <span style="flex: 2; text-align: center;">程序名称:</span>
        <span style="flex: 3;" v-text="model.programname"></span>
      </p>
      <p style="display: flex;">
        <span style="flex: 2; text-align: center;">&nbsp;&nbsp;&nbsp;&nbsp;机床ip:</span>
        <span style="flex: 3;" v-text="model.cncip"></span>
      </p>
      <p style="display: flex;">
        <span style="flex: 2; text-align: center;">机床模式:</span>
        <span style="flex: 3;" v-text="model.cncmodel"></span>
      </p>
      <p style="display: flex;">
        <span style="flex: 2; text-align: center;">机床状态:</span>
        <span style="flex: 3;" v-text="model.cncstate"></span>
      </p>
      <p style="display: flex;">
        <span style="flex: 2; text-align: center;">通电时间:</span>
        <span style="flex: 3;" v-text="model.powerntime"></span>
      </p>
      <!--<p style="display: flex;">
        <span style="flex: 2; text-align: center;">运行时间:</span>
        <span style="flex: 3;" v-text="model.runningtime"></span>
      </p>-->
      <p style="display: flex;">
        <span style="flex: 2; text-align: center;">加工时间:</span>
        <span style="flex: 3;" v-text="model.cuttingtime"></span>
      </p>
      <!--<p style="display: flex;">
        <span style="flex: 2; text-align: center;">程序备注:</span>
        <span style="flex: 3;">备注信息</span>
      </p>-->
    </div>
  </div>
</template>

<script>
import { getRequest } from '../../config/config'
export default {
  name: 'RankingBoard',
  data () {
    return {
      /* cnc数据 */
      model: {},
      /* 公司列表 */
      companyList: [],
      /* 网关列表 */
      gatewayList: [],
      /* 设备列表 */
      equipmentList: [],
      activeName: '',
      defaultValues: '',
      config: {
        data: [
          {
            name: '日常养护',
            value: 55
          },
          {
            name: '交通事故',
            value: 120
          },
          {
            name: '路面',
            value: 78
          },
          {
            name: '桥通',
            value: 66
          },
          {
            name: '计日工',
            value: 80
          },
          {
            name: '路基',
            value: 45
          },
          {
            name: '交安设施',
            value: 29
          },
          {
            name: '除雪',
            value: 29
          },
          {
            name: '绿化',
            value: 29
          }
        ],
        rowNum: 9
      },
      url: {
        getCompanyList: '/system/mpiCompany/allList'
      }
    }
  },
  mounted () {
    this.getCompanyList()
  },
  methods: {
    /* 查询公司列表 */
    getCompanyList () {
      /* 利登：id -> 1285047315798949890 */
      var param = { id: '1285047315798949890' }
      getRequest(this.url.getCompanyList, param).then(res => {
        this.companyList = res.data.result
        // console.log('companyList ： ' + JSON.stringify(this.companyList))
        this.defaultValues = this.companyList[0].id
        this.getGatewayListByCompany(this.defaultValues)
        // console.log('result : ' + JSON.stringify(res.data.result))
      }).catch(exc => {
        console.log('查询公司列表发生异常！异常信息：' + exc)
      })
    },
    changeCompany (val) {
      this.getGatewayListByCompany(val)
    },
    getGatewayListByCompany (id) {
      getRequest('/system/mpiGateway/allList', { companyId: id }).then(res => {
        this.gatewayList = res.data.result
        // console.log('gatewayList ： ' + JSON.stringify(this.gatewayList))
        if (this.gatewayList.length > 0) {
          this.activeName = this.gatewayList[2].id // 第一台设备暂无数据
          this.getEquipmentListByGateway(this.activeName)
        }
        // console.log('result : ' + JSON.stringify(res.data.result))
      }).catch(exc => {
        console.log('根据公司查询网关列表发生异常！异常信息：' + exc)
      })
    },
    getEquipmentListByGateway (id) {
      getRequest('/system/mpiEquipment/allList', { gatewayId: id }).then(res => {
        this.equipmentList = res.data.result
        // console.log('equipmentList ： ' + JSON.stringify(this.equipmentList))
        this.$emit('getEquipmentId', this.equipmentList[0]) // 第一台设备暂无数据
      }).catch(exc => {
        console.log('根据网关查询设备列表发生异常！异常信息：' + exc)
      })
    },
    handleChange (val) {
      // eslint-disable-next-line eqeqeq
      if (val != '') {
        getRequest('/system/mpiEquipment/allList', { gatewayId: val }).then(res => {
          this.equipmentList = res.data.result
        }).catch(exc => {
          console.log('根据网关查询设备列表发生异常！异常信息：' + exc)
        })
      }
    },
    handleEquipment: function (item) {
      this.$emit('getEquipmentId', item)
    },
    // 接收cnc数据
    getCNCModel1 (model) {
      // console.log('父组件接收的数据 : ' + JSON.stringify(model))
      this.model = model
    }
  }
}
</script>

<style lang="less">
/*  .transition-box {
    margin-bottom: 10px;
    width: 200px;
    height: 100px;
    border-radius: 4px;
    background-color: #409EFF;
    text-align: center;
    color: #fff;
    padding: 40px 20px;
    box-sizing: border-box;
    margin-right: 20px;
  }*/
#ranking-board {
  height: 50%;
  width: 20%;
  box-shadow: 0 0 3px blue;
  display: flex;
  flex-direction: column;
  background-color: rgba(6, 30, 93, 0.5);
  border-top: 2px solid rgba(1, 153, 209, .5);
  box-sizing: border-box;
  padding: 0px 30px;

  .program_name{
    margin-top: 20px;
    border-radius: 10px;
    border: 1px rgba(1, 153, 209, .5) solid;
    width: 90%;
    height: 35%;//height: 45%; 修改过的
    padding-left: 15px;
    padding-right: 15px;
  }

  .el-collapse {
    border-color: #0a3861;
    border-bottom: none;
    height: 250px;
    .el-collapse-item {
      background-color: #0a3861;
      .el-collapse-item__header {
        padding-left: 20px;
        font-size: 16px;
        /*border-top-color: darkred;*/
        border-color: #0a3861;
        background-color:#01264a;
        color: white;
      }
      .el-collapse-item__content {
        padding-top: 10px;
        /*padding-left: 40px;
        padding-right: 20px;*/
        font-size: 14px;
        background-color: #0a3861;
        color: white;
        height: 50px;//height: 100px;
        div {
          background-color: #0a3861;
          padding-left: 40px;
          padding-right: 20px;
          margin-bottom: 10px;
          line-height: 30px;
          font-size: 14px;
        }
        div:hover {
          background-color: #006ea7;
        }
      }
      .el-collapse-item__wrap {
        border-color: #0a3861;
      }
    }
  }
  .el-select {
    .el-input__inner {
      border-color: #085786;
      background-color: #11234a;
      color: #00e3d0;
    }
    .el-input__suffix {
      .el-input__suffix-inner {
        .el-input__icon {
          color: #4cbcf4;
        }
      }
    }
  }
  .select-down {
    .el-scrollbar__wrap{
      .el-select-dropdown__list{
        .el-select-dropdown__item{
          background-color: #134e90;
          color: #7ccfff;
        }
        .el-select-dropdown__item:hover{
          background-color: #006ea7;
        }
      }
    }
    /*.popper__arrow{
      background-color: #03d3ec;
    }*/
    .popper__arrow::after {
      position: absolute;
      display: block;
      width: 0;
      height: 0;
      border-color: transparent;
      border-style: solid;
      /*background-color: greenyellow;*/
    }
    background-color: #134e90;
    border: 1px #006ea7 solid;
  }
  .ranking-board-title {
    font-weight: bold;
    height: 50px;
    display: flex;
    align-items: center;
    font-size: 20px;
  }

  .dv-scroll-ranking-board {
    flex: 1;
  }
}
</style>
