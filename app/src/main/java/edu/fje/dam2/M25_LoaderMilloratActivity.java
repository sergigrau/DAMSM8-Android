package edu.fje.dam2;

import android.app.ActionBar.LayoutParams;
import android.app.ListActivity;
import android.provider.ContactsContract;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.app.LoaderManager;

/**
 * Classe que demostra el funcionament de l'accés als contactes mitjançant un
 * loader
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 26/12/2013
 * @version 2.0, 1/10/2020 actualització a API30
 * 
 */
public class M25_LoaderMilloratActivity extends ListActivity implements
		LoaderManager.LoaderCallbacks<Cursor> {

	// adaptador per a mostrar les dades
	SimpleCursorAdapter mAdapter;

	//files de contactes que volem recuperar
	static final String[] PROJECCIO = new String[] {
			ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME };

	//criteri de selecció
	static final String SELECCIO = "((" + ContactsContract.Data.DISPLAY_NAME
			+ " NOTNULL) AND (" + ContactsContract.Data.DISPLAY_NAME
			+ " != '' ))";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// barra de progress mentre es carreguen les dades 
		ProgressBar progressBar = new ProgressBar(this);
		progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, Gravity.CENTER));
		progressBar.setIndeterminate(true);
		getListView().setEmptyView(progressBar);

		// afegim la barra al layout
		ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
		root.addView(progressBar);

		// especifiquem les columnes a afegir a la vista per al adaptador cursor
		String[] columnesOrigen = { ContactsContract.Data.DISPLAY_NAME };
		int[] vista = { android.R.id.text1 };

		// creem un adaptador buit per mostrar les dades carregades
		// passem null al cursor, actualitzem  en onLoadFinished()
		mAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, null, columnesOrigen,
				vista, 0);
		setListAdapter(mAdapter);

		// preparem el loader, reconnectem si existeix, creem si no
		getLoaderManager().initLoader(0, null, this);
	}

	// cridat quan un nou Loader necessita ser creat
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// creeem i retornem un CursorLoader que permetrà un cursor
		// quan les dades siguin mostrades
		return new CursorLoader(this, ContactsContract.Data.CONTENT_URI,
                PROJECCIO, SELECCIO, null, null);
	}

	// cridat quan un loader ha carregat les dades 
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		// canviem de cursor. L'altre es tanca automàticament
		mAdapter.swapCursor(data);
	}

	// cridat quan un loader ja ha estat creat, fa un reset
	public void onLoaderReset(Loader<Cursor> loader) {
		
		mAdapter.swapCursor(null);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		//fer quelcom
	}
}