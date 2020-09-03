package org.jeecg.modules.system.vo;
import lombok.Data;

/**
 * 杭州三米明德科技有限公司
 * 地址信息  -- 坐标
 * @author zzm
 * date 2020-08-04
 */
@Data
public class CoordinateVO {

    /**经度**/
    private Double lng;
    /**纬度**/
    private Double lat;
    /**半径**/
    private Double radius;
    /**id**/
    private String id;
    
}
