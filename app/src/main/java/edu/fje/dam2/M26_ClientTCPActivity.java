/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.fje.dam2;

/**
 * Activitat que es comunica amb una Activitat servidora
 * mitjançant sócols i el protocol TCP
 * @author sergi.grau@fje.edu
 * @version 1.0 5.02.2015
 * @version 2.0, 1/10/2020 actualització a API30
 */

import java.io.*;
import java.net.*;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activitat que definieix un client
 * mitjançant sòcols i el protocol TCP
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 9.01.2015
 */
public class M26_ClientTCPActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		StrictMode.ThreadPolicy politiques = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(politiques);

		String res = "ERROR";
		try {

			Socket s = new Socket("192.168.1.13", 12345);
			System.out.println("C: Connectat al servidor" + s.toString());
			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					s.getInputStream()));

			out.println("SERGI");
			res = in.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}

		TextView tv = new TextView(this);
		tv.setText("C: " + res);
		setContentView(tv);
	}
}
