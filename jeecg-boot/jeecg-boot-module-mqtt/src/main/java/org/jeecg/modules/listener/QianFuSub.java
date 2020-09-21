package org.jeecg.modules.listener;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.jeecg.modules.system.entity.CNCModel;
import org.jeecg.modules.system.entity.qianfu.QFPressModel;
import org.jeecg.modules.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

import static org.jeecg.modules.listener.utils.CNCMessageDispose.mqttMessageDispose;
import static org.jeecg.modules.listener.utils.MQTTConnentionUtil.getMQTTConnect;
import static org.jeecg.modules.listener.utils.MQTTConnentionUtil.reconnectionMQTT;
import static org.jeecg.modules.listener.utils.QFMessageDispose.QFMessageDispose;


/**
 * 杭州三米明德科技有限公司
 *
 * @author zzm
 * date 2020-08-28
 */
@Slf4j
@Component
public class QianFuSub implements MqttCallback {
    //@Value("${mqtt.host}")
    private String host = "tcp://47.105.51.27:1883";
    //@Value("${mqtt.name}")
    private String name = "admin";
    //@Value("${mqtt.password}")
    private String password = "public";
    //@Value("${mqtt.topic.qianfu}")
    private String[] topic = {"/qianfu/device/press1/message", "/qianfu/device/press2/message", "/qianfu/cnc/siemens828d/001"};
    //@Value("${mqtt.clientId.qianfu}")
    private String clientId = "qianfu_consumer";
    /**mqtt连接**/
    private MqttClient sampleClient;
    /*采集时间*/
    private Date create_time = null;

    @Autowired
    private IQFPressModelService iqfPressModelService;
    @Autowired
    private ICNCModelService icncModelService;

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
            if ("/qianfu/cnc/siemens828d/001".equals(topic)) {
                /*乐芯*/
                CNCModel cncModel = mqttMessageDispose(message);
                icncModelService.save(cncModel);
                log.info("cnc : " + cncModel);
            } else {
                /*物通博联*/
                //String theMsg = MessageFormat.format("{0}", new String(message.getPayload()));
                String pagLoad = new String(message.getPayload());
                JSONObject jsonObject = JSONObject.parseObject(pagLoad);
                JSONArray devList = jsonObject.getJSONArray("devList");
                JSONObject json = (JSONObject) JSONObject.toJSON(devList.get(0));
                JSONArray varList = json.getJSONArray("varList");
                if (null != varList) {
                    String devNo = json.getString("devNo");
                    QFPressModel qfPressModel = QFMessageDispose(varList);
                    qfPressModel.setEquipmentsn(devNo);
                    if ("null".equals(qfPressModel.getCounterdisplay()) || null == qfPressModel.getCounterdisplay()) {
                        qfPressModel.setCounterdisplay("0");
                    }
                    iqfPressModelService.save(qfPressModel);
                    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + qfPressModel);
                }
            }
        } catch (Exception e) {
            System.out.println("无用消息");
            // e.printStackTrace();
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
