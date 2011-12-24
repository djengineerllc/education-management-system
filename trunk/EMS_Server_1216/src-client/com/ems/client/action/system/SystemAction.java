package com.ems.client.action.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ems.biz.applyonline.service.IApplyOnlineService;
import com.ems.common.dao.ICommonDAO;
import com.ems.common.model.vo.DicVO;
import com.ems.system.client.DirectAction;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

import conf.hibernate.CodeType;
import conf.hibernate.Project;


public class SystemAction extends DirectAction {
	
	@DirectMethod
	public Date getSystemTime() {
		return new Date();
	}
	
	@DirectMethod
	public List<DicVO> getDicData(String dicType) {
		List<DicVO> dicVOList = new ArrayList<DicVO>();
		if("project".equals(dicType)){
			IApplyOnlineService applyOnlineService = (IApplyOnlineService)this.getBean("applyOnlineService");
			try{
				List<Project> projects = applyOnlineService.findAllProject();
				for(int i=0;i<projects.size();i++){
					Project project = (Project)projects.get(i);
					dicVOList.add(new DicVO(project.getId(), "project", "S"+i,project.getProjectName() , project.getProjectName(), null, null));
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			ICommonDAO commonDAO = (ICommonDAO)this.getBean("commonDAO");
			List<CodeType> codes = commonDAO.getCodeTypeByType(dicType);
			if(codes != null && codes.size()>0){
				for(int i=0;i<codes.size();i++){
					CodeType code = codes.get(i);
					dicVOList.add(new DicVO(code.getId(), dicType, code.getCodeKey() , code.getCodeValue(), code.getCodeName(),code.getCodeDes(), 
							code.getCodeGroup()));
				}
			}
		}
		
		return dicVOList;
	}
}