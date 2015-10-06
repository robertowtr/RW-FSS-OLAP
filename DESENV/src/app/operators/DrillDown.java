package app.operators;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;

import app.connection.DBConnection;
import app.util.Util;

public class DrillDown {

    private DBConnection dbcon = new DBConnection();

    public static void main(String[] args) {
        String datasource = "rw_fato_vendas";

        DrillDown dd = new DrillDown();

        String[] dimensions = "CIDADE;ANO".split(";");
        String value = "QUANTIDADE";
        dd.start(datasource, dimensions, value, "MODELO");

    }

    public void start(String datasource, String[] dimensions, String value, String C_detalhe) {
        String key_detail = "";
        String key_master = "";
        String helper = "";

        String[] dimensions_details = new String[dimensions.length + 1];
        //dimensions.
        int i = 0;
        for (; i < dimensions.length; i++) {
            key_master += dimensions[i] + "#";
            dimensions_details[i] = dimensions[i];
        }
        dimensions_details[i] = C_detalhe;
        key_master = key_master.substring(0, key_master.length() - 1);

        /**
         * **
         *
         */
        //Busca todos os atributos que podem ser sumarizados
        for (int idx = 0; idx < dimensions_details.length; idx++) {
            if (!key_detail.contains(dimensions_details[idx])) {
                key_detail += dimensions_details[idx] + "#";
            }
            helper = getPrevAtt(datasource, dimensions_details[idx]);
            while (!"".equals(helper)) {
                if (!key_detail.contains(helper)) {
                    key_detail += helper + "#";
                }
                helper = getPrevAtt(datasource, helper);
            }
        }
        key_detail = key_detail.toUpperCase();
        dimensions_details = key_detail.split("#");

        MainCube mc = new MainCube();
        mc.generateCube(datasource, dimensions_details, value);

        String filters = Util.getFilters(datasource + "_formated");
        String[] nov = filters.split("#");
        String[] filterName = nov[0].split(";");
        String[] filterOperator = nov[1].split(";");
        String[] filterValue = nov[2].split(";");

        this.dice(datasource + "_formated", filterName, filterOperator, filterValue);
        this.generateCube(datasource + "_formated_dice_drilldown", key_master, key_detail, "_value");
    }

    public void start(String datasource, String[] dimensions, String[] Fname, String[] Foperator, String[] Fvalue,
            String value, String C_detalhe) {
        String key_detail = "";
        String key_master = "";
        String helper = "";

        String[] dimensions_details = new String[dimensions.length + 1];
        //dimensions.
        int i = 0;
        for (; i < dimensions.length; i++) {
            key_master += dimensions[i] + "#";
            dimensions_details[i] = dimensions[i];
        }
        dimensions_details[i] = C_detalhe;
        key_master = key_master.substring(0, key_master.length() - 1);

        /**
         * **
         *
         */
        //Busca todos os atributos que podem ser sumarizados
        /*for (int idx = 0; idx < dimensions_details.length; idx++) {
            if (!key_detail.contains(dimensions_details[idx])) {
                key_detail += dimensions_details[idx] + "#";
            }
            helper = getPrevAtt(datasource, dimensions_details[idx]);
            while (!"".equals(helper)) {
                if (!key_detail.contains(helper)) {
                     += helper + "#";
                }
                helper = getPrevAtt(datasource, helper);
            }
        }*/
        key_detail = key_master + "#" + getPrevAtt(datasource, C_detalhe);
        key_detail = key_detail.toUpperCase();
        dimensions_details = key_detail.split("#");

        MainCube mc = new MainCube();
        mc.generateCube(datasource, dimensions_details, value);

        /*String filters = Util.getFilters(datasource + "_formated");
        String[] nov = filters.split("#");
        String[] filterName = nov[0].split(";");
        String[] filterOperator = nov[1].split(";");
        String[] filterValue = nov[2].split(";");*/

        //this.dice(datasource + "_formated", filterName, filterOperator, filterValue);
        this.dice(datasource + "_formated", Fname, Foperator, Fvalue);
        this.generateCube(datasource + "_formated_dice_drilldown", key_master, key_detail, "_value");
    }

    //Função que busca o próximo atributo a ser detalhado
    public String getPrevAtt(String datasource, String att) {
        DBCollection collection = dbcon.getDb().getCollection("rw_structure");

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("colecao", datasource);

        DBCursor cursor = collection.find(whereQuery);
        String[] attributes1 = cursor.next().toString().split(",");
        String[] helper = attributes1[2].split(":");
        attributes1[2] = helper[1];

        attributes1 = attributes1[2].split("#");

        if (attributes1.length > 0) {
            attributes1[0] = attributes1[0].replace("\"", "");
            attributes1[attributes1.length - 1] = attributes1[attributes1.length - 1].replace("\"}", "");
        }

        for (int idx1 = 0; idx1 < attributes1.length; idx1++) {
            String[] attributes2 = attributes1[idx1].split(";");
            for (int idx2 = 0; idx2 < attributes2.length; idx2++) {
                if (attributes2[idx2].trim().equalsIgnoreCase(att.trim())) {
                    if (idx2 - 1 < 0) {
                        return "";
                    }
                    return attributes2[idx2 - 1].trim();
                }

            }
        }

        return "";
    }

