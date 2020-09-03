package org.jeecg.modules.system.service.impl;

import org.jeecg.modules.system.entity.MpiMonitorSite;
import org.jeecg.modules.system.mapper.MpiMonitorSiteMapper;
import org.jeecg.modules.system.service.IMpiMonitorSiteService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: 监测点
 * @Author: jeecg-boot
 * @Date:   2020-07-10
 * @Version: V1.0
 */
@Service
public class MpiMonitorSiteServiceImpl extends ServiceImpl<MpiMonitorSiteMapper, MpiMonitorSite> implements IMpiMonitorSiteService {

    @Resource
    private MpiMonitorSiteMapper mpiMonitorSiteMapper;

    @Override
    public List<MpiMonitorSite> querySiteListByEquipment(String equipmentId) {
        return mpiMonitorSiteMapper.querySiteListByEquipment(equipmentId);
    }
}
