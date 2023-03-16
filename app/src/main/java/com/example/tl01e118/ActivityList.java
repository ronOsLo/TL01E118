package com.example.tl01e118;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.tl01e118.configuraciones.SQLliteConexion;
import com.example.tl01e118.configuraciones.transacciones;
import com.example.tl01e118.tablas.contactos;

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity implements View.OnClickListener, SearchView.OnQueryTextListener {
    Integer id;
    String Dnombre, Dpais, Dnumero, Dnota;




SQLliteConexion conexion;
ListView listaContactos;
ArrayList<contactos> lista;
ArrayList<String> arregloContactos, OriginalContactos;
//buscador
SearchView buscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        conexion = new SQLliteConexion(this, transacciones.NameDatabase,null,1);
        listaContactos=(ListView) findViewById(R.id.listaContactos);
        //buscador
        buscar=(SearchView) findViewById(R.id.txtBuscar);

        obtenerListaContactos();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arregloContactos);
        listaContactos.setAdapter(adp);

        listaContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               id=lista.get(i).getId();
               Dnombre=lista.get(i).getNombre().toString();
               Dnumero=lista.get(i).getNumero().toString();
              Dpais=lista.get(i).getPais().toString();
               Dnota=lista.get(i).getNota().toString();

                abrirDetalle();
            }
        });
       // buscar.setOnQueryTextListener(this);
    }

    private void abrirDetalle()
    {
        Intent inten = new Intent(getApplicationContext(),ActivityContacto.class);

         inten.putExtra("id",id);
         inten.putExtra("nombre",Dnombre);
        inten.putExtra("numero",Dnumero);
        inten.putExtra("pais",Dpais);
        inten.putExtra("nota",Dnota);
        startActivity(inten);

    }

    public void obtenerListaContactos()
    {
        SQLiteDatabase db =conexion.getReadableDatabase();
        contactos contac =null;
        lista=new ArrayList<contactos>();

        Cursor cursor = db.rawQuery("select * from  "+ transacciones.tablaagenda,null);
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


    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

}