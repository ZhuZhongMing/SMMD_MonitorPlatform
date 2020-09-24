package org.jeecg.modules.system.service.qf.cnc;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.formula.functions.T;
import org.jeecg.modules.system.entity.CNCModel;
import org.jeecg.modules.system.mapper.qf.cnc.QFCNCModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 杭州三米明德科技有限公司
 * 钱富 CNC数据模型
 * @author zzm
 * date 2020-08-06
 */
@Service
public class QFCNCModelServiceImpl extends ServiceImpl<QFCNCModelMapper, CNCModel> implements IQFCNCModelService {

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
