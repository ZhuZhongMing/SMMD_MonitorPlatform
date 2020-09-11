package org.jeecg.modules.listener.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.system.entity.hubin.*;
import org.jeecg.modules.system.entity.qianfu.WTBLMessageVO;
import org.jeecg.modules.system.entity.qianfu.QFPressModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 杭州三米明德科技有限公司
 * 湖滨，物通博联网关 - 数据
 *
 * @author zzm
 * date 2020-09-11
 */
@Slf4j
public class HBMessageDispose {


    public static HBMenuModel MessageToHBMenuModel(JSONArray ar) {

        HBMenuModel model = new HBMenuModel();
        model.setMenu_ToothNumber(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(0), WTBLMessageVO.class).getVarValue()));
        model.setMenu_TailPaper(JSONObject.toJavaObject((JSON) ar.get(1), WTBLMessageVO.class).getVarValue());
        model.setMenu_Unwinding(JSONObject.toJavaObject((JSON) ar.get(2), WTBLMessageVO.class).getVarValue());
        model.setMenu_CurrentValue(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(3), WTBLMessageVO.class).getVarValue()));
        model.setMenu_TotalMeters(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(4), WTBLMessageVO.class).getVarValue()));
        model.setMenu_Setting(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(5), WTBLMessageVO.class).getVarValue()));
        try {
            WTBLMessageVO vo = JSONObject.toJavaObject((JSON) ar.get(0), WTBLMessageVO.class);
            model.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getReadTime()));
        } catch (ParseException e) {
            model.setCreate_time(new Date());
            log.error("湖滨消息中采集时间异常！已取用系统当前时间，异常信息:" + e);
            e.printStackTrace();
        }
        return model;
    }

    public static HBTowModel MessageToTowModel(JSONArray ar) {

        HBTowModel model = new HBTowModel();
        model.setTow_Tension1_1(JSONObject.toJavaObject((JSON) ar.get(0), WTBLMessageVO.class).getVarValue());
        model.setTow_Tension2_1(JSONObject.toJavaObject((JSON) ar.get(1), WTBLMessageVO.class).getVarValue());
        model.setTow_Tension3_1(JSONObject.toJavaObject((JSON) ar.get(2), WTBLMessageVO.class).getVarValue());
        model.setTow_Tension1_2(JSONObject.toJavaObject((JSON) ar.get(3), WTBLMessageVO.class).getVarValue());
        model.setTow_Tension2_2(JSONObject.toJavaObject((JSON) ar.get(4), WTBLMessageVO.class).getVarValue());
        model.setTow_Tension3_2(JSONObject.toJavaObject((JSON) ar.get(5), WTBLMessageVO.class).getVarValue());
        model.setTow_TractionRate1(JSONObject.toJavaObject((JSON) ar.get(6), WTBLMessageVO.class).getVarValue());
        model.setTow_TractionRate2(JSONObject.toJavaObject((JSON) ar.get(7), WTBLMessageVO.class).getVarValue());
        model.setTow_TractionRate3(JSONObject.toJavaObject((JSON) ar.get(8), WTBLMessageVO.class).getVarValue());
        model.setRoll_Setting1(JSONObject.toJavaObject((JSON) ar.get(9), WTBLMessageVO.class).getVarValue());
        model.setRoll_Setting2(JSONObject.toJavaObject((JSON) ar.get(10), WTBLMessageVO.class).getVarValue());
        model.setRoll_Setting3(JSONObject.toJavaObject((JSON) ar.get(11), WTBLMessageVO.class).getVarValue());
        model.setRoll_CurrentValue1(JSONObject.toJavaObject((JSON) ar.get(11), WTBLMessageVO.class).getVarValue());
        model.setRoll_CurrentValue2(JSONObject.toJavaObject((JSON) ar.get(12), WTBLMessageVO.class).getVarValue());
        model.setRoll_CurrentValue3(JSONObject.toJavaObject((JSON) ar.get(13), WTBLMessageVO.class).getVarValue());
        model.setRoll_SlowDown1(JSONObject.toJavaObject((JSON) ar.get(14), WTBLMessageVO.class).getVarValue());
        model.setMenu_TowPattern1(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(15), WTBLMessageVO.class).getVarValue()));
        model.setTow_Pattern2(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(16), WTBLMessageVO.class).getVarValue()));
        model.setTow_Pattern3(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(17), WTBLMessageVO.class).getVarValue()));
        model.setMenu_PE(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(18), WTBLMessageVO.class).getVarValue()));
        model.setMenu_ArtPaper(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(19), WTBLMessageVO.class).getVarValue()));
        model.setMenu_Membrane(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(20), WTBLMessageVO.class).getVarValue()));
        try {
            WTBLMessageVO vo = JSONObject.toJavaObject((JSON) ar.get(0), WTBLMessageVO.class);
            model.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getReadTime()));
        } catch (ParseException e) {
            model.setCreate_time(new Date());
            log.error("湖滨消息中采集时间异常！已取用系统当前时间，异常信息:" + e);
            e.printStackTrace();
        }
        return model;
    }

    public static HBFunctionModel MessageToHBFunctionModel(JSONArray ar) {

        HBFunctionModel model = new HBFunctionModel();
        model.setFunction_ReverseValue(JSONObject.toJavaObject((JSON) ar.get(0), WTBLMessageVO.class).getVarValue());
        model.setFunction_UnwindingTailPaper(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(1), WTBLMessageVO.class).getVarValue()));
        model.setFunction_CoronaAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(2), WTBLMessageVO.class).getVarValue()));
        model.setFunction_UASystem(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(3), WTBLMessageVO.class).getVarValue()));
        model.setFunction_Reversal(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(4), WTBLMessageVO.class).getVarValue()));
        model.setFunction_UnwindingTractionTension(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(5), WTBLMessageVO.class).getVarValue()));
        model.setFunction_MiddleTowPaperBreak(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(6), WTBLMessageVO.class).getVarValue()));
        model.setFunction_PaperOutTowPaperBreak(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(7), WTBLMessageVO.class).getVarValue()));
        model.setFunction_ColdWave1Paper(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(8), WTBLMessageVO.class).getVarValue()));
        model.setFunction_ColdWave2Paper(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(9), WTBLMessageVO.class).getVarValue()));
        model.setFunction_CoronaFan_SingleAction(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(10), WTBLMessageVO.class).getVarValue()));
        model.setFunction_CoronaFan_ManyAction(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(11), WTBLMessageVO.class).getVarValue()));
        model.setFunction_CoronaFan_NotUsed(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(12), WTBLMessageVO.class).getVarValue()));
        try {
            WTBLMessageVO vo = JSONObject.toJavaObject((JSON) ar.get(0), WTBLMessageVO.class);
            model.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getReadTime()));
        } catch (ParseException e) {
            model.setCreate_time(new Date());
            log.error("湖滨消息中采集时间异常！已取用系统当前时间，异常信息:" + e);
            e.printStackTrace();
        }
        return model;
    }

    public static HBCorrenctModel MessageToHBCorrenctModel(JSONArray ar) {

        HBCorrenctModel model = new HBCorrenctModel();
        model.setCorrect_1Color(JSONObject.toJavaObject((JSON) ar.get(0), WTBLMessageVO.class).getVarValue());
        model.setCorrect_2Color(JSONObject.toJavaObject((JSON) ar.get(1), WTBLMessageVO.class).getVarValue());
        model.setCorrect_3Color(JSONObject.toJavaObject((JSON) ar.get(2), WTBLMessageVO.class).getVarValue());
        model.setCorrect_4Color(JSONObject.toJavaObject((JSON) ar.get(3), WTBLMessageVO.class).getVarValue());
        model.setCorrect_5Color(JSONObject.toJavaObject((JSON) ar.get(4), WTBLMessageVO.class).getVarValue());
        model.setCorrect_6Color(JSONObject.toJavaObject((JSON) ar.get(5), WTBLMessageVO.class).getVarValue());
        model.setCorrect_7Color(JSONObject.toJavaObject((JSON) ar.get(6), WTBLMessageVO.class).getVarValue());
        model.setCorrect_8Color(JSONObject.toJavaObject((JSON) ar.get(7), WTBLMessageVO.class).getVarValue());
        model.setCorrect_9Color(JSONObject.toJavaObject((JSON) ar.get(8), WTBLMessageVO.class).getVarValue());
        model.setCorrect_10Color(JSONObject.toJavaObject((JSON) ar.get(9), WTBLMessageVO.class).getVarValue());
        model.setTracingAngle_1Color(JSONObject.toJavaObject((JSON) ar.get(10), WTBLMessageVO.class).getVarValue());
        model.setTracingAngle_2Color(JSONObject.toJavaObject((JSON) ar.get(11), WTBLMessageVO.class).getVarValue());
        model.setTracingAngle_3Color(JSONObject.toJavaObject((JSON) ar.get(12), WTBLMessageVO.class).getVarValue());
        model.setTracingAngle_4Color(JSONObject.toJavaObject((JSON) ar.get(13), WTBLMessageVO.class).getVarValue());
        model.setTracingAngle_5Color(JSONObject.toJavaObject((JSON) ar.get(14), WTBLMessageVO.class).getVarValue());
        model.setTracingAngle_6Color(JSONObject.toJavaObject((JSON) ar.get(15), WTBLMessageVO.class).getVarValue());
        model.setTracingAngle_7Color(JSONObject.toJavaObject((JSON) ar.get(16), WTBLMessageVO.class).getVarValue());
        model.setTracingAngle_8Color(JSONObject.toJavaObject((JSON) ar.get(17), WTBLMessageVO.class).getVarValue());
        model.setTracingAngle_9Color(JSONObject.toJavaObject((JSON) ar.get(18), WTBLMessageVO.class).getVarValue());
        model.setTracingAngle_10Color(JSONObject.toJavaObject((JSON) ar.get(19), WTBLMessageVO.class).getVarValue());
        model.setTracingAngle_Cutter(JSONObject.toJavaObject((JSON) ar.get(20), WTBLMessageVO.class).getVarValue());
        model.setMarkAdjustment_1Color(JSONObject.toJavaObject((JSON) ar.get(21), WTBLMessageVO.class).getVarValue());
        model.setMarkAdjustment_2Color(JSONObject.toJavaObject((JSON) ar.get(22), WTBLMessageVO.class).getVarValue());
        model.setMarkAdjustment_3Color(JSONObject.toJavaObject((JSON) ar.get(23), WTBLMessageVO.class).getVarValue());
        model.setMarkAdjustment_4Color(JSONObject.toJavaObject((JSON) ar.get(24), WTBLMessageVO.class).getVarValue());
        model.setMarkAdjustment_5Color(JSONObject.toJavaObject((JSON) ar.get(25), WTBLMessageVO.class).getVarValue());
        model.setMarkAdjustment_6Color(JSONObject.toJavaObject((JSON) ar.get(26), WTBLMessageVO.class).getVarValue());
        model.setMarkAdjustment_7Color(JSONObject.toJavaObject((JSON) ar.get(27), WTBLMessageVO.class).getVarValue());
        model.setMarkAdjustment_8Color(JSONObject.toJavaObject((JSON) ar.get(28), WTBLMessageVO.class).getVarValue());
        model.setMarkAdjustment_9Color(JSONObject.toJavaObject((JSON) ar.get(29), WTBLMessageVO.class).getVarValue());
        model.setMarkAdjustment_10Color(JSONObject.toJavaObject((JSON) ar.get(30), WTBLMessageVO.class).getVarValue());
        model.setMarkAdjustment_Cutter(JSONObject.toJavaObject((JSON) ar.get(31), WTBLMessageVO.class).getVarValue());
        model.setTemperature_1Color(JSONObject.toJavaObject((JSON) ar.get(32), WTBLMessageVO.class).getVarValue());
        model.setTemperature_2Color(JSONObject.toJavaObject((JSON) ar.get(33), WTBLMessageVO.class).getVarValue());
        model.setTemperature_3Color(JSONObject.toJavaObject((JSON) ar.get(34), WTBLMessageVO.class).getVarValue());
        model.setTemperature_4Color(JSONObject.toJavaObject((JSON) ar.get(35), WTBLMessageVO.class).getVarValue());
        model.setTemperature_5Color(JSONObject.toJavaObject((JSON) ar.get(36), WTBLMessageVO.class).getVarValue());
        model.setTemperature_6Color(JSONObject.toJavaObject((JSON) ar.get(37), WTBLMessageVO.class).getVarValue());
        model.setTemperature_7Color(JSONObject.toJavaObject((JSON) ar.get(38), WTBLMessageVO.class).getVarValue());
        model.setTemperature_8Color(JSONObject.toJavaObject((JSON) ar.get(39), WTBLMessageVO.class).getVarValue());
        model.setTemperature_9Color(JSONObject.toJavaObject((JSON) ar.get(40), WTBLMessageVO.class).getVarValue());
        model.setTemperature_10Color(JSONObject.toJavaObject((JSON) ar.get(41), WTBLMessageVO.class).getVarValue());
        model.setActualAngle_1Color(JSONObject.toJavaObject((JSON) ar.get(42), WTBLMessageVO.class).getVarValue());
        model.setActualAngle_2Color(JSONObject.toJavaObject((JSON) ar.get(43), WTBLMessageVO.class).getVarValue());
        model.setActualAngle_3Color(JSONObject.toJavaObject((JSON) ar.get(44), WTBLMessageVO.class).getVarValue());
        model.setActualAngle_4Color(JSONObject.toJavaObject((JSON) ar.get(45), WTBLMessageVO.class).getVarValue());
        model.setActualAngle_5Color(JSONObject.toJavaObject((JSON) ar.get(46), WTBLMessageVO.class).getVarValue());
        model.setActualAngle_6Color(JSONObject.toJavaObject((JSON) ar.get(47), WTBLMessageVO.class).getVarValue());
        model.setActualAngle_7Color(JSONObject.toJavaObject((JSON) ar.get(48), WTBLMessageVO.class).getVarValue());
        model.setActualAngle_8Color(JSONObject.toJavaObject((JSON) ar.get(49), WTBLMessageVO.class).getVarValue());
        model.setActualAngle_9Color(JSONObject.toJavaObject((JSON) ar.get(50), WTBLMessageVO.class).getVarValue());
        model.setActualAngle_10Color(JSONObject.toJavaObject((JSON) ar.get(51), WTBLMessageVO.class).getVarValue());
        model.setActualAngle_Cutter(JSONObject.toJavaObject((JSON) ar.get(52), WTBLMessageVO.class).getVarValue());
        model.setPressureCylinder_1Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(53), WTBLMessageVO.class).getVarValue()));
        model.setCylinder_1Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(54), WTBLMessageVO.class).getVarValue()));
        model.setPressureCylinder_2Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(55), WTBLMessageVO.class).getVarValue()));
        model.setCylinder_2Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(56), WTBLMessageVO.class).getVarValue()));
        model.setPressureCylinder_3Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(57), WTBLMessageVO.class).getVarValue()));
        model.setCylinder_3Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(58), WTBLMessageVO.class).getVarValue()));
        model.setPressureCylinder_4Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(59), WTBLMessageVO.class).getVarValue()));
        model.setCylinder_4Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(60), WTBLMessageVO.class).getVarValue()));
        model.setPressureCylinder_5Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(61), WTBLMessageVO.class).getVarValue()));
        model.setCylinder_5Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(62), WTBLMessageVO.class).getVarValue()));
        model.setPressureCylinder_6Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(63), WTBLMessageVO.class).getVarValue()));
        model.setCylinder_6Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(64), WTBLMessageVO.class).getVarValue()));
        model.setPressureCylinder_7Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(65), WTBLMessageVO.class).getVarValue()));
        model.setCylinder_7Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(66), WTBLMessageVO.class).getVarValue()));
        model.setPressureCylinder_8Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(67), WTBLMessageVO.class).getVarValue()));
        model.setCylinder_8Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(68), WTBLMessageVO.class).getVarValue()));
        model.setPressureCylinder_9Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(69), WTBLMessageVO.class).getVarValue()));
        model.setCylinder_9Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(70), WTBLMessageVO.class).getVarValue()));
        model.setPressureCylinder_10Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(71), WTBLMessageVO.class).getVarValue()));
        model.setCylinder_10Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(72), WTBLMessageVO.class).getVarValue()));
        model.setCylinder_Cutter(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(73), WTBLMessageVO.class).getVarValue()));
        model.setBidChasing_1Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(74), WTBLMessageVO.class).getVarValue()));
        model.setBidChasing_2Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(75), WTBLMessageVO.class).getVarValue()));
        model.setBidChasing_3Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(76), WTBLMessageVO.class).getVarValue()));
        model.setBidChasing_4Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(77), WTBLMessageVO.class).getVarValue()));
        model.setBidChasing_5Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(78), WTBLMessageVO.class).getVarValue()));
        model.setBidChasing_6Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(79), WTBLMessageVO.class).getVarValue()));
        model.setBidChasing_7Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(80), WTBLMessageVO.class).getVarValue()));
        model.setBidChasing_8Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(81), WTBLMessageVO.class).getVarValue()));
        model.setBidChasing_9Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(82), WTBLMessageVO.class).getVarValue()));
        model.setBidChasing_10Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(83), WTBLMessageVO.class).getVarValue()));
        model.setBidChasing_Cutter(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(84), WTBLMessageVO.class).getVarValue()));
        model.setColorCode_1Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(85), WTBLMessageVO.class).getVarValue()));
        model.setColorCode_2Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(86), WTBLMessageVO.class).getVarValue()));
        model.setColorCode_3Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(87), WTBLMessageVO.class).getVarValue()));
        model.setColorCode_4Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(88), WTBLMessageVO.class).getVarValue()));
        model.setColorCode_5Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(89), WTBLMessageVO.class).getVarValue()));
        model.setColorCode_6Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(90), WTBLMessageVO.class).getVarValue()));
        model.setColorCode_7Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(91), WTBLMessageVO.class).getVarValue()));
        model.setColorCode_8Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(92), WTBLMessageVO.class).getVarValue()));
        model.setColorCode_9Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(93), WTBLMessageVO.class).getVarValue()));
        model.setColorCode_10Color(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(94), WTBLMessageVO.class).getVarValue()));
        model.setColorCode_Cutter(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(95), WTBLMessageVO.class).getVarValue()));

        try {
            WTBLMessageVO vo = JSONObject.toJavaObject((JSON) ar.get(0), WTBLMessageVO.class);
            model.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getReadTime()));
        } catch (ParseException e) {
            model.setCreate_time(new Date());
            log.error("湖滨消息中采集时间异常！已取用系统当前时间，异常信息:" + e);
            e.printStackTrace();
        }
        return model;
    }

    public static HBTaskModel MessageToHBTaskModel(JSONArray ar) {

        HBTaskModel model = new HBTaskModel();
        model.setTask_MeterNumber_CuttentValue(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(0), WTBLMessageVO.class).getVarValue()));
        model.setTask_ZhangNumber_CuttentValue1(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(1), WTBLMessageVO.class).getVarValue()));
        model.setTask_MeterNumber_TotalNumber(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(2), WTBLMessageVO.class).getVarValue()));
        model.setTask_ZhangNumber_TotalNumber1(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(3), WTBLMessageVO.class).getVarValue()));
        model.setTask_MeterNumber_Setting(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(4), WTBLMessageVO.class).getVarValue()));
        model.setTask_ZhangNumber_Setting1(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(5), WTBLMessageVO.class).getVarValue()));
        model.setTask_ZhangNumber_Setting2(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(6), WTBLMessageVO.class).getVarValue()));
        model.setTask_ZhangNumber_CuttentValue2(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(7), WTBLMessageVO.class).getVarValue()));
        model.setTask_ZhangNumber_TotalNumber2(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(8), WTBLMessageVO.class).getVarValue()));
        model.setTask_TheElectricityTime(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(9), WTBLMessageVO.class).getVarValue()));
        model.setTask_RunningTime(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(10), WTBLMessageVO.class).getVarValue()));
        model.setTask_PrintingTime(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(11), WTBLMessageVO.class).getVarValue()));
        model.setTask_TotalMileage(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(12), WTBLMessageVO.class).getVarValue()));
        model.setTask_TheAverageVelocity(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(13), WTBLMessageVO.class).getVarValue()));

        try {
            WTBLMessageVO vo = JSONObject.toJavaObject((JSON) ar.get(0), WTBLMessageVO.class);
            model.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getReadTime()));
        } catch (ParseException e) {
            model.setCreate_time(new Date());
            log.error("湖滨消息中采集时间异常！已取用系统当前时间，异常信息:" + e);
            e.printStackTrace();
        }
        return model;
    }

    public static HBAlarmModel MessageToHBAlarmModel(JSONArray ar) {

        HBAlarmModel model = new HBAlarmModel();
        model.setAlarm_UnwindingLnflationNotOpen(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(0), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_UnwindingLiftingCylinderNotOpen(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(1), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_PressPaperSignal1(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(2), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_PressPaperSignal2(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(3), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_HostLowPressureAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(4), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_UnwindingFloatPaperBreakStop(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(5), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_UnwindingTailPaperStop(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(6), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_RollUpLnflationNotOpen(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(7), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_RollUpLiftingCylinderNotOpen(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(8), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_RollUpServoAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(9), WTBLMessageVO.class).getVarValue()));
        model.setAlaem_MetersCountStop(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(10), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_ZhangShuCountStop(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(11), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_UVSystemAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(12), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_UVSystemNotPrepared(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(13), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_CoilMotorFanAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(14), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_CoolingWaterCoolingAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(15), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_CoronaAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(16), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_TheUnwindingStopButtonIsLocked(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(17), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_TheRewindStopButtonIsSocked(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(18), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_TheMainPanelStopButtonIsLocked(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(19), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_PressEmergencyStop(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(20), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_ColdIroningEndPaperStop(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(21), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_PaperRollerMotorAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(22), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_FirstColorPressureAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(23), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_FirstColorPlateAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(24), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_SecondColorPressureAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(25), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_SecondColorPlateAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(26), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_ThirdColorPressureAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(27), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_ThirdColorPlateAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(28), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_ForthColorPressureAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(29), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_ForthColorPlateAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(30), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_FifthColorPressureAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(31), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_FifthColorPlateAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(32), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_SixthColorPressureAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(33), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_SixthColorPlateAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(34), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_SeventhColorPressureAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(35), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_SeventhColorPlateAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(36), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_EighthColorPressureAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(37), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_EighthColorPlateAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(38), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_NinthColorPressureAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(39), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_NinthColorPlateAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(40), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_TenthColorPressureAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(41), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_TenthColorPlateAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(42), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_EleventhColorPressureAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(43), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_EleventhColorPlateAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(44), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_TwelfthColorPressureAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(45), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_TwelfthColorPlateAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(46), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_ExhaustTractionMotorAlarm1(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(47), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_RollerMotorAlarmBefore(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(48), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_FirstMotorAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(49), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_ExhaustTractionMotorAlarm2(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(50), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_ThirdMotorAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(51), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_PaperReceivingTractionRollerMotorAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(52), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_FilmAttachedAuctionFanAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(53), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_FilmMountedMotorAlarm(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(54), WTBLMessageVO.class).getVarValue()));
        model.setAlarm_RollUpMotorNotOpen(Integer.parseInt(JSONObject.toJavaObject((JSON) ar.get(55), WTBLMessageVO.class).getVarValue()));

        try {
            WTBLMessageVO vo = JSONObject.toJavaObject((JSON) ar.get(0), WTBLMessageVO.class);
            model.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getReadTime()));
        } catch (ParseException e) {
            model.setCreate_time(new Date());
            log.error("湖滨消息中采集时间异常！已取用系统当前时间，异常信息:" + e);
            e.printStackTrace();
        }
        return model;
    }

}
