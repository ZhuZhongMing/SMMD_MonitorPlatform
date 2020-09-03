package org.jeecg.modules.system.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.system.entity.hubin.HBAlarmModel;
import org.jeecg.modules.system.entity.hubin.HBCorrenctModel;
import org.jeecg.modules.system.service.IHBAlarmModelService;
import org.jeecg.modules.system.service.IHBCorrenctModelService;
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
@RequestMapping("/system/hb/correnct")
@Slf4j
public class HBCorrenctModelController extends JeecgController<HBCorrenctModel, IHBCorrenctModelService> {

    @Autowired
    private IHBCorrenctModelService ihbCorrenctModelService;


}
