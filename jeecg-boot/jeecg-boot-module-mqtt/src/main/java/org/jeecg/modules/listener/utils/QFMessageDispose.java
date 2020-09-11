package org.jeecg.modules.listener.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.system.entity.qianfu.WTBLMessageVO;
import org.jeecg.modules.system.entity.qianfu.QFPressModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        model.setSystempressurevalue(JSONObject.toJavaObject((JSON) ar.get(0), WTBLMessageVO.class).getVarValue());
        model.setMastercylinderPressurevalue(JSONObject.toJavaObject((JSON) ar.get(1), WTBLMessageVO.class).getVarValue());

        model.setSystemhydraulicpressure(JSONObject.toJavaObject((JSON) ar.get(2), WTBLMessageVO.class).getVarValue());
        model.setSystemtonnage(JSONObject.toJavaObject((JSON) ar.get(3), WTBLMessageVO.class).getVarValue());
        model.setMastercylinderNominalforce(JSONObject.toJavaObject((JSON) ar.get(4), WTBLMessageVO.class).getVarValue());
        model.setMastercylinderStroke(JSONObject.toJavaObject((JSON) ar.get(5), WTBLMessageVO.class).getVarValue());
        model.setMastercylinderReturnforce(JSONObject.toJavaObject((JSON) ar.get(6), WTBLMessageVO.class).getVarValue());
        model.setMastercylinderAccelerationspeed(JSONObject.toJavaObject((JSON) ar.get(7), WTBLMessageVO.class).getVarValue());
        model.setMastercylinderCompressionspeed(JSONObject.toJavaObject((JSON) ar.get(8), WTBLMessageVO.class).getVarValue());
        model.setMastercylinderReturntripspeed(JSONObject.toJavaObject((JSON) ar.get(9), WTBLMessageVO.class).getVarValue());
        model.setLowercylinderNominalforce(JSONObject.toJavaObject((JSON) ar.get(10), WTBLMessageVO.class).getVarValue());
        model.setLowercylinderStroke(JSONObject.toJavaObject((JSON) ar.get(11), WTBLMessageVO.class).getVarValue());
        model.setLowercylinderEjectionspeed(JSONObject.toJavaObject((JSON) ar.get(12), WTBLMessageVO.class).getVarValue());
        model.setLowercylinderReturntripspeed(JSONObject.toJavaObject((JSON) ar.get(13), WTBLMessageVO.class).getVarValue());
        model.setReturncylinderNominalforce(JSONObject.toJavaObject((JSON) ar.get(14), WTBLMessageVO.class).getVarValue());
        model.setReturncylinderStroke(JSONObject.toJavaObject((JSON) ar.get(15), WTBLMessageVO.class).getVarValue());
        model.setCylinderEjectionspeed(JSONObject.toJavaObject((JSON) ar.get(16), WTBLMessageVO.class).getVarValue());
        model.setCylinderReturnspeed(JSONObject.toJavaObject((JSON) ar.get(17), WTBLMessageVO.class).getVarValue());
        model.setMaximumBay(JSONObject.toJavaObject((JSON) ar.get(18), WTBLMessageVO.class).getVarValue());
        model.setMinimumBay(JSONObject.toJavaObject((JSON) ar.get(19), WTBLMessageVO.class).getVarValue());
        model.setWorkbenchLeftright(JSONObject.toJavaObject((JSON) ar.get(20), WTBLMessageVO.class).getVarValue());
        model.setWorkbenchUpdown(JSONObject.toJavaObject((JSON) ar.get(21), WTBLMessageVO.class).getVarValue());
        model.setMainmotorKw(JSONObject.toJavaObject((JSON) ar.get(22), WTBLMessageVO.class).getVarValue());
        model.setMainmotorX(JSONObject.toJavaObject((JSON) ar.get(23), WTBLMessageVO.class).getVarValue());
        model.setAuxiliarymotorKw(JSONObject.toJavaObject((JSON) ar.get(24), WTBLMessageVO.class).getVarValue());
        model.setAuxiliarymotorX(JSONObject.toJavaObject((JSON) ar.get(25), WTBLMessageVO.class).getVarValue());
        model.setMainoilpump(JSONObject.toJavaObject((JSON) ar.get(26), WTBLMessageVO.class).getVarValue());
        model.setMainoilpumpX(JSONObject.toJavaObject((JSON) ar.get(27), WTBLMessageVO.class).getVarValue());
        model.setAuxiliaryoilpump(JSONObject.toJavaObject((JSON) ar.get(28), WTBLMessageVO.class).getVarValue());
        model.setAuxiliaryoilpumpX(JSONObject.toJavaObject((JSON) ar.get(29), WTBLMessageVO.class).getVarValue());
        model.setDateofproduction(JSONObject.toJavaObject((JSON) ar.get(30), WTBLMessageVO.class).getVarValue());
        model.setFactorynumber(JSONObject.toJavaObject((JSON) ar.get(31), WTBLMessageVO.class).getVarValue());




        model.setSystemtransmissionrange(JSONObject.toJavaObject((JSON) ar.get(32), WTBLMessageVO.class).getVarValue());
        model.setMastercylindersystemtransmissionrange(JSONObject.toJavaObject((JSON) ar.get(33), WTBLMessageVO.class).getVarValue());
        model.setCounterdisplay(JSONObject.toJavaObject((JSON) ar.get(34), WTBLMessageVO.class).getVarValue());
        model.setMaingyrusStopspeed(JSONObject.toJavaObject((JSON) ar.get(35), WTBLMessageVO.class).getVarValue());
        model.setMastercylinderPositionvalue(JSONObject.toJavaObject((JSON) ar.get(36), WTBLMessageVO.class).getVarValue());
        model.setMastercylinderTonnagevalue(JSONObject.toJavaObject((JSON) ar.get(37), WTBLMessageVO.class).getVarValue());
        model.setLowercylinderPositionvalue(JSONObject.toJavaObject((JSON) ar.get(38), WTBLMessageVO.class).getVarValue());
        model.setLowercylinderTonnagevalue(JSONObject.toJavaObject((JSON) ar.get(39), WTBLMessageVO.class).getVarValue());
        model.setReturncylinderTonnagevalue(JSONObject.toJavaObject((JSON) ar.get(40), WTBLMessageVO.class).getVarValue());
        model.setServo1pressure(JSONObject.toJavaObject((JSON) ar.get(41), WTBLMessageVO.class).getVarValue());
        model.setServo1flow(JSONObject.toJavaObject((JSON) ar.get(42), WTBLMessageVO.class).getVarValue());
        model.setServo2pressure(JSONObject.toJavaObject((JSON) ar.get(43), WTBLMessageVO.class).getVarValue());
        model.setServo2flow(JSONObject.toJavaObject((JSON) ar.get(44), WTBLMessageVO.class).getVarValue());
        model.setServo3pressure(JSONObject.toJavaObject((JSON) ar.get(45), WTBLMessageVO.class).getVarValue());
        model.setServo3flow(JSONObject.toJavaObject((JSON) ar.get(46), WTBLMessageVO.class).getVarValue());

        try {
            WTBLMessageVO vo = JSONObject.toJavaObject((JSON) ar.get(0), WTBLMessageVO.class);
            model.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vo.getReadTime()));
        } catch (ParseException e) {
            model.setCreateTime(new Date());
            log.error("钱富西门子消息中采集时间异常！已取用系统当前时间，异常信息:" + e);
            e.printStackTrace();
        }

        return  model;
    }

}
