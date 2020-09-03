package org.jeecg.modules.system.entity;

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
import java.math.BigDecimal;
import java.util.Date;

/**
 * 杭州三米明德科技有限公司
 * @Description: 充电桩
 *<p> 充电桩 </p>
 * @author zzm
 * date 2020-08-18
 */
@Data
@TableName("sc_outlet")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="sc_outlet对象", description="充电桩")
public class ScOutlet implements Serializable {
    /** 智能充电桩id **/
    @TableField(value = "outletId")
    private String outletId;
    /** 供应商id **/
    @TableField(value = "providerId")
    private String providerId;
    /** 充电桩名称 **/
    @TableField(value = "outletName")
    private String outletName;
    /** 类型编号 **/
    @TableField(value = "typeId")
    private String typeId;
    /** 价格 **/
    @TableField(value = "price")
    private BigDecimal price;
    /** 省份 **/
    @TableField(value = "province")
    private String province;
    /** 城市 **/
    @TableField(value = "city")
    private String city;
    /** 地区 **/
    @TableField(value = "areaID")
    private String areaID;
    /**服务编号**/
    @TableField(value = "serverId")
    private String serverId;
    /**安装地点**/
    @TableField(value = "installationSite")
    private String installtionSite;
    /**经度**/
    @TableField(value = "longitude")
    private BigDecimal longitude;
    /**纬度**/
    @TableField(value = "latitude")
    private BigDecimal latitude;
    /**序列号**/
    @TableField(value = "serialNumber")
    private Integer serialNumber;
    /**支付方式**/
    @TableField(value = "priceMode")
    private Integer priceMode;
    /**描述**/
    @TableField(value = "Describe")
    private String Describe;
    /** ip地址 **/
    @TableField(value = "ip")
    private String ip;
    /**序列号**/
    @TableField(value = "serialNum")
    private Integer serialNum;
    /**会话**/
    @TableField(value = "")
    private String Session;
    /** app密钥 **/
    @TableField(value = "appKey")
    private String appKey;
    /**是否在线**/
    @TableField(value = "isOnline")
    private Integer isOnline;
    /**信号质量**/
    @TableField(value = "signalQuality")
    private Integer signalQuality;
    /**开启**/
    @TableField(value = "enable")
    private Integer enable;
    /** 版本 **/
    @TableField(value = "version")
    private String version;
    /** 继承电路识别码 **/
    @TableField(value = "ICCID")
    private String ICCID;
    /**启动时间**/
    @TableField(value = "enableDate")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "启动时间")
    private Date enableDate;
    /**还原时间**/
    @TableField(value = "recoverDate")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "还原时间")
    private Date recoverDate;
    /**电价**/
    @TableField(value = "electricityPrice")
    private BigDecimal electricityPrice;
    /**分布方式**/
    @TableField(value = "distributionMode")
    private Integer distributionMode;
}
