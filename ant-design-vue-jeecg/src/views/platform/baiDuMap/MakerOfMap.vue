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
      <bm-marker v-for="(item,key) in points" :key="key" :position="item" :dragging="false" @click="changeShow(item)" :title="item.equipmentName" animation="BMAP_ANIMATION_BOUNCE"></bm-marker>
      <!--信息窗体-->　　　　　　　 　　　
      <bm-info-window :position="infoWindow.centerStrWindow" title="" :show="infoWindow.show" @close="infoWindowClose" @open="infoWindowOpen" style="padding: 15px;">
        <a-layout>
          <a-layout-content style="background-color: #FFFFFF; padding-right: 40px;">
            <!--<detail-list title="退款申请" style="width: 350px;">
              <detail-list-item term="取货单号">1000000000</detail-list-item>
              <detail-list-item term="状态">已取货</detail-list-item>
            </detail-list>-->
            <div style="width: 350px;">
              <p v-if="'' != infoWindow.centerStrWindow.id && null != infoWindow.centerStrWindow.id">
                <span class="lable">设备ID：</span>
                <span class="content" v-text="infoWindow.centerStrWindow.id"></span>
              </p>
              <p v-if="'' != infoWindow.centerStrWindow.equipmentName && null != infoWindow.centerStrWindow.equipmentName">
                <span class="lable">设备名称：</span>
                <span class="content" v-text="infoWindow.centerStrWindow.equipmentName"></span>
              </p>
              <p v-if="'' != infoWindow.centerStrWindow.equipmentTypeName && null != infoWindow.centerStrWindow.equipmentTypeName">
                <span class="lable">设备类型：</span>
                <span class="content" v-text="infoWindow.centerStrWindow.equipmentTypeName"></span>
              </p>
              <p v-if="'' != infoWindow.centerStrWindow.deployAddress && null != infoWindow.centerStrWindow.deployAddress">
                <span class="lable">安装地点：</span>
                <span class="content" v-text="infoWindow.centerStrWindow.deployAddress"></span>
              </p>
              <p v-if="'' != infoWindow.centerStrWindow.deployTime && null != infoWindow.centerStrWindow.deployTime">
                <span class="lable">安装时间：</span>
                <span class="content" v-text="infoWindow.centerStrWindow.deployTime"></span>
              </p>
              <p v-if="'' != infoWindow.centerStrWindow.status && null != infoWindow.centerStrWindow.status">
                <span class="lable">设备状态：</span>
                <span class="content" v-text="infoWindow.centerStrWindow.status"></span>
              </p>
              <p v-if="'' != infoWindow.centerStrWindow.equipmentScore && null != infoWindow.centerStrWindow.equipmentScore">
                <span class="lable">设备评分：</span>
                <span class="content" v-text="infoWindow.centerStrWindow.equipmentScore"></span>
              </p>
              <p v-if="'' != infoWindow.centerStrWindow.remarks && null != infoWindow.centerStrWindow.remarks">
                <span class="lable">备注：</span>
                <span class="content" v-text="infoWindow.centerStrWindow.remarks"></span>
              </p>
            </div>
          </a-layout-content>
          <a-layout-sider theme="light" :width="300">
            <h3>设备图片</h3>
            <img :src="getImgView(infoWindow.centerStrWindow.equipmentImage)" height="200px" width="300px" alt="" style="max-width:6000px;font-size: 12px;font-style: italic;"/>
          </a-layout-sider>
        </a-layout>
      </bm-info-window>
    </baidu-map>
  </div>
</template>

<script>
  import {getAction, getFileAccessHttpUrl} from '@/api/manage'
  import DetailList from '@/components/tools/DetailList'
  import PageContent from '../../../components/layouts/PageView'

  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'
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
          queryEquitmentByCoordinate: "/system/mpiEquipment/queryEquitmentByCoordinate"
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
        getAction(this.url.queryEquitmentByCoordinate,this.centerStr).then((res)=>{
          if(res.success){
            /*this.points.splice(0)
            for (let i = 0; i < res.result.length; i++) {
              this.points.push(res.result[i])
            }*/
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
        //console.log(e)
        /*this.infoWindow.centerStrWindow.lng = e.point.lng
        this.infoWindow.centerStrWindow.lat = e.point.lat*/
        this.infoWindow.centerStrWindow = item
        //console.log("id : " + item)
        //console.log("item : " + JSON.stringify(this.infoWindow.centerStrWindow))
        if (!this.infoWindow.show) {
          this.infoWindow.show = true
        }
        var text = filterMultiDictText(this.dictOptions['equipmentType'], this.infoWindow.centerStrWindow.equipmentType)
        this.infoWindow.centerStrWindow.equipmentTypeName = text
      },
      changeCenter (e) {
        var that = this
        this.centerStrCircle.lng = e.point.lng
        this.centerStrCircle.lat = e.point.lat
        this.zoom = e.target.getZoom()
        this.infoWindowClose()
        //console.log(this.centerStrCircle)

        getAction(this.url.queryEquitmentByCoordinate,this.centerStrCircle).then((res)=>{
          if(res.success){
            /*this.points.splice(0)
            for (let i = 0; i < res.result.length; i++) {
              this.points.push(res.result[i])
            }*/
            this.points = []
            setTimeout(() => {
              this.points = res.result
              //console.log("result : " + JSON.stringify(res.result))
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
    width: 25%;
    display: inline-block;
    color: #444444;
  }
  .content {
    display: inline-block;
    width: 75%;
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