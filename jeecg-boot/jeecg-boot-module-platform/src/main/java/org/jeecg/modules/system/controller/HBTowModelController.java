package org.jeecg.modules.system.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.system.entity.hubin.HBTaskModel;
import org.jeecg.modules.system.entity.hubin.HBTowModel;
import org.jeecg.modules.system.service.IHBTaskModelService;
import org.jeecg.modules.system.service.IHBTowModelService;
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
@RequestMapping("/system/hb/tow")
@Slf4j
public class HBTowModelController extends JeecgController<HBTowModel, IHBTowModelService> {

    @Autowired
    private IHBTowModelService iHBTowModelService;


}
