package br.edu.infnet.dontbelate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
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
                                        Constants.DEFAULT_PREFS_FILE, MODE_PRIVATE);

        //startActivity(new Intent(this, ManagerActivity.class));



        String savedPassword = preferences.getString(email, null);
        if (savedPassword != null){

            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(password.getBytes());
                byte[] hashBytes = md.digest();
                String hashedWord = new String(hashBytes, "UTF-8");
                if (hashedWord.equals(savedPassword)){
                    Intent intent = new Intent(this, CryptoTestActivity.class);
                    intent.putExtra("message", "Instituto Infnet é o melhor!");
                    startActivity(intent);
                } else {
                    Toast.makeText(this,
                            "Senha não confere",
                            Toast.LENGTH_LONG).show();
                }
            } catch (NoSuchAlgorithmException exception){
                Toast.makeText(LoginActivity.this,
                        "Algoritmo de criptografia inválido",
                        Toast.LENGTH_LONG).show();
            } catch (UnsupportedEncodingException exception){
                Toast.makeText(LoginActivity.this,
                        "Problema de codificação",
                        Toast.LENGTH_LONG).show();
            }


        }
        else {
            Toast.makeText(this,
                    "Senha não encontrada",
                    Toast.LENGTH_LONG).show();
        }
        //TODO: tratar o caso que não da certo
    }


    public void goToSignUp(View view){
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
