package org.jeecg.modules.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jeecg.modules.system.entity.MpiEquipment;
import org.jeecg.modules.system.entity.MpiEquipmentComponent;
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
public class MpiComponentSiteTreeModel implements Serializable{

    private static final long serialVersionUID = 1L;

    /** 对应MpiEquipmentComponent中的id字段,前端数据树中的key*/
    private String key;

    /** 对应MpiEquipmentComponent中的id字段,前端数据树中的value*/
    private String value;

    /** 对应MpiEquipmentComponent中的name字段,前端数据树中的title*/
    private String title;

    private String parentId;

    private Integer level; // 判断级别

    @JsonProperty("isLeaf")
    private boolean isLeaf;
    // 以下所有字段均与SysDepart相同

    /**设备组成编号*/
    private String id;
    /**设备编号*/
    private String equipmentId;
    /**设备名称*/
    private String equipmentName;
    /**部件编号*/
    private String componentId;
    /**部件名称*/
    private String componentName;
    /**部件评分*/
    private Integer componentScore;
    /**部件状态*/
    private Integer status;
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



    private List<MpiSiteTreeModel> children = new ArrayList<>();


    /**
     * 将MpiEquipmentComponent对象转换成MpiComponentSiteTreeModel对象
     * @param equipmentComponent
     */
	public MpiComponentSiteTreeModel(MpiEquipmentComponent equipmentComponent) {
	    this.level = 3; // 四级节点 -- 设备下级，设备组成
	    this.parentId = equipmentComponent.getEquipmentId(); // 父级为网关
		this.key = equipmentComponent.getId();
        this.value = equipmentComponent.getId();
        this.title = equipmentComponent.getId();
        this.id = equipmentComponent.getId();
        /**设备编号*/
        this.equipmentId = equipmentComponent.getEquipmentId();
        /**设备名称*/
        this.equipmentName = equipmentComponent.getEquipmentName();
        /**部件编号*/
        this.componentId = equipmentComponent.getComponentId();
        /**部件名称*/
        this.componentName = equipmentComponent.getComponentName();
        /**部件评分*/
        this.componentScore = equipmentComponent.getComponentScore();
        /**部件状态*/
        this.status = equipmentComponent.getStatus();
        /**备注*/
        this.remarks = equipmentComponent.getRemarks();
        this.delFlag = equipmentComponent.getDelFlag();
        this.createBy = equipmentComponent.getCreateBy();
        this.createTime = equipmentComponent.getCreateTime();
        this.updateBy = equipmentComponent.getUpdateBy();
        this.updateTime = equipmentComponent.getUpdateTime();
    }



    public List<MpiSiteTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<MpiSiteTreeModel> children) {
        if (children==null){
            this.isLeaf=true;
        }
        this.children = children;
    }



    public MpiComponentSiteTreeModel() { }



}
