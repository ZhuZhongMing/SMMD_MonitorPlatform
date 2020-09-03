package org.jeecg.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.MpiMonitorSite;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 监测点
 * @Author: jeecg-boot
 * @Date:   2020-07-10
 * @Version: V1.0
 */
public interface MpiMonitorSiteMapper extends BaseMapper<MpiMonitorSite> {

    /**
     * 根据设备信息查询 监测点
     * @param equipmentId 设备编号
     * @return
     */
    List<MpiMonitorSite> querySiteListByEquipment(String equipmentId);

}
