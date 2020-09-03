package org.jeecg.modules.system.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 杭州三米明德科技有限公司
 * 经纬度计算工具类
 * @author zzm
 * date 2020-08-05
 */
public class CoordinateUtil {

    private static final double EARTH_RADIUS = 6371393; // 平均半径,单位：m；不是赤道半径。赤道为6378左右
    private static double PI = 3.14159265;

    /**
     * @描述 反余弦进行计算
     * @参数  [lat1, lng1, lat2, lng2]
     * @返回值  double
     * @创建人  Young
     * @创建时间  2019/3/13 20:31
     **/
    public static double getDistance(Double lat1,Double lng1,Double lat2,Double lng2) {
        // 经纬度（角度）转弧度。弧度用作参数，以调用Math.cos和Math.sin
        double radiansAX = Math.toRadians(lng1); // A经弧度
        double radiansAY = Math.toRadians(lat1); // A纬弧度
        double radiansBX = Math.toRadians(lng2); // B经弧度
        double radiansBY = Math.toRadians(lat2); // B纬弧度

        // 公式中“cosβ1cosβ2cos（α1-α2）+sinβ1sinβ2”的部分，得到∠AOB的cos值
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
                + Math.sin(radiansAY) * Math.sin(radiansBY);
        //System.out.println("cos = " + cos); // 值域[-1,1]
        double acos = Math.acos(cos); // 反余弦值
        //System.out.println("acos = " + acos); // 值域[0,π]
        //System.out.println("∠AOB = " + Math.toDegrees(acos)); // 球心角 值域[0,180]
        return EARTH_RADIUS * acos; // 最终结果
    }


    /**
     * 根据指定经纬度，以及半径计算指定长度 2 * dis 矩形范围内最大最小经纬度
     * @param lng 精度
     * @param lat 纬度
     * @param raidus 距离 - 半径
     * @return
     */
    public static List<Double> haversine(Double lng, Double lat, Double raidus) {
        Double latitude = lat;      //纬度
        Double longitude = lng;     //经度
        Double degree = (24901 * 1609) / 360.0; //1 度长多少公里 南极到北极 纬度 】
        Double raidusMile = raidus;
        Double dpmLat = 1 / degree;     //1公里  等于多少度
        Double radiusLat = dpmLat * raidusMile;
        Double minLat = latitude - radiusLat;
        Double maxLat = latitude + radiusLat;
        Double mpdLng = degree * Math.cos(latitude * (PI / 180));//横的
        Double dpmLng = 1 / mpdLng;
        Double radiusLng = dpmLng * raidusMile;
        Double minLng = longitude - radiusLng;
        Double maxLng = longitude + radiusLng;
        //return new double[] { minLat, minLng, maxLat, maxLng };
        List<Double> ar =  new ArrayList<>();
        ar.add(0,minLat); // 指定第0位为最小纬度
        ar.add(1,maxLat); // 指定第0位为最大纬度
        ar.add(2,minLng); // 指定第0位为最小经度
        ar.add(3,maxLng); // 指定第0位为最大经度*/L
        return ar;
    }

}
