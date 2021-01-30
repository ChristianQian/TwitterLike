package servlet.services.serviceTools;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserTools {
	
	public static boolean userExists(String login) throws BDException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Connection conn;
		boolean retour;
		conn= Database.getMySQLConnection();
		String query= "SELECT id FROM users WHERE login=\""+login+"\";";
		
		Statement ins= conn.createStatement();
		ins.executeQuery(query);
		ResultSet rs= ins.getResultSet();
		
		if(rs.next()) 
			retour= true;
		else 
			retour= false;
		rs.close();
		ins.close();
		conn.close();
		return retour;
	
	}
	
	public static JSONObject insertUser(String login, String pw, String nom, String prenom) throws JSONException, SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		Connection conn= Database.getMySQLConnection();
		String query= "INSERT INTO users VALUES (NULL, '"+login+"','"+pw+"', '"+prenom+"', '"+nom+"');";
		Statement ins= conn.createStatement();
		ins.executeUpdate(query);
		ins.close();
		conn.close();

		JSONObject ok= new JSONObject();
		ok.put("created", "OK");
		return ok;
	}
	
	public static boolean checkPass(String login, String pw) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Connection conn= Database.getMySQLConnection();
		String query= "SELECT login FROM users WHERE login=\""+login+"\" and password=\""+pw+"\";";
		Statement inq= conn.createStatement();
		inq.executeQuery(query);
		ResultSet rs=inq.getResultSet();
		
		boolean r;
		
		if(rs.next())
			r = true;
		else
			r = false;
		
		rs.close();
		inq.close();
		conn.close();
		return r;
	}
	
	public static int getIdUser(String login) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Connection conn= Database.getMySQLConnection();
		String query= "SELECT id FROM users WHERE login=\""+login+"\";";
		Statement inq= conn.createStatement();
		inq.executeQuery(query);
		ResultSet rs=inq.getResultSet();
		int i;
		if(rs.next())
		   i = rs.getInt(1);
		else
			throw new SQLException("Erreur user: echec recup id ");
				
		rs.close();
		inq.close();
		conn.close();
		return i;
	}
	
	public static JSONArray getAllUser(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		Connection conn= Database.getMySQLConnection();
		List<Integer> l= FriendTools.getFriendsList(id);
		
		String query= "SELECT id,prenom,nom FROM users WHERE id<>"+id+";";
		Statement inq;
		JSONArray ja = new JSONArray();
		try {
			inq = conn.createStatement();
			inq.executeQuery(query);
			ResultSet rs=inq.getResultSet();
			
			while(rs.next()) {
				if(!l.contains(rs.getInt(1))){
				Document d= new Document();
				d.append("id", rs.getInt(1));
				d.append("prenom", rs.getString(2));
				d.append("nom", rs.getString(3));
				ja.put(d);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ja;
		
		
	}

	public static String [] getNameUser(int id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Connection conn= Database.getMySQLConnection();
		String query= "SELECT nom,prenom FROM users WHERE id="+id+";";
		
		Statement inq= conn.createStatement();
		inq.executeQuery(query);
		ResultSet rs=inq.getResultSet();
		String [] name= new String [2];
		if(rs.next()) {
			   name[0] = rs.getString(1);
			   name[1] = rs.getString(2);
		}		
		else
			throw new SQLException("Erreur user: echec recup nom with id ");
				
		rs.close();
		inq.close();
		conn.close();
		return name;
	}
}
