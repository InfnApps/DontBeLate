package br.edu.infnet.dontbelate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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


        SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        startActivity(new Intent(this, ManagerActivity.class));

        String savedPassword = preferences.getString(email, null);
//        if (savedPassword != null){
//            if (password.equals(savedPassword)){
//                startActivity(new Intent(this, ManagerActivity.class));
//            }
//        }
        //TODO: tratar o caso que não da certo
    }


    public void goToSignUp(View view){
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
