package com.athensoft.content.ad.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.athensoft.content.ad.dao.AdRequestDao;
import com.athensoft.content.ad.entity.AdAction;
import com.athensoft.content.ad.entity.AdRequest;
import com.athensoft.content.ad.entity.AdRequestStatus;
import com.athensoft.content.ad.entity.AdRequestType;

@Service
public class AdRequestService {
	
	@Autowired
	@Qualifier("adRequestDaoJdbcImpl")
	private AdRequestDao adRequestDao;
	
	public List<AdRequest> getAllAdRequest(){
		return adRequestDao.findAll();
	}
	
	public List<AdRequest> getAdRequestByStatus(int requestStatus){
		return adRequestDao.findByStatus(requestStatus);
	}
	
	public List<AdRequest> getAdRequestByType(int requestType){
		return adRequestDao.findByStatus(requestType);
	}
	
	public List<AdRequest> getLastestApplyRequest(){
		return adRequestDao.findByType(AdRequestType.CREATE);
	}
	
	public List<AdRequest> getLastestOtherRequest(){
		String queryString = " AND request_type IN (1,3,4,5)";
		return adRequestDao.findByQuery(queryString);
	}
	
	
	public List<AdRequest> getAdRequestByAcctName(String acctName){
		return adRequestDao.findByAcctName(acctName);
	}
	
	public void updateStatus(AdRequest adRequest){
		adRequestDao.updateStatus(adRequest);
	}
	
	public String[][] getData(List<AdRequest> listObj, String action){
		int entryLength = listObj.size();
		final int COLUMN_NUM = 10;
		String[][] data = new String[entryLength][COLUMN_NUM];
		
		String field0 = "";	//check box
		String field1 = "";	//global id, adRequestID
		String field2 = "";	//requestType
		String field3 = "";	//requestName
		String field4 = "";	//requestPhone
		String field5 = "";	//requestSubject
		String field6 = "";	//requestMsg
		String field7 = "";	//requestDate
		String field8 = "";	//requestStatus
		String field9 = "";//action
		
		for(int i=0; i<entryLength ; i++){			
			field0 = "<input type='checkbox' name='id[]' value="+listObj.get(i).getGlobalId()+">";
//			field0 = "<input type='checkbox' name='id[]' value="+listObj.get(i).getAdUUID()+">";
			field1 = listObj.get(i).getGlobalId()+"";
			String strObjectClass = (listObj.get(i).getRequestType())+"";
			field2 = getObjectClass(strObjectClass);
			field3 = listObj.get(i).getRequestName();
			field4 = listObj.get(i).getRequestPhone();
			field5 = listObj.get(i).getRequestSubject();
			field6 = listObj.get(i).getRequestMessage();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			field7 = sdf.format(listObj.get(i).getRequestDate())+"";
			int intRequestStatus = listObj.get(i).getRequestStatus();
			String[] adPostStatusPair = getStatusPair(intRequestStatus);
			String adPostStatusKey = adPostStatusPair[0];
			String adStatus = adPostStatusPair[1];
			field8 = "<span class='label label-sm label-"+adPostStatusKey+"'>"+adStatus+"</span>";
			field9 = "<a href='"+getActionUrl(action)+"?globalId="+field1+"' class='btn btn-xs default btn-editable'><i class='fa fa-pencil'></i> "+getActionName(action)+"</a>";
			
			data[i][0] = field0;
			data[i][1] = field1;
			data[i][2] = field2;
			data[i][3] = field3;
			data[i][4] = field4;
			data[i][5] = field5;
			data[i][6] = field6;
			data[i][7] = field7;
			data[i][8] = field8;
			data[i][9] = field9;
		}
		
		return data;
	}
	
	public String getActionUrl(String action){
		String actionUrl = "";
		switch(action){
			case AdAction.EDIT:
				actionUrl = "/acp/ad/adrequest/edit.html";	//adPost Edit
				break;
			case AdAction.DELETE:
				actionUrl = "/acp/ad/adrequest/delete.html";	//adPost Delete
				break;
			default:
				break;
		}
		return actionUrl;
	}
	
	public String getObjectClass(String strObjectClass){
		
		int intObjectClass = Integer.parseInt(strObjectClass);
		String objectClass = "";
		switch(intObjectClass){
			case AdRequestType.INQUIRY:
				objectClass = "广告咨询";
				break;
			case AdRequestType.CREATE:
				objectClass = "广告新增申请";
				break;
			case AdRequestType.UPDATE:
				objectClass = "广告变更申请";
				break;
			case AdRequestType.REVOKE:
				objectClass = "广告撤回申请";
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
			case AdRequestStatus.APPLIED: 
				status = "已申请";
				statusKey = "success";
				break;
			case AdRequestStatus.PROCESSING: 
				status = "处理中";
				statusKey = "warning";
				break;
			case AdRequestStatus.COMPLETE: 
				status = "已处理";
				statusKey = "default";
				break;
			case AdRequestStatus.REJECTED: 
				status = "已拒绝";
				statusKey = "danger";
				break;
			default: 
				break;
		}
		
		statusPair[0]=statusKey;
		statusPair[1]=status;
		
		
		return statusPair;
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
