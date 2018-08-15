package br.edu.infnet.dontbelate;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        Button button = findViewById(R.id.signup_button);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Sign Up button code

                        EditText field = findViewById(R.id.email_field);
                        String email = field.getText().toString();

                        field = findViewById(R.id.email_confirmation_field);
                        String confirmation = field.getText().toString();

                        field = findViewById(R.id.password_field);
                        String password = field.getText().toString();


                        //TODO: Validate fields
                        if (validateField()){
                            // Se validou, posso armazenar um novo usuário
                            SharedPreferences preferences = getSharedPreferences(
                                                    Constants.DEFAULT_PREFS_FILE, MODE_PRIVATE);
                            SharedPreferences.Editor editor  = preferences.edit();

                            try {
                                MessageDigest md = MessageDigest.getInstance("SHA-256");
                                md.update(password.getBytes());
                                byte[] hashBytes = md.digest();
                                String hashedWord = new String(hashBytes, "UTF-8");
                                Toast.makeText(SignUpActivity.this,
                                        "" + email + ": " + hashedWord,
                                        Toast.LENGTH_LONG).show();

                                md.reset();
                                md.update(email.getBytes());
                                byte[] hashmail = md.digest();
                                editor.putString(new String(hashmail), hashedWord);
                                editor.commit();
                                finish();
                            } catch (NoSuchAlgorithmException exception){
                                Toast.makeText(SignUpActivity.this,
                                        "Algoritmo de criptografia inválido",
                                        Toast.LENGTH_LONG).show();
                            } catch (UnsupportedEncodingException exception){
                                Toast.makeText(SignUpActivity.this,
                                        "Problema de codificação",
                                        Toast.LENGTH_LONG).show();
                            }



                        }
                    }
                }
        );

    }


    private boolean validateField(){
        //TODO: make it right!
        return true;
    }

}
