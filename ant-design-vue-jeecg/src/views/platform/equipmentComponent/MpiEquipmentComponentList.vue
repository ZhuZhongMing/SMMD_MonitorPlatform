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
      <a-button type="primary" icon="download" @click="handleExportXls('设备组成')">导出</a-button>
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

    <mpiEquipmentComponent-modal ref="modalForm" @ok="modalFormOk"></mpiEquipmentComponent-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import MpiEquipmentComponentModal from './modules/MpiEquipmentComponentModal'
  import {filterMultiDictText} from '@/components/dict/JDictSelectUtil'
  import { getAction } from '@/api/manage'

  export default {
    name: "MpiEquipmentComponentList",
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      MpiEquipmentComponentModal
    },
    data () {
      return {
        /*设备编号*/
        equipmentId: '',
        description: '设备组成管理页面',
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
          list: "/system/mpiEquipmentComponent/list",
          delete: "/system/mpiEquipmentComponent/delete",
          deleteBatch: "/system/mpiEquipmentComponent/deleteBatch",
          exportXlsUrl: "/system/mpiEquipmentComponent/exportXls",
          importExcelUrl: "system/mpiEquipmentComponent/importExcel",
          getEquipmentIdsByCompany: "/system/mpiEquipment/getEquipmentIdsByCompany", // 根据公司编号查询设备ids
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
      getComponentMain(equipmentId) {
        this.queryParam.equipmentId = equipmentId;
        this.equipmentId = equipmentId
        //console.log("queryParam : " + JSON.stringify(this.queryParam))
        this.loadData(1);
      },
      initDictConfig(){
      },
      /*根据公司编号查询设备ids*/
      getEquipmentIdsByCompany(companyId) {
        // console.log("id -> " + companyId)
        if ("" != companyId) {
          getAction(this.url.getEquipmentIdsByCompany,{"id" : companyId}).then((res)=>{
            if(res.success){
              /*that.$message.success(res.message);
              that.$emit('ok');*/
              //console.log("result : " + JSON.stringify(res.result))
              //this.companyList = res.result
              //console.log(res.result)
              //this.queryParam.equipmentIds = res.result
              var equipmentIds = " "
              if (res.result.length > 0) {
                equipmentIds = ""
                for (let i = 0; i < res.result.length; i++) {
                  if (i != res.result.length - 1) {
                    equipmentIds += res.result[i] + ","
                  } else {
                    equipmentIds += res.result[i]
                  }
                }
              }
              this.queryParam.equipmentIds = equipmentIds
              this.loadData(1)
            }else{
              //this.loadData(1)
              that.$message.warning(res.message);
            }
          })
        } else {
          this.queryParam.equipmentIds = ""
          this.loadData()
        }
      },
      handleAdd: function () {
        if ('' != this.equipmentId) {
          this.$refs.modalForm.equipmentIds = this.equipmentId;
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