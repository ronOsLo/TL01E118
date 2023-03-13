package com.example.tl01e118;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tl01e118.configuraciones.SQLliteConexion;
import com.example.tl01e118.configuraciones.transacciones;

public class ActivityIngresar extends AppCompatActivity {
    Spinner pais;
    String Cpais;
    EditText nombre, numero, nota;
    Button guardar, contactos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        pais=(Spinner) findViewById(R.id.spnPais);
        nombre = (EditText) findViewById(R.id.txtNombre);
        numero=(EditText) findViewById(R.id.txtNumero);
        nota=(EditText) findViewById(R.id.txtNota);
        guardar=(Button) findViewById(R.id.btnGuardar);
        contactos=(Button) findViewById(R.id.btnContactos);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.combo_Pais, android.R.layout.simple_spinner_item);
    pais.setAdapter(adapter);

    contactos.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent lista = new Intent(getApplicationContext(),ActivityList.class);
            startActivity(lista);
        }
    });

    guardar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            if(nombre.getText().length()==0)
            {
                Toast.makeText(getApplicationContext(),"Debe ingresar un Nombre ", Toast.LENGTH_SHORT).show();
            } else if (numero.getText().length()==0)
            {
                Toast.makeText(getApplicationContext(),"Debe ingresar un Numero ", Toast.LENGTH_SHORT).show();
            } else if (nota.getText().length()==0)
            {
                Toast.makeText(getApplicationContext(),"Debe ingresar una Nota ", Toast.LENGTH_SHORT).show();
            } else
            {
                Cpais=pais.getSelectedItem().toString();
                guardarContacto();

            }

        }
    });
    }

    private void guardarContacto()
    {
        try {
            SQLliteConexion conexion = new SQLliteConexion(this,
                    transacciones.NameDatabase,
                    null,
                    1);
            SQLiteDatabase db =conexion.getWritableDatabase();
            ContentValues valores =new ContentValues();
            valores.put(transacciones.pais, Cpais.toString());
            valores.put(transacciones.nombre, nombre.getText().toString());
            valores.put(transacciones.numero, numero.getText().toString());
            valores.put(transacciones.nota, nota.getText().toString());

            long resultado = db.insert(transacciones.tablaagenda, transacciones.id, valores);

            Toast.makeText(this, "INGRESADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            limpiarPantalla();
        }catch (Exception er)
        {
            er.toString();
            Toast.makeText(this,er.toString(), Toast.LENGTH_SHORT).show();
        }


    }

    private void limpiarPantalla()
    {

        nombre.setText("");
        numero.setText("");
        nota.setText("");
    }
}