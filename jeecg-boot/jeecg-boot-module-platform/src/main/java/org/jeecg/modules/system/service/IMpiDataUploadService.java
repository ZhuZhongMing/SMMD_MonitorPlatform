package org.jeecg.modules.system.service;

import org.jeecg.modules.system.entity.MpiDataUpload;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 设备上行数据
 * @Author: jeecg-boot
 * @Date:   2020-07-16
 * @Version: V1.0
 */
public interface IMpiDataUploadService extends IService<MpiDataUpload> {

    /**
     * 根据上行数据编号批量查询设备名称，监测点名称
     * @param ids
     * @return
     */
    Map<String, List<String>> listEquipmentSiteByIds(List<String> ids);
}
