package edu.fje.dam2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Classe que hereta de la classe Activity i que mostra com
 * utilitzar un giny GridView per a mostrar diverses opcions en forma de graella
 * Implementa la interfície  AdapterView.OnItemSelectedListener 
 * Es defineix un descriptor XML per cadascuna de les cel·les, el qual és un TextView
 * Utilitza un ArrayAdapter que permet utilitzar el patró MVC
 * @author sergi.grau@fje.edu
 * @version 1.0, 22/11/2012
 * @version 2.0, 1/10/2020 actualització a API30
 */

public class M17_CellesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
	private TextView seleccio;
	private  String[] items = { "pomes", "peres", "taronges",
			"plàtans", "pinyes", "mango", "llimones" };

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.m17_celles);
		seleccio = (TextView) findViewById(R.id.seleccio);
		GridView g = (GridView) findViewById(R.id.grid);
		g.setAdapter(new ArrayAdapter<String>(this, R.layout.m17_definicio_cella, items));
		g.setOnItemSelectedListener(this);
	}
	
	 @Override
	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
		Log.v("dam2", items[position]);
		seleccio.setText(items[position]);
	}
	 @Override
	public void onNothingSelected(AdapterView<?> parent) {
		seleccio.setText("");
	}
}
