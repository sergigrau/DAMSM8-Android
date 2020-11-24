package edu.fje.dam2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

/**
 * Classe que demostra el funcionament de les comunicacions amb Bluetooth.
 * Hereta de Fil per a implementar un client socket.Fa les connexions sortints
 * i reprodueix un so mitjançant Lego NXT communication protocol
 * <p/>
 * codis LPC gràcies a http://www.robotappstore.com/Knowledge-Base/How-to-Make-Lego-NXT-to-Play-a-Sound/35.html
 *
 * @author sergi.grau@fje.edu
 * @version 1.0 19.01.2016
 * @version 2.0, 1/10/2020 actualització a API30
 */

public class M40_ConnexioBlueToothFilClient extends Thread {

    //00:16:53:13:7E:94 adreça MAC NXT num 4
    private BluetoothSocket socol = null;
    NxtBrick nxt;

    public M40_ConnexioBlueToothFilClient(BluetoothDevice d) {
        // obtenim un socol per a la connexio BT
        try {

            Method m = d.getClass().getMethod("createRfcommSocket", new Class[]{int.class});
            socol = (BluetoothSocket) m.invoke(d, 1);

        } catch (Exception e) {
        }
    }

    public void run() {
        Log.i(getName(), "fil funcionant");
        try {
            nxt = new NxtBrick(socol.getOutputStream(),
                    socol.getInputStream());

            // connectem com a client. es bloqueja fins que no tenim la connexio
            socol.connect();

            try {
                for (int i = 0; i < 100; i++)
                    nxt.playTone('9', 'B');
                    Log.i(getName(), "reproduint");
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.i(getName(), "enviat!");

        } catch (IOException connectException) {
            Log.i(getName(), connectException.getMessage());
            try {
                socol.close();
            } catch (IOException closeException) {
            }
            return;
        }
        // la gestió seria millor fer-la en un altre fil
        Log.i(getName(), "socol.isConnected()");
    }

    /**
     * Classe interna que ens permet comunicar mb LPC amb un NXT
     */
    public class NxtBrick {

        private OutputStream out;
        private InputStream in;

        public NxtBrick(OutputStream out,
                        InputStream in) {
            this.out = out;
            this.in = in;
        }


        public void playTone(char f, char msec)
                throws IOException {
            byte[] msg = new byte[6];
            msg[0] = (byte) 0x80;
            msg[1] = (byte) 0x03;
            msg[2] = (byte) (f & 0xff);
            msg[3] = (byte) ((f >> 8) & 0xff);
            msg[4] = (byte) (msec & 0xff);
            msg[5] = (byte) ((msec >> 8) & 0xff);
            sendMessage(msg);
        }

        public void setOutputState(byte motor,
                                   byte power,
                                   boolean speedReg,
                                   boolean motorSync,
                                   byte runState) throws IOException {
            byte[] msg = {(byte) 0x80, 0x04, motor, power,
                    0x01, 0x01, 0x33, runState, 0x00,
                    0x00, 0x00, 0x00};

            sendMessage(msg);
        }

        private void sendMessage(byte[] msg) throws IOException {
            if (out != null) {
                out.write(msg.length & 0xff);
                out.write((msg.length >> 8) & 0xff);
                out.write(msg);
                out.flush();
            }
        }
    }
}
