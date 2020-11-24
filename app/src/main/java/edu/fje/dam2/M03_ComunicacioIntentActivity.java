package edu.fje.dam2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

/**
 * Classe que hereta de la classe Activity i implementa la interfície
 * OnClickListener continguda a la classe View
 *
 * Cal afegir l'activitat en el Manifest
 * 
 * @author sergi.grau@fje.edu
 * @version 1.0, 22/10/2013
 * @version 2.0, 1/10/2020 actualització a API30
 */
public class M03_ComunicacioIntentActivity extends AppCompatActivity implements View.OnClickListener {

	public static final String MISSATGE_CLAU = "edu.fje.dam2.data";
	private Button boto;

	/**
     * Cal inicialitzar l'activitat la primera vegada que és creada. 
     * cridant a {@link Activity#setContentView setContentView()}
     * descriu que es mostrarà a la pantalla
     */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		boto = new Button(this);
		boto.setOnClickListener(this);
		boto.setTextSize(20);
		mostrarData();
		setContentView(boto);
	}

	public void onClick(View arg0) {
		enviarMissatge(arg0);
	}

	/**
	 * Mètode privat que assigna la data a un botó
	 */
	private void mostrarData() {
		boto.setText(new Date().toString());
	}
	/**
	 * Mètode que crea un Intent i el passa a una activity per a mostrar
	 * les dades que s'envien amb el missatge
	 * @param view
	 */
	public void enviarMissatge(View view) {
	    Intent intent = new Intent(this, M03_ComunicacioIntentDepenentActivity.class);
	    String missatge = boto.getText().toString();
	    intent.putExtra(MISSATGE_CLAU, missatge);
	    startActivity(intent);
	}
}
