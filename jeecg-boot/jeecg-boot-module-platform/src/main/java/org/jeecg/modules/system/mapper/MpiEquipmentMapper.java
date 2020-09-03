package org.jeecg.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.MpiEquipment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.system.vo.MpiEquipmentGatewayVo;

/**
 * @Description: 设备
 * @Author: jeecg-boot
 * @Date:   2020-07-09
 * @Version: V1.0
 */
public interface MpiEquipmentMapper extends BaseMapper<MpiEquipment> {

    /**
     * 根据设备编号查询网关信息  --- 批量
     * @param ids 设备编号
     * @return
     */
    List<MpiEquipmentGatewayVo> listEquipmentGatewayByIds(List<String> ids);

}
