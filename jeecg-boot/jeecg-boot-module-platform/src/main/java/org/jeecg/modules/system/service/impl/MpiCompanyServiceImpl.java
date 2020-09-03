package org.jeecg.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.jeecg.modules.system.entity.MpiCompany;
import org.jeecg.modules.system.entity.MpiEquipment;
import org.jeecg.modules.system.entity.MpiGateway;
import org.jeecg.modules.system.mapper.MpiCompanyMapper;
import org.jeecg.modules.system.mapper.MpiEquipmentMapper;
import org.jeecg.modules.system.mapper.MpiGatewayMapper;
import org.jeecg.modules.system.service.IMpiCompanyService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 公司表
 * @Author: jeecg-boot
 * @Date:   2020-07-08
 * @Version: V1.0
 */
@Service
public class MpiCompanyServiceImpl extends ServiceImpl<MpiCompanyMapper, MpiCompany> implements IMpiCompanyService {

    @Resource
    private MpiCompanyMapper mpiCompanyMapper;
    @Resource
    private MpiGatewayMapper mpiGatewayMapper;
    @Resource
    private MpiEquipmentMapper mpiEquipmentMapper;

    @Transactional
    @Override
    public void delGatewayEquipmentByCompany(List<String> ids) {
        // 1.根据公司查询以下设备
        QueryWrapper<MpiGateway> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.in("company_id",ids);
        List<MpiGateway> mpiGateways = mpiGatewayMapper.selectList(queryWrapper1); // 网关信息信息
        if (mpiGateways.size() > 0) {
            List<String> gatewayIds = mpiGateways.stream().map(MpiGateway::getId).collect(Collectors.toList()); // 网关编号
            QueryWrapper<MpiEquipment> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.in("gateway_id", gatewayIds);
            List<MpiEquipment> mpiEquipments = mpiEquipmentMapper.selectList(queryWrapper2); // 设备信息
            if (mpiEquipments.size() > 0) {
                List<String> equipmentIds = mpiEquipments.stream().map(MpiEquipment::getId).collect(Collectors.toList()); // 设备编号
                mpiEquipmentMapper.deleteBatchIds(equipmentIds); // 批量删除设备
            }
            //List<String> ids = new ArrayList<String>();
            mpiGatewayMapper.deleteBatchIds(gatewayIds); // 批量删除网关
        }
        // 未查询到公司下网关信息
        mpiCompanyMapper.deleteBatchIds(ids); // 删除公司
    }
}
