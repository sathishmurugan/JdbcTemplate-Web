package com.sathish.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sathish.dao.StudentDAO;
import com.sathish.model.Student;
@WebServlet("/RegisterUserController")

public class RegisterUserController extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
//		out.print(name);
//		out.print(email);
//		out.print(password);
		Student student=new Student();
		student.setName(name);
		student.setEmail(email);
		student.setPassword(password);
		StudentDAO dao=new StudentDAO();
		dao.save(student);
		
		
	}

}
