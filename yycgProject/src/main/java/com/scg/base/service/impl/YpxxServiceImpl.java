package com.scg.base.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scg.base.db.dao.YpxxMapper;
import com.scg.base.db.dao.YpxxMapperCustom;
import com.scg.base.db.model.po.Ypxx;
import com.scg.base.db.model.po.YpxxExample;
import com.scg.base.db.model.vo.YpxxCoustomVo;
import com.scg.base.db.model.vo.YpxxCustom;
import com.scg.base.process.context.Config;
import com.scg.base.process.result.ResultInfo;
import com.scg.base.process.result.ResultUtil;
import com.scg.base.service.YpxxService;
import com.scg.util.UUIDBuild;



@Service
public class YpxxServiceImpl implements YpxxService{
	
	@Autowired
	private YpxxMapper ypxxMapper;
	
	@Autowired 
	private YpxxMapperCustom ypxxMapperCustom;

	@Override
	/**
	 * 查询药品的service
	 */
	public List<YpxxCustom> findYpxxTotal(YpxxCoustomVo ypxxCoustomVo) throws Exception {
			 List<YpxxCustom> list = ypxxMapperCustom.selectListMedicine(ypxxCoustomVo);
			
		return list;
	}

	@Override
	public int findCountYpxx(YpxxCoustomVo ypxxCoustomVo) throws Exception {
		int total = ypxxMapperCustom.queryTotalMedicine(ypxxCoustomVo);
		return total;
	}

