package org.jeecg.modules.system.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.system.entity.hubin.HBMenuModel;
import org.jeecg.modules.system.entity.hubin.HBTaskModel;
import org.jeecg.modules.system.service.IHBMenuModelService;
import org.jeecg.modules.system.service.IHBTaskModelService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/system/hb/task")
@Slf4j
public class HBTaskModelController extends JeecgController<HBTaskModel, IHBTaskModelService> {

    @Autowired
    private IHBTaskModelService iHBTaskModelService;


}
