<template>
  <div id="bottomRight">
    <div class="bg-color-black">
      <div class="d-flex pt-2 pl-2">
        <span style="color:#5cd9e8">
          <icon name="chart-area"></icon>
        </span>
        <div class="d-flex">
          <span class="fs-xl text mx-2">设备生产数量对比</span>
          <div class="decoration2">
            <dv-decoration-2 :reverse="true" style="width:5px;height:380px;" />
          </div>
        </div>
      </div>
      <div>
        <!--<bottomRightChart />-->
        <dv-conical-column-chart :config="config" style="width:100%; height:5.7rem;" />
      </div>
    </div>
  </div>
</template>

<script>
//import bottomRightChart from "@/components/echart/bottom/bottomRightChart";
export default {
  data() {
    return {
      config: {
        data: [
          {
            name: '周口',
            value: 55
          },
          {
            name: '南阳',
            value: 120
          },
          {
            name: '西峡',
            value: 71
          },
          {
            name: '驻马店',
            value: 66
          },
          {
            name: '新乡',
            value: 80
          },
          {
            name: '信阳',
            value: 35
          },
          {
            name: '漯河',
            value: 15
          }
        ],
        img: [],
        showValue: true
      }
    };
  },
  components: {
    //bottomRightChart
  },
  mounted() {},
  methods: {
    getDataOfBottom(data) {
      //console.log(typeof data[0].value)
      this.config = {
        data: data,
        img: [],
        showValue: true
      }
    }
  }
};
</script>

<style lang="scss">
#bottomRight {
  padding: 0.2rem 0.2rem 0;
  height: 6.5rem;
  min-width: 3.75rem;
  border-radius: 0.0625rem;
  .bg-color-black {
    height: 6.1875rem;
    border-radius: 0.125rem;
  }
  .text {
    color: #c3cbde;
  } //下滑线动态
  .decoration2 {
    position: absolute;
    right: 0.125rem;
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
