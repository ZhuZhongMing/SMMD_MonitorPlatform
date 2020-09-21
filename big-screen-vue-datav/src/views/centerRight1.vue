<template>
  <div id="centreRight1">
    <div class="bg-color-black">
      <div class="d-flex pt-2 pl-2">
        <span style="color:#5cd9e8">
          <icon name="chart-line"></icon>
        </span>
        <div class="d-flex">
          <span class="fs-xl text mx-2">生产计划详情</span>
        </div>
      </div>
      <div class="d-flex jc-center body-box">
        <!--<dv-scroll-board :config="config" style="width:3.375rem;height:4.28rem" />-->
        <div class="my-p">
          <p style="margin-top: 5px;">
              <span>订单编号：</span><span>QF-20200913-001</span>
          </p>
          <p>
            <span>产品名称：</span><span>QF-1001020-00</span>
          </p>
          <p>
            <span>计划数量：</span><span>400000</span>
          </p>
          <p>
            <span>完成数量：</span><span>17682</span>
          </p>
          <!--<dv-decoration-2 style="width:100%; height:5px; margin-bottom: 0.25rem;" />-->
          <p>
            <span>备注信息：</span><span></span>
          </p>
          <!--<p>
            <span>主缸回程：</span><span>000</span>
          </p>
          <p>
            <span>下缸顶出：</span><span>000</span>
          </p>
          <p>
            <span>下缸回程：</span><span>000</span>
          </p>-->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      config: {
        header: ["组件", "分支", "覆盖率"],
        data: [
          ["组件1", "dev-1", "<span  class='colorGrass'>↑75%</span>"],
          ["组件2", "dev-2", "<span  class='colorRed'>↓33%</span>"],
          ["组件3", "dev-3", "<span  class='colorGrass'>↑100%</span>"],
          ["组件4", "rea-1", "<span  class='colorGrass'>↑94%</span>"],
          ["组件5", "rea-2", "<span  class='colorGrass'>↑95%</span>"],
          ["组件6", "fix-2", "<span  class='colorGrass'>↑63%</span>"],
          ["组件7", "fix-4", "<span  class='colorGrass'>↑84%</span>"],
          ["组件8", "fix-7", "<span  class='colorRed'>↓46%</span>"],
          ["组件9", "dev-2", "<span  class='colorRed'>↓13%</span>"],
          ["组件10", "dev-9", "<span  class='colorGrass'>↑76%</span>"]
        ],
        rowNum: 7, //表格行数
        headerHeight: 35,
        headerBGC: "#0f1325", //表头
        oddRowBGC: "#0f1325", //奇数行
        evenRowBGC: "#171c33", //偶数行
        index: true,
        columnWidth: [50],
        align: ["center"]
      }
    };
  },
  components: {},
  mounted() {},
  methods: {}
};
</script>

<style lang="scss">
#centreRight1 {
  padding: 0.2rem;
  height: 5.125rem;
  min-width: 3.75rem;
  border-radius: 0.0625rem;
  .bg-color-black {
    height: 4.8125rem;
    border-radius: 0.125rem;
  }
  .text {
    color: #c3cbde;
  }
  .body-box {
    border-radius: 0.125rem;
    overflow: hidden;
    .my-p {
      // border: 1px red solid;
      width: 100%;
      height: 100%;
      p {
        margin-bottom: 0.5rem;
        display: flex;
        span:first-child {
          flex: 3;
          text-align: right;
          font-size: 16px;
          color: #257dff;
        }
        span:last-child {
          flex: 4;
          text-align: left;
          font-size: 14px;
        }
      }
    }
  }
}
</style>
