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
@ApiModel(value=" ", description=" ")
@TableName("hb_function_model")
public class HBFunctionModel implements Serializable {
    /**编号**/
    @ApiModelProperty(value = "编号")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**功能_翻转数值**/
    @TableField("Function_ReverseValue")
    private String Function_ReverseValue;
    /**功能_放卷尾纸**/
    @TableField("Function_UnwindingTailPaper")
    private Integer Function_UnwindingTailPaper;
    /**功能_UA系统**/
    @TableField("Function_UASystem")
    private Integer Function_UASystem;
    /**功能_翻转使用**/
    @TableField("Function_Reversal")
    private Integer Function_Reversal;
    /**功能_放卷牵引张力检测**/
    @TableField("Function_UnwindingTractionTension")
    private Integer Function_UnwindingTractionTension;
    /**功能_中间牵引张力检测**/
    @TableField("Function_MiddleTowPaperBreak")
    private Integer Function_MiddleTowPaperBreak;
    /**功能_出纸牵引断纸检测**/
    @TableField("Function_PaperOutTowPaperBreak")
    private Integer Function_PaperOutTowPaperBreak;
    /**功能_冷烫1尾纸**/
    @TableField("Function_ColdWave1Paper")
    private Integer Function_ColdWave1Paper;
    /**功能_冷烫2尾纸**/
    @TableField("Function_ColdWave2Paper")
    private Integer Function_ColdWave2Paper;
    /**功能_电晕风机（单动）**/
    @TableField("Function_CoronaFan_SingleAction")
    private Integer Function_CoronaFan_SingleAction;
    /**功能_电晕风机（连动）**/
    @TableField("Function_CoronaFan_ManyAction")
    private Integer Function_CoronaFan_ManyAction;
    /**功能_电晕风机（不使用）**/
    @TableField("Function_CoronaFan_NotUsed")
    private Integer Function_CoronaFan_NotUsed;

    /**采集时间**/
    @ApiModelProperty(value = "采集时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date create_time;
}
