package app.util;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JComboBox;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import app.connection.*;

public class Util {

    static DBConnection con = new DBConnection();

    public static void main(String[] args) {
        //String s = getFilters("cod_produto#modelo#cidade");
        //System.out.println(s);
        System.out.println(getAttsNames("rw_fato_vendas_formated"));
    }

    public static JComboBox getCollsCombo() {
        Set<String> set = con.getDbCollections();
        JComboBox cbb = new JComboBox();

        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            cbb.addItem(iter.next());
        }

        return cbb;
    }

    public static JComboBox getAttsCombo(String coll) {
        String[] set = con.getCollAtts(coll).split("#");
        JComboBox cbb = new JComboBox();

        cbb.addItem(null);

        for (int i = 0; i < set.length; i++) {
            cbb.addItem(set[i]);
        }

        return cbb;
    }

    public static String getCollections() {
        Set<String> _set = con.getDbCollections();
        String _ret = "";
        int idx = 0;

        Iterator _iter = _set.iterator();
        while (_iter.hasNext()) {
            _ret += "\t" + ++idx + " - " + _iter.next().toString().trim() + ";\n";
        }

        return _ret;
    }

    public static String getAttributes(String _datasource) {
        String[] _set = con.getCollAtts(_datasource).split("#");
        String _ret = "";

        for (int idx = 0; idx < _set.length; idx++) {
            _ret += "\t" + (idx + 1) + " - " + _set[idx].trim() + ";\n";
        }

        return _ret;
    }

    public static String getFilterAtt(String datasource) {
        DBCollection collection = con.getDb().getCollection(datasource);
        String[] _set = collection.findOne().toString().split(":");
        _set = _set[3].split("#");
        _set[0] = _set[0].substring(2, _set[0].length());
        String[] help = _set[_set.length - 1].split("\"");
        _set[_set.length - 1] = help[0];

        System.out.println("Attribute to filter");
        for (int idx = 0; idx < _set.length; idx++) {
            System.out.println(idx + 1 + " - " + _set[idx]);
        }
        System.out.println("0 - Sair");
        String option = scan();

        if (option.equals("0")) {
            return option;
        }

        option = "_key" + option.trim();

        return option;
    }

    public static String[] getAttsNames(String datasource) {
        DBCollection collection = con.getDb().getCollection(datasource);
        String[] _set = collection.findOne().toString().split(":");
        _set = _set[3].split("#");
        _set[0] = _set[0].substring(2, _set[0].length());
        String[] help = _set[_set.length - 1].split("\"");
        _set[_set.length - 1] = help[0];

        return _set;
    }    
    
    public static String getAttKey(String datasource, String key) {
        DBCollection collection = con.getDb().getCollection(datasource);
        String[] _set = collection.findOne().toString().split(":");
        _set = _set[3].split("#");
        _set[0] = _set[0].substring(2, _set[0].length());
        String[] help = _set[_set.length - 1].split("\"");
        _set[_set.length - 1] = help[0];

        for (int idx = 0; idx < _set.length; idx++) {
            if(_set[idx].equalsIgnoreCase(key)){
                return "_key" + (idx+1);
            }
        }
        return "";
    }    
    
    public static String getOperator(String operator) {
        String out = "==";
        switch (operator) {
            case "!=":
                out = "$ne";
                break;
            case ">":
                out = "$gt";
                break;
            case "<":
                out = "$lt";
                break;
            case ">=":
                out = "$gte";
                break;
            case "<=":
                out = "$lte";
                break;
            case "in":
                out = "$in";
                break;
        }
        return out;
    }

    public static String scan() {
        Scanner s = new Scanner(System.in);
        String scan = s.next();

        clearConsole();
        printStart();

        return scan;
    }

    //Função para limpar o console
    public static void clearConsole() {
        for (int i = 0; i < 100; ++i) {
            System.out.println();
        }
    }

    public static void printStart() {
        System.out
                .println("--------------------------------------------------");
        System.out
                .println("----------------- Roberto Walter -----------------");
        System.out
                .println("--------------------------------------------------");
    }

    //Função que faz a leitura da coleção que foi selecionada no console
    public static String getCollection() {
        String[] _collections = printCollections().split(";");

        String _option = scan();

        for (int _idx = 0; _idx < _collections.length; _idx++) {
            String[] _items = _collections[_idx].split("-");
            _items[0] = _items[0].replace("\t", "").trim();

            if (_items[0].equals(_option)) {
                return _items[1].trim();
            }
        }

        return "";
    }

    //Função que imprime as coleções
    public static String printCollections() {
        String collections = getCollections();

        System.out.println("\nCollection");
        System.out.println(collections);

        return collections;
    }

    //Função que faz a leitura da coleção que foi selecionada no console
    public static String getAttribute(String _datasource) {
        String[] _attributes = printAttributes(_datasource).split(";");

        String _option = scan();

        if (_option.equals("0")) {
            return "";
        }

        for (int _idx = 0; _idx < _attributes.length; _idx++) {
            String[] _items = _attributes[_idx].split("-");
            _items[0] = _items[0].replace("\t", "").trim();

            if (_items[0].equals(_option)) {
                return _items[1].trim();
            }
        }

        return "";
    }

    //Função que imprime os atributos de uma coleção
    public static String printAttributes(String _datasource) {
        String attributes = getAttributes(_datasource);

        System.out.println("\nAttributes");
        System.out.println(attributes);
        System.out.println("\t0 - QUIT");
        return attributes;
    }

    public static String getOlapOption() {
        String[] _olap = printOLAPOperators().split(";");

        String _option = scan();

        if (_option.equals("5")) {
            return "";
        }

        for (int _idx = 0; _idx < _olap.length; _idx++) {
            String[] _items = _olap[_idx].split("-");
            _items[0] = _items[0].replace("\t", "").trim();

            if (_items[0].equals(_option)) {
                String _ret = _items[1].trim();
                if (_items.length == 3) {
                    _ret += '-' + _items[2].trim();
                }
                return _ret;
            }
        }

        return "";
    }

    //Função que imprime as opções de operadores OLAP
    public static String printOLAPOperators() {
        System.out.println("\nOLAP Operators");

        String options = "\t1 - Slice;\n"
                + "\t2 - Dice;\n"
                + "\t3 - Roll-Up;\n"
                + "\t4 - Drill-Down;";
        System.out.println(options + "\n\t5 - Sair;");
        return options;
    }

    public static String getFilters(String datasource) {
        String string = "", names = "", operators = "", values = "";

        String option = getFilterAtt(datasource);

        while (!"0".equals(option)) {
            names += option + ";";
            printHelp();
            option = Util.scan();
            operators += option + ";";
            System.out.println("\nValue: ");
            option = Util.scan();
            values += option + ";";

            option = getFilterAtt(datasource);
        }

        string = names + "#" + operators + "#" + values;
        return string;
    }

    public static void printHelp() {
        printStart();
        System.out.println("\nSelect Filter Operator:");
        System.out.println("\t!= - Not equal");
        System.out.println("\t = - Equal");
        System.out.println("\t > - Greater than");
        System.out.println("\t < - Less than");
        System.out.println("\t>= - Greater than or equal to");
        System.out.println("\t<= - Less than or equal to");
        System.out.println("\tin - In the list of given values\n");
    }

    public static String get_key(String value, String key) {
        String[] chunk = value.split("#");
        for (int i = 0; i < chunk.length; i++) {
            if (chunk[i].equalsIgnoreCase(key)) {
                return "_key" + ++i;
            }
        }
        return "";
    }    
    
}
