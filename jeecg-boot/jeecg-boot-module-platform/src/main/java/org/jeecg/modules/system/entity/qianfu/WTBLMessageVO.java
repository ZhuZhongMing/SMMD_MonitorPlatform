package org.jeecg.modules.system.entity.qianfu;

import lombok.Data;

/**
 * 物通博联设备网关数据
 */
@Data
public class WTBLMessageVO {
    private String varName;
    private String varValue;
    private String readTime;
    private String isWarn;
    private String varId;
}
