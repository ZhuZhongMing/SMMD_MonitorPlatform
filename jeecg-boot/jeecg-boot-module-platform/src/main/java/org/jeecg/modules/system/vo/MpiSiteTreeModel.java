package org.jeecg.modules.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.jeecg.modules.system.entity.MpiEquipmentComponent;
import org.jeecg.modules.system.entity.MpiMonitorSite;
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
public class MpiSiteTreeModel implements Serializable{

    private static final long serialVersionUID = 1L;

    /** 对应MpiMonitorSite中的id字段,前端数据树中的key*/
    private String key;

    /** 对应MpiMonitorSite中的id字段,前端数据树中的value*/
    private String value;

    /** 对应MpiMonitorSite中的name字段,前端数据树中的title*/
    private String title;

    private String parentId;

    private Integer level; // 判断级别

    @JsonProperty("isLeaf")
    private boolean isLeaf;
    // 以下所有字段均与SysDepart相同

    /**监测点ID*/
    private String id;
    /**设备组成ID*/
    private String componentId;
    /**监测点名称*/
    private String monitorName;
    /**监测参数*/
    private String parameter;
    /**当前值*/
    private BigDecimal currentValue;
    /**单位*/
    private String unit;
    /**主题*/
    private String topic;
    /**监测点状态*/
    private String status;
    /**上限报警*/
    private BigDecimal upperAlarm;
    /**下限报警*/
    private BigDecimal lowerAlarm;
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
     * @param monitorSite
     */
	public MpiSiteTreeModel(MpiMonitorSite monitorSite) {
        this.isLeaf = true; // 最后一级
	    this.level = 4; // 五级节点 -- 设备组成下级，监测点
	    this.parentId = monitorSite.getComponentId(); // 父级为设备组成
		this.key = monitorSite.getId();
        this.value = monitorSite.getId();
        this.title = monitorSite.getMonitorName();
        this.id = monitorSite.getId();
        /**设备组成ID*/
        this.componentId = monitorSite.getComponentId();
        /**监测点名称*/
        this.monitorName = monitorSite.getMonitorName();
        /**监测参数*/
        this.parameter = monitorSite.getParameter();
        /**当前值*/
        this.currentValue = monitorSite.getCurrentValue();
        /**单位*/
        this.unit = monitorSite.getUnit();
        /**主题*/
        this.topic = monitorSite.getTopic();
        /**监测点状态*/
        this.status = monitorSite.getStatus();
        /**上限报警*/
        this.upperAlarm = monitorSite.getUpperAlarm();
        /**下限报警*/
        this.lowerAlarm = monitorSite.getLowerAlarm();
        /**备注*/
        this.remarks = monitorSite.getRemarks();
        this.delFlag = monitorSite.getDelFlag();
        this.createBy = monitorSite.getCreateBy();
        this.createTime = monitorSite.getCreateTime();
        this.updateBy = monitorSite.getUpdateBy();
        this.updateTime = monitorSite.getUpdateTime();
    }



    public List<MpiSiteTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<MpiSiteTreeModel> children) {
        /*if (children==null){
            this.isLeaf=true;
        }*/
        this.isLeaf = true; // 最后一级
        this.children = children;
    }



    public MpiSiteTreeModel() { }



}
