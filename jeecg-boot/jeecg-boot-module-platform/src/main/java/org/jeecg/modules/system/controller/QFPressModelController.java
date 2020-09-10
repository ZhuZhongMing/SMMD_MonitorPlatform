package org.jeecg.modules.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.system.entity.hubin.HBTaskModel;
import org.jeecg.modules.system.entity.qianfu.QFPressModel;
import org.jeecg.modules.system.service.IHBTaskModelService;
import org.jeecg.modules.system.service.IQFPressModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 杭州三米明德科技有限公司
 * 钱富，西门子plc ， 液压机
 * @author zzm
 * date 2020-07-24
 */
@Api(tags="menu")
@RestController
@RequestMapping("/system/qf")
@Slf4j
public class QFPressModelController extends JeecgController<QFPressModel, IQFPressModelService> {

    @Autowired
    private IQFPressModelService iQFPressModelService;

    /**
     * 查询最新的指定设备名称的 钱富 数据
     * @return
     */
    @ApiOperation(value="查询最新的指定设备名称的 钱富 数据", notes="查询最新的指定设备名称的 钱富 数据")
    @GetMapping(value = "/queryByEquipmentId")
    public Result<?> queryByEquipmentId(QFPressModel qfPressModel) throws Exception {
        QueryWrapper<QFPressModel> queryWrapper =  new QueryWrapper<>();
        String lastSql = "where id in (select max(id) from qf_press_model where equipmentsn = '" + qfPressModel.getEquipmentsn() +"')";
        queryWrapper.last(lastSql);
        QFPressModel model = null;
        try {
            model = iQFPressModelService.list(queryWrapper).get(0);
        } catch (IndexOutOfBoundsException e) {
            log.info("下标越界");
        }
        return Result.ok(model);
    }


    /**
     * 查询最新5条的指定设备名称的 钱富 数据
     * @return
     */
    @ApiOperation(value="查询最新的指定设备名称的 钱富 数据", notes="查询最新的指定设备名称的 钱富 数据")
    @GetMapping(value = "/queryTOP5ByEquipmentId")
    public Result<?> queryTOP5ByEquipmentId(QFPressModel qfPressModel) {
        List<QFPressModel> list = iQFPressModelService.queryTOP5ByEquipmentId(qfPressModel);
        list.forEach(item -> {
            /*系统压力*/
            if ("null".equals(item.getSystempressurevalue()) || item.getSystempressurevalue() == null) {
               item.setSystempressurevalue("0");
            }
            /*液压压力*/
            if ("null".equals(item.getSystemhydraulicpressure()) || item.getSystemhydraulicpressure() == null) {
                item.setSystemhydraulicpressure("0");
            }
            /*主缸压力*/
            if ("null".equals(item.getMastercylinderPressurevalue()) || item.getMastercylinderPressurevalue() == null) {
                item.setMastercylinderPressurevalue("0");
            }
        });
        return Result.ok(list);
    }

}
