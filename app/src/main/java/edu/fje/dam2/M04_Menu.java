package edu.fje.dam2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;


/**
 * Classe que implementa un menu a la barra d'acció
 *
 * @author sergi.grau@fje.edu
 * @version 2.0, 1/10/2020, actualització a API.30
 */
public class M04_Menu extends AppCompatActivity {

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m04_menu);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.m04_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.ajustos:
                obrirAjustos();
                return true;
            case R.id.cercar:
                cercar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void obrirAjustos() {
        Snackbar.make(coordinatorLayout, "AJUSTOS", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void cercar() {
        Snackbar.make(coordinatorLayout, "CERCAR", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}