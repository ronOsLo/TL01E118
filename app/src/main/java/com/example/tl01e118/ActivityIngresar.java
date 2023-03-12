package com.example.tl01e118;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ActivityIngresar extends AppCompatActivity {
    Spinner pais;
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

    guardar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    });
    }
}