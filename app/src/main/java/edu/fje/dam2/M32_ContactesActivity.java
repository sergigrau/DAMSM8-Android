package edu.fje.dam2;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Classe Activity que envia un intent
 * per accedir i recuperar informació dels 
 * contactes
 * @author sergi.grau@fje.edu
 * @version 1.0 20/11/2013
 *
 */

public class M32_ContactesActivity extends AppCompatActivity {
	static final int CODI_PETICIO = 1; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.m32_activity_contactes);
	}

	@Override
	protected void onActivityResult(int codiPeticio, int resultCode, Intent data) {
		super.onActivityResult(codiPeticio, resultCode, data);
		if (codiPeticio == CODI_PETICIO) {
			if (resultCode == RESULT_OK) {
				Uri contactUri = data.getData();
				String[] projeccio = {Phone.NUMBER};
				Cursor cursor = getContentResolver()
						.query(contactUri, projeccio, null, null, null);
				cursor.moveToFirst();

				int columna = cursor.getColumnIndex(Phone.NUMBER);
				String nombre = cursor.getString(columna);

				Log.i("ContactesActivity", nombre);
			}
		}
	}
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.contactes, menu);
		return true;
	}
	*/
	public void mostrarNomsContactes(View vista) {
	    Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
	    pickContactIntent.setType(Phone.CONTENT_TYPE); // mostra només contactes amb numero de telf
	    startActivityForResult(pickContactIntent, CODI_PETICIO);
	}
}
