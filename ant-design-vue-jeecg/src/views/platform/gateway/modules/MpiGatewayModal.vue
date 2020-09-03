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

        <a-form-item label="网关名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['gatewayName', validatorRules.gatewayName]" placeholder="请输入网关名称"></a-input>
        </a-form-item>
        <a-form-item label="公司编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <!--<j-dict-select-tag type="list" v-decorator="['companyId', validatorRules.companyId]" :trigger-change="true" dictCode="" placeholder="请选择公司编号"/>-->
          <!-- :disabled="companyIsEmpty" -->
          <!-- 使计算函数作用 -->
          <!--{{companyIds}}-->
          <!--<span :disabled="companyIsEmpty" ></span>-->
          <a-select v-decorator="['companyId', validatorRules.companyId]" placeholder="请选择公司编号">
            <a-select-option v-for="item in companyList" :key="item.id" :value="item.id">{{item.companyName}}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="网关IP" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['ipAddress', validatorRules.ipAddress]" placeholder="请输入网关IP"></a-input>
        </a-form-item>
        <a-form-item label="网关IMEI码" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['imeiCode']" placeholder="请输入网关IMEI码"></a-input>
        </a-form-item>
        <a-form-item label="类型" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <!--<a-input v-decorator="['gatewayType', validatorRules.gatewayType]" placeholder="请输入类型"></a-input>-->
          <j-category-select v-decorator="['gatewayType', validatorRules.gatewayType]" pcode="B05" placeholder="请选择网关类型" />
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

  import { httpAction, getAction  } from '@/api/manage'
  import pick from 'lodash.pick'
  import { validateDuplicateValue } from '@/utils/util'
  import JDate from '@/components/jeecg/JDate'
  import JDictSelectTag from "@/components/dict/JDictSelectTag"
  import JCategorySelect from '@/components/jeecg/JCategorySelect'


  export default {
    name: "MpiGatewayModal",
    components: {
      JDate,
      JDictSelectTag,
      JCategorySelect
    },
    data () {
      return {
        /*公司编号*/
        companyIds: '',
        /* 公司信息 */
        companyList: [],
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
          gatewayName: {
            rules: [
              { required: true, message: '请输入网关名称!'},
              { validator: (rule, value, callback) => validateDuplicateValue('mpi_gateway', 'gateway_name', value, this.model.id, callback)},
            ]
          },
          companyId: {
            rules: [
              { required: true, message: '请输入公司编号!'},
            ]
          },
          ipAddress: {
            rules: [
              { required: true, message: '请输入网关IP!'},
            ]
          },
          gatewayType: {
            rules: [
              { required: true, message: '请输入类型!'},
            ]
          },
        },
        url: {
          add: "/system/mpiGateway/add",
          edit: "/system/mpiGateway/edit",
          queryCompanyList: "/system/mpiCompany/allList" // 全查询公司信息
        }
      }
    },
    computed: {
    },
    created () {
    },
    mounted () {
      this.queryCompanyList()
      //this.form.setFieldsValue({"companyId" : this.companyId});
      //console.log("companyId : " + this.companyId)
      //this.form.setFieldsValue({"companyId" : this.companyId});
    },
    methods: {
      /* 全查询公司信息 */
      queryCompanyList() {
        getAction(this.url.queryCompanyList,null).then((res)=>{
          if(res.success){
            /*that.$message.success(res.message);
            that.$emit('ok');*/
            //console.log("result : " + JSON.stringify(res.result))
            this.companyList = res.result
            this.$nextTick(() => {
              this.form.setFieldsValue({"companyId" : this.companyIds});
            })
          }else{
            that.$message.warning(res.message);
          }
        })
      },
      add () {
        this.queryCompanyList()
        //this.form.setFieldsValue({"companyId" : this.companyIds});
        this.edit({});
      },
      edit (record) {
        //console.log("companyId : " + this.companyId)
        //this.form.setFieldsValue({"companyId" : this.companyId});
        //console.log("record : " + JSON.stringify(record) + " type : " + typeof record)
        this.form.resetFields();
        this.model = Object.assign({}, record);
        this.visible = true;
        this.$nextTick(() => {
          this.form.setFieldsValue(pick(this.model,'gatewayName','companyId','ipAddress','imeiCode','gatewayType','remarks','createBy','createTime','updateBy','updateTime','delFlag'))
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
        this.form.setFieldsValue(pick(row,'gatewayName','companyId','ipAddress','imeiCode','gatewayType','remarks','createBy','createTime','updateBy','updateTime','delFlag'))
      },


    }
  }
</script>