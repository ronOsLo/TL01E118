package com.example.tl01e118;

import static com.example.tl01e118.R.id.btbIngresar;
import static com.example.tl01e118.R.id.btnListar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
 Button agregar, listar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       agregar=(Button) findViewById(btbIngresar);
       listar=(Button) findViewById(btnListar);

       agregar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent agrega = new Intent(getApplicationContext(),ActivityIngresar.class);
               startActivity(agrega);
           }
       });

       listar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent lista = new Intent(getApplicationContext(),ActivityList.class);
               startActivity(lista);
           }
       });

    }
}