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
 * 湖滨，物通博联网关，报警分组
 * @author zzm
 * date 2020-08-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="报警分组", description="报警分组")
@TableName("hb_alarm_model")
public class HBAlarmModel implements Serializable {
    /**编号**/
    @ApiModelProperty(value = "编号")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**报警_放卷气涨轴未开**/  // （不能运行、缓动，可点动）
    @TableField("Alarm_UnwindingLnflationNotOpen")
    private Integer Alarm_UnwindingLnflationNotOpen;
    /**报警_放卷抬升气缸未开**/ // （不能运行、缓动，可点动）
    @TableField("Alarm_UnwindingLiftingCylinderNotOpen")
    private Integer Alarm_UnwindingLiftingCylinderNotOpen;
    /**报警_接纸台压纸信号1**/
    @TableField("Alarm_PressPaperSignal1")
    private Integer Alarm_PressPaperSignal1;
    /**报警_接纸台压纸信号2**/
    @TableField("Alarm_PressPaperSignal2")
    private Integer Alarm_PressPaperSignal2;
    /**报警_主机低气压报警**/
    @TableField("Alarm_HostLowPressureAlarm")
    private Integer Alarm_HostLowPressureAlarm;
    /**报警_放卷浮动辊断纸停机**/ //（不能运行，可点动、缓动）
    @TableField("Alarm_UnwindingFloatPaperBreakStop")
    private Integer Alarm_UnwindingFloatPaperBreakStop;
    /**报警_放卷尾纸停机**/
    @TableField("Alarm_UnwindingTailPaperStop")
    private Integer Alarm_UnwindingTailPaperStop;
    /**报警_收卷气涨轴未开**/ //（不能运行，可点动、缓动）
    @TableField("Alarm_RollUpLnflationNotOpen")
    private Integer Alarm_RollUpLnflationNotOpen;
    /**报警_收卷抬升气缸未开**/ //（不能运行，可点动、缓动）
    @TableField("Alarm_RollUpLiftingCylinderNotOpen")
    private Integer Alarm_RollUpLiftingCylinderNotOpen;
    /**报警_收卷伺服报警**/
    @TableField("Alarm_RollUpServoAlarm")
    private Integer Alarm_RollUpServoAlarm;
    /**报警_米数计数停机**/
    @TableField("Alaem_MetersCountStop")
    private Integer Alaem_MetersCountStop;
    /**报警_张数计数停机**/
    @TableField("Alarm_ZhangShuCountStop")
    private Integer Alarm_ZhangShuCountStop;
    /**报警_UV系统报警**/
    @TableField("_UVSystemAlarm")
    private Integer _UVSystemAlarm;
    /**报警_UV系统未准备**/
    @TableField("Alarm_UVSystemNotPrepared")
    private Integer Alarm_UVSystemNotPrepared;
    /**报警_卷取马达风扇报警**/
    @TableField("Alarm_CoilMotorFanAlarm")
    private Integer Alarm_CoilMotorFanAlarm;
    /**报警_冷却水冷报警**/
    @TableField("Alarm_CoolingWaterCoolingAlarm")
    private Integer Alarm_CoolingWaterCoolingAlarm;
    /**Alarm_CoronaAlarm**/
    @TableField("Alarm_CoronaAlarm")
    private Integer Alarm_CoronaAlarm;
    /**报警_放卷部停止按钮锁住**/
    @TableField("Alarm_TheUnwindingStopButtonIsLocked")
    private Integer Alarm_TheUnwindingStopButtonIsLocked;
    /**报警_收卷部停止按钮锁住**/
    @TableField("Alarm_TheRewindStopButtonIsSocked")
    private Integer Alarm_TheRewindStopButtonIsSocked;
    /**报警_主面板停止按钮锁住**/
    @TableField("Alarm_TheMainPanelStopButtonIsLocked")
    private Integer Alarm_TheMainPanelStopButtonIsLocked;
    /**报警_紧急停止按下**/
    @TableField("Alarm_PressEmergencyStop")
    private Integer Alarm_PressEmergencyStop;
    /**报警_冷烫放卷尾纸停机**/
    @TableField("Alarm_ColdIroningEndPaperStop")
    private Integer Alarm_ColdIroningEndPaperStop;
    /**报警_给纸牵引辊马达报警**/
    @TableField("Alarm_PaperRollerMotorAlarm")
    private Integer Alarm_PaperRollerMotorAlarm;
    /**报警_第一色压辊马达报警**/
    @TableField("Alarm_FirstColorPressureAlarm")
    private Integer Alarm_FirstColorPressureAlarm;
    /**报警_第一色版辊马达报警**/
    @TableField("Alarm_FirstColorPlateAlarm")
    private Integer Alarm_FirstColorPlateAlarm;
    /**报警_第二色压辊马达报警**/
    @TableField("Alarm_SecondColorPressureAlarm")
    private Integer Alarm_SecondColorPressureAlarm;
    /**报警_第二色版辊马达报警**/
    @TableField("Alarm_SecondColorPlateAlarm")
    private Integer Alarm_SecondColorPlateAlarm;
    /**报警_第三色压辊马达报警**/
    @TableField("Alarm_ThirdColorPressureAlarm")
    private Integer Alarm_ThirdColorPressureAlarm;
    /**报警_第三色版辊马达报警**/
    @TableField("Alarm_ThirdColorPlateAlarm")
    private Integer Alarm_ThirdColorPlateAlarm;
    /**报警_第四色压辊马达报警**/
    @TableField("Alarm_ForthColorPressureAlarm")
    private Integer Alarm_ForthColorPressureAlarm;
    /**报警_第四色版辊马达报警**/
    @TableField("Alarm_ForthColorPlateAlarm")
    private Integer Alarm_ForthColorPlateAlarm;
    /**报警_第五色压辊马达报警**/
    @TableField("Alarm_FifthColorPressureAlarm")
    private Integer Alarm_FifthColorPressureAlarm;
    /**报警_第五色版辊马达报警**/
    @TableField("Alarm_FifthColorPlateAlarm")
    private Integer Alarm_FifthColorPlateAlarm;
    /**报警_第六色压辊马达报警**/
    @TableField("Alarm_SixthColorPressureAlarm")
    private Integer Alarm_SixthColorPressureAlarm;
    /**报警_第六色版辊马达报警**/
    @TableField("Alarm_SixthColorPlateAlarm")
    private Integer Alarm_SixthColorPlateAlarm;
    /**报警_第七色压辊马达报警**/
    @TableField("Alarm_SeventhColorPressureAlarm")
    private Integer Alarm_SeventhColorPressureAlarm;
    /**报警_第七色版辊马达报警**/
    @TableField("Alarm_SeventhColorPlateAlarm")
    private Integer Alarm_SeventhColorPlateAlarm;
    /**报警_第八色压辊马达报警**/
    @TableField("Alarm_EighthColorPressureAlarm")
    private Integer Alarm_EighthColorPressureAlarm;
    /**报警_第八色版辊马达报警**/
    @TableField("Alarm_EighthColorPlateAlarm")
    private Integer Alarm_EighthColorPlateAlarm;
    /**报警_第九色压辊马达报警**/
    @TableField("Alarm_NinthColorPressureAlarm")
    private Integer Alarm_NinthColorPressureAlarm;
    /**报警_第九色版辊马达报警**/
    @TableField("Alarm_NinthColorPlateAlarm")
    private Integer Alarm_NinthColorPlateAlarm;
    /**报警_第十色压辊马达报警**/
    @TableField("Alarm_TenthColorPressureAlarm")
    private Integer Alarm_TenthColorPressureAlarm;
    /**报警_第十色版辊马达报警**/
    @TableField("Alarm_TenthColorPlateAlarm")
    private Integer Alarm_TenthColorPlateAlarm;
    /**报警_第十一色压辊马达报警**/
    @TableField("Alarm_EleventhColorPressureAlarm")
    private Integer Alarm_EleventhColorPressureAlarm;
    /**报警_第十一色版辊马达报警**/
    @TableField("Alarm_EleventhColorPlateAlarm")
    private Integer Alarm_EleventhColorPlateAlarm;
    /**报警_第十二色压辊马达报警**/
    @TableField("Alarm_TwelfthColorPressureAlarm")
    private Integer Alarm_TwelfthColorPressureAlarm;
    /**报警_第十二色版辊马达报警**/
    @TableField("Alarm_TwelfthColorPlateAlarm")
    private Integer Alarm_TwelfthColorPlateAlarm;
    /**报警_排废牵引马达报警1**/
    @TableField("Alarm_ExhaustTractionMotorAlarm1")
    private Integer Alarm_ExhaustTractionMotorAlarm1;
    /**报警_模切前牵引辊马达报警**/
    @TableField("Alarm_RollerMotorAlarmBefore")
    private Integer Alarm_RollerMotorAlarmBefore;
    /**报警_第一模切马达报警**/
    @TableField("Alarm_FirstMotorAlarm")
    private Integer Alarm_FirstMotorAlarm;
    /**报警_排废牵引马达报警2**/
    @TableField("Alarm_ExhaustTractionMotorAlarm2")
    private Integer Alarm_ExhaustTractionMotorAlarm2;
    /**报警_第三模切马达报警**/
    @TableField("Alarm_ThirdMotorAlarm")
    private Integer Alarm_ThirdMotorAlarm;
    /**报警_收纸牵引辊马达报警**/
    @TableField("Alarm_PaperReceivingTractionRollerMotorAlarm")
    private Integer Alarm_PaperReceivingTractionRollerMotorAlarm;
    /**报警_膜内贴吸风机报警**/
    @TableField("Alarm_FilmAttachedAuctionFanAlarm")
    private Integer Alarm_FilmAttachedAuctionFanAlarm;
    /**报警_膜内贴马达报警**/
    @TableField("Alarm_FilmMountedMotorAlarm")
    private Integer Alarm_FilmMountedMotorAlarm;
    /**报警_收卷马达未开**/
    @TableField("Alarm_RollUpMotorNotOpen")
    private Integer Alarm_RollUpMotorNotOpen;

    /**采集时间**/
    @ApiModelProperty(value = "采集时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss:SSS")
    private Date create_time;

    /**报警——UV系统报警**/
    @TableField("Alarm_UVSystemAlarm")
    private Integer Alarm_UVSystemAlarm;

}
