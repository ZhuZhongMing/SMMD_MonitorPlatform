package org.jeecg.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.system.entity.hubin.HBMenuModel;
import org.jeecg.modules.system.entity.qianfu.QFPressModel;
import org.jeecg.modules.system.mapper.HBMenuModelMapper;
import org.jeecg.modules.system.mapper.QFPressModelMapper;
import org.jeecg.modules.system.service.IHBMenuModelService;
import org.jeecg.modules.system.service.IQFPressModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 杭州三米明德科技有限公司
 *
 * @author zzm
 * date 2020-08-06
 */
@Service
public class QFPressModelServiceImpl extends ServiceImpl<QFPressModelMapper, QFPressModel> implements IQFPressModelService {

    @Resource
    private QFPressModelMapper qfPressModelMapper;

    @Override
    public List<QFPressModel> queryTOP5ByEquipmentId(QFPressModel model) {
        return qfPressModelMapper.queryTOP5ByEquipmentId(model);
    }
}
