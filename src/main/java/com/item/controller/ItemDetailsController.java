package com.item.controller;

import java.io.IOException;
import java.net.Authenticator.RequestorType;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.item.model.ItemDetails;
import com.item.service.ItemDetailsService;
import com.item.service.impl.ItemDetailsServiceImpl;
import com.sun.net.httpserver.Request;
@WebServlet("/ItemDetailsController")
public class ItemDetailsController extends HttpServlet {

	@Resource(name = "jdbc/connection") 
	private DataSource dataSource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		
		switch (action) {
		
		case "add-item-details":
			 addItemDetails(request, response);
			break;
		case "load-item-details":
			loadItemDetails(request, response);
			break;
		case "show-item-details":
			showItemDetails(request, response);
	   
		}
	}

	private void showItemDetails(HttpServletRequest request, HttpServletResponse response) {
		ItemDetailsService itemDetailsService = new ItemDetailsServiceImpl(dataSource);
		int id = Integer.parseInt(request.getParameter("id"));
		ItemDetails itemDetails = itemDetailsService.loadItemDetails(id);
		request.setAttribute("itemDetailsData", itemDetails);
		
		try {
			request.getRequestDispatcher("/show-item-details.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	private void loadItemDetails(HttpServletRequest request, HttpServletResponse response) {
		ItemDetailsService itemDetailsService = new ItemDetailsServiceImpl(dataSource);
		int id = Integer.parseInt(request.getParameter("id"));
		ItemDetails itemDetails = itemDetailsService.loadItemDetails(id);
		request.setAttribute("itemDetailsData", itemDetails);
		
		try {
			request.getRequestDispatcher("/add-details.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} 
	}

	
	
	
	
	private void addItemDetails(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        String description = request.getParameter("description");
	        String itemIdStr = request.getParameter("itemId");

	        if (itemIdStr == null || itemIdStr.trim().isEmpty()) {
	            throw new IllegalArgumentException("Item ID is missing or empty");
	        }

	        int itemId = Integer.parseInt(itemIdStr);
	        ItemDetails itemDetails = new ItemDetails(description, itemId);

	        ItemDetailsService itemDetailsService = new ItemDetailsServiceImpl(dataSource);
	        boolean added = itemDetailsService.saveItemDetails(itemDetails);

	        if (added) {
	            // redirect or show confirmation
	        	response.getWriter().println("item details is saved");
	        } else {
	            response.getWriter().println("Error saving item details.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}














