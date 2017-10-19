//package com.sbd.hibernate.controller;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//public class UserControllerServlet extends HttpServlet{
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
//		
//		String userName = request.getParameter("userName");
//		String password = request.getParameter("password1");
//		String email = request.getParameter("email");
//		String major = request.getParameter("major");
//		
//		HttpSession session = request.getSession(true);
//		try {
//			UserDAO userDAO = new UserDAO();
//			userDAO.addUserDetails(userName, password, email, major);
//			response.sendRedirect("Success");
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//	}
//}
