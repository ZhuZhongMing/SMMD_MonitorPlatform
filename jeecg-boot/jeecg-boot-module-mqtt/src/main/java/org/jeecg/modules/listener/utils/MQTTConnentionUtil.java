package org.jeecg.modules.listener.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.jeecg.modules.listener.QianFuSub;
import org.jeecg.modules.listener.subInterface.SubMqttCallBack;

/**
 * 杭州三米明德科技有限公司
 *
 * @author zzm
 * date 2020-09-04
 */
@Slf4j
public class MQTTConnentionUtil {

    /**
     *  MQTT连接
     * @param host MQTT 服务器ip地址：1833
     * @param clientId 客户端id
     * @param name 用户名
     * @param password 密码
     * @param topic 主题S
     * @return MQTT连接
     */
    public static MqttClient getMQTTConnect(String host, String clientId, String name, String password, String topic) {
        log.info("host:" + host);
        log.info("clientId:" + clientId);
        log.info("name:" + name);
        log.info("password:" + password);
        log.info("topic:" + topic);
        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient sampleClient = null;
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

            //sampleClient.subscribe(String.valueOf(topic), topic.length);
            Integer[] qos = {1,2,3};
            sampleClient.subscribe(topic);
        }catch(MqttException me){
            log.error("【MQTT】【" + clientId + "】连接时发生异常！异常信息：" + me);
        }
        return sampleClient;
    }


    /**
     *
     * @param sampleClient 客户端连接
     * @param clientId 客户端id
     * @param sub
     */
    public static void reconnectionMQTT(MqttClient sampleClient, String clientId, SubMqttCallBack sub) {
        log.warn("【MQTT】【" + clientId + "】连接断开，30S后重新尝试重连......");
        while (true) {
            try {
                sampleClient.close();
                Thread.sleep(30000);
                sub.run();
                log.info("=========================================================》【MQTT】【" + clientId + "】重新连接成功");
                break;
            } catch (InterruptedException e) {
                Thread.interrupted(); // 重置线程中断状态
                log.error("【MQTT】【" + clientId + "】重连时发生线程中断异常！异常信息：" + e);
            }catch (Exception e) {
                e.printStackTrace();
                log.error("【MQTT】【" + clientId + "】重连时发生异常！异常信息：" + e);
            }
        }
    }
}
