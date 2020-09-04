<template>
  <div id="data-view">
    <dv-full-screen-container>
      <top-header />
      <div class="main-content">
        <!--隐藏-->
        <!--<digital-flop />-->

        <div class="block-left-right-content">
          <ranking-board ref="rankingBoard" @getEquipmentId="getEquipmentId"/>

          <div class="block-top-bottom-content">
            <machineInfo ref="machineInfo" style="margin-bottom: 10px;"/>
            <axle ref="axle" style="margin-bottom: 10px;" />
            <div class="block-top-content">
                <rose-chart />

                <!--<water-level-chart />-->

                <scroll-board  ref="scrollBoard"/>
            </div>

          </div>
          <right ref="right" />
        </div>
      </div>
    </dv-full-screen-container>
  </div>
</template>

<script>
import topHeader from './topHeader'
import digitalFlop from './digitalFlop'
import rankingBoard from './rankingBoard'
import roseChart from './roseChart'
import scrollBoard from './scrollBoard'
import machineInfo from './machineInfo'
import axle from './axle'
import right from './right'
import { getRequest } from '../../config/config'

export default {
  name: 'DataView',
  components: {
    topHeader,
    // eslint-disable-next-line vue/no-unused-components
    digitalFlop,
    rankingBoard,
    roseChart,
    scrollBoard,
    machineInfo,
    axle,
    right
  },
  data () {
    return {
      /* 当前选中的设备信息 */
      equipment: '',
      oldEquipment: '',
      url: {
        queryByEquipmentId: '/system/cnc/queryByEquipmentId'
      }
    }
  },
  methods: {
    /* 选择的设备信息 */
    getEquipmentId (item) {
      // console.log(item)
      this.equipment = item
      var param = { 'cncsn': this.equipment.id }
      getRequest(this.url.queryByEquipmentId, param).then(res => {
        if (res.data.result) {
          // console.log('result : ' + JSON.stringify(res.data.result))
          this.$refs.rankingBoard.getCNCModel1(res.data.result)
          this.$refs.machineInfo.getCNCModel2(res.data.result)
          this.$refs.scrollBoard.getCNCModel3(res.data.result)
          this.$refs.axle.getCNCModel4(res.data.result)
          this.$refs.right.getCNCModel5(res.data.result)
          this.oldEquipment = this.equipment
        } else {
          this.$message({
            message: '当前设备暂未开启',
            type: 'warning'
          })
          this.equipment = this.oldEquipment
        }
      }).catch(exc => {
        console.log('查询当前设备CNC数据发生异常！异常信息：' + exc)
      })
    }
  },
  mounted () {
    setInterval(() => {
      if (this.equipment) {
        // console.log(JSON.stringify(this.equipment))
        this.getEquipmentId(this.equipment)
      }
    }, 1000)
  }
}
</script>

<style lang="less">
#data-view {
  max-width: 1920px;
  max-height: 1080px;
  width: 100%;
  height: 100%;
  background-color: #030409;
  color: #fff;

  #dv-full-screen-container {
    max-width: 1920px;
    max-height: 1080px;
    background-image: url('./img/bg.png');
    background-size: 100% 100%;
    box-shadow: 0 0 3px blue;
    display: flex;
    flex-direction: column;
  }

  .main-content {
    flex: 1;
    display: flex;
    flex-direction: column;
  }

  .block-left-right-content {
    flex: 1;
    display: flex;
    margin-top: 20px;
  }

  .block-top-bottom-content {
    width: 60%;
    flex: 1;
    display: flex;
    flex-direction: column;
    box-sizing: border-box;
    padding-left: 20px;
    padding-right: 20px;
  }

  .block-top-content {
    height: 58%;
    display: flex;
    flex-grow: 0;
    box-sizing: border-box;
    padding-bottom: 20px;
  }
}
</style>
