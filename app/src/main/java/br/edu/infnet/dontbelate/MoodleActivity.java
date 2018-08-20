package br.edu.infnet.dontbelate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.JsonReader;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MoodleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moodle);

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String username = getApplicationContext().getString(R.string.app_name);
                            String password = getApplicationContext().getString(R.string.app_name);
                            String moodle_baseurl = "https://lms.infnet.edu.br/moodle/login/token.php?username=%s&password=%s&service=moodle_mobile_app";
                            URL url = new URL(String.format(moodle_baseurl, username, password));
                            final HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();

                            /*urlConnection.setDoOutput(true);
                            urlConnection.setChunkedStreamingMode(0);
                            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
                            writeStream(out);*/
                            //urlConnection.setRequestProperty("Accept", "application/json");
                            //urlConnection.setRequestProperty("Content-type", "application/json");

                            urlConnection.setRequestMethod("POST");
                            urlConnection.setDoOutput(true);
                            //urlConnection.setDoInput(true);

                            /*urlConnection.setRequestProperty("username", "hallison.paz");
                            urlConnection.setRequestProperty("password", "jk1009hsINFNET!");
                            urlConnection.setRequestProperty("service", "moodle_mobile_app");*/

                            /*final OutputStream outputStream = urlConnection.getOutputStream();
                            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                            JSONObject data = new JSONObject();
                            try {
                                data.put("username", "hallison.paz");
                                data.put("password", "jk1009hsINFNET!");
                                data.put("service", "moodle_mobile_app");
                                writer.write(data.toString());
                                writer.flush();
                                writer.close();
                                outputStream.close();
                            } catch (JSONException e){
                                Log.d("XABUZACO", "XABUZAAAAAAAAACO");
                            }*/


                            //urlConnection.connect();


                            if (urlConnection.getResponseCode() == 200) {
                                // Success
                                // Further processing here
                                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                                final InputStreamReader reader = new InputStreamReader(in);
                                final JsonReader jsonReader = new JsonReader(reader);
                                //BufferedReader bufferedReader = new BufferedReader(reader);
                                //String line = bufferedReader.readLine();
                                String text = "";
                                jsonReader.beginObject();
                                while (jsonReader.hasNext()) {
                                    text += jsonReader.nextName() + ": ";
                                    String value = jsonReader.nextString();
                                    if (value != null){
                                        text += value + "\n";
                                    }
                                    //line = bufferedReader.readLine();
                                }
                                jsonReader.close();
                                final String fulltext = text;
                                final TextView textView = findViewById(R.id.page_content);
                                runOnUiThread(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    textView.setText(fulltext);
                                                    urlConnection.disconnect();
                                                } catch (Exception e){
                                                    Toast.makeText(getApplicationContext(),
                                                            "PUTZ", Toast.LENGTH_LONG).show();
                                                }

                                            }
                                        }
                                );
                            } else {
                                // Error handling code goes here
                            }

                            /*InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                            InputStreamReader reader = new InputStreamReader(in);
                            final TextView textView = findViewById(R.id.page_content);
                            BufferedReader bufferedReader = new BufferedReader(reader);
                            String line = bufferedReader.readLine();
                            String text = "";
                            while (line != null) {
                                text += line;
                                line = bufferedReader.readLine();
                            }
                            final String fullText = text;
                            Log.d("OLHA AQUI XXXXXXX", fullText);
                            runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            textView.setText(fullText);
                                            urlConnection.disconnect();
                                        }
                                    }
                            );*/

                        } catch (final MalformedURLException exception){
                            runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                            );

                        } catch (final IOException exception){
                            runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                            );
                        }
                        finally {

                        }
                    }
                }
        ).start();
    }
}
