package org.jeecg.modules.system.controller;

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
import org.jeecg.modules.system.entity.MpiComponent;
import org.jeecg.modules.system.entity.MpiEquipmentComponent;
import org.jeecg.modules.system.service.IMpiComponentService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.system.service.IMpiEquipmentComponentService;
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
 * @Description: 部件
 * @Author: jeecg-boot
 * @Date:   2020-07-09
 * @Version: V1.0
 */
@Api(tags="部件")
@RestController
@RequestMapping("/system/mpiComponent")
@Slf4j
public class MpiComponentController extends JeecgController<MpiComponent, IMpiComponentService> {
	@Autowired
	private IMpiComponentService mpiComponentService;
	@Autowired
	private IMpiEquipmentComponentService mpiEquipmentComponentService;

	 /**
	  * 全查询
	  * 当传入设备编号时，排除设备组成中该设备以添加过的部件
	  * @param equipmentId 设备编号
	  * @return
	  */
	 //@AutoLog(value = "ls_gateway-分页列表查询")
	 @ApiOperation(value="部件-全查询", notes="部件-全查询")
	 @GetMapping(value = "/allList")
	 public Result<?> queryAllList(@RequestParam(name="equipmentId", defaultValue="") String equipmentId) {
		 QueryWrapper<MpiComponent> queryWrapper = new QueryWrapper<>();
		 // log.info("============================================================> equipmentId : " + equipmentId +"   " +"".equals(equipmentId));
	 	if (!"".equals(equipmentId)) {
	 		QueryWrapper<MpiEquipmentComponent> queryWrapper1 = new QueryWrapper<>();
	 		queryWrapper1.eq("equipment_id", equipmentId);
			List<MpiEquipmentComponent> ecList = mpiEquipmentComponentService.list(queryWrapper1);
			List<String> ids = ecList.stream().map(MpiEquipmentComponent::getComponentId).collect(Collectors.toList());
			// log.info("============================================================> size : " + ecList.size());
			if (ecList.size() > 0) {
				queryWrapper.notIn("id", ids);
			}
		}
		List<MpiComponent> list = mpiComponentService.list(queryWrapper);
		return Result.ok(list);
	 }
	
	/**
	 * 分页列表查询
	 *
	 * @param mpiComponent
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "部件-分页列表查询")
	@ApiOperation(value="部件-分页列表查询", notes="部件-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MpiComponent mpiComponent,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MpiComponent> queryWrapper = QueryGenerator.initQueryWrapper(mpiComponent, req.getParameterMap());
		/*QueryWrapper<MpiComponent> queryWrapper = new QueryWrapper<>();
		if ("" != mpiComponent.getComponentImage() && null != mpiComponent.getComponentImage()) {
			// 前端传来设备组成id
			QueryWrapper<MpiEquipmentComponent> queryWrapper1 = new QueryWrapper<>();
			queryWrapper1.eq
		}*/

		Page<MpiComponent> page = new Page<MpiComponent>(pageNo, pageSize);
		IPage<MpiComponent> pageList = mpiComponentService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mpiComponent
	 * @return
	 */
	@AutoLog(value = "部件-添加")
	@ApiOperation(value="部件-添加", notes="部件-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MpiComponent mpiComponent) {
		mpiComponentService.save(mpiComponent);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mpiComponent
	 * @return
	 */
	@AutoLog(value = "部件-编辑")
	@ApiOperation(value="部件-编辑", notes="部件-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MpiComponent mpiComponent) {
		mpiComponentService.updateById(mpiComponent);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "部件-通过id删除")
	@ApiOperation(value="部件-通过id删除", notes="部件-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mpiComponentService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "部件-批量删除")
	@ApiOperation(value="部件-批量删除", notes="部件-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mpiComponentService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "部件-通过id查询")
	@ApiOperation(value="部件-通过id查询", notes="部件-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MpiComponent mpiComponent = mpiComponentService.getById(id);
		if(mpiComponent==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(mpiComponent);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mpiComponent
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MpiComponent mpiComponent) {
        return super.exportXls(request, mpiComponent, MpiComponent.class, "部件");
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
        return super.importExcel(request, response, MpiComponent.class);
    }

}
