package com.scg.base.controller.medicine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scg.base.db.model.po.Dictinfo;
import com.scg.base.db.model.po.Dicttype;
import com.scg.base.db.model.po.PageQuery;
import com.scg.base.db.model.po.Ypxx;
import com.scg.base.db.model.vo.YpxxCoustomVo;
import com.scg.base.db.model.vo.YpxxCustom;
import com.scg.base.process.context.Config;
import com.scg.base.process.result.DataGridResultInfo;
import com.scg.base.process.result.ExceptionResultInfo;
import com.scg.base.process.result.ResultInfo;
import com.scg.base.process.result.ResultUtil;
import com.scg.base.service.SysConfigService;
import com.scg.base.service.YpxxService;
import com.scg.util.ExcelExportSXXSSF;

@Controller
@RequestMapping(value="/medicine")
public class MedicineController {
	
	@Autowired
	private YpxxService ypxxService;
	@Autowired
	private SysConfigService sysConfigService;
	
	@RequestMapping(value="/querymedicine.action")
	public String showQuerymedicine(Model model) throws Exception{
		List<Dictinfo> list = sysConfigService.serchDictinfo("药品类别");
		model.addAttribute("dictinfos", list);
		List<Dictinfo> list2 = sysConfigService.serchDictinfo("药品交易状态");
		model.addAttribute("dictinfos2", list2);
		
		return "/base/medicine/querymedicine";
	}
	
	/**
	 * 根据条件查询列表
	 * @param ypxxCoustomVo
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/serch.action",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public DataGridResultInfo serchMedicine(YpxxCoustomVo ypxxCoustomVo,int page,int rows)throws Exception{
		PageQuery pageQuery = new PageQuery();
		//查询总条数
		int count = ypxxService.findCountYpxx(ypxxCoustomVo);
		//设置行数
		pageQuery.setPageParams(count, rows, page);
		ypxxCoustomVo.setPageQuery(pageQuery);
		//条件查询数据
		List<YpxxCustom> list = ypxxService.findYpxxTotal(ypxxCoustomVo);
		DataGridResultInfo resultInfo = new DataGridResultInfo();
		resultInfo.setTotal(count);
		resultInfo.setRows(list);
		return resultInfo;
	}
	
	/**
	 * 显示天价药品的要页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/showaddmedicine.action")
	public String showAddMedicine(Model model)throws Exception{
		List<Dictinfo> list = sysConfigService.serchDictinfo("药品类别");
		model.addAttribute("dictinfos", list);
		List<Dictinfo> list2 = sysConfigService.serchDictinfo("药品交易状态");
		model.addAttribute("dictinfos2", list2);
		List<Dictinfo> list3 = sysConfigService.serchDictinfo("药品质量导次");
		model.addAttribute("dictinfos3", list3);
		List<Dictinfo> list4 = sysConfigService.serchDictinfo("供货状态");
		model.addAttribute("dictinfos4", list4);
		return "/base/medicine/addmedicine";
	}
	
	/**
	 * 保存药品
	 * @param ypxxCoustomVo
	 * @return
	 */
	@RequestMapping(value="/submitMedicine.action",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo  saveMedicine(YpxxCoustomVo ypxxCoustomVo){ 
		ResultInfo resultInfo = null;
		Map<String, Object> map = new HashMap<String ,Object>();
		try {
			int count = ypxxService.saveMedicineYpxx(ypxxCoustomVo);
			if(count>0){
				map.put("id", "ypxxCustom_msg");
				resultInfo = ResultUtil.createSuccess(Config.MESSAGE, 1222, new Object[]{count,count});
				resultInfo.setSysdata(map);
				return resultInfo;
			}else{
				map.put("id", "ypxxCustom_msg");
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1223, new Object[]{count,count});
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
				return resultInfo;
			}
		} catch (Exception e) {
			if(e instanceof ExceptionResultInfo){
				ExceptionResultInfo ex = (ExceptionResultInfo) e;
				return ex.getResultInfo();
			}else {
			map.put("id", "ypxxCustom_msg");
			resultInfo = ResultUtil.createFail(Config.MESSAGE, 900, null);
			resultInfo.setSysdata(map);
			return resultInfo;
			}
		}
	}
	
