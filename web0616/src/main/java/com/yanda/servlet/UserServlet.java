package com.yanda.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.yanda.common.ConstNum;
import com.yanda.common.ServerResponse;
import com.yanda.service.IUserService;
import com.yanda.service.impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type=request.getParameter("type");
		
		if(type.contentEquals("login")) {
			//登录
			login(request, response);
			
		}else if(type.contentEquals("register")) {
			
		}else if(type.contentEquals("logout")) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	@SuppressWarnings("rawtypes")
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String username = request.getParameter("user_name");
		String password = request.getParameter("user_password");
		//登录业务逻辑
		IUserService userservice=new UserServiceImpl();
		ServerResponse se=userservice.loginLogic(username, password);
		
		if(se.isuccess()) {
			
			request.getSession().setAttribute(ConstNum.CURRENTUSER,username);
			
			response.sendRedirect("NewFile.jsp");
		}else {
			request.getSession().setAttribute("msg",se.getMsg());
			response.sendRedirect("login.jsp");
		}
		
		System.out.println(username);System.out.println(password);
		System.out.println(se.toString());
		
		//对象转json
		
		/*Gson gson=new Gson();
		String json=gson.toJson(se);
		
		PrintWriter pw=response.getWriter();
		pw.write(json);
		pw.close();
		
		response.sendRedirect("login.jsp");*/
		
	}

}
