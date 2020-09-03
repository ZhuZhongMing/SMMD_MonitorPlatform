package org.jeecg.modules.system.service;

import org.jeecg.modules.system.entity.MpiEquipment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 设备
 * @Author: jeecg-boot
 * @Date:   2020-07-09
 * @Version: V1.0
 */
public interface IMpiEquipmentService extends IService<MpiEquipment> {

    /**
     * 根据设备编号查询网关信息  --- 批量
     * @param ids 设备编号
     * @return
     */
    Map<String, String> listEquipmentGatewayByIds(List<String> ids);

}
