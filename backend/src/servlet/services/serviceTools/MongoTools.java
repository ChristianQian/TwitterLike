package servlet.services.serviceTools;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoTools {

	public static JSONObject createComment(int id, String text) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, JSONException {
		MongoDatabase bd= Database.getMongoDatabase();
		MongoCollection<Document> collection= bd.getCollection("comments");
		
		String [] name= UserTools.getNameUser(id);
		
		Document query= new Document();
		
		query.append("author_id", id);
		query.append("author_nom", name[0]);
		query.append("author_prenom", name[1]);
		query.append("date", new Date());
		query.append("text", text);
		
		collection.insertOne(query);
		JSONObject j= new JSONObject();
		j.put("user", id);
		j.put("comment", text);
		return j;
	}
	
	public static JSONArray AllComment(int id) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		MongoDatabase bd= Database.getMongoDatabase();
		MongoCollection<Document> collection= bd.getCollection("comments");
		List<Integer> l= FriendTools.getFriendsList(id);
		List<Document> lq= new ArrayList<>();
		lq.add(new Document("author_id", id));
		
		for(int i: l) 
			lq.add(new Document("author_id", i));
		
		Document q= new Document();
		q.append("$or", lq);
		MongoCursor<Document> c= collection.find(q).iterator();
		
		JSONArray j= new JSONArray();
		while(c.hasNext()) {
			Document d = c.next();
			j.put(d);
		}
		return j;
	}
	
	public static JSONArray search(String key, String id2,PrintWriter out) throws JSONException, InstantiationException, IllegalAccessException, ClassNotFoundException, BDException, SQLException {
		int id= LoginTools.getIdUser(key);
		MongoDatabase bd= Database.getMongoDatabase();
		MongoCollection<Document> collection= bd.getCollection("comments");
		
		JSONArray j= new JSONArray();
		Document q1= new Document("author_id", id);
		Document q2= new Document("author_id", Integer.parseInt(id2));
		List<Document> q12= new ArrayList<>();
		q12.add(q1);
		q12.add(q2);
		Document qt= new Document();
		qt.append("$or", q12);

		MongoCursor<Document> c= collection.find(qt).iterator();
		while(c.hasNext()) {
			Document d = c.next();
			j.put(d);
			
		}
		return j;
	}
}