    public void dice(String datasource, String[] filterName, String[] filterOperator, String[] filterValue) {
        DBCollection collection = dbcon.getDb().getCollection(datasource);
        DBCollection insertCollection = dbcon.getDb().getCollection(datasource + "_dice_drilldown");

        insertCollection.drop();

        BasicDBObject whereQuery = new BasicDBObject();

        String operator = "";

        for (int i = 0; i < filterName.length; i++) {
            operator = Util.getOperator(filterOperator[i]);

            if (operator.equals("==")) {
                whereQuery.put(filterName[i], filterValue[i]);
            } else if (operator.equals("$in")) {
                String[] list = filterValue[i].split("#");
                whereQuery.put(filterName[i], new BasicDBObject(operator, list));
            } else {
                whereQuery.put(filterName[i], new BasicDBObject(operator, filterValue[i]));
            }

        }

        DBCursor cursor = collection.find(whereQuery);

        while (cursor.hasNext()) {
            insertCollection.insert(cursor.next());
        }
    }

    public void generateCube(String datasource, String key_master, String key_detail, String value) {

        DBCollection collection = dbcon.getDb().getCollection(datasource);
        DBCollection outputCollection = dbcon.getDb().getCollection(
                datasource + "_temp");
        outputCollection.drop();

        DBCursor cursor = collection.find();

        String key_detail_value;
        MainCube mc = new MainCube();
        String key_master_mr = get_key_master_mr(getKeyValue(collection.findOne().toString()), key_master);
        String key_detail_mr = get_key_master_mr(getKeyValue(collection.findOne().toString()), key_detail);
        String key_mr_filter;

        while (cursor.hasNext()) {
            DBObject next_obj = cursor.next();
			//outputCollection.insert(cursor.next());

            String next = next_obj.toString();
            key_detail_value = get_key_detail_value(next);

            key_mr_filter = get_key_master_mr_filter(next, key_master_mr);

			//System.out.println(next);
            //System.out.println(key_detail_value);
            String[] s = key_detail_value.split("#");
            String key_mr_filter_value = get_key_master_mr_filter_value(key_master_mr, key_detail_value);

			//System.out.println(key_detail);
            //System.out.println(key_detail_value);
            /**
             * MASTER*
             */
			//String map = "function() { if(this._key1 === \"" + s[0] + "\") emit(" + key_master_mr + ", this." + value + ");}";
            String map = "function() { if(" + key_mr_filter + " === \"" + key_mr_filter_value + "\") emit(" + key_master_mr + ", this." + value + ");}";

            String reduce = "function(key, values) { "
                    + "	return Array.sum(values)}";
            MapReduceCommand cmd = new MapReduceCommand(collection, map, reduce,
                    outputCollection.toString(),
                    MapReduceCommand.OutputType.MERGE, null);
            collection.mapReduce(cmd);

			//mc.formatOutput("this._key1 + '#' + this._key2", datasource + "_temp");
            /**
             * DETAIL*
             */
            key_mr_filter = get_key_master_mr_filter(next, key_detail_mr);
            key_mr_filter_value = get_key_master_mr_filter_value(key_detail_mr, key_detail_value);

            map = "function() { if(" + key_mr_filter + " === \"" + key_mr_filter_value + "\") emit(" + key_detail_mr + ", this." + value + ");}";
            reduce = "function(key, values) { "
                    + "	return Array.sum(values)}";
            cmd = new MapReduceCommand(collection, map, reduce,
                    outputCollection.toString(),
                    MapReduceCommand.OutputType.MERGE, null);
            collection.mapReduce(cmd);

        }
        mc.formatOutput(key_detail, datasource + "_temp");

    }

    private String get_key_detail_value(String key_detail_value) {
        String l_key_detail_value = "";
        String key_detail[] = key_detail_value.split(",");

        for (int i = 2; i < key_detail.length - 1; i++) {
            String helper[] = key_detail[i].split(":");
            l_key_detail_value += helper[1].replace("\"", "").trim() + "#";
        }
        l_key_detail_value = l_key_detail_value.substring(0, l_key_detail_value.length() - 1);
        return l_key_detail_value;
    }

    public String getKeyValue(String key) {
        String[] chunk = key.split(":");
        chunk = chunk[3].replace("\"", "").split(",");
        return chunk[0];
    }

    public String get_key_master_mr(String source, String key_master) {
        String[] source_dimensions = source.split("#");
        String[] key_master_dimensions = key_master.split("#");
        String _key = "";

        for (int i = 0; i < key_master_dimensions.length; i++) {
            for (int j = 0; j < source_dimensions.length; j++) {
                if (key_master_dimensions[i].equalsIgnoreCase(source_dimensions[j].trim())) {
                    _key += "this._key" + (j + 1) + " + '#' + ";
                    break;
                }
            }
        }
        _key = _key.substring(0, _key.length() - 9);
        return _key;
    }

    public String get_key_master_mr_filter(String source, String key_master) {
        String[] chunks = source.split(",");
        key_master = key_master.replace(" + '#' + ", ";").replace("this.", "");
        String[] keys = key_master.split(";");
        String key_filter = "";

        for (int i = 0; i < keys.length; i++) {
            for (int j = 2; j < chunks.length; j++) {
                String[] key_value = chunks[j].split(":");
                if (key_value[0].replace("\"", "").trim().equalsIgnoreCase(keys[i])) {
                    key_filter += "this." + key_value[0].replace("\"", "").trim() + " + '#' + ";
                }
            }
        }
        return key_filter.substring(0, key_filter.length() - 9);
    }

    public String get_key_master_mr_filter_value(String key_master, String values) {
        String[] chunks = values.split("#");
        key_master = key_master.replace(" + '#' + ", ";").replace("this.", "");
        String[] keys = key_master.split(";");
        String filter_value = "";

        for (int i = 0; i < keys.length; i++) {
            if (keys[i].trim().equalsIgnoreCase("_key" + (i + 1))) {
                filter_value += chunks[i] + "#";
            }
        }
        return filter_value.substring(0, filter_value.length() - 1);
    }
}
