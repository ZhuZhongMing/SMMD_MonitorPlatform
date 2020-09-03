<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('监测点')">导出</a-button>
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

    <mpiMonitorSite-modal ref="modalForm" @ok="modalFormOk"></mpiMonitorSite-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import MpiMonitorSiteModal from './modules/MpiMonitorSiteModal'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'

  import { getAction } from '@/api/manage'

  export default {
    name: "MpiMonitorSiteList",
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      MpiMonitorSiteModal
    },
    data () {
      return {
        /*设备组成编号*/
        componentId: '',
        description: '监测点管理页面',
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
          list: "/system/mpiMonitorSite/list",
          delete: "/system/mpiMonitorSite/delete",
          deleteBatch: "/system/mpiMonitorSite/deleteBatch",
          exportXlsUrl: "/system/mpiMonitorSite/exportXls",
          importExcelUrl: "system/mpiMonitorSite/importExcel",
          getECIdsByEquipment: "/system/mpiEquipmentComponent/getECIdsByEquipment", // 根据设备编号查询设备组成ids
        },
        dictOptions:{},
      }
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      getSiteMain (componentId) {
        this.queryParam.componentId = componentId;
        this.componentId = componentId
        //console.log("queryParam : " + JSON.stringify(this.queryParam))
        this.loadData(1);
      },
      // 根据设备编号查询监测点ids
      getECIdsByEquipment (equipmentId) {
        if ("" != equipmentId) {
          getAction(this.url.getECIdsByEquipment,{"id" : equipmentId}).then((res)=>{
            if(res.success){
              // console.log("ids ======> " + JSON.stringify(res.result))
              var componentIds = " "
              if (res.result.length > 0) {
                componentIds = ""
                for (let i = 0; i < res.result.length; i++) {
                  if (i != res.result.length - 1) {
                    componentIds += res.result[i] + ","
                  } else {
                    componentIds += res.result[i]
                  }
                }
              }
              this.queryParam.componentIds = componentIds
              this.loadData(1)
            }else{
              //this.loadData(1)
              that.$message.warning(res.message);
            }
          })
        } else {
          this.queryParam.componentIds = ""
          this.loadData()
        }
      },
      handleAdd: function () {
        if ('' != this.componentId) {
          this.$refs.modalForm.componentIds = this.componentId;
        }
        this.$refs.modalForm.add();
        this.$refs.modalForm.title = "新增";
        this.$refs.modalForm.disableSubmit = false;
      },
      initDictConfig(){
      },
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>