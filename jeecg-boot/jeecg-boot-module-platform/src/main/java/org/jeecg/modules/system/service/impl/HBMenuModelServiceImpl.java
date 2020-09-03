package org.jeecg.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.system.entity.CNCModel;
import org.jeecg.modules.system.entity.hubin.HBMenuModel;
import org.jeecg.modules.system.mapper.CNCModelMapper;
import org.jeecg.modules.system.mapper.HBMenuModelMapper;
import org.jeecg.modules.system.service.ICNCModelService;
import org.jeecg.modules.system.service.IHBMenuModelService;
import org.springframework.stereotype.Service;

/**
 * 杭州三米明德科技有限公司
 *
 * @author zzm
 * date 2020-08-06
 */
@Service
public class HBMenuModelServiceImpl extends ServiceImpl<HBMenuModelMapper, HBMenuModel> implements IHBMenuModelService {
}
