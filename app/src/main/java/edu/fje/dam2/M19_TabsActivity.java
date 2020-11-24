package edu.fje.dam2;

import android.os.Bundle;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Classe que hereta de la classe Activity i que mostra com
 * utilitzar un giny de pestanyes.
 * Utilitza un layout XML per a definir la interfície d'un tab
 * amb dues pestanyes, una amb un rellotge analògic, l'altre
 * amb un botó
 * @author sergi.grau@fje.edu
 * @version 1.0, 26/11/2012
 * @version 2.0, 1/10/2020 actualització a API30
 */
public class M19_TabsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle icicle) {

        super.onCreate(icicle);
        setContentView(R.layout.m19_tabs);
        TabHost tabs = (TabHost) findViewById(R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec = tabs.newTabSpec("tag1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Rellotge");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Botó");
        tabs.addTab(spec);
    }
}



