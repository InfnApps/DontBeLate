package br.edu.infnet.dontbelate;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
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

public class SignUpActivity extends AppCompatActivity
        implements MoodleAuthTask.OnTaskCompleteListener {

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


    private boolean validateFields(String email, String password) {

        if (!email.endsWith(VALID_EMAIL_SUFFIX)) {
            return false;
        }

        if (password.length() < PASSWORD_MINIMUM_LENGHT) {
            return false;
        }

        return true;
    }

    public void createUser(View view) {
        // Pegar os campos
        EditText field = findViewById(R.id.email_field);
        email = field.getText().toString();

        field = findViewById(R.id.password_field);
        password = field.getText().toString();

        field = findViewById(R.id.moodle_password_field);
        moodlePassword = field.getText().toString();

        if (validateFields(email, password)) {
            //aqui pode demorar
            MoodleAuthTask task = new MoodleAuthTask();
            task.setOnTaskCompleteListener(this);
            task.execute(email, password, moodlePassword);
        }
    }

    @Override
    public void onTaskComplete(boolean success) {
        progressBar.setVisibility(View.GONE);
        if (false) {
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
                finish();
                // Sucesso, podemos encerrar esta Activity
            } catch (NoSuchAlgorithmException exception) {
                Toast.makeText(getApplicationContext(),
                        exception.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Snackbar.make(findViewById(R.id.root_view),
                    getString(R.string.auth_fail),
                    Snackbar.LENGTH_LONG
            ).show();
//            Toast.makeText(getApplicationContext(),
//                    "FALHOU",
//                    Toast.LENGTH_LONG).show();
        }
    }

}