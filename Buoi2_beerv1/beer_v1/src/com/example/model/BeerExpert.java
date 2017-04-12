package com.example.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BeerExpert extends HttpServlet {
	public List getBrands(String color) {
		List brands = new ArrayList();
		if (color.equals("amber")) {
		brands.add("Jack Amber");
		brands.add("Red Moose");
		}
		else {
		brands.add("Jail Pale Ale");
		brands.add("Gout Stout");
		}
		return(brands);
		}

}
