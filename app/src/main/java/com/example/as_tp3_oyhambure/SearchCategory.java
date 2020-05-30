package com.example.as_tp3_oyhambure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SearchCategory extends AppCompatActivity {
    public ListView MiListaCatgorias;
    public ArrayAdapter arrayAdapter;
    public ArrayList < String > ListaCategorias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ListaCategorias = new ArrayList < > ();
        MiListaCatgorias = findViewById(R.id.ListaMostrar);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ListaCategorias);
        Tareita miTarea = new Tareita();
        miTarea.execute();
        MiListaCatgorias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView < ? > parent, View view, int position, long id) {
                String Cat = ListaCategorias.get(position);
                IrAPantalla(Cat);

            }
        });
    }
    private void IrAPantalla(String categoria) {
        Intent llamar = new Intent(this, ShowCategory.class);
        Bundle paqueteDeDatos = new Bundle();
        Log.d("valor", categoria);
        paqueteDeDatos.putString("InformacionElegida", categoria);
        llamar.putExtras(paqueteDeDatos);
        startActivity(llamar);
    }
    private class Tareita extends AsyncTask < Void, Void, Void > {
        @Override
        protected Void doInBackground(Void...voids) {

            try {
                URL miRuta = new URL("https://epok.buenosaires.gob.ar/getCategorias/");
                HttpURLConnection MiConexion = (HttpURLConnection) miRuta.openConnection();

                if (MiConexion.getResponseCode() == 200) {
                    Log.d("Conexion", "Funciona");
                    InputStream cuerpoRespuesta = MiConexion.getInputStream();
                    InputStreamReader lectorRespuesta = new InputStreamReader(cuerpoRespuesta, "UTf-8");
                    procesarJSONLeido(lectorRespuesta);
                } else {
                    Log.d("Conexion", "Error en la conexion");
                }
                MiConexion.disconnect();

            } catch (Exception e) {
                Log.d("Error", "Error en el primer try catch   " + e.getMessage());
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            MiListaCatgorias.setAdapter(arrayAdapter);
        }
    }
    public void procesarJSONLeido(InputStreamReader streamLeido) {
        JsonReader JSONLeido = new JsonReader(streamLeido);
        try {
            JSONLeido.beginObject();
            while (JSONLeido.hasNext()) {
                String NombreElemtoActual = JSONLeido.nextName();

                if (NombreElemtoActual.equals("cantidad_de_categorias")) {
                    int cantidadCategorias = JSONLeido.nextInt();
                } else {
                    JSONLeido.beginArray();
                    while (JSONLeido.hasNext()) {
                        JSONLeido.beginObject();
                        while (JSONLeido.hasNext()) {
                            NombreElemtoActual = JSONLeido.nextName();
                            if (NombreElemtoActual.equals("nombre")) {
                                String valorElementoActual = JSONLeido.nextString();
                                ListaCategorias.add(valorElementoActual);
                            } else {
                                JSONLeido.skipValue();
                            }
                        }
                        JSONLeido.endObject();
                    }
                    JSONLeido.endArray();
                }
            }

        } catch (Exception e) {

        }
    }
}