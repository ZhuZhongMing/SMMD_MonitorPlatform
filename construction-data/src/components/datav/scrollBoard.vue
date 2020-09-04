<template>
  <div id="scroll-board">
    <!--<dv-scroll-board :config="config" />-->
    <span style="text-align: left; margin-left: 15%;">实时监测数据</span>
    <div class="row">
      <div>
        <p style="margin-top: 20px">
          <span>主轴倍率</span><span v-text="model.spindlerate"></span>
        </p>
        <p>
          <span>主轴转数</span><span v-text="model.spindlespeed"></span>
        </p>
        <p>
          <span>主轴负载</span><span v-text="model.spindleload"></span>
        </p>
      </div>
      <div>
        <p style="margin-top: 20px">
          <span>进给轴转速</span><span v-text="model.feedspeed"></span>
        </p>
        <p>
          <span>进给轴倍率</span><span v-text="model.feedrate"></span>
        </p>
        <p>
          <span>进给轴设定转速</span><span v-text="model.feedspeedset"></span>
        </p>
      </div>
    </div>
    <div class="row">
      <div>
        <p style="margin-top: 20px">
          <span>X轴负载</span><span v-text="model.xload"></span>
        </p>
        <p>
          <span>Y轴负载</span><span v-text="model.yload"></span>
        </p>
        <p>
          <span>Z轴负载</span><span v-text="model.zload"></span>
        </p>
      </div>
      <div>
        <p style="margin-top: 20px">
          <span>A轴负载</span><span v-text="model.aload"></span>
        </p>
        <p>
          <span>B轴负载</span><span v-text="model.bload"></span>
        </p>
        <p>
          <span>C轴负载</span><span v-text="model.cload"></span>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ScrollBoard',
  data () {
    return {
      /* cnc数据 */
      model: {},
      config: {
        header: ['时间', '病害信息', '数量', '标段'],
        data: [
          ['2019-07-01 19:25:00', '路面危害-松散', '5', 'xxxxxxx'],
          ['2019-07-02 17:25:00', '路面危害-路面油污清理', '13', 'xxxxxxx'],
          ['2019-07-03 16:25:00', '交安设施-交通标志牌结构', '6', 'xxxxxxx'],
          ['2019-07-04 15:25:00', '路基危害-防尘网', '2', 'xxxxxxx'],
          ['2019-07-05 14:25:00', '交安设施-交通标志牌结构', '1', 'xxxxxxx'],
          ['2019-07-06 13:25:00', '路面危害-松散', '3', 'xxxxxxx'],
          ['2019-07-07 12:25:00', '路基危害-防尘网', '4', 'xxxxxxx'],
          ['2019-07-08 11:25:00', '路面危害-路面油污清理', '2', 'xxxxxxx'],
          ['2019-07-09 10:25:00', '交安设施-交通标志牌结构', '5', 'xxxxxxx'],
          ['2019-07-10 09:25:00', '路基危害-防尘网', '3', 'xxxxxxx']
        ],
        index: true,
        columnWidth: [50, 170, 300],
        align: ['center'],
        rowNum: 7,
        headerBGC: '#1981f6',
        headerHeight: 45,
        oddRowBGC: 'rgba(0, 44, 81, 0.8)',
        evenRowBGC: 'rgba(10, 29, 50, 0.8)'
      }
    }
  },
  methods: {
    // 接收cnc数据
    getCNCModel3 (model) {
      // console.log('父组件接收的数据 : ' + JSON.stringify(model))
      this.model = model
    }
  }
}
</script>

<style lang="less">
#scroll-board {
  background-color: rgba(6, 30, 93, 0.5);
  width: 80%;
  box-sizing: border-box;
  margin-left: 20px;
  height: 100%;
  overflow: hidden;
  padding-top: 10px;
  span:first-child {
    text-align: right;
    width: 65%;
    /*padding-left: 5%;
    padding-right: 10%;*/
  }
  span:last-child {
    text-align: center;
    width: 35%;
  }
  span{
    /*padding-left: 10%;*/
    font-size: 18px;
    display: inline-block;
  };
  .row {
    margin: 0 auto;
    border: 1px solid rgba(1, 153, 209, .5);
    width: 70%;
    height: 45%;
    display: flex;
    span:first-child {
      flex: 1;
      color: rgba(2, 154, 215, .9);
    }
    span {
      font-size: 18px;
      flex: 2;
    }
  }
  .row:first-child{
    border-bottom: none;
  }
  .row div {
    border: 1px solid rgba(1, 153, 209, .5);
    width: 100%;
    height: 100%;
  }
}
</style>
