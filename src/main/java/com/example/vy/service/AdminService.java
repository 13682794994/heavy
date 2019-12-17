package com.example.vy.service;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.vy.bean.Admin;
import com.example.vy.bean.Bucket;
import com.example.vy.bean.Good;
import com.example.vy.bean.Record;
import com.example.vy.mapper.AdminMapper;





@Service

@Transactional(rollbackFor = RuntimeException.class)

public class AdminService {

 

    @Autowired

    private AdminMapper adminMapper;

    /*/**

     * 注册

     * @param user 参数封装

     * @return Result

     */

    /*public Result regist(Admin admin) {

        Result result = new Result();

        result.setSuccess(false);

        result.setDetail(null);

        try {

            Admin existUser = adminMapper.findUserByName(admin.getAid());

            if(existUser != null){

                //如果用户名已存在

                result.setMsg("用户名已存在");

 

            }else{

                adminMapper.regist(admin);

                //System.out.println(user.getId());

                result.setMsg("注册成功");

                result.setSuccess(true);

                result.setDetail(admin);

            }

        } catch (Exception e) {

            result.setMsg(e.getMessage());

            e.printStackTrace();

        }

        return result;

    }*/

    /**

     * 登录

     * @param admin 用户名和密码

     * @return boolean

     */

    public String existAdmin(String Aid) {

        try {

            String a= adminMapper.existAdmin(Aid);
            if(a==null) {
            	return "注册";
            }
            if(a.equals("ADMIN")||a.equals("admin")) {
                return "admin";
            }
            return "用户登录";

        } catch (Exception e) {
 
            e.printStackTrace();

        }

        return null;

    }

	public String login(Admin admin) {
		
		try {
			String s= adminMapper.existAdmin(admin.getAid());
			if(s==null){
				if(adminMapper.signin(admin))
					return "注册成功";
				else
					return "注册失败";
			}
			else {
				String a= adminMapper.login(admin);
				if(a==null) {
	            	return "登录失败";
	            }
	            if(a.equals("ADMIN")||a.equals("admin")) {
	                return "卖家登录成功";
	            }
	            else {
	            	return "登录成功";
	            }
			}

        } catch (Exception e) {
 
            e.printStackTrace();

        }
		return null;
	}

	public List<Good> selectAllGoods() {
		
		try {
			List<Good> s= adminMapper.selectAllGoods();
			return s;

        } catch (Exception e) {
 
            e.printStackTrace();

        }
		return null;
	}


	public List<Good> selectAllBuckets(String m) {
		try {
			List<Bucket> s= adminMapper.selectAllBucket(m);
			
			List<Good> goods=new ArrayList<Good>();
			
			for(int i=0;i<s.size();i++)
			{
				
				Good good=(Good)adminMapper.selectMyGoods(s.get(i).getGid());
				goods.add(good);
			}

			return goods;

        } catch (Exception e) {
 
            e.printStackTrace();

        }
		return null;
	}

	public List<Record> selectAllRecords() {
		try {
			List<Record> s= adminMapper.selectAllRecords();
			return s;

        } catch (Exception e) {
 
            e.printStackTrace();

        }
		return null;
	}

	public List<Record> selectMyRecords(String m) {
		try {
			List<Record> s= adminMapper.selectMyRecord(m);
			return s;

        } catch (Exception e) {
 
            e.printStackTrace();

        }
		return null;
	}

	public Good addBucket(Bucket s) {
		Good good=null;
		try {
			adminMapper.insertBucket(s);
			good=(Good)adminMapper.selectMyGoods(s.getGid());

        } catch (Exception e) {
 
            e.printStackTrace();

        }
		return good;
	}

	public List<Good> selectNOTGoods(String m) {
		
		try {
			/*List<Bucket> s= adminMapper.selectAllBucket(m);
            List<String> goods=new ArrayList<String>();
			for(int i=0;i<s.size();i++)
			{
				
				goods.add("\""+ s.get(i).getGid()+"\"");
			}
			System.out.println(StringUtils.strip(goods.toString(), "[]"));*/
			List<Good> good=adminMapper.selectNotGoods(m);
			return good;
        } catch (Exception e) {
 
            e.printStackTrace();

        }
		
		return null;
	}

	public boolean deleteBucket(String string) {
		
		try {
			
			boolean s=adminMapper.deleteBucket(string);
			return s;
        } catch (Exception e) {
 
            e.printStackTrace();

        }
		
		return false;
	}

	public boolean buyBucket(Record record) {
		
		
		try {
			
			return adminMapper.insertRecord(record);

        } catch (Exception e) {
 
            e.printStackTrace();

        }
		return false;
	}

	public boolean insertGood(Good good) {
		
		try {
			
			return adminMapper.insertGood(good);

        } catch (Exception e) {
 
            e.printStackTrace();

        }
		return false;
	}

	public boolean deleteGood(String string) {
		
		try {
			adminMapper.deleteBucket(string);
			return adminMapper.deleteGood(string);
        } catch (Exception e) {
 
            e.printStackTrace();

        }
		return false;
	}
}

