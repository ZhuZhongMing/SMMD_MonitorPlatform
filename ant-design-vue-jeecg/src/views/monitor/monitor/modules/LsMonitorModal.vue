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
          <!--<j-dict-select-tag type="list" v-decorator="['ecid', validatorRules.ecid]" :trigger-change="true" dictCode="" placeholder="请选择设备组成ID"/>-->
          <a-select v-decorator="['ecid', validatorRules.ecid]" placeholder="请选择设备组成ID">
            <a-select-option v-for="item in equipmentCList" :key="item.id" :value="item.id">{{item.id}}</a-select-option>
          </a-select>
          <!--<a-input v-decorator="['ecid', validatorRules.ecid]" placeholder="请选择设备组成ID"></a-input>-->
        </a-form-item>
        <a-form-item label="监测点名称" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['mname']" placeholder="请输入监测点名称"></a-input>
        </a-form-item>
        <a-form-item label="监测参数" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['mparameter']" placeholder="请输入监测参数"></a-input>
        </a-form-item>
        <a-form-item label="单位" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['unit']" placeholder="请输入单位"></a-input>
        </a-form-item>
        <a-form-item label="主题" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['theme']" placeholder="请输入主题"></a-input>
        </a-form-item>
        <!--<a-form-item label="监测点状态" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <j-dict-select-tag type="list" v-decorator="['mstate']" :trigger-change="true" dictCode="mstate" placeholder="请选择监测点状态"/>
        </a-form-item>-->
        <a-form-item label="上限报警" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['upperlimit']" placeholder="请输入上限报警"></a-input>
        </a-form-item>
        <a-form-item label="下限报警" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['lowerlimit']" placeholder="请输入下限报警"></a-input>
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
  import JDictSelectTag from "@/components/dict/JDictSelectTag"
  import AFormItem from 'ant-design-vue/es/form/FormItem'


  export default {
    name: "LsMonitorModal",
    components: {
      AFormItem,
      JDate,
      JDictSelectTag,
    },
    data () {
      return {
        /* 设备组成列表 */
        equipmentCList: [],
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
          ecid: {
            rules: [
              { required: true, message: '请输入设备组成ID!'},
            ]
          },
        },
        url: {
          add: "/system/lsMonitor/add",
          edit: "/system/lsMonitor/edit",
          queryEquipmentCompositionCList: "/system/lsEquipmentComposition/allList"
        }
      }
    },
    created () {
    },
    mounted () {
      this.queryEquipmentCompositionCList()
    },
    methods: {
      /* 全查询设备组成信息 */
      queryEquipmentCompositionCList() {
        getAction(this.url.queryEquipmentCompositionCList,null).then((res)=>{
          if(res.success){
            /*that.$message.success(res.message);
            that.$emit('ok');*/
            //console.log("result : " + JSON.stringify(res.result))
            this.equipmentCList = res.result
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
          this.form.setFieldsValue(pick(this.model,'ecid','mname','mparameter','unit','theme','mstate','upperlimit','lowerlimit','disp','createBy','createTime','updateBy','updateTime','delFlag'))
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
        this.form.setFieldsValue(pick(row,'ecid','mname','mparameter','unit','theme','mstate','upperlimit','lowerlimit','disp','createBy','createTime','updateBy','updateTime','delFlag'))
      },


    }
  }
</script>