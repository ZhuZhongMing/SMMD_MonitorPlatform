package org.jeecg.modules.system.entity.hubin;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="报警分组", description="报警分组")
@TableName("hb_task_model")
public class HBTaskModel implements Serializable {

    /**编号**/
    @ApiModelProperty(value = "编号")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**任务_米数_当前值**/
    @TableField("Task_MeterNumber_CuttentValue")
    private Integer Task_MeterNumber_CuttentValue;
    /**任务_张数_当前值1**/
    @TableField("Task_ZhangNumber_CuttentValue1")
    private Integer Task_ZhangNumber_CuttentValue1;
    /**任务_米数_总数**/
    @TableField("Task_MeterNumber_TotalNumber")
    private Integer Task_MeterNumber_TotalNumber;
    /**任务_张数_总数1**/
    @TableField("Task_ZhangNumber_TotalNumber1")
    private Integer Task_ZhangNumber_TotalNumber1;
    /**任务_米数_设定值**/
    @TableField("Task_MeterNumber_Setting")
    private Integer Task_MeterNumber_Setting;
    /**任务_张数_设定值1**/
    @TableField("Task_ZhangNumber_Setting1")
    private Integer Task_ZhangNumber_Setting1;
    /**任务_张数_设定值2**/
    @TableField("Task_ZhangNumber_Setting2")
    private Integer Task_ZhangNumber_Setting2;
    /**任务_张数_当前值2**/
    @TableField("Task_ZhangNumber_CuttentValue2")
    private Integer Task_ZhangNumber_CuttentValue2;
    /**任务_张数_总数2**/
    @TableField("Task_ZhangNumber_TotalNumber2")
    private Integer Task_ZhangNumber_TotalNumber2;
    /**任务_通电时间**/
    @TableField("Task_TheElectricityTime")
    private Integer Task_TheElectricityTime;
    /**任务_运转时间**/
    @TableField("Task_RunningTime")
    private Integer Task_RunningTime;
    /**任务_印刷时间**/
    @TableField("Task_PrintingTime")
    private Integer Task_PrintingTime;
    /**任务_总里程**/
    @TableField("Task_TotalMileage")
    private Integer Task_TotalMileage;
    /**任务_平均速度**/
    @TableField("Task_TheAverageVelocity")
    private Integer Task_TheAverageVelocity;

    /**采集时间**/
    @ApiModelProperty(value = "采集时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date create_time;

}
