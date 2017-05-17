package com.sathish.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sathish.model.Student;
import com.sathish.util.ConnectionUtil;
@WebServlet("/UserLoginController")
public class UserLoginController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		JdbcTemplate jdbcTemplate=ConnectionUtil.getJdbcTemplate();
		String sql="SELECT * FROM student_detials WHERE email='"+email+"' and password='"+password+"'";
		System.out.println(sql);
		Student user = null;
		
		try {
			user=jdbcTemplate.queryForObject(sql, (rs,rno)->
			
			{
				Student s=new Student();
				s.setEmail(rs.getString("email"));
				return s;
				
			}
			);
		} catch (EmptyResultDataAccessException e) {
			
			e.printStackTrace();
		}
		
		if ( user != null ){
			HttpSession session = request.getSession();
			session.setAttribute("loguser", user);
			response.sendRedirect("sucess.jsp");
		}
		else
		{
			response.sendRedirect("login.jsp");
		}

		
	}
	}


