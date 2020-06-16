package com.yanda.service.impl;

import com.yanda.util.JdbcUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.yanda.common.ServerResponse;
import com.yanda.service.IUserService;

public class UserServiceImpl implements IUserService {

	public ServerResponse loginLogic(String username, String password) {
		// TODO Auto-generated method stub\
		
		//1.用户名非空判断
		if(username==null||username=="") {
			return ServerResponse.serverResponseByError("账号不能为空");
		}
		if(password==null||password=="") {
			return ServerResponse.serverResponseByError("密码不能为空");
		}
		//2.判断是否存在
		int num=JdbcUtil.executeUpdate("select COUNT(*) from user where username=\""+username+"\" ");
		System.out.println("num"+num);
		if(num!=0) {
			num=JdbcUtil.executeUpdate("select COUNT(*) from user where username=\""+username+"\" and password=\""+password+"\"");
			if(num!=0) {
				return ServerResponse.serverResponseBySuccess();
			}else {
				return ServerResponse.serverResponseByError("密码错误");
			}
		}else {
			return ServerResponse.serverResponseByError("账户不存在");
		}
		//3.根据用户名密码查询
		
		//4.返回ServerResponse
		
		
		
	}
	
	public static void main(String[] args) {
		IUserService userservice=new UserServiceImpl();
		ServerResponse se=userservice.loginLogic("好好", "123");
		
	}
}
