package edu.fje.dam2;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Classe que hereta de la classe ListActivity Utilitza un layout XML per a
 * definir la interfície d'una llista de la qual l'usuari pot seleccionar un
 * item. Utilitza un ArrayAdapter que permet utilitzar el patró MVC
 *
 * @author sergi.grau@fje.edu
 * @version 1.0, 19.10.2015
 * @version 2.0, 1/10/2020, actualització a API.30
 */
public class M14_ListActivityIntentActivity extends ListActivity {

	private TextView seleccio;
	//private static final String[] items = { "pomes", "peres", "taronges",
	//		"plàtans", "pinyes" };
	private ArrayAdapter<String> adapter;

	/*
	 * alternativament al strings.xml <string-array name="items">
	 * <item>pomes</item> <item>peres</item> </string-array>
	 */

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// ArrayAdapter té 3 paràmetres (instància d'Activity, id del giny on visualitzar, llista d'ítems
		// setListAdapter(new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, items));
		// setListAdapter(new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_single_choice, items));

		 String[] items = getResources().getStringArray(R.array.fruites);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_multiple_choice, items);

		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		getListView().setAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView parent, View v, int position, long id) {
		// selecció simple
		// seleccio.setText(items[position]);
		// selecció múltiple

		SparseBooleanArray checked = parent.getCheckedItemPositions();
		ArrayList<String> itemsSeleccionats = new ArrayList<String>();
		for (int i = 0; i < checked.size(); i++) {
			int pos = checked.keyAt(i);
			if (checked.valueAt(i))
				itemsSeleccionats.add(adapter.getItem(pos));
		}

		Intent intent = new Intent(getApplicationContext(),
				M14_ListActivityIntentDepenentActivity.class);

		Bundle b = new Bundle();
		b.putStringArrayList("itemsSeleccionats", itemsSeleccionats);

		intent.putExtras(b);

		startActivity(intent);
	}
}
