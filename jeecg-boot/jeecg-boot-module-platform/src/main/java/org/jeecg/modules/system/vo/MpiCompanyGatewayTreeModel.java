package org.jeecg.modules.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecg.modules.system.entity.MpiCompany;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 部门表 存储树结构数据的实体类
 * <p>
 * 
 * @Author Steve
 * @Since 2019-01-22 
 */
@Data
public class MpiCompanyGatewayTreeModel implements Serializable{

    private static final long serialVersionUID = 1L;

    /** 对应SysDepart中的id字段,前端数据树中的key*/
    private String key;

    /** 对应SysDepart中的id字段,前端数据树中的value*/
    private String value;

    /** 对应depart_name字段,前端数据树中的title*/
    private String title;

    private String parentId;

    private Integer level; // 判断级别

    @JsonProperty("isLeaf")
    private boolean isLeaf;
    // 以下所有字段均与SysDepart相同

    /**公司编号*/
    private String id;
    /**公司名称*/
    private String companyName;
    /**公司地址*/
    private String address;
    /**联系人*/
    private String contactPerson;
    /**联系电话*/
    private String contactNumber;
    /**邮箱*/
    private String email;
    /**企业网址*/
    private String website;
    /**简称*/
    private String abbreviation;
    /**纳税人识别号*/
    private String taxNumber;
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



    private List<MpiGatewayEquipmentTreeModel> children = new ArrayList<>();


    /**
     * 将SysDepart对象转换成SysDepartTreeModel对象
     * @param company
     */
	public MpiCompanyGatewayTreeModel(MpiCompany company) {
        this.level = 0; //根节点 -- 公司
		this.key = company.getId();
        this.value = company.getId();
        this.title = company.getCompanyName();
        this.id = company.getId();
        this.companyName = company.getCompanyName();
        /**公司地址*/
        this.address = company.getAddress();
        /**联系人*/
        this.contactPerson = company.getContactPerson();
        /**联系电话*/
        this.contactNumber = company.getContactNumber();
        /**邮箱*/
        this.email = company.getEmail();
        /**企业网址*/
        this.website = company.getWebsite();
        /**简称*/
        this.abbreviation = company.getAbbreviation();
        /**纳税人识别号*/
        this.taxNumber = company.getTaxNumber();
        /**备注*/
        this.remarks = company.getRemarks();
        this.delFlag = company.getDelFlag();
        this.createBy = company.getCreateBy();
        this.createTime = company.getCreateTime();
        this.updateBy = company.getUpdateBy();
        this.updateTime = company.getUpdateTime();
    }



    public List<MpiGatewayEquipmentTreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<MpiGatewayEquipmentTreeModel> children) {
        if (children==null){
            this.isLeaf=true;
        }
        this.children = children;
    }



    public MpiCompanyGatewayTreeModel() { }



}
