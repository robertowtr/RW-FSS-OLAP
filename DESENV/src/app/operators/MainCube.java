package app.operators;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MapReduceCommand;

import app.connection.DBConnection;

public class MainCube {
	private DBConnection dbcon = new DBConnection();

	public void generateCube(String datasource, String[] dimensions,
			String value) {

		DBCollection collection = dbcon.getDb().getCollection(datasource);
		DBCollection outputCollection = dbcon.getDb().getCollection(
				datasource + "_temp");

		String key = "this." + dimensions[0];

		for (int idx = 1; idx < dimensions.length; idx++) {
			key += "+ '#' + this." + dimensions[idx];
		}
		//System.out.println(key);
		String map = "function() {emit(" + key + ", this." + value + ");}";
		String reduce = "function(key, values) { return Array.sum(values)}";

		MapReduceCommand cmd = new MapReduceCommand(collection, map, reduce,
				outputCollection.toString(),
				MapReduceCommand.OutputType.REPLACE, null);
		collection.mapReduce(cmd);

		MainCube cc = new MainCube();
		cc.formatOutput(key, outputCollection.toString());
	}

	public void formatOutput(String key, String datasource) {

		DBCollection collection = dbcon.getDb().getCollection(datasource);
		DBCollection persist = dbcon.getDb().getCollection(
				datasource.replace("_temp", "") + "_formated");
		persist.drop();

		BasicDBObject query = new BasicDBObject();

		DBCursor cursor = collection.find(query);

		while (cursor.hasNext()) {

			String next = cursor.next().toString();
			float value;

			String[] chunk = next.toString().split(",");
			String[] chunkHelp;

			// Pega somente o valor
			chunkHelp = chunk[1].split(":");

			value = Float.valueOf(
					chunkHelp[1].substring(0, chunkHelp[1].length() - 1))
					.floatValue();

			// Pega somente a chave
			chunkHelp = chunk[0].split(":");
			// Quebra as 3 dimensões
			String[] keys = chunkHelp[1].split("#");

			// Remover os "
			keys[0] = keys[0].substring(2, keys[0].length());
			keys[keys.length - 1] = keys[keys.length - 1].substring(0,
					keys[keys.length - 1].length() - 2);

			// Persiste todas as chaves separadas e o valor
			BasicDBObject document = new BasicDBObject();

			document.put("_key",
					key.replace("this.", "").replace("+ '#' + ", "#"));

			for (int i = 0; i < keys.length; i++) {
				document.put("_key" + (i + 1), keys[i]);
			}

			document.put("_value", value);

			persist.insert(document);

		}
		collection.drop();
	}
}
