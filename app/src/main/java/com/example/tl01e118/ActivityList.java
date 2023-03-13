package com.example.tl01e118;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tl01e118.configuraciones.SQLliteConexion;
import com.example.tl01e118.configuraciones.transacciones;
import com.example.tl01e118.tablas.contactos;

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity {
SQLliteConexion conexion;
ListView listaContactos;
ArrayList<contactos> lista;
ArrayList<String> arregloContactos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        conexion = new SQLliteConexion(this, transacciones.NameDatabase,null,1);
        listaContactos=(ListView) findViewById(R.id.listaContactos);

        obtenerListaContactos();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arregloContactos);
        listaContactos.setAdapter(adp);

    }

    private void obtenerListaContactos()
    {
        SQLiteDatabase db =conexion.getReadableDatabase();
        contactos contac =null;
        lista=new ArrayList<contactos>();

        Cursor cursor = db.rawQuery("select * from "+ transacciones.tablaagenda,null);
        while (cursor.moveToNext())
        {
           contac=new contactos();
           contac.setId(cursor.getInt(0));
           contac.setPais(cursor.getString(1));
           contac.setNombre(cursor.getString(2));
           contac.setNumero(cursor.getString(3));
           contac.setNota(cursor.getString(4));
           lista.add(contac);
        }
        cursor.close();
        filllist();

    }

    private void filllist()
    {
        arregloContactos=new ArrayList<String>();
        for (int i=0; i<lista.size();i++)
        {
            arregloContactos.add(lista.get(i).getId()+" | "+
                    lista.get(i).getPais()+" | "+
                    lista.get(i).getNombre()+" | "+
                    lista.get(i).getNumero()+" | "+
                    lista.get(i).getNota());
        }
    }
}