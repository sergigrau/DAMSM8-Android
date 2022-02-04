package edu.fje.dam2;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Activitat que demostra el funcionament d'afegir al
 * calendari un "event", posteriorment els consulta.
 * Cal afegir els permissos WRITE_CALENDAR i READ_CALENDAR
 * al fitxer de manifest
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 10.01.2015
 * @version 2.0, 1/10/2020 actualització a API30
 */
public class M31_CalendariAmpliatActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_READ_CALENDAR = 100;

    private ContentResolver contentResolver;
    private Set<String> calendaris = new HashSet<String>();
    private List<String> events = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_CALENDAR)) {


            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_CALENDAR},
                        PERMISSIONS_REQUEST_READ_CALENDAR);
            }
        }
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CALENDAR)) {


            } else {


                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CALENDAR},
                        PERMISSIONS_REQUEST_READ_CALENDAR);
            }
        }



        setContentView(R.layout.m30_calendari);
        contentResolver = getContentResolver();


    }

    public void onClick(View view) {
        afegirEvent();
        obtenirEvents();
        Log.i(getClass().getName(), calendaris.toString());
        Log.i(getClass().getName(), events.toString());
    }

    /**
     * Mètode que permet afegir un event a un calendari de l'usuari
     */
    private void afegirEvent() {

        ContentValues esdeveniment = new ContentValues();
        esdeveniment.put(CalendarContract.Events.CALENDAR_ID, 1); // Tipus de calendari
        esdeveniment.put(CalendarContract.Events.TITLE, "DAM2 Escola del Clot");
        esdeveniment.put(CalendarContract.Events.DTSTART, Calendar.getInstance().getTimeInMillis());
        esdeveniment.put(CalendarContract.Events.DTEND, Calendar.getInstance().getTimeInMillis());
        esdeveniment.put(CalendarContract.Events.EVENT_TIMEZONE, "Europe/Madrid");
        Uri uri = contentResolver.insert(CalendarContract.Events.CONTENT_URI, esdeveniment);

        // La URI conté el contentProvider i retorna el id del event creat
        int id = Integer.parseInt(uri.getLastPathSegment());
        Toast.makeText(getApplicationContext(), "Esdeveniment creat amb codi" + id,
                Toast.LENGTH_SHORT).show();
        obtenirCalendaris();
    }

    /**
     * Mètode que recupera tots els calendaris disponibles al dispositiu
     */
    private void obtenirCalendaris() {
        //la URI dels calendaris és content://com.android.calendar/calendars
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String[] projeccio = {CalendarContract.Calendars.NAME,
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                CalendarContract.Calendars.CALENDAR_COLOR,
                CalendarContract.Calendars.VISIBLE};
        Cursor cursor = contentResolver.query(uri, projeccio, null, null, null);

        try {
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    String nomIntern = cursor.getString(0);
                    String nomMostrat = cursor.getString(1);
                    String color = cursor.getString(cursor.getColumnIndex(
                            CalendarContract.Calendars.CALENDAR_COLOR));
                    Boolean seleccionat = !cursor.getString(3).equals("0");
                    calendaris.add(nomMostrat);
                }
            }
        } catch (AssertionError ex) {
        }
    }

    /**
     * Mètode que recupera determinats events d'un calendari.
     * Filtra pel titol del esdeveniment
     */
    private void obtenirEvents() {
        Uri uri = CalendarContract.Events.CONTENT_URI;
        String seleccio = String.format("(%s = ?)", CalendarContract.Events.TITLE);
        String[] seleccioArgs = new String[]{"DAM2 Escola del Clot"};
        String[] projeccio = new String[]{
                CalendarContract.Events._ID,
                CalendarContract.Events.TITLE,
                CalendarContract.Events.DTSTART
        };
        Cursor cursor = contentResolver.query(uri, projeccio, seleccio, seleccioArgs, null);
        while (cursor.moveToNext()) {
            long id = cursor.getLong(0);
            String titol = cursor.getString(1);
            events.add(titol);
        }
    }

}
