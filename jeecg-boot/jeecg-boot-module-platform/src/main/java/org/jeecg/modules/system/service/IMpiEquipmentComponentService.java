package org.jeecg.modules.system.service;

import org.jeecg.modules.system.entity.MpiEquipmentComponent;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 设备组成
 * @Author: jeecg-boot
 * @Date:   2020-07-09
 * @Version: V1.0
 */
public interface IMpiEquipmentComponentService extends IService<MpiEquipmentComponent> {

    /**
     * 根据设备组成编号批量查询部件名称，设备名称
     * @param ids
     * @return
     */
    Map<String, List<String>> listEquipmentComponentByIds(List<String> ids);

    /**
     * 全查询设备组成信息 （设备名称，部件名称）
     */
    List<MpiEquipmentComponent> allList();
}
