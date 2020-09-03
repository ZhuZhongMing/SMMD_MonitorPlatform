<template>
  <div class="top-left-cmp">
    <div class="dc-left">
      <dv-border-box-5>
        <el-popover
          popper-class="rs"
          placement="right"
          width="550"
          trigger="click">
          <el-table :data="gridData">
            <el-table-column width="150" property="date" label="日期"></el-table-column>
            <el-table-column width="100" property="name" label="姓名"></el-table-column>
            <el-table-column width="300" property="address" label="地址"></el-table-column>
          </el-table>
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-sizes="pageSizes"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
          </el-pagination>
          <div class="info" slot="reference"><el-link type="primary">查看详情</el-link></div>
        </el-popover>
        <!--<div style="margin-left: 70%; height: 10%; position: absolute;"><a>查看详情</a></div>-->
        <div class="main-value"><span>0</span>件</div>
        <div class="compare-value"><span>同比</span>81</div>
        <div class="compare-value"><span>环比</span>15</div>
      </dv-border-box-5>
      <div class="dc-text">
        <!--运维人均工作量-->
        网关离线数量
        <dv-decoration-3 style="width:200px;height:20px;" />
      </div>
    </div>
    <div class="dc-right">
      <div class="dc-text">
        <!--当月维修任务量-->
        网关在线数量
        <dv-decoration-3 style="width:200px;height:20px;" />
      </div>
      <dv-border-box-5 :reverse="true">
        <div class="main-value"><span>0.1</span>件 / 日</div>
        <div class="compare-value"><span>同比</span>66</div>
        <div class="compare-value"><span>环比</span>9</div>
      </dv-border-box-5>
    </div>
  </div>
</template>

<script>
export default {
  name: 'TopLeftCmp',
  data () {
    return {
      total: 10,
      pageSize: 5,
      currentPage: 4,
      pageSizes: [5, 10, 15, 20],
      gridData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }]
    }
  },
  methods: {
    handleSizeChange (val) {
      console.log(`每页 ${val} 条`)
    },
    handleCurrentChange (val) {
      console.log(`当前页: ${val}`)
    }
  }
}
</script>

<style lang="less">
.top-left-cmp {
  display: flex;

  .dc-left, .dc-right {
    width: 50%;
  }

  .dv-border-box-5 {
    height: 60%;
    .el-popover.test {
      background-color: #00c0ff;
    }
  }

  .dc-text {
    display: flex;
    flex-direction: column;
    height: 40%;
    font-size: 20px;
    padding: 20px;
    box-sizing: border-box;
  }

  .dc-left .dc-text {
    align-items: flex-end;
    justify-content: center;
  }

  .dc-right .dc-text {
    justify-content: flex-start;
    padding-top: 20px;
  }

  .dc-left .dv-border-box-5 {
    padding: 30px;
    box-sizing: border-box;
    /*.el-popper {
      color: #00c0ff;
    }*/
    .info {
      margin-left: 70%;
      height: 10%;
      position: absolute;
    }
  }

  .dc-right .dv-border-box-5 {
    padding: 40px;
    padding-left: 75px;
    box-sizing: border-box;
  }

  .main-value {
    font-weight: bold;
    font-size: 30px;

    span {
      font-size: 50px;
      color: #00c0ff;
      margin-right: 15px;
    }
  }

  .compare-value {
    height: 35px;
    line-height: 35px;
    font-size: 18px;

    span {
      margin-right: 30px;
    }
  }
}
/*.el-popover{
  background-color: #00c0ff;
}*/
</style>
