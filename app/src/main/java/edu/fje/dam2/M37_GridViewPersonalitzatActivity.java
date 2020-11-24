package edu.fje.dam2;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activitat que mostra el funcionament d'un GridView i que fa servir una cel·la
 * personalitzada
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 6.12.2014
 * @version 2.0, 1/10/2020 actualització a API30
 */

public class M37_GridViewPersonalitzatActivity extends AppCompatActivity {

    private GridView gridView;
    private TextView etiqueta;
    private TextView focus;

    static final String[] SISTEMA_OPERATIU = new String[]{"Android", "iOS",
            "Windows", "Blackberry"};

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
/*
a - z-> 29 - 54

"0" - "9"-> 7 - 16

BACK BUTTON - 4, MENU BUTTON - 82

UP-19, DOWN-20, LEFT-21, RIGHT-22

SELECT (MIDDLE) BUTTON - 23

SPACE - 62, SHIFT - 59, ENTER - 66, BACKSPACE - 67
 */
        if (keyCode > 7 && keyCode < 16)
            focus.setText(String.valueOf(keyCode-7));
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.m37_activity_gridview_personalitzat);


        gridView = (GridView) findViewById(R.id.gridView1);
        etiqueta = (TextView) findViewById(R.id.seleccio);

        gridView.setAdapter(new ImageAdapter(this, SISTEMA_OPERATIU));
        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {


                InputMethodManager imm = (InputMethodManager) getSystemService(M37_GridViewPersonalitzatActivity.this.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

                etiqueta.setText(SISTEMA_OPERATIU[position]);
                focus = (TextView) v.findViewById(R.id.grid_item_label);
                Toast.makeText(
                        getApplicationContext(),
                        focus.getText(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}

/**
 * Classe que encapsul·la una cel·la que es mostrarà a la vista
 *
 * @author Sergi Grau (sergi.grau@fje.edu)
 * @version 1.0 6.12.2014
 */
class ImageAdapter extends BaseAdapter {
    private Context context;
    private final String[] mobileValues;

    public ImageAdapter(Context context, String[] mobileValues) {
        this.context = context;
        this.mobileValues = mobileValues;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            gridView = inflater.inflate(
                    R.layout.m37_activity_gridview_personalitzat_cella, null);

            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(mobileValues[position]);


            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);

            String mobile = mobileValues[position];

            if (mobile.equals("Windows")) {
                imageView.setImageResource(R.drawable.android);
            } else if (mobile.equals("iOS")) {
                imageView.setImageResource(R.drawable.android);
            } else if (mobile.equals("Blackberry")) {
                imageView.setImageResource(R.drawable.android);
            } else {
                imageView.setImageResource(R.drawable.android);
            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return mobileValues.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}