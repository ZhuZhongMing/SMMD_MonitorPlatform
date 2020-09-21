<template>
  <div id="center">
    <div class="up">
      <el-tooltip class="item" effect="dark" content="点击切换" placement="top" v-for="item in titleItem" :key="item.title">
        <div class="bg-color-black item" @click="handlClickEquipment(item.info)">
          <p class="ml-3 colorBlue fw-b">{{item.title}}</p>
          <div>
            <dv-digital-flop :config="item.number" style="width:1.25rem;height:.625rem;" />
          </div>
        </div>
      </el-tooltip>
    </div>
    <div class="down">
      <div class="ranking bg-color-black" style="width: 100%;"> <!--<div class="ranking bg-color-black">-->
        <span style="color:#5cd9e8">
          <icon name="align-left"></icon>
        </span>
        <span class="fs-xl text mx-2 mb-1">生产数量排行</span>
        <dv-scroll-ranking-board :config="ranking" style="height:2.75rem" />
      </div>
      <!--<div class="percent">
        <div class="item bg-color-black">
          <span>今日任务通过率</span>
          <centerChart :id="rate[0].id" :tips="rate[0].tips" :colorObj="rate[0].colorData" />
        </div>
        <div class="item bg-color-black">
          <span>今日任务达标率</span>
          <centerChart :id="rate[1].id" :tips="rate[1].tips" :colorObj="rate[1].colorData" />
        </div>
        <div class="water">
          <dv-water-level-pond :config="water" style="height: 1.5rem" />
        </div>
      </div>-->
    </div>
  </div>
</template>

