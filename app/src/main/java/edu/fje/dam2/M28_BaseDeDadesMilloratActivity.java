package edu.fje.dam2;

import android.os.Bundle;
import android.provider.BaseColumns;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Classe que demostra el funcionament de l'accés a la base de dades SQlite
 * Disposa d'una classe d'utiltat per a facilitar la manipulació de la taula
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 26/11/2013
 * @version 2.0, 1/10/2020 actualització a API30
 */
public class M28_BaseDeDadesMilloratActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.m28_base_de_dades_millorat);

		AlumneDbUtilitat utilitatDB = new AlumneDbUtilitat(getBaseContext());

		// seleccionem les date per a poder escriure
		SQLiteDatabase db = utilitatDB.getWritableDatabase();

		// creem un mapa de valors on les columnes són les claus
		ContentValues valors = new ContentValues();
		valors.put(AlumneContract.TaulaAlumne.COLUMNA_CODI, "1237");
		valors.put(AlumneContract.TaulaAlumne.COLUMNA_NOM, "joan");
		valors.put(AlumneContract.TaulaAlumne.COLUMNA_NOTA, 8);

		// afegim una fila i retorna la clau primària
		long id;
		id = db.insert(AlumneContract.TaulaAlumne.NOM_TAULA,
				AlumneContract.TaulaAlumne.COLUMNA_NULL, valors);

		
		db = utilitatDB.getReadableDatabase();

		String[] projeccio = {
				AlumneContract.TaulaAlumne._ID,
				AlumneContract.TaulaAlumne.COLUMNA_NOM,
				AlumneContract.TaulaAlumne.COLUMNA_NOTA
		    };

		String seleccio ="nota >= ?";
		String[] seleccioArgs = {"5"};
		String ordre =
				AlumneContract.TaulaAlumne.COLUMNA_NOTA + " DESC";

		
		Cursor c = db.query(
				AlumneContract.TaulaAlumne.NOM_TAULA,  // taula
		    projeccio,                               // columnes
		    seleccio,                                // columnes WHERE 
		    seleccioArgs,                            // valors WHERE
		    null,                                     // GROUP
		    null,                                     // HAVING
		    ordre                                 // ordre
		    );
		
		c.moveToFirst();
		Log.i(getClass().getName(),c.getString(		    
				c.getColumnIndexOrThrow(AlumneContract.TaulaAlumne.COLUMNA_NOM)
		));
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.m28_base_de_dades, menu);
		return true;
	}

}

/**
 * Classe Utilitat que defineix el contacte envers la base de dades
 * 
 * @author sergi grau
 * @version 1.0 26/11/2013
 * @version 2.0, 1/10/2020 actualització a API30
 */
final class AlumneContract {

	public AlumneContract() {
	}

	/**
	 * Classe Interna que declara una taula Alumne
	 * 
	 * @author sergi grau
	 * @version 1.0 26/11/2013
	 * 
	 */
	public static abstract class TaulaAlumne implements BaseColumns {
		public static final String NOM_TAULA = "alumne";
		public static final String COLUMNA_CODI = "codi";
		public static final String COLUMNA_NOM = "nom";
		public static final String COLUMNA_NOTA = "nota";
		public static final String COLUMNA_NULL = "null";
	}
}

/**
 * Classe utilitat per a manipulació de la BDs
 * 
 * @author sergi grau
 * @version 1.0 26/11/2013
 * @version 2.0, 1/10/2020 actualització a API30
 */
class AlumneDbUtilitat extends SQLiteOpenHelper {
	private static final String TIPUS_TEXT = " TEXT";
	private static final String TIPUS_ENTER = " INT";
	private static final String SEPARADOR_COMA = ",";
	private static final String SQL_CREACIO_TAULA = "CREATE TABLE "
			+ AlumneContract.TaulaAlumne.NOM_TAULA + " ("
			+ AlumneContract.TaulaAlumne._ID + " INTEGER PRIMARY KEY,"
			+ AlumneContract.TaulaAlumne.COLUMNA_CODI + TIPUS_TEXT
			+ SEPARADOR_COMA + AlumneContract.TaulaAlumne.COLUMNA_NOM
			+ TIPUS_TEXT + SEPARADOR_COMA
			+ AlumneContract.TaulaAlumne.COLUMNA_NOTA + TIPUS_ENTER + " )";

	private static final String SQL_ESBORRAT_TAULA = "DROP TABLE IF EXISTS "
			+ AlumneContract.TaulaAlumne.NOM_TAULA;

	// Si canviem l'esquema hem de canviar la versió
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Alumne.db";

	public AlumneDbUtilitat(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREACIO_TAULA);
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_ESBORRAT_TAULA);
		onCreate(db);
	}

	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onUpgrade(db, oldVersion, newVersion);
	}
}
