package org.jeecg.modules.system.vo;

import lombok.Data;

/**
 * 杭州三米明德科技有限公司
 *
 * @author zzm
 * date 2020-07-03
 */
@Data
public class MpiDataUploadEquipmentSiteVo {

    /** 设备组成编号 **/
    private String id;
    /**设备名称*/
    private String equipmentName;
    /**监测点名称*/
    private String monitorName;

}
