package org.jeecg.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.MpiGateway;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.system.vo.MpiGatewayCompanyVo;

/**
 * @Description: 网关
 * @Author: jeecg-boot
 * @Date:   2020-07-09
 * @Version: V1.0
 */
public interface MpiGatewayMapper extends BaseMapper<MpiGateway> {

    /**
     * 根据公司编号查询公司信息  --- 批量
     * @param ids 公司编号
     * @return
     */
    List<MpiGatewayCompanyVo> listGatewayCompanyByIds(List<String> ids);

}
