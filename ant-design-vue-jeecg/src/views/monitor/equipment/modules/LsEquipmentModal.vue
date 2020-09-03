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
              <a-input v-decorator="['ename', validatorRules.ename]" placeholder="请输入设备名称"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="网关编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <!--<j-dict-select-tag type="list" v-decorator="['wgid', validatorRules.wgid]" :trigger-change="true" dictCode="" placeholder="请选择网关编号"/>-->
              <a-select type="list" v-decorator="['wgid', validatorRules.wgid]" placeholder="请选择网关编号">
                <a-select-option v-for="item in gatewayList" :key="item.id" :value="item.id">{{item.wgname}}</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>

        <!--<a-form-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['createBy']" placeholder="请输入创建人"></a-input>
        </a-form-item>
        <a-form-item label="创建日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择创建日期" v-decorator="['createTime']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="更新人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['updateBy']" placeholder="请输入更新人"></a-input>
        </a-form-item>
        <a-form-item label="更新日期" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择更新日期" v-decorator="['updateTime']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
        </a-form-item>-->

        <a-row>
          <a-col :span="12">
            <a-form-item label="设备类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <!--<j-dict-select-tag type="list" v-decorator="['etypeid', validatorRules.etypeid]" :trigger-change="true" dictCode="" placeholder="请选择设备类型"/>-->
              <a-select type="list" v-decorator="['etypeid', validatorRules.etypeid]" placeholder="请选择设备类型">
                <a-select-option v-for="item in typeList" :key="item.id" :value="item.id">{{item.etname}}</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="一级地区" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-area-linkage type="cascader" v-decorator="['levelarea1']" placeholder="请输入省市区"/>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row>
          <a-col :span="12">
            <a-form-item label="二级地区" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-area-linkage type="cascader" v-decorator="['levelarea2']" placeholder="请输入省市区"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="三级地区" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-area-linkage type="cascader" v-decorator="['levelarea3']" placeholder="请输入省市区"/>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row>
          <a-col :span="12">
            <a-form-item label="安装时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <j-date placeholder="请选择安装时间" v-decorator="['installtime']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="安装地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['installaddress']" placeholder="请输入安装地址"></a-input>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row>
          <a-col :span="12">
            <a-form-item label="精度" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['eprecision']" placeholder="请输入精度"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="维度" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['dimensionality']" placeholder="请输入维度"></a-input>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row>
          <a-col :span="12">
            <a-form-item label="通讯方式" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['communicationmode', validatorRules.communicationmode]" placeholder="请输入通讯方式"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="产能系数" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['capacitycoefficient']" placeholder="请输入产能系数"></a-input>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row>
          <a-col :span="12">
            <a-form-item label="维护规则编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['mrid']" placeholder="请输入维护规则编号"></a-input>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item label="设备评分" :labelCol="labelCol" :wrapperCol="wrapperCol">
              <a-input v-decorator="['egrade']" placeholder="请输入设备评分"></a-input>
            </a-form-item>

          </a-col>
        </a-row>

        <a-row>
          <a-col :span="24">
            <a-form-item label="设备图片" :labelCol="labelCol1" :wrapperCol="wrapperCol1">
              <j-image-upload isMultiple v-decorator="['epicture']"></j-image-upload>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row>
          <a-col :span="24">
            <a-form-item label="备注" :labelCol="labelCol1" :wrapperCol="wrapperCol1">
              <a-textarea v-decorator="['disp']" rows="4" placeholder="请输入备注"/>
            </a-form-item>
          </a-col>
        </a-row>

        <!--<a-form-item label="设备状态0-离线,1-在线" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['estate']" :trigger-change="true" dictCode="estate" placeholder="请选择设备状态0-离线,1-在线"/>
        </a-form-item>-->

        <!--<a-form-item label="删除标识0-正常,1-已删除" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['delFlag']" :trigger-change="true" dictCode="del_flag" placeholder="请选择删除标识0-正常,1-已删除"/>
        </a-form-item>-->

      </a-form>
    </a-spin>
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
  import ARow from 'ant-design-vue/es/grid/Row'
  import ACol from 'ant-design-vue/es/grid/Col'


  export default {
    name: "LsEquipmentModal",
    components: {
      ACol,
      ARow,
      JDate,
      JImageUpload,
      JDictSelectTag,
      JAreaLinkage,
    },
    data () {
      return {
        /*设备信息列表*/
        typeList: [],
        /*网关信息列表*/
        gatewayList: [],
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
        visible: false,
        model: {},
        labelCol: {
          xs: { span: 12 },
          sm: { span: 8 },
        },
        wrapperCol: {
          xs: { span: 12 },
          sm: { span: 16 },
        },
        labelCol1: {
          xs: { span: 12 },
          sm: { span: 4 },
        },
        wrapperCol1: {
          xs: { span: 12 },
          sm: { span: 20 },
        },
       /* labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },*/
        confirmLoading: false,
        validatorRules: {
          ename: {
            rules: [
              { required: true, message: '请输入设备名称!'},
            ]
          },
          wgid: {
            rules: [
              { required: true, message: '请输入网关编号!'},
            ]
          },
          etypeid: {
            rules: [
              { required: true, message: '请输入设备类型!'},
            ]
          },
          communicationmode: {
            rules: [
              { required: true, message: '请输入通讯方式!'},
            ]
          },
        },
        url: {
          add: "/system/lsEquipment/add",
          edit: "/system/lsEquipment/edit",
          queryEquipmentTypeList: "/system/lsEquipmentType/allList",
          queryGatewayList: "/system/lsGateway/allList"
        }
      }
    },
    created () {
    },
    mounted () {
      this.queryEquipmentTypeList()
      this.queryGatewayList()
    },
    methods: {
      /* 全查询设备类型 */
      queryEquipmentTypeList() {
        getAction(this.url.queryEquipmentTypeList,null).then((res)=>{
          if(res.success){
            /*that.$message.success(res.message);
            that.$emit('ok');*/
            //console.log("result : " + JSON.stringify(res.result))
            this.typeList = res.result
          }else{
            that.$message.warning(res.message);
          }
        })
      },
      /* 全查询网关信息 */
      queryGatewayList() {
        getAction(this.url.queryGatewayList,null).then((res)=>{
          if(res.success){
            /*that.$message.success(res.message);
            that.$emit('ok');*/
            //console.log("result : " + JSON.stringify(res.result))
            this.gatewayList = res.result
          }else{
            that.$message.warning(res.message);
          }
        })
      },
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'createBy','createTime','updateBy','updateTime','ename','wgid','etypeid','levelarea1','levelarea2','levelarea3','installtime','installaddress','eprecision','dimensionality','communicationmode','capacitycoefficient','estate','mrid','epicture','egrade','disp','delFlag'))
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
        this.form.setFieldsValue(pick(row,'createBy','createTime','updateBy','updateTime','ename','wgid','etypeid','levelarea1','levelarea2','levelarea3','installtime','installaddress','eprecision','dimensionality','communicationmode','capacitycoefficient','estate','mrid','epicture','egrade','disp','delFlag'))
      },


    }
  }
</script>