
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
import org.json.JSONObject;

import servlet.services.*;
import servlet.services.serviceTools.*;


public class AllFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
		PrintWriter out;

		String id= request.getParameter("id");
		
		try {
			out = response.getWriter();
			out.println(FriendTools.getFriends(Integer.parseInt(id)));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}catch (IOException e2) {
			e2.printStackTrace();
		}
	}

}

