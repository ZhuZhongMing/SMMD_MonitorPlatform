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
 * @Description: 监测点
 * @Author: jeecg-boot
 * @Date:   2020-07-10
 * @Version: V1.0
 */
@Data
@TableName("mpi_monitor_site")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="mpi_monitor_site对象", description="监测点")
public class MpiMonitorSite implements Serializable {
    private static final long serialVersionUID = 1L;

	/**监测点ID*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "监测点ID")
    private String id;
	/**设备组成ID*/
	@Excel(name = "设备组成ID", width = 15)
    @ApiModelProperty(value = "设备组成ID")
    private String componentId;
	/**监测点名称*/
	@Excel(name = "监测点名称", width = 15)
    @ApiModelProperty(value = "监测点名称")
    private String monitorName;
	/**监测参数*/
	@Excel(name = "监测参数", width = 15)
    @ApiModelProperty(value = "监测参数")
    private String parameter;
	/**当前值*/
	@Excel(name = "当前值", width = 15)
    @ApiModelProperty(value = "当前值")
    private BigDecimal currentValue;
	/**单位*/
	@Excel(name = "单位", width = 15)
    @ApiModelProperty(value = "单位")
    private String unit;
	/**主题*/
	@Excel(name = "主题", width = 15)
    @ApiModelProperty(value = "主题")
    private String topic;
	/**监测点状态*/
	@Excel(name = "监测点状态", width = 15)
    @ApiModelProperty(value = "监测点状态")
    private String status;
	/**上限报警*/
	@Excel(name = "上限报警", width = 15)
    @ApiModelProperty(value = "上限报警")
    private BigDecimal upperAlarm;
	/**下限报警*/
	@Excel(name = "下限报警", width = 15)
    @ApiModelProperty(value = "下限报警")
    private BigDecimal lowerAlarm;
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

    /**设备组成ids
     * 多个设备组成id -- 条件查询
     * **/
    @TableField(exist = false)
    private String componentIds;
}
