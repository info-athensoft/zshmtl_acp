package com.athensoft.content.ad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.content.ad.dao.AdRecommendDao;
import com.athensoft.content.ad.entity.AdAction;
import com.athensoft.content.ad.entity.AdPostStatus;
import com.athensoft.content.ad.entity.AdRecommend;

@Service
public class AdRecommendService {
	
	@Autowired
	@Qualifier("adRecommendDaoJdbcImpl")
	private AdRecommendDao adRecommenDao;
	
	public List<AdRecommend> getAll(){
		return adRecommenDao.findAll();
	}
	
	public void createAdRecommend(AdRecommend adRecommend){
		adRecommenDao.create(adRecommend);
	}
	
	public String[][] getData(List<AdRecommend> listObj, String action){
		int entryLength = listObj.size();
		final int COLUMN_NUM = 7;
		String[][] data = new String[entryLength][COLUMN_NUM];
		
		String field0 = "";	//check box
		String field1 = "";	//adUUID
		String field2 = "";	//pageId
		String field3 = "";	//pageName
		String field4 = "";	//rcmdRank
		String field5 = "";	//rcmdStatus
		String field6 = "";	//action
		
		for(int i=0; i<entryLength ; i++){			
			field0 = "<input type='checkbox' name='id[]' value="+listObj.get(i).getGlobalId()+">";
			field1 = listObj.get(i).getAdUUID()+"";
			field2 = listObj.get(i).getPageId()+"";
			field3 = listObj.get(i).getPageName();
			field4 = listObj.get(i).getRcmdRank()+"";
			
			int intAdStatus = listObj.get(i).getRcmdStatus();
			String[] adStatusPair = getStatusPair(intAdStatus);
			String adStatusKey = adStatusPair[0];
			String adStatus = adStatusPair[1];
			field5 = "<span class='label label-sm label-"+adStatusKey+"'>"+adStatus+"</span>";
			field6 = "<a href='"+getActionUrl(action)+"?adUUID="+field1+"' class='btn btn-xs default btn-editable'><i class='fa fa-pencil'></i> "+getActionName(action)+"</a>";
			
			data[i][0] = field0;
			data[i][1] = field1;
			data[i][2] = field2;
			data[i][3] = field3;
			data[i][4] = field4;
			data[i][5] = field5;
			data[i][6] = field6;
		}
		
		return data;
	}
	
	public String getActionUrl(String action){
		String actionUrl = "";
		switch(action){
			case AdAction.EDIT:
				actionUrl = "/acp/ad/adrcmd/edit.html";	//adRecommend Edit
				break;
			case AdAction.DELETE:
				actionUrl = "/acp/ad/adrcmd/delete.html";	//adRecommend Delete
				break;
			default:
				break;
		}
		return actionUrl;
	}
	
	public String getActionName(String action){
		String actionName = "";
		switch(action){
			case AdAction.EDIT:
				actionName = "编辑";	//Button Name - edit
				break;
			case AdAction.DELETE:
				actionName = "删除";	//Button Name - delete
				break;
			case AdAction.VIEW:
				actionName = "查看";	//Button Name - view
				break;
			default:
				actionName = "未定义";
		}
		return actionName;
	}
	
	public String[] getStatusPair(int intStatus){
		String[] statusPair = new String[2];
		
		String status = "";
		String statusKey = "";
		switch(intStatus){
			case AdPostStatus.PUBLISHED: 
				status = "显示";
				statusKey = "success";
				break;
			case AdPostStatus.WAIT_TO_POST: 
				status = "不显示";
				statusKey = "default";
				break;
			default: 
				break;
		}
		statusPair[0]=statusKey;
		statusPair[1]=status;
		return statusPair;
	}
}
