<template>
  <div>
    <div id="centreRight2Chart" style="width:100%; height: 4.5rem;">123</div>
  </div>
</template>

<script>
import echartMixins from "@/utils/resizeMixins";

export default {
  data() {
    return {
      chart: null,
      /*option: {
        backgroundColor: '#061326',
        "grid": {
          "top": "25%",
          "left": "-5%",
          "bottom": "5%",
          "right": "5%",
          "containLabel": true
        },
        tooltip:{
          show:true
        },
        animation: false,
        "xAxis": [{
          "type": "category",
          "data": ["本年话务总量", "本年人工话务量", "每万客户呼入量"],
          "axisTick": {
            "alignWithLabel": true
          },
          "nameTextStyle": {
            "color": "#82b0ec"
          },
          "axisLine": {
            show: false,
            "lineStyle": {
              "color": "#82b0ec"
            }
          },
          "axisLabel": {
            "textStyle": {
              "color": "#fff"
            },
            margin: 30
          }
        }],
        "yAxis": [{
          show: false,
          "type": "value",
          "axisLabel": {
            "textStyle": {
              "color": "#fff"
            },
          },
          "splitLine": {
            "lineStyle": {
              "color": "#0c2c5a"
            }
          },
          "axisLine": {
            "show": false
          }
        }],
        "series": [{
          "name": "",
          type: 'pictorialBar',
          symbolSize: [40, 10],
          symbolOffset: [0, -6],
          symbolPosition: 'end',
          z: 12,
          // "barWidth": "0",
          "label": {
            "normal": {
              "show": true,
              "position": "top",
              // "formatter": "{c}%"
              fontSize: 25,
              fontWeight: 'bold',
              color: '#34DCFF'
            }
          },
          color: "#2DB1EF",
          data: [2342, 1230, 425]
        },
          {
            name: '',
            type: 'pictorialBar',
            symbolSize: [40, 10],
            symbolOffset: [0, 7],
            // "barWidth": "20",
            z: 12,
            "color": "#2DB1EF",
            "data": [2342, 1230, 425]
          },
          {
            name: '',
            type: 'pictorialBar',
            symbolSize: [50, 15],
            symbolOffset: [0, 12],
            z: 10,
            itemStyle: {
              normal: {
                color: 'transparent',
                borderColor: '#2EA9E5',
                borderType: 'solid',
                borderWidth: 1
              }
            },
            data: [2342, 1230, 425]
          },
          {
            name: '',
            type: 'pictorialBar',
            symbolSize: [70, 20],
            symbolOffset: [0, 18],
            z: 10,
            itemStyle: {
              normal: {
                color: 'transparent',
                borderColor: '#19465D',
                borderType: 'solid',
                borderWidth: 2
              }
            },
            data: [2342, 1230, 425]
          },
          {
            type: 'bar',
            //silent: true,
            "barWidth": "40",
            barGap: '10%', // Make series be overlap
            barCateGoryGap: '10%',
            itemStyle: {
              normal: {
                color: new echarts.graphic.LinearGradient(0, 0, 0, 0.7, [{
                  offset: 0,
                  color: "#38B2E6"
                },
                  {
                    offset: 1,
                    color: "#0B3147"
                  }
                ]),
                opacity: .8
              },
            },
            data: [2342, 1230, 425]
          }
        ]
      }*/
    };
  },
  mixins: [echartMixins],
  mounted() {
    this.draw();
  },
  methods: {
    draw() {
      // 基于准备好的dom，初始化echarts实例
      this.chart = this.$echarts.init(document.getElementById("centreRight2Chart"));
      //  ----------------------------------------------------------------
      let option = {
        backgroundColor: '#000000',
        "grid": {
          "top": "25%",
          "left": "-5%",
          "bottom": "5%",
          "right": "5%",
          "containLabel": true
        },
        tooltip:{
          show:true
        },
        animation: false,
        "xAxis": [{
          "type": "category",
          "data": ["系统压力", "主缸压力", "液压压力"],
          "axisTick": {
            "alignWithLabel": true
          },
          "nameTextStyle": {
            "color": "#82b0ec"
          },
          "axisLine": {
            show: false,
            "lineStyle": {
              "color": "#82b0ec"
            }
          },
          "axisLabel": {
            "textStyle": {
              "color": "#fff"
            },
            margin: 30
          }
        }],
        "yAxis": [{
          show: false,
          "type": "value",
          "axisLabel": {
            "textStyle": {
              "color": "#fff"
            },
          },
          "splitLine": {
            "lineStyle": {
              "color": "#0c2c5a"
            }
          },
          "axisLine": {
            "show": false
          }
        }],
        "series": [{
          "name": "",
          type: 'pictorialBar',
          symbolSize: [40, 10],
          symbolOffset: [0, -6],
          symbolPosition: 'end',
          z: 12,
          // "barWidth": "0",
          "label": {
            "normal": {
              "show": true,
              "position": "top",
              // "formatter": "{c}%"
              fontSize: 16,
              fontWeight: 'bold',
              color: '#34DCFF'
            }
          },
          color: "#2DB1EF",
          data: [15875, 15422, 16840]
        },
          {
            name: '',
            type: 'pictorialBar',
            symbolSize: [40, 10],
            symbolOffset: [0, 7],
            // "barWidth": "20",
            z: 12,
            "color": "#2DB1EF",
            "data": [15875, 15422, 16840]
          },
          {
            name: '',
            type: 'pictorialBar',
            symbolSize: [50, 15],
            symbolOffset: [0, 12],
            z: 10,
            itemStyle: {
              normal: {
                color: 'transparent',
                borderColor: '#2EA9E5',
                borderType: 'solid',
                borderWidth: 1
              }
            },
            data: [15875, 15422, 16840]
          },
          {
            name: '',
            type: 'pictorialBar',
            symbolSize: [70, 20],
            symbolOffset: [0, 18],
            z: 10,
            itemStyle: {
              normal: {
                color: 'transparent',
                borderColor: '#19465D',
                borderType: 'solid',
                borderWidth: 2
              }
            },
            data: [15875, 15422, 16840]
          },
          {
            type: 'bar',
            //silent: true,
            "barWidth": "40",
            barGap: '10%', // Make series be overlap
            barCateGoryGap: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 0.7, [{
                  offset: 0,
                  color: "#24B2E6"
                },
                  {
                    offset: 1,
                    color: "#0B3147"
                  }
                ]),
                opacity: .8
              },
            },
            data: [15875, 15422, 16840]
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
#centreRight2Chart1 {
  margin: 0 auto;
}
</style>
