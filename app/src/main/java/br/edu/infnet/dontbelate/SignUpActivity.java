package br.edu.infnet.dontbelate;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

public class SignUpActivity extends AppCompatActivity implements Runnable {

    Button signUpButton;
    private final String VALID_EMAIL_SUFFIX = "infnet.edu.br";
    private final int PASSWORD_MINIMUM_LENGHT = 6;
    private final String MOODLE_VALIDATION_WORD = "token";
    private String email;
    private String password;
    private String moodlePassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpButton = findViewById(R.id.signup_button);
        progressBar = findViewById(R.id.progressBar);

    }

    private boolean validateWithMoodle(String username, String password){
        String baseurl = "https://lms.infnet.edu.br/moodle/login/token.php" +
                "?username=%s&password=%s&service=moodle_mobile_app";
        // TODO: discriminar cada exceção
        try {
            URL url = new URL(String.format(baseurl, username, password));
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            // pega um objeto que sabe ler dados da conexão (bytes)
            InputStream in = connection.getInputStream();
            // A partir de quem sabe ler bytes, construímos quem sabe ler caracteres
            InputStreamReader reader = new InputStreamReader(in);
            // A partir de quem sabe ler caracteres construímos quem sabe ler caracteres
            // de forma controlada
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            String texto = "";
            while (line != null) {
                texto = texto + line; //texto += line
                line = bufferedReader.readLine();
            }
            if (texto.contains(MOODLE_VALIDATION_WORD)){
                return true;
            }
        } catch (Exception exception){
            return false;
        }
        return true;
    }

    private boolean validateFields(String email, String password){

        if(!email.endsWith(VALID_EMAIL_SUFFIX)){
            return false;
        }

        if (password.length() < PASSWORD_MINIMUM_LENGHT){
            return false;
        }

        return true;
    }

    public void createUser(View view){
        // Pegar os campos
        EditText field = findViewById(R.id.email_field);
        email = field.getText().toString();

        field = findViewById(R.id.password_field);
        String password = field.getText().toString();

        field = findViewById(R.id.moodle_password_field);
        moodlePassword = field.getText().toString();

        if (validateFields(email, password)){
            progressBar.setVisibility(View.VISIBLE);
            //aqui pode demorar
            new Thread(
                    this
            ).start();
        } else {
            Toast.makeText(this, "Não validou", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void run() {
        if (validateWithMoodle(email, moodlePassword)){
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(password.getBytes());
                byte[] hashBytes = messageDigest.digest();
                // hash da senha
                String hashedPassword = new String(hashBytes);

                // armazenar o hash da senha
                SharedPreferences preferences = getSharedPreferences(
                        Constants.PREFERENCES_FILE,
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                // TODO: hash email too
                editor.putString(email, hashedPassword);
                editor.commit();

                // Sucesso, podemos encerrar esta Activity
                runOnUiThread(
                        new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                finish();
                            }
                        }
                );

            } catch (NoSuchAlgorithmException exception){
                Toast.makeText(getApplicationContext(),
                        exception.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}
