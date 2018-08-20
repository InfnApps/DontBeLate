package br.edu.infnet.dontbelate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);


        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL("https://api.github.com/users/octocat");
                            final HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();


                            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
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
                            );

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
