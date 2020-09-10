package org.jeecg.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.system.entity.hubin.HBMenuModel;
import org.jeecg.modules.system.entity.qianfu.QFPressModel;

import java.util.List;

/**
 * 杭州三米明德科技有限公司
 *
 * @author zzm
 * date 2020-07-24
 */
public interface IQFPressModelService extends IService<QFPressModel> {

    /**
     * 查询最新5条的指定设备名称的 钱富 数据
     * @return
     */
    List<QFPressModel> queryTOP5ByEquipmentId(QFPressModel model);
}
