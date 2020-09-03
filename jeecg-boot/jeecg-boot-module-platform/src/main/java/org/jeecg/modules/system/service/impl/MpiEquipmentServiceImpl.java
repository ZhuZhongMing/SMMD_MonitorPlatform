package org.jeecg.modules.system.service.impl;

import org.jeecg.modules.system.entity.MpiEquipment;
import org.jeecg.modules.system.mapper.MpiEquipmentMapper;
import org.jeecg.modules.system.service.IMpiEquipmentService;
import org.jeecg.modules.system.vo.MpiEquipmentGatewayVo;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 设备
 * @Author: jeecg-boot
 * @Date:   2020-07-09
 * @Version: V1.0
 */
@Service
public class MpiEquipmentServiceImpl extends ServiceImpl<MpiEquipmentMapper, MpiEquipment> implements IMpiEquipmentService {

    @Resource
    MpiEquipmentMapper mpiEquipmentMapper;

    @Override
    public Map<String, String> listEquipmentGatewayByIds(List<String> ids) {
        List<MpiEquipmentGatewayVo> list = mpiEquipmentMapper.listEquipmentGatewayByIds(ids);
        Map<String, String> res = new HashMap<String, String>();
        list.forEach(item -> {
                    if (res.get(item.getId()) == null) {
                        res.put(item.getId(), item.getGatewayName());
                    } else {
                        res.put(item.getId(), res.get(item.getId()) + "," + item.getGatewayName());
                    }
                }
        );
        return res;
    }

}
