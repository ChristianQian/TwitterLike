
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import servlet.services.Services;
import servlet.services.serviceTools.MongoTools;

/**
 * Servlet implementation class AddComment
 */
public class AllComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out;

		String id= request.getParameter("id");

		try {
			out = response.getWriter();
			out.println(MongoTools.AllComment(Integer.parseInt(id)));
		} catch (NumberFormatException | InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException | JSONException e) {
			e.printStackTrace();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
	}
}
