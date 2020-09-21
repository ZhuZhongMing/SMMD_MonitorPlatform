<template>
  <div id="index">
    <dv-full-screen-container class="bg">
      <dv-loading v-if="loading">Loading...</dv-loading>
      <div v-else class="host-body">
        <div class="d-flex jc-center">
          <dv-decoration-10 style="width:33.3%;height:.0625rem;" />
          <div class="d-flex jc-center">
            <dv-decoration-8 :color="['#568aea', '#000000']" style="width:2.5rem;height:.625rem;" />
            <div class="title">
              <span class="title-text">生产设备详情</span>
              <dv-decoration-6
                class="title-bototm"
                :reverse="true"
                :color="['#50e3c2', '#67a1e5']"
                style="width:3.125rem;height:.1rem;"
              />
            </div>
            <dv-decoration-8
              :reverse="true"
              :color="['#568aea', '#000000']"
              style="width:2.5rem;height:.625rem;"
            />
          </div>
          <dv-decoration-10 style="width:33.3%;height:.0625rem; transform: rotateY(180deg);" />
        </div>

        <!-- 第二行 -->
        <div class="d-flex jc-between px-2">
          <div class="d-flex" style="width: 40%">
            <div
              class="react-right ml-4"
              style="width: 6.25rem; text-align: left;background-color: #0f1325;"
            >
              <span class="react-before"></span>
              <span class="text"></span>
            </div>
            <div class="react-right bg-color-blue ml-3">
            <!--<div class="react-right ml-3" style="background-color: #0f1325;">-->
              <!--<span class="text colorBlue">数据分析2</span>-->
              <span class="text fw-b">当前设备：{{equipment.equipmentName}}</span>
            </div>
          </div>
          <div style="width: 40%" class="d-flex">
            <div class="react-left ml-3" style="background-color: #0f1325;">
            <!--<div class="react-left bg-color-blue mr-3">-->
              <span class="text fw-b"></span>
            </div>
            <div
              class="react-left mr-4"
              style="width: 6.25rem; background-color: #0f1325; text-align: right;"
            >
              <span class="react-after"></span>
              <span class="text" v-text="nowTime"></span>
            </div>
          </div>
        </div>

        <div class="body-box">
          <!-- 第三行数据 -->
          <div class="content-box">

            <!-- 中间 -->
            <div>
              <dv-border-box-12>
                <center @getEquipment="getEquipment" @getCount="getCount" />
              </dv-border-box-12>
            </div>
            <!-- 中间 -->

            <div>
              <dv-border-box-10>
                <centerLeft1 />
              </dv-border-box-10>
            </div>
            <div>
              <dv-border-box-12>
                <centerLeft2 ref="centerLeft2" />
              </dv-border-box-12>
            </div>

            <div>
              <centerRight2 ref="centerRight2" />
            </div>
            <div>
              <dv-border-box-13>
                <centerRight1 />
              </dv-border-box-13>
            </div>
          </div>

          <!-- 第四行数据 -->
          <div class="bototm-box">
            <dv-border-box-13>
              <bottomLeft ref="bottomLeft" />
            </dv-border-box-13>
            <dv-border-box-12>
              <bottomRight ref="bottomRight" />
            </dv-border-box-12>
          </div>
        </div>
      </div>
    </dv-full-screen-container>
  </div>
</template>

<script>
import centerLeft1 from "./centerLeft1";
import centerLeft2 from "./centerLeft2";
import centerRight1 from "./centerRight1";
import centerRight2 from "./centerRight2";
import center from "./center";
import bottomLeft from "./bottomLeft";
import bottomRight from "./bottomRight";
import { getRequest } from '@/config/config'
export default {
  data() {
    return {
      nowTime: '',
      loading: true,
      equipment: {},
      /*当前数据*/
      pressModel: '',
      url: {
        queryByEquipmentId: '/system/qf/queryByEquipmentId'
      }
    };
  },
  components: {
    centerLeft1,
    centerLeft2,
    centerRight1,
    centerRight2,
    center,
    bottomLeft,
    bottomRight
  },
  mounted() {
    this.cancelLoading();
    setInterval(() =>{
      this.getNowTime()
    },1000)
  },
  methods: {
    cancelLoading() {
      setTimeout(() => {
        this.loading = false;
      }, 500);
    },
    /*获取数量*/
    getCount(data) {
      this.$refs.centerRight2.getData(data)
      this.$refs.bottomRight.getDataOfBottom(data)
    },
    /*获取当前设备信息*/
    getEquipment(item) {
      this.equipment = item
      this.getQFPressModel()
    },
    getQFPressModel() {
      var param = {
        equipmentsn : this.equipment.id
      }
      // console.log(param)
      getRequest(this.url.queryByEquipmentId, param).then(res => {
        this.pressModel = res.data.result
        this.$refs.centerLeft2.getPressModel2(this.pressModel)
        this.$refs.bottomLeft.getPressModel3(this.pressModel)
      }).catch(exc => {
        console.log('设备数据发生异常！异常信息：' + exc)
      })
    },
    getNowTime() {
      var str; //colorfoot
      var objD = new Date()
      var yy = objD.getFullYear();
      var MM = objD.getMonth()+1;
      if(MM<10) MM = '0' + MM;
      var dd = objD.getDate();
      if(dd<10) dd = '0' + dd;
      var hh = objD.getHours();
      if(hh<10) hh = '0' + hh;
      var mm = objD.getMinutes();
      if(mm<10) mm = '0' + mm;
      var ss = objD.getSeconds();
      if(ss<10) ss = '0' + ss;
      var ww = objD.getDay();
      /*if  ( ww==0 )  colorhead="<font color=\"#ffffff\">";
      if  ( ww > 0 && ww < 6 )  colorhead="<font color=\"#ffffff\">";
      if  ( ww==6 )  colorhead="<font color=\"#ffffff\">";*/
      if  (ww==0)  ww="星期日";
      if  (ww==1)  ww="星期一";
      if  (ww==2)  ww="星期二";
      if  (ww==3)  ww="星期三";
      if  (ww==4)  ww="星期四";
      if  (ww==5)  ww="星期五";
      if  (ww==6)  ww="星期六";
      /*colorfoot="</font>"*/
      str = yy + "-" + MM + "-" + dd + " " + hh + ":" + mm + ":" + ss + "  " + ww /* + colorfoot*/;
      this.nowTime = str
    }
  }
};
</script>

<style lang="scss">
@import "../assets/scss/index.scss";
</style>
