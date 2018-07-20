package com.athensoft.content.ad.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.content.ad.dao.AdPostDao;
import com.athensoft.content.ad.entity.AdAction;
import com.athensoft.content.ad.entity.AdPost;
import com.athensoft.content.ad.entity.AdPostStatus;
import com.athensoft.content.ad.entity.AdPostType;

@Service
public class AdPostService {
	
	@Autowired
	@Qualifier("adPostDaoJdbcImpl")
	private AdPostDao adPostDao; 
	
	public List<AdPost> getAdPostList(){
		return adPostDao.findAll();
	}
	
	public AdPost getAdPostByAdUUID(String adUUID){
		return adPostDao.findByUUID(adUUID);
	}
	
	public List<AdPost> getAdPostByFilter(String queryString){
		return adPostDao.findByFilter(queryString);
	}
	
	public void createAdPost(AdPost adpost){
		adPostDao.create(adpost);
	}
	
	public void updateAdPost(AdPost adpost){
		adPostDao.update(adpost);
	}
	
	public void updateAdPostGroup(List<AdPost> adPostList) {
		this.adPostDao.updateBatch(adPostList);
	}
	
	public long countAllAdpost(){
		return adPostDao.count();
	}
	
	public String[][] getData(List<AdPost> listObj, String action){
		int entryLength = listObj.size();
		final int COLUMN_NUM = 11;
		String[][] data = new String[entryLength][COLUMN_NUM];
		
		String field0 = "";	//check box
		String field1 = "";	//global id, adPostId
		String field2 = "";	//adText -> adTitle
//		String field3 = "";	//adImage
//		String field4 = "";	//adLink
//		String field5 = "";	//adUrl
		
		String field6 = "";	//adType
		String field7 = "";	//adOwnerId
		
		String field8 = "";	//create date
		String field9 = ""; //post date
		String field10 = ""; //expire date
		String field11 = ""; //modify date
		
		String field12 = "";	//adStatus
		String field13 = "";	//action
		
		for(int i=0; i<entryLength ; i++){			
//			field0 = "<input type='checkbox' name='id[]' value="+listObj.get(i).getGlobalId()+">";
			field0 = "<input type='checkbox' name='id[]' value="+listObj.get(i).getAdUUID()+">";
			field1 = listObj.get(i).getAdUUID()+"";
//			field2 = listObj.get(i).getAdText();
			field2 = listObj.get(i).getAdTitle();
//			field3 = listObj.get(i).getAdImage();//.substring(0,15);		//TODO
//			field4 = listObj.get(i).getAdLink();//.substring(0,15);			//TODO
//			field5 = listObj.get(i).getAdUrl();//.substring(0,15);			//TODO
			
//			field6 = listObj.get(i).getAdOwnerId()+"";
			field6 = listObj.get(i).getAcctName();
			
			String strObjectClass = (listObj.get(i).getAdType())+"";
			field7 = getObjectClass(strObjectClass);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			field8 = sdf.format(listObj.get(i).getCreateDate())+"";
			field9 = sdf.format(listObj.get(i).getPostDate())+"";
			field10 = sdf.format(listObj.get(i).getExpireDate())+"";
			field11 = sdf.format(listObj.get(i).getModifyDate())+"";
			
			
			
			int intAdStatus = listObj.get(i).getAdStatus();
			String[] adPostStatusPair = getStatusPair(intAdStatus);
			String adPostStatusKey = adPostStatusPair[0];
			String adStatus = adPostStatusPair[1];
			field12 = "<span class='label label-sm label-"+adPostStatusKey+"'>"+adStatus+"</span>";
			field13 = "<a href='"+getActionUrl(action)+"?adUUID="+field1+"' class='btn btn-xs default btn-editable'><i class='fa fa-pencil'></i> "+getActionName(action)+"</a>";
			
//			data[i][0] = field0;
//			data[i][1] = field1;
//			data[i][2] = field2;
//			data[i][3] = field3;
//			data[i][4] = field4;
//			data[i][5] = field5;
//			data[i][6] = field6;
//			data[i][7] = field7;
//			data[i][8] = field8;
//			data[i][9] = field9;
//			data[i][10] = field10;
//			data[i][11] = field11;
//			data[i][12] = field12;
//			data[i][13] = field13;
			
			data[i][0] = field0;
			data[i][1] = field1;
			data[i][2] = field2;
			data[i][3] = field6;	//
			data[i][4] = field7;
			data[i][5] = field8;
			data[i][6] = field9;
			data[i][7] = field10;
			data[i][8] = field11;
			data[i][9] = field12;
			data[i][10] = field13;
		}
		
		return data;
	}
	
	
	public String getObjectClass(String strObjectClass){
		
		int intObjectClass = Integer.parseInt(strObjectClass);
		String objectClass = "";
		switch(intObjectClass){
			case AdPostType.TEXT_BASED:
				objectClass = "文本广告";
				break;
			case AdPostType.IMAGE_BASED:
				objectClass = "图片广告";
				break;
			case AdPostType.LINK_BASED:
				objectClass = "链接广告";
				break;
			default: 
				objectClass = "未知类型";
				break;
		}
		
		return objectClass;
	}
	
	public String[] getStatusPair(int intStatus){
		String[] statusPair = new String[2];
		
		String status = "";
		String statusKey = "";
		switch(intStatus){
			case AdPostStatus.PUBLISHED: 
				status = "已发布";
				statusKey = "success";
				break;
			case AdPostStatus.WAIT_TO_POST: 
				status = "待发布";
				statusKey = "warning";
				break;
			case AdPostStatus.DELETED: 
				status = "已删除";
				statusKey = "default";
				break;
			case AdPostStatus.OUT_OF_DATE: 
				status = "已过期";
				statusKey = "info";
				break;
			case AdPostStatus.SUSPENDED: 
				status = "审查中";
				statusKey = "danger";
				break;
			default: 
				break;
		}
		
		statusPair[0]=statusKey;
		statusPair[1]=status;
		
		
		return statusPair;
	}
	
	public String getActionUrl(String action){
		String actionUrl = "";
		switch(action){
			case AdAction.EDIT:
				actionUrl = "/acp/ad/adpost/edit.html";	//adPost Edit
				break;
			case AdAction.DELETE:
				actionUrl = "/acp/ad/adpost/delete.html";	//adPost Delete
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
				actionName = "编辑";	//adPost Button Name - edit
				break;
			case AdAction.DELETE:
				actionName = "删除";	//adPost Button Name - delete
				break;
			case AdAction.VIEW:
				actionName = "查看";	//adPost Button Name - view
				break;
			default:
				actionName = "未定义";
		}
		return actionName;
	}
	
}
