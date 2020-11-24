package edu.fje.dam2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Classe que hereta de la classe Activity
 * Demostra la superposició de ginys en la  interfície.
 * i un layout de tipus RelativeLayout.
 * @author sergi.grau@fje.edu
 * @version 1.0, 16/11/2012
 * @version 2.0, 1/10/2020, actualització a API.30
 */
public class M10_SuperposicioGinysActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.m10_superposicio_ginys);

    }

}


