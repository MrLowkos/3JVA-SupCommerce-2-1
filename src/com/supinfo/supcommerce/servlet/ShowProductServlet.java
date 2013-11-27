package com.supinfo.supcommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.sun.supcommerce.bo.SupProduct;
import com.supinfo.sun.supcommerce.doa.SupProductDao;
import com.supinfo.sun.supcommerce.exception.UnknownProductException;

/**
 * <b>ShowProductServlet</b>
 * <p>
 * Show product registered in memory (through SupCommerce.jar) by "id" <code>GET</code> parameter use
 * contextPath/showProduct?id=X where X is a Long
 * <p>
 * 
 * @author Elka
 * @version 1.0
 * @since SupCommerce 2.1
 */
public class ShowProductServlet extends HttpServlet {
	private static final long	serialVersionUID	= 1L;
	private static final String	ID_GET_PARAM		= "id";
	
	/**
	 * Handle <code>GET</code> HTTP method
	 * 
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recover id parameter through url
		final String id = request.getParameter(ID_GET_PARAM);
		
		// Set MIME type and Charset (optional)
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		// HTML5 response
		/** @f:off */
		out.println("<!DOCTYPE html>"
		+ "<html lang=\"en\">"
		+ "<head>"
		+ "<meta charset=\"UTF-8\">"
		+ "</head>"
		+ "<body>"

		+ "<header>"
		+ "<a href=\"/SupCommerce-2-1\">SupCommerce 2.1</a>"
		+ "</header>"
		+ "<nav>"
		+ "<ul>"
		+ "<li><a href=\"/SupCommerce-2-1\">Home</a></li>"
		+ "<li><a href=\"/SupCommerce-2-1/listProduct\">Products List</a></li>"
		+ "<li><a href=\"/SupCommerce-2-1/basicInsert\">Random Product</a></li>"
		+ "</ul>"
		+ "</nav>"

		+ "<section>"
		+ "<h1>Product Details</h1>");
		
		if (id == null) {
			// No id parameter in URI
			out.println("<div style=\"color:red;\"><h3>A Product ID's parameter is needed in URI !</h3></div>");
		} else {			
			// We try to found the product with matching id and next print its information
			try {
				SupProduct product = SupProductDao.findProductById(Long.parseLong(id));

				out.println("<article>"
						+ "<header>"
						+ "<h3>Product Id " + product.getId() + "</h3>"
						+ "</header>"
						+ "<section>"
						+ "<p>Name: " + product.getName() + "</p>"
						+ "<p>Description: " + product.getContent() + "</p>"
						+ "<p>Price: "+ product.getPrice() + " €</p>" 
						+ "</section>" 
						+ "<article>");
			}
			// Product with id parameter doesn't exist in memory
			catch (UnknownProductException e) {
				out.println("<div><h3 style=\"color:red;\">No product found for this ID !</h3></div>");
			}
			// Id parameter doesn't have correct type and/or format
			catch (NumberFormatException e) {
				out.println("<div><h3 style=\"color:red;\">A correct \"id\" type (Long) is needed !</h3></div>");
			}
		}

		out.println("</section>"
	
		+ "</body>"
		+ "</html>");
		out.close();

	}

}
