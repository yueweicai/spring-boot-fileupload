package com.orilore.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
public class FileUploadCtrl {
	@RequestMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile file,HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("files");
		String name = file.getOriginalFilename();
		String ext = name.substring(name.lastIndexOf("."));
		name= System.currentTimeMillis()+ext;
		path = path+"\\"+name;
		File dest = new File(path);
		try {	
			file.transferTo(dest);
			return "files/"+name;
		}catch (Exception e) {
			return "";
		}
	}
	@RequestMapping("/uploads")
	public List<String> uploads(@RequestParam("file[]") MultipartFile[] files,HttpServletRequest request){
		List<String> list = new ArrayList<String>();
		for(int i=0;i<files.length;i++){
			String path = request.getSession().getServletContext().getRealPath("files");
			String name = files[i].getOriginalFilename();
			String ext = name.substring(name.lastIndexOf("."));
			Date now = new Date();
			name = now.getTime()+"-"+i+ext;
			path = path+"\\"+name;
			File dest = new File(path);
			try {
				files[i].transferTo(dest);
				list.add("files/"+name);
			}catch (Exception e) {
				continue;
			}
		}
		return list;
	}
}
