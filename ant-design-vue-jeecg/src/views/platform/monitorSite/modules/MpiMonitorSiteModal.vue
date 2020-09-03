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

        <a-form-item label="设备组成ID" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <!--<j-dict-select-tag type="list" v-decorator="['componentId', validatorRules.componentId]" :trigger-change="true" dictCode="" placeholder="请选择设备组成ID"/>-->
          <a-select v-decorator="['componentId', validatorRules.componentId]" placeholder="请选择设备组成ID">
            <a-select-option v-for="(item, key) in equipmentComponentList" :key="key" :value="item.id">{{item.equipmentName}}  ---  {{item.componentName}}</a-select-option>
            <!--<a-select-option v-for="item in equipmentComponentList" :key="item.id" :value="item.id">{{item.id}}</a-select-option>-->
          </a-select>
        </a-form-item>
        <a-form-item label="监测点名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['monitorName', validatorRules.monitorName]" placeholder="请输入监测点名称"></a-input>
        </a-form-item>
        <a-form-item label="监测参数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['parameter', validatorRules.parameter]" placeholder="请输入监测参数"></a-input>
        </a-form-item>
        <a-form-item label="主题" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['topic', validatorRules.topic]" placeholder="请输入主题"></a-input>
        </a-form-item>
        <a-form-item label="当前值" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['currentValue', validatorRules.currentValue]" placeholder="请输入当前值" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="单位" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['unit']" placeholder="请输入单位"></a-input>
        </a-form-item>
        <a-form-item label="监测点状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['status']" placeholder="请输入监测点状态"></a-input>
        </a-form-item>
        <a-form-item label="上限报警" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['upperAlarm', validatorRules.upperAlarm]" placeholder="请输入上限报警" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="下限报警" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['lowerAlarm', validatorRules.lowerAlarm]" placeholder="请输入下限报警" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea v-decorator="['remarks']" rows="4" placeholder="请输入备注"/>
        </a-form-item>
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
  </j-modal>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"


  export default {
    name: "MpiMonitorSiteModal",
    components: {
      JDate,
      JDictSelectTag,
    },
    data () {
      return {
        /*设备组成编号*/
        componentIds: '',
        /*设备组成信息列表*/
        equipmentComponentList: [],
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
          componentId: {
            rules: [
              { required: true, message: '请输入设备组成ID!'},
            ]
          },
          monitorName: {
            rules: [
              { required: true, message: '请输入监测点名称!'},
            ]
          },
          parameter: {
            rules: [
              { required: true, message: '请输入监测参数!'},
            ]
          },
          currentValue: {
            rules: [
              { pattern: /^-?\d+\.?\d*$/, message: '请输入数字!'},
            ]
          },
          upperAlarm: {
            rules: [
              { pattern: /^-?\d+\.?\d*$/, message: '请输入数字!'},
            ]
          },
          lowerAlarm: {
            rules: [
              { pattern: /^-?\d+\.?\d*$/, message: '请输入数字!'},
            ]
          },
          topic: {
            rules: [
              { required: true, message: '请输入主题!'},
            ]
          }
        },
        url: {
          add: "/system/mpiMonitorSite/add",
          edit: "/system/mpiMonitorSite/edit",
          queryEquipmentComponentList: "/system/mpiEquipmentComponent/allList" // 全查询部件组成信息
        }
      }
    },
    created () {
    },
    mounted (){
      this.queryEquipmentComponentList()
    },
    methods: {
      /* 全查询部件组成信息 */
      queryEquipmentComponentList() {
        getAction(this.url.queryEquipmentComponentList,null).then((res)=>{
          if(res.success){
            /*that.$message.success(res.message);
            that.$emit('ok');*/
            //console.log("result : " + JSON.stringify(res.result))
            this.equipmentComponentList = res.result
            this.$nextTick(() => {
              this.form.setFieldsValue({"componentId" : this.componentIds});
            })
          }else{
            that.$message.warning(res.message);
          }
        })
      },
      add () {
        this.queryEquipmentComponentList()
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'componentId','monitorName','parameter','currentValue','unit','topic','status','upperAlarm','lowerAlarm','remarks','createBy','createTime','updateBy','updateTime','delFlag'))
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
        this.form.setFieldsValue(pick(row,'componentId','monitorName','parameter','currentValue','unit','topic','status','upperAlarm','lowerAlarm','remarks','createBy','createTime','updateBy','updateTime','delFlag'))
      },


    }
  }
</script>