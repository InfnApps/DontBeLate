package br.edu.infnet.dontbelate;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    // variável simples
    Geocoder nomeComposto;

    //
    public static final String NOME = "location";

    private final int LOCATION_REQUEST_CODE = 8;
    private final int PADAWAN_QUEIJO = 7;
    private final int REQUEST_PICTURE = 123;
    LocationManager locationManager;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }

    public void requestUserLocation(View view){
        //pedir permissão
        //checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        if (ContextCompat.checkSelfPermission(this,
                                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            // tenho permissão rodar o que eu preciso
            updateLocation();
        } else{
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
            ActivityCompat.requestPermissions(this, permissions, LOCATION_REQUEST_CODE);
        }
    }

    public void requestCallPermission(View view){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            // tenho permissão rodar o que eu preciso
            callInsetisan();
        } else{
            String[] permissions = {Manifest.permission.CALL_PHONE};
            ActivityCompat.requestPermissions(this, permissions, PADAWAN_QUEIJO);
        }
    }

    private void callInsetisan(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:25696969"));
        try {
            startActivity(intent);
        }catch (SecurityException exception){
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateLocation() {
        //final Location latLong = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        try {
            fusedLocationProviderClient.getLastLocation().
                    addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            TextView textView = findViewById(R.id.lat_value);
                            textView.setText(Double.toString(location.getLatitude()));

                            textView = findViewById(R.id.long_value);
                            textView.setText(Double.toString(location.getLongitude()));

                        }
                    });
        } catch (SecurityException exception){
            // alternativa ao Toast
            View rootView = findViewById(R.id.root);
            Snackbar snackbar = Snackbar.make(rootView,
                                                R.string.security_error,
                                                Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }
//        new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        try{
//                            final Location latLong = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                            //pegar os textview
//                            runOnUiThread(
//                                    new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            updateUi(latLong);
//                                        }
//                                    }
//                            );
//
//                        } catch (SecurityException exception){
//                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
//                        } catch (NullPointerException exception){
//                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
//
//                            TextView textView = findViewById(R.id.lat_value);
//                            textView.setText("Desconhecido");
//                            textView = findViewById(R.id.long_value);
//                            textView.setText("Desconhecido");
//                        }
//                    }
//                }
//        ).start();
//
//    }


//    private void updateUi(Location latLong){
//        TextView textView = findViewById(R.id.lat_value);
//        textView.setText(Double.toString(latLong.getLatitude()));
//
//        textView = findViewById(R.id.long_value);
//        textView.setText(Double.toString(latLong.getLongitude()));
//    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case LOCATION_REQUEST_CODE:
                //rodar o que é necessário para pegar localização
                if ( (grantResults.length > 0) && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // AGIR!
                    updateLocation();
                } else{

                }
                break;
            case PADAWAN_QUEIJO:
                //fazer ligação pra Insetisan
                if ( (grantResults.length > 0) && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // AGIR!
                    callInsetisan();
                } else{

                }
                break;
            default:
                Toast.makeText(this, "WTF?", Toast.LENGTH_SHORT).show();
        }
    }



    public void takePicture(View view){
        // pedir app de câmera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQUEST_PICTURE:
                if (resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    Bitmap image = (Bitmap) bundle.get("data");
                    ImageView imageView = findViewById(R.id.photo);
                    imageView.setImageBitmap(image);
                }
        }
    }
}
