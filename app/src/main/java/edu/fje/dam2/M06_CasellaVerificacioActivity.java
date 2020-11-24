package edu.fje.dam2;

import android.app.Activity;
import android.os.Bundle;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Classe que hereta de la classe Activity i implementa
 * la interfície OnCheckedChangeListener continguda a la classe CompoundButton
 * Utilitza un layout XML per a definir la interfície i mostra una casella de
 * verificació
 *
 * @author sergi.grau@fje.edu
 * @version 2.0, 1/10/2020, actualització a API.30
 */
public class M06_CasellaVerificacioActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m06_casella_verificacio);
        cb = (CheckBox) findViewById(R.id.check);
        cb.setOnCheckedChangeListener(this);
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            cb.setText("el checkbox està marcat");
        } else {
            cb.setText("el checkbox està desmarcat");
        }
    }
}


