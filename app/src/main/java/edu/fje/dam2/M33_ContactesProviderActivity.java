package edu.fje.dam2;

import android.Manifest;
import android.app.ListActivity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Classe Activity que hereta de ListActivity i que accedeix mitjançant un
 * proveïdors de contingut als contactes d'Android
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 20/11/2013
 * @version 2.0, 1/10/2020 actualització a API30 *
 */
public class M33_ContactesProviderActivity extends ListActivity {

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {


            } else {


                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }

        Cursor mCursor = obtenirContactes();
        /*
         * Els paràmetres de context El context en el ListView associat amb
         * Aquest SimpleListItemFactory s'està executant identificador de recurs de disseny d'una
         * Arxiu de disseny que defineix els punts de vista d'aquest element de la llista. la disposició
         * Arxiu ha d'incloure almenys les vistes amb nom definides en "a " c El
         * Cursor base de dades . Pot ser nul si el cursor encara no està disponible . des
         * Una llista dels noms de les columnes que representen les dades per unir-se a la interfície d'usuari.
         * Nul·la si el cursor encara no està disponible . als punts de vista que ha de
         * Columna de pantalla en el paràmetre " de" . Aquests han de ser
         * TextViews . Les primeres visites de N en aquesta llista s'estableixen els valors de la
         * Primeres columnes de N en el paràmetre de . Pot ser nul si el cursor està
         * No disponible encara . banderes banderes utilitzades per determinar el comportament de la
         * Adaptador , segons CursorAdapter ( Context , Cursor , int ) .
         */
        ListAdapter adaptador = new SimpleCursorAdapter(this,
                android.R.layout.two_line_list_item, mCursor, new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME}, new int[]{
                android.R.id.text1, android.R.id.text2},
                CursorAdapter.NO_SELECTION);

        setListAdapter(adaptador);
    }

    private Cursor obtenirContactes() {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projeccio = new String[]{ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME};
        String seleccio = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = '"
                + ("1") + "'";
        String[] argsSeleccio = null;
        String ordre = ContactsContract.Contacts.DISPLAY_NAME
                + " COLLATE LOCALIZED ASC";

        return getContentResolver().query(uri, projeccio, null, null, null);
    }

}
