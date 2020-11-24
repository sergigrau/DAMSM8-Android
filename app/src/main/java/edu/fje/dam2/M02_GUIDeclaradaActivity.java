package edu.fje.dam2;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

/**
 * Classe que hereta de la classe Activity i implementa
 * la interfície OnClickListener continguda a la classe View
 * Utilitza un layout XML per a definir la interfície
 * @author sergi.grau@fje.edu
 * @version 1.0, 06/11/2012
 * @version 2.0, 1/10/2020, actualització a API.30
 */
public class M02_GUIDeclaradaActivity extends AppCompatActivity implements View.OnClickListener {
    private Button boto;

    /** Mètode que es crida quan es crea l'Activitat, normalment correspon
    a una inicialització de la GUI*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        // mira /res/layout/m02_gui_declarada.xmllarada.xml per
        // a la descripció de la vista, la qual serà el contingut de la pantalla
        setContentView(R.layout.m02_gui_declarada);
        boto = (Button) findViewById(R.id.boto);
        // aquest exercici fa servir onclick en el layout en comptes de boto.setOnClickListener(this);
        mostrarData();
    }

    /**
     * Aquest mètode el cridem directament des del layout
     * @param arg0
     */
    public void onClick(View arg0) {
        mostrarData();
    }

    /**
     * Mètode privat que assigna la data a un botó
     */
    private void mostrarData() {
        boto.setText(new Date().toString());
    }
}
