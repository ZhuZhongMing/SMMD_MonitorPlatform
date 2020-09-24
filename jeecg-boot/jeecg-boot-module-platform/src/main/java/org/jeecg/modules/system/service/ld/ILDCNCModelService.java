package org.jeecg.modules.system.service.ld;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.system.entity.CNCModel;

/**
 * 杭州三米明德科技有限公司
 * 利登 cnc 数据模型
 * @author zzm
 * date 2020-07-24
 */
@DS("lidengDatasource")
public interface ILDCNCModelService extends IService<CNCModel> {

}
