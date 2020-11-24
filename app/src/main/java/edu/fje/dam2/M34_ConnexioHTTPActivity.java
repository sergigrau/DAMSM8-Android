package edu.fje.dam2;

import java.net.*;
import java.io.*;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.content.Context;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
/*
 * 
 *  <uses-permission android:name="android.permission.WRITE_SETTINGS"/>
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    <uses-permission android:name="android.permission.INTERNET"/>
 * 
 */

/**
 * Classe que utilitza la classe HTTPURLConnection, per a enviar i rebre dades d'Internet.
 * S'utilitza quan no es coneix la longitud de les dades a enviar i rebre
 * @author sergi.grau@fje.edu
 * @version 1.0 4/04/2013
 * @version 2.0, 1/10/2020 actualització a API30
 */
public class M34_ConnexioHTTPActivity extends AppCompatActivity {

	private TextView text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.m34_connexio_http);
		text = (TextView) findViewById(R.id.text);
		
		//per evitar que el fil de la GUI tingui ocupada la comunicació
		//caldria millorar-ho amb un servei
		StrictMode.ThreadPolicy politiques = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
		StrictMode.setThreadPolicy(politiques);

		//en el cas d'un proxy
		// Settings.System.putString(getContentResolver(),
		// Settings.System.HTTP_PROXY, "proxy:8080");

		try {
			URL url = new URL("http://www.google.com");
			HttpURLConnection connexio = (HttpURLConnection) url.openConnection();
			llegirStream(connexio.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void llegirStream(InputStream in) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String linia = "";
			StringBuilder linies = new StringBuilder();
			
			while ((linia = reader.readLine()) != null) {
				linies.append(linia);
			}
			text.setText(linies.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	public boolean isXarxaDisponible() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}

}
