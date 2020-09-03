package org.jeecg.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.MpiEquipmentComponent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.system.vo.MpiEquipmentComponentVo;

/**
 * @Description: 设备组成
 * @Author: jeecg-boot
 * @Date:   2020-07-09
 * @Version: V1.0
 */
public interface MpiEquipmentComponentMapper extends BaseMapper<MpiEquipmentComponent> {

    /**
     * 根据设备组成编号批量查询部件名称，设备名称
     * @param ids
     * @return
     */
    List<MpiEquipmentComponentVo> listEquipmentComponentByIds(List<String> ids);

    /**
     * 全查询设备组成信息 （设备名称，部件名称）
     */
    List<MpiEquipmentComponent> allList();

}
