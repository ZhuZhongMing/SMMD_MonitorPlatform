<template>
  <div id="info">
    <dv-charts :option="option" />
    <dv-decoration-2 :reverse="true" style="width:5px;height:100%;" />
    <dv-charts :option="option1" />
    <dv-decoration-2 :reverse="true" style="width:5px;height:100%;" />
    <dv-charts :option="option2" />
    <dv-decoration-2 :reverse="true" style="width:5px;height:100%;" />
    <dv-charts :option="option3" />
  </div>
</template>

<script>
export default {
  name: 'axle',
  data () {
    return {
      option: {
        title: {
          text: '主轴',
          style: {
            fill: '#fff'
          }
        },
        series: [
          {
            type: 'gauge',
            data: [ { name: '负载', value: 0 } ],
            center: ['50', '60'],
            min: 0,
            max: 100,
            splitNum: 6,
            axisLabel: {
              formatter: '{value}',
              style: {
                fill: '#fff'
              }
            },
            axisTick: {
              style: {
                stroke: '#fff'
              }
            },
            animationCurve: 'easeInOutBack'
          }
        ]
      },
      option1: {
        title: {
          text: 'X轴',
          style: {
            fill: '#fff'
          }
        },
        series: [
          {
            type: 'gauge',
            data: [ { name: '负载', value: 0 } ],
            center: ['50', '60'],
            min: 0,
            max: 100,
            splitNum: 6,
            axisLabel: {
              formatter: '{value}',
              style: {
                fill: '#fff'
              }
            },
            axisTick: {
              style: {
                stroke: '#fff'
              }
            },
            animationCurve: 'easeInOutBack'
          }
        ]
      },
      option2: {
        title: {
          text: 'Y轴',
          style: {
            fill: '#fff'
          }
        },
        series: [
          {
            type: 'gauge',
            data: [ { name: '负载', value: 0 } ],
            center: ['50', '60'],
            min: 0,
            max: 100,
            splitNum: 6,
            axisLabel: {
              formatter: '{value}',
              style: {
                fill: '#fff'
              }
            },
            axisTick: {
              style: {
                stroke: '#fff'
              }
            },
            animationCurve: 'easeInOutBack'
          }
        ]
      },
      option3: {
        title: {
          text: 'Z轴',
          style: {
            fill: '#fff'
          }
        },
        series: [
          {
            type: 'gauge',
            data: [ { name: '负载', value: 0 } ],
            center: ['50', '60'],
            min: 0,
            max: 100,
            splitNum: 6,
            axisLabel: {
              formatter: '{value}',
              style: {
                fill: '#fff'
              }
            },
            axisTick: {
              style: {
                stroke: '#fff'
              }
            },
            animationCurve: 'easeInOutBack'
          }
        ]
      },
      /* cnc数据 */
      model: {}
    }
  },
  methods: {
    // 接收cnc数据
    getCNCModel4 (model) {
      // console.log('父组件接收的数据 : ' + JSON.stringify(model))
      this.model = model
      this.option = {
        title: {
          text: '主轴',
          style: {
            fill: '#fff'
          }
        },
        series: [
          {
            type: 'gauge',
            data: [ { name: '负载', value: this.model.spindleload } ],
            center: ['50', '60'],
            min: 0,
            max: 100,
            splitNum: 6,
            axisLabel: {
              formatter: '{value}',
              style: {
                fill: '#fff'
              }
            },
            axisTick: {
              style: {
                stroke: '#fff'
              }
            },
            animationCurve: 'easeInOutBack'
          }
        ]
      }
      this.option1 = {
        title: {
          text: 'X轴',
          style: {
            fill: '#fff'
          }
        },
        series: [
          {
            type: 'gauge',
            data: [ { name: '负载', value: this.model.xload } ],
            center: ['50', '60'],
            min: 0,
            max: 100,
            splitNum: 6,
            axisLabel: {
              formatter: '{value}',
              style: {
                fill: '#fff'
              }
            },
            axisTick: {
              style: {
                stroke: '#fff'
              }
            },
            animationCurve: 'easeInOutBack'
          }
        ]
      }
      this.option2 = {
        title: {
          text: 'Y轴',
          style: {
            fill: '#fff'
          }
        },
        series: [
          {
            type: 'gauge',
            data: [ { name: '负载', value: this.model.yload } ],
            center: ['50', '60'],
            min: 0,
            max: 100,
            splitNum: 6,
            axisLabel: {
              formatter: '{value}',
              style: {
                fill: '#fff'
              }
            },
            axisTick: {
              style: {
                stroke: '#fff'
              }
            },
            animationCurve: 'easeInOutBack'
          }
        ]
      }
      this.option3 = {
        title: {
          text: 'Z轴',
          style: {
            fill: '#fff'
          }
        },
        series: [
          {
            type: 'gauge',
            data: [ { name: '负载', value: this.model.zload } ],
            center: ['50', '60'],
            min: 0,
            max: 100,
            splitNum: 6,
            axisLabel: {
              formatter: '{value}',
              style: {
                fill: '#fff'
              }
            },
            axisTick: {
              style: {
                stroke: '#fff'
              }
            },
            animationCurve: 'easeInOutBack'
          }
        ]
      }
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
  height:25%;// 修改过的
  background-color: rgba(6, 30, 93, 0.5);

  .dv-charts {
    width: 24%;
  }
}
</style>
