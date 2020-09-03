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
 * @Description: 设备上行数据
 * @Author: jeecg-boot
 * @Date:   2020-07-16
 * @Version: V1.0
 */
@Data
@TableName("mpi_data_upload")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="mpi_data_upload对象", description="设备上行数据")
public class MpiDataUpload implements Serializable {
    private static final long serialVersionUID = 1L;

	/**上传ID*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "上传ID")
    private String id;


	/**设备ID*/
	@Excel(name = "设备ID", width = 15)
    @ApiModelProperty(value = "设备ID")
    private String equipmentId;
    /**设备名称*/
    @TableField(exist = false)
    private String equipmentName;
	/**监测点ID*/
	@Excel(name = "监测点ID", width = 15)
    @ApiModelProperty(value = "监测点ID")
    private String monitorId;
    /**监测点名称*/
    @TableField(exist = false)
    private String monitorName;


	/**主题*/
	@Excel(name = "主题", width = 15)
    @ApiModelProperty(value = "主题")
    private String topic;
	/**参数名称*/
	@Excel(name = "参数名称", width = 15)
    @ApiModelProperty(value = "参数名称")
    private String paramName;
	/**参数值*/
	@Excel(name = "参数值", width = 15)
    @ApiModelProperty(value = "参数值")
    private String paramValue;
	/**参数数值*/
	@Excel(name = "参数数值", width = 15)
    @ApiModelProperty(value = "参数数值")
    private Double paramData;
	/**是否报警0-是,1-否*/
	@Excel(name = "是否报警0-是,1-否", width = 15, dicCode = "is_warn")
	@Dict(dicCode = "is_warn")
    @ApiModelProperty(value = "是否报警0-是,1-否")
    private Integer isWarn;
	/**上传时间*/
	@Excel(name = "上传时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "上传时间")
    private Date uploadTime;
	/**返回值*/
	@Excel(name = "返回值", width = 15)
    @ApiModelProperty(value = "返回值")
    private String returnCode;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private String remarks;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
    private String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
	/**删除标识0-正常,1-删除*/
	@Excel(name = "删除标识0-正常,1-删除", width = 15, dicCode = "del_flag")
	@Dict(dicCode = "del_flag")
    @ApiModelProperty(value = "删除标识0-正常,1-删除")
    private Integer delFlag;
}
