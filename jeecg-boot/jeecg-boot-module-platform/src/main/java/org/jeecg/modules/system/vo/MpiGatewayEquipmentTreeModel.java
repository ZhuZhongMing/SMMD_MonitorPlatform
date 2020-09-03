package org.jeecg.modules.system.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecg.modules.system.entity.MpiCompany;
import org.jeecg.modules.system.entity.MpiGateway;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
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
public class MpiGatewayEquipmentTreeModel implements Serializable{

    private static final long serialVersionUID = 1L;

    /** 对应MpiGateway中的id字段,前端数据树中的key*/
    private String key;

    /** 对应MpiGateway中的id字段,前端数据树中的value*/
    private String value;

    /** 对应MpiGateway字段,前端数据树中的title*/
    private String title;

    private String parentId;

    private Integer level; // 判断级别

    @JsonProperty("isLeaf")
    private boolean isLeaf;
    // 以下所有字段均与SysDepart相同

    /**网关编号*/
    private String id;
    /**网关名称*/
    private String gatewayName;
    /**公司编号*/
    private String companyId;
    /** 公司名称 **/
    private String companyName;
    /**网关IP*/
    private String ipAddress;
    /**网关IMEI码*/
    private String imeiCode;
    /**类型*/
    private String gatewayType;
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
    @Dict(dicCode = "del_flag")
    private Integer delFlag;



    private List<MpiEquipmentComponentTreeModel> children = new ArrayList<>();


    /**
     * 将SysDepart对象转换成SysDepartTreeModel对象
     * @param gateway
     */
	public MpiGatewayEquipmentTreeModel(MpiGateway gateway) {
	    this.level = 1; // 二级节点 -- 公司下级，网关
	    this.parentId = gateway.getCompanyId(); // 父级为公司
		this.key = gateway.getId();
        this.value = gateway.getId();
        this.title = gateway.getGatewayName();
        this.id = gateway.getId();
        /**网关名称*/
        this.gatewayName = gateway.getGatewayName();
        /**公司编号*/
        this.companyId = gateway.getCompanyId();
        /** 公司名称 **/
        this.companyName = gateway.getCompanyName();
        /**网关IP*/
        this.ipAddress = gateway.getIpAddress();
        /**网关IMEI码*/
        this.imeiCode = gateway.getImeiCode();
        /**类型*/
        this.gatewayType = gateway.getGatewayType();
        /**备注*/
        this.remarks = gateway.getRemarks();
        this.delFlag = gateway.getDelFlag();
        this.createBy = gateway.getCreateBy();
        this.createTime = gateway.getCreateTime();
        this.updateBy = gateway.getUpdateBy();
        this.updateTime = gateway.getUpdateTime();
    }



    public List<MpiEquipmentComponentTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<MpiEquipmentComponentTreeModel> children) {
        if (children==null){
            this.isLeaf=true;
        }
        this.children = children;
    }



    public MpiGatewayEquipmentTreeModel() { }



}
