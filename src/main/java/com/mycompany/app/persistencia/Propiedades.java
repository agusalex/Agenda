package com.mycompany.app.persistencia;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class Propiedades {
    private static Propiedades ourInstance = new Propiedades();
    private static final String DEFAULTPROP="default.properties";
    private static final String CUSTOMPROP="custom.properties";
    private static final String DATABASENAME="agenda";
    private static final String DATABASEOPTIONS="?autoReconnect=true&useSSL=false";

    public static String getDATABASEOPTIONS() {
        return DATABASEOPTIONS;
    }



    public static String getDEFAULTPROP() {
        return DEFAULTPROP;
    }

    public static String getCUSTOMPROP() {
        return CUSTOMPROP;
    }

    public static String getDATABASENAME() {
        return DATABASENAME;
    }

    public static Propiedades getInstance() {
        return ourInstance;
    }

    private Propiedades() {
    }



    public static boolean exists(String property){

        if(getProperties(property)==null){
            return false;
        }
        return true;
    }


    public static void removeDefaults(){
        try{

            File file = new File(DEFAULTPROP);

            if(file.delete()){
                System.out.println(file.getName() + " is deleted!");
            }else{
                System.out.println("Delete operation is failed.");
            }

        }catch(Exception e){

            e.printStackTrace();

        }
    }

    public static boolean setHarcodedDefaults(){

        java.util.Properties prop = new java.util.Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream(DEFAULTPROP);

            // set the properties value
            //prop.store(output,"#Defaults:");
            //"com.mysql.jdbc.Driver"
            prop.setProperty("jdbc.driver","com.mysql.jdbc.Driver" );
            prop.setProperty("jdbc.url", "jdbc:mysql://localhost:3306");
            prop.setProperty("jdbc.username","root");
            prop.setProperty("jdbc.password", "root");

            // save properties to project root folder
            prop.store(output, null);
            return true;

        } catch (IOException io) {
            io.printStackTrace();
            writeErrorLog("Error al escribir las Properties: \n"+io.toString());
        } finally {
            if (output != null) {
                try {

                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return false;

    }

    public static Properties getHardcoded(){
        Properties prop=new Properties();
        prop.setProperty("jdbc.driver","com.mysql.jdbc.Driver" );
        prop.setProperty("jdbc.url", "jdbc:mysql://localhost:3306");
        prop.setProperty("jdbc.username","root");
        prop.setProperty("jdbc.password", "root");
        return prop;

    }

    public static java.util.Properties getProperties(String propsname){

        java.util.Properties props = new java.util.Properties();
        FileInputStream in = null;
        try {
            in = new FileInputStream(propsname);
            props.load(in);
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: "+propsname);

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error de lectura desconocido...");
            return null;
        }

        return props;
    }

    public static boolean setProperties(String filename,String jdbcDriver, String jdbcurl,
                                     String jdbcusername,
                                     String jdbcpassword)
    {
        java.util.Properties prop = new java.util.Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream(filename);

            // set the properties value
            //prop.store(output,"#Defaults:");
            //"com.mysql.jdbc.Driver"
            prop.setProperty("jdbc.driver",jdbcDriver );
            prop.setProperty("jdbc.url", jdbcurl);
            prop.setProperty("jdbc.username",jdbcusername);
            prop.setProperty("jdbc.password", jdbcpassword);

            // save properties to project root folder
            prop.store(output, null);
            return true;

        } catch (IOException io) {
            io.printStackTrace();
            writeErrorLog("Error al escribir las Properties: \n"+io.toString());
        } finally {
            if (output != null) {
                try {

                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return false;
    }


    public static void writeErrorLog(String error){
        BufferedWriter writer = null;
        try {
            //create a temporary file
            String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            File logFile = new File("Error timeLog");

            // This will output the full path where the file will be written to...

            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(error);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the writer regardless of what happens...
                writer.close();
            } catch (Exception e) {
            }
        }


    }
}
