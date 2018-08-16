package br.edu.infnet.dontbelate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.crypto.spec.PBEKeySpec;

public class CryptoActivity extends AppCompatActivity {

    String message = "All your base are belong to us";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto);



    }


    private String encryptMessage(String message){
        //Password Based Encryption
        PBEKeySpec pbeKeySpec = new PBEKeySpec()


        return null;
    }

    private String decryptMessage(String message){

        return null;
    }
}
