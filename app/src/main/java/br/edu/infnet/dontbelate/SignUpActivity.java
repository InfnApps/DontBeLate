package br.edu.infnet.dontbelate;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

                        if (validateFields(email, confirmation, password)){
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




                        //TODO: Validate fields
//                        if (validateFields()){
//                            // Se validou, posso armazenar um novo usuário
//                            SharedPreferences preferences = getSharedPreferences(
//                                                                Constants.PREFERENCES_FILE,
//                                                                MODE_PRIVATE);
//                            SharedPreferences.Editor editor  = preferences.edit();
//                            editor.putString(email,password);
//                            editor.commit();
//                            finish();
//                        }
                    }
                }
        );

    }


    private boolean validateFields(String email, String confirmation, String password){
        //TODO: make it right!
        return true;
    }

}
