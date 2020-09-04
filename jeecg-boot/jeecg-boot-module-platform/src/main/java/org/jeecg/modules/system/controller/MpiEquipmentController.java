package org.jeecg.modules.system.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.system.entity.MpiEquipment;
import org.jeecg.modules.system.entity.MpiGateway;
import org.jeecg.modules.system.service.IMpiEquipmentService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.system.service.IMpiGatewayService;
import org.jeecg.modules.system.vo.CoordinateVO;
import org.jeecg.modules.system.vo.EquipmentCoordinateVO;
import org.jeecg.modules.system.vo.MpiEquipmentComponentTreeModel;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

import static org.jeecg.modules.system.util.CoordinateUtil.*;

/**
 * @Description: 设备
 * @Author: jeecg-boot
 * @Date:   2020-07-09
 * @Version: V1.0
 */
@Api(tags="设备")
@RestController
@RequestMapping("/system/mpiEquipment")
@Slf4j
public class MpiEquipmentController extends JeecgController<MpiEquipment, IMpiEquipmentService> {
	@Autowired
	private IMpiEquipmentService mpiEquipmentService;
	@Autowired
	private IMpiGatewayService mpiGatewayService;

	 /**
	  * 根据坐标查询固定范围内设备
	  *
	  * @return
	  */
	 @RequestMapping(value = "/queryEquitmentByCoordinate", method = RequestMethod.GET)
	 public Result<?> queryEquitmentByCoordinate(CoordinateVO coordinateVO) {
		 Result<List<EquipmentCoordinateVO>> result = new Result<>();
		 try {
			Double radius = 30000D; //半径距离,30000m以内
		 	if ( null != coordinateVO.getRadius()) {
				radius = coordinateVO.getRadius();
			}

			 List<Double> ar = haversine(coordinateVO.getLng(), coordinateVO.getLat(), radius);
			 //DecimalFormat decimalFormat = new DecimalFormat("#.000000");
			 QueryWrapper<MpiEquipment> queryWrapper = new QueryWrapper<>();
			 queryWrapper.ge("latitude",ar.get(0));
			 queryWrapper.le("latitude",ar.get(1));
			 queryWrapper.ge("longitude",ar.get(2));
			 queryWrapper.le("longitude",ar.get(3));
			 List<MpiEquipment> list = mpiEquipmentService.list(queryWrapper);
			 List<EquipmentCoordinateVO> records = new ArrayList<>();
			 Double finalRadius = radius; //半径
			 list.forEach(item -> {
				Double v = getDistance(coordinateVO.getLat(), coordinateVO.getLng(), item.getLatitude().doubleValue(), item.getLongitude().doubleValue());
				//log.info("v : " + v + "   distance : " + distance);
				if (finalRadius > 0) {
					if (v < finalRadius) {
						records.add(new EquipmentCoordinateVO(item));
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

	 /**
	  * 查询数据 查出所有公司,并以树结构数据格式响应给前端
	  *
	  * @return
	  */
	 @RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
	 public Result<List<MpiEquipmentComponentTreeModel>> queryTreeList(MpiEquipment mpiEquipment,
																	   HttpServletRequest req) {
		 Result<List<MpiEquipmentComponentTreeModel>> result = new Result<>();
		 try {
			 QueryWrapper<MpiEquipment> queryWrapper = QueryGenerator.initQueryWrapper(mpiEquipment, req.getParameterMap());
			 List<MpiEquipment> list = mpiEquipmentService.list(queryWrapper);

			 List<MpiEquipmentComponentTreeModel> records = new ArrayList<>();
			 list.forEach(item -> {
				 records.add(new MpiEquipmentComponentTreeModel(item));
			 });
			 result.setResult(records);
			 result.setSuccess(true);
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
		 }
		 return result;
	 }

	 /**
	  * 全查询
	  * @return
	  */
	 //@AutoLog(value = "ls_gateway-分页列表查询")
	 @ApiOperation(value="设备-全查询", notes="设备-全查询")
	 @GetMapping(value = "/allList")
	 public Result<?> queryAllList(MpiEquipment mpiEquipment,
								   HttpServletRequest req) {
		 QueryWrapper<MpiEquipment> queryWrapper = QueryGenerator.initQueryWrapper(mpiEquipment, req.getParameterMap());
		 List<MpiEquipment> list = mpiEquipmentService.list(queryWrapper);
		 return Result.ok(list);
	 }

	 /**
	  * 根据公司编号查询设备ids
	  * id 公司编号
	  * @return
	  */
	 @ApiOperation(value="设备-根据公司编号查询设备ids", notes="设备-根据公司编号查询设备ids")
	 @GetMapping(value = "/getEquipmentIdsByCompany")
	 public Result<?> getEquipmentIdsByCompany(String id) {
		 /*按公司检索设备信息*/
		 QueryWrapper<MpiGateway> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("company_id", id);
		 List<MpiGateway> list = mpiGatewayService.list(queryWrapper);
		 QueryWrapper<MpiEquipment> queryWrapper1 = new QueryWrapper<>();
		 List<String> ids = new ArrayList<String>();
		 if (list.size() > 0) {
			 ids = list.stream().map(MpiGateway::getId).collect(Collectors.toList());
			 queryWrapper1.in("gateway_id",ids);
		 } else {
			 // 该公司下未查询到网关信息
			 ids.add("");
			 queryWrapper1.in("gateway_id",ids);
		 }
		 List<MpiEquipment> list1 = mpiEquipmentService.list(queryWrapper1);
		 List<String> ids1 = list1.stream().map(MpiEquipment::getId).collect(Collectors.toList());
		 return Result.ok(ids1);
	 }

	/**
	 * 根据公司编号查询设备s
	 * id 公司编号
	 * @return
	 */
	@ApiOperation(value="设备-根据公司编号查询设备s", notes="设备-根据公司编号查询设备s")
	@GetMapping(value = "/getEquipmentListByCompany")
	public Result<?> getEquipmentListByCompany(String id) {
		/*按公司检索设备信息*/
		QueryWrapper<MpiGateway> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("company_id", id);
		List<MpiGateway> list = mpiGatewayService.list(queryWrapper);
		QueryWrapper<MpiEquipment> queryWrapper1 = new QueryWrapper<>();
		List<String> ids = new ArrayList<String>();
		if (list.size() > 0) {
			ids = list.stream().map(MpiGateway::getId).collect(Collectors.toList());
			queryWrapper1.in("gateway_id",ids);
		} else {
			// 该公司下未查询到网关信息
			ids.add("");
			queryWrapper1.in("gateway_id",ids);
		}
		List<MpiEquipment> list1 = mpiEquipmentService.list(queryWrapper1);
		return Result.ok(list1);
	}

	 /**
	 * 分页列表查询
	 *
	 * @param mpiEquipment
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "设备-分页列表查询")
	@ApiOperation(value="设备-分页列表查询", notes="设备-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MpiEquipment mpiEquipment,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		IPage<MpiEquipment> pageList = null;
		Page<MpiEquipment> page = new Page<MpiEquipment>(pageNo, pageSize);
		if (null != mpiEquipment.getCompanyId() && "" != mpiEquipment.getCompanyId()) {
			/*按公司检索设备信息*/
			QueryWrapper<MpiGateway> queryWrapper1 = new QueryWrapper<>();
			queryWrapper1.eq("company_id",mpiEquipment.getCompanyId());
			List<MpiGateway> list = mpiGatewayService.list(queryWrapper1);
			QueryWrapper<MpiEquipment> queryWrapper2 = new QueryWrapper<>();
			List<String> ids = new ArrayList<String>();
			if (list.size() > 0) {
				ids = list.stream().map(MpiGateway::getId).collect(Collectors.toList());
				queryWrapper2.in("gateway_id",ids);
			} else {
				// 该公司下未查询到网关信息
				ids.add("");
				queryWrapper2.in("gateway_id",ids);
			}
			pageList = mpiEquipmentService.page(page, queryWrapper2);
		} else {
			QueryWrapper<MpiEquipment> queryWrapper = QueryGenerator.initQueryWrapper(mpiEquipment, req.getParameterMap());
			pageList = mpiEquipmentService.page(page, queryWrapper);
		}

		if (null != pageList && pageList.getSize() > 0) {
			//批量查询网关的所属公司
			//step.1 先拿到全部的 gatewayIds
			//step.2 通过 gatewayIds，一次性查询网关所属公司的名称
			List<String> ids = pageList.getRecords().stream().map(MpiEquipment::getId).collect(Collectors.toList());

			if(ids!=null && ids.size()>0){
				Map<String,String>  names = mpiEquipmentService.listEquipmentGatewayByIds(ids);
				pageList.getRecords().forEach(item->{
					//TODO 存储网关名称
					item.setGatewayName(names.get(item.getId()));
				});
			}
		}

        return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mpiEquipment
	 * @return
	 */
	@AutoLog(value = "设备-添加")
	@ApiOperation(value="设备-添加", notes="设备-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MpiEquipment mpiEquipment) {
		mpiEquipmentService.save(mpiEquipment);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mpiEquipment
	 * @return
	 */
	@AutoLog(value = "设备-编辑")
	@ApiOperation(value="设备-编辑", notes="设备-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MpiEquipment mpiEquipment) {
		mpiEquipmentService.updateById(mpiEquipment);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备-通过id删除")
	@ApiOperation(value="设备-通过id删除", notes="设备-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mpiEquipmentService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "设备-批量删除")
	@ApiOperation(value="设备-批量删除", notes="设备-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mpiEquipmentService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备-通过id查询")
	@ApiOperation(value="设备-通过id查询", notes="设备-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MpiEquipment mpiEquipment = mpiEquipmentService.getById(id);
		if(mpiEquipment==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(mpiEquipment);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mpiEquipment
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MpiEquipment mpiEquipment) {
        return super.exportXls(request, mpiEquipment, MpiEquipment.class, "设备");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, MpiEquipment.class);
    }

}
