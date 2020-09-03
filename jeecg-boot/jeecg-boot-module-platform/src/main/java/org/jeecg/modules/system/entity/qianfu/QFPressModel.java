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
    @TableField("equipmentsn")
    private String equipmentsn;


    /**系统压力显示值**/
    @TableField("SystemPressureValue")
    private String SystemPressureValue;
    /**主缸压力显示值**/
    @TableField("MasterCylinder_PressureValue")
    private String MasterCylinder_PressureValue;
    /**系统液压压力**/
    @TableField("SystemHydraulicPressure")
    private String SystemHydraulicPressure;
    /**系统吨位*/
    @TableField("SystemTonnage")
    private String SystemTonnage;
    /**主缸公称力**/
    @TableField("MasterCylinder_NominalForce")
    private String MasterCylinder_NominalForce;
    /**主缸行程**/
    @TableField("MasterCylinder_Stroke")
    private String MasterCylinder_Stroke;
    /**主缸回程力**/
    @TableField("MasterCylinder_ReturnForce")
    private String MasterCylinder_ReturnForce;
    /**主缸加速速度**/
    @TableField("MasterCylinder_AccelerationSpeed")
    private String MasterCylinder_AccelerationSpeed;
    /**主缸加压速度**/
    @TableField("MasterCylinder_CompressionSpeed")
    private String MasterCylinder_CompressionSpeed;
    /**主缸回程速度**/
    @TableField("MasterCylinder_ReturnTripSpeed")
    private String MasterCylinder_ReturnTripSpeed;
    /**下缸公称力**/
    @TableField("LowerCylinder_NominalForce")
    private String LowerCylinder_NominalForce;
    /**下缸行程**/
    @TableField("LowerCylinder_Stroke")
    private String LowerCylinder_Stroke;
    /**下缸顶出速度**/
    @TableField("LowerCylinder_EjectionSpeed")
    private String LowerCylinder_EjectionSpeed;
    /**下缸回程速度**/
    @TableField("LowerCylinder_ReturnTripSpeed")
    private String LowerCylinder_ReturnTripSpeed;
    /**退料缸公称力**/
    @TableField("ReturnCylinder_NominalForce")
    private String ReturnCylinder_NominalForce;
    /**退料缸行程**/
    @TableField("ReturnCylinder_Stroke")
    private String ReturnCylinder_Stroke;
    /**退料缸顶出速度**/
    @TableField("Cylinder_EjectionSpeed")
    private String Cylinder_EjectionSpeed;
    /**退料缸返回速度**/
    @TableField("Cylinder_ReturnSpeed")
    private String Cylinder_ReturnSpeed;
    /**最大开间**/
    @TableField("Maximum_Bay")
    private String Maximum_Bay;
    /**最小开间**/
    @TableField("Minimum_Bay")
    private String Minimum_Bay;
    /**工作台左右**/
    @TableField("Workbench_LeftRight")
    private String Workbench_LeftRight;
    /**工作台前后**/
    @TableField("Workbench_UpDown")
    private String Workbench_UpDown;
    /**主电机KW**/
    @TableField("MainMotor_KW")
    private String MainMotor_KW;
    /**主电机X台**/
    @TableField("MainMotor_X")
    private String MainMotor_X; // MainMotor_X台
    /**副电机KW**/
    @TableField("AuxiliaryMotor_KW")
    private String AuxiliaryMotor_KW;
    /**副电机X台**/
    @TableField("AuxiliaryMotor_X")
    private String AuxiliaryMotor_X; // AuxiliaryMotor_X台
    /**主油泵**/
    @TableField("MainOilPump")
    private String MainOilPump;
    /**主油泵X台**/
    @TableField("MainOilPump_X")
    private String MainOilPump_X; // MainOilPump_X台
    /**副油泵**/
    @TableField("AuxiliaryOilPump")
    private String AuxiliaryOilPump;
    /**副油泵X台**/
    @TableField("AuxiliaryOilPump_X")
    private String AuxiliaryOilPump_X; // AuxiliaryOilPump_X台
    /***出场日期*/
    @TableField("DateofProduction")
    private String DateofProduction;
    /**出厂编号**/
    @TableField("FactoryNumber")
    private String FactoryNumber;
    /**系统传量程**/
    @TableField("SystemTransmissionRange")
    private String SystemTransmissionRange;
    /**主缸传量程**/
    @TableField("MasterCylinderSystemTransmissionRange")
    private String MasterCylinderSystemTransmissionRange;
    /**计数器显示**/
    @TableField("CounterDisplay")
    private String CounterDisplay;
    /**主回提前停速度**/
    @TableField("MainGyrus_StopSpeed")
    private String MainGyrus_StopSpeed;
    /**主缸位置显示值**/
    @TableField("MasterCylinder_PositionValue")
    private String MasterCylinder_PositionValue;





    /**主缸吨位显示值**/
    @TableField("MasterCylinder_TonnageValue")
    private String MasterCylinder_TonnageValue;
    /**下缸位置显示值**/
    @TableField("LowerCylinder_PositionValue")
    private String LowerCylinder_PositionValue;
    /**下缸吨位显示值**/
    @TableField("LowerCylinder_TonnageValue")
    private String LowerCylinder_TonnageValue;
    /**退料缸吨位显示值**/
    @TableField("ReturnCylinder_TonnageValue")
    private String ReturnCylinder_TonnageValue;
    /**伺服1压力**/
    @TableField("Servo1Pressure")
    private String Servo1Pressure;
    /**伺服1流量**/
    @TableField("Servo1Flow")
    private String Servo1Flow;
    /**伺服2压力**/
    @TableField("Servo2Pressure")
    private String Servo2Pressure;
    /**伺服2流量**/
    @TableField("Servo2Flow")
    private String Servo2Flow;
    /**伺服3压力**/
    @TableField("Servo3Pressure")
    private String Servo3Pressure;
    /**伺服3流量**/
    @TableField("Servo3Flow")
    private String Servo3Flow;

    /**采集时间**/
    @ApiModelProperty(value = "采集时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date create_time;

}