	@RequestMapping(value="/removeypxx.action",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo delMedicineYpxx(String id){
		try {
			int count = ypxxService.delMedicineYpxx(id);
			if(count>0){
				return ResultUtil.createSuccess(Config.MESSAGE, 906, null);
			}else{
				return ResultUtil.createFail(Config.MESSAGE, 912, null);
			}
		} catch (Exception e) {
			if(e instanceof ExceptionResultInfo){
				ExceptionResultInfo ex = (ExceptionResultInfo) e;
				return ex.getResultInfo();
			}else{
				return ResultUtil.createFail(Config.MESSAGE, 407, null);
			}
		}
	}
	
	@RequestMapping(value="/showmdicineMsg.action")
	public String showMedicineMsg(String id,Model model) throws Exception{
		List<Dictinfo> list = sysConfigService.serchDictinfo("药品类别");
		model.addAttribute("dictinfos", list);
		List<Dictinfo> list2 = sysConfigService.serchDictinfo("药品交易状态");
		model.addAttribute("dictinfos2", list2);
		List<Dictinfo> list3 = sysConfigService.serchDictinfo("药品质量导次");
		model.addAttribute("dictinfos3", list3);
		Ypxx findypxxByid = ypxxService.findypxxByid(id);
		model.addAttribute("ypxx", findypxxByid);
		return "/base/medicine/updatemedicine";
	}
	
	
	@RequestMapping(value="/updateMedicine.action",produces="application/json;charset=UTF-8",method=RequestMethod.POST)
	@ResponseBody
	public ResultInfo updateMedicine(YpxxCoustomVo ypxxCoustomVo){
		ResultInfo resultInfo = null;
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			int count = ypxxService.updateMedicineYpxx(ypxxCoustomVo);
			if(count>0){
				resultInfo = ResultUtil.createSuccess(Config.MESSAGE, 906, null);
				map.put("id", "ypxxCustom_msg");
				resultInfo.setSysdata(map);
			}else {
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 912, null);
				map.put("id", "ypxxCustom_msg");
				resultInfo.setSysdata(map);
			}
			return resultInfo;
		} catch (Exception e) {
			if(e instanceof ExceptionResultInfo){
				ExceptionResultInfo ex = (ExceptionResultInfo) e;
				resultInfo = ex.getResultInfo();
			}else{
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 911, null);
			}
			return resultInfo;
		}
	}
	
	
	/**
	 * 导出excel的文件
	 * @param ypxxCoustomVo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/exportExcle.action",method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public ResultInfo exportExcel(YpxxCoustomVo ypxxCoustomVo) throws Exception{
		//创建服务器上的虚拟目录
		String filePath = "f:/export/ypxx/";
		
		//文件的前缀
		String filePrefix = "export";
		
		//-1表示关闭自动刷新 ，手动控制写磁盘的时机，其他数表示多少数据在内存保存，超过就写入磁盘。
		
		int flushRows = 100;
		
		//设置横向标题的名称title
		
		List<String> rowTitle = new ArrayList<String>();
		rowTitle.add("供货企业");
		rowTitle.add("流水号");
		rowTitle.add("通用名");
		rowTitle.add("剂型");
		rowTitle.add("规格");
		rowTitle.add("转换系数");
		rowTitle.add("生产企业");
		rowTitle.add("商品名称");
		rowTitle.add("中标价");
		rowTitle.add("交易状态");
		rowTitle.add("药品类别");
		
		//设置数据的对象的model的名称
		List<String> dbCode = new ArrayList<String>();
		dbCode.add("scqymc");
		dbCode.add("bm");
		dbCode.add("mc");
		dbCode.add("jx");
		dbCode.add("gg");
		dbCode.add("zhxs");
		dbCode.add("scqymc");
		dbCode.add("spmc");
		dbCode.add("zbjg");
		dbCode.add("jyztmc");
		dbCode.add("lbmc");
		//开始导出   执行一些workbook以及sheet对象的出时创建
		
		ExcelExportSXXSSF excelExportSXXSSF = ExcelExportSXXSSF.start(filePath, "/export/", filePrefix, rowTitle, dbCode, flushRows);
		
		List<YpxxCustom> list = ypxxService.findYpxxTotal(ypxxCoustomVo);
		
		excelExportSXXSSF.writeDatasByObject(list);
		
		String exportFile = excelExportSXXSSF.exportFile();
		
		
		return ResultUtil.createSuccess(Config.MESSAGE, 1300,new Object[]{list.size(),exportFile});
	}
}
