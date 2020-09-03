package org.jeecg.modules.listener;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.jeecg.modules.system.entity.CNCModel;
import org.jeecg.modules.system.service.ICNCModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

import static org.jeecg.modules.listener.utils.LDMessageDispose.mqttMessageDispose;

/**
 * 杭州三米明德科技有限公司
 *  MQTT 回调工具类
 * @author zzm
 * date 2020-08-24
 */
@Slf4j
@Component
public class LiDengSub implements MqttCallback {
    @Value("${mqtt.host}")
    private String host;
    @Value("${mqtt.name}")
    private String name;
    @Value("${mqtt.password}")
    private String password;
    @Value("${mqtt.topic.lideng}")
    private String[] topic;
    @Value("${mqtt.clientId.lideng}")
    private String clientId;
    /**mqtt连接**/
    private MqttClient sampleClient;

    @Autowired
    private ICNCModelService icncModelService;

    // 连接丢失
    @Override
    public void connectionLost(Throwable throwable) {
        log.warn("【MQTT】连接断开，30S后重新尝试重连......");
        while (true) {
            try {
                sampleClient.close();
                Thread.sleep(30000);
                this.run();
                log.info("=========================================================》【MQTT】重新连接成功");
                break;
            } catch (Exception e) {
                e.printStackTrace();
                log.error("【MQTT】重连时发生异常！异常信息：" + e);
            }
        }
    }

    /**
     * 订阅消息
     * @param topic 主题
     * @param message 消息
     * @throws Exception
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) {
        log.info("=========================================================》"+ clientId +"接收消息成功！当前消息主题 : " + topic);
        CNCModel cncModel = mqttMessageDispose(message);
        icncModelService.save(cncModel);
        log.info("cnc : " + cncModel);
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
        MemoryPersistence persistence = new MemoryPersistence();
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
            log.info("=============================================》 【MQTT】连接成功！");
            log.info("=============================================》 服务端地址:" + host);
            log.info("=============================================》 主题:" + JSONObject.toJSONString(topic));

            sampleClient.subscribe(topic);

            sampleClient.setCallback(this);

        }catch(MqttException me){
            log.error("【MQTT】连接时发生异常！异常信息：" + me);
        }
    }

}
