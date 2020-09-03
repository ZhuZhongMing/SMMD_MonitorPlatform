package org.jeecg.modules.system.service.impl;

import org.jeecg.modules.system.entity.MpiEquipmentComponent;
import org.jeecg.modules.system.mapper.MpiEquipmentComponentMapper;
import org.jeecg.modules.system.service.IMpiEquipmentComponentService;
import org.jeecg.modules.system.vo.MpiEquipmentComponentVo;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 设备组成
 * @Author: jeecg-boot
 * @Date:   2020-07-09
 * @Version: V1.0
 */
@Service
public class MpiEquipmentComponentServiceImpl extends ServiceImpl<MpiEquipmentComponentMapper, MpiEquipmentComponent> implements IMpiEquipmentComponentService {

    @Resource
    private MpiEquipmentComponentMapper mpiEquipmentComponentMapper;

    @Override
    public Map<String, List<String>> listEquipmentComponentByIds(List<String> ids) {
        List<MpiEquipmentComponentVo> list = mpiEquipmentComponentMapper.listEquipmentComponentByIds(ids);
        Map<String, List<String>> res = new HashMap<String, List<String>>();
        list.forEach(item -> {
                    if (res.get(item.getId()) == null) {
                        List<String> ar = new ArrayList<String>();
                        ar.add(item.getEquipmentName());// 第0位为设备名称名称
                        ar.add(item.getComponentName());// 第1位为部件名称
                        res.put(item.getId(), ar);
                    } else {
                        List<String> ar = new ArrayList<String>();
                        ar.add(item.getEquipmentName());// 第0位为设备名称名称
                        ar.add(item.getComponentName());// 第1位为部件名称
                        res.put(item.getId(),ar);
                        //res.put(item.getId(), res.get(item.getId()) + "," + item.getCname()); // 此处为多个值值存入时，例如设备图片，可同时存储多个图片地址，以逗号隔开
                    }
                }
        );
        return res;
    }

    @Override
    public List<MpiEquipmentComponent> allList() {
        return mpiEquipmentComponentMapper.allList();
    }
}
