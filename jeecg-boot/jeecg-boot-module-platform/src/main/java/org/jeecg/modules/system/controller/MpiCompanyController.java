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
import org.jeecg.modules.system.entity.MpiCompany;
import org.jeecg.modules.system.service.IMpiCompanyService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.system.vo.MpiCompanyGatewayTreeModel;
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
 * @Description: 公司表
 * @Author: jeecg-boot
 * @Date:   2020-07-08
 * @Version: V1.0
 */
@Api(tags="公司表")
@RestController
@RequestMapping("/system/mpiCompany")
@Slf4j
public class MpiCompanyController extends JeecgController<MpiCompany, IMpiCompanyService> {
	@Autowired
	private IMpiCompanyService mpiCompanyService;

	 /**
	  * <p>
	  * 公司搜索功能方法,根据关键字模糊搜索相关公司
	  * </p>
	  *
	  * @param keyWord
	  * @return
	  */
	 @RequestMapping(value = "/searchBy", method = RequestMethod.GET)
	 public Result<List<MpiCompanyGatewayTreeModel>> searchBy(@RequestParam(name = "keyWord", required = true) String keyWord) {
		 Result<List<MpiCompanyGatewayTreeModel>> result = new Result<List<MpiCompanyGatewayTreeModel>>();

		 QueryWrapper<MpiCompany> queryWrapper = new QueryWrapper<>();
		 queryWrapper.like("company_name", "%" + keyWord + "%");
		 List<MpiCompany> list = mpiCompanyService.list(queryWrapper);

		 List<MpiCompanyGatewayTreeModel> records = new ArrayList<>();
		 list.forEach(item -> {
			 records.add(new MpiCompanyGatewayTreeModel(item));
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
	 public Result<List<MpiCompanyGatewayTreeModel>> queryTreeList(MpiCompany mpiCompany,
																   HttpServletRequest req) {
		 Result<List<MpiCompanyGatewayTreeModel>> result = new Result<>();
		 try {
			 QueryWrapper<MpiCompany> queryWrapper = QueryGenerator.initQueryWrapper(mpiCompany, req.getParameterMap());
			 List<MpiCompany> list = mpiCompanyService.list(queryWrapper);
			 //List<MpiCompanyGatewayTreeModel> idList = new ArrayList<MpiCompanyGatewayTreeModel>();
			 List<MpiCompanyGatewayTreeModel> records = new ArrayList<>();
			 list.forEach(item -> {
				 records.add(new MpiCompanyGatewayTreeModel(item));
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
	 public Result<?> queryAllList(MpiCompany mpiCompany,
								   HttpServletRequest req) {
		 QueryWrapper<MpiCompany> queryWrapper = QueryGenerator.initQueryWrapper(mpiCompany, req.getParameterMap());
		 List<MpiCompany> list = mpiCompanyService.list(queryWrapper);
		 return Result.ok(list);
	 }
	
	/**
	 * 分页列表查询
	 *
	 * @param mpiCompany
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "公司表-分页列表查询")
	@ApiOperation(value="公司表-分页列表查询", notes="公司表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MpiCompany mpiCompany,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MpiCompany> queryWrapper = QueryGenerator.initQueryWrapper(mpiCompany, req.getParameterMap());
		Page<MpiCompany> page = new Page<MpiCompany>(pageNo, pageSize);
		IPage<MpiCompany> pageList = mpiCompanyService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mpiCompany
	 * @return
	 */
	@AutoLog(value = "公司表-添加")
	@ApiOperation(value="公司表-添加", notes="公司表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MpiCompany mpiCompany) {
		mpiCompanyService.save(mpiCompany);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mpiCompany
	 * @return
	 */
	@AutoLog(value = "公司表-编辑")
	@ApiOperation(value="公司表-编辑", notes="公司表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MpiCompany mpiCompany) {
		mpiCompanyService.updateById(mpiCompany);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "公司表-通过id删除")
	@ApiOperation(value="公司表-通过id删除", notes="公司表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mpiCompanyService.removeById(id);
		/*List<String> ids = new ArrayList<>();
		ids.add(id);
		mpiCompanyService.delGatewayEquipmentByCompany(ids);*/
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "公司表-批量删除")
	@ApiOperation(value="公司表-批量删除", notes="公司表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mpiCompanyService.removeByIds(Arrays.asList(ids.split(",")));
		/*this.mpiCompanyService.delGatewayEquipmentByCompany(Arrays.asList(ids.split(",")));*/
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "公司表-通过id查询")
	@ApiOperation(value="公司表-通过id查询", notes="公司表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MpiCompany mpiCompany = mpiCompanyService.getById(id);
		if(mpiCompany==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(mpiCompany);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mpiCompany
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MpiCompany mpiCompany) {
        return super.exportXls(request, mpiCompany, MpiCompany.class, "公司表");
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
        return super.importExcel(request, response, MpiCompany.class);
    }

}
