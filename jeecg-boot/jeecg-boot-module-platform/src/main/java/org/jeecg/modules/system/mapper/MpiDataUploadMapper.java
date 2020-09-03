package org.jeecg.modules.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.system.entity.MpiDataUpload;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.system.vo.MpiDataUploadEquipmentSiteVo;

/**
 * @Description: 设备上行数据
 * @Author: jeecg-boot
 * @Date:   2020-07-16
 * @Version: V1.0
 */
public interface MpiDataUploadMapper extends BaseMapper<MpiDataUpload> {

    /**
     * 根据上行数据编号批量查询设备名称，监测点名称
     * @param ids
     * @return
     */
    List<MpiDataUploadEquipmentSiteVo> listEquipmentSiteByIds(List<String> ids);

}
