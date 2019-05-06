package com.patic.mdm;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerConection {
    public void sendJSON(JSONObject json) {
        OutputStream os = null;
        InputStream is = null;
        HttpURLConnection conn = null;
        try {
            //constants
            URL url = new URL("http://192.168.43.64/happiness_barometer/php_input.php");
            String message = json.toString();

            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /*milliseconds*/);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setFixedLengthStreamingMode(message.getBytes().length);

            //make some HTTP header nicety
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("X-Requested-With", "XMLHttpRequest");

            //open
            conn.connect();

            //setup send
            os = new BufferedOutputStream(conn.getOutputStream());
            os.write(message.getBytes());
            //clean up
            os.flush();

            //do somehting with response
            is = conn.getInputStream();
            //String contentAsString = readIt(is,len);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //clean up
            try {
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            conn.disconnect();
        }
    }
}
