package edu.fje.dam2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Classe que hereta de la classe Activity i implementa la interfície
 * OnItemSelectedListener per a definir una selecció mitjançant un spinner (un
 * combo box). Utilitza un layout XML per a definir la interfície d'una llista
 * de la qual l'usuari pot seleccionar un item. Utilitza un ArrayAdapter que
 * permet utilitzar el patró MVC
 *
 * @author sergi.grau@fje.edu
 * @version 2.0, 1/10/2020, actualització a API.30
 */
public class M15_SpinnerActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    private TextView seleccio;
    private String[] items = {"pomes", "peres", "taronges", "plàtans", "pinyes"};

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.m15_spinner);
        seleccio = (TextView) findViewById(R.id.seleccio);
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        seleccio.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> pare, View v, int posicio, long id) {
        seleccio.setText(items[posicio]);
    }
}
