package edu.fje.dam2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.*;

/**
 * Activitat que demostra el funcionament d'afegir al
 * calendari un event. El intent es tractat per l'aplicació de calendari
 * que obre un event que cal confirmar
 *
 * @author sergi.grau@fje.edu
 * @version 2.0 28.11.2016
 * @version 2.0, 1/10/2020 actualització a API30
 */
public class M30_CalendariBasicActivity extends AppCompatActivity {

    static final int EVENT_AFEGIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m30_calendari);
    }

    public void onClick(View view) {
        afegirEvent();
    }

    /**
     * Mètode que permet afegir un event a un calendari de l'usuari.
     * Si l'usuari finalment l'afegeix es mostra un toast
     */
    private void afegirEvent() {

        Calendar calendari = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", calendari.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("endTime", calendari.getTimeInMillis() + 60 * 60 * 1000);
        intent.putExtra("title", "ESDEVENIMENT PROGRAMAT");
        intent.putExtra("description", "DAM2 - SERGI GRAU");
        intent.putExtra("eventLocation", "BARCELONA");

        startActivityForResult(intent,EVENT_AFEGIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EVENT_AFEGIT) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "s'ha afegit un esdeveniment", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

}