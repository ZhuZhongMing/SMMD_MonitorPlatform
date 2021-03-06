package org.jeecg.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.system.entity.CNCModel;
import org.jeecg.modules.system.mapper.CNCModelMapper;
import org.jeecg.modules.system.service.ICNCModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 杭州三米明德科技有限公司
 * CNC数据模型
 * @author zzm
 * date 2020-08-06
 */
@Service
public class CNCModelServiceImpl extends ServiceImpl<CNCModelMapper, CNCModel> implements ICNCModelService {

    /**
     * 插入一条记录（选择字段，策略插入）
     *
     * @param entity 实体对象
    @Override
    //@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Transactional( propagation = Propagation.REQUIRES_NEW)
    public boolean save(CNCModel entity) {
        return retBool(baseMapper.insert(entity));
    }*/

}
