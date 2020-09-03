package org.jeecg.modules.system.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.MpiEquipment;
import org.jeecg.modules.system.entity.MpiEquipmentComponent;
import org.jeecg.modules.system.entity.MpiMonitorSite;
import org.jeecg.modules.system.service.IMpiEquipmentComponentService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.system.service.IMpiMonitorSiteService;
import org.jeecg.modules.system.vo.MpiComponentSiteTreeModel;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 设备组成
 * @Author: jeecg-boot
 * @Date:   2020-07-09
 * @Version: V1.0
 */
@Api(tags="设备组成")
@RestController
@RequestMapping("/system/mpiEquipmentComponent")
@Slf4j
public class MpiEquipmentComponentController extends JeecgController<MpiEquipmentComponent, IMpiEquipmentComponentService> {
	@Autowired
	private IMpiEquipmentComponentService mpiEquipmentComponentService;
	@Autowired
	private IMpiMonitorSiteService mpiMonitorSiteService;

	 /**
	  * 查询数据 查出所有公司,并以树结构数据格式响应给前端
	  *
	  * @return
	  */
	 @RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
	 public Result<List<MpiComponentSiteTreeModel>> queryTreeList(MpiEquipmentComponent mpiEquipmentComponent,
																			HttpServletRequest req) {
		 Result<List<MpiComponentSiteTreeModel>> result = new Result<>();
		 try {
			 QueryWrapper<MpiEquipmentComponent> queryWrapper = QueryGenerator.initQueryWrapper(mpiEquipmentComponent, req.getParameterMap());
			 List<MpiEquipmentComponent> list = mpiEquipmentComponentService.list(queryWrapper);

			 List<MpiComponentSiteTreeModel> records = new ArrayList<>();
			 list.forEach(item -> {
				 records.add(new MpiComponentSiteTreeModel(item));
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
	 @ApiOperation(value="公司表-全查询", notes="公司表-全查询")
	 @GetMapping(value = "/allList")
	 public Result<?> queryAllList() {
		 List<MpiEquipmentComponent> list = mpiEquipmentComponentService.allList();
		 return Result.ok(list);
	 }

	 /**
	  * 根据设备编号查询设备组成ids
	  * id 设备编号
	  * @return
	  */
	 @ApiOperation(value="设备组成-根据设备编号查询监测点ids", notes="设备组成-根据设备编号查询设备组成ids")
	 @GetMapping(value = "/getECIdsByEquipment")
	 public Result<?> getECIdsByEquipment(String id) {
		 log.info("equipmentId : " + id);
		 /*按设备检索设备组成信息*/
		 QueryWrapper<MpiEquipmentComponent> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("equipment_id", id);
		 List<MpiEquipmentComponent> list = mpiEquipmentComponentService.list(queryWrapper);
		 List<String> ids = list.stream().map(MpiEquipmentComponent::getId).collect(Collectors.toList()); // 设备组成编号
		 return Result.ok(ids);
	 }
	
	/**
	 * 分页列表查询
	 *
	 * @param mpiEquipmentComponent
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "设备组成-分页列表查询")
	@ApiOperation(value="设备组成-分页列表查询", notes="设备组成-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MpiEquipmentComponent mpiEquipmentComponent,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		// QueryWrapper<MpiEquipmentComponent> queryWrapper = QueryGenerator.initQueryWrapper(mpiEquipmentComponent, req.getParameterMap());
		QueryWrapper<MpiEquipmentComponent> queryWrapper = null;
		Page<MpiEquipmentComponent> page = new Page<MpiEquipmentComponent>(pageNo, pageSize);

		if (null != mpiEquipmentComponent.getEquipmentIds() && "" != mpiEquipmentComponent.getEquipmentIds()) {
			queryWrapper = new QueryWrapper<MpiEquipmentComponent>();
			queryWrapper.in("equipment_id" , mpiEquipmentComponent.getEquipmentIds().split(","));
		} else {
			queryWrapper = QueryGenerator.initQueryWrapper(mpiEquipmentComponent, req.getParameterMap());
		}

		IPage<MpiEquipmentComponent> pageList = mpiEquipmentComponentService.page(page, queryWrapper);

		//批量查询设备组成，设备，部件
		//step.1 先拿到全部的 EquipmentCompositionIds
		//step.2 通过 EquipmentCompositionIds，一次性查询设备组成，所需设备名称，部件名称
		List<String> ids = pageList.getRecords().stream().map(MpiEquipmentComponent::getId).collect(Collectors.toList());

		if(ids!=null && ids.size()>0){
			Map<String, List<String>>  names = mpiEquipmentComponentService.listEquipmentComponentByIds(ids);
			pageList.getRecords().forEach(item->{
				//TODO 存储部件名称，设备名称
				item.setEquipmentName(names.get(item.getId()).get(0)); //存入是第0位就为设备名称
				item.setComponentName(names.get(item.getId()).get(1)); //存入是第1位就为部件名称
			});
		}

		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mpiEquipmentComponent
	 * @return
	 */
	@AutoLog(value = "设备组成-添加")
	@ApiOperation(value="设备组成-添加", notes="设备组成-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MpiEquipmentComponent mpiEquipmentComponent) {
		mpiEquipmentComponentService.save(mpiEquipmentComponent);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mpiEquipmentComponent
	 * @return
	 */
	@AutoLog(value = "设备组成-编辑")
	@ApiOperation(value="设备组成-编辑", notes="设备组成-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MpiEquipmentComponent mpiEquipmentComponent) {
		mpiEquipmentComponentService.updateById(mpiEquipmentComponent);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备组成-通过id删除")
	@ApiOperation(value="设备组成-通过id删除", notes="设备组成-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mpiEquipmentComponentService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "设备组成-批量删除")
	@ApiOperation(value="设备组成-批量删除", notes="设备组成-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mpiEquipmentComponentService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备组成-通过id查询")
	@ApiOperation(value="设备组成-通过id查询", notes="设备组成-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MpiEquipmentComponent mpiEquipmentComponent = mpiEquipmentComponentService.getById(id);
		if(mpiEquipmentComponent==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(mpiEquipmentComponent);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mpiEquipmentComponent
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MpiEquipmentComponent mpiEquipmentComponent) {
        return super.exportXls(request, mpiEquipmentComponent, MpiEquipmentComponent.class, "设备组成");
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
        return super.importExcel(request, response, MpiEquipmentComponent.class);
    }

}
