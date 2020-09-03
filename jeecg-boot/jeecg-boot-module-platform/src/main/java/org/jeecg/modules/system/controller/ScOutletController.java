package org.jeecg.modules.system.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.system.entity.ScOutlet;
import org.jeecg.modules.system.service.IScOutletService;
import org.jeecg.modules.system.vo.CoordinateVO;
import org.jeecg.modules.system.vo.ScOutletCoordinateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.jeecg.modules.system.util.CoordinateUtil.getDistance;
import static org.jeecg.modules.system.util.CoordinateUtil.haversine;

/**
 * 杭州三米明德科技有限公司
 *  <p>充电桩</p>
 * @author zzm
 * date 2020-08-19
 */
@Api(tags="充电桩")
@Slf4j
@RestController
@RequestMapping("/system/scOutlet")
public class ScOutletController {

    @Autowired
    private IScOutletService iScOutletService;

    /**
     * 根据坐标查询固定范围内设备
     *
     * @return
     */
    @GetMapping(value = "/queryNear")
    public Result<?> queryEquitmentByCoordinate(CoordinateVO coordinateVO) {
        Result<List<ScOutletCoordinateVO>> result = new Result<List<ScOutletCoordinateVO>>();
        try {
            Double radius = 30000D; //半径距离,30000m以内 -- 默认
            if ( null != coordinateVO.getRadius()) {
                radius = coordinateVO.getRadius();
            }
            List<Double> ar = haversine(coordinateVO.getLng(), coordinateVO.getLat(), radius);
            QueryWrapper<ScOutlet> queryWrapper = new QueryWrapper<>();
            queryWrapper.ge("latitude",ar.get(0));
            queryWrapper.le("latitude",ar.get(1));
            queryWrapper.ge("longitude",ar.get(2));
            queryWrapper.le("longitude",ar.get(3));
            List<ScOutlet> list = iScOutletService.list(queryWrapper);
            List<ScOutletCoordinateVO> records = new ArrayList<>(); // 返回的结果，其中存储设备经度，纬度，id，查询范围半径，地址，名称
            Double finalRadius = radius; //半径
            // log.info("半径 ： " + finalRadius);
            list.forEach(item -> {
                Double v = getDistance(coordinateVO.getLat(), coordinateVO.getLng(), item.getLatitude().doubleValue(), item.getLongitude().doubleValue());
                //log.info("v : " + v + "   distance : " + distance);
                if (finalRadius > 0) {
                    if (v < finalRadius) {
                        records.add(new ScOutletCoordinateVO(item, finalRadius));
                    }
                }
            });
            log.info("records : " + JSON.toJSONString(records));
            result.setResult(records);
            result.setSuccess(true);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
    }

}
