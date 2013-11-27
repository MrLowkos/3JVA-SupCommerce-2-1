package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;

/**
 * <b>ListProductServlet</b>
 * <p>
 * List all products registered in memory (through SupCommerce.jar)
 * </p>
 * 
 * @author Elka
 * @version 1.0
 * @since SupCommerce 2.1
 */
public class ListProductServlet extends HttpServlet {
	private static final long	serialVersionUID	= 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListProductServlet() {
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
		
		// Retrieve Product list + protect the reference of productList with final keyword
		final List<SupProduct> productList = SupProductDao.getAllProducts();
		
		// Set MIME type and Charset (optional)
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		// HTML5 response (optional)
		/** @f:off */
		out.println("<!DOCTYPE html>"
		+ "<html lang=\"en\">"
		+ "<head>"
		+ "<meta charset=\"UTF-8\">"
		+ "<title>ListProduct - Servlet</title>"
		+ "</head>"
		+ "<body>"

		+ "<header>"
		+ "<a href=\"/SupCommerce-2-1\">SupCommerce 2.1</a>"
		+ "</header>"
		+ "<nav>"
		+ "<ul>"
		+ "<li><a href=\"/SupCommerce-2-1\">Home</a></li>"
		+ "<li class=\"active\"><a href=\"/SupCommerce-2-1/listProduct\">Products List</a></li>"
		+ "<li><a href=\"/SupCommerce-2-1/basicInsert\">Random Product</a></li>"
		+ "</ul>"
		+ "</nav>"

		+ "<section>"
		+ "<h1>Product List</h1>");
		// If list is empty
		if (productList.isEmpty()) {
			out.println("<div><h3 style=\"color:red;\">No product registered !</h3></div>");
		}
		// Else we browse productList and print information for each product
		else {
			for (SupProduct product : productList) {
				out.println("<article>"
				+ "<header>"
				+ "<h3><a href=\"/SupCommerce-2-1/showProduct?id=" + product.getId() + "\">Product Id " + product.getId() + "</a></h3>"
				+ "</header>"
				+ "<section>"
				+ "<p>Name: " + product.getName() + "</p>"
				+ "<p>Description: " + product.getContent() + "</p>"
				+ "<p>Price: " + product.getPrice() + " €</p>"
				+ "</section>" 
				+ "<article>");
			}
		}
		out.println("</div>"
		+ "</section>"

		
		+ "</body>"
		+ "</html>");
		out.close();
	}

}
