package com.example.tl01e118.configuraciones;

public class transacciones {
    //nombre de la base de datos
    public static final String NameDatabase = "TL01E118";

    //creacion de tablas
    public static final String tablaagenda="agenda";

    //Campos de la tabla
    public static String id="id";
    public static String pais="pais";
    public static String nombre="nombre";
    public static String numero="numero";
    public static String nota="nota";

    //consulta SQL

    public static String CreateTBagenda = "CREATE TABLE agenda(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
       "pais TEXT, nombre TEXT, numero TEXT, nota TEXT )";
    public static String DropTBagenda="DROP table if exists agenda ";

}
