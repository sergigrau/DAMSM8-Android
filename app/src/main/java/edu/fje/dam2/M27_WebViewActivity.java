/*
 *  Cal modificar AndroidManifest.xml, per poder accedir a Internet:
<manifest xmlns:android="http://schemas.android.com/apk/res/android" package="com.commonsware.android.browser1">
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_GPS" />
<uses-permission android:name="android.permission.ACCESS_ASSISTED_GPS" />
<uses-permission android:name="android.permission.ACCESS_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<application android:icon="@drawable/cw">
<activity android:name=".BrowserDemo1" android:label="BrowserDemo1"> <intent-filter>
<action android:name="android.intent.action.MAIN" />
<category android:name="android.intent.category.LAUNCHER" /> </intent-filter>
</activity> </application>
</manifest>
 */
package edu.fje.dam2;

import android.content.Context;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.GeolocationPermissions;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Classe que hereta de la classe Activity i que
 * implementa el giny WebView que mostra un navegador
 * webkit
 * @author sergi.grau@fje.edu
 * @version 1.0, 29/11/2012
 * @version 2.0, 1/10/2020 actualització a API30
 */
public class M27_WebViewActivity extends AppCompatActivity {

    WebView navegador;

    @Override
    public void onCreate(Bundle icicle) {

        super.onCreate(icicle);
        setContentView(R.layout.m27_webview);
        navegador = (WebView) findViewById(R.id.webkit);

        /*crea un directori a l'arrel al mateix nivell que res anomenat assets, 
        i dins col·loca les pàgines que es vulguin colocar dins d'aplicació*/

        navegador.getSettings().setJavaScriptEnabled(true);

        //per geolocalització de HTML5
        navegador.getSettings().setAppCacheEnabled(true);
        navegador.getSettings().setDatabaseEnabled(true);
        navegador.getSettings().setDomStorageEnabled(true);
        navegador.setWebChromeClient(new WebChromeClient() {
       	 public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
       	    callback.invoke(origin, true, false);
       	 }
       	});
        //per defecte ens obrirà Chrome, cal canviar-ho
        navegador.setWebViewClient(new WebViewClient());
        //navegador.loadUrl("http://www.fje.edu/");
        navegador.loadData("<html><body>hola, mon!</body></html>", "text/html", "UTF-8");
//        navegador.loadData("<html><body><input type=\"button\" value=\"Hola\" onClick=\"mostrarToast('Hola Android!')\" />\n" +
//                "<script type=\"text/javascript\">\n" +
//                "function mostrarToast(toast) {\n" +
//                "Android.mostrarToast(toast);\n" +
//                "}\n" +
//                "</script></body></html>", "text/html", "UTF-8");
//       navegador.addJavascriptInterface (new WebAppInterface (this), "Android");
        //navegador.loadUrl("file:///android_asset/html5_geolocalizacion4.html");

    }

    class WebAppInterface {
        Context mContext;
        /** instancia la interfície i l'associa el context */
        WebAppInterface(Context c) {
            mContext = c;
        }
        /** mostra una torrada */
        @JavascriptInterface
        public void mostrarToast(String toast) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        }
    }
    
    
}
