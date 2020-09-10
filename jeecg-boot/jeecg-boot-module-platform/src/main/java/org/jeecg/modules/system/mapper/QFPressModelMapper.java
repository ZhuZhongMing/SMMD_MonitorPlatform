package org.jeecg.modules.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.system.entity.hubin.HBMenuModel;
import org.jeecg.modules.system.entity.qianfu.QFPressModel;

import java.util.List;

/**
 * 杭州三米明德科技有限公司
 *
 * @author zzm
 * date 2020-08-06
 */
public interface QFPressModelMapper extends BaseMapper<QFPressModel> {

    /**
     * 查询最新5条的指定设备名称的 钱富 数据
     * @return
     */
    List<QFPressModel> queryTOP5ByEquipmentId(QFPressModel model);

}
