package com.example.tl01e118;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityContacto extends AppCompatActivity {

    Bundle bundle;

    TextView txtNombre, txtNumero, txtNota;
    Spinner spPais;

    Button Eliminar, Editar, Llamar, Compartir;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        bundle=getIntent().getExtras();
        Integer id = bundle.getInt("id");
       String pais = bundle.getString("pais");
       String nombre = bundle.getString("nombre");
        String numero = bundle.getString("numero");
        String nota = bundle.getString("nota");

        txtNombre=(TextView) findViewById(R.id.txtCNombre);
        txtNumero=(TextView) findViewById(R.id.txtCNumero);
        txtNota=(TextView) findViewById(R.id.txtCNota);
        spPais=(Spinner) findViewById(R.id.spCCpais);
        Eliminar=(Button) findViewById(R.id.btnEliminar);
        Editar=(Button) findViewById(R.id.btnEditar);
        Compartir=(Button) findViewById(R.id.btnCompartir);
        Llamar=(Button) findViewById(R.id.btnLlamar);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.combo_Pais, android.R.layout.simple_spinner_item);
        spPais.setAdapter(adapter);

        txtNombre.setText(nombre.toString());
        txtNumero.setText(numero.toString());
        txtNota.setText(nota);
        spPais.setSelection(3);

        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityContacto.this);
                builder.setMessage("Desea Eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                eliminar();

                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });










    }

    private void eliminar()
    {

    }
}