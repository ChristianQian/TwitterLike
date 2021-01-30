package servlet.services;

import java.io.PrintWriter;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import servlet.services.serviceTools.BDException;
import servlet.services.serviceTools.FriendTools;
import servlet.services.serviceTools.LoginTools;
import servlet.services.serviceTools.MongoTools;
import servlet.services.serviceTools.Refused;
import servlet.services.serviceTools.UserTools;

public class Services {
	public static JSONObject createUser(String p, String n, String l, String pa) throws JSONException {
		try {
			if(p==null || n==null | l ==null || pa == null)
				return Refused.serviceRefused("none",-1);
			if(UserTools.userExists(l))
				return Refused.serviceRefused("already",1);
		
			return UserTools.insertUser(l, pa, n, p);
		}catch(JSONException e) {
			return Refused.serviceRefused("JSON Prob createUser"+e.getMessage(), 100);
		}catch(SQLException e) {
			return Refused.serviceRefused("SQL Prob createUser"+e.getMessage(), 1000);
		}catch(Exception e) {
			return Refused.serviceRefused("JAVA Prob createUser"+e.getMessage(), 10000);
		}
	}
	
	public static JSONObject Login(String l, String p) throws JSONException{
		try {
		if(l==null || p==null)
			return Refused.serviceRefused("none",-1);
		
		//erreur 1
			boolean isUser= UserTools.userExists(l);
			if(!isUser) return Refused.serviceRefused("unknown",1);
		//erreur 2
			boolean pOk= UserTools.checkPass(l,p);
			if(!pOk) return Refused.serviceRefused("wpw",2);
			int idUser= UserTools.getIdUser(l);
			
			JSONObject retour= new JSONObject();
			retour.put("id", idUser);
			retour.put("login", l);
			
			String key= LoginTools.insereConnexion(idUser,0); // 0 false, 1 true
			retour.put("key", key);
			return retour;
		}catch(JSONException e) {
			return Refused.serviceRefused("JSON Prob Login"+e.getMessage(), 100);
		}catch(SQLException e) {
			return Refused.serviceRefused("SQL Prob Login"+e.getMessage(), 1000);
		}catch(Exception e) {
			return Refused.serviceRefused("JAVA Prob Login"+e.getMessage(), 10000);
		}
	}
	
	public static JSONObject Logout(String ikey) throws JSONException {
		try {
				return LoginTools.retireConnexion(ikey);
		}catch(Exception e) {
			return Refused.serviceRefused("JAVA Problem "+e.getMessage(), 10000);
		}
	}
	
	public static JSONObject AddFriend(String key, String sid2) throws JSONException {
		try {
			if(key==null || sid2==null)
				return Refused.serviceRefused("Wrong Arg",-1);

			int id1= LoginTools.getIdUser(key);
			int id2= Integer.parseInt(sid2);
			if(FriendTools.alreadyFriend(id1,id2)) {
				JSONObject j= new JSONObject();
				j.put("user", id1);
				j.put("already", id2);
				return j;
			}
				
			return FriendTools.addFriend(id1,id2);
		}catch(JSONException e) {
			return Refused.serviceRefused("JSON Prob AddFriend"+e.getMessage(), 100);
		}catch(SQLException e) {
			return Refused.serviceRefused("SQL Probl AddFriend"+e.getMessage(), 1000);
		}catch(Exception e) {
			return Refused.serviceRefused("JAVA Probl AddFriend"+e.getMessage(), 10000);
		}
	}
	
	public static JSONObject RemoveFriend(String key, String sid2) throws JSONException {
		try {
			if(key==null || sid2==null)
				return Refused.serviceRefused("Wrong Arg",-1);
			
			//erreur 1
			int id2= Integer.parseInt(sid2);
			int id1= LoginTools.getIdUser(key);
			if(!FriendTools.alreadyFriend(id1,id2)) {
				JSONObject j= new JSONObject();
				j.put("user", id1);
				j.put("non", id2);
				return j;
			}
			return FriendTools.removeFriend(id1,id2);
		}catch(JSONException e) {
			return Refused.serviceRefused("JSON Prob RemoveFriend"+e.getMessage(), 100);
		}catch(SQLException e) {
			return Refused.serviceRefused("SQL Prob RemoveFriend"+e.getMessage(), 1000);
		}catch(Exception e) {
			return Refused.serviceRefused("JAVA Prob RemoveFriend"+e.getMessage(), 10000);
		}
	}
	
	public static JSONObject AddComment(String key, String text) throws JSONException {
		try {
			if(key==null || text==null)
				return Refused.serviceRefused("none",-1);
		
			int id= LoginTools.getIdUser(key);
		
			return MongoTools.createComment(id,text);
		}catch(JSONException e) {
			return Refused.serviceRefused("JSON Prob AddComment"+e.getMessage(), 100);
		}catch(SQLException e) {
			return Refused.serviceRefused("SQL Prob AddCommen"+e.getMessage(), 1000);
		}catch(Exception e) {
			return Refused.serviceRefused("JAVA Prob AddCommen"+e.getMessage(), 10000);
		}
	}
	
}
