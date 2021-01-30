package servlet.services.serviceTools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginTools {
	
	public static String insereConnexion(int id, int b) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, BDException {
		Connection conn= Database.getMySQLConnection();
		String query= "SELECT UUID();";
		
		Statement inq= conn.createStatement();
		inq.executeQuery(query);
		ResultSet rs=inq.getResultSet();
		String ikey= "";
		if(rs.next())
			   ikey = rs.getString(1);
		else
			throw new SQLException("Erreur creation cle ");
		query= "INSERT INTO login VALUES ('"+ikey+"','"+id+"','"+b+"')";
		inq.executeUpdate(query);
		rs.close();
		inq.close();
		conn.close();
		
		return ikey;
	}
	
	public static JSONObject retireConnexion(String ikey) throws BDException,InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException  {
		Connection conn= Database.getMySQLConnection();
		int id= getIdUser(ikey);
		String query= "DELETE FROM login WHERE ikey=\""+ikey+"\";";
		Statement inq= conn.createStatement();
		inq.executeUpdate(query);
		inq.close();
		conn.close();

		JSONObject ok= new JSONObject();
		String [] s= UserTools.getNameUser(id);
		ok.put("nom", s[0]);
		ok.put("prenom", s[1]);
			
		return ok;
	}
	
	public static int getIdUser(String key) throws BDException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		int id = -1;

			Connection c = Database.getMySQLConnection();
			Statement st = c.createStatement();
			String query = "Select id From login Where ikey=\""+key+"\";";
			st.executeQuery(query);
			ResultSet rs = st.getResultSet();
			if(rs.next()) 
				id = rs.getInt(1);
			else
				throw new BDException("Erreur recup id in login");
			
			rs.close();
			st.close();
			c.close();
		return id;
	}

}
