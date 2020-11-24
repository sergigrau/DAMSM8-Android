package edu.fje.dam2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Classe Activity que mostra els resultats d'una selecció múltiple
 * 
 * Cal afegir al AndroidManifest
 * 
 * <activity android:name=".ResultActivity"
 * android:label="@string/result_activity" > </activity>
 * 
 * @author sergi grau
 * @version 1.0, 19.10.2015
 * @version 2.0, 1/10/2020, actualització a API.30
 */
public class M14_ListActivityIntentDepenentActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.m14_list_activity_intent_depenent);

		Bundle b = getIntent().getExtras();
		ListView lv = (ListView) findViewById(R.id.llistaResultats);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,  b.getStringArrayList("itemsSeleccionats"));
		lv.setAdapter(adapter);
	}
}