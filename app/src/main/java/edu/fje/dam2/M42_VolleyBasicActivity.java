package edu.fje.dam2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.StringRequest;

/**
 * Classe que demostra el funcionament bàsic de la biblioteca Volley
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 26.11.2018
 * @version 2.0, 1/10/2020 actualització a API30

 */

public class M42_VolleyBasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle instanciaDesada) {
        super.onCreate(instanciaDesada);
        setContentView(R.layout.m42_volley_basic);
        final TextView textSortida = (TextView) findViewById(R.id.sortida);

        RequestQueue cuaPeticions = Volley.newRequestQueue(this);
        String url ="http:/192.168.1.14:8000";

        StringRequest cadenaPeticio = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String resposta) {
                        // Mostra els primers 500 caràcters
                        textSortida.setText("La resposta és: "+ resposta.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textSortida.setText("no funciona!");
            }
        });

        cuaPeticions.add(cadenaPeticio);
    }
}
