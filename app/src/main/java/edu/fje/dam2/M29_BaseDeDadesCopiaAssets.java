package edu.fje.dam2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Classe que copia un conjunt de dades a la base de dades
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 26/11/2013
 * @version 2.0, 1/10/2020 actualització a API30
 */
public class M29_BaseDeDadesCopiaAssets extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.m28_base_de_dades_millorat);

		UtilitatCopia utilitat = new UtilitatCopia(this);
		SQLiteDatabase db = utilitat.openDatabase();
		Cursor c = db.rawQuery("SELECT * FROM xxx;", null);
		Log.d("MyApp", "cnt: "+c.getCount());
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.m28_base_de_dades, menu);
		return true;
	}

}

class UtilitatCopia {

   private static final String DB_NAME = "asset.db";

   private Context context;

   public UtilitatCopia(Context context) {
       this.context = context;
   }

   public SQLiteDatabase openDatabase() {
       File dbFile = context.getDatabasePath(DB_NAME);

       if (!dbFile.exists()) {
           try {
               copyDatabase(dbFile);
           } catch (IOException e) {
               throw new RuntimeException("Error creating source database", e);
           }
       }

       return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READONLY);
   }

   private void copyDatabase(File dbFile) throws IOException {
       InputStream is = context.getAssets().open(DB_NAME);
       OutputStream os = new FileOutputStream(dbFile);

       byte[] buffer = new byte[1024];
       while (is.read(buffer) > 0) {
           os.write(buffer);
       }

       os.flush();
       os.close();
       is.close();
   }

}