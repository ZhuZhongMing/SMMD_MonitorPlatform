package org.jeecg.modules.system.service.qf.wg;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.system.entity.qianfu.QFPressModel;
import org.jeecg.modules.system.mapper.QFPressModelMapper;
import org.jeecg.modules.system.mapper.qf.wg.QFWGPressModelMapper;
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
public class QFWGPressModelServiceImpl extends ServiceImpl<QFWGPressModelMapper, QFPressModel> implements IQFWGPressModelService {

    @Resource
    private QFWGPressModelMapper qfPressModelMapper;

    @Override
    public List<QFPressModel> queryTOP5ByEquipmentId(QFPressModel model) {
        return qfPressModelMapper.queryTOP5ByEquipmentId(model);
    }
}
