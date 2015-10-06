/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.model;

import app.connection.DBConnection;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.Collection;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;

/**
 *
 * @author roberto
 */
public class Model {

    DBConnection dbConnection;// = new DBConnection();

    public Model(String connection){
        String[] chunks = connection.split(":");
        String host = chunks[0];
        chunks = chunks[1].split("/");
        int port = Integer.parseInt(chunks[0]);
        String dbName = chunks[1];
        
        dbConnection = new DBConnection(host, port, dbName);
    }

    public Model(){
        dbConnection = new DBConnection();
    }
        
    public DBConnection getDb() {
        return dbConnection;
    }

    public void setDb(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }
    
    public DBCollection getCollection(String coll){
        return dbConnection.getDb().getCollection(coll);
    }

    public Set<String> getDbCollections() {
        Set<String> colls = null;
        colls = dbConnection.getDb().getCollectionNames();
        return colls;
    }

    public String getCollAtts(String coll) {
        //DBCollection c = null;
        String[] s;
        String set = "";

        //c = getDb().getCollection(coll);
        s = dbConnection.getDb().getCollection(coll).findOne().toString().split(",");
        //s = c.findOne().toString().split(",");

        for (int i = 1; i < s.length; i++) {
            String[] ss = s[i].split(":");
            set = set + ss[0].replace('"', ' ') + "#";
        }

        return set;
    }
    
    public String[] getAttsCombo(String coll) {
        String[] set = dbConnection.getCollAtts(coll).split("#");
        return set;
    }    

    public void setTable(JTable table, String collection) {
        DBCollection col = this.getCollection(collection);
        DBCursor cur = col.find();

        String[] columnNames = {"id", "CIDADE", "UF"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        while (cur.hasNext()) {
            DBObject obj = cur.next();
            String cidade = (String) obj.get("CIDADE");
            String uf = (String) obj.get("UF");
            ObjectId id = (ObjectId) obj.get("_id");
            model.addRow(new Object[]{id, cidade, uf});
        }
        cur.close();
        table.setModel(model);
    }    
}
