package servlet.services.serviceTools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FriendTools {
	
	public static JSONObject addFriend(int id1, int id2) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		
		
		Connection conn= Database.getMySQLConnection();
		String query= "INSERT INTO friends VALUES ('"+id1+"','"+id2+"',0);";
		Statement ins= conn.createStatement();
		ins.executeUpdate(query);
		ins.close();
		conn.close();

		JSONObject ok= new JSONObject();
		ok.put("new", id2);
		ok.put("user", id1);
		
		return ok;
	}

	public static List<Integer> getFriendsList(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		Connection conn= Database.getMySQLConnection();
		String query= "SELECT u.id FROM friends f,users u WHERE (f.from_id="+id+" AND f.to_id=u.id) OR (f.from_id=u.id AND f.to_id="+id+");";
		Statement inq;
		List<Integer> l= new ArrayList<>();
		try {
			inq = conn.createStatement();
			inq.executeQuery(query);
			ResultSet rs=inq.getResultSet();		
			while(rs.next()) 
				l.add( rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	
	public static JSONArray getFriends(int id) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		Connection conn= Database.getMySQLConnection();
		String query= "SELECT u.id,u.prenom,u.nom,f.valid FROM friends f,users u WHERE (f.from_id="+id+" AND f.to_id=u.id) OR (f.from_id=u.id AND f.to_id="+id+");";
		Statement inq;
		JSONArray ja = new JSONArray();
		try {
			inq = conn.createStatement();
			inq.executeQuery(query);
			ResultSet rs=inq.getResultSet();
			
			while(rs.next()) {
				Document d= new Document();
				d.append("id", rs.getInt(1));
				d.append("prenom", rs.getString(2));
				d.append("nom", rs.getString(3));
				d.append("valid",rs.getInt(4));
				ja.put(d);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ja;
	}
	
	public static boolean alreadyFriend(int id1, int id2) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Connection conn= Database.getMySQLConnection();
		String query= "Select from_id From friends Where (from_id="+id1+" AND to_id="+id2+") OR (from_id="+id2+" AND to_id="+id1+");";

		Statement st= conn.createStatement();
		st.executeQuery(query);
		ResultSet rs = st.getResultSet();
		boolean b= false;
		if(rs.next()) 
			b = true;
	
		rs.close();
		st.close();
		conn.close();

		return b;
	}
	
	public static JSONObject removeFriend(int id1, int id2) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		Connection conn= Database.getMySQLConnection();
		String query= "DELETE FROM friends WHERE (from_id="+id1+" AND to_id="+id2+") OR (from_id="+id2+" AND to_id="+id1+");";
		Statement inq= conn.createStatement();
		inq.executeUpdate(query);
		
		inq.close();
		conn.close();
		JSONObject ok= new JSONObject();
		ok.put("sup", id2);
		ok.put("user ", id1);

		return ok;
	}
}
