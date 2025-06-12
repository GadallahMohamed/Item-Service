package com.item.controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.item.model.Item;
import com.item.service.ItemService;
import com.item.service.impl.ItemServiceImpl;


@WebServlet("/itemController")
public class itemController extends HttpServlet {
       
	@Resource(name = "jdbc/connection") 
	private DataSource dataSource;

	private ItemService itemService;

	@Override
	public void init() throws ServletException{
		itemService = new ItemServiceImpl(dataSource);

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(Objects.isNull(action)) {
			action = "load-items";
		}
		switch (action) {
		case "add-item":
			addItem(request, response);
			break;
		case "remove-item":
			removeItem(request, response);
			break;
		case "update-item":
			updateItem(request, response);
		case "load-item":
			loadItem(request, response);
			break;
		case "load-items":
			loadItems(request, response);		
			break;
		default:
			loadItems(request, response);
		    
				
			
		}
	}

	
	private void updateItem(HttpServletRequest request, HttpServletResponse response) {
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		String itemName = request.getParameter("itemName");
		double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
		int itemTotalNumber = Integer.parseInt(request.getParameter("itemTotalNumber"));
		
		Item item = new Item(itemId, itemName,itemPrice,itemTotalNumber);
		
		
		boolean updatedItem = itemService.updateItem(item);
		if(updatedItem) {
			loadItems(request, response);
		}
	}


	private void loadItems(HttpServletRequest request, HttpServletResponse response) {
		List<Item> items = itemService.loadItems();
		
		request.setAttribute("itemsData", items);
		
		try {
			request.getRequestDispatcher("/load-items.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	private void loadItem(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id").trim());
		Item item = itemService.loadItem(id);
		request.setAttribute("itemData", item);
		try {
			request.getRequestDispatcher("/update-item.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}




	private void removeItem(HttpServletRequest request, HttpServletResponse response) {
		
		int id = Integer.parseInt(request.getParameter("id").trim());
		Boolean removedItem = itemService.removeItem(id);
         if(removedItem) {
        	 loadItems(request, response);
         }
	}


	private void addItem(HttpServletRequest request, HttpServletResponse response) {
		String itemName = request.getParameter("itemName");
		double itemPrice = Double.parseDouble(request.getParameter("itemPrice"));
		int itemTotalNumber = Integer.parseInt(request.getParameter("itemTotalNumber"));
		
		Item item = new Item(itemName,itemPrice,itemTotalNumber);
		
        boolean addedItem = itemService.saveItem(item);
        
        if(addedItem) {
        	loadItems(request, response);
        }
	}


	

}
