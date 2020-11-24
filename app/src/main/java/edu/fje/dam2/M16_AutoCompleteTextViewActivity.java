package edu.fje.dam2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Classe que hereta de la classe Activity i que mostra com
 * utilitzar un giny d'autocompletament AutoCompleteTextView.
 * Implementa la interfície TextWatcher per a cercar el text
 * Utilitza un layout XML per a definir la interfície d'una llista
 * de la qual l'usuari pot seleccionar un item.
 * Utilitza un ArrayAdapter que permet utilitzar el patró MVC
 *
 * @author sergi.grau@fje.edu
 * @version 2.0, 1/10/2020 actualització a API30
 */
public class M16_AutoCompleteTextViewActivity extends AppCompatActivity implements TextWatcher {

    private TextView seleccio;
    private AutoCompleteTextView entrada;
    private String[] items = {"pomes", "peres", "taronges", "plàtans", "pinyes"};

    @Override
    protected void onCreate(Bundle icicle) {

        super.onCreate(icicle);
        setContentView(R.layout.m16_auto_complete_text_view);
        seleccio = (TextView) findViewById(R.id.seleccio);
        entrada = (AutoCompleteTextView) findViewById(R.id.entrada);
        entrada.addTextChangedListener(this);
        entrada.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items));
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        seleccio.setText(entrada.getText());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}