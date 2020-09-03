<template xmlns:background-color="http://www.w3.org/1999/xhtml">
  <a-row :gutter="10">
    <a-col :md="8" :sm="24">
      <a-card :bordered="false">

        <!-- 按钮操作区域 -->
        <!--<a-row style="margin-left: 14px">
          <a-button @click="handleAdd(1)" type="primary">添加部门</a-button>
          <a-button @click="handleAdd(2)" type="primary">添加下级</a-button>
          <a-button type="primary" icon="download" @click="handleExportXls('部门信息')">导出</a-button>
          <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
            <a-button type="primary" icon="import">导入</a-button>
          </a-upload>
          <a-button title="删除多条数据" @click="batchDel" type="default">批量删除</a-button>
          &lt;!&ndash;<a-button @click="refresh" type="default" icon="reload" :loading="loading">刷新</a-button>&ndash;&gt;
        </a-row>-->
        <div style="background: #fff;padding-left:16px;height: 100%; margin-top: 5px">
          <a-alert type="info" :showIcon="true">
            <div slot="message">
              当前选择：<span v-if="this.currSelected.title">{{ getCurrSelectedTitle() }}</span>
              <a v-if="this.currSelected.title" style="margin-left: 10px" @click="onClearSelected">取消选择</a>
            </div>
          </a-alert>
          <!--<a-input-search @search="onSearch" style="width:100%;margin-top: 10px" placeholder="请输入公司名称"/>-->
          <!-- 树-->
          <a-col :md="10" :sm="24">
            <template>
              <a-dropdown :trigger="[this.dropTrigger]" @visibleChange="dropStatus">
               <span style="user-select: none">
                 <!-- 右键 -->
                 <!-- @rightClick="rightHandle" -->
                 <!--:checkedKeys="checkedKeys"-->
                 <!--checkable-->
                 <!--multiple-->
                 <!--@check="onCheck"-->
            <a-tree
              :load-data = "onLoadData"
              @select="onSelect"
              :selectedKeys="selectedKeys"
              :treeData="departTree"
              :checkStrictly="checkStrictly"
              :expandedKeys="iExpandedKeys"
              :autoExpandParent="autoExpandParent"
              @expand="onExpand"/>
                </span>
                <!--新增右键点击事件,和增加添加和删除功能-->
                <a-menu slot="overlay">
                  <a-menu-item @click="handleAdd(3)" key="1">添加</a-menu-item>
                  <a-menu-item @click="handleDelete" key="2">删除</a-menu-item>
                  <a-menu-item @click="closeDrop" key="3">取消</a-menu-item>
                </a-menu>
              </a-dropdown>
            </template>
          </a-col>
        </div>
      </a-card>
      <!---- author:os_chengtgen -- date:20190827 --  for:切换父子勾选模式 =======------>
      <!--<div class="drawer-bootom-button">
        <a-dropdown :trigger="['click']" placement="topCenter">
          <a-menu slot="overlay">
            <a-menu-item key="1" @click="switchCheckStrictly(1)">父子关联</a-menu-item>
            <a-menu-item key="2" @click="switchCheckStrictly(2)">取消关联</a-menu-item>
            <a-menu-item key="3" @click="checkALL">全部勾选</a-menu-item>
            <a-menu-item key="4" @click="cancelCheckALL">取消全选</a-menu-item>
            <a-menu-item key="5" @click="expandAll">展开所有</a-menu-item>
            <a-menu-item key="6" @click="closeAll">合并所有</a-menu-item>
          </a-menu>
          <a-button>
            树操作 <a-icon type="up" />
          </a-button>
        </a-dropdown>
      </div>-->
      <!---- author:os_chengtgen -- date:20190827 --  for:切换父子勾选模式 =======------>
    </a-col>
    <a-col :md="16" :sm="24">
      <a-tabs defaultActiveKey="1">
        <a-tab-pane :tab="tabTitle" key="1" >
          <a-card :bordered="false" v-if="selectedKeys.length>0">
            <a-table
              ref="table"
              size="middle"
              bordered
              rowKey="id"
              :columns="columns"
              :dataSource="dataSource"
              :pagination="ipagination"
              :loading="loading"

              class="j-table-force-nowrap"
              @change="handleTableChange">
              <!--:rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"-->
              <template slot="htmlSlot" slot-scope="text">
                <div v-html="text"></div>
              </template>
              <template slot="imgSlot" slot-scope="text">
                <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
                <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
              </template>
              <template slot="fileSlot" slot-scope="text">
                <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
                <a-button
                  v-else
                  :ghost="true"
                  type="primary"
                  icon="download"
                  size="small"
                  @click="uploadFile(text)">
                  下载
                </a-button>
              </template>

              <span slot="action" slot-scope="text, record">
                <a @click="handleEdit(record)">编辑</a>

                <a-divider type="vertical" />
                <a-dropdown>
                  <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
                  <a-menu slot="overlay">
                    <a-menu-item>
                      <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                        <a>删除</a>
                      </a-popconfirm>
                    </a-menu-item>
                  </a-menu>
                </a-dropdown>
              </span>

            </a-table>
          </a-card>
          <a-card v-else >
            <a-empty>
              <span slot="description"> {{message}} </span>
            </a-empty>
          </a-card>
        </a-tab-pane>
        <!--<a-tab-pane tab="部门权限" key="2" forceRender>
          <depart-auth-modal ref="departAuth"/>
        </a-tab-pane>-->
      </a-tabs>

    </a-col>
    <!--<depart-modal ref="departModal" @ok="loadTree"></depart-modal>-->
  </a-row>
