package br.edu.infnet.dontbelate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.security.NoSuchAlgorithmException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoActivity extends AppCompatActivity {

    String message = "All your base are belong to us";
    String password = "senha";
    byte[] SALT = "1234567887654321".getBytes();
    String PBE_ALGORITHM = "PBE....";
    int KEY_SIZE = 256;
    int NUM_ITERATIONS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto);



    }


    private String encryptMessage(String message){
        //Password Based Encryption
        PBEKeySpec pbeKeySpec = new PBEKeySpec(
                password.toCharArray(), SALT, NUM_ITERATIONS, KEY_SIZE);
        try{
            SecretKey tempKey = SecretKeyFactory.getInstance("PBEWithSHA256And256BitAES-CBC-BC").generateSecret(pbeKeySpec);
            SecretKey secretKey = new SecretKeySpec(tempKey.getEncoded(), "AES");


        } catch (Exception exception){

        }






        return null;
    }

    private String decryptMessage(String message){

        return null;
    }
}
