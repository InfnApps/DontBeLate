package br.edu.infnet.dontbelate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    //
    public void signIn(View view){
        //TODO: sign in code

        EditText field = findViewById(R.id.email_field);
        String email = field.getText().toString();

        field = findViewById(R.id.password_field);
        String password = field.getText().toString();


        SharedPreferences preferences = getSharedPreferences(
                                            Constants.PREFERENCES_FILE,
                                            MODE_PRIVATE);
        String savedHashedPassword = preferences.getString(email, null);

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(Constants.DEFAULT_HASH_ALGORITHM);
            messageDigest.update(password.getBytes());
            String hashedPassword = new String(messageDigest.digest());

            if (savedHashedPassword != null && hashedPassword.equals(savedHashedPassword)){
                startActivity(new Intent(this, ManagerActivity.class));
            } else {
                Toast.makeText(getApplicationContext(),
                        "Senha não confere!",
                        Toast.LENGTH_LONG).show();
            }

        } catch (NoSuchAlgorithmException exception){
            Toast.makeText(getApplicationContext(),
                    exception.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        //TODO: tratar o caso que não da certo
    }


    public void goToSignUp(View view){
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
