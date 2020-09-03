<template>
  <a-drawer
    :title="title"
    :width="width"
    placement="right"
    :closable="false"
    @close="close"
    :visible="visible">
  
    <a-spin :spinning="confirmLoading">
      <a-form :form="form">

        <a-form-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol">
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
        </a-form-item>
        <a-form-item label="设备名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['ename', validatorRules.ename]" placeholder="请输入设备名称"></a-input>
        </a-form-item>
        <a-form-item label="网关编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['wgid', validatorRules.wgid]" :trigger-change="true" dictCode="" placeholder="请选择网关编号"/>
        </a-form-item>
        <a-form-item label="设备类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['etypeid', validatorRules.etypeid]" :trigger-change="true" dictCode="" placeholder="请选择设备类型"/>
        </a-form-item>
        <a-form-item label="一级地区" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['levelarea1']" placeholder="请输入一级地区"></a-input>
        </a-form-item>
        <a-form-item label="二级地区" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['levelarea2']" placeholder="请输入二级地区"></a-input>
        </a-form-item>
        <a-form-item label="三级地区" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['levelarea3']" placeholder="请输入三级地区"></a-input>
        </a-form-item>
        <a-form-item label="安装时间" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-date placeholder="请选择安装时间" v-decorator="['installtime']" :trigger-change="true" :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="安装地址" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['installaddress']" placeholder="请输入安装地址"></a-input>
        </a-form-item>
        <a-form-item label="精度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['eprecision']" placeholder="请输入精度"></a-input>
        </a-form-item>
        <a-form-item label="维度" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['dimensionality']" placeholder="请输入维度"></a-input>
        </a-form-item>
        <a-form-item label="通讯方式" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['communicationmode', validatorRules.communicationmode]" placeholder="请输入通讯方式"></a-input>
        </a-form-item>
        <a-form-item label="产能系数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['capacitycoefficient']" placeholder="请输入产能系数"></a-input>
        </a-form-item>
        <a-form-item label="设备状态0-离线,1-在线" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['estate']" :trigger-change="true" dictCode="estate" placeholder="请选择设备状态0-离线,1-在线"/>
        </a-form-item>
        <a-form-item label="维护规则编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['mrid']" placeholder="请输入维护规则编号"></a-input>
        </a-form-item>
        <a-form-item label="设备图片" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-image-upload isMultiple v-decorator="['epicture']"></j-image-upload>
        </a-form-item>
        <a-form-item label="设备评分" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['egrade']" placeholder="请输入设备评分"></a-input>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea v-decorator="['disp']" rows="4" placeholder="请输入备注"/>
        </a-form-item>
        <a-form-item label="删除标识0-正常,1-已删除" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['delFlag']" :trigger-change="true" dictCode="del_flag" placeholder="请选择删除标识0-正常,1-已删除"/>
        </a-form-item>
        
      </a-form>
    </a-spin>
    <a-button type="primary" @click="handleOk">确定</a-button>
    <a-button type="primary" @click="handleCancel">取消</a-button>
  </a-drawer>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'  
  import JImageUpload from '@/components/jeecg/JImageUpload'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"
  
  export default {
    name: "LsEquipmentModal",
    components: { 
      JDate,
      JImageUpload,
      JDictSelectTag,
    },
    data () {
      return {
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
        }
      }
    },
    created () {
    },
    methods: {
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
      }
      
    }
  }
</script>

<style lang="less" scoped>
/** Button按钮间距 */
  .ant-btn {
    margin-left: 30px;
    margin-bottom: 30px;
    float: right;
  }
</style>