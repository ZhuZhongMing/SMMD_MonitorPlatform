package org.jeecg.modules.system.service;

import org.jeecg.modules.system.entity.MpiCompany;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.system.vo.MpiCompanyGatewayTreeModel;

import java.util.List;

/**
 * @Description: 公司表
 * @Author: jeecg-boot
 * @Date:   2020-07-08
 * @Version: V1.0
 */
public interface IMpiCompanyService extends IService<MpiCompany> {

    /**
     * 删除公司 ，及以下网关，及以下设备
     * @param ids 公司编号
     */
    void delGatewayEquipmentByCompany (List<String> ids);

}
