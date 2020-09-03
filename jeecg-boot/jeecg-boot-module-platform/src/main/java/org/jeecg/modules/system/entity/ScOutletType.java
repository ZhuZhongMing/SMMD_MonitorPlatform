package org.jeecg.modules.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * 杭州三米明德科技有限公司
 * @Description: 充电桩类型
 *<p> 充电桩类型 </p>
 * @author zzm
 * date 2020-08-18
 */
@Data
@TableName("sc_outlettype")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="sc_outlettype对象", description="充电桩类型")
public class ScOutletType implements Serializable {

    /**类型编号**/
    private String typeId;
    /**类型名称**/
    private String typeName;
    /**描述**/
    private String description;
    /**图片**/
    private String image;

}
