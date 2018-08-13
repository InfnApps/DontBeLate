package br.edu.infnet.dontbelate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoTestActivity extends AppCompatActivity {

    final String PBE_ALGORITHM = "PBEWithSHA256And256BitAES-CBC-BC";
    final int NUM_OF_ITERATIONS = 1000;
    final int KEY_SIZE = 256;
    final byte[] SALT = "ababababababababababab".getBytes();
    final byte[] IV = "1234567890abcdef".getBytes();
    final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_test);

        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        String encrypted = encryptMessage(message);


        String decrypted = decryptMessage(message);

        TextView textView = findViewById(R.id.plaintext);
        textView.setText(message);

        textView = findViewById(R.id.encrypted);
        textView.setText(encrypted);

        textView = findViewById(R.id.decrypted);
        textView.setText(decryptMessage(decrypted));
    }

    public String encryptMessage(String message){
        try {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(message.toCharArray(), SALT, NUM_OF_ITERATIONS, KEY_SIZE);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBE_ALGORITHM);
            SecretKey tempKey = keyFactory.generateSecret(pbeKeySpec);
            SecretKey secretKey = new SecretKeySpec(tempKey.getEncoded(), "AES");

            IvParameterSpec ivSpec = new IvParameterSpec(IV);

            Cipher encrypter = Cipher.getInstance(CIPHER_ALGORITHM);
            encrypter.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

            return  new String(encrypter.doFinal(message.getBytes()));


        } catch (NoSuchAlgorithmException exception){
            Toast.makeText(this,
                    exception.getMessage(),
                    Toast.LENGTH_LONG).show();

        } catch (InvalidKeySpecException exception){
            Toast.makeText(this,
                    exception.getMessage(),
                    Toast.LENGTH_LONG).show();
        } catch (NoSuchPaddingException exception){
            Toast.makeText(this,
                    exception.getMessage(),
                    Toast.LENGTH_LONG).show();
        } catch (Exception exception){
            Toast.makeText(this,
                    exception.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        return "XABU";
    }

    public String decryptMessage(String message){
        try {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(message.toCharArray(), SALT, NUM_OF_ITERATIONS, KEY_SIZE);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBE_ALGORITHM);
            SecretKey tempKey = keyFactory.generateSecret(pbeKeySpec);
            SecretKey secretKey = new SecretKeySpec(tempKey.getEncoded(), "AES");

            IvParameterSpec ivSpec = new IvParameterSpec(IV);


            Cipher encrypter = Cipher.getInstance(CIPHER_ALGORITHM);
            encrypter.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            byte[] encrypted = encrypter.doFinal(message.getBytes());

            Cipher decrypter = Cipher.getInstance(CIPHER_ALGORITHM);
            decrypter.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            return  new String(decrypter.doFinal(encrypted));

        } catch (NoSuchAlgorithmException exception){
            Toast.makeText(this,
                    exception.getMessage(),
                    Toast.LENGTH_LONG).show();

        } catch (InvalidKeySpecException exception){
            Toast.makeText(this,
                    exception.getMessage(),
                    Toast.LENGTH_LONG).show();
        } catch (NoSuchPaddingException exception){
            Toast.makeText(this,
                    exception.getMessage(),
                    Toast.LENGTH_LONG).show();
        } catch (Exception exception){
            Toast.makeText(this,
                    exception.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        return "XABU";
    }
}
