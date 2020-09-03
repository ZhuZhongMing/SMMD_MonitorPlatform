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
import org.jeecg.modules.system.entity.MpiGateway;
import org.jeecg.modules.system.service.IMpiGatewayService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.system.vo.MpiGatewayEquipmentTreeModel;
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
 * @Description: 网关
 * @Author: jeecg-boot
 * @Date:   2020-07-09
 * @Version: V1.0
 */
@Api(tags="网关")
@RestController
@RequestMapping("/system/mpiGateway")
@Slf4j
public class MpiGatewayController extends JeecgController<MpiGateway, IMpiGatewayService> {
	@Autowired
	private IMpiGatewayService mpiGatewayService;

	 /**
	  * 根据公司编号查询网关ids
	  * id 公司编号
	  * @return
	  */
	 @ApiOperation(value="设备-根据公司编号查询网关ids", notes="设备-根据公司编号查询网关ids")
	 @GetMapping(value = "/getGatewayIdsByCompany")
	 public Result<?> getGatewayIdsByCompany(String id) {
		 log.info("companyId : " + id);
		 /*按公司检索网关信息*/
		 QueryWrapper<MpiGateway> queryWrapper = new QueryWrapper<>();
		 queryWrapper.eq("company_id", id);
		 List<MpiGateway> list = mpiGatewayService.list(queryWrapper);
		 List<String> ids = list.stream().map(MpiGateway::getId).collect(Collectors.toList());
		 return Result.ok(ids);
	 }

	 /**
	  * <p>
	  * 公司搜索功能方法,根据关键字模糊搜索相关公司
	  * </p>
	  *
	  * @param keyWord
	  * @return
	  */
	 @RequestMapping(value = "/searchBy", method = RequestMethod.GET)
	 public Result<List<MpiGatewayEquipmentTreeModel>> searchBy(@RequestParam(name = "keyWord", required = true) String keyWord) {
		 Result<List<MpiGatewayEquipmentTreeModel>> result = new Result<List<MpiGatewayEquipmentTreeModel>>();

		 QueryWrapper<MpiGateway> queryWrapper = new QueryWrapper<>();
		 queryWrapper.like("gateway_name", "%" + keyWord + "%");
		 List<MpiGateway> list = mpiGatewayService.list(queryWrapper);

		 List<MpiGatewayEquipmentTreeModel> records = new ArrayList<>();
		 list.forEach(item -> {
			 records.add(new MpiGatewayEquipmentTreeModel(item));
		 });

		 if (list.size() == 0) {
			 result.setSuccess(false);
			 result.setMessage("未查询匹配数据！");
			 return result;
		 }
		 result.setResult(records);
		 return result;
	 }

	 /**
	  * 查询数据 查出所有公司,并以树结构数据格式响应给前端
	  *
	  * @return
	  */
	 @RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
	 public Result<List<MpiGatewayEquipmentTreeModel>> queryTreeList(MpiGateway mpiGateway,
																	 HttpServletRequest req) {
		 Result<List<MpiGatewayEquipmentTreeModel>> result = new Result<>();
		 try {
			 QueryWrapper<MpiGateway> queryWrapper = QueryGenerator.initQueryWrapper(mpiGateway, req.getParameterMap());
			 List<MpiGateway> list = mpiGatewayService.list(queryWrapper);
			 //List<MpiCompanyGatewayTreeModel> idList = new ArrayList<MpiCompanyGatewayTreeModel>();
			 List<MpiGatewayEquipmentTreeModel> records = new ArrayList<>();
			 list.forEach(item -> {
				 records.add(new MpiGatewayEquipmentTreeModel(item));
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
	 @ApiOperation(value="网关-全查询", notes="网关-全查询")
	 @GetMapping(value = "/allList")
	 public Result<?> queryAllList(MpiGateway mpiGateway,
								   HttpServletRequest req) {
		 QueryWrapper<MpiGateway> queryWrapper = QueryGenerator.initQueryWrapper(mpiGateway, req.getParameterMap());
		 List<MpiGateway> list = mpiGatewayService.list(queryWrapper);
		 return Result.ok(list);
	 }
	
	/**
	 * 分页列表查询
	 *
	 * @param mpiGateway
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "网关-分页列表查询")
	@ApiOperation(value="网关-分页列表查询", notes="网关-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MpiGateway mpiGateway,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MpiGateway> queryWrapper = QueryGenerator.initQueryWrapper(mpiGateway, req.getParameterMap());
		Page<MpiGateway> page = new Page<MpiGateway>(pageNo, pageSize);
		IPage<MpiGateway> pageList = mpiGatewayService.page(page, queryWrapper);

		//批量查询网关的所属公司
		//step.1 先拿到全部的 gatewayIds
		//step.2 通过 gatewayIds，一次性查询网关所属公司的名称
		List<String> ids = pageList.getRecords().stream().map(MpiGateway::getId).collect(Collectors.toList());

		if(ids!=null && ids.size()>0){
			Map<String,String>  names = mpiGatewayService.listGatewayCompanyByIds(ids);
			pageList.getRecords().forEach(item->{
				//TODO 存储公司名称
				item.setCompanyName(names.get(item.getId()));
			});
		}

		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mpiGateway
	 * @return
	 */
	@AutoLog(value = "网关-添加")
	@ApiOperation(value="网关-添加", notes="网关-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MpiGateway mpiGateway) {
		mpiGatewayService.save(mpiGateway);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mpiGateway
	 * @return
	 */
	@AutoLog(value = "网关-编辑")
	@ApiOperation(value="网关-编辑", notes="网关-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MpiGateway mpiGateway) {
		mpiGatewayService.updateById(mpiGateway);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "网关-通过id删除")
	@ApiOperation(value="网关-通过id删除", notes="网关-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mpiGatewayService.removeById(id);
		/*List<String> ids = new ArrayList<>();
		ids.add(id);
		mpiGatewayService.deleteBatchByGatewayIds(ids);*/
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "网关-批量删除")
	@ApiOperation(value="网关-批量删除", notes="网关-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mpiGatewayService.removeByIds(Arrays.asList(ids.split(",")));
		//this.mpiGatewayService.deleteBatchByGatewayIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "网关-通过id查询")
	@ApiOperation(value="网关-通过id查询", notes="网关-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MpiGateway mpiGateway = mpiGatewayService.getById(id);
		if(mpiGateway==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(mpiGateway);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mpiGateway
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MpiGateway mpiGateway) {
        return super.exportXls(request, mpiGateway, MpiGateway.class, "网关");
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
        return super.importExcel(request, response, MpiGateway.class);
    }

}
