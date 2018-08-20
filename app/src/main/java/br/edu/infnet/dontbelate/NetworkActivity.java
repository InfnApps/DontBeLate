package br.edu.infnet.dontbelate;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkActivity extends AppCompatActivity {

    String texto = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);


        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        final String password = intent.getStringExtra("password");

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String baseurl = "https://lms.infnet.edu.br/moodle/login/token.php" +
                                    "?username=%s&password=%s&service=moodle_mobile_app";
                            //String username = "";
                            //String password = "";
                            URL url = new URL(String.format(baseurl, username, password));
                            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

                            //quando for enviar dados para o servidor, habilite essas linhas
                            connection.setRequestMethod("POST");
                            connection.setDoOutput(true);

                            // pega um objeto que sabe ler dados da conexão (bytes)
                            InputStream in = connection.getInputStream();
                            // A partir de quem sabe ler bytes, construímos quem sabe ler caracteres
                            InputStreamReader reader = new InputStreamReader(in);

                            // A partir de quem sabe ler caracteres construímos quem sabe ler caracteres
                            // de forma controlada
                            BufferedReader bufferedReader = new BufferedReader(reader);
                            String line = bufferedReader.readLine();
                            //String texto = "";
                            while (line != null){
                                texto = texto + line; //texto += line

                                line = bufferedReader.readLine();
                            }

                            runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            TextView textView = findViewById(R.id.content_text);
                                            textView.setText(texto);
                                        }
                                    }
                            );
                            //return texto;
                            // nesse ponto tenho o texto completo, posso colocar no textview
                        } catch (MalformedURLException exception){
                            Toast.makeText(getApplicationContext(),
                                    "URL Mal formada",
                                    Toast.LENGTH_LONG).show();
                        } catch (IOException exception){
                            Toast.makeText(getApplicationContext(),
                                    exception.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        //return "Deu exceção";
                    }
                }
        ).start();

    }
}
