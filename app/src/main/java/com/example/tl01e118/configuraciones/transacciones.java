package com.example.tl01e118.configuraciones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class transacciones extends SQLliteConexion
{


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

    Context context;

    public transacciones(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context=context;
    }

    public boolean editarContacto(int Id, String Nombre, String Numero, String Nota, String Pais){
        boolean correcto = false;

        SQLliteConexion sqLliteConexion = new SQLliteConexion(context,
                transacciones.NameDatabase,
                null,
                1);
        SQLiteDatabase db = sqLliteConexion.getWritableDatabase();
        try {
            db.execSQL("UPDATE " + tablaagenda + " SET nombre = '" + Nombre + "', numero = '" + Numero +"', nota = '" + Nota + "', pais = '" + Pais + "' WHERE id='" + Id + "' ");
            correcto=true;
        }catch (Exception er){
            er.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;
    }


    public boolean eliminarContacto(int id){
        boolean correcto = false;

        SQLliteConexion sqLliteConexion = new SQLliteConexion(context,
                transacciones.NameDatabase,
                null,
                1);
        SQLiteDatabase db = sqLliteConexion.getWritableDatabase();
        try {
            db.execSQL("delete from "+tablaagenda+" where id ='"+id+"'");
            correcto=true;
        }catch (Exception er){
            er.toString();
            correcto=false;
        }finally {
            db.close();
        }

        return correcto;
    }

}
