<template>
  <div id="centreRight2">
    <div class="bg-color-black">
      <div class="d-flex pt-2 pl-2">
        <span style="color:#5cd9e8">
          <icon name="align-left"></icon>
        </span>
        <span class="fs-xl text mx-2">系统压力</span>
        <dv-decoration-1 style="width:1.25rem;height:.25rem; position:relative;top:-.0375rem;" />
      </div>
      <div class="d-flex jc-center body-box" style=" margin-top: 0;">
        <!--<dv-capsule-chart :config="config" style="width: 100%;height:2rem" />-->
        <!--<dv-active-ring-chart :config="config" style="width:100%;height:4.5rem;font-size: 10px;" />-->
        <!-- ---------------------------------------- -->
        <centreRight2Chart></centreRight2Chart>
      </div>
    </div>
  </div>
</template>

<script>
import centreRight2Chart from "@/components/echart/centreRight2/centreRight2Chart";

export default {
  data() {
    return {
      config: {
        data: [
          {
            name: "南阳",
            value: 167
          },
          {
            name: "周口",
            value: 67
          },
          {
            name: "漯河",
            value: 123
          },
          {
            name: "郑州",
            value: 55
          },
          {
            name: "西峡",
            value: 98
          }
        ]
      }
    };
  },
  components: {
    centreRight2Chart
  },
  mounted() {},
  methods: {
    getData(data) {
      this.config = {
        data: data
      }
    }
  }
};
</script>

<style lang="scss">
#centreRight2 {
  padding: 0.0625rem;
  height: 5rem;
  min-width: 3.75rem;
  border-radius: 0.0625rem;
  .bg-color-black {
    padding: 0.0625rem;
    height: 5.0625rem;
    border-radius: 0.125rem;
  }
  .text {
    color: #c3cbde;
  }
  .body-box {
    border-radius: 0.125rem;
    overflow: hidden;
  }
}
</style>
