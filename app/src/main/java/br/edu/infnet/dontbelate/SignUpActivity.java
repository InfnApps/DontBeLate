package br.edu.infnet.dontbelate;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                            SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                            SharedPreferences.Editor editor  = preferences.edit();
                            editor.putString(email,password);
                            editor.commit();
                            finish();
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