<script>
// import centerChart from "@/components/echart/center/centerChartRate";
import { getRequest } from '@/config/config'
export default {
  data: function () {
    return {
      titleItem: [],
      defaultTitleItem: [
        {
          title: "液压设备3",
          info: {},
          number: {
            number: ['离线'],
            toFixed: 1,
            content: "{nt}",
            style: {
              fontSize: 26,
              fill: '#858f91'
            }
          }
        },
        {
          title: "液压设备4",
          info: {},
          number: {
            number: ['离线'],
            toFixed: 1,
            content: "{nt}",
            style: {
              fontSize: 26,
              fill: '#858f91'
            }
          }
        },
        {
          title: "磨洗设备2",
          info: {},
          number: {
            number: ['离线'],
            toFixed: 1,
            content: "{nt}",
            style: {
              fontSize: 26,
              fill: '#858f91'
            }
          }
        }
      ],
      ranking: {
        data: [],
        waitTime: 4000,
        unit: "件"
      },
      defaultData: [
        {
          name: "液压设备3",
          value: 0
        },
        {
          name: "液压设备4",
          value: 0
        },
        {
          name: "磨洗设备2",
          value: 0
        }
      ],
      water: {
        data: [24, 45],
        shape: "roundRect",
        formatter: "{value}%",
        waveNum: 3
      },
      // 通过率和达标率的组件复用数据
      rate: [
        {
          id: "centerRate1",
          tips: 60,
          colorData: {
            textStyle: "#3fc0fb",
            series: {
              color: ["#00bcd44a", "transparent"],
              dataColor: {
                normal: "#03a9f4",
                shadowColor: "#97e2f5"
              }
            }
          }
        },
        {
          id: "centerRate2",
          tips: 40,
          colorData: {
            textStyle: "#67e0e3",
            series: {
              color: ["#faf3a378", "transparent"],
              dataColor: {
                normal: "#ff9800",
                shadowColor: "#fcebad"
              }
            }
          }
        }
      ],
      equipmentList: [],
      /*当前设备信息*/
      currentEquipment: {},
      url: {
        getEquipmentListByCompany: '/system/mpiEquipment/getEquipmentListByCompany',
        queryByEquipmentId: '/system/qf/queryByEquipmentId',
        getCnnModelValue: '/system/cnc/queryByEquipmentId'
      }
    };
  },
  components: {
    // centerChart
    // centerChart1,
    // centerChart2
  },
  methods: {
    /* 查询公司下的设备列表 */
    getEquipmentListByCompany () {
      /* 钱富：id -> 1301343481058353154 */
      var param = { id: '1301343481058353154' }
      getRequest(this.url.getEquipmentListByCompany, param).then(res => {
        this.equipmentList = res.data.result
        this.sortEquipmentDevCount () // 查询排行
        this.currentEquipment = this.equipmentList[1] //默认查看钱富西门子2号设备
        this.$emit("getEquipment",this.currentEquipment)
        this.titleItem.splice(0)
        for (let i = 0; i < this.equipmentList.length; i++) {
          this.titleItem.push({
            title: this.equipmentList[i].equipmentName,
            info: this.equipmentList[i],
            number: {
              number: ['在线'],
              toFixed: 1,
              content: "{nt}",
              style: {
                fontSize: 26,
              }
            }
          })
        }
        for (let i = 0; i < this.defaultTitleItem.length; i++) {
          this.titleItem.push(this.defaultTitleItem[i])
        }
      }).catch(exc => {
        console.log('查询设备列表发生异常！异常信息：' + exc)
      })
    },
    /*查询设备生产数量排行*/
    sortEquipmentDevCount () {
      this.ranking.data.splice(0) // 清空
      const data = this.defaultData
      /*for (let i = 0; i < this.defaultData; i++) {
        data.push(this.defaultData[i])
      }*/
      for (let i = 0; i < this.equipmentList.length; i++) {
        if(this.equipmentList[i].id == 'QF_SIEMENS808D_001') {
          // cnc设备
          var paramCNC = {
            cncsn : this.equipmentList[i].id
          }
          // console.log("cncsn : " + JSON.stringify(paramCNC))
          getRequest(this.url.getCnnModelValue, paramCNC).then(res => {
            // console.log("data : " + res.data.result)
            if (res.data.result) {
              data.push({
                name: this.equipmentList[i].equipmentName,
                value: parseInt(res.data.result.count)
              })
            }  else {
              data.push({
                name: this.equipmentList[i].equipmentName,
                value: parseInt(0)
              })
            }
            this.ranking = {
              data: data,
              waitTime: 4000,
              unit: "件"
            }
            this.$emit("getCount", data) // 传递给父组件
          }).catch(exc => {
            console.log('CNC设备生产数量发生异常！异常信息：' + exc)
          })
        } else {
          var param = {
            equipmentsn : this.equipmentList[i].id
          }
          getRequest(this.url.queryByEquipmentId, param).then(res => {
            if (res.data.result) {
              data.push({
                name: this.equipmentList[i].equipmentName,
                value: parseInt(res.data.result.counterdisplay)
              })
            }  else {
              data.push({
                name: this.equipmentList[i].equipmentName,
                value: parseInt(0)
              })
            }
            this.ranking = {
              data: data,
              waitTime: 4000,
              unit: "件"
            }
            this.$emit("getCount", data) // 传递给父组件
          }).catch(exc => {
            console.log('西门子设备生产数量发生异常！异常信息：' + exc)
          })
        }

      }
      //console.log("data : " + JSON.stringify(data))
      /*this.ranking = {
        data: data,
        waitTime: 4000,
        carousel: "page",
        unit: "件"
      }*/
      //console.log("ranking : " + JSON.stringify(this.ranking))
    },
    /*点击切换设备*/
    handlClickEquipment(item) {
      if (item.id) {
        this.currentEquipment = item
        this.$emit("getEquipment",this.currentEquipment)
      } else {
        this.$message({
          message: '该设备为离线状态',
          type: 'warning'
        });
      }
    }
  },
  mounted() {
    this.getEquipmentListByCompany()
  }
};
</script>

<style lang="scss" scoped>
#center {
  display: flex;
  flex-direction: column;
  .up {
    width: 100%;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    .item {
      border-radius: 0.0625rem;
      padding-top: 0.2rem;
      margin-top: 0.1rem;
      width: 32%;
      height: 0.875rem;
    }
  }
  .down {
    padding: 0.07rem 0.05rem;
    padding-bottom: 0;
    width: 100%;
    display: flex;
    height: 3.1875rem;
    justify-content: space-between;
    .bg-color-black {
      border-radius: 0.0625rem;
    }
    .ranking {
      padding: 0.125rem;
      width: 59%;
    }
    .percent {
      width: 40%;
      display: flex;
      flex-wrap: wrap;
      .item {
        width: 50%;
        height: 1.5rem;
        span {
          margin-top: 0.0875rem;
          display: flex;
          justify-content: center;
        }
      }
      .water {
        width: 100%;
      }
    }
  }
}
</style>
