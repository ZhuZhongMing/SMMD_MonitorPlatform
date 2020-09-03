package org.jeecg.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.system.entity.CNCModel;
import org.jeecg.modules.system.entity.hubin.HBMenuModel;
import org.jeecg.modules.system.service.ICNCModelService;
import org.jeecg.modules.system.service.IHBMenuModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 杭州三米明德科技有限公司
 *
 * @author zzm
 * date 2020-07-24
 */
@Api(tags="menu")
@RestController
@RequestMapping("/system/hb/menu")
@Slf4j
public class HBMenuModelController extends JeecgController<HBMenuModel, IHBMenuModelService> {

    @Autowired
    private IHBMenuModelService iHBMenuModelService;


}
