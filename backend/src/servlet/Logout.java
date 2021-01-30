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


public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out;

		String ikey= request.getParameter("key");
		try {
			out = response.getWriter();
			out.println(Services.Logout(ikey)); 
		} catch (IOException|JSONException e) {
			e.printStackTrace();
		}
	}
}
