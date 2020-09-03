package org.jeecg.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.system.entity.MpiEquipment;
import org.jeecg.modules.system.entity.MpiGateway;
import org.jeecg.modules.system.mapper.MpiEquipmentMapper;
import org.jeecg.modules.system.mapper.MpiGatewayMapper;
import org.jeecg.modules.system.service.IMpiGatewayService;
import org.jeecg.modules.system.vo.MpiGatewayCompanyVo;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: 网关
 * @Author: jeecg-boot
 * @Date:   2020-07-09
 * @Version: V1.0
 */
@Service
public class MpiGatewayServiceImpl extends ServiceImpl<MpiGatewayMapper, MpiGateway> implements IMpiGatewayService {

    @Resource
    private MpiGatewayMapper mpiGatewayMapper;
    @Resource
    private MpiEquipmentMapper mpiEquipmentMapper;

    @Override
    public Map<String, String> listGatewayCompanyByIds(List<String> ids) {
        List<MpiGatewayCompanyVo> list = mpiGatewayMapper.listGatewayCompanyByIds(ids);
        Map<String, String> res = new HashMap<String, String>();
        list.forEach(item -> {
                    if (res.get(item.getId()) == null) {
                        res.put(item.getId(), item.getCompanyName());
                    } else {
                        res.put(item.getId(), res.get(item.getId()) + "," + item.getCompanyName());
                    }
                }
        );
        return res;
    }

    @Transactional
    @Override
    public void deleteBatchByGatewayIds(List<String> ids) {
        //mpiGatewayMapper.deleteBatchByCompanyIds(ids);
        // 1.根据公司查询以下设备
        QueryWrapper<MpiEquipment> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.in("gateway_id",ids);
        List<MpiEquipment> mpiEquipments = mpiEquipmentMapper.selectList(queryWrapper1); // 设备信息
        if (mpiEquipments.size() > 0) {
            List<String> equipmentIds = mpiEquipments.stream().map(MpiEquipment::getId).collect(Collectors.toList()); // 设备编号
            mpiEquipmentMapper.deleteBatchIds(equipmentIds); // 批量删除设备
        }
        // 未查询到网关下设备信息
        mpiGatewayMapper.deleteBatchIds(ids); // 批量删除网关
    }
}
