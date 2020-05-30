package com.example.as_tp3_oyhambure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SearchRadio extends AppCompatActivity {
    SeekBar seekbar;
    TextView ValorSeekBar;
    EditText PosicionX;
    EditText posicionY;
    EditText Categoria;
    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_radio);
        seekbar = findViewById(R.id.SeekBarRadio);
        PosicionX = findViewById(R.id.PosicionX);
        posicionY = findViewById(R.id.PosicionY);
        Categoria = findViewById(R.id.CategoriaIngresada);
        ValorSeekBar = findViewById(R.id.TextoBarra);
        PosicionX.addTextChangedListener(Required);
        posicionY.addTextChangedListener(Required);
        boton = findViewById(R.id.BotonBuscarRadio);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ValorSeekBar.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private TextWatcher Required = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String TextoUno = PosicionX.getText().toString().trim();
            String TextoDos = posicionY.getText().toString().trim();

            boton.setEnabled(!TextoUno.isEmpty() && !TextoDos.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    public void BuscarRadio(View vista) {
        Intent llamar = new Intent(this, ShowRadio.class);
        Bundle paqueteDeDatos = new Bundle();
        paqueteDeDatos.putString("positionX", PosicionX.getText().toString());
        paqueteDeDatos.putString("posicionY", posicionY.getText().toString());
        paqueteDeDatos.putInt("radio", seekbar.getProgress());
        paqueteDeDatos.putString("categoria", Categoria.getText().toString());
        llamar.putExtras(paqueteDeDatos);
        startActivity(llamar);
    }
}