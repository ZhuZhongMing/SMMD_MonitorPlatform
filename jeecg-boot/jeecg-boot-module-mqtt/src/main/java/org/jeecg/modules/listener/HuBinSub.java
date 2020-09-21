package org.jeecg.modules.listener;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.jeecg.modules.system.entity.hubin.*;
import org.jeecg.modules.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.Date;

import static org.jeecg.modules.listener.utils.HBMessageDispose.*;
import static org.jeecg.modules.listener.utils.MQTTConnentionUtil.getMQTTConnect;
import static org.jeecg.modules.listener.utils.MQTTConnentionUtil.reconnectionMQTT;


/**
 * 杭州三米明德科技有限公司
 *
 * @author zzm
 * date 2020-08-28
 */
@Slf4j
@Component
public class HuBinSub implements MqttCallback {
    //@Value("${mqtt.host}")
    private String host = "tcp://47.105.51.27:1883";
    //@Value("${mqtt.name}")
    private String name = "admin";
    //@Value("${mqtt.password}")
    private String password = "public";
    @Value("${mqtt.topic.hubin}")
    private String[] topic = {"/sys/hubin/message"};
    //@Value("${mqtt.clientId.hubin}")
    private String clientId = "hubin_consumer";
    /**mqtt连接**/
    private MqttClient sampleClient;
    /*采集时间*/
    private Date create_time = null;

    @Autowired
    private IHBMenuModelService ihbMenuModelService;
    @Autowired
    private IHBAlarmModelService ihbAlarmModelService;
    @Autowired
    private IHBCorrenctModelService ihbCorrenctModelService;
    @Autowired
    private IHBFunctionModelService ihbFunctionModelService;
    @Autowired
    private IHBTaskModelService ihbTaskModelService;
    @Autowired
    private IHBTowModelService ihbTowModelService;

    // 连接丢失
    @Override
    public void connectionLost(Throwable throwable) {
        reconnectionMQTT(sampleClient,clientId);
        /*log.warn("【MQTT】【" + clientId + "】连接断开，30S后重新尝试重连......");
        while (true) {
            try {
                sampleClient.close();
                Thread.sleep(30000);
                this.run();
                log.info("=========================================================》【MQTT】【" + clientId + "】重新连接成功");
                break;
            } catch (Exception e) {
                e.printStackTrace();
                log.error("【MQTT】【" + clientId + "】重连时发生异常！异常信息：" + e);
            }
        }*/
    }

    /**
     * 订阅消息
     * @param topic 主题
     * @param message 消息
     * @throws Exception
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) {
        log.info("=========================================================》【"+ clientId +"】接收消息成功！当前消息主题 : " + topic);
        try {
            //String theMsg = MessageFormat.format("{0}", new String(message.getPayload()));
            String pagLoad = new String(message.getPayload());
            JSONObject jsonObject = JSONObject.parseObject(pagLoad);
            //JSONArray devList = jsonObject.getJSONArray("devList"); // 改动
            JSONObject varList = jsonObject.getJSONObject("varList");
            //JSONObject obj = JSONObject.parseObject(devList.get(0).toString()); // 改动
            //JSONArray varList = obj.getJSONArray("varList"); // 改动
            //System.out.println("varList : " + varList );
            /*Object varList = jsonObject.get("varList");*/
            if (null !=varList) {
                if (varList.toString().contains("Tow_Tension1_1")) {
                    if (null == create_time) {
                        create_time = new Date();
                    }
                    HBTowModel model =  JSONObject.toJavaObject(varList, HBTowModel.class);
                    //HBTowModel model =  MessageToTowModel(varList);
                    model.setCreate_time(create_time);
                    ihbTowModelService.save(model);
                    System.out.println("----------------> hubin第一组 : " + model );
                } else if (varList.toString().contains("Menu_ToothNumber")) {
                    if (null == create_time) {
                        create_time = new Date();
                    }
                    //HBMenuModel model = MessageToHBMenuModel(varList);
                    HBMenuModel model = JSONObject.toJavaObject(varList, HBMenuModel.class);
                    model.setCreate_time(create_time);
                    ihbMenuModelService.save(model);
                    System.out.println("----------------> hubin第二组 : " +model );
                } else if (varList.toString().contains("Function_ReverseValue")) {
                    if (null == create_time) {
                        create_time = new Date();
                    }
                    HBFunctionModel model = JSONObject.toJavaObject(varList, HBFunctionModel.class);
                    //HBFunctionModel model = MessageToHBFunctionModel(varList);
                    model.setCreate_time(create_time);
                    ihbFunctionModelService.save(model);
                    System.out.println("----------------> hubin第三组 : " + model );
                } else if (varList.toString().contains("Correct_1Color")) {
                    if (null == create_time) {
                        create_time = new Date();
                    }
                    HBCorrenctModel model = JSONObject.toJavaObject(varList, HBCorrenctModel.class);
                    //HBCorrenctModel model = MessageToHBCorrenctModel(varList);
                    model.setCreate_time(create_time);
                    ihbCorrenctModelService.save(model);
                    System.out.println("----------------> hubin第四组 : " + model );
                } else if (varList.toString().contains("Task_MeterNumber_CuttentValue")) {
                    if (null == create_time) {
                        create_time = new Date();
                    }
                    HBTaskModel model = JSONObject.toJavaObject(varList, HBTaskModel.class);
                    //HBTaskModel model = MessageToHBTaskModel(varList);
                    model.setCreate_time(create_time);
                    ihbTaskModelService.save(model);
                    System.out.println("----------------> hubin第五组 : " + model );
                } else if (varList.toString().contains("Alarm_UnwindingLnflationNotOpen")) {
                    if (null == create_time) {
                        create_time = new Date();
                    }
                    HBAlarmModel model = JSONObject.toJavaObject(varList, HBAlarmModel.class);
                    //HBAlarmModel model = MessageToHBAlarmModel(varList);
                    model.setCreate_time(create_time);
                    ihbAlarmModelService.save(model);
                    create_time = null; // 清除
                    System.out.println("----------------> hubin第六组 : " + model );
                }
            }

        } catch (Exception e) {
            System.out.println("无用消息");
            e.printStackTrace();
        }

    }

    /**
     *  发布消息
     * @param iMqttDeliveryToken
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("发送消息");
    }

    /**
     * 开启连接
     */
    @PostConstruct
    public void  run() {
        sampleClient = getMQTTConnect(host, clientId, name, password, topic);
        sampleClient.setCallback(this);
        /*MemoryPersistence persistence = new MemoryPersistence();
        try{
            sampleClient = new MqttClient(host, clientId, persistence);
            // 连接设置
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            // 设置连接用户名
            connOpts.setUserName(name);
            // 设置连接的密码
            connOpts.setPassword(password.toCharArray());
            // 设置超时时间，单位S
            connOpts.setConnectionTimeout(10);
            // 设置会话心跳时间，单位S
            connOpts.setKeepAliveInterval(3600);
            connOpts.setAutomaticReconnect(true);

            sampleClient.connect(connOpts);
            //IMqttToken token = sampleClient.connectWithResult(connOpts);
            //token.waitForCompletion();
            log.info("=============================================》 【MQTT】【" + clientId + "】连接成功！");
            log.info("=============================================》 服务端地址:" + host);
            log.info("=============================================》 主题:" + JSONObject.toJSONString(topic));

            sampleClient.subscribe(topic);

            sampleClient.setCallback(this);

        }catch(MqttException me){
            log.error("【MQTT】【" + clientId + "】连接时发生异常！异常信息：" + me);
        }*/
    }
}