	//根据生产厂家，商品名称，通用名，剂型，规格，转换系数查询药品
	public Ypxx findYpxxByExample(YpxxCustom ypxxCustom)throws Exception{
		YpxxExample example = new YpxxExample();
		YpxxExample.Criteria criteria = example.createCriteria();
		criteria.andScqymcEqualTo(ypxxCustom.getScqymc());
		criteria.andSpmcEqualTo(ypxxCustom.getSpmc());
		criteria.andMcEqualTo(ypxxCustom.getMc());
		criteria.andJxEqualTo(ypxxCustom.getJx());
		criteria.andGgEqualTo(ypxxCustom.getGg());
		criteria.andZhxsEqualTo(ypxxCustom.getZhxs());
		List<Ypxx> list = ypxxMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	//检测更新的内容是否为空
	public int  chekcInputNull(YpxxCustom ypxxCustom) throws Exception{
		ResultInfo resultInfo = null;
		Map<String, Object> map = new HashMap<String,Object>();
		String mc = ypxxCustom.getMc();
		String jx = ypxxCustom.getJx();
		String gg = ypxxCustom.getGg();
		String zhxs = ypxxCustom.getZhxs();
		String scqymc = ypxxCustom.getScqymc();
		String spmc = ypxxCustom.getSpmc();
		Float zbjg = ypxxCustom.getZbjg();
		String lb = ypxxCustom.getLb();
		String jyzt = ypxxCustom.getJyzt();
		String zlcc = ypxxCustom.getZlcc();
		if(mc==null ||mc==""){
			map.put("id", "ypxxCustom_mc");
			resultInfo = ResultUtil.createFail(Config.MESSAGE, 1211, null);
			resultInfo.setSysdata(map);
			ResultUtil.throwExcepion(resultInfo);
			return -1;
		}
		if(jx==null||jx==""){
			resultInfo = ResultUtil.createFail(Config.MESSAGE, 1212, null);
			map.put("id", "ypxxCustom_jx");
			resultInfo.setSysdata(map);
			ResultUtil.throwExcepion(resultInfo);
			return -1;
		}
		if(gg==null||gg==""){
			map.put("id", "ypxxCustom_gg");
			resultInfo = ResultUtil.createFail(Config.MESSAGE, 1213, null);
			resultInfo.setSysdata(map);
			ResultUtil.throwExcepion(resultInfo);
			return -1;
		}
		if(zhxs==null||zhxs==""){
			map.put("id", "ypxxCustom_zhxs");
			resultInfo = ResultUtil.createFail(Config.MESSAGE, 1214, null);
			resultInfo.setSysdata(map);
			ResultUtil.throwExcepion(resultInfo);
			return -1;
		}
		if(scqymc==null||scqymc==""){
			map.put("id", "ypxxCustom_scqymc");
			resultInfo = ResultUtil.createFail(Config.MESSAGE, 1216, null);
			resultInfo.setSysdata(map);
			ResultUtil.throwExcepion(resultInfo);
			return -1;
		}
		if(spmc==null||spmc==""){
			map.put("id", "ypxxCustom_spmc");
			resultInfo = ResultUtil.createFail(Config.MESSAGE, 1217, null);
			resultInfo.setSysdata(map);
			ResultUtil.throwExcepion(resultInfo);
			return -1;
		}
		if(zbjg==null||zbjg==0.0f){
			map.put("id", "ypxxCustom_zbjg");
			resultInfo = ResultUtil.createFail(Config.MESSAGE, 1218, null);
			resultInfo.setSysdata(map);
			ResultUtil.throwExcepion(resultInfo);
			return -1;
		}
		if(lb==null||lb==""){
			map.put("id", "ypxxCustom_lb");
			resultInfo = ResultUtil.createFail(Config.MESSAGE, 1219, null);
			resultInfo.setSysdata(map);
			ResultUtil.throwExcepion(resultInfo);
			return -1;
		}
		if(jyzt==null||jyzt==""){
			map.put("id", "ypxxCustom_jyzt");
			resultInfo = ResultUtil.createFail(Config.MESSAGE, 1220, null);
			resultInfo.setSysdata(map);
			ResultUtil.throwExcepion(resultInfo);
			return -1;
		}
		if(zlcc==null||zlcc==""){
			map.put("id", "ypxxCustom_zlcc");
			resultInfo = ResultUtil.createFail(Config.MESSAGE, 1221, null);
			resultInfo.setSysdata(map);
			ResultUtil.throwExcepion(resultInfo);
			return -1;
		}
		return 1;
	}
	
	
	
	@Override
	public int  saveMedicineYpxx(YpxxCoustomVo ypxxCoustomVo) throws Exception {
		YpxxCustom ypxxCustom = ypxxCoustomVo.getYpxxCustom();
		ResultInfo resultInfo = null;
		Map<String, Object> map = new HashMap<String,Object>();
		if(ypxxCustom!=null){
			Ypxx findYpxxByExample = this.findYpxxByExample(ypxxCustom);
			if(findYpxxByExample!=null){
				map.put("id", "ypxxCustom_msg");
				resultInfo = ResultUtil.createFail(Config.MESSAGE, 1224, null);
				resultInfo.setSysdata(map);
				ResultUtil.throwExcepion(resultInfo);
				return -1;
			}else {
				int checknum = this.chekcInputNull(ypxxCustom);
				if(checknum<0){
					return checknum;
				}else{
					ypxxCustom.setId(UUIDBuild.getUUID());
					int count = ypxxMapper.insert(ypxxCustom);
					return count;
				}
			}
		}else{
			map.put("id", "ypxxCustom_msg");
			resultInfo = ResultUtil.createFail(Config.MESSAGE, 1210, null);
			resultInfo.setSysdata(map);
			ResultUtil.throwExcepion(resultInfo);
			return -1;
		}
	}

	/**
	 * 删除药品
	 */
	@Override
	public int delMedicineYpxx(String id) throws Exception {
		Ypxx ypxx = ypxxMapper.selectByPrimaryKey(id);
		if(ypxx==null){
			ResultUtil.createFail(Config.MESSAGE, 406, null);
			return -1;
		}else{
			int count = ypxxMapper.deleteByPrimaryKey(id);
			return count;
		}
	}

	
	@Override
	public int updateMedicineYpxx(YpxxCoustomVo ypxxCoustomVo) throws Exception {
		ResultInfo resultInfo = null;
		Map<String, Object> map = new HashMap<String,Object>();
		if(ypxxCoustomVo.getYpxxCustom()==null){
			//判断传入的数据是否为空
			ResultUtil.createFail(Config.MESSAGE, 1210, null);
			return -1;
		}else{
			//不为空
			YpxxCustom ypxxCustom = ypxxCoustomVo.getYpxxCustom();
			//检查有没有内容没有输入全
			int checknum = chekcInputNull(ypxxCustom);
			if(checknum<0)
				return checknum;
			//通过提交表单的id查询是否存在这个药品
			Ypxx ypxx = ypxxMapper.selectByPrimaryKey(ypxxCustom.getId());
			if(ypxx==null){
				//如果不存在抛出异常
				ResultUtil.createFail(Config.MESSAGE, 406, null);
				return -1;
			}else{
				//存在  根据生产厂家，商品名称，通用名，剂型，规格，转换系数查询药品
				 Ypxx ypxx2 = this.findYpxxByExample(ypxxCustom);
				 YpxxExample example = new YpxxExample();
				 YpxxExample.Criteria criteria = example.createCriteria();
				 if(ypxx2!=null){
					//用户已存在判断两个值的id是否一致
					String id = ypxx.getId(); 
					String id2 = ypxx2.getId();
					System.err.println(id);
					System.err.println(id2);
					if(id.equals(id2)){
						//如何id相等说明同一个药品没有更新生产厂家，商品名称，通用名，剂型，规格，转换系数 然后更新
						criteria.andIdEqualTo(ypxxCustom.getId());
						int count = ypxxMapper.updateByExampleSelective(ypxxCustom, example);
						return count;
					}else{
						map.put("id", "ypxxCustom_msg");
						resultInfo = ResultUtil.createFail(Config.MESSAGE, 1227, null);
						resultInfo.setSysdata(map);
						ResultUtil.throwExcepion(resultInfo);
						return -1;
					}
				 }else{
					//用户不存在就说明没有修改生产厂家，商品名称，通用名，剂型，规格，转换系数直接更新
					 criteria.andIdEqualTo(ypxxCustom.getId());
					 int count = ypxxMapper.updateByExampleSelective(ypxxCustom, example);
					 return count;
				 }
			}
		}
	}

	@Override
	public Ypxx findypxxByid(String id) throws Exception {
		Ypxx ypxx = ypxxMapper.selectByPrimaryKey(id);
		if(ypxx==null){
			ResultUtil.createFail(Config.MESSAGE, 406, null);
			return null;
		}else{
			return ypxx;
		}
	}
}
