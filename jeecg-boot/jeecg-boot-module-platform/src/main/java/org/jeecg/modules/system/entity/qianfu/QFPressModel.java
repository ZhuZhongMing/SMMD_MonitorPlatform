package org.jeecg.modules.system.entity.qianfu;

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

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * 杭州三米明德科技有限公司
 * 钱富，物通博联网关 - 西门子plc，液压机 数据
 * @author zzm
 * date 2020-08-28
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="液压机数据", description="液压机数据")
@TableName("qf_press_model")
public class QFPressModel implements Serializable {

    /**编号**/
    @ApiModelProperty(value = "编号")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**设备编号**/
    private String equipmentsn;

    /**系统压力显示值**/
    private String systempressurevalue;
    /**主缸压力显示值**/
    private String mastercylinderPressurevalue;
    /**系统液压压力**/
    private String systemhydraulicpressure;
    /**系统吨位*/
    private String systemtonnage;
    /**主缸公称力**/
    private String mastercylinderNominalforce;
    /**主缸行程**/
    private String mastercylinderStroke;
    /**主缸回程力**/
    private String mastercylinderReturnforce;
    /**主缸加速速度**/
    private String mastercylinderAccelerationspeed;
    /**主缸加压速度**/
    private String mastercylinderCompressionspeed;
    /**主缸回程速度**/
    private String mastercylinderReturntripspeed;
    /**下缸公称力**/
    private String lowercylinderNominalforce;
    /**下缸行程**/
    private String lowercylinderStroke;
    /**下缸顶出速度**/
    private String lowercylinderEjectionspeed;
    /**下缸回程速度**/
    private String lowercylinderReturntripspeed;
    /**退料缸公称力**/
    private String returncylinderNominalforce;
    /**退料缸行程**/
    private String returncylinderStroke;
    /**退料缸顶出速度**/
    private String cylinderEjectionspeed;
    /**退料缸返回速度**/
    private String cylinderReturnspeed;
    /**最大开间**/
    private String maximumBay;
    /**最小开间**/
    private String minimumBay;
    /**工作台左右**/
    private String workbenchLeftright;
    /**工作台前后**/
    private String workbenchUpdown;
    /**主电机KW**/
    private String mainmotorKw;
    /**主电机X台**/
    private String mainmotorX; // MainMotor_X台
    /**副电机KW**/
    private String auxiliarymotorKw;
    /**副电机X台**/
    private String auxiliarymotorX; // AuxiliaryMotor_X台
    /**主油泵**/
    private String mainoilpump;
    /**主油泵X台**/
    private String mainoilpumpX; // MainOilPump_X台
    /**副油泵**/
    private String auxiliaryoilpump;
    /**副油泵X台**/
    private String auxiliaryoilpumpX; // AuxiliaryOilPump_X台
    /***出场日期*/
    private String dateofproduction;
    /**出厂编号**/
    private String factorynumber;
    /**系统传量程**/
    private String systemtransmissionrange;
    /**主缸传量程**/
    private String mastercylindersystemtransmissionrange;
    /**计数器显示**/
    private String counterdisplay;
    /**主回提前停速度**/
    private String maingyrusStopspeed;
    /**主缸位置显示值**/
    private String mastercylinderPositionvalue;

    /**主缸吨位显示值**/
    private String mastercylinderTonnagevalue;
    /**下缸位置显示值**/
    private String lowercylinderPositionvalue;
    /**下缸吨位显示值**/
    private String lowercylinderTonnagevalue;
    /**退料缸吨位显示值**/
    private String returncylinderTonnagevalue;
    /**伺服1压力**/
    private String servo1pressure;
    /**伺服1流量**/
    private String servo1flow;
    /**伺服2压力**/
    private String servo2pressure;
    /**伺服2流量**/
    private String servo2flow;
    /**伺服3压力**/
    private String servo3pressure;
    /**伺服3流量**/
    private String servo3flow;

    /**采集时间**/
    @ApiModelProperty(value = "采集时间")
    /*@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")*/
    @JsonFormat(timezone = "GMT+8", pattern = "HH:mm:ss")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date createTime;

}
