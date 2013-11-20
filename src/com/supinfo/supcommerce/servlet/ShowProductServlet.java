package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;
import com.supinfo.sun.supcommerce.exception.UnknownProductException;

/**
 * Servlet implementation class ShowProductServlet
 * 
 * @author Elka
 * @version 2.1
 * @since SupCommerce 2.1
 */
public class ShowProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * Handles <code>GET</code> HTTP method
	 * 
	 * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// String format for product price (optional)
		DecimalFormat priceFormat = new DecimalFormat("0.00 €");
		
		// Get parameter recovery
		String productId = request.getParameter("id");
		
		// Set MIME type and Charset
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		// HTML5 response
		out.println("<!DOCTYPE HTML>");
		out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<title>ShowProduct - Servlet</title>");
        // CSS
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
        		+ ".error {"
        			+ "color: #FF8D02;"
        			+ "margin-left: 2em;"
        		+ "}"
        		+ "</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Product Details</h1>");
        
        /// If product Id parameter exists
		if(productId != null) {
			//// We try to found the product with matching id and next print its information
			try {
				SupProduct product = SupProductDao.findProductById( Long.parseLong(productId) );
				
				out.println("<div class=\"fieldset\">");
		        out.println("<h3><span>Product ID: " + Long.toString( product.getId() ) + "</span></h3>"); // Long.toString() - optional
		        out.println("<p>Product name: " + product.getName() + "</p>");
		        out.println("<p>Product description: " + product.getContent() + "</p>");
		        out.println("<p>Product price: " + priceFormat.format(product.getPrice()) + "</p>");
		        out.println("</div>");
			}
			//// Product with id parameter doesn't exist
			catch(UnknownProductException e) {
				out.println("<p class=\"error\">No product found for this ID !</p>");
			}
			//// Id parameter doesn't have correct type and/or format
			catch(NumberFormatException e) {
				out.println("<p class=\"error\">A correct ID's syntax is needed !</p>");
			}
		}
		/// No id parameter in URI
		else {
			out.println("<p class=\"error\">A Product ID's parameter is needed in URI !</p>");
		}
		
		out.println("</body>");
        out.println("</html>");           
        out.close();
        
	}

}
