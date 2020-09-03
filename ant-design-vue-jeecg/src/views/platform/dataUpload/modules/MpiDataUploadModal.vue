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

        <a-form-item label="设备ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <!--<a-input v-decorator="['equipmentId', validatorRules.equipmentId]" placeholder="请输入设备ID"></a-input>-->
          <a-select :allowClear="true" v-decorator="['equipmentId', validatorRules.equipmentId]" placeholder="请选择设备编号" @change="handlChange">
            <a-select-option value="">请选择</a-select-option>
            <a-select-option v-for="item in equipmentList" :key="item.id" :value="item.id">{{item.equipmentName}}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="监测点ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <!--<a-input v-decorator="['monitorId', validatorRules.monitorId]" placeholder="请输入监测点ID"></a-input>-->
          <a-select :allowClear="true" v-decorator="['monitorId', validatorRules.monitorId]" placeholder="请选择设备编号">
            <a-select-option value="">请选择</a-select-option>
            <a-select-option v-for="item in siteList" :key="item.id" :value="item.id">{{item.monitorName}}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="主题" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['topic', validatorRules.topic]" placeholder="请输入主题"></a-input>
        </a-form-item>
        <a-form-item label="参数名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['paramName']" placeholder="请输入参数名称"></a-input>
        </a-form-item>
        <a-form-item label="参数值" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['paramValue']" placeholder="请输入参数值"></a-input>
        </a-form-item>
        <a-form-item label="参数数值" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['paramData']" placeholder="请输入参数数值" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="是否报警" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['isWarn']" :trigger-change="true" dictCode="is_warn" placeholder="请选择是否报警"/>
        </a-form-item>
        <a-form-item label="上传时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择上传时间" v-decorator="['uploadTime']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="返回值" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['returnCode']" placeholder="请输入返回值"></a-input>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea v-decorator="['remarks']" rows="4" placeholder="请输入备注"/>
        </a-form-item>
        <!--<a-form-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['createBy']" placeholder="请输入创建人"></a-input>
        </a-form-item>
        <a-form-item label="创建时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择创建时间" v-decorator="['creataTime']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="修改人" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['updateBy']" placeholder="请输入修改人"></a-input>
        </a-form-item>
        <a-form-item label="修改时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择修改时间" v-decorator="['updateTime']" :trigger-change="true" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="删除标识0-正常,1-删除" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['delFlag']" :trigger-change="true" dictCode="del_flag" placeholder="请选择删除标识0-正常,1-删除"/>
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
  import JDictSelectTag from "@/components/dict/JDictSelectTag"


  export default {
    name: "MpiDataUploadModal",
    components: {
      JDate,
      JDictSelectTag,
    },
    data () {
      return {
        /*设备信息列表*/
        equipmentList: [],
        /*监测点列表*/
        siteList: [],
        form: this.$form.createForm(this),
        title:"操作",
        width:800,
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
          equipmentId: {
            rules: [
              { required: true, message: '请选择设备ID!'},
            ]
          },
          monitorId: {
            rules: [
              { required: true, message: '请输入监测点ID!'},
            ]
          },
          topic: {
            rules: [
              { required: true, message: '请输入主题!'},
            ]
          },
        },
        url: {
          add: "/system/mpiDataUpload/add",
          edit: "/system/mpiDataUpload/edit",
          queryEquitmentList: "/system/mpiEquipment/allList",
          querySiteListByEquipment: "/system/mpiMonitorSite/querySiteListByEquipment"
        }
      }
    },
    created () {
    },
    mounted () {
      this.queryEquitmentList()
    },
    methods: {
      handlChange(value) {

        if ("" != value) {
          console.log("id : " + value)
          this.chanegeEquipment(value)
        } else {
          this.form.setFieldsValue({"monitorId" : ""});
          this.siteList.splice(0)
        }

      },
      chanegeEquipment(value) {
        console.log("siteId : " + value)
        getAction(this.url.querySiteListByEquipment,{ equipmentId : value }).then((res)=>{
          if(res.success){
            /*that.$message.success(res.message);
            that.$emit('ok');*/
            //this.equipmentList = res.result
            this.siteList = res.result
            console.log("result : " + JSON.stringify(res.result))
          }
        })
      },
      /* 全查询设备信息 */
      queryEquitmentList() {
        getAction(this.url.queryEquitmentList,null).then((res)=>{
          if(res.success){
            /*that.$message.success(res.message);
            that.$emit('ok');*/
            this.equipmentList = res.result
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
          this.form.setFieldsValue(pick(this.model,'equipmentId','monitorId','topic','paramName','paramValue','paramData','isWarn','uploadTime','returnCode','remarks','createBy','creataTime','updateBy','updateTime','delFlag'))
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
        this.form.setFieldsValue(pick(row,'equipmentId','monitorId','topic','paramName','paramValue','paramData','isWarn','uploadTime','returnCode','remarks','createBy','creataTime','updateBy','updateTime','delFlag'))
      },


    }
  }
</script>