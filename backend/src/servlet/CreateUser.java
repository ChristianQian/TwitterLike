package servlet;

import java.io.*;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import servlet.services.Services;
import servlet.services.serviceTools.BDException;


public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
		PrintWriter out;

		String p= req.getParameter("prenom");
		String n= req.getParameter("nom");
		String l= req.getParameter("login");
		String pa= req.getParameter("password");
		
		try {
			out = resp.getWriter();
			out.println(Services.createUser(p,n,l,pa)); 

		}catch (JSONException|IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
