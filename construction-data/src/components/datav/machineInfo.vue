<template>
  <div id="info">
    <div class="item">
      <!--<dv-decoration-11 style="width:150px;height:50px;">Decoration</dv-decoration-11>-->
      <div>
        <!-- 假设机床名称与机床编号一致 -->
        <span>机床名称：</span>
        <span v-text="model.cncsn"></span>
      </div>
      <div>
        <span>机床编号：</span>
        <span v-text="model.cncsn"></span>
      </div>
    </div>
    <div class="item">
      <div class="item-content">
        <dv-decoration-7><span>当前产品名</span></dv-decoration-7>
        <span class="text" v-text="model.partname"></span>
      </div>
      <div class="item-content">
        <dv-decoration-7><span>计划加工数量</span></dv-decoration-7>
        <span class="text" v-text="model.targetcount"></span>
      </div>
      <div class="item-content">
        <dv-decoration-7><span>已加工数量</span></dv-decoration-7>
        <span class="text" v-text="model.count"></span>
      </div>
      <div class="item-content">
        <dv-decoration-7><span>刀具号</span></dv-decoration-7>
        <span class="text" v-text="model.toolnum"></span>
      </div>
    </div>
    <div class="item">
      <div class="item-content">
        <dv-decoration-7><span>报警类型</span></dv-decoration-7>
        <span class="text" v-text="model.alarmtype"></span>
      </div>
      <div class="item-content">
        <dv-decoration-7><span>报警号码</span></dv-decoration-7>
        <span class="text" v-text="model.alarmnum"></span>
      </div>
      <div class="item-content">
        <dv-decoration-7><span>报警描述</span></dv-decoration-7>
        <span class="text" v-text="model.alarminfo"></span>
      </div>
      <div class="item-content">
        <dv-decoration-7><span>程序运行时间</span></dv-decoration-7>
        <span class="text" v-text="model.runningtime"></span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'machineInfo',
  data () {
    return {
      /* cnc数据 */
      model: {}
    }
  },
  methods: {
    // 接收cnc数据
    getCNCModel2 (model) {
      // console.log('父组件接收的数据 : ' + JSON.stringify(model))
      this.model = model
    }
  },
  mounted () {
  }
}
</script>

<style lang="less">
  #info {
    display: flex;
    justify-content: space-between;
    height: 20%; // 修改过的
    background-color: rgba(6, 30, 93, 0.5);
    display: flex;
    .item:first-child {
      flex: 2;
      width: 33%;
      height: 100%;
      div {
        display: flex;
        margin-top: 20%;
        margin-bottom: 20%;
        margin-left: 10%;
        span:first-child {
          width: 40%;
          font-size: 14px;//font-size: 20px;
          color: rgba(2, 154, 215, .9);
        }
        span {
          width: 60%;
          font-size: 14px;//font-size: 24px;
        }
      }
    }
    .item {
      flex: 3;
      width: 33%;
      height: 100%;
      .item-content {
        padding: 0px;
        width: 100%;
        height:20%;
        display: flex;
        margin-top: 2%;
        margin-left: 5%;
        .dv-decoration-7 {
          color: rgba(2, 154, 215, .9);
          span{
            width: 100%;
            text-align: center;
          }
          width: 55%;
          /*line-height:100%;*/
        }
        .text {
          width: 63%;
          line-height:36px;
          padding-left: 5%;
          display: inline-block;
        }
      }
    }

  }
</style>
