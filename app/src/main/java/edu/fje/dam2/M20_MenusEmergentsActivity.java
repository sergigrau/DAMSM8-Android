package edu.fje.dam2;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Classe que hereta de la classe ListActivity i que mostra com
 * funcionen els menus emergents. El menu permet modificar
 * la distància entre els ítems de la llista.
 * Utilitza un layout XML per a definir la interfície .
 * amb un botó
 * @author sergi.grau@fje.edu
 * @version 1.0, 29/11/2012
 * @version 2.0, 1/10/2020 actualització a API30
 */
public class M20_MenusEmergentsActivity extends ListActivity {

    TextView seleccio;
    String[] items = {"pomes", "peres", "taronges", "plàtans", "pinyes"};
    public static final int id1 = Menu.FIRST + 1;
    public static final int id2 = Menu.FIRST + 2;
    public static final int id8 = Menu.FIRST + 3;
    public static final int id16 = Menu.FIRST + 4;
    public static final int id24 = Menu.FIRST + 5;
    public static final int id32 = Menu.FIRST + 6;
    public static final int id40 = Menu.FIRST + 7;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.m20_menus_emergents);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items));
        seleccio = (TextView) findViewById(R.id.seleccio);

        registerForContextMenu(getListView());
    }

    public void onListItemClick(ListView parent, View v,
            int position, long id) {
        seleccio.setText(items[position]);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenu.ContextMenuInfo menuInfo) {
        omplirMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        omplirMenu(menu);

        return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return (escollirMenu(item)
                || super.onOptionsItemSelected(item));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return (escollirMenu(item)
                || super.onContextItemSelected(item));
    }

    private void omplirMenu(Menu menu) {
        menu.add(Menu.NONE, id1, Menu.NONE, "1 Pixel");
        menu.add(Menu.NONE, id2, Menu.NONE, "2 Pixels");
        menu.add(Menu.NONE, id8, Menu.NONE, "8 Pixels");
        menu.add(Menu.NONE, id16, Menu.NONE, "16 Pixels");
        menu.add(Menu.NONE, id24, Menu.NONE, "24 Pixels");
        menu.add(Menu.NONE, id32, Menu.NONE, "32 Pixels");
        menu.add(Menu.NONE, id40, Menu.NONE, "40 Pixels");
    }

    private boolean escollirMenu(MenuItem item) {
        switch (item.getItemId()) {
            case id1:
                getListView().setDividerHeight(1);
                return (true);

            case id8:
                getListView().setDividerHeight(8);
                return (true);

            case id16:
                getListView().setDividerHeight(16);
                return (true);

            case id24:
                getListView().setDividerHeight(24);
                return (true);

            case id2:
                getListView().setDividerHeight(2);
                return (true);

            case id32:
                getListView().setDividerHeight(32);
                return (true);

            case id40:
                getListView().setDividerHeight(40);
                return (true);
        }

        return (false);
    }
}