</template>
<script>
  //import DepartModal from './modules/DepartModal'
  import pick from 'lodash.pick'
  import {queryDepartTreeList, searchByKeywords, deleteByDepartId} from '@/api/api'
  import {httpAction, deleteAction, getAction} from '@/api/manage'
  import {JeecgListMixin} from '@/mixins/JeecgListMixin'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import Area from '@/components/_util/Area'
  import { loadCategoryData } from '@/api/api'
  //import DepartAuthModal from './modules/DepartAuthModal'
  const dictOptions = {}
  // 表头
  const gatewayColumns = [
    {
      title: '#',
      dataIndex: '',
      key:'rowIndex',
      width:60,
      align:"center",
      customRender:function (t,r,index) {
        return parseInt(index)+1;
      }
    },
    {
      title:'网关名称',
      align:"center",
      dataIndex: 'gatewayName'
    },
    {
      title:'所属公司', //title:'公司编号',
      align:"center",
      dataIndex: 'companyName' //dataIndex: 'companyId_dictText'
    },
    {
      title:'网关IP',
      align:"center",
      dataIndex: 'ipAddress'
    },
    {
      title:'网关IMEI码',
      align:"center",
      dataIndex: 'imeiCode'
    },
    {
      title:'类型',
      align:"center",
      dataIndex: 'gatewayType'
    },
    {
      title:'备注',
      align:"center",
      dataIndex: 'remarks'
    }
  ]
  const equipmentColums = [
    {
      title: '#',
      dataIndex: '',
      key:'rowIndex',
      width:60,
      align:"center",
      customRender:function (t,r,index) {
        return parseInt(index)+1;
      }
    },
    {
      title:'设备名称',
      align:"center",
      dataIndex: 'equipmentName'
    },
    {
      title:'网关名称', //title:'网关编号',
      align:"center",
      dataIndex: 'gatewayName' //dataIndex: 'gatewayId_dictText'
    },
    {
      title:'设备类型',
      align:"center",
      dataIndex: 'equipmentType',
      customRender: (text) => (text ? filterMultiDictText(dictOptions['equipmentType'], text) : '')
    },
    {
      title:'安装时间',
      align:"center",
      dataIndex: 'deployTime'
    },
    {
      title:'安装地址',
      align:"center",
      dataIndex: 'deployAddress',
    },
    {
      title:'设备状态',
      align:"center",
      dataIndex: 'status_dictText'
    },
    {
      title:'设备图片',
      align:"center",
      dataIndex: 'equipmentImage',
      scopedSlots: {customRender: 'imgSlot'}
    },
  ]
  const componentColums = [
    {
      title: '#',
      dataIndex: '',
      key:'rowIndex',
      width:60,
      align:"center",
      customRender:function (t,r,index) {
        return parseInt(index)+1;
      }
    },
    {
      title:'设备名称', //title:'设备编号',
      align:"center",
      dataIndex: 'equipmentName' //dataIndex: 'equipmentId_dictText'
    },
    {
      title:'部件名称', //title:'部件编号',
      align:"center",
      dataIndex: 'componentName' //dataIndex: 'componentId_dictText'
    },
    {
      title:'部件评分',
      align:"center",
      dataIndex: 'componentScore'
    },
    {
      title:'部件状态',
      align:"center",
      dataIndex: 'status_dictText'
    },
  ]
  const siteColums = [
    {
      title: '#',
      dataIndex: '',
      key:'rowIndex',
      width:60,
      align:"center",
      customRender:function (t,r,index) {
        return parseInt(index)+1;
      }
    },
    {
      title:'设备组成ID',
      align:"center",
      dataIndex: 'componentId' // dataIndex: 'componentId' // dataIndex: 'componentId_dictText'
    },
    {
      title:'监测点名称',
      align:"center",
      dataIndex: 'monitorName'
    },
    {
      title:'监测参数',
      align:"center",
      dataIndex: 'parameter'
    },
    {
      title:'当前值',
      align:"center",
      dataIndex: 'currentValue'
    },
    {
      title:'单位',
      align:"center",
      dataIndex: 'unit'
    },
    {
      title:'主题',
      align:"center",
      dataIndex: 'topic'
    },
    {
      title:'监测点状态',
      align:"center",
      dataIndex: 'status'
    },
    {
      title:'上限报警',
      align:"center",
      dataIndex: 'upperAlarm'
    },
    {
      title:'下限报警',
      align:"center",
      dataIndex: 'lowerAlarm'
    },
  ]
  export default {
    name: 'DepartList',
    mixins: [JeecgListMixin],
    components: {
      /*DepartAuthModal,
      DepartModal*/
    },
    data() {
      return {
        message: "请选择一个节点!",
        /*选项卡标题*/
        tabTitle: "网关列表",
        iExpandedKeys: [],
        loading: false,
        autoExpandParent: true,
        currFlowId: '',
        currFlowName: '',
        disable: true,
        treeData: [],
        visible: false,
        departTree: [],
        rightClickSelectedKey: '',
        rightClickSelectedOrgCode: '',
        hiding: true,
        model: {},
        dropTrigger: '',
        depart: {},
       /* columns: columns,*/
        disableSubmit: false,
        checkedKeys: [],
        selectedKeys: [],
        autoIncr: 1,
        currSelected: {},

        allTreeKeys:[],
         checkStrictly: true,

        form: this.$form.createForm(this),
        labelCol: {
          xs: {span: 24},
          sm: {span: 5}
        },
        wrapperCol: {
          xs: {span: 24},
          sm: {span: 16}
        },
        graphDatasource: {
          nodes: [],
          edges: []
        },
        validatorRules: {
          departName: {rules: [{required: true, message: '请输入机构/部门名称!'}]},
          orgCode: {rules: [{required: true, message: '请输入机构编码!'}]},
          orgCategory: {rules: [{required: true, message: '请输入机构类型!'}]},
          mobile: {rules: [{validator: this.validateMobile}]}
        },
        url: {
          delete: '/sys/sysDepart/delete',
          edit: '/sys/sysDepart/edit',
          deleteBatch: '/sys/sysDepart/deleteBatch',
          exportXlsUrl: "sys/sysDepart/exportXls",
          importExcelUrl: "sys/sysDepart/importExcel",
          loadTreeUrl: "/system/mpiCompany/queryTreeList",
          gatewayTreeByCompanyUrl: "/system/mpiGateway/queryTreeList",
          equipmentTreeByGatewayUrl: "/system/mpiEquipment/queryTreeList",
          componentTreeByequipmentUrl: "/system/mpiEquipmentComponent/queryTreeList",
          siteTreeByComponentUrl: "/system/mpiMonitorSite/queryTreeList",
        },
        orgCategoryDisabled:false,
        columns: gatewayColumns ,
        /*dictOptions:{},*/
        pcaData:''
      }
    },
    created() {
      this.pcaData = new Area()
      this.currFlowId = this.$route.params.id
      this.currFlowName = this.$route.params.name
      // this.loadTree()
    },
    computed: {
      importExcelUrl: function () {
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      }
    },
    methods: {
      /*异步加载方法*/
      onLoadData(treeNode) {
        return new Promise((resolve, code) => {
          var node = treeNode.dataRef  // 当前点击节点全部信息 treeNode.$vnode.data.props(第二种方式)
          var url = ""
          var param = {}

          if (node.level == 4) {
            resolve();
            treeNode.dataRef.children = [];
            this.departTree = [...this.departTree];
          } else {
            if (node.level == 0) {
              url = this.url.gatewayTreeByCompanyUrl
              param = {companyId: node.id}
            } else if (node.level == 1) {
              url = this.url.equipmentTreeByGatewayUrl
              param = {gatewayId: node.id}
            } else if (node.level == 2) {
              url = this.url.componentTreeByequipmentUrl
              param = {equipmentId: node.id}
            } else if (node.level == 3) {
              url = this.url.siteTreeByComponentUrl
              param = {componentId: node.id}
            }
            getAction(url,param).then((res)=>{
              if(res.success){
                resolve();
                treeNode.dataRef.children = res.result;
                this.departTree = [...this.departTree];
                console.log("result : " + JSON.stringify(res.result))
              }
            })
          }
          /*console.log(treeNode.dataRef)
          console.log(node.id + "      " + node.level)
          console.log(resolve)
          console.log(code)*/
        });
      },
      loadData() {
        this.refresh();
      },
      loadTree() {
        var that = this
        that.treeData = []
        that.departTree = []
        /*queryDepartTreeList().then((res) => {
          if (res.success) {
            //部门全选后，再添加部门，选中数量增多
            this.allTreeKeys = [];
            for (let i = 0; i < res.result.length; i++) {
              let temp = res.result[i]
              that.treeData.push(temp)
              that.departTree.push(temp)
              that.setThisExpandedKeys(temp)
              that.getAllKeys(temp);
              // console.log(temp.id)
            }
            this.loading = false
            console.log("treeData : " +JSON.stringify(that.treeData))
            console.log("departTree : " +JSON.stringify(that.departTree))
          }
        })*/

        getAction(this.url.loadTreeUrl,null).then((res)=>{
          if(res.success){
            //部门全选后，再添加部门，选中数量增多
            this.allTreeKeys = [];
            for (let i = 0; i < res.result.length; i++) {
              let temp = res.result[i]
              that.treeData.push(temp)
              that.departTree.push(temp)
              that.setThisExpandedKeys(temp)
              that.getAllKeys(temp);
              // console.log(temp.id)
            }
            this.loading = false
            /*console.log("treeData : " +JSON.stringify(that.treeData))
            console.log("departTree : " +JSON.stringify(that.departTree))*/
          }
        })


      },
      setThisExpandedKeys(node) {
        if (node.children && node.children.length > 0) {
          this.iExpandedKeys.push(node.key)
          for (let a = 0; a < node.children.length; a++) {
            this.setThisExpandedKeys(node.children[a])
          }
        }
      },
      refresh() {
        this.loading = true
        this.loadTree()
      },
      // 右键操作方法
      rightHandle(node) {
        this.dropTrigger = 'contextmenu'
        //console.log(node.node.eventKey)
        this.rightClickSelectedKey = node.node.eventKey
        this.rightClickSelectedOrgCode = node.node.dataRef.orgCode
      },
      onExpand(expandedKeys) {
        //console.log('onExpand', expandedKeys)
        // if not set autoExpandParent to false, if children expanded, parent can not collapse.
        // or, you can remove all expanded children keys.
        this.iExpandedKeys = expandedKeys
        this.autoExpandParent = false
      },
      backFlowList() {
        this.$router.back(-1)
      },
      // 右键点击下拉框改变事件
      dropStatus(visible) {
        if (visible == false) {
          this.dropTrigger = ''
        }
      },
      // 右键店家下拉关闭下拉框
      closeDrop() {
        this.dropTrigger = ''
      },
      addRootNode() {
        this.$refs.nodeModal.add(this.currFlowId, '')
      },
      batchDel: function () {
        console.log(this.checkedKeys)
        if (this.checkedKeys.length <= 0) {
          this.$message.warning('请选择一条记录！')
        } else {
          var ids = ''
          for (var a = 0; a < this.checkedKeys.length; a++) {
            ids += this.checkedKeys[a] + ','
          }
          var that = this
          this.$confirm({
            title: '确认删除',
            content: '确定要删除所选中的 ' + this.checkedKeys.length + ' 条数据，以及子节点数据吗?',
            onOk: function () {
              deleteAction(that.url.deleteBatch, {ids: ids}).then((res) => {
                if (res.success) {
                  that.$message.success(res.message)
                  that.loadTree()
                  that.onClearSelected()
                } else {
                  that.$message.warning(res.message)
                }
              })
            }
          })
        }
      },
      onSearch(value) {
        let that = this
        if (value) {
          //console.log("value : " + value)
          /*searchByKeywords({keyWord: value}).then((res) => {
            if (res.success) {
              that.departTree = []
              for (let i = 0; i < res.result.length; i++) {
                let temp = res.result[i]
                that.departTree.push(temp)
              }
            } else {
              that.$message.warning(res.message)
            }
          })*/
          getAction("/system/mpiCompany/searchBy",{keyWord: value}).then((res) => {
            if (res.success) {
              that.departTree = []
              for (let i = 0; i < res.result.length; i++) {
                let temp = res.result[i]
                that.departTree.push(temp)
              }
            } else {
              that.$message.warning(res.message)
            }
          })
        } else {
          that.loadTree()
        }

      },
      nodeModalOk() {
        this.loadTree()
      },
      nodeModalClose() {
      },
      hide() {
        console.log(111)
        this.visible = false
      },
      onCheck(checkedKeys, info) {
        console.log('onCheck', checkedKeys, info)
        this.hiding = false
        //this.checkedKeys = checkedKeys.checked
        // <!---- author:os_chengtgen -- date:20190827 --  for:切换父子勾选模式 =======------>
        if(this.checkStrictly){
          this.checkedKeys = checkedKeys.checked;
        }else{
          this.checkedKeys = checkedKeys
        }
        // <!---- author:os_chengtgen -- date:20190827 --  for:切换父子勾选模式 =======------>
      },
      onSelect(selectedKeys, e) {
        //console.log('selected', selectedKeys, e)
        this.hiding = false
        let record = e.node.dataRef
        console.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + record.level)
        //console.log('onSelect-record', record)
        this.currSelected = Object.assign({}, record)
        this.model = this.currSelected
        this.selectedKeys = [record.key]
        this.model.parentId = record.parentId
        this.setValuesToForm(record)
        var param = {}
        var url = ""

        if (record.level < 4) {
          if (record.level == 0) {
            param = { companyId : record.id }
            var url = "/system/mpiGateway/list"
            this.tabTitle = "网关列表"
            this.columns = gatewayColumns
          } else if (record.level == 1) {
            param = { gatewayId : record.id }
            var url = "/system/mpiEquipment/list"
            this.tabTitle = "设备列表"
            this.columns = equipmentColums
          } else if (record.level == 2) {
            param = { equipmentId : record.id }
            var url = "/system/mpiEquipmentComponent/list"
            this.tabTitle = "设备组成"
            this.columns = componentColums
          } else if (record.level == 3) {
            param = { componentId : record.id }
            var url = "/system/mpiMonitorSite/list"
            this.tabTitle = "监测点"
            this.columns = siteColums
          }
          // var param = Object.assign({}, parm, this.isorter ,this.filters);
          getAction(url,param).then((res)=>{
            if(res.success){
              this.dataSource = res.result.records;
              this.ipagination.total = res.result.total;
            }
          })
        } else {
          this.message = "此节点为最后一级!"
          this.selectedKeys.splice(0)
        }


      },
      // 触发onSelect事件时,为部门树右侧的form表单赋值
      setValuesToForm(record) {
        if(record.orgCategory == '1'){
          this.orgCategoryDisabled = true;
        }else{
          this.orgCategoryDisabled = false;
        }
        this.$nextTick(() => {
          this.form.getFieldDecorator('fax', {initialValue: ''})
          this.form.setFieldsValue(pick(record, 'departName','orgCategory', 'orgCode', 'departOrder', 'mobile', 'fax', 'address', 'memo'))
        })
      },
      getCurrSelectedTitle() {
        return !this.currSelected.title ? '' : this.currSelected.title
      },
      onClearSelected() {
        this.hiding = true
        this.checkedKeys = []
        this.currSelected = {}
        this.form.resetFields()
        this.selectedKeys = []
        this.message = "请选择一个节点"
        /*this.$refs.departAuth.departId = ''*/
      },
      handleNodeTypeChange(val) {
        this.currSelected.nodeType = val
      },
      notifyTriggerTypeChange(value) {
        this.currSelected.notifyTriggerType = value
      },
      receiptTriggerTypeChange(value) {
        this.currSelected.receiptTriggerType = value
      },
      submitCurrForm() {
        this.form.validateFields((err, values) => {
          if (!err) {
            if (!this.currSelected.id) {
              this.$message.warning('请点击选择要修改部门!')
              return
            }

            let formData = Object.assign(this.currSelected, values)
            console.log('Received values of form: ', formData)
            httpAction(this.url.edit, formData, 'put').then((res) => {
              if (res.success) {
                this.$message.success('保存成功!')
                this.loadTree()
              } else {
                this.$message.error(res.message)
              }
            })
          }
        })
      },
      emptyCurrForm() {
        this.form.resetFields()
      },
      nodeSettingFormSubmit() {
        this.form.validateFields((err, values) => {
          if (!err) {
            console.log('Received values of form: ', values)
          }
        })
      },
      openSelect() {
        this.$refs.sysDirectiveModal.show()
      },
      handleAdd(num) {
        if (num == 1) {
          this.$refs.departModal.add()
          this.$refs.departModal.title = '新增'
        } else if (num == 2) {
          let key = this.currSelected.key
          if (!key) {
            this.$message.warning('请先点击选中上级部门！')
            return false
          }
          this.$refs.departModal.add(this.selectedKeys)
          this.$refs.departModal.title = '新增'
        } else {
          this.$refs.departModal.add(this.rightClickSelectedKey)
          this.$refs.departModal.title = '新增'
        }
      },
      handleDelete() {
        var that = this
        this.$confirm({
          title: '确认删除',
          content: '确定要删除此部门以及子节点数据吗?',
          onOk: function () {
            deleteByDepartId({id: that.rightClickSelectedKey}).then((resp) => {
              if (resp.success) {
                //删除成功后，去除已选中中的数据
                that.checkedKeys.splice(that.checkedKeys.findIndex(key => key === that.rightClickSelectedKey), 1);
                that.$message.success('删除成功!')
                that.loadTree()
                //删除后同步清空右侧基本信息内容
                let orgCode=that.form.getFieldValue("orgCode");
                if(orgCode && orgCode === that.rightClickSelectedOrgCode){
                  that.onClearSelected()
                }
              } else {
                that.$message.warning('删除失败!')
              }
            })
          }
        })
      },
      selectDirectiveOk(record) {
        console.log('选中指令数据', record)
        this.nodeSettingForm.setFieldsValue({directiveCode: record.directiveCode})
        this.currSelected.sysCode = record.sysCode
      },
      getFlowGraphData(node) {
        this.graphDatasource.nodes.push({
          id: node.id,
          text: node.flowNodeName
        })
        if (node.children.length > 0) {
          for (let a = 0; a < node.children.length; a++) {
            let temp = node.children[a]
            this.graphDatasource.edges.push({
              source: node.id,
              target: temp.id
            })
            this.getFlowGraphData(temp)
          }
        }
      },
     // <!---- author:os_chengtgen -- date:20190827 --  for:切换父子勾选模式 =======------>
      expandAll () {
        this.iExpandedKeys = this.allTreeKeys
      },
      closeAll () {
        this.iExpandedKeys = []
      },
      checkALL () {
        this.checkStriccheckStrictlytly = false
        this.checkedKeys = this.allTreeKeys
      },
      cancelCheckALL () {
        //this.checkedKeys = this.defaultCheckedKeys
        this.checkedKeys = []
      },
      switchCheckStrictly (v) {
        if(v==1){
          this.checkStrictly = false
        }else if(v==2){
          this.checkStrictly = true
        }
      },
      getAllKeys(node) {
        // console.log('node',node);
        this.allTreeKeys.push(node.key)
        if (node.children && node.children.length > 0) {
          for (let a = 0; a < node.children.length; a++) {
            this.getAllKeys(node.children[a])
          }
        }
      },
      // <!---- author:os_chengtgen -- date:20190827 --  for:切换父子勾选模式 =======------>
      getPcaText(code){
        return this.pcaData.getText(code);
      },
      initDictConfig(){
        loadCategoryData({code:'B03'}).then((res) => {
          if (res.success) {
            this.$set(dictOptions, 'equipmentType', res.result)
          }
        })
      },
    },
  }
</script>
<style scoped>
  .ant-card-body .table-operator {
    margin: 15px;
  }

  .anty-form-btn {
    width: 100%;
    text-align: center;
  }

  .anty-form-btn button {
    margin: 0 5px;
  }

  .anty-node-layout .ant-layout-header {
    padding-right: 0
  }

  .header {
    padding: 0 8px;
  }

  .header button {
    margin: 0 3px
  }

  .ant-modal-cust-warp {
    height: 100%
  }

  .ant-modal-cust-warp .ant-modal-body {
    height: calc(100% - 110px) !important;
    overflow-y: auto
  }

  .ant-modal-cust-warp .ant-modal-content {
    height: 90% !important;
    overflow-y: hidden
  }

  #app .desktop {
    height: auto !important;
  }

  /** Button按钮间距 */
  .ant-btn {
    margin-left: 3px
  }

  .drawer-bootom-button {
    /*position: absolute;*/
    bottom: 0;
    width: 100%;
    border-top: 1px solid #e8e8e8;
    padding: 10px 16px;
    text-align: left;
    left: 0;
    background: #fff;
    border-radius: 0 0 2px 2px;
  }
</style>