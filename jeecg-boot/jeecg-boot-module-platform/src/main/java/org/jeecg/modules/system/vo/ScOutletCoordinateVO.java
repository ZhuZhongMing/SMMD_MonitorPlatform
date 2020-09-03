package org.jeecg.modules.system.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecg.modules.system.entity.MpiEquipment;
import org.jeecg.modules.system.entity.ScOutlet;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 杭州三米明德科技有限公司
 * 设备地址信息  -- 坐标
 * @author zzm
 * date 2020-08-04
 */
@Data
public class ScOutletCoordinateVO {

    /**经度**/
    private Double lng;
    /**纬度**/
    private Double lat;
    /**半径**/
    private Double radius;
    /** 充电桩名称 **/
    private String outletName;
    /**安装地点**/
    private String installtionSite;
    /** 智能充电桩id **/
    private String outletId;

    public ScOutletCoordinateVO(ScOutlet sc, Double radius) {
        this.lng = sc.getLongitude().doubleValue();
        this.lat = sc.getLatitude().doubleValue();
        this.radius = radius;
        this.outletName = sc.getOutletName();
        this.installtionSite = sc.getInstalltionSite();
        this.outletId = sc.getOutletId();
    }

}
