package com.yanda.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.PreparedStatement;

public class JdbcUtil {
	private static String url = "";
	private static String user = "";
	private static String password = "";
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties properties = new Properties();
			InputStream inputStream = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
			properties.load(inputStream);
		
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			password = properties.getProperty("password");
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	private static void close(PreparedStatement pstmt,Connection con) {
		try {
			if(pstmt != null)
				pstmt.close();
			if(con != null)
				con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void close(ResultSet rs,PreparedStatement pstmt,Connection con) {
		try {
			if(rs != null)
				rs.close();
			close(pstmt,con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static int executeUpdate(String sql) {
		int result = 0;
		ResultSet rs = null;
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt,con);
		}
		return result;
	}

	public static <T> List<T> executeQuery(String sql,Class<T> clz,Object... params) {
		List<T> list = new ArrayList<T>();
		Connection con = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			if(params != null) {
				for(int i = 0; i < params.length; i++) {
					pstmt.setObject(i + 1, params[i]);
				}
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				T t = clz.newInstance();
				Field[] fields = clz.getDeclaredFields();
				for(Field field : fields) {
					try {
						field.setAccessible(true);
						field.set(t, rs.getObject(field.getName()));
					}catch(Exception e) {}
				}
				list.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs,pstmt,con);
		}
		return list;
	}
	
	public static void main(String[] args) {
		JdbcUtil a = null;
		int kk=a.executeUpdate("select COUNT(*) from user where username=\"好好\"");
		System.out.println("num"+kk);
	}
	
}
