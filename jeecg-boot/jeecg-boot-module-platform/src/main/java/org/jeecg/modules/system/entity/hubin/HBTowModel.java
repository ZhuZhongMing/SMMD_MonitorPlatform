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

/**
 * 杭州三米明德科技有限公司
 *  湖滨，物通博联网关，牵引分组
 * @author zzm
 * date 2020-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="牵引分组", description="牵引分组")
@TableName("hb_tow_model")
public class HBTowModel implements Serializable {
    /**编号**/
    @ApiModelProperty(value = "编号")
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 牵引_张力1_1 **/
    @TableField("Tow_Tension1_1")
    private String Tow_Tension1_1;
    /** 牵引_张力2_1 **/
    @TableField("Tow_Tension2_1")
    private String Tow_Tension2_1;
    /** 牵引_张力3_1 **/
    @TableField("Tow_Tension3_1")
    private String Tow_Tension3_1;
    /** 牵引_张力1_2 **/
    @TableField("Tow_Tension1_2")
    private String Tow_Tension1_2;
    /** 牵引_张力2_2 **/
    @TableField("Tow_Tension2_2")
    private String Tow_Tension2_2;
    /** 牵引_张力3_2 **/
    @TableField("Tow_Tension3_2")
    private String Tow_Tension3_2;
    /**牵引_牵引率1**/
    @TableField("Tow_TractionRate1")
    private String Tow_TractionRate1;
    /**牵引_牵引率2**/
    @TableField("Tow_TractionRate2")
    private String Tow_TractionRate2;
    /**牵引_牵引率3**/
    @TableField("Tow_TractionRate3")
    private String Tow_TractionRate3;
    /**卷径_设定值1**/
    @TableField("Roll_Setting1")
    private String Roll_Setting1;
    /**卷径_设定值2**/
    @TableField("Roll_Setting2")
    private String Roll_Setting2;
    /**卷径_设定值3**/
    @TableField("Roll_Setting3")
    private String Roll_Setting3;
    /**卷径_当前值1**/
    @TableField("Roll_CurrentValue1")
    private String Roll_CurrentValue1;
    /**卷径_当前值2**/
    @TableField("Roll_CurrentValue2")
    private String Roll_CurrentValue2;
    /**卷径_当前值3**/
    @TableField("Roll_CurrentValue3")
    private String Roll_CurrentValue3;
    /**卷径_减速值1**/
    @TableField("Roll_SlowDown1")
    private String Roll_SlowDown1;
    /**牵引_模式1**/ // 0/1 布尔类型
    @TableField("Menu_TowPattern1")
    private Integer Menu_TowPattern1;
    /**牵引_模式2**/ // 0/1 布尔类型
    @TableField("Tow_Pattern2")
    private Integer Tow_Pattern2;
    /**牵引_模式3**/ // 0/1 布尔类型
    @TableField("Tow_Pattern3")
    private Integer Tow_Pattern3;
    /**材料类型_PE**/ // 0/1 布尔类型 0:否，1:是
    @TableField("Menu_PE")
    private Integer Menu_PE;
    /**材料类型_铜纸板**/ // 0/1 布尔类型 0:否，1:是
    @TableField("Menu_ArtPaper")
    private Integer Menu_ArtPaper;
    /**材料类型_膜**/ // 0/1 布尔类型 0:否，1:是
    @TableField("Menu_Membrane")
    private Integer Menu_Membrane;

    /**采集时间**/
    @ApiModelProperty(value = "采集时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date create_time;
}
