<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
          <!--<a-col :xl="6" :lg="7" :md="8" :sm="24">
            <a-form-item label="公司名称">
              &lt;!&ndash;<a-input placeholder="请输入公司名称" v-model="queryParam.companyName"></a-input>&ndash;&gt;
              <a-select v-model="queryParam.companyId" placeholder="请选择公司编号">
                <a-select-option v-for="item in companyList" :key="item.id" :value="item.id">{{item.companyName}}</a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :xl="6" :lg="7" :md="8" :sm="24">
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>-->
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('设备')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
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
        <template slot="pcaSlot" slot-scope="text">
          <div>{{ getPcaText(text) }}</div>
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
        <!-- 字符串超长截取省略号显示-->
        <span slot="component" slot-scope="text">
          <j-ellipsis :value="text"/>
        </span>
      </a-table>
    </div>

    <mpiEquipment-modal ref="modalForm" @ok="modalFormOk"></mpiEquipment-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import MpiEquipmentModal from './modules/MpiEquipmentModal'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import Area from '@/components/_util/Area'
  import JEllipsis from '@/components/jeecg/JEllipsis'
  import { loadCategoryData } from '@/api/api'
  import { getAction } from '@/api/manage'

  export default {
    name: "MpiEquipmentList",
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      MpiEquipmentModal,
      JEllipsis
    },
    data () {
      return {
        /* 公司信息 */
        companyList: [],
        /*网关编号*/
        gatewayId: '',
        description: '设备管理页面',
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
            customRender: (text) => (text ? filterMultiDictText(this.dictOptions['equipmentType'], text) : '')
          },
          /*{
            title:'一级地区',
            align:"center",
            dataIndex: 'level1Region',
            scopedSlots: {customRender: 'pcaSlot'}
          },
          {
            title:'二级地区',
            align:"center",
            dataIndex: 'level2Region',
            scopedSlots: {customRender: 'pcaSlot'}
          },
          {
            title:'三级地区',
            align:"center",
            dataIndex: 'level3Region',
            scopedSlots: {customRender: 'pcaSlot'}
          },*/
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
          /*{
            title:'经度',
            align:"center",
            dataIndex: 'longitude'
          },
          {
            title:'纬度',
            align:"center",
            dataIndex: 'latitude'
          },
          {
            title:'通讯方式',
            align:"center",
            dataIndex: 'comType'
          },
          {
            title:'产能系数',
            align:"center",
            dataIndex: 'productCoefficient'
          },*/
          {
            title:'设备状态',
            align:"center",
            dataIndex: 'status_dictText'
          },
          /*{
            title:'维护规则编号',
            align:"center",
            dataIndex: 'maintainRule'
          },*/
          {
            title:'设备图片',
            align:"center",
            dataIndex: 'equipmentImage',
            scopedSlots: {customRender: 'imgSlot'}
          },
          {
            title:'设备评分',
            align:"center",
            dataIndex: 'equipmentScore'
          },
          {
            title:'备注',
            align:"center",
            dataIndex: 'remarks',
            scopedSlots: { customRender: 'component' }
          },
          /*{
            title:'创建人',
            align:"center",
            dataIndex: 'createBy'
          },
          {
            title:'创建时间',
            align:"center",
            dataIndex: 'createTime'
          },
          {
            title:'修改人',
            align:"center",
            dataIndex: 'updateBy'
          },
          {
            title:'修改时间',
            align:"center",
            dataIndex: 'updateTime'
          },*/
          /*{
            title:'删除标识0-正常,1-已删除',
            align:"center",
            dataIndex: 'delFlag_dictText'
          },*/
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            // fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/system/mpiEquipment/list",
          delete: "/system/mpiEquipment/delete",
          deleteBatch: "/system/mpiEquipment/deleteBatch",
          exportXlsUrl: "/system/mpiEquipment/exportXls",
          importExcelUrl: "system/mpiEquipment/importExcel",
          queryCompanyList: "/system/mpiCompany/allList", // 全查询公司信息
          getGatewayIdsByCompany: "/system/mpiGateway/getGatewayIdsByCompany", // 根据公司编号查询网关ids
        },
        dictOptions:{},
        pcaData:''
      }
    },
    created() {
      this.pcaData = new Area()
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    mounted () {
      this.queryCompanyList()
    },
    methods: {
      changeQuery(companyId) {
        this.queryParam.companyId = companyId
        this.loadData(1)
      },
      /*根据公司编号查询网关ids*/
      /*getGatewayIdsByCompany(companyId) {
        // console.log("id -> " + companyId)
        if ("" != companyId) {
          getAction(this.url.getGatewayIdsByCompany,{"id" : companyId}).then((res)=>{
            if(res.success){
              var gatewayIds = " "
              if (res.result.length > 0) {
                gatewayIds = ""
                for (let i = 0; i < res.result.length; i++) {
                  if (i != res.result.length - 1) {
                    gatewayIds += res.result[i] + ","
                  } else {
                    gatewayIds += res.result[i]
                  }
                }
              }
              this.queryParam.gatewayIds = gatewayIds
              this.loadData(1)
            }else{
              //this.loadData(1)
              that.$message.warning(res.message);
            }
          })
        } else {
          this.queryParam.gatewayIds = ""
          this.loadData()
        }
      },*/
      /* 全查询公司信息 */
      queryCompanyList() {
        getAction(this.url.queryCompanyList,null).then((res)=>{
          if(res.success){
            /*that.$message.success(res.message);
            that.$emit('ok');*/
            //console.log("result : " + JSON.stringify(res.result))
            this.companyList = res.result
          }else{
            that.$message.warning(res.message);
          }
        })
      },
      getPcaText(code){
        return this.pcaData.getText(code);
      },
      initDictConfig(){
        loadCategoryData({code:'B03'}).then((res) => {
          if (res.success) {
            this.$set(this.dictOptions, 'equipmentType', res.result)
          }
        })
      },
      getGatewayMain(gatewayId) {
        //console.log("gatewayId : " + gatewayId)
        this.queryParam.gatewayId = gatewayId;
        this.gatewayId = gatewayId
        //console.log("queryParam : " + JSON.stringify(this.queryParam))
        this.loadData(1);
      },
      handleAdd: function () {
        if ('' != this.gatewayId) {
          this.$refs.modalForm.gatewayIds = this.gatewayId;
        }
        this.$refs.modalForm.add();
        this.$refs.modalForm.title = "新增";

        this.$refs.modalForm.disableSubmit = false;
      },
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>