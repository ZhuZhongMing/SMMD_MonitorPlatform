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
      <a-button type="primary" icon="download" @click="handleExportXls('设备信息')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
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

      </a-table>
    </div>

    <lsEquipment-modal ref="modalForm" @ok="modalFormOk"></lsEquipment-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import LsEquipmentModal from './modules/LsEquipmentModal'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import Area from '@/components/_util/Area'

  export default {
    name: "LsEquipmentList",
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      LsEquipmentModal
    },
    data () {
      return {
        description: '设备信息管理页面',
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
            dataIndex: 'ename'
          },
          {
            title:'网关编号',
            align:"center",
            dataIndex: 'wgname' // wgid
          },
          {
            title:'设备类型',
            align:"center",
            dataIndex: 'etname' // etypeid
          },
          /*{
            title:'一级地区',
            align:"center",
            dataIndex: 'levelarea1',
            scopedSlots: {customRender: 'pcaSlot'}
          },
          {
            title:'二级地区',
            align:"center",
            dataIndex: 'levelarea2',
            scopedSlots: {customRender: 'pcaSlot'}
          },
          {
            title:'三级地区',
            align:"center",
            dataIndex: 'levelarea3',
            scopedSlots: {customRender: 'pcaSlot'}
          },*/
          {
            title:'精度',
            align:"center",
            dataIndex: 'eprecision'
          },
          {
            title:'维度',
            align:"center",
            dataIndex: 'dimensionality'
          },
          {
            title:'通讯方式',
            align:"center",
            dataIndex: 'communicationmode'
          },
          {
            title:'产能系数',
            align:"center",
            dataIndex: 'capacitycoefficient'
          },
          {
            title:'设备状态',
            align:"center",
            dataIndex: 'estate_dictText'
          },
          /*{
            title:'维护规则编号',
            align:"center",
            dataIndex: 'mrid'
          },*/
          {
            title:'设备图片',
            align:"center",
            dataIndex: 'epicture',
            scopedSlots: {customRender: 'imgSlot'}
          },
          {
            title:'安装时间',
            align:"center",
            dataIndex: 'installtime'
          },
          {
            title:'安装地址',
            align:"center",
            dataIndex: 'installaddress'
          },
          /*{
            title:'设备评分',
            align:"center",
            dataIndex: 'egrade'
          },*/
          {
            title:'备注',
            align:"center",
            dataIndex: 'disp'
          },
          {
            title:'创建人',
            align:"center",
            dataIndex: 'createBy'
          },
          {
            title:'创建日期',
            align:"center",
            dataIndex: 'createTime'
          },
          {
            title:'更新人',
            align:"center",
            dataIndex: 'updateBy'
          },
          {
            title:'更新日期',
            align:"center",
            dataIndex: 'updateTime'
          },
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
          list: "/system/lsEquipment/list",
          delete: "/system/lsEquipment/delete",
          deleteBatch: "/system/lsEquipment/deleteBatch",
          exportXlsUrl: "/system/lsEquipment/exportXls",
          importExcelUrl: "system/lsEquipment/importExcel",
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
    methods: {
      getPcaText(code){
        return this.pcaData.getText(code);
      },
      initDictConfig(){
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>