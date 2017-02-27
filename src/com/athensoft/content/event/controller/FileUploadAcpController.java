package com.athensoft.content.event.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class FileUploadAcpController {
	
	private static final Logger logger = Logger.getLogger(NewsAcpController.class);
	
	public static final String FileDir = "D:\\Shared\\2017_athensoft_website\\fileupload";
//	private static final String RESP_SUCCESS = "{\"jsonrpc\" : \"2.0\", \"result\" : \"success\", \"id\" : \"id\"}";
	private static final String RESP_SUCCESS = "{\"jsonrpc\" : \"2.0\", \"result\" : \"OK\", \"id\" : \"id\"}";
	private static final String RESP_ERROR = "{\"jsonrpc\" : \"2.0\", \"error\" : {\"code\": 101, \"message\": \"Failed to open input stream.\"}, \"id\" : \"id\"}";
	public static final int BUF_SIZE = 2 * 1024;
	
	private int chunk;
	private int chunks;
	private String name;
	private String user;
	private String time;

	
	
//	@RequestMapping(value="/content/fileUpload",produces="text/html")
	@RequestMapping(value="/content/fileUpload",produces="application/json")
//	@RequestMapping(value="/content/fileUpload",produces="text/plain")
	@ResponseBody
	public Map<String,Object> fileUpload(HttpServletRequest req){
		logger.info("entering /content/fileUpload");
		
		String responseString = RESP_SUCCESS;
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		logger.info("isMultipart:" + isMultipart);
		
		if(isMultipart){
			ServletFileUpload upload = new ServletFileUpload();
			try {
				FileItemIterator iter = upload.getItemIterator(req);
				while (iter.hasNext()) {
				    FileItemStream item = iter.next();
				    InputStream input = item.openStream();

				    
				    
				    // Handle a form field.
				    if(item.isFormField()){
				        String fileName = item.getFieldName();
				        String value = Streams.asString(input);

				        if("name".equals(fileName)){
				        	this.name = value;
				        }
				        else if("chunks".equals(fileName)){
				        	this.chunks = Integer.parseInt(value);
				        }else if("chunk".equals(fileName)){
				        	this.chunk = Integer.parseInt(value);
				        }else if("user".equals(fileName)){
				        	this.user = value;
				        }else if("time".equals(fileName)){
				        	this.time = value;
				        }
				        logger.info("name:" + this.name);
				        logger.info("chunks:" + this.chunks);
				        logger.info("chunk:" + this.chunk);
				        logger.info("user:" + this.user);
				        logger.info("time:" + this.time);
				    }
				    
				    // Handle a multi-part MIME encoded file.
				    else {
//				    	String fileDir = req.getSession().getServletContext().getRealPath("/")+FileDir;
				    	
				    	String fileDir = FileDir;
//						
				    	File dstFile = new File(fileDir);
						if (!dstFile.exists()){
							dstFile.mkdirs();
						}
//						
						File dst = new File(dstFile.getPath()+ "/" + this.name);
						
						logger.info("fileDir:" + fileDir);
						logger.info("fileName:" + this.name);
						
				        saveUploadFile(input, dst);
				    }
				}
			}
			catch (Exception e) {
				responseString = RESP_ERROR;
				e.printStackTrace();
			}
		}
		
		// Not a multi-part MIME request.
		else {
			responseString = RESP_ERROR;
		}
		
		logger.info("responseString:" + responseString);
		
		ModelAndView mav = new ModelAndView();
		
		//view
		String viewName = "events/event_news_edit";
		mav.setViewName(viewName);
		
		//data
		Map<String, Object> model = mav.getModel();
		
		
		//response.setContentType(JSON);
		//byte[] responseBytes = responseString.getBytes();
		//response.setContentLength(responseBytes.length);
		//ServletOutputStream output = response.getOutputStream();
		//output.write(responseBytes);
		//output.flush();
		
		model.put("jsonrpc", "2.0");
		model.put("result", "OK");
		model.put("id", "id");
		
		logger.info("leaving /content/fileUpload");
		return model;
	}
	
	private void saveUploadFile(InputStream input, File dst) throws IOException {
		OutputStream out = null;
		try {
			if (dst.exists()) {
				out = new BufferedOutputStream(new FileOutputStream(dst, true), BUF_SIZE);
			} else {
				out = new BufferedOutputStream(new FileOutputStream(dst), BUF_SIZE);
			}

			byte[] buffer = new byte[BUF_SIZE];
			int len = 0;
			while ((len = input.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
