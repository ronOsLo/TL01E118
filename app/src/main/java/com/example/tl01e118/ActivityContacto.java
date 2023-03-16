package com.example.tl01e118;

import static com.example.tl01e118.configuraciones.transacciones.tablaagenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tl01e118.configuraciones.SQLliteConexion;
import com.example.tl01e118.configuraciones.transacciones;

public class ActivityContacto extends AppCompatActivity {

    Bundle bundle;

    EditText txtNombre, txtNumero, txtNota;
    Spinner spPais;

    Button Eliminar, Editar, Llamar, Compartir ,Guardar;







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

        txtNombre=(EditText) findViewById(R.id.txtCNombre);
        txtNumero=(EditText) findViewById(R.id.txtCNumero);
        txtNota=(EditText) findViewById(R.id.txtCNota);
        spPais=(Spinner) findViewById(R.id.spCCpais);
        Eliminar=(Button) findViewById(R.id.btnEliminar);
        Editar=(Button) findViewById(R.id.btnEditar);
        Compartir=(Button) findViewById(R.id.btnCompartir);
        Llamar=(Button) findViewById(R.id.btnLlamar);
        Guardar=(Button) findViewById(R.id.btnCGuardar);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.combo_Pais, android.R.layout.simple_spinner_item);
        spPais.setAdapter(adapter);

        txtNombre.setText(nombre.toString());
        txtNumero.setText(numero.toString());
        txtNota.setText(nota);

        String honduras = "Honduras (504)";
        String guatemala = "Guatemala (502)";
        String costarica = "Costa Rica (506)";
        String nicaragua = "Nicaragua (505)";
        String panama = "Panama (507)";

        if(honduras.equals(pais)) {
            spPais.setSelection(0);
            Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
        }
        if(guatemala.equals(pais)) {
            spPais.setSelection(1);
            Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
        }
        if(costarica.equals(pais)) {
            spPais.setSelection(2);
        }
        if(nicaragua.equals(pais)) {
            spPais.setSelection(3);
        }
        if(panama.equals(pais)) {
            spPais.setSelection(4);
        }

        Editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtNombre.setEnabled(true);
                txtNumero.setEnabled(true);
                spPais.setVisibility(View.VISIBLE);
                txtNota.setVisibility(View.VISIBLE);
                Guardar.setVisibility(View.VISIBLE);
                Editar.setVisibility(View.INVISIBLE);
                Eliminar.setVisibility(View.INVISIBLE);
                Llamar.setVisibility(View.INVISIBLE);
                Compartir.setVisibility(View.INVISIBLE);

            }
        });

        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String EdNombre, EdNumero, EdNota, EdPais;

                EdNombre=txtNombre.getText().toString();
                EdNumero=txtNumero.getText().toString();
                EdNota=txtNota.getText().toString();
                EdPais=spPais.getSelectedItem().toString();
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityContacto.this);
                builder.setMessage("Desea Actualizar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                transacciones tran = new transacciones(ActivityContacto.this,transacciones.NameDatabase,null,1);
                                if( tran.editarContacto(id,EdNombre,EdNumero,EdNota,EdPais)){
                                    lista();
                                };
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
        Compartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent compartir = new Intent(android.content.Intent.ACTION_SEND);
                compartir.setType("text/plain");
                compartir.putExtra(android.content.Intent.EXTRA_SUBJECT,"Empleos Baja App");
                compartir.putExtra(android.content.Intent.EXTRA_TEXT,nombre+" "+numero);
                startActivity(Intent.createChooser(compartir, "Compartir vía"));

            }
        });
        Llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Uri num = Uri.parse("tel:"+numero);
                Intent i = new Intent(Intent.ACTION_CALL, num);
                startActivity(i);
            }
        });
        Eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ActivityContacto.this);
                builder.setMessage("Desea Eliminar este contacto?")
                        .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                transacciones tran = new transacciones(ActivityContacto.this,transacciones.NameDatabase,null,1);
                               if( tran.eliminarContacto(id)){
                                   lista();
                               };
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

private void lista(){
        Intent intent = new Intent(this, ActivityList.class);
        startActivity(intent);
}

}