package org.jeecg.modules.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.system.entity.CNCModel;
import org.jeecg.modules.system.service.ICNCModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 杭州三米明德科技有限公司
 * CNC数据模型
 * @author zzm
 * date 2020-07-24
 */
@Api(tags="CNC数据模型")
@RestController
@RequestMapping("/system/cnc")
@Slf4j
public class CNCModelController extends JeecgController<CNCModel, ICNCModelService> {

    @Autowired
    private ICNCModelService icncModelService;


    /**
     * 查询最新的指定设备名称的 cnc 数据
     * @return
     */
    @ApiOperation(value="查询最新的指定设备名称的 cnc 数据", notes="查询最新的指定设备名称的 cnc 数据")
    @GetMapping(value = "/queryByEquipmentId")
    public Result<?> queryByEquipmentId(CNCModel cncModel) {
        QueryWrapper<CNCModel> queryWrapper =  new QueryWrapper<>();
        String lastSql = "where id in (select max(id) from cnc_model where cncsn = '" + cncModel.getCncsn() +"')";
        queryWrapper.last(lastSql);
        CNCModel model = null;
        try {
            model = icncModelService.list(queryWrapper).get(0);
        } catch (IndexOutOfBoundsException e) {
            log.info("下标越界");
        }
        return Result.ok(model);
    }


}
