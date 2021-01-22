package edu.fje.dam2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Classe que utilitza la classe HTTPURLConnection, per a enviar i rebre dades
 * en format JSON. Els fitxers de backend es troben al directori /assets
 * el backend està desenvolupat en llenguatge PHP
 *
 * @author sergi.grau@fje.edu
 * @version 3.0 16.12.2016
 * @version 2.0, 1/10/2020 actualització a API30
 */
public class M36_ComunicacioJSONActivity extends AppCompatActivity {
    //cal desactivar proxy d'Android
    //cal posar a res/xml un fixter  res/xml/network_security_config.xml
    /*
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">192.168.1.14</domain>
    </domain-config>
</network-security-config>

    //i afegir a manifest
     <application
    android:networkSecurityConfig="@xml/network_security_config"
     */
    private static final String URL = "http://172.20.16.207:8000/?nom=" ;

    private Button botoEnviar;
    private TextView nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // per evitar que el fil de la GUI tingui ocupada la comunicació
        // caldria millorar-ho amb un servei
        StrictMode.ThreadPolicy politiques = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(politiques);

        setContentView(R.layout.m34_connexio_http);

        botoEnviar = (Button) findViewById(R.id.botoEnviar) ;
        nom = (TextView) findViewById(R.id.nom) ;

        botoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //enviar dades a Backend
                try {
                    URL urlObj = new URL(URL+nom.getText().toString());
                    HttpURLConnection clientHTTP = (HttpURLConnection) urlObj.openConnection();
                    InputStream is = clientHTTP.getInputStream();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        String dadesLLegides = null;
        try {
            dadesLLegides = llegirJSON();
            Log.i(M36_ComunicacioJSONActivity.class.getName(), dadesLLegides);
            nom.setText(dadesLLegides);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            //mostrar les dadesl de JSON rebut
            JSONObject objecte = new JSONObject(dadesLLegides);
            Log.i(M36_ComunicacioJSONActivity.class.getName(), "Nombre d'entrades "
                    + objecte.length());
            Log.i(M36_ComunicacioJSONActivity.class.getName(),
                    objecte.getString("nom"));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mètode que llegeix les dades des d'un servidor web o servidor
     * d'aplicacions i crea un objecte JSON per a manipular les dades
     *
     * @return cadena obtinguda
     */


    public String llegirJSON() throws IOException {
        StringBuilder builder = new StringBuilder();
        URL urlObj = urlObj = new URL("http://172.20.16.207:8000");
        HttpURLConnection clientHTTP = (HttpURLConnection) urlObj.openConnection();

        if (clientHTTP.getResponseCode() == 200) {
            InputStream contingut = clientHTTP.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(contingut));
            String linia;
            while ((linia = reader.readLine()) != null) {
                builder.append(linia);
            }
        } else {
            Log.e(M36_ComunicacioJSONActivity.class.toString(), "Problemes HTTP");
        }
        return builder.toString();
    }

    /**
     * Mètode per escriure dades en una extensió de servidor web o servidor
     * d'aplicacions
     */

    public void escriureJSON() {
        JSONObject object = new JSONObject();
        try {
            object.put("nom", "Sergi");
            object.put("cognom", "Grau");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}