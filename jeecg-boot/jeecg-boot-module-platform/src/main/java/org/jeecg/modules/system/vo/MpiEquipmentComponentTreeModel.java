package org.jeecg.modules.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecg.modules.system.entity.MpiEquipment;
import org.jeecg.modules.system.entity.MpiGateway;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 部门表 存储树结构数据的实体类
 * <p>
 * 
 * @Author Steve
 * @Since 2019-01-22 
 */
@Data
public class MpiEquipmentComponentTreeModel implements Serializable{

    private static final long serialVersionUID = 1L;

    /** 对应MpiEquipment中的id字段,前端数据树中的key*/
    private String key;

    /** 对应MpiEquipment中的id字段,前端数据树中的value*/
    private String value;

    /** 对应MpiEquipment中的name字段,前端数据树中的title*/
    private String title;

    private String parentId;

    private Integer level; // 判断级别

    @JsonProperty("isLeaf")
    private boolean isLeaf;
    // 以下所有字段均与SysDepart相同

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
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
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



    private List<MpiComponentSiteTreeModel> children = new ArrayList<>();


    /**
     * 将MpiEquipment对象转换成MpiEquipmentComponentTreeModel对象
     * @param equipment
     */
	public MpiEquipmentComponentTreeModel(MpiEquipment equipment) {
	    this.level = 2; // 三级节点 -- 网关下级，设备
	    this.parentId = equipment.getGatewayId(); // 父级为网关
		this.key = equipment.getId();
        this.value = equipment.getId();
        this.title = equipment.getEquipmentName();
        this.id = equipment.getId();
        /**设备名称*/
        this.equipmentName = equipment.getEquipmentName();
        /**网关编号*/
        this.gatewayId = equipment.getGatewayId();
        /** 网关名称 **/
        this.gatewayName = equipment.getGatewayName();
        /**设备类型*/
        this.equipmentType = equipment.getEquipmentType();
        /**一级地区*/
        this.level1Region = equipment.getLevel1Region();
        /**二级地区*/
        this.level2Region = equipment.getLevel2Region();
        /**三级地区*/
        this.level3Region = equipment.getLevel3Region();
        /**安装时间*/
        this.deployTime = equipment.getDeployTime();
        /**安装地址*/
        this.deployAddress = equipment.getDeployAddress();
        /**经度*/
        this.longitude = equipment.getLongitude();
        /**纬度*/
        this.latitude = equipment.getLatitude();
        /**通讯方式*/
        this.comType = equipment.getComType();
        /**产能系数*/
        this.productCoefficient = equipment.getProductCoefficient();
        /**设备状态0-离线,1-在线*/
        this.status = equipment.getStatus();
        /**维护规则编号*/
        this.maintainRule = equipment.getMaintainRule();
        /**设备图片*/
        this.equipmentImage = equipment.getEquipmentImage();
        /**设备评分*/
        this.equipmentScore = equipment.getEquipmentScore();
        /**备注*/
        this.remarks = equipment.getRemarks();
        this.delFlag = equipment.getDelFlag();
        this.createBy = equipment.getCreateBy();
        this.createTime = equipment.getCreateTime();
        this.updateBy = equipment.getUpdateBy();
        this.updateTime = equipment.getUpdateTime();
    }



    public List<MpiComponentSiteTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<MpiComponentSiteTreeModel> children) {
        if (children==null){
            this.isLeaf=true;
        }
        this.children = children;
    }



    public MpiEquipmentComponentTreeModel() { }



}
