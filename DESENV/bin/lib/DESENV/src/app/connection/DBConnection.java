package app.connection;

import java.net.UnknownHostException;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;

public class DBConnection {
	private Mongo mongo = null;
	private DB db = null;
	
	public DBConnection(String host, int port, String dbName){
		try{
			mongo = new Mongo(host, port);
			db = mongo.getDB(dbName);
		}catch(UnknownHostException e){
			e.printStackTrace();
		}				
	}
        
	public DBConnection(){
		try{
			mongo = new Mongo("localhost", 27017);
			db = mongo.getDB("desenv");
		}catch(UnknownHostException e){
			e.printStackTrace();
		}		
	}

	public Mongo getMongo() {
		return mongo;
	}

	public void setMongo(Mongo mongo) {
		this.mongo = mongo;
	}

	public DB getDb() {
		return db;
	}

	public void setDb(DB db) {
		this.db = db;
	}
	
	public Set<String> getDbCollections(){
		Set<String> colls = null;
		colls = getDb().getCollectionNames();
		return colls;
	}
	
	public String getCollAtts(String coll){
		DBCollection c = null;
		String[] s;
		String set = "";
		
		c = getDb().getCollection(coll);
				
		s = c.findOne().toString().split(",");
		
		for(int i=1; i < s.length; i++){
			String[] ss = s[i].split(":");
			set = set + ss[0].replace('"', ' ') + "#" ;
		}
		
		return set;
	}
	
}

