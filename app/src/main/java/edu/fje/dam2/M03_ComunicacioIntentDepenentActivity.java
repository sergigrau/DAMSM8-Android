package edu.fje.dam2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Classe que hereta de la classe Activity i
 * es cridada mitjançant un Intent
 *
 * @author sergi grau
 * @version 1.0, 22/10/2013
 * @version 2.0, 1/10/2020 actualització a API30
 */

public class M03_ComunicacioIntentDepenentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m03_comunicacio_intent_depenent);

        Intent intent = getIntent();
        String missatge = intent.getStringExtra(M03_ComunicacioIntentActivity.MISSATGE_CLAU);
        TextView textView = findViewById(R.id.textData);
        textView.setTextSize(20);
        textView.setText(missatge);
    }


}
