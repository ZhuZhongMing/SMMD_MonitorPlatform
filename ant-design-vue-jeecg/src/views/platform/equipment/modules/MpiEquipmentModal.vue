<template>
  <j-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    switchFullscreen
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-row>
          <a-col :span="12">
            <a-form-item label="设备名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['equipmentName', validatorRules.equipmentName]" placeholder="请输入设备名称"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="网关编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <!--<j-dict-select-tag type="list" v-decorator="['gatewayId', validatorRules.gatewayId]" :trigger-change="true" dictCode="" placeholder="请选择网关编号"/>-->
              <!-- 使计算函数作用 -->
              <a-select v-decorator="['gatewayId', validatorRules.gatewayId]" placeholder="请选择网关编号">
                <a-select-option v-for="item in gatewayList" :key="item.id" :value="item.id">{{item.gatewayName}}</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row>
          <a-col :span="12">
            <a-form-item label="设备类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <!--<a-input v-decorator="['equipmentType', validatorRules.equipmentType]" placeholder="请输入设备类型"></a-input>-->
              <j-category-select v-decorator="['equipmentType', validatorRules.equipmentType]" pcode="B03" placeholder="请选择设备类型" />
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="通讯方式" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['comType']" placeholder="请输入通讯方式"></a-input>
            </a-form-item>
          </a-col>
          <!--<a-col :span="12">
            <a-form-item label="一级地区" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-area-linkage type="cascader" v-decorator="['level1Region']" placeholder="请输入省市区"/>
            </a-form-item>
          </a-col>-->
        </a-row>

        <!--<a-row>
          <a-col :span="12">
            <a-form-item label="二级地区" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-area-linkage type="cascader" v-decorator="['level2Region']" placeholder="请输入省市区"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="三级地区" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-area-linkage type="cascader" v-decorator="['level3Region']" placeholder="请输入省市区"/>
            </a-form-item>
          </a-col>
        </a-row>-->

        <a-row>
          <a-col :span="12">
            <a-form-item label="安装时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择安装时间" v-decorator="['deployTime']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="安装地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['deployAddress', validatorRules.deployAddress]" placeholder="安装地址" style="width: 80%;" disabled></a-input>
              <a-button style="width: 20%;" @click="showModal">地图</a-button>
              <!--<j-area-linkage type="cascader" v-decorator="['deployAddress', validatorRules.deployAddress]" placeholder="请输入省市区"/>-->
              <!--<Map></Map>-->
              <!--<a-row>
                <a-col :span="16">
                  <a-form-item label="" labelCol="0" wrapperCol="24">
                    <a-input v-decorator="['deployAddress']" placeholder="请输入安装地址"></a-input>
                  </a-form-item>
                </a-col>
                <a-col :span="8">
                  <a-form-item label="" labelCol="2" wrapperCol="22">
                    <a-button>地图</a-button>
                  </a-form-item>
                </a-col>
              </a-row>-->

            </a-form-item>
          </a-col>
        </a-row>

        <a-row>
          <a-col :span="12">
            <a-form-item label="经度" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['longitude', validatorRules.longitude]" placeholder="经度" style="width: 100%" disabled/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="纬度" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['latitude', validatorRules.latitude]" placeholder="纬度" style="width: 100%" disabled/>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row>
          <!--<a-col :span="12">
            <a-form-item label="通讯方式" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['comType']" placeholder="请输入通讯方式"></a-input>
            </a-form-item>
          </a-col>-->
          <a-col :span="12">
            <a-form-item label="产能系数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['productCoefficient']" placeholder="请输入产能系数" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="维护规则编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['maintainRule']" placeholder="请输入维护规则编号"></a-input>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row>
          <!--<a-col :span="12">
            <a-form-item label="维护规则编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['maintainRule']" placeholder="请输入维护规则编号"></a-input>
            </a-form-item>
          </a-col>-->
          <a-col :span="12">
            <a-form-item label="设备评分" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input-number v-decorator="['equipmentScore', validatorRules.equipmentScore]" placeholder="请输入设备评分" style="width: 100%"/>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row>
          <a-col :span="12">
            <a-form-item label="设备图片" :labelCol="labelCol" :wrapperCol="wrapperCol">
            <j-image-upload isMultiple v-decorator="['equipmentImage']"></j-image-upload>
          </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-textarea v-decorator="['remarks']" rows="4" placeholder="请输入备注"/>
            </a-form-item>
          </a-col>
        </a-row>

        <!--<a-form-item label="设备状态0-离线,1-在线" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['status']" :trigger-change="true" dictCode="status_mpi" placeholder="请选择设备状态0-离线,1-在线"/>
        </a-form-item>-->

        <!--<a-form-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['createBy']" placeholder="请输入创建人"></a-input>
        </a-form-item>
        <a-form-item label="创建时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择创建时间" v-decorator="['createTime']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="修改人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['updateBy']" placeholder="请输入修改人"></a-input>
        </a-form-item>
        <a-form-item label="修改时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择修改时间" v-decorator="['updateTime']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="删除标识0-正常,1-已删除" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['delFlag']" :trigger-change="true" dictCode="del_flag" placeholder="请选择删除标识0-正常,1-已删除"/>
        </a-form-item>-->

      </a-form>
    </a-spin>
    <!--地图弹窗-->
    <a-modal
      :width="800"
      title="地图"
      :visible="mapVisible"
      :confirm-loading="confirmLoading"
      @ok="mapOk"
      @cancel="mapCancel"
    >
      <Map ref="Map"></Map>
    </a-modal>
  </j-modal>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'
  import JImageUpload from '@/components/jeecg/JImageUpload'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"
  import JAreaLinkage from '@comp/jeecg/JAreaLinkage'
  import JCategorySelect from '@/components/jeecg/JCategorySelect'
  import Map from "@/views/platform/baiDuMap/Map"
  import ARow from 'ant-design-vue/es/grid/Row'


  export default {
    name: "MpiEquipmentModal",
    components: {
      ARow,
      JDate,
      JImageUpload,
      JDictSelectTag,
      JAreaLinkage,
      JCategorySelect,
      Map
    },
    data () {
      return {
        /*网关编号*/
        gatewayIds: '',
        /* 网关信息列表 */
        gatewayList: [],
        form: this.$form.createForm(this),
        title:"操作",
        width:950,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
          deployAddress: {
            rules: [
              { required: true, message: '请选择安装地址!'},
            ]
          },
          equipmentName: {
            rules: [
              { required: true, message: '请输入设备名称!'},
            ]
          },
          gatewayId: {
            rules: [
              { required: true, message: '请输入网关编号!'},
            ]
          },
          equipmentType: {
            rules: [
              { required: true, message: '请选择设备类型!'},
            ]
          },
          longitude: {
            rules: [
              { required: true, message: '请选择安装地址!'},
              { pattern: /^-?\d+\.?\d*$/, message: '请输入数字!'},
            ]
          },
          latitude: {
            rules: [
              { required: true, message: '请选择安装地址!'},
              { pattern: /^-?\d+\.?\d*$/, message: '请输入数字!'},
            ]
          },
          equipmentScore: {
            rules: [
              { pattern: /^-?\d+$/, message: '请输入整数!'},
            ]
          },
        },
        url: {
          add: "/system/mpiEquipment/add",
          edit: "/system/mpiEquipment/edit",
          queryAllGateway: "system/mpiGateway/allList" // 全查询网关信息
        },
        lat: '',
        lng: '',
        address: '',
        mapVisible: false,
      }
    },
    computed: {
    },
    created () {
    },
    mounted () {
      this.queryAllGateway()
    },
    methods: {
      /* 全查询网关信息 */
      queryAllGateway() {
        getAction(this.url.queryAllGateway,null).then((res)=>{
          if(res.success){
            /*that.$message.success(res.message);
            that.$emit('ok');*/
            //console.log("result : " + JSON.stringify(res.result))
            this.gatewayList = res.result
            this.$nextTick(() => {
              this.form.setFieldsValue({"gatewayId" : this.gatewayIds});
            })
          }else{
            that.$message.warning(res.message);
          }
        })
      },
      add () {
        this.queryAllGateway()
        this.edit({});
      },
      edit (record) {
        if (record.id != undefined && record.id != null && record.id != "") {
          this.lng = record.longitude
          this.lat = record.latitude
          this.address = record.deployAddress
        }
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'equipmentName','gatewayId','equipmentType','level1Region','level2Region','level3Region','deployTime','deployAddress','longitude','latitude','comType','productCoefficient','status','maintainRule','equipmentImage','equipmentScore','remarks','createBy','createTime','updateBy','updateTime','delFlag'))
        })
      },
      close () {
        this.$emit('close');
        this.visible = false;
      },
      handleOk () {
        const that = this;
        // 触发表单验证
        this.form.validateFields((err, values) => {
          if (!err) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            let formData = Object.assign(this.model, values);
            console.log("表单提交数据",formData)
            httpAction(httpurl,formData,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }

        })
      },
      handleCancel () {
        this.close()
      },
      popupCallback(row){
        this.form.setFieldsValue(pick(row,'equipmentName','gatewayId','equipmentType','level1Region','level2Region','level3Region','deployTime','deployAddress','longitude','latitude','comType','productCoefficient','status','maintainRule','equipmentImage','equipmentScore','remarks','createBy','createTime','updateBy','updateTime','delFlag'))
      },
      mapOk() {
        this.$nextTick(() => {
          this.form.setFieldsValue({"deployAddress" : this.$refs.Map.address});
          this.form.setFieldsValue({"longitude" : this.$refs.Map.centerStr.lng});
          this.form.setFieldsValue({"latitude" : this.$refs.Map.centerStr.lat});
        })
        this.mapVisible = false;
        this.lng = this.$refs.Map.centerStr.lng
        this.lat = this.$refs.Map.centerStr.lat
        this.address = this.$refs.Map.address
        /*console.log('经度：' + this.$refs.Map.centerStr.lng);
        console.log('纬度：' + this.$refs.Map.centerStr.lat);
        console.log('地址：' + this.$refs.Map.address);*/
      },
      mapCancel() {
        /*this.lng = ""
        this.lat = ""
        this.address = ""*/
        this.mapVisible = false;
      },
      showModal() {
        this.mapVisible = true;
        if (this.lat != "") {
          setTimeout(() => {
            this.$refs.Map.changeAddress(this.lng, this.lat, this.address)
          },500)
        }
      },


    }
  }
</script>