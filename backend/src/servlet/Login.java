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


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
		PrintWriter out;

		String l= request.getParameter("login");
		String pa= request.getParameter("password");
		try {
			out = response.getWriter();
			out.println(Services.Login(l,pa)); 
		} catch (IOException|JSONException e) {
			e.printStackTrace();
		}
	}

}
