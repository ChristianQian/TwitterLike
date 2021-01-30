package servlet.services.serviceTools;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.bson.Document;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Database {
	
	private DataSource dataSource;
	private static Database database= null;
	
	public Database(String jndiname)throws SQLException {
		try	{
			dataSource = (DataSource)new InitialContext().lookup("java:comp/env/"+ jndiname);
		}catch(NamingException e) {
			// Handle error that it’s not configured in JNDI.
			throw new SQLException(jndiname +" is missing in JNDI! : "+e.getMessage());
		}
	}
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	public static Connection getMySQLConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		if(!DBStatic.mysql_pooling) {
			return (DriverManager.getConnection("jdbc:mysql://"+ DBStatic.mysql_host +"/"+DBStatic.mysql_db, DBStatic.mysql_username, DBStatic.mysql_password));
		}else{
			if(database==null) {
				database=new Database("jdbc/db");
			}
			return (database.getConnection());
		}
	}
	
	public static MongoDatabase getMongoDatabase() throws MongoException {
			MongoClient mongo = MongoClients.create();
			return mongo.getDatabase (DBStatic.mongo_db);
	}

	// test de la bd
	public static void main(String args[]) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		//try {
			MongoDatabase bd= Database.getMongoDatabase();
			MongoCollection<Document> collection= bd.getCollection("comments");
			
			String [] name= {"YTES","FDSQF"};
			
			Document query= new Document();
			
			ArrayList<Integer> topics = new ArrayList<>();
			topics.add(1);
			topics.add(8);
			query.append("lol",topics);
			query.append("b",false);
			
			collection.insertOne(query);
			//Document q1= new Document("author_id", 87);
			//Document q2= new Document("author_id", 200);
			//ArrayList<Document> q12= new ArrayList<>();
			//q12.add(q1);
			//q12.add(q2);
			//Document qt= new Document();
			//qt.append("$or", q12);
			MongoCursor<Document> c= collection.find().iterator();
			while(c.hasNext()) {
				Document d = c.next();
				System.out.println(d);
			}
			
		/*	System.out.println("ca marche 2");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}