package org.jeecg.modules.listener.subInterface;

import org.eclipse.paho.client.mqttv3.MqttCallback;

public interface SubMqttCallBack extends MqttCallback {

    void  run();

}
