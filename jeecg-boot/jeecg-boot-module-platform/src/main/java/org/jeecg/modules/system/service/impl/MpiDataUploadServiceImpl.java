package org.jeecg.modules.system.service.impl;

import org.jeecg.modules.system.entity.MpiDataUpload;
import org.jeecg.modules.system.mapper.MpiDataUploadMapper;
import org.jeecg.modules.system.service.IMpiDataUploadService;
import org.jeecg.modules.system.vo.MpiDataUploadEquipmentSiteVo;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 设备上行数据
 * @Author: jeecg-boot
 * @Date:   2020-07-16
 * @Version: V1.0
 */
@Service
public class MpiDataUploadServiceImpl extends ServiceImpl<MpiDataUploadMapper, MpiDataUpload> implements IMpiDataUploadService {

    @Resource
    private MpiDataUploadMapper mpiDataUploadMapper;

    @Override
    public Map<String, List<String>> listEquipmentSiteByIds(List<String> ids) {
        List<MpiDataUploadEquipmentSiteVo> list = mpiDataUploadMapper.listEquipmentSiteByIds(ids);
        Map<String, List<String>> res = new HashMap<String, List<String>>();
        list.forEach(item -> {
                    if (res.get(item.getId()) == null) {
                        List<String> ar = new ArrayList<String>();
                        ar.add(item.getEquipmentName());// 第0位为设备名称名称
                        ar.add(item.getMonitorName());// 第1位为监测名称
                        res.put(item.getId(), ar);
                    } else {
                        List<String> ar = new ArrayList<String>();
                        ar.add(item.getEquipmentName());// 第0位为设备名称名称
                        ar.add(item.getMonitorName());// 第1位为监测名称
                        res.put(item.getId(),ar);
                        //res.put(item.getId(), res.get(item.getId()) + "," + item.getCname()); // 此处为多个值值存入时，例如设备图片，可同时存储多个图片地址，以逗号隔开
                    }
                }
        );
        return res;
    }
}
