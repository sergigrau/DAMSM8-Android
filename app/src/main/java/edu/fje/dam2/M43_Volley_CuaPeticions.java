package edu.fje.dam2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;

/**
 * Classe que demostra la personalització de Volley amb cau i gestió de xarxa
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 26.11.2018
 * @version 2.0, 1/10/2020 actualització a API30
 */


public class M43_Volley_CuaPeticions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle instanciaDesada) {
        super.onCreate(instanciaDesada);
        setContentView(R.layout.m42_volley_basic);
        final TextView textSortida = (TextView) findViewById(R.id.sortida);

        RequestQueue cuaPeticions;

        // gestió de la cau
        Cache cau = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        // configuració de la xarxa per utilitzar HttpURLConnection com a HTTP client.
        Network xarxa = new BasicNetwork(new HurlStack());

        // RequestQueue amb cau i xarxa
        cuaPeticions = new RequestQueue(cau, xarxa);

        // inici de la cua
        cuaPeticions.start();

        String url ="http://192.168.1.14:8000";

        // petició i gestió asícrona de la resposta.
        StringRequest peticio = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String resposta) {
                        textSortida.setText("Resposta ok!");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textSortida.setText("no funciona!");
                    }
                });

        // afegir petició a la cua
        cuaPeticions.add(peticio);
    }
}
