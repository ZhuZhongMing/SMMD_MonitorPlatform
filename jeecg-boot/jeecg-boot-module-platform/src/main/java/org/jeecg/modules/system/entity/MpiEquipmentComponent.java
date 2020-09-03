package org.jeecg.modules.system.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import java.util.List;

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
 * @Description: 设备组成
 * @Author: jeecg-boot
 * @Date:   2020-07-09
 * @Version: V1.0
 */
@Data
@TableName("mpi_equipment_component")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="mpi_equipment_component对象", description="设备组成")
public class MpiEquipmentComponent implements Serializable {
    private static final long serialVersionUID = 1L;

	/**设备组成编号*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "设备组成编号")
    private String id;
	/**设备编号*/
	@Excel(name = "设备编号", width = 15)
    @ApiModelProperty(value = "设备编号")
    private String equipmentId;
    /**设备名称*/
    @TableField(exist = false)
    private String equipmentName;
	/**部件编号*/
	@Excel(name = "部件编号", width = 15)
    @ApiModelProperty(value = "部件编号")
    private String componentId;
    /**部件名称*/
    @TableField(exist = false)
    private String componentName;
	/**部件评分*/
	@Excel(name = "部件评分", width = 15)
    @ApiModelProperty(value = "部件评分")
    private Integer componentScore;
	/**部件状态*/
	@Excel(name = "部件状态", width = 15, dicCode = "status_mpi")
	@Dict(dicCode = "status_mpi")
    @ApiModelProperty(value = "部件状态")
    private Integer status;
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

	/**设备ids
     * 多个设备id -- 条件查询
     * **/
	@TableField(exist = false)
    private String equipmentIds;
}
