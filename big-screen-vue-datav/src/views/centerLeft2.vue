<template>
  <div id="centreLeft1">
    <div class="bg-color-black">
      <div class="d-flex pt-2 pl-2">
        <span style="color:#5cd9e8">
          <icon name="chart-pie"></icon>
        </span>
        <div class="d-flex">
          <span class="fs-xl text mx-2">设备状况</span>
          <dv-decoration-1 style="width:1.25rem;height:.25rem; position:relative;top:-.0375rem;" />
        </div>
      </div>
      <div class="d-flex jc-center">
        <!--<centreLeft2Chart />-->
        <div class="my-p">
          <p>
            <span>主缸行程：</span><span v-text="pressModel.mastercylinderStroke"></span>
          </p>
          <p>
            <span>系统吨位：</span><span v-text="pressModel.systemtonnage"></span>
          </p>
          <p>
            <span>主缸公称力：</span><span v-text="pressModel.mastercylinderNominalforce"></span>
          </p>
          <p>
            <span>主缸回程力：</span><span v-text="pressModel.mastercylinderReturnforce"></span>
          </p>
          <dv-decoration-2 style="width:100%; height:5px; margin-bottom: 0.25rem;" />
          <p>
            <span>主缸加压速度：</span><span v-text="pressModel.mastercylinderCompressionspeed"></span>
          </p>
          <p>
            <span>主缸回程速度：</span><span v-text="pressModel.mastercylinderReturntripspeed"></span>
          </p>
          <p>
            <span>下缸顶出速度：</span><span v-text="pressModel.lowercylinderEjectionspeed"></span>
          </p>
          <p>
            <span>下缸回程速度：</span><span v-text="pressModel.lowercylinderReturntripspeed"></span>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import centreLeft2Chart from "@/components/echart/centerLeft2/centreLeft2Chart";
export default {
  data() {
    return {
      pressModel: {}
    };
  },
  components: {
    // centreLeft2Chart
  },
  mounted() {},
  methods: {
    getPressModel2(item) {
      this.pressModel = item
      // console.log("item : " + JSON.stringify(this.pressModel))
    }
  }
};
</script>

<style lang="scss">
#centreLeft1 {
  padding: 0.2rem;
  height: 5.125rem;
  min-width: 3.75rem;
  border-radius: 0.0625rem;
  .bg-color-black {
    height: 4.8125rem;
    border-radius: 0.125rem;
    .jc-center {
      padding-top: 0.125rem;
      .my-p {
        // border: 1px red solid;
        width: 100%;
        height: 100%;
        p {
          margin-bottom: 0.25rem;
          display: flex;
          span:first-child {
            flex: 4;
            text-align: right;
            font-size: 16px;
            color: #257dff;
          }
          span:last-child {
            flex: 4;
            text-align: center;
            font-size: 18px;
          }
        }
      }
    }
  }
  .text {
    color: #c3cbde;
  }
  .chart-box {
    margin-top: 0.2rem;
    width: 2.125rem;
    height: 2.125rem;
    .active-ring-name {
      padding-top: 0.125rem;
    }
  }
}
</style>
