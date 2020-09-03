package org.jeecg.modules.system.entity.qianfu;

import lombok.Data;

/**
 * 钱富，西门子设备网关数据
 */
@Data
public class QFMessageVO {
    private String varName;
    private String varValue;
    private String readTime;
    private String isWarn;
    private String varId;
}
