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
import org.jeecg.modules.system.entity.MpiDataUpload;
import org.jeecg.modules.system.service.IMpiDataUploadService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

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
 * @Description: 设备上行数据
 * @Author: jeecg-boot
 * @Date:   2020-07-16
 * @Version: V1.0
 */
@Api(tags="设备上行数据")
@RestController
@RequestMapping("/system/mpiDataUpload")
@Slf4j
public class MpiDataUploadController extends JeecgController<MpiDataUpload, IMpiDataUploadService> {
	@Autowired
	private IMpiDataUploadService mpiDataUploadService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mpiDataUpload
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "设备上行数据-分页列表查询")
	@ApiOperation(value="设备上行数据-分页列表查询", notes="设备上行数据-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MpiDataUpload mpiDataUpload,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MpiDataUpload> queryWrapper = QueryGenerator.initQueryWrapper(mpiDataUpload, req.getParameterMap());
		Page<MpiDataUpload> page = new Page<MpiDataUpload>(pageNo, pageSize);
		IPage<MpiDataUpload> pageList = mpiDataUploadService.page(page, queryWrapper);

		//批量查询上行数据，设备，监测点
		//step.1 先拿到全部的 MpiDataUploadIds
		//step.2 通过 MpiDataUploadIds，一次性查询上行数据，所属设备名称，监测点名称
		List<String> ids = pageList.getRecords().stream().map(MpiDataUpload::getId).collect(Collectors.toList());

		if(ids!=null && ids.size()>0){
			Map<String, List<String>>  names = mpiDataUploadService.listEquipmentSiteByIds(ids);
			pageList.getRecords().forEach(item->{
				//TODO 存储设备名称，监测点名称
				item.setEquipmentName(names.get(item.getId()).get(0)); //存入是第0位就为设备名称
				item.setMonitorName(names.get(item.getId()).get(1)); //第1位为监测点名称
			});
		}

		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mpiDataUpload
	 * @return
	 */
	@AutoLog(value = "设备上行数据-添加")
	@ApiOperation(value="设备上行数据-添加", notes="设备上行数据-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MpiDataUpload mpiDataUpload) {
		mpiDataUploadService.save(mpiDataUpload);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mpiDataUpload
	 * @return
	 */
	@AutoLog(value = "设备上行数据-编辑")
	@ApiOperation(value="设备上行数据-编辑", notes="设备上行数据-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MpiDataUpload mpiDataUpload) {
		mpiDataUploadService.updateById(mpiDataUpload);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备上行数据-通过id删除")
	@ApiOperation(value="设备上行数据-通过id删除", notes="设备上行数据-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mpiDataUploadService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "设备上行数据-批量删除")
	@ApiOperation(value="设备上行数据-批量删除", notes="设备上行数据-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mpiDataUploadService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "设备上行数据-通过id查询")
	@ApiOperation(value="设备上行数据-通过id查询", notes="设备上行数据-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MpiDataUpload mpiDataUpload = mpiDataUploadService.getById(id);
		if(mpiDataUpload==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(mpiDataUpload);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mpiDataUpload
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MpiDataUpload mpiDataUpload) {
        return super.exportXls(request, mpiDataUpload, MpiDataUpload.class, "设备上行数据");
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
        return super.importExcel(request, response, MpiDataUpload.class);
    }

}
