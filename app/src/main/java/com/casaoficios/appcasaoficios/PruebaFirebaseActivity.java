package com.casaoficios.appcasaoficios;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.casaoficios.appcasaoficios.adapters.ImageListAdapter;
import com.casaoficios.appcasaoficios.beans.beanImagen;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

/**
 * Created by Jaime on 26/10/2017.
 */

public class PruebaFirebaseActivity extends AppCompatActivity implements View.OnClickListener {

    Button tbtnfirebase;
    private StorageReference mystorage;


    ListView lt ;
    ImageListAdapter adapter;
    private String lenguajeProgramacion[];

    private String[] imgid;
//    ImageListAdapter adapter;

    ArrayList<beanImagen> belist = new  ArrayList<beanImagen>();


    private static final int GALLERY_INENT = 1;

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prueba_firebase);

        mystorage = FirebaseStorage.getInstance().getReference();
        lt = (ListView)findViewById(R.id.lstxm2);
        tbtnfirebase = (Button)findViewById(R.id.btnfirebase);
        tbtnfirebase.setOnClickListener(this);

        checkPermissionREAD_EXTERNAL_STORAGE(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnfirebase:

//                if (ContextCompat.checkSelfPermission(PruebaFirebaseActivity.this,
//                        Manifest.permission.READ_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED) {
//
//                    if (ActivityCompat.shouldShowRequestPermissionRationale(PruebaFirebaseActivity.this,
//                            Manifest.permission.READ_EXTERNAL_STORAGE)) {
//
//                        // Show an expanation to the user *asynchronously* -- don't block
//                        // this thread waiting for the user's response! After the user
//                        // sees the explanation, try again to request the permission.
//
//                    } else {
//
//                        // No explanation needed, we can request the permission.
//
//                        ActivityCompat.requestPermissions(PruebaFirebaseActivity.this,
//                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
//
//
//                        if (MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE == 1){
//                            Intent iNT = new Intent(Intent.ACTION_PICK);
//                            //"Todas las imagenes
//                            iNT.setType("image/*");
//                            startActivityForResult(iNT,GALLERY_INENT);
//
//                        }else{
//
//                            Toast.makeText(PruebaFirebaseActivity.this,"Debe brindar los permisos respectivos", Toast.LENGTH_SHORT);
//                        }
//
//
//                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                        // app-defined int constant. The callback method gets the
//                        // result of the request.
//                    }
//
//                }
                if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {
                    metodo_inte();
                }


               break;

        }

    }


    public void metodo_inte(){
        Intent iNT = new Intent(Intent.ACTION_PICK);
        //"Todas las imagenes
        iNT.setType("image/*");
        startActivityForResult(iNT,GALLERY_INENT);
    }


    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("Permiso de acceso a almacenamiento externo", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }


    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Toast.makeText(PruebaFirebaseActivity.this, "Debe otorgar permisos de lectura",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }


    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permisos necesarios");
        alertBuilder.setMessage(msg + " es necesario");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }



    public void actualizaradapter(beanImagen be){




if (adapter == null){

    adapter=new ImageListAdapter(this,belist);
    lt.setAdapter(adapter);
}else{
    adapter.add(be);

}







    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GALLERY_INENT && resultCode == RESULT_OK){

            Uri ur = data.getData();

            beanImagen beim = new beanImagen();
            beim.setCodigo(ur.getLastPathSegment());
            beim.setNombre(ur.getLastPathSegment());
            beim.setPath_ruta(ur.getPath());
            beim.setStruri(ur);

            belist.add(beim);
            belist.add(beim);

            actualizaradapter(beim);
//
//            Image adapter=new LenguajeListAdapter(this,lenguajeProgramacion,imgid);




            StorageReference filepath = mystorage.child("fotos").child(ur.getLastPathSegment());





            filepath.putFile(ur).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(PruebaFirebaseActivity.this,"Se Cargo correctamente la imagen", Toast.LENGTH_SHORT)
                                    .show();
                }


            });
        }

    }
}
