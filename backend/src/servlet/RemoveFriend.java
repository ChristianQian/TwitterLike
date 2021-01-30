package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import servlet.services.Services;


public class RemoveFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
		PrintWriter out;
		String key= request.getParameter("key");
		String id2= request.getParameter("id2");
		
		try {
			out = response.getWriter();
			out.println(Services.RemoveFriend(key,id2)); 
		} catch (IOException|JSONException e) {
			e.printStackTrace();
		}
	}

}
