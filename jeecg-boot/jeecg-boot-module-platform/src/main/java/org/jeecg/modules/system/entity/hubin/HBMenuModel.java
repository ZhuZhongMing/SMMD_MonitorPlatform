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
@TableName("hb_menu_model")
public class HBMenuModel implements Serializable {
    /**编号**/
    @ApiModelProperty(value = "编号")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**菜单_齿数**/
    @TableField("Menu_ToothNumber")
    private Integer Menu_ToothNumber;
    /**菜单_尾纸**/
    @TableField("Menu_TailPaper")
    private String Menu_TailPaper;
    /**菜单_放卷**/
    @TableField("Menu_Unwinding")
    private String Menu_Unwinding;
    /**菜单_当前值**/
    @TableField("Menu_CurrentValue")
    private Integer Menu_CurrentValue;
    /**菜单_设定值**/
    @TableField("Menu_Setting")
    private Integer Menu_Setting;

    /**采集时间**/
    @ApiModelProperty(value = "采集时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date create_time;

}
