package com.example.web;

import com.example.model.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.BeerExpert;

public class BeerSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	   
    public BeerSelect() {
        
    }

    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	
	}

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String c = request.getParameter("color");
    	BeerExpert be = new BeerExpert();
    	List result = be.getBrands(c);
    
    	request.setAttribute("styles", result);
    	RequestDispatcher view = request.getRequestDispatcher("result.jsp");
    	view.forward(request, response);
    		
    	
//    	String c = request.getParameter("color");
//    	BeerExpert be = new BeerExpert();
//    	List result = be.getBrands(c);
//    	response.setContentType("text/html");
//    	PrintWriter out = response.getWriter();
//    	out.println("Beer Selection Advice<br>");
//    	Iterator it = result.iterator();
//    	while(it.hasNext()) {
//    	out.print("<br>try: " + it.next());
//    	}
    	
//    		response.setContentType("text/html");
//    		PrintWriter out = response.getWriter();
//    		out.println("Beer Selection Advice<br>");
//    		String c = request.getParameter("color");
//    		out.println("<br>Got beer color " + c);
//    		this.doGet(request, response);

	}

}
