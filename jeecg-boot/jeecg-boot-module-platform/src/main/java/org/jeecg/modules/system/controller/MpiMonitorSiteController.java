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
import org.jeecg.modules.system.entity.MpiMonitorSite;
import org.jeecg.modules.system.service.IMpiMonitorSiteService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.system.vo.MpiSiteTreeModel;
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
 * @Description: 监测点
 * @Author: jeecg-boot
 * @Date:   2020-07-10
 * @Version: V1.0
 */
@Api(tags="监测点")
@RestController
@RequestMapping("/system/mpiMonitorSite")
@Slf4j
public class MpiMonitorSiteController extends JeecgController<MpiMonitorSite, IMpiMonitorSiteService> {
	@Autowired
	private IMpiMonitorSiteService mpiMonitorSiteService;

	 /**
	  * 查询数据 查出所有公司,并以树结构数据格式响应给前端
	  *
	  * @return
	  */
	 @RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
	 public Result<List<MpiSiteTreeModel>> queryTreeList(MpiMonitorSite monitorSite,
														 HttpServletRequest req) {
		 Result<List<MpiSiteTreeModel>> result = new Result<>();
		 try {
			 QueryWrapper<MpiMonitorSite> queryWrapper = QueryGenerator.initQueryWrapper(monitorSite, req.getParameterMap());
			 List<MpiMonitorSite> list = mpiMonitorSiteService.list(queryWrapper);

			 List<MpiSiteTreeModel> records = new ArrayList<>();
			 list.forEach(item -> {
				 records.add(new MpiSiteTreeModel(item));
			 });
			 log.info("records : " + JSON.toJSONString(records));
			 result.setResult(records);
			 result.setSuccess(true);
		 } catch (Exception e) {
			 log.error(e.getMessage(),e);
		 }
		 log.info("result : " + JSON.toJSONString(result));
		 return result;
	 }

	 /**
	  * 全查询
	  * @return
	  */
	 //@AutoLog(value = "ls_gateway-分页列表查询")
	 @ApiOperation(value="监测点-全查询", notes="监测点-全查询")
	 @GetMapping(value = "/allList")
	 public Result<?> queryAllList(MpiMonitorSite mpiMonitorSite,
								   HttpServletRequest req) {
		 QueryWrapper<MpiMonitorSite> queryWrapper = QueryGenerator.initQueryWrapper(mpiMonitorSite, req.getParameterMap());
		 List<MpiMonitorSite> list = mpiMonitorSiteService.list(queryWrapper);
		 return Result.ok(list);
	 }

	 /**
	  * 根据设备信息查询 监测点
	  * @param equipmentId 设备编号
	  * @return
	  */
	 @ApiOperation(value="监测点-根据设备信息查询 监测点", notes="监测点-根据设备信息查询 监测点")
	 @GetMapping(value = "/querySiteListByEquipment")
	 public Result<?> querySiteListByEquipment(String equipmentId) {
	 	log.info("id : " +equipmentId);
		 List<MpiMonitorSite> list = mpiMonitorSiteService.querySiteListByEquipment(equipmentId);
		 return Result.ok(list);
	 }
	
	/**
	 * 分页列表查询
	 *
	 * @param mpiMonitorSite
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "监测点-分页列表查询")
	@ApiOperation(value="监测点-分页列表查询", notes="监测点-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MpiMonitorSite mpiMonitorSite,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		//QueryWrapper<MpiMonitorSite> queryWrapper = QueryGenerator.initQueryWrapper(mpiMonitorSite, req.getParameterMap());
		QueryWrapper<MpiMonitorSite> queryWrapper = null;
		Page<MpiMonitorSite> page = new Page<MpiMonitorSite>(pageNo, pageSize);

		if (null != mpiMonitorSite.getComponentIds() && "" != mpiMonitorSite.getComponentIds()) {
			queryWrapper = new QueryWrapper<MpiMonitorSite>();
			queryWrapper.in("component_id" , mpiMonitorSite.getComponentIds().split(","));
		} else {
			queryWrapper = QueryGenerator.initQueryWrapper(mpiMonitorSite, req.getParameterMap());
		}

		IPage<MpiMonitorSite> pageList = mpiMonitorSiteService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mpiMonitorSite
	 * @return
	 */
	@AutoLog(value = "监测点-添加")
	@ApiOperation(value="监测点-添加", notes="监测点-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MpiMonitorSite mpiMonitorSite) {
		mpiMonitorSiteService.save(mpiMonitorSite);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mpiMonitorSite
	 * @return
	 */
	@AutoLog(value = "监测点-编辑")
	@ApiOperation(value="监测点-编辑", notes="监测点-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MpiMonitorSite mpiMonitorSite) {
		mpiMonitorSiteService.updateById(mpiMonitorSite);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "监测点-通过id删除")
	@ApiOperation(value="监测点-通过id删除", notes="监测点-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mpiMonitorSiteService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "监测点-批量删除")
	@ApiOperation(value="监测点-批量删除", notes="监测点-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mpiMonitorSiteService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "监测点-通过id查询")
	@ApiOperation(value="监测点-通过id查询", notes="监测点-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MpiMonitorSite mpiMonitorSite = mpiMonitorSiteService.getById(id);
		if(mpiMonitorSite==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(mpiMonitorSite);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mpiMonitorSite
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MpiMonitorSite mpiMonitorSite) {
        return super.exportXls(request, mpiMonitorSite, MpiMonitorSite.class, "监测点");
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
        return super.importExcel(request, response, MpiMonitorSite.class);
    }

}
