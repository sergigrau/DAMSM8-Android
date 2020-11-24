package edu.fje.dam2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import android.os.Bundle;
import android.os.ParcelUuid;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Classe que demostra el funcionament de les comunicacions amb Bluetooth,
 * connetant per BT un dispositiu NXT i enviant el codi LPC per a reproduir un so
 *
 * @author sergi.grau@fje.edu
 * @version 2.0 19.01.2016
 * @version 2.0, 1/10/2020 actualització a API30
 */

public class M40_ConnexioBluetooth extends AppCompatActivity {

    private final int OBTENCIO_BT = 1;

    // broadcast per a descobrir dispositius
    private final BroadcastReceiver receptorBroadcast = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String accio = intent.getAction();
            // Quan trobem un dispositiu
            if (BluetoothDevice.ACTION_FOUND.equals(accio)) {
                // recuperem l'objecte corresponent al dispositiu
                BluetoothDevice dispositiu = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.i(getClass().getName(), "descobert dispositiu" +
                        dispositiu.getName() + " amb MAC "
                        + dispositiu.getAddress());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.m40_connexio_bluetooth);

        BluetoothAdapter adaptadorBT = BluetoothAdapter
                .getDefaultAdapter();
        if (adaptadorBT == null) {
            Toast.makeText(this, "Bluetooth no suportat", Toast.LENGTH_LONG)
                    .show();
        }
        // activa BT
        if (!adaptadorBT.isEnabled()) {
            Intent intentActivarBT = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intentActivarBT, OBTENCIO_BT);
        }
        // defineix el temps que està en descobriment per a altres dispositius
        Intent intentDescobrimentBT = new Intent(
                BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        intentDescobrimentBT.putExtra(
                BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(intentDescobrimentBT);

        // dispositius amb els que esta aparellat
        Set<BluetoothDevice> dispositiusAparellats = adaptadorBT
                .getBondedDevices();

        //recuperem el mètode de la classe BT mitjançant tècniques reflexives
        Method obtenirUUIDS = null;
        try {
            obtenirUUIDS = BluetoothAdapter.class.getDeclaredMethod("getUuids", null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        ParcelUuid[] uuids = null;
        try {
            uuids = (ParcelUuid[]) obtenirUUIDS.invoke(adaptadorBT, null);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        for (ParcelUuid uuid : uuids) {
            Log.i(getClass().getName(), "UUID registrades: " + uuid.getUuid().toString());
        }

        if (dispositiusAparellats.size() > 0) {
            // mostra informació dels dispositius amb els que està aparellat
            for (BluetoothDevice dispositiu : dispositiusAparellats) {
                Log.i(getClass().getName(), "dispositiu registrat " +
                        dispositiu.getName() + " amb MAC "
                        + dispositiu.getAddress());
                for (ParcelUuid uuid : dispositiu.getUuids()) {
                    Log.i(getClass().getName(), "UUIDs de dispositiu registrat: " + uuid.getUuid().toString());
                }

                //Thread fil1 = new M40_ConnexioBlueToothFilClient(dispositiu);
                //fil1.start();
            }
        }

        // Registrem el BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(receptorBroadcast, filter);

        BluetoothDevice dispositiu = adaptadorBT.getRemoteDevice("00:16:53:13:7E:94");

        //connectem un fil de client BT amb NXT
        Thread fil1 = new M40_ConnexioBlueToothFilClient(dispositiu);
        fil1.start();
    }
}
