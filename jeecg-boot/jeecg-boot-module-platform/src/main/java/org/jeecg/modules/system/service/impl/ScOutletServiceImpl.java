package org.jeecg.modules.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jeecg.modules.system.entity.ScOutlet;
import org.jeecg.modules.system.mapper.ScOutletMapper;
import org.jeecg.modules.system.service.IScOutletService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 杭州三米明德科技有限公司
 * 充电桩
 * @author zzm
 * date 2020-08-19
 */
@Service
public class ScOutletServiceImpl extends ServiceImpl<ScOutletMapper, ScOutlet> implements IScOutletService {

    @Resource
    private ScOutletMapper scOutletMapper;

}
