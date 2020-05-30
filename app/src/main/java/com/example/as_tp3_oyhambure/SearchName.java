package com.example.as_tp3_oyhambure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SearchName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_name);

    }
    public void RecaudarInformacion(View vista) {
        EditText editText;
        editText = findViewById(R.id.NombreIngresado);

        String nombre = editText.getText().toString();
        Intent llamar = new Intent(this, ShowCategory.class);
        Bundle paqueteDeDatos = new Bundle();
        paqueteDeDatos.putString("InformacionElegida", nombre);
        llamar.putExtras(paqueteDeDatos);
        startActivity(llamar);
    }
}