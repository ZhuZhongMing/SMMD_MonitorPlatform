package org.jeecg.modules.system.service;

import org.jeecg.modules.system.entity.MpiMonitorSite;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 监测点
 * @Author: jeecg-boot
 * @Date:   2020-07-10
 * @Version: V1.0
 */
public interface IMpiMonitorSiteService extends IService<MpiMonitorSite> {

    /**
     * 根据设备信息查询 监测点
     * @param equipmentId 设备编号
     * @return
     */
    List<MpiMonitorSite> querySiteListByEquipment(String equipmentId);
}
