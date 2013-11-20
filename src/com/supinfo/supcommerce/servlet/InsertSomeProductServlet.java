package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;

/**
 * Servlet implementation class InsertSomeProductServlet
 * 
 * @author Elka
 * @version 2.1
 * @since SupCommerce 2.1
 */
public class InsertSomeProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertSomeProductServlet() {
        super();
    }

	/**
	 * Handles all HTTP methods (GET, POST, PUT, DELETE, ...).
	 * 
	 * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Random generator (optional)
		Random rand = new Random();
		
		// String format for product price (optional - see DecimalFormat doc to use it)
		DecimalFormat priceFormat = new DecimalFormat("0.00 €");
		
		// New product's creation
		SupProduct product = new SupProduct();
		// Set product properties randomly 
		product.setName("Product-" + rand.nextInt(100));
		product.setContent("Product's Information of " + product.getName());
		product.setPrice(rand.nextFloat() + rand.nextInt(100));
		
		// Generate ID and add product in memory
		SupProductDao.addProduct(product);
		
		/*!! This following part is just for testing, later we remove it and redirect to listProduct  !!*/
		
		// Set MIME type and charset (optional, many other HTTP header fields can be set)
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		// HTML5 response (optional)
		out.println("<!DOCTYPE HTML>");
		out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>InsertSomeProduct - Servlet</title>");
        // CSS (just to show how make this code part more readable)
        out.println("<style type=\"text/css\" media=\"screen\">"
        		+ "body {"
        			+ "background: #FFF6CE;"
        			+ "font-family: verdana,helvetica,arial,sans-serif;"
        			+ "color: #406467;"
        		+ "}"
        		+ "h1 {"
        		+ "margin-left: 1em;"
        		+ "}"
        		+ ".fieldset {"
        			+ "background: #FFF;"
	        		+ "border: 2px solid #66A7AB;"
	        		+ "border-top: none;"
	        		+ "padding: 0.5em;"
	        		+ "margin: 1em 2em;"
        		+ "}"
        		+ ".fieldset > h3 {"
	        		+ "font: 1em normal;"
	        		+ "margin: -1em -0.5em 0;"
	        		+ "font-family: verdana,helvetica,arial,sans-serif;"
        		+ "}"
        		+ ".fieldset > h3 > span {"
        			+ "float: left;"
        			+ "color: #66A7AB;"
        		+ "}"
        		+ ".fieldset > h3:before {"
	        		+ "border-top: 2px solid #66A7AB;"
	        		+ "content: ' ';"
	        		+ "float: left;"
	        		+ "margin: 0.5em 2px 0 -1px;"
	        		+ "width: 0.75em;"
        		+ "}"
        		+ ".fieldset > h3:after {"
	        		+ "border-top: 2px solid #66A7AB;"
	        		+ "content: ' ';"
	        		+ "display: block;"
	        		+ "height: 1.5em;"
	        		+ "left: 2px;"
	        		+ "margin: 0 1px 0 0;"
	        		+ "overflow: hidden;"
	        		+ "position: relative;"
	        		+ "top: 0.5em;"
        		+ "}"
        		+ "</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>New Product added !</h1>");
        out.println("<div class=\"fieldset\">");
        // Long.toString() - (optional, you can use implicit conversion)
        out.println("<h3 ><span>Product ID: " + Long.toString(product.getId()) + "</span></h3>");
        out.println("<p>Product name: " + product.getName() + "</p>");
        out.println("<p>Product description: " + product.getContent() + "</p>");
        out.println("<p>Product price: " + priceFormat.format(product.getPrice())  + "</p>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");           
        out.close();
	}

}
