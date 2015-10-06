package app.util;

import app.operators.Dice;
import app.operators.DrillDown;
import app.operators.MainCube;
import app.operators.RollUp;
import app.operators.Slice;

public class Start {

    public static void main(String[] args) {
        String olap;
        String collection;
        String attributes = "";
        Util.printStart();

        collection = Util.getCollection();

        //Seleciona a coleção Delta
        while ("".equals(collection)) {
            collection = Util.getCollection();
        }

        //option = Util.scanInt();
        boolean bool = true;

        //Seleciona as dimensões D
        while (bool) {
            System.out.println("Selecione as Dimensões");
            String att = Util.getAttribute(collection);

            if ("".equals(att)) {
                if (attributes.length() > 0) {
                    bool = false;
                } else {

                }
            } else {
                attributes += ";" + att;
            }
        }

        //Seleciona a medida M
        String measure = "";
        while ("".equals(measure)) {
            System.out.println("Selecione a Medida");
            measure = Util.getAttribute(collection);
        }

        attributes = attributes.substring(1, attributes.length());

        MainCube mc = new MainCube();

        olap = Util.getOlapOption();
        while (!olap.trim().equals("5")) {
            String[] dimensions;
            switch (olap.trim()) {
                case "Slice":
                    mc.generateCube(collection, attributes.split(";"), measure);
                    Slice slice = new Slice();
                    slice.start(collection);
                    break;

                case "Dice":
                    mc.generateCube(collection, attributes.split(";"), measure);
                    Dice dice = new Dice();
                    dice.start(collection + "_formated", attributes);
                    break;
                case "Roll-Up":
                    RollUp ru = new RollUp();
                    dimensions = attributes.split(";");
                    System.out.println("Selecione a chave a ser sumarizada");
                    String C_sumario = Util.getAttribute(collection);
                    //String temp = 
                    ru.start(collection, dimensions, measure, C_sumario);
                    //dimensions = temp.split("#");							
                    break;
                case "Drill-Down":
                    DrillDown dd = new DrillDown();
                    dimensions = attributes.split(";");
                    System.out.println("Selecione a chave a ser detalhadada");
                    String C_detalhe = Util.getAttribute(collection);
                    //String temp = 
                    dd.start(collection, dimensions, measure, C_detalhe);
                    //dimensions = temp.split("#");							
                    break;
            }

            olap = Util.getOlapOption();

        }

        /*Util.printHelp();
		
         MainCube mc = new MainCube();
         mc.generateCube("rw_fato_vendas", "MODELO;ANO;UF".split(";"), "QUANTIDADE");*/
        //Util.clearConsole();
    }

    public void start(String OL, String Delta, String D, String M, String[] Fname, String[] Foperator, String[] Fvalue, String C) {
        MainCube mc = new MainCube();

        //if(OL.trim().equalsIgnoreCase("Slice") || OL.trim().equalsIgnoreCase("Dice")){
        mc.generateCube(Delta, D.split(";"), M);
        //}
        for(int i = 0; i < Fvalue.length && !"".equalsIgnoreCase(Fvalue[i].trim()); i++){
           Fname[i] = Util.getAttKey(Delta + "_formated", Fname[i]);
        }
        
        switch (OL.trim()) {
            case "Slice":
                //mc.generateCube(Delta, D.split(";"), M);
                Slice slice = new Slice();
                //Fname[0] = Util.getAttKey(Delta + "_formated", Fname[0]);
                //start(String datasource, String filterName, String filterValue)
                //slice.start(Delta);
                slice.start(Delta, Fname[0], Fvalue[0]);
                break;

            case "Dice":
                //mc.generateCube(Delta, D.split(";"), M);
                Dice dice = new Dice();
                //dice.start(Delta + "_formated", D);
                dice.start(Delta, Fname, Foperator, Fvalue);
                break;
            case "Roll-Up":
                RollUp ru = new RollUp();
                //System.out.println("Selecione a chave a ser sumarizada");
                //String C_sumario = Util.getAttribute(Delta);
                //ru.start(Delta, D.split(";"), M, C);
                ru.start(Delta, D.split(";"), Fname, Foperator, Fvalue, M, C);
                break;
            case "Drill-Down":
                DrillDown dd = new DrillDown();
                //System.out.println("Selecione a chave a ser detalhadada");
                //String C_detalhe = Util.getAttribute(Delta);
                //dd.start(Delta, D.split(";"), M, C_detalhe);
                dd.start(Delta, D.split(";"), Fname, Foperator, Fvalue, M, C);
                break;
        }

    }
    
}
