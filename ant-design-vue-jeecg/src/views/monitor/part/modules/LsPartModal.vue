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

        <a-form-item label="部件名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['pname', validatorRules.pname]" placeholder="请输入部件名称"></a-input>
        </a-form-item>
        <a-form-item label="型号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['model']" placeholder="请输入型号"></a-input>
        </a-form-item>
        <a-form-item label="规格" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['specification']" placeholder="请输入规格"></a-input>
        </a-form-item>
        <a-form-item label="类型编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <!--<j-dict-select-tag type="list" v-decorator="['ptypeid', validatorRules.ptypeid]" :trigger-change="true" dictCode="" placeholder="请选择类型编号"/>-->
          <!--<a-select v-decorator="['ptypeid', validatorRules.ptypeid]" placeholder="请选择类型编号"></a-select>-->
          <a-select type="list" v-decorator="['ptypeid', validatorRules.ptypeid]" placeholder="请选择类型编号">
            <a-select-option v-for="item in typeList" :key="item.id" :value="item.id">{{item.ptname}}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="单价" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input-number v-decorator="['price', validatorRules.price]" placeholder="请输入单价" style="width: 100%"/>
        </a-form-item>
        <a-form-item label="税率" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['tax']" placeholder="请输入税率"></a-input>
        </a-form-item>
        <a-form-item label="产地" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['origin']" placeholder="请输入产地"></a-input>
        </a-form-item>
        <a-form-item label="供货单位编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['supplierid']" placeholder="请输入供货单位编号"></a-input>
        </a-form-item>
        <a-form-item label="部件图片" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-image-upload isMultiple v-decorator="['ppicture']"></j-image-upload>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea v-decorator="['disp']" rows="4" placeholder="请输入备注"/>
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
  import JImageUpload from '@/components/jeecg/JImageUpload'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"


  export default {
    name: "LsPartModal",
    components: {
      JDate,
      JImageUpload,
      JDictSelectTag,
    },
    data () {
      return {
        /*部件类型列表*/
        typeList: [],
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
          pname: {
            rules: [
              { required: true, message: '请输入部件名称!'},
            ]
          },
          ptypeid: {
            rules: [
              { required: true, message: '请输入类型编号!'},
            ]
          },
          price: {
            rules: [
              { pattern: /^(([1-9][0-9]*)|([0]\.\d{0,2}|[1-9][0-9]*\.\d{0,2}))$/, message: '请输入正确的金额!'},
            ]
          },
        },
        url: {
          add: "/system/lsPart/add",
          edit: "/system/lsPart/edit",
          queryPartTypeList: "/system/lsPartType/allList"
        }
      }
    },
    created () {
    },
    mounted () {
      this.queryPartTypeList()
    },
    methods: {
      /* 全查询部件类型 */
      queryPartTypeList() {
        getAction(this.url.queryPartTypeList,null).then((res)=>{
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
      add () {
        this.edit({});
      },
      edit (record) {
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'pname','model','specification','ptypeid','price','tax','origin','supplierid','ppicture','disp','createBy','createTime','updateBy','updateTime','delFlag'))
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
        this.form.setFieldsValue(pick(row,'pname','model','specification','ptypeid','price','tax','origin','supplierid','ppicture','disp','createBy','createTime','updateBy','updateTime','delFlag'))
      },


    }
  }
</script>