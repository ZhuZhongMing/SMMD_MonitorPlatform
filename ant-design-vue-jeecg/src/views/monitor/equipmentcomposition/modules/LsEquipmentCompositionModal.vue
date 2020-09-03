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

        <a-form-item label="设备编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <!--<a-input v-decorator="['equipmentid', validatorRules.equipmentid]" placeholder="请输入设备编号"></a-input>-->
          <a-select type="list" v-decorator="['equipmentid', validatorRules.equipmentid]" placeholder="请输入设备编号">
            <a-select-option v-for="item in equipmentList" :key="item.id" :value="item.id">{{item.ename}}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="部件编号" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <!--<a-input v-decorator="['partid', validatorRules.partid]" placeholder="请输入部件编号"></a-input>-->
          <a-select type="list" v-decorator="['partid', validatorRules.partid]" placeholder="请输入部件编号">
            <a-select-option v-for="item in partList" :key="item.id" :value="item.id">{{item.pname}}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item label="部件评分" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-input v-decorator="['pgrade']" placeholder="请输入部件评分"></a-input>
        </a-form-item>
        <a-form-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol">
          <a-textarea v-decorator="['disp']" placeholder="请输入备注"></a-textarea>
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
  import ATextarea from 'ant-design-vue/es/input/TextArea'


  export default {
    name: "LsEquipmentCompositionModal",
    components: {
      ATextarea,
      JDate,
      JDictSelectTag,
    },
    data () {
      return {
        /* 部件信息列表 */
        partList: [],
        /* 设备信息列表 */
        equipmentList: [],
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
          equipmentid: {
            rules: [
              { required: true, message: '请输入设备编号!'},
            ]
          },
          partid: {
            rules: [
              { required: true, message: '请输入部件编号!'},
            ]
          },
        },
        url: {
          add: "/system/lsEquipmentComposition/add",
          edit: "/system/lsEquipmentComposition/edit",
          queryPartList: "/system/lsPart/allList",
          queryEquitmentList: "/system/lsEquipment/allList"
        }
      }
    },
    created () {
    },
    mounted () {
      this.queryPartList()
      this.queryEquitmentList()
    },
    methods: {
      /* 全查询部件信息 */
      queryPartList() {
        getAction(this.url.queryPartList,null).then((res)=>{
          if(res.success){
            /*that.$message.success(res.message);
            that.$emit('ok');*/
            //console.log("result : " + JSON.stringify(res.result))
            this.partList = res.result
          }else{
            that.$message.warning(res.message);
          }
        })
      },
      /* 全查设备信息 */
      queryEquitmentList() {
        getAction(this.url.queryEquitmentList,null).then((res)=>{
          if(res.success){
            /*that.$message.success(res.message);
            that.$emit('ok');*/
            //console.log("result : " + JSON.stringify(res.result))
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
          this.form.setFieldsValue(pick(this.model,'equipmentid','partid','pgrade','disp','createBy','createTime','updateBy','updateTime','delFlag'))
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
        this.form.setFieldsValue(pick(row,'equipmentid','partid','pgrade','disp','createBy','createTime','updateBy','updateTime','delFlag'))
      },


    }
  }
</script>