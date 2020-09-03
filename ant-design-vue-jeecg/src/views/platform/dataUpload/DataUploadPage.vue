<template>
  <!-- defaultActiveKey="1"-->
  <a-tabs v-model="activeKey" @change="changeKey">
    <a-tab-pane tab="列表" key="1">
      <mpi-data-upload-list ref="MpiDataUploadList"></mpi-data-upload-list>
    </a-tab-pane>
    <a-tab-pane tab="图表" key="2">
      图表
    </a-tab-pane>
  </a-tabs>
</template>

<script>
  import MpiDataUploadList from "@/views/platform/dataUpload/MpiDataUploadList"
    export default {
        name: 'DataUploadPage',
        data () {
          return {
            activeKey: "1"
          }
        },
        components: {
          MpiDataUploadList,
        },
        methods: {
          changeKey(key) {
            if ("2" == key || 2 == key) {
              var equipmentId = this.$refs.MpiDataUploadList.queryParam.equipmentId
              if ("" == equipmentId || equipmentId == undefined) {
                this.$message.warning("请先选择设备");
                this.activeKey = "1"
              }
            }

          }
        }
    }
</script>

<style scoped>

</style>