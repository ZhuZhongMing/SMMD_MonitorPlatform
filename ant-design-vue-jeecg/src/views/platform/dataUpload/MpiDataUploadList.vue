<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="设备ID">
              <!--<a-input placeholder="请输入设备ID" v-model="queryParam.equipmentId"></a-input>-->
              <!-- :allowClear="true" 清除-->
              <!--<a-select v-model="queryParam.equipmentId" placeholder="请选择设备" show-search :filter-option="filterOption">
                <a-select-option v-for="(item, key) in equipmentList" :key="key" :value="item.id">{{item.equipmentName}}</a-select-option>
              </a-select>-->
              <a-cascader
                v-model="equipmentId"
                :options="companyList"
                :load-data="loadSelectData"
                placeholder="请选择设备"
                change-on-select
                @change="onChangeSelect"
              />
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="监测点ID">
              <!--<a-input placeholder="请输入监测点ID" v-model="queryParam.monitorId"></a-input>-->
              <a-select v-model="queryParam.monitorId" placeholder="请选择监测点" show-search :filter-option="filterOption">
                <a-select-option v-for="(item, key) in siteList" :key="key" :value="item.id">{{item.monitorName}}</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <template v-if="toggleSearchStatus">
            <a-col :xl="6" :lg="7" :md="8" :sm="24">
              <a-form-item label="主题">
                <a-tooltip placement="bottom">
                  <template slot="title">
                    <span>支持左右模糊和全模糊 需要在查询输入框内前或后带*或是前后全部带*，例如：*张三* 则为全模糊</span>
                  </template>
                  <a-input placeholder="请输入主题" v-model="queryParam.topic"></a-input>
                </a-tooltip>
              </a-form-item>
            </a-col>
            <a-col :xl="10" :lg="11" :md="12" :sm="24">
              <a-form-item label="上传时间">
                <!--<j-date :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" placeholder="请选择上传时间" class="query-group-cust" v-model="queryParam.uploadTime"></j-date>-->
                <!--时间区间-->
                <j-date :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" placeholder="请选择开始时间" class="query-group-cust" v-model="queryParam.uploadTime_begin"></j-date>
                <span class="query-group-split-cust"></span>
                <j-date :show-time="true" date-format="YYYY-MM-DD HH:mm:ss" placeholder="请选择结束时间" class="query-group-cust" v-model="queryParam.uploadTime_end"></j-date>
              </a-form-item>
            </a-col>
          </template>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <!--<a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>-->
      <a-button type="primary" icon="download" @click="handleExportXls('设备上行数据')">导出</a-button>
      <!--<a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>-->
      <!--<a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>-->
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

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
    </div>

    <mpiDataUpload-modal ref="modalForm" @ok="modalFormOk"></mpiDataUpload-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import MpiDataUploadModal from './modules/MpiDataUploadModal'
  import JDate from '@/components/jeecg/JDate.vue'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import { getAction } from '@/api/manage'

  export default {
    name: "MpiDataUploadList",
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      JDate,
      MpiDataUploadModal
    },
    data () {
      return {
        /*设备编号*/
        equipmentId: [],
        /*公司信息列表*/
        companyList: [],
        /*设备信息列表*/
        gatewayList: [],
        /*设备信息列表*/
        equipmentList: [],
        /*监测点信息列表*/
        siteList: [],
        description: '设备上行数据管理页面',
        // 表头
        columns: [
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
            title:'设备名称', //title:'设备ID',
            align:"center",
            dataIndex: 'equipmentName' //dataIndex: 'equipmentId'
          },
          {
            title:'监测点名称', //title:'监测点ID',
            align:"center",
            dataIndex: 'monitorName' //dataIndex: 'monitorId'
          },
          {
            title:'主题',
            align:"center",
            dataIndex: 'topic'
          },
          {
            title:'参数名称',
            align:"center",
            dataIndex: 'paramName'
          },
          {
            title:'参数值',
            align:"center",
            dataIndex: 'paramValue'
          },
          {
            title:'参数数值',
            align:"center",
            dataIndex: 'paramData'
          },
          {
            title:'是否报警',
            align:"center",
            dataIndex: 'isWarn_dictText'
          },
          {
            title:'上传时间',
            align:"center",
            dataIndex: 'uploadTime'
          },
          {
            title:'返回值',
            align:"center",
            dataIndex: 'returnCode'
          },
          {
            title:'备注',
            align:"center",
            dataIndex: 'remarks'
          },
          /*{
            title:'创建人',
            align:"center",
            dataIndex: 'createBy'
          },
          {
            title:'创建时间',
            align:"center",
            dataIndex: 'creataTime'
          },
          {
            title:'修改人',
            align:"center",
            dataIndex: 'updateBy'
          },
          {
            title:'修改时间',
            align:"center",
            dataIndex: 'updateTime',
            customRender:function (text) {
              return !text?"":(text.length>10?text.substr(0,10):text)
            }
          },
          {
            title:'删除标识0-正常,1-删除',
            align:"center",
            dataIndex: 'delFlag_dictText'
          },*/
          /*{
            title: '操作',
            dataIndex: 'action',
            align:"center",
            // fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }*/
        ],
        url: {
          list: "/system/mpiDataUpload/list",
          delete: "/system/mpiDataUpload/delete",
          deleteBatch: "/system/mpiDataUpload/deleteBatch",
          exportXlsUrl: "/system/mpiDataUpload/exportXls",
          importExcelUrl: "system/mpiDataUpload/importExcel",
          queryEquitmentList: "/system/mpiEquipment/allList",
          querySiteList: "/system/mpiMonitorSite/allList",
          queryCompanyList: "/system/mpiCompany/allList",
          queryGatewayList: "/system/mpiGateway/allList",
        },
        dictOptions:{},
      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    mounted () {
      this.queryCompanyList()
      this.querySiteList()
    },
    methods: {
      getEquipmentId () {
        if (this.equipmentId[2] == undefined) {
          this.queryParam.equipmentId = ""
        }  else {
          this.queryParam.equipmentId = this.equipmentId[2]
        }
      },
      /* 全查询监测点信息 */
      querySiteList() {
        getAction(this.url.querySiteList,null).then((res)=>{
          if(res.success){
            /*that.$message.success(res.message);
            that.$emit('ok');*/
            this.siteList = res.result
          }else{
            that.$message.warning(res.message);
          }
        })
      },
      /* 全查询公司信息 */
      queryCompanyList() {
        getAction(this.url.queryCompanyList,null).then((res)=>{
          if(res.success){
            /*that.$message.success(res.message);
            that.$emit('ok');*/
            //this.equipmentList = res.result
            this.companyList.splice(0)
            for (let i = 0; i < res.result.length; i++) {
              this.companyList.push({
                value: res.result[i].id,
                label: res.result[i].companyName,
                isLeaf: false,
              })
            }
          }else{
            that.$message.warning(res.message);
          }
        })
      },
      /* 全查询网关信息 */
      queryGatewayList(id) {
        getAction(this.url.queryGatewayList,{companyId: id}).then((res)=>{
          if(res.success){
            /*that.$message.success(res.message);
            that.$emit('ok');*/
            //this.equipmentList = res.result
            this.gatewayList.splice(0)
            for (let i = 0; i < res.result.length; i++) {
              this.gatewayList.push({
                value: res.result[i].id,
                label: res.result[i].gatewayName,
                isLeaf: false,
              })
            }
          }else{
            that.$message.warning(res.message);
          }
        })
      },
      /* 全查询设备信息 */
      queryEquitmentList(id) {
        getAction(this.url.queryEquitmentList,{gatewayId: id}).then((res)=>{
          if(res.success){
            /*that.$message.success(res.message);
            that.$emit('ok');*/
            //this.equipmentList = res.result
            this.equipmentList.splice(0)
            for (let i = 0; i < res.result.length; i++) {
              this.equipmentList.push({
                value: res.result[i].id,
                label: res.result[i].equipmentName,
                isLeaf: true,
              })
            }
          }else{
            that.$message.warning(res.message);
          }
        })
      },
      loadSelectData(selectedOptions) {
        //console.log("selectedOptions :" + selectedOptions.length )
        const targetOption = selectedOptions[selectedOptions.length - 1];
        targetOption.loading = true;

        if (selectedOptions.length == 1) {
          /*加载第二级*/
          // load options lazily
          /*setTimeout(() => {
            targetOption.loading = false;
            targetOption.children = [
              {
                label: `${targetOption.label} Dynamic 1`,
                value: 'dynamic1',
                isLeaf: false, // 是不是最后一级
              },
              {
                label: `${targetOption.label} Dynamic 2`,
                value: 'dynamic2',
                isLeaf: false,
              },
            ];
            this.equipmentList = [...this.equipmentList];
          }, 1000);*/
          //this.queryGatewayList(targetOption.value)

          getAction(this.url.queryGatewayList,{companyId: targetOption.value}).then((res)=>{
            if(res.success){
              //this.equipmentList = res.result
              //this.gatewayList.splice(0)
              var children = []
              for (let i = 0; i < res.result.length; i++) {
                children.push({
                  value: res.result[i].id,
                  label: res.result[i].gatewayName,
                  isLeaf: false,
                })
              }
              targetOption.children = children
              this.companyList = [...this.companyList];
              targetOption.loading = false;
            }else{
              that.$message.warning(res.message);
              targetOption.children = []
              this.companyList = [...this.companyList];
              targetOption.loading = false;
            }
          })

          //targetOption.children = this.gatewayList
          //this.gatewayList.splice(0) // 清空
          //this.companyList = [...this.companyList];
          //targetOption.loading = false;
        } else if (selectedOptions.length == 2) {
          /*加载第三级*/
          // load options lazily
          //this.queryEquitmentList(targetOption.value)

          getAction(this.url.queryEquitmentList,{gatewayId: targetOption.value}).then((res)=>{
            if(res.success){
              //this.equipmentList = res.result
              //this.equipmentList.splice(0)
              var children = []
              for (let i = 0; i < res.result.length; i++) {
                children.push({
                  value: res.result[i].id,
                  label: res.result[i].equipmentName,
                  isLeaf: true,
                })
              }
              targetOption.children = children
              this.companyList = [...this.companyList];
              targetOption.loading = false;
            }else{
              that.$message.warning(res.message);
              targetOption.children = []
              this.companyList = [...this.companyList];
              targetOption.loading = false;
            }
          })

          //targetOption.children = this.equipmentList
          //this.equipmentList.splice(0)
          //this.companyList = [...this.companyList];
          //targetOption.loading = false;
        }
      },
      searchReset() {
        this.equipmentId = []
        this.queryParam = {}
        this.loadData(1);
      },
      onChangeSelect(value) {
       // console.log(value);
        this.getEquipmentId()
      },
      filterOption(input, option) {
        return (
          option.componentOptions.children[0].text.toLowerCase().indexOf(input.toLowerCase()) >= 0
        );
      },
      initDictConfig(){
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>