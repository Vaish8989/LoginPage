package com.uniquedeveloper.registration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.session;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response, String uemail) throws ServletException, IOException {
		String uemail=request.getParameter("username");
		String upwd=request.getParameter("password");
		HttpSession session= request.getSession();
		RequestDispatcher dispatcher = null;
	    try
	    {
	    	Class.forName("con.mysql.jdbc.Driver");
	    	Connection con = DriverManager.getConnection(getServletInfo("jdbc:mysql://localhost:3306/youtube?useSSL=false","root","MySQL"));
	    	PreparedStatement pst = con.prepareStatement("select*from users where uemail=?,upwd=?");
	    	pst.setString(1,uemail);
	    	pst.setString(2,upwd);
	    	ResultSet rs= pst.executeQuery();
	    	if(rs.next()) {
	    		session.setAttribute("name",rs.getString("uname"));
	    		dispatcher = request.getRequestDispatcher("index.jsp");
	    	}else {
	    		request.setAttribute("ststus","failed");
	    		dispatcher = request.getRequestDispatcher("Login.jsp");
	    	}
	    	dispatcher.forward(request,response);
	    }catch (Exception e){
	    	e.printStackTrace();
	    	e.printStackTrace();
       	}finally {
       		try {
       			String user;
       			Connection con = null;
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       	}
	}
	private String getServletInfo(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		return null;
	}
}