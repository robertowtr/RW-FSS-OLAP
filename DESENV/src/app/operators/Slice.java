package app.operators;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import app.connection.DBConnection;
import app.util.Util;

public class Slice{
	private DBConnection dbcon = new DBConnection();
	
	public static void main(String[] args){
		Slice s = new Slice();
		s.slice("rw_fato_vendas_formated", "_key3", "SC");
		
	}
	
        ///DEPRACATED
	public void start(String datasource){
		datasource += "_formated";
		String filterName = Util.getFilterAtt(datasource);
		System.out.println("Type the value");
		String filterValue = Util.scan();
		
		this.slice(datasource, filterName, filterValue);
	}
        
	public void start(String datasource, String filterName, String filterValue){
		datasource += "_formated";
		this.slice(datasource, filterName, filterValue);
	}        
	
	public void slice(String datasource, String filterName, String filterValue){
		DBCollection collection = dbcon.getDb().getCollection(datasource);
		DBCollection insertCollection = dbcon.getDb().getCollection(datasource + "_slice");
		
		insertCollection.drop();
		
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put(filterName, filterValue);
		
		DBCursor cursor = collection.find(whereQuery);	
				
		while(cursor.hasNext()){
			insertCollection.insert(cursor.next());
		}
                collection.drop();
	}
}
