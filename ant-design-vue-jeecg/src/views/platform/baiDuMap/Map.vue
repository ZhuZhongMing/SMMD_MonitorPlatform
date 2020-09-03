<template>
  <div>
    <a-form :model="centerStr">
      <a-row>
        <a-col :span="8">
          <a-form-item label="经度" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input :value.sync="centerStr.lng"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="纬度" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input :value.sync="centerStr.lat"></a-input>
          </a-form-item>
        </a-col>
        <a-col :span="8">
          <a-form-item label="地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-input :value.sync="address"></a-input>
          </a-form-item>
        </a-col>
        <!--<a-col :span="6">
          <a-form-item label="" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <a-button type="primary">确定</a-button>
          </a-form-item>
        </a-col>-->
      </a-row>
    </a-form>
    <!--<baidu-map class="map" center="杭州" :zoom="19" :scroll-wheel-zoom="true">-->
    <!--<baidu-map @click="getClickInfo" @moving="syncCenterAndZoom" @moveend="syncCenterAndZoom"
               @zoomend="syncCenterAndZoom" class="map" :center="centerStr" :zoom="zoom" :scroll-wheel-zoom="true">-->
    <baidu-map @click="getClickInfo" class="map" :center="centerStr" :zoom="zoom" :scroll-wheel-zoom="true">
      <!--比例尺-->
      <bm-scale anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-scale>
      <!--图层-->
      <!--<bm-circle :center="centerStr" :radius="10000" stroke-color="gray" :stroke-opacity="0.5" fill-color="gray" :fill-opacity="0.5" :stroke-weight="1" :editing="false"></bm-circle>-->
      <!--<bm-view style="width: 100%; height:500px;"></bm-view>-->
      <!--缩放控件-->
      <bm-navigation anchor="BMAP_ANCHOR_TOP_RIGHT"></bm-navigation>
      <!--点-->
      <bm-marker :position="centerStr" :dragging="true" animation="BMAP_ANIMATION_BOUNCE">
      </bm-marker>　　　　　　　
      <bm-control :offset="{ width: '10px', height: '10px' }" style="padding-top: 10px;">
        <bm-auto-complete v-model="keyword" :sugStyle="{ zIndex: 999999 }" style="margin-left: 10px;">
          <a-input type="text" placeholder="请输入搜索关键字" class="serachinput"/>
        </bm-auto-complete>　　　
      </bm-control>　　　　
      <bm-local-search :keyword="keyword" :auto-viewport="true" style="width:0px;height:0px;overflow: hidden;"></bm-local-search>
    </baidu-map>
  </div>
</template>

<script>

  export default {
    name: 'Map',
    data () {
      return {
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
        address: ''
      }
    },
    mounted () {

    },
    methods: {
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
      }
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
  .map {
    width: 100%;
    height: 500px;
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