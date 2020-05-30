package com.example.as_tp3_oyhambure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ProximaVista(View vista) {
        int id = vista.getId();
        if (id == R.id.BotonNombre) {
            Intent SearchByName = new Intent(this, SearchName.class);
            startActivity(SearchByName);
        }
        if (id == R.id.BotonCategoria) {
            Intent SearchByCategory = new Intent(this, SearchCategory.class);
            startActivity(SearchByCategory);
        }
        if (id == R.id.BotonRadio) {
            Intent SearchByZone = new Intent(this, SearchRadio.class);
            startActivity(SearchByZone);
        }
    }
}