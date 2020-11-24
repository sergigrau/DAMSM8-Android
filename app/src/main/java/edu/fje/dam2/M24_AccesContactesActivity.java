package edu.fje.dam2;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activitat que demostra el funcionament de l'accés als contactes del dispositiu
 *
 * @author sergi.grau@fje.edu
 * @version 2.0 28.11.2016
 * @version 2.0, 1/10/2020 actualització a API30
 */
public class M24_AccesContactesActivity extends AppCompatActivity {

    private Button botoNom;
    static final int OBTENIR_CONTACTE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m24_acces_contactes);
        botoNom = (Button) findViewById(R.id.botoNoms);

        botoNom.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, OBTENIR_CONTACTE);
            }
        });

    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (OBTENIR_CONTACTE):
                if (resultCode == AppCompatActivity.RESULT_OK) {
                    Uri contactData = data.getData();
                    // crea el cursor amb le URI retornada
                    Cursor c = getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String nom = c.getString(c.getColumnIndex(PhoneLookup.DISPLAY_NAME));

                        String nombre = c
                                .getString(c
                                        .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        String contactId = c.getString(c
                                .getColumnIndex(ContactsContract.Contacts._ID));
                        if (nombre.equals("1")) {
                            Cursor telefons = getContentResolver()
                                    .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                                    + " = " + contactId, null, null);
                            while (telefons.moveToNext()) {
                                nombre = telefons
                                        .getString(telefons
                                                .getColumnIndex(ContactsContract.
                                                        CommonDataKinds.Phone.NUMBER));
                            }
                            telefons.close();
                        }

                        Toast.makeText(this, nom + " té el numero " + nombre, Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m24_acces_contactes, menu);
        return true;
    }

}
