package org.jeecg.modules.listener.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.jeecg.modules.system.entity.CNCModel;
import org.jeecg.modules.system.entity.qianfu.QFMessageVO;
import org.jeecg.modules.system.entity.qianfu.QFPressModel;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 杭州三米明德科技有限公司
 *  钱富，物通博联网关 - 西门子plc，液压机 数据
 * @author zzm
 * date 2020-08-24
 */
@Slf4j
public class QFMessageDispose {

    public static QFPressModel QFMessageDispose(JSONArray ar){
        QFPressModel model = new QFPressModel();
        model.setSystemPressureValue(JSONObject.toJavaObject((JSON) ar.get(0), QFMessageVO.class).getVarValue());
        model.setMasterCylinder_PressureValue(JSONObject.toJavaObject((JSON) ar.get(1), QFMessageVO.class).getVarValue());

        model.setSystemHydraulicPressure(JSONObject.toJavaObject((JSON) ar.get(2), QFMessageVO.class).getVarValue());
        model.setSystemTonnage(JSONObject.toJavaObject((JSON) ar.get(3), QFMessageVO.class).getVarValue());
        model.setMasterCylinder_NominalForce(JSONObject.toJavaObject((JSON) ar.get(4), QFMessageVO.class).getVarValue());
        model.setMasterCylinder_Stroke(JSONObject.toJavaObject((JSON) ar.get(5), QFMessageVO.class).getVarValue());
        model.setMasterCylinder_ReturnForce(JSONObject.toJavaObject((JSON) ar.get(6), QFMessageVO.class).getVarValue());
        model.setMasterCylinder_AccelerationSpeed(JSONObject.toJavaObject((JSON) ar.get(7), QFMessageVO.class).getVarValue());
        model.setMasterCylinder_CompressionSpeed(JSONObject.toJavaObject((JSON) ar.get(8), QFMessageVO.class).getVarValue());
        model.setMasterCylinder_ReturnTripSpeed(JSONObject.toJavaObject((JSON) ar.get(9), QFMessageVO.class).getVarValue());
        model.setLowerCylinder_NominalForce(JSONObject.toJavaObject((JSON) ar.get(10), QFMessageVO.class).getVarValue());
        model.setLowerCylinder_Stroke(JSONObject.toJavaObject((JSON) ar.get(11), QFMessageVO.class).getVarValue());
        model.setLowerCylinder_EjectionSpeed(JSONObject.toJavaObject((JSON) ar.get(12), QFMessageVO.class).getVarValue());
        model.setLowerCylinder_ReturnTripSpeed(JSONObject.toJavaObject((JSON) ar.get(13), QFMessageVO.class).getVarValue());
        model.setReturnCylinder_NominalForce(JSONObject.toJavaObject((JSON) ar.get(14), QFMessageVO.class).getVarValue());
        model.setReturnCylinder_Stroke(JSONObject.toJavaObject((JSON) ar.get(15), QFMessageVO.class).getVarValue());
        model.setCylinder_EjectionSpeed(JSONObject.toJavaObject((JSON) ar.get(16), QFMessageVO.class).getVarValue());
        model.setCylinder_ReturnSpeed(JSONObject.toJavaObject((JSON) ar.get(17), QFMessageVO.class).getVarValue());
        model.setMaximum_Bay(JSONObject.toJavaObject((JSON) ar.get(18), QFMessageVO.class).getVarValue());
        model.setMinimum_Bay(JSONObject.toJavaObject((JSON) ar.get(19), QFMessageVO.class).getVarValue());
        model.setWorkbench_LeftRight(JSONObject.toJavaObject((JSON) ar.get(20), QFMessageVO.class).getVarValue());
        model.setWorkbench_UpDown(JSONObject.toJavaObject((JSON) ar.get(21), QFMessageVO.class).getVarValue());
        model.setMainMotor_KW(JSONObject.toJavaObject((JSON) ar.get(22), QFMessageVO.class).getVarValue());
        model.setMainMotor_X(JSONObject.toJavaObject((JSON) ar.get(23), QFMessageVO.class).getVarValue());
        model.setAuxiliaryMotor_KW(JSONObject.toJavaObject((JSON) ar.get(24), QFMessageVO.class).getVarValue());
        model.setAuxiliaryMotor_X(JSONObject.toJavaObject((JSON) ar.get(25), QFMessageVO.class).getVarValue());
        model.setMainOilPump(JSONObject.toJavaObject((JSON) ar.get(26), QFMessageVO.class).getVarValue());
        model.setMainOilPump_X(JSONObject.toJavaObject((JSON) ar.get(27), QFMessageVO.class).getVarValue());
        model.setAuxiliaryOilPump(JSONObject.toJavaObject((JSON) ar.get(28), QFMessageVO.class).getVarValue());
        model.setAuxiliaryOilPump_X(JSONObject.toJavaObject((JSON) ar.get(29), QFMessageVO.class).getVarValue());
        model.setDateofProduction(JSONObject.toJavaObject((JSON) ar.get(30), QFMessageVO.class).getVarValue());
        model.setFactoryNumber(JSONObject.toJavaObject((JSON) ar.get(31), QFMessageVO.class).getVarValue());




        model.setSystemTransmissionRange(JSONObject.toJavaObject((JSON) ar.get(32), QFMessageVO.class).getVarValue());
        model.setMasterCylinderSystemTransmissionRange(JSONObject.toJavaObject((JSON) ar.get(33), QFMessageVO.class).getVarValue());
        model.setCounterDisplay(JSONObject.toJavaObject((JSON) ar.get(34), QFMessageVO.class).getVarValue());
        model.setMainGyrus_StopSpeed(JSONObject.toJavaObject((JSON) ar.get(35), QFMessageVO.class).getVarValue());
        model.setMasterCylinder_PositionValue(JSONObject.toJavaObject((JSON) ar.get(36), QFMessageVO.class).getVarValue());
        model.setMasterCylinder_TonnageValue(JSONObject.toJavaObject((JSON) ar.get(37), QFMessageVO.class).getVarValue());
        model.setLowerCylinder_PositionValue(JSONObject.toJavaObject((JSON) ar.get(38), QFMessageVO.class).getVarValue());
        model.setLowerCylinder_TonnageValue(JSONObject.toJavaObject((JSON) ar.get(39), QFMessageVO.class).getVarValue());
        model.setReturnCylinder_TonnageValue(JSONObject.toJavaObject((JSON) ar.get(40), QFMessageVO.class).getVarValue());
        model.setServo1Pressure(JSONObject.toJavaObject((JSON) ar.get(41), QFMessageVO.class).getVarValue());
        model.setServo1Flow(JSONObject.toJavaObject((JSON) ar.get(42), QFMessageVO.class).getVarValue());
        model.setServo2Pressure(JSONObject.toJavaObject((JSON) ar.get(43), QFMessageVO.class).getVarValue());
        model.setServo2Flow(JSONObject.toJavaObject((JSON) ar.get(44), QFMessageVO.class).getVarValue());
        model.setServo3Pressure(JSONObject.toJavaObject((JSON) ar.get(45), QFMessageVO.class).getVarValue());
        model.setServo3Flow(JSONObject.toJavaObject((JSON) ar.get(46), QFMessageVO.class).getVarValue());

        try {
            QFMessageVO vo = JSONObject.toJavaObject((JSON) ar.get(0), QFMessageVO.class);
            model.setCreate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getReadTime()));
        } catch (ParseException e) {
            model.setCreate_time(new Date());
            log.error("钱富西门子消息中采集时间异常！已取用系统当前时间，异常信息:" + e);
            e.printStackTrace();
        }

        return  model;
    }

}
