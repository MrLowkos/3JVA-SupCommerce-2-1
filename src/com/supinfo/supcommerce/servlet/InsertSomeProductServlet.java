package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;

/**
 * <b>InsertSomeProductServlet</b>
 * <p>
 * Register random generated product in memory (through SupCommerce.jar)
 * </p>
 * 
 * @author Elka
 * @version 1.0
 * @since SupCommerce 2.1
 */
public class InsertSomeProductServlet extends HttpServlet {
	private static final long	serialVersionUID	= 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertSomeProductServlet() {
		super();
	}
	
	/**
	 * Handle all HTTP methods (GET, POST, PUT, DELETE, ...).
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		
		// Random generator (optional) + protect the reference of generator with final keyword
		final Random rand = new Random();
		
		// New product's creation
		final SupProduct product = new SupProduct();
		// Set product properties randomly
		product.setName("Product-" + rand.nextInt(100));
		product.setContent("Product onformation of " + product.getName() + "have to be set.");
		product.setPrice(rand.nextFloat() + rand.nextInt(100));
		
		// Generate ID and add product in memory
		SupProductDao.addProduct(product);
		
		/*
		 * !! This following part is just for testing, later we remove it and redirect to ListProductServlet !!
		 */
		
		// Set MIME type and charset (optional, many other HTTP header fields can be set)
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		// HTML5 response (optional)
		/** @f:off */
		out.println("<!DOCTYPE html>"
		+ "<html lang=\"en\">"
		+ "<head>"
		+ "<meta charset=\"UTF-8\">"
		+ "<meta content=\"IE=edge\" http-equiv=\"X-UA-Compatible\">"
		+ "<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\">"
		+ "<title>InsertSomeProduct - Servlet</title>"
		+ "</head>"
		+ "<body>"

		+ "<header>"
		+ "<a href=\"/SupCommerce-2-1\">SupCommerce 2.1</a>"
		+ "</header>"
		+ "<nav>"
		+ "<ul>"
		+ "<li><a href=\"/SupCommerce-2-1/listProduct\">Products List</a></li>"
		+ "<li><a href=\"/SupCommerce-2-1/basicInsert\">Random Product</a></li>"
		+ "</ul>"
		+ "</nav>"

		+ "<section>"
		+ "<h1>New Random Product added !</h1>"
		+ "<article>"
		+ "<header>"
		+ "<h3>"
		+ "<a href=\"/SupCommerce-2-1/showProduct?id=" + product.getId() + "\"> Product Id " + product.getId() + "</a>"
		+ "</h3>"
		+ "</header>"
		+ "<section>"
		+ "<p>Name: " + product.getName() + "</p>"
		+ "<p>Description: " + product.getContent()	+ "</p>"
		+ "<p>Price: " + product.getPrice() + " €</p>"
		+ "</section>"
		+ "</article>"
		+ "</section>"

		+ "</body>"
		+ "</html>");
		out.close();
	}
}
