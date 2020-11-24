package edu.fje.dam2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


/**
 * Classe que hereta de la classe Activity
 * Utilitza un layout XML per a definir la interfície amb diversos ginys
 * i un layout de tipus GridLayout.
 * @author sergi.grau@fje.edu
 * @version 1.0, 18/11/2012
 * @version 2.0, 1/10/2020, actualització a API.30
 */
public class M13_GridLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.m13_grid_layout);

    }

}


