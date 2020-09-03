<template>
  <div id="ranking-board">
    <div class="ranking-board-title">当前日期：<span v-text="today"></span></div>
    <div align="center" class="ranking-board-time">
      <now-time />
    </div>
    <dv-decoration-2 />
    <!-- border: 1px rgba(1, 153, 209, .5) solid-->
    <div style="width: 100%; height: 30%;">
      <div class="ranking-board-title">报警信息</div>
      <dv-scroll-board :config="config" style="width:100%;height: 80%; margin-top: 10%;" />
    </div>
    <dv-decoration-2 />
    <!-- border: 1px rgba(1, 153, 209, .5) solid-->
    <div style="width: 100%; height: 35%;">
      <div class="ranking-board-title">每日加工数量</div>
      <dv-active-ring-chart :config="configOfChart" style="width:100%;height: 80%; margin-top: 10%;" />
    </div>
  </div>
</template>

<script>
// import {getRequest} from '../../config/config'
import nowTime from './nowTime'

export default {
  name: 'RankingBoard',
  components: {
    nowTime
  },
  data: function () {
    return {
      today: '',
      day: 1,
      config: {
        header: ['报警类型', '报警号码'],
        data: [
          ['EX', '0'],
          ['EX', '0'],
          ['EX', '0'],
          ['EX', '0']
        ],
        rowNum: 4,
        headerBGC: '#0a3861',
        index: true,
        columnWidth: [50],
        align: ['center']
      },
      configOfChart: {
        radius: '70%',
        activeRadius: '60%',
        lineWidth: 30,
        data: [
          {
            name: '',
            value: 0
          },
          {
            name: '',
            value: 0
          },
          {
            name: '西峡',
            value: 78
          },
          {
            name: '',
            value: 0
          },
          {
            name: '新乡',
            value: 0
          }
        ],
        digitalFlopStyle: {
          fontSize: 20
        },
        showOriginValue: true
      },
      /* CNC数据 */
      model: {},
      flag: true
    }
  },
  mounted () {
    this.getToDay()
  },
  methods: {
    getToDay () {
      var date = new Date()
      this.day = date.getDate()
      this.today = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
    },
    // 接收cnc数据
    getCNCModel5 (model) {
      // console.log('父组件接收的数据 : ' + JSON.stringify(model))
      this.model = model
      this.getChartValue()
    },
    getChartValue () {
      if (this.flag) {
        const result = []
        for (let i = 0; i < 5; i++) {
          result.push({
            name: this.day - i + '日',
            value: this.model.count - (i * 20)
          })
        }
        this.configOfChart = {
          radius: '70%',
          activeRadius: '60%',
          lineWidth: 30,
          data: result,
          digitalFlopStyle: {
            fontSize: 20
          },
          showOriginValue: true
        }
        this.flag = false
      }
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
  height: 98%;
  width: 20%;
  box-shadow: 0 0 3px blue;
  display: flex;
  flex-direction: column;
  background-color: rgba(6, 30, 93, 0.5);
  border-top: 2px solid rgba(1, 153, 209, .5);
  box-sizing: border-box;
  padding: 0px 30px;
  .ranking-board-title {
    font-weight: bold;
    height: 5%;
    display: flex;
    align-items: center;
    font-size: 20px;
  }
  .ranking-board-time{
    width: 90%;
    height: 20%;
  }
  /* 分割线, 占约10% */
  .dv-decoration-2 {
    width:100%;
    height:5px;
    margin-top: 4%;
    margin-bottom: 5%;
  }
}
</style>
