package br.edu.infnet.dontbelate;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        Button button = findViewById(R.id.signup_button);

    }


    private boolean validateFields(String email, String confirmation, String password){

        return true;
    }

    public void createUser(View view){
        EditText field = findViewById(R.id.email_field);
        String email = field.getText().toString();


        field = findViewById(R.id.password_field);
        String password = field.getText().toString();

        field = findViewById(R.id.moodle_password_field);
        String moodle_password = field.getText().toString();

        if (validateFields(email, password, moodle_password)){
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

                messageDigest.update(password.getBytes());

                byte[] hashBytes = messageDigest.digest();

                // hash da senha
                String hashedPassword = new String(hashBytes);

                Toast.makeText(getApplicationContext(),
                        hashedPassword,
                        Toast.LENGTH_LONG).show();

                // armazenar o hash da senha
                SharedPreferences preferences = getSharedPreferences(
                        Constants.PREFERENCES_FILE,
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                // TODO: hash email too
                editor.putString(email, hashedPassword);
                editor.commit();

                // Sucesso, podemos encerrar esta Activity
                finish();

            } catch (NoSuchAlgorithmException exception){
                Toast.makeText(getApplicationContext(),
                        exception.getMessage(),
                        Toast.LENGTH_LONG).show();
            }

        }
    }


    public boolean validarComMoodle() {
/*
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
        while (line != null) {
            texto = texto + line; //texto += line

            line = bufferedReader.readLine();
        }

        TextView textView = findViewById(R.id.content_text);
        textView.setText(texto);
        return true;*/
        return false;
    }

}
