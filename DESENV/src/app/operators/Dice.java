package app.operators;

import app.connection.DBConnection;
import app.util.Util;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class Dice {
	private DBConnection dbcon = new DBConnection();
	
	public static void main(String[] args){
		Dice d = new Dice();
		String datasource = "rw_fato_vendas_formated";
		String[] filterName = "_key1;_key2;_key3".split(";");
		String[] filterName2 = d.getFiltersNames(datasource).split(";");
		
		String[] filterOperator = ">=;in;in".split(";"); 
		String[] filterValue = "2014;SC#SP#MG;11A013X".split(";");
		
		d.dice(datasource, filterName, filterOperator, filterValue);
		System.out.println("dice");
	}
	
	public void start(String datasource, String attributes){
		//String datasource = "rw_fato_vendas_formated";
		//String s = Util.getFilterAtt(datasource);
		
		String filters = Util.getFilters(datasource);			
		String[] nov = filters.split("#");
		String[] filterName = nov[0].split(";");//"_key2;_key3;_key1".split(";");			
		String[] filterOperator = nov[1].split(";");//">=;in;in".split(";");
		String[] filterValue = nov[2].split(";");//"2014;SC#SP#MG;11A013X".split(";");
		
		this.dice(datasource, filterName, filterOperator, filterValue);
		//System.out.println("dice");		
	}
	
	public void start(String datasource, String[] filterName, String[] filterOperator, String[] filterValue){
            int count=0;
            String[] Fname, Foperator, Fvalue;
            
            for(int i = 0; i < filterValue.length && !"".equalsIgnoreCase(filterValue[i].trim()); i++){
                count++;
            }
            
            Fname = new String[count];
            Foperator = new String[count];
            Fvalue = new String[count];
            
            for(int i = 0; i<count; i++){
                Fname[i] = filterName[i];
                Foperator[i] = filterOperator[i];
                Fvalue[i] = filterValue[i];
            }            
            datasource += "_formated";
            this.dice(datasource, Fname, Foperator, Fvalue);
	}        
        
	public String getFiltersNames(String datasource){
		String filtersNames = "";
		String scan;
		
		scan = Util.getFilterAtt(datasource);
		
		while(!"0".equals(scan)){
			filtersNames += ";" + scan;
			scan = Util.getFilterAtt(datasource);
		}
		return filtersNames;
	}
	
	public void dice(String datasource, String[] filterName, String[] filterOperator, String[] filterValue){
		DBCollection collection = dbcon.getDb().getCollection(datasource);
		DBCollection insertCollection = dbcon.getDb().getCollection(datasource + "_dice");
		
		insertCollection.drop();
		
		BasicDBObject whereQuery = new BasicDBObject();
		
		String operator = "";
		
		for(int i = 0; i < filterName.length; i++){
			operator = Util.getOperator(filterOperator[i]); 
			
			if(operator.equals("==")){
				whereQuery.put(filterName[i], filterValue[i]);
			}else if(operator.equals("$in")){
				String[] list = filterValue[i].split("#");
				whereQuery.put(filterName[i], new BasicDBObject(operator, list));
			}else{				
				whereQuery.put(filterName[i], new BasicDBObject(operator, filterValue[i]));				
			}
			
		}			
		
		DBCursor cursor = collection.find(whereQuery);	
				
		while(cursor.hasNext()){
			insertCollection.insert(cursor.next());
		}
                collection.drop();
	}
}
