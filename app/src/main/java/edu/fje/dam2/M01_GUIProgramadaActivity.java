package edu.fje.dam2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Date;


import androidx.appcompat.app.AppCompatActivity;

/**
 * Classe que hereta de la classe Activity i implementa la interfície
 * OnClickListener continguda a la classe View
 *
 * @author sergi.grau@fje.edu
 * @version 1.0, 29/10/2012
 * @version 2.0, 1/10/2020 actualització a API30
 */
public class M01_GUIProgramadaActivity extends AppCompatActivity implements View.OnClickListener {

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
		mostrarData();
		setContentView(boto);
	}

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
