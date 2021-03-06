package org.jeecg.modules.system.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 设备
 * @Author: jeecg-boot
 * @Date:   2020-07-09
 * @Version: V1.0
 */
@Data
@TableName("mpi_equipment")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="mpi_equipment对象", description="设备")
public class MpiEquipment implements Serializable {
    private static final long serialVersionUID = 1L;

	/**设备编号*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "设备编号")
    private String id;
	/**设备名称*/
	@Excel(name = "设备名称", width = 15)
    @ApiModelProperty(value = "设备名称")
    private String equipmentName;
	/**网关编号*/
	@Excel(name = "网关编号", width = 15)
    @ApiModelProperty(value = "网关编号")
    private String gatewayId;
    /** 网关名称 **/
    @TableField(exist = false)
    private String gatewayName;
	/**设备类型*/
	@Excel(name = "设备类型", width = 15)
    @ApiModelProperty(value = "设备类型")
    private String equipmentType;
	/**一级地区*/
	@Excel(name = "一级地区", width = 15)
    @ApiModelProperty(value = "一级地区")
    private String level1Region;
	/**二级地区*/
	@Excel(name = "二级地区", width = 15)
    @ApiModelProperty(value = "二级地区")
    private String level2Region;
	/**三级地区*/
	@Excel(name = "三级地区", width = 15)
    @ApiModelProperty(value = "三级地区")
    private String level3Region;
	/**安装时间*/
	@Excel(name = "安装时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "安装时间")
    private Date deployTime;
	/**安装地址*/
	@Excel(name = "安装地址", width = 15)
    @ApiModelProperty(value = "安装地址")
    private String deployAddress;
	/**经度*/
	@Excel(name = "经度", width = 15)
    @ApiModelProperty(value = "经度")
    private BigDecimal longitude;
	/**纬度*/
	@Excel(name = "纬度", width = 15)
    @ApiModelProperty(value = "纬度")
    private BigDecimal latitude;
	/**通讯方式*/
	@Excel(name = "通讯方式", width = 15)
    @ApiModelProperty(value = "通讯方式")
    private String comType;
	/**产能系数*/
	@Excel(name = "产能系数", width = 15)
    @ApiModelProperty(value = "产能系数")
    private BigDecimal productCoefficient;
	/**设备状态0-离线,1-在线*/
	@Excel(name = "设备状态0-离线,1-在线", width = 15, dicCode = "status_mpi")
	@Dict(dicCode = "status_mpi")
    @ApiModelProperty(value = "设备状态0-离线,1-在线")
    private Integer status;
	/**维护规则编号*/
	@Excel(name = "维护规则编号", width = 15)
    @ApiModelProperty(value = "维护规则编号")
    private String maintainRule;
	/**设备图片*/
	@Excel(name = "设备图片", width = 15)
    @ApiModelProperty(value = "设备图片")
    private String equipmentImage;
	/**设备评分*/
	@Excel(name = "设备评分", width = 15)
    @ApiModelProperty(value = "设备评分")
    private Integer equipmentScore;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
    private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
	/**删除标识0-正常,1-已删除*/
	@Excel(name = "删除标识0-正常,1-已删除", width = 15, dicCode = "del_flag")
	@Dict(dicCode = "del_flag")
    @ApiModelProperty(value = "删除标识0-正常,1-已删除")
    private Integer delFlag;

    /**公司编号
     * 用于条件查询 -- 按公司检索设备信息
     * */
    @TableField(exist = false)
    @ApiModelProperty(value = "公司编号")
    private String companyId;
}
