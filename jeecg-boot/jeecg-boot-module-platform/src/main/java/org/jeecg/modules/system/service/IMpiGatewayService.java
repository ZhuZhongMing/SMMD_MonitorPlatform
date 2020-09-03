package org.jeecg.modules.system.service;

import org.jeecg.modules.system.entity.MpiGateway;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 网关
 * @Author: jeecg-boot
 * @Date:   2020-07-09
 * @Version: V1.0
 */
public interface IMpiGatewayService extends IService<MpiGateway> {

    /**
     * 根据公司编号查询公司信息  --- 批量
     * @param ids 公司编号
     * @return
     */
    Map<String, String> listGatewayCompanyByIds(List<String> ids);

    /**
     * 根据公司编号，批量删除网关
     * @param ids 公司编号
     */
    void deleteBatchByGatewayIds(List<String> ids);

}
