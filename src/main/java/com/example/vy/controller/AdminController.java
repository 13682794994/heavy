package com.example.vy.controller;



import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.vy.bean.Admin;
import com.example.vy.bean.Bucket;
import com.example.vy.bean.Good;
import com.example.vy.bean.Record;
import com.example.vy.service.AdminService;

@Controller    

public class AdminController {

 

    @Autowired

    private AdminService userService;
    //public Admin aa;
    
    @RequestMapping(value="/existAdmin")
	@ResponseBody
	public String existAdmin(@RequestBody Map<String, Object> s){
		JSONObject jsonObj = new JSONObject(s);
		String m=userService.existAdmin((String)jsonObj.get("Aid"));
		//System.out.println(jsonObj.get("Aid"));
		//System.out.println(m);
		return m;
    }
    @RequestMapping(value="/addBucket")
	@ResponseBody
	public Good addBucket(@RequestBody Map<String, Object> s){
    	JSONObject jsonObj = new JSONObject(s);
    	
    	
    	Bucket bucket=new Bucket();
    	bucket.setAid((String)jsonObj.get("Aid"));
    	bucket.setGid((String)jsonObj.get("Gid"));
		Good m=userService.addBucket(bucket);
		
		return m;
    }
    @RequestMapping(value="/deleteBucket")
	@ResponseBody
	public boolean deleteBucket(@RequestBody Map<String, Object> s){
    	JSONObject jsonObj = new JSONObject(s);
    	
		boolean m=userService.deleteBucket((String)jsonObj.get("Gid"));
		
		return m;
    }
    @RequestMapping(value="/deleteGood")
	@ResponseBody
	public boolean deleteGood(@RequestBody Map<String, Object> s){
    	JSONObject jsonObj = new JSONObject(s);
    	
		boolean m=userService.deleteGood((String)jsonObj.get("Gid"));
		
		return m;
    }
    @RequestMapping(value="/buyBucket")
	@ResponseBody
	public boolean buyBucket(@RequestBody Map<String, Object> s){
    	JSONObject jsonObj = new JSONObject(s);
    	Record record=new Record();
    	record.setRid(jsonObj.get("Rid").toString());
    	record.setBuytime((String)jsonObj.get("Buytime"));
    	record.setAid((String)jsonObj.get("Aid"));
    	record.setGname((String)jsonObj.get("Gname"));
		boolean m=userService.buyBucket(record);
		return m;
    }
    @RequestMapping(value="/login")
	public String login(Admin admin ,Model model){
		
		String m=userService.login(admin);
		
		if(m.equals("卖家登录成功"))
		{
			List<Good> aGoods=userService.selectAllGoods();
			model.addAttribute("u", "卖家");
			model.addAttribute("good", aGoods);
			List<Record> records=userService.selectAllRecords();
			model.addAttribute("record",records);
			return "manage";
		}
		if(m.equals("登录成功")||m.equals("注册成功"))
		{
			List<Good> aGoods=userService.selectNOTGoods(admin.getAid());
			if(aGoods!=null)
			{
				model.addAttribute("good", aGoods);
			}
			
			List<Good> goods=userService.selectAllBuckets(admin.getAid());
			
			model.addAttribute("bucket", goods);
			List<Record> records=userService.selectMyRecords(admin.getAid());
			
			model.addAttribute("record",records);
			model.addAttribute("u", admin.getAid());
			return "shop";
		}
		if(m.equals("注册失败"))
		{
			model.addAttribute("error", "注册失败");
			return "index";
		}
		if(m.equals("登录失败"))
		{
			model.addAttribute("error", "用户名或密码错误，请重新输入！");
			return "index";
		}
		return null;
		
    }
    @RequestMapping(value="/addGood")
	public String addGood(Good good,Model model){

		if(userService.insertGood(good))
		{
			List<Good> aGoods=userService.selectAllGoods();
			model.addAttribute("u", "卖家");
			model.addAttribute("good", aGoods);
			List<Record> records=userService.selectAllRecords();
			model.addAttribute("record",records);
			return "manage";
		}
		return null;
    }
}

