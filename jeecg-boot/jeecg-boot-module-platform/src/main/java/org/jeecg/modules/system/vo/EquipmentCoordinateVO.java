package org.jeecg.modules.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecg.modules.system.entity.MpiEquipment;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 杭州三米明德科技有限公司
 * 设备地址信息  -- 坐标
 * @author zzm
 * date 2020-08-04
 */
@Data
public class EquipmentCoordinateVO {

    /**经度**/
    private Double lng;
    /**纬度**/
    private Double lat;

    /**设备编号*/
    private String id;
    /**设备名称*/
    private String equipmentName;
    /**网关编号*/
    private String gatewayId;
    /** 网关名称 **/
    private String gatewayName;
    /**设备类型*/
    private String equipmentType;
    /**一级地区*/
    private String level1Region;
    /**二级地区*/
    private String level2Region;
    /**三级地区*/
    private String level3Region;
    /**安装时间*/
    private Date deployTime;
    /**安装地址*/
    private String deployAddress;
    /**经度*/
    private BigDecimal longitude;
    /**纬度*/
    private BigDecimal latitude;
    /**通讯方式*/
    private String comType;
    /**产能系数*/
    private BigDecimal productCoefficient;
    /**设备状态0-离线,1-在线*/
    private Integer status;
    /**维护规则编号*/
    private String maintainRule;
    /**设备图片*/
    private String equipmentImage;
    /**设备评分*/
    private Integer equipmentScore;
    /**备注*/
    private String remarks;
    /**创建人*/
    private String createBy;
    /**创建时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**修改人*/
    private String updateBy;
    /**修改时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**删除标识0-正常,1-已删除*/
    private Integer delFlag;

    public EquipmentCoordinateVO(MpiEquipment mpiEquipment) {
        /**经度**/
        this.lng = new Double(String.valueOf(mpiEquipment.getLongitude()));
        /**纬度**/
        this.lat = new Double(String.valueOf(mpiEquipment.getLatitude()));

        /**设备编号*/
        this.id = mpiEquipment.getId();
        /**设备名称*/
        this.equipmentName = mpiEquipment.getEquipmentName();
        /**网关编号*/
        this.gatewayId = mpiEquipment.getGatewayId();
        /** 网关名称 **/
        this.gatewayName = mpiEquipment.getGatewayName();
        /**设备类型*/
        this.equipmentType = mpiEquipment.getEquipmentType();
        /**一级地区*/
        this.level1Region = mpiEquipment.getLevel1Region();
        /**二级地区*/
        this.level2Region = mpiEquipment.getLevel2Region();
        /**三级地区*/
        this.level3Region = mpiEquipment.getLevel3Region();
        /**安装时间*/
        this.deployTime = mpiEquipment.getDeployTime();
        /**安装地址*/
        this.deployAddress = mpiEquipment.getDeployAddress();
        /**经度*/
        this.longitude = mpiEquipment.getLongitude();
        /**纬度*/
        this.latitude = mpiEquipment.getLatitude();
        /**通讯方式*/
        this.comType = mpiEquipment.getComType();
        /**产能系数*/
        this.productCoefficient = mpiEquipment.getProductCoefficient();
        /**设备状态0-离线,1-在线*/
        this.status = mpiEquipment.getStatus();
        /**维护规则编号*/
        this.maintainRule = mpiEquipment.getMaintainRule();
        /**设备图片*/
        this.equipmentImage = mpiEquipment.getEquipmentImage();
        /**设备评分*/
        this.equipmentScore = mpiEquipment.getEquipmentScore();
        /**备注*/
        this.remarks = mpiEquipment.getRemarks();
        /**创建人*/
        this.createBy = mpiEquipment.getCreateBy();
        /**创建时间*/
        this.createTime = mpiEquipment.getCreateTime();
        /**修改人*/
        this.updateBy = mpiEquipment.getUpdateBy();
        /**修改时间*/
        this.updateTime = mpiEquipment.getUpdateTime();
        /**删除标识0-正常,1-已删除*/
        this.delFlag = mpiEquipment.getDelFlag();

    }

}
