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
import servlet.services.serviceTools.BDException;


public class AddFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out;
		
		String key= request.getParameter("key");
		String id2= request.getParameter("id2");
		
		try {
			out = response.getWriter();
			out.println(Services.AddFriend(key,id2)); 
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
