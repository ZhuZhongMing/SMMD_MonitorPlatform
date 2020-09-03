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
@ApiModel(value="", description="")
@TableName("hb_correnct_model")
public class HBCorrenctModel implements Serializable {
    /**编号**/
    @ApiModelProperty(value = "编号")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**预套修正_1色**/
    @TableField("Correct_1Color")
    private String Correct_1Color;
    /**预套修正_2色**/
    @TableField("Correct_2Color")
    private String Correct_2Color;
    /**预套修正_3色**/
    @TableField("Correct_3Color")
    private String Correct_3Color;
    /**预套修正_4色**/
    @TableField("Correct_4Color")
    private String Correct_4Color;
    /**预套修正_5色**/
    @TableField("Correct_5Color")
    private String Correct_5Color;
    /**预套修正_6色**/
    @TableField("Correct_6Color")
    private String Correct_6Color;
    /**预套修正_7色**/
    @TableField("Correct_7Color")
    private String Correct_7Color;
    /**预套修正_8色**/
    @TableField("Correct_8Color")
    private String Correct_8Color;
    /**预套修正_9色**/
    @TableField("Correct_9Color")
    private String Correct_9Color;
    /**预套修正_10色**/
    @TableField("Correct_10Color")
    private String Correct_10Color;
    /**追标角度_1色**/
    @TableField("TracingAngle_1Color")
    private String TracingAngle_1Color;
    /**追标角度_2色**/
    @TableField("TracingAngle_2Color")
    private String TracingAngle_2Color;
    /**追标角度_3色**/
    @TableField("TracingAngle_3Color")
    private String TracingAngle_3Color;
    /**追标角度_4色**/
    @TableField("TracingAngle_4Color")
    private String TracingAngle_4Color;
    /**追标角度_5色**/
    @TableField("TracingAngle_5Color")
    private String TracingAngle_5Color;
    /**追标角度_6色**/
    @TableField("TracingAngle_6Color")
    private String TracingAngle_6Color;
    /**追标角度_7色**/
    @TableField("TracingAngle_7Color")
    private String TracingAngle_7Color;
    /**追标角度_8色**/
    @TableField("TracingAngle_8Color")
    private String TracingAngle_8Color;
    /**追标角度_9色**/
    @TableField("TracingAngle_9Color")
    private String TracingAngle_9Color;
    /**追标角度_10色**/
    @TableField("TracingAngle_10Color")
    private String TracingAngle_10Color;
    /**追标角度_Cutter**/
    @TableField("TracingAngle_Cutter")
    private String TracingAngle_Cutter;
    /**追标调整_1色**/
    @TableField("MarkAdjustment_1Color")
    private String MarkAdjustment_1Color;
    /**追标调整_2色**/
    @TableField("MarkAdjustment_2Color")
    private String MarkAdjustment_2Color;
    /**追标调整_3色**/
    @TableField("MarkAdjustment_3Color")
    private String MarkAdjustment_3Color;
    /**追标调整_4色**/
    @TableField("MarkAdjustment_4Color")
    private String MarkAdjustment_4Color;
    /**追标调整_5色**/
    @TableField("MarkAdjustment_5Color")
    private String MarkAdjustment_5Color;
    /**追标调整_6色**/
    @TableField("MarkAdjustment_6Color")
    private String MarkAdjustment_6Color;
    /**追标调整_7色**/
    @TableField("MarkAdjustment_7Color")
    private String MarkAdjustment_7Color;
    /**追标调整_8色**/
    @TableField("MarkAdjustment_8Color")
    private String MarkAdjustment_8Color;
    /**追标调整_9色**/
    @TableField("MarkAdjustment_9Color")
    private String MarkAdjustment_9Color;
    /**追标调整_10色**/
    @TableField("MarkAdjustment_10Color")
    private String MarkAdjustment_10Color;
    /**追标调整_Cutter**/
    @TableField("MarkAdjustment_Cutter")
    private String MarkAdjustment_Cutter;
    /**温度_1色**/
    @TableField("Temperature_1Color")
    private String Temperature_1Color;
    /**温度_2色**/
    @TableField("Temperature_2Color")
    private String Temperature_2Color;
    /**温度_3色**/
    @TableField("Temperature_3Color")
    private String Temperature_3Color;
    /**温度_4色**/
    @TableField("Temperature_4Color")
    private String Temperature_4Color;
    /**温度_5色**/
    @TableField("Temperature_5Color")
    private String Temperature_5Color;
    /**温度_6色**/
    @TableField("Temperature_6Color")
    private String Temperature_6Color;
    /**温度_7色**/
    @TableField("Temperature_7Color")
    private String Temperature_7Color;
    /**温度_8色**/
    @TableField("Temperature_8Color")
    private String Temperature_8Color;
    /**温度_9色**/
    @TableField("Temperature_9Color")
    private String Temperature_9Color;
    /**温度_10色**/
    @TableField("Temperature_10Color")
    private String Temperature_10Color;
    /**实际角度_1色**/
    @TableField("ActualAngle_1Color")
    private String ActualAngle_1Color;
    /**实际角度_2色**/
    @TableField("ActualAngle_2Color")
    private String ActualAngle_2Color;
    /**实际角度_3色**/
    @TableField("ActualAngle_3Color")
    private String ActualAngle_3Color;
    /**实际角度_4色**/
    @TableField("ActualAngle_4Color")
    private String ActualAngle_4Color;
    /**实际角度_5色**/
    @TableField("ActualAngle_5Color")
    private String ActualAngle_5Color;
    /**实际角度_6色**/
    @TableField("ActualAngle_6Color")
    private String ActualAngle_6Color;
    /**实际角度_7色**/
    @TableField("ActualAngle_7Color")
    private String ActualAngle_7Color;
    /**实际角度_8色**/
    @TableField("ActualAngle_8Color")
    private String ActualAngle_8Color;
    /**实际角度_9色**/
    @TableField("ActualAngle_9Color")
    private String ActualAngle_9Color;
    /**实际角度_10色**/
    @TableField("ActualAngle_10Color")
    private String ActualAngle_10Color;
    /**实际角度_Cutter**/
    @TableField("ActualAngle_Cutter")
    private String ActualAngle_Cutter;
    /**压筒_1色**/
    @TableField("PressureCylinder_1Color")
    private Integer PressureCylinder_1Color;
    /**版筒_1色**/
    @TableField("Cylinder_1Color")
    private Integer Cylinder_1Color;
    /**压筒_2色**/
    @TableField("PressureCylinder_2Color")
    private Integer PressureCylinder_2Color;
    /**版筒_2色**/
    @TableField("Cylinder_2Color")
    private Integer Cylinder_2Color;
    /**压筒_3色**/
    @TableField("PressureCylinder_3Color")
    private Integer PressureCylinder_3Color;
    /**版筒_3色**/
    @TableField("Cylinder_3Color")
    private Integer Cylinder_3Color;
    /**压筒_4色**/
    @TableField("PressureCylinder_4Color")
    private Integer PressureCylinder_4Color;
    /**版筒_4色**/
    @TableField("Cylinder_4Color")
    private Integer Cylinder_4Color;
    /**压筒_5色**/
    @TableField("PressureCylinder_5Color")
    private Integer PressureCylinder_5Color;
    /**版筒_5色**/
    @TableField("Cylinder_5Color")
    private Integer Cylinder_5Color;
    /**压筒_6色**/
    @TableField("PressureCylinder_6Color")
    private Integer PressureCylinder_6Color;
    /**版筒_6色**/
    @TableField("Cylinder_6Color")
    private Integer Cylinder_6Color;
    /**压筒_7色**/
    @TableField("PressureCylinder_7Color")
    private Integer PressureCylinder_7Color;
    /**版筒_7色**/
    @TableField("Cylinder_7Color")
    private Integer Cylinder_7Color;
    /**压筒_8色**/
    @TableField("PressureCylinder_8Color")
    private Integer PressureCylinder_8Color;
    /**版筒_8色**/
    @TableField("Cylinder_8Color")
    private Integer Cylinder_8Color;
    /**压筒_9色**/
    @TableField("PressureCylinder_9Color")
    private Integer PressureCylinder_9Color;
    /**版筒_9色**/
    @TableField("Cylinder_9Color")
    private Integer Cylinder_9Color;
    /**压筒_10色**/
    @TableField("PressureCylinder_10Color")
    private Integer PressureCylinder_10Color;
    /**版筒_10色**/
    @TableField("Cylinder_10Color")
    private Integer Cylinder_10Color;
    /**版筒_Cutter**/
    @TableField("Cylinder_Cutter")
    private Integer Cylinder_Cutter;
    /**追标_1色**/
    @TableField("BidChasing_1Color")
    private Integer BidChasing_1Color;
    /**追标_2色**/
    @TableField("BidChasing_2Color")
    private Integer BidChasing_2Color;
    /**追标_3色**/
    @TableField("BidChasing_3Color")
    private Integer BidChasing_3Color;
    /**追标_4色**/
    @TableField("BidChasing_4Color")
    private Integer BidChasing_4Color;
    /**追标_5色**/
    @TableField("BidChasing_5Color")
    private Integer BidChasing_5Color;
    /**追标_6色**/
    @TableField("BidChasing_6Color")
    private Integer BidChasing_6Color;
    /**追标_7色**/
    @TableField("BidChasing_7Color")
    private Integer BidChasing_7Color;
    /**追标_8色**/
    @TableField("BidChasing_8Color")
    private Integer BidChasing_8Color;
    /**追标_9色**/
    @TableField("BidChasing_9Color")
    private Integer BidChasing_9Color;
    /**追标_10色**/
    @TableField("BidChasing_10Color")
    private Integer BidChasing_10Color;
    /**追标_Cutter**/
    @TableField("BidChasing_Cutter")
    private Integer BidChasing_Cutter;
    /**色标_1色**/
    @TableField("ColorCode_1Color")
    private Integer ColorCode_1Color;
    /**色标_2色**/
    @TableField("ColorCode_2Color")
    private Integer ColorCode_2Color;
    /**色标_3色**/
    @TableField("ColorCode_3Color")
    private Integer ColorCode_3Color;
    /**色标_4色**/
    @TableField("ColorCode_4Color")
    private Integer ColorCode_4Color;
    /**色标_5色**/
    @TableField("ColorCode_5Color")
    private Integer ColorCode_5Color;
    /**色标_6色**/
    @TableField("ColorCode_6Color")
    private Integer ColorCode_6Color;
    /**色标_7色**/
    @TableField("ColorCode_7Color")
    private Integer ColorCode_7Color;
    /**色标_8色**/
    @TableField("ColorCode_8Color")
    private Integer ColorCode_8Color;
    /**色标_9色**/
    @TableField("ColorCode_9Color")
    private Integer ColorCode_9Color;
    /**色标_10色**/
    @TableField("ColorCode_10Color")
    private Integer ColorCode_10Color;
    /**色标_Cutter**/
    @TableField("ColorCode_Cutter")
    private Integer ColorCode_Cutter;

    /**采集时间**/
    @ApiModelProperty(value = "采集时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date create_time;
}
