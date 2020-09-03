<template>
  <div>
    <!--鼠标右键点击事件-->
    <!-- @rightclick="changeCenter"-->
    <baidu-map class="map" :center="centerStr" :zoom="zoom" :scroll-wheel-zoom="true">
      <!--比例尺-->
      <bm-scale anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-scale>
      <!--图层-->
      <!--<bm-circle :center="centerStrCircle" :radius="10000" stroke-color="gray" :stroke-opacity="0.5" fill-color="gray" :fill-opacity="0.5" :stroke-weight="1" :editing="false">
      </bm-circle>-->
      <!--图层中心点-->
      <bm-marker :position="centerStrCircle" :dragging="false" title="中心点"></bm-marker>
      <!--缩放控件-->
      <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
      <!--点-->
      <!-- @click="changeShow(item)"-->
      <bm-marker v-for="(item,key) in points" :key="key" :position="item" :dragging="false" @click="changeShow(item)" :title="item.outletName" animation="BMAP_ANIMATION_BOUNCE"></bm-marker>
      <!--信息窗体-->　　　　　　　 　　　
      <bm-info-window :position="infoWindow.centerStrWindow" title="" :show="infoWindow.show" @close="infoWindowClose" @open="infoWindowOpen" style="padding: 15px;">
        <a-layout>
          <a-layout-content style="background-color: #FFFFFF; padding-right: 40px;">
            <div style="width: 350px;">
              <p v-if="'' != infoWindow.centerStrWindow.outletId && null != infoWindow.centerStrWindow.outletId">
                <span class="lable">编号：</span>
                <span class="content" v-text="infoWindow.centerStrWindow.outletId"></span>
              </p>
              <p v-if="'' != infoWindow.centerStrWindow.outletName && null != infoWindow.centerStrWindow.outletName">
                <span class="lable">名称：</span>
                <span class="content" v-text="infoWindow.centerStrWindow.outletName"></span>
              </p>
              <p v-if="'' != infoWindow.centerStrWindow.installtionSite && null != infoWindow.centerStrWindow.installtionSite">
                <span class="lable">地址：</span>
                <span class="content" v-text="infoWindow.centerStrWindow.installtionSite"></span>
              </p>
            </div>
          </a-layout-content>
          <!--<a-layout-sider theme="light" :width="300">
            <h3>设备图片</h3>
            <img :src="getImgView(infoWindow.centerStrWindow.equipmentImage)" height="200px" width="300px" alt="" style="max-width:6000px;font-size: 12px;font-style: italic;"/>
          </a-layout-sider>-->
        </a-layout>
      </bm-info-window>
    </baidu-map>
  </div>
</template>

<script>
  import {getAction, getFileAccessHttpUrl} from '@/api/manage'
  import DetailList from '@/components/tools/DetailList'
  import PageContent from '../../../components/layouts/PageView'
  import Area from '@/components/_util/Area'
  import { loadCategoryData } from '@/api/api'

  const DetailListItem = DetailList.Item
  export default {
    name: 'Map',
    components: {
      PageContent,
      DetailList,
      DetailListItem,
    },
    data () {
      return {
        infoWindow: {
          centerStrWindow:{
            lng: 120.219396, lat: 30.297149
          },
          show: false,
          contents: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.'
        },
        points: [],
        zoom: 12,
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 }
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 }
        },
        showMapComponent: this.value,
        keyword: '',
        mapStyle: {
          width: '100%',
          height: this.mapHeight + 'px'
        },
        centerStr: {
          lng: 120.219396, lat: 30.297149
        },
        centerStrCircle: {
          lng: 120.219396, lat: 30.297149
        },
        address: '',
        url: {
          queryNear: "/system/scOutlet/queryNear"
        },
        dictOptions:{},
        pcaData:''
      }
    },
    created() {
      this.pcaData = new Area()
    },
    mounted () {
      this.initDictConfig()
      this.queryEquipmentOfCenter()
    },
    methods: {
      /*获取中间点范围内的设备*/
      queryEquipmentOfCenter() {
        var param = this.centerStr
        param.radius = 40000 // m 为单位
        getAction(this.url.queryNear,param).then((res)=>{
          if(res.success){
            this.points = []
            setTimeout(() => {
              this.points = res.result
            },500)
          }
        })
      },
      /* 图片预览 */
      getImgView(text){
        if(text && text.indexOf(",")>0){
          text = text.substring(0,text.indexOf(","))
        }
        return getFileAccessHttpUrl(text)
      },
      infoWindowClose (e) {
        this.infoWindow.show = false
      },
      infoWindowOpen (e) {
        this.infoWindow.show = true
      },
      changeShow (item) {
        this.infoWindow.centerStrWindow = item
        if (!this.infoWindow.show) {
          this.infoWindow.show = true
        }
      },
      changeCenter (e) {
        var that = this
        this.centerStrCircle.lng = e.point.lng
        this.centerStrCircle.lat = e.point.lat
        this.zoom = e.target.getZoom()
        this.infoWindowClose()

        getAction(this.url.queryEquitmentByCoordinate,this.centerStrCircle).then((res)=>{
          if(res.success){
            this.points = []
            setTimeout(() => {
              this.points = res.result

            },500)
          }
        })

      },
      getClickInfo (e) {
        var that = this
        this.centerStr.lng = e.point.lng
        this.centerStr.lat = e.point.lat
        this.zoom = e.target.getZoom()
        var geoc = new BMap.Geocoder()  //地址解析对象
        geoc.getLocation(e.point, function (rs) {
          // console.log(rs)
          that.address = rs.address
        })
      },
      syncCenterAndZoom (e) {
        const { lng, lat } = e.target.getCenter()
        this.centerStr.lng = lng
        this.centerStr.lat = lat
        this.zoom = e.target.getZoom()
      },
      changeAddress(lng,lat,address) {
        this.centerStr.lng = lng
        this.centerStr.lat = lat
        this.address = address
      },
      getPcaText(code){
        return this.pcaData.getText(code);
      },
      initDictConfig(){
        loadCategoryData({code:'B03'}).then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'equipmentType', res.result)
          }
        })
      },
    },
    watch: {
      value: function (currentValue) {
        this.showMapComponent = currentValue
        console.log('currentValue : ' + currentValue)
        if (currentValue) {
          this.keyword = ''
        }
      }
    }
  }
</script>

<style scoped>
  .lable {
    font-size: 14px;
    text-align: right;
    width: 20%;
    display: inline-block;
    color: #444444;
  }
  .content {
    display: inline-block;
    width: 80%;
    font-size: 16px;
  }
  .map {
    width: 100%;
    height: 800px;
  }

  .BMap_cpyCtrl {
    display: none;
  }

  .anchorBL {
    display: none;
  }

  .head {
    margin-left: 200px;
    width: 100px;
  }

  /*.baiduMap {
   !* width: 80%;
    height: 800px;*!
    margin: 0 auto;
    overflow: hidden;
  }
  .ivu-form-item {
    display: inline-block;
    width: 40%;
   }
  .bm-view > div {
    width: 100%;
    height: 700px !important;
  }
  .serachinput {
    width: 300px;
    box-sizing: border-box;
    padding: 9px;
    border: 1px solid #dddee1;
    line-height: 20px;
    font-size: 16px;
    height: 38px;
    color: #333;
    position: relative;
    border-radius: 4px;
    -webkit-box-shadow: #666 0px 0px 10px;
    -moz-box-shadow: #666 0px 0px 10px;
    box-shadow: #666 0px 0px 10px;
   }*/
</style>