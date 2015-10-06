package app.operators;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MapReduceCommand;

import app.connection.DBConnection;
import app.util.Util;

public class RollUp {

    private DBConnection dbcon = new DBConnection();

    public static void main(String[] args) {
        String datasource = "rw_fato_vendas";
		//String[] filterName = "_key7;_key5;_key2".split(";");
        //String[] filterOperator = ">=;in;in".split(";");
        //String[] filterValue = "2014;SC#SP#MG;11A013X".split(";");

        RollUp ru = new RollUp();
        //String C_sumario = ru.getNextAtt("rw_fato_vendas", "CIDADE");
        datasource = "rw_fato_vendas";
        //String[] dimensions = "COD_PRODUTO;CIDADE;ANO".split(";");
        String[] dimensions = "CIDADE;ANO".split(";");
        String value = "QUANTIDADE";
        String temp = ru.start(datasource, dimensions, value, "COD_PRODUTO");

        /**
         * **********************************
         * String filters = Util.getFilters(datasource); String[] nov =
         * filters.split("#"); String[] filterName =
         * nov[0].split(";");//"_key2;_key3;_key1".split(";"); String[]
         * filterOperator = nov[1].split(";");//">=;in;in".split(";"); String[]
         * filterValue = nov[2].split(";");//"2014;SC#SP#MG;11A013X".split(";");	         *
         *
         * dimensions = temp.split("#");
         *
         * ru.dice(datasource + "_formated", filterName, filterOperator,
         * filterValue); ru.generateCube(datasource + "_formated_dice_rollup",
         * dimensions, "_value", "cod_produto");
         *
         ****************************************
         */
        System.out.println("ok");
		//MainCube mc = new MainCube();
        //mc.generateCube(datasource, dimensions, value);

//		Dice d = new Dice();
        //	d.dice(datasource+ "_formated", filterName, filterOperator, filterValue);
        //ru.dice(datasource, dimensions, value, filterName, filterOperator, filterValue);
    }

    public String start(String datasource, String[] dimensions, String value, String C_sumario) {

        String key = "";
        String helper = "";

        /**
         * **
         *
         */
        String[] newDimensions = new String[dimensions.length + 1];
        //dimensions.
        int i = 0;
        for (; i < dimensions.length; i++) {
            newDimensions[i] = dimensions[i];
        }
        newDimensions[i] = C_sumario;

        /**
         * **
         *
         */
        //Busca todos os atributos que podem ser sumarizados
        for (int idx = 0; idx < newDimensions.length; idx++) {
            if (!key.contains(newDimensions[idx])) {
                key += newDimensions[idx] + "#";
            }
            helper = getNextAtt(datasource, newDimensions[idx]);
            while (!"".equals(helper)) {
                if (!key.contains(helper)) {
                    key += helper + "#";
                }
                helper = getNextAtt(datasource, helper);
            }
        }
        //key.substring(0, key.length()-2);
        key = key.toUpperCase();
        newDimensions = key.split("#");

        MainCube mc = new MainCube();
        mc.generateCube(datasource, newDimensions, value);

		//ATÉ AQUI FOI FEITO A PRIMEIRA TABELA COM OS DADOS E TODOS OS NÍVEIS DE SUMARIZAÇÃO
        String filters = Util.getFilters(datasource + "_formated");
        String[] nov = filters.split("#");
        String[] filterName = nov[0].split(";");//"_key2;_key3;_key1".split(";");			
        String[] filterOperator = nov[1].split(";");//">=;in;in".split(";");
        String[] filterValue = nov[2].split(";");//"2014;SC#SP#MG;11A013X".split(";");		

        dimensions = key.split("#");

        this.dice(datasource + "_formated", filterName, filterOperator, filterValue);
        this.generateCube(datasource + "_formated_dice_rollup", dimensions, "_value", C_sumario);

		//String[] filterName = "_key2;_key3;_key1".split(";");
        //String[] filterOperator = ">=;in;in".split(";");
        //String[] filterValue = "2014;SC#SP#MG;11A013X".split(";");	
        //this.dice(datasource, filterName, filterOperator, filterValue);
        return key;
    }

    public void start() {
        System.out.println("ok");
    }

    public String start(String datasource, String[] dimensions, String[] Fname, String[] Foperator, String[] Fvalue,
            String value, String C_sumario) {

        String key = "";
        String helper = "";

        String[] newDimensions = new String[dimensions.length + 1];
        int i = 0;
        for (; i < dimensions.length; i++) {
            newDimensions[i] = dimensions[i];
            key += dimensions[i] + "#";
        }
        newDimensions[i] = C_sumario;
        
        key = key.replace(C_sumario.trim(), getNextAtt(datasource, C_sumario));
        key = key.toUpperCase();
        newDimensions = key.split("#");

        MainCube mc = new MainCube();
        mc.generateCube(datasource, newDimensions, value);

        dimensions = key.split("#");

        this.dice(datasource + "_formated", Fname, Foperator, Fvalue);
        //this.generateCube(datasource + "_formated_dice_rollup", dimensions, "_value", C_sumario);
        return key;
    }

    public void rollup(String datasource, String sumarizar, String[] dimensions,
            String value) {

        DBCollection collection = dbcon.getDb().getCollection(datasource);
        DBCollection insertCollection = dbcon.getDb().getCollection(datasource + "_rollup_temp");

        insertCollection.drop();

        RollUp ru = new RollUp();
        String newkey = dimensions[0].replace(sumarizar, ru.getNextAtt(datasource, sumarizar));

        BasicDBObject whereQuery = new BasicDBObject();
        DBCursor cursor = collection.find(whereQuery);

        while (cursor.hasNext()) {

        }
    }

    //Função que busca o próximo atributo a ser sumarizado
    public String getNextAtt(String datasource, String att) {
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
                    if (idx2 + 1 == attributes2.length) {
                        return "";
                    }
                    return attributes2[idx2 + 1];
                }

            }
        }

        return "";
    }

    public void dice(String datasource, String[] filterName, String[] filterOperator, String[] filterValue) {
        DBCollection collection = dbcon.getDb().getCollection(datasource);
        DBCollection insertCollection = dbcon.getDb().getCollection(datasource + "_dice_rollup");

        insertCollection.drop();

        BasicDBObject whereQuery = new BasicDBObject();

        String operator = "";

        for (int i = 0; i < filterName.length; i++) {
            if("".equalsIgnoreCase(filterValue[i].trim())) break;
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
        collection.drop();
    }

    public void generateCube(String datasource, String[] dimensions, String value, String sumarize) {

        DBCollection collection = dbcon.getDb().getCollection(datasource);
        DBCollection outputCollection = dbcon.getDb().getCollection(datasource + "_teste");

        //String key = "this." + dimensions[0];
        String key = "";
        String format = "";
        for (int idx = 0; idx < dimensions.length; idx++) {
            if (!dimensions[idx].equalsIgnoreCase(sumarize)) {
                key += "+ '#' + this._key" + (idx + 1);// dimensions[idx];
                format += "#" + dimensions[idx].toUpperCase();
            }
        }
        key = key.substring(8, key.length());
        format = format.substring(1, format.length());

        String map = "function() {emit(" + key + ", this." + value + ");}";
        String reduce = "function(key, values) { return Array.sum(values)}";

        MapReduceCommand cmd = new MapReduceCommand(collection, map, reduce,
                outputCollection.toString(),
                MapReduceCommand.OutputType.REPLACE, null);
        collection.mapReduce(cmd);

        MainCube cc = new MainCube();
        //cc.formatOutput(key, outputCollection.toString());
        cc.formatOutput(format, outputCollection.toString());
        
    }
}
