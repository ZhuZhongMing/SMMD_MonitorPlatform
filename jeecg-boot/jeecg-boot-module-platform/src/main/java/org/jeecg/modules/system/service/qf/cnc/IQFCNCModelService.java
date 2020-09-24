package org.jeecg.modules.system.service.qf.cnc;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.ss.formula.functions.T;
import org.jeecg.modules.system.entity.CNCModel;
import org.springframework.transaction.annotation.Transactional;


/**
 * 杭州三米明德科技有限公司
 * 钱富 cnc 数据模型
 * @author zzm
 * date 2020-07-24
 */
@DS("qianfuDatasource")
public interface IQFCNCModelService extends IService<CNCModel> {


}
