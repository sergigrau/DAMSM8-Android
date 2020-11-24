/*
sqlite3 /data/data/edu.fje.dam2/databases/data.db
sqlite> .tables
.tables
usuaris
sqlite> select * from usuaris;
 */
package edu.fje.dam2;

import java.util.ArrayList;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;

/**
 * Classe que hereta de la classe ListActivity i que
 * mostra com podem treballar amb la base de dades SQLlite3
 * @author sergi.grau@fje.edu
 * @version 1.0, 29/11/2012
 * @version 2.0, 1/10/2020 actualització a API30
 */
public class M23_BaseDeDadesBasicActivity extends ListActivity {

    private final String BASE_DADES = "dam2";
    private final String TAULA = "usuari";

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);


        ArrayList<String> resultats = new ArrayList<String>();

        SQLiteDatabase baseDades = null;
        try {

            baseDades = this.openOrCreateDatabase(BASE_DADES, MODE_PRIVATE, null);


            baseDades.execSQL("CREATE TABLE IF NOT EXISTS "
                    + TAULA
                    + " (nom VARCHAR, cognom VARCHAR,"
                    + " pais VARCHAR, edat INT(3));");


            baseDades.execSQL("INSERT INTO "
                    + TAULA
                    + " (nom, cognom, pais, edat)"
                    + " VALUES ('sergi', 'grau', 'espanya', 38);");
            baseDades.execSQL("INSERT INTO "
                    + TAULA
                    + " (nom, cognom, pais, edat)"
                    + " VALUES ('joan', 'perez', 'US', 34);");


            Cursor c = baseDades.rawQuery("SELECT nom, edat"
                    + " FROM " + TAULA
                    + " WHERE edat > 10 LIMIT 7;",
                    null);


            int columnaNom = c.getColumnIndex("nom");
            int columnaEdat = c.getColumnIndex("edat");


            if (c != null) {

                if (c.isBeforeFirst()) {
                    c.moveToFirst();
                    int i = 0;

                    do {
                        i++;

                        String nom = c.getString(columnaNom);
                        int edat = c.getInt(columnaEdat);

                        String nomColumnaEdat = c.getColumnName(columnaEdat);


                        resultats.add("" + i + ": " + nom
                                + " (" + nomColumnaEdat + ": " + edat + ")");
                    } while (c.moveToNext());
                }
            }

        } finally {
            if (baseDades != null) {
                baseDades.close();
            }
        }

        this.setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, resultats));
    }
}
