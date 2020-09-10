<template>
  <div>
    <div id="bottomLeftChart" style="width:11.25rem;height:6.25rem;"></div>
  </div>
</template>

<script>
import echartMixins from "@/utils/resizeMixins";
import { getRequest } from '@/config/config'
export default {
  data() {
    return {
      chart: null,
      pressModel: {},
      pressure: {
        pressure1: [],
        pressure2: [],
        pressure3: [],
        datetime: []
      },
      url: {
        queryTOP5ByEquipmentId: '/system/qf/queryTOP5ByEquipmentId'
      }
    };
  },
  mixins: [echartMixins],
  mounted() {
    // this.draw();
  },
  methods: {
    /*获取传递来的数据*/
    getDataOfLeftChart(item) {
      this.pressModel = item
      // console.log('chart :' + JSON.stringify(this.pressModel))
      this.queryTOP5ByEquipmentId()
    },
    queryTOP5ByEquipmentId() {
      var param = { equipmentsn: this.pressModel.equipmentsn }
      getRequest(this.url.queryTOP5ByEquipmentId, param).then(res => {
        this.pressure.pressure1.splice(0)
        this.pressure.pressure2.splice(0)
        this.pressure.pressure3.splice(0)
        this.pressure.datetime.splice(0)
        for (let i = 0; i < res.data.result.length; i++) {
          this.pressure.pressure1.push(parseInt(res.data.result[i].systempressurevalue)) // 系统压力
          this.pressure.pressure2.push(parseInt(res.data.result[i].mastercylinderPressurevalue)) // 主缸压力
          this.pressure.pressure3.push(parseInt(res.data.result[i].systemhydraulicpressure)) // 液压压力
          this.pressure.datetime.push(res.data.result[i].createTime)
        }
        this.draw()
      }).catch(exc => {
        console.log('查询压力变化发生异常！异常信息：' + exc)
      })
    },
    draw() {
      // 基于准备好的dom，初始化echarts实例
      this.chart = this.$echarts.init(document.getElementById("bottomLeftChart"));
      //  ----------------------------------------------------------------
      /*let category = [
        "市区",
        "万州",
        "江北",
        "南岸",
        "北碚"
      ];
      let lineData = [
        18092,
        20728,
        24045,
        28348,
        32808
      ];
      let barData = [
        4600,
        5000,
        5500,
        6500,
        7500
      ];
      let rateData = [];
      for (let i = 0; i < 33; i++) {
        let rate = barData[i] / lineData[i];
        rateData[i] = rate.toFixed(2);
      }*/

      let option = {
        title: {
          text: "",
          x: "center",
          y: 0,
          textStyle: {
            color: "#B4B4B4",
            fontSize: 16,
            fontWeight: "normal"
          }
        },
        tooltip: {
          trigger: "axis",
          backgroundColor: "rgba(255,255,255,0.1)",
          axisPointer: {
            type: "shadow",
            label: {
              show: true,
              backgroundColor: "#7B7DDC"
            }
          }
        },
        legend: {
          data: ["系统压力", "主缸压力", "液压压力"], //data: ["已贯通", "计划贯通", "贯通率"],
          textStyle: {
            color: "#B4B4B4"
          },
          top: "0%"
        },
        grid: {
          x: "8%",
          width: "84%",
          y: "4%"
        },
        xAxis: {
          data: this.pressure.datetime,
          axisLine: {
            lineStyle: {
              color: "#B4B4B4"
            }
          },
          axisTick: {
            show: false
          }
        },
        yAxis: [
          {
            splitLine: { show: false },
            axisLine: {
              lineStyle: {
                color: "#B4B4B4"
              }
            },

            axisLabel: {
              formatter: "{value} "
            }
          },
          {
            splitLine: { show: false },
            axisLine: {
              lineStyle: {
                color: "#B4B4B4"
              }
            },
            axisLabel: {
              formatter: "{value} "
            }
          }
        ],
        series: [
          {
            name: "系统压力",
            type: "line",
            smooth: true,
            showAllSymbol: true,
            symbol: "emptyCircle",
            symbolSize: 8,
            yAxisIndex: 0, // 1
            itemStyle: {
              normal: {
                color: "#F02FC2"
              }
            },
            data: this.pressure.pressure1
          },
          {
            name: "主缸压力",
            type: "bar",
            barWidth: 40,
            itemStyle: {
              normal: {
                barBorderRadius: 5,
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  { offset: 0, color: "#956FD4" },
                  { offset: 1, color: "#3EACE5" }
                ])
              }
            },
            data: this.pressure.pressure2
          },
          {
            name: "液压压力",
            type: "bar",
            barGap: "-100%",
            barWidth: 40,
            itemStyle: {
              normal: {
                barBorderRadius: 5,
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  { offset: 0, color: "rgba(156,107,211,0.8)" },
                  { offset: 0.2, color: "rgba(156,107,211,0.5)" },
                  { offset: 1, color: "rgba(156,107,211,0.2)" }
                ])
              }
            },
            z: -12,

            data: this.pressure.pressure3
          }
        ]
      };
      this.chart.setOption(option);
    }
  },
  destroyed() {
    window.onresize = null;
  }
};
</script>

<style lang="scss" scoped>
</style>
