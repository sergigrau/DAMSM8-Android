package edu.fje.dam2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Classe que hereta de la classe Activity i implementa
 * la interfície OnCheckedChangeListener continguda a la classe RadioGroup
 * Utilitza un layout XML per a definir la interfície amb diversos ginys
 * i un layout de tipus LinearLayout. Utilitza RadioButtons per a modificar
 * les propietats del layout
 * @author sergi.grau@fje.edu
 * @version 1.0, 06/11/2012
* @version 2.0, 1/10/2020, actualització a API.30
 */
public class M08_BotonsRadioOrientacioActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    RadioGroup orientacio;
    RadioGroup gravetat;

    @Override
   	protected void onCreate(Bundle savedInstanceState) {
   		super.onCreate(savedInstanceState);
        setContentView(R.layout.m08_botons_radio_orientacio);
        orientacio = (RadioGroup) findViewById(R.id.orientation);
        orientacio.setOnCheckedChangeListener(this);
        gravetat = (RadioGroup) findViewById(R.id.gravity);
        gravetat.setOnCheckedChangeListener(this);
    }
    
    @Override
    public void onCheckedChanged(RadioGroup grup, int checkedId) {
        switch (checkedId) {

            case R.id.horizontal:
                orientacio.setOrientation(LinearLayout.HORIZONTAL);
                break;
            case R.id.vertical:
                orientacio.setOrientation(LinearLayout.VERTICAL);
                break;
            case R.id.left:
                gravetat.setGravity(Gravity.LEFT);
                break;
            case R.id.center:
                gravetat.setGravity(Gravity.CENTER_HORIZONTAL);
                break;
            case R.id.right:
                gravetat.setGravity(Gravity.RIGHT);
                break;
        }
    }
}


