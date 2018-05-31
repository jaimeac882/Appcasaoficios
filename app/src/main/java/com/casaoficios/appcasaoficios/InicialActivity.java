package com.casaoficios.appcasaoficios;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;

import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Transition;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Switch;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.casaoficios.appcasaoficios.beans.beanTipos;
import com.casaoficios.appcasaoficios.beans.beanUsuario;
import com.casaoficios.appcasaoficios.util.Constans;
import com.casaoficios.appcasaoficios.util.SessionPrefs;
import com.casaoficios.appcasaoficios.util.StringWithTag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaime on 19/10/2017.
 */
public class InicialActivity extends AppCompatActivity implements View.OnClickListener {


    Button btnnuevo;
    Button btnLogin;
    String baseUrl ="";
    String url ="";
//    public static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 0;
    RequestQueue requestQueue;
//    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 1;
    List<String> permissionsNeeded = new ArrayList<String>();
    final List<String> permissionsList = new ArrayList<String>();

    public static final String PREFS_NAME = "SALUDMOCK_PREFS";

    boolean PERMISO_LOCATION = false ;
    boolean PERMISO_STORAGE = false;

    beanUsuario be = new beanUsuario();

    private Transition tr;


//    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    public void obtener_usuarios(){
        getRepoList("");
    }

    private boolean addPermission(List<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(InicialActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!ActivityCompat.shouldShowRequestPermissionRationale(InicialActivity.this, permission))
                return false;
        }
        return true;
    }

    private void getRepoList(String username) {
        // First, we insert the username into the repo url.
        // The repo url is defined in GitHubs API docs (https://developer.github.com/v3/repos/).



        this.url = this.baseUrl;



        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        mTextView.setText("Response is: "+ response.substring(0,500));

                        llenar_usuario(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
            }
        });


        requestQueue.add(stringRequest);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (SessionPrefs.get(this).isLoggedIn()) {

            System.out.println("ONRESUME");
//            mostrarprincipal();
        }

    }

    private void llenar_usuario(String response) {
        // This will add a new repo to our list.
        // It combines the repoName and lastUpdated strings together.
        // And then adds them followed by a new line (\n\n make two new lines).
//        String strRow = repoName + " / " + lastUpdated;
//        String currentText = tvRepoList.getText().toString();
//        this.tvRepoList.setText(currentText + "\n\n" + strRow);

        System.out.println("123456:  " + response);

//        Log.d("LLEGAMO2S", response);
//
//        ArraybeanTipos = new ArrayList<beanTipos>();



        try {
            JSONObject jsonData = new JSONObject(response);

            JSONArray jsonarray = new JSONArray(jsonData.getString(Constans.APIresponse));
            int number = jsonarray.length();

//            arrayBeanTiposCombo = new ArrayList<String>();


            for (int i = 0; i < number; i++) {
                try {
                    // For each repo, add a new line to our repo list.
                    JSONObject jsonObj = jsonarray.getJSONObject(i);



                    be.setCOD_USUARIO(Integer.parseInt(jsonObj.get("COD_USUARIO").toString()));
                    be.setDES_USUARIO(jsonObj.get("DES_USUARIO").toString());
                    be.setLOG_USUARIO(jsonObj.get("LOG_USUARIO").toString());
                    be.setPASS_USUARIO(jsonObj.get("PASS_USUARIO").toString());
                    be.setESTADO(jsonObj.get("ESTADO").toString());
                    be.setCOD_TIPO_USUARIO(jsonObj.get("COD_TIPO_USUARIO").toString() );
                    be.setFEC_REGISTRO(jsonObj.get("FEC_REGISTRO").toString() );
                    be.setFEC_MODIFICACION(jsonObj.get("FEC_MODIFICACION").toString());
                    be.setCOD_USUARIO_REGISTRO(Integer.parseInt(jsonObj.get("COD_USUARIO_REGISTRO").toString()));


//
//
//                    bet.setCOD_TIPO_MAESTRO(Integer.parseInt(jsonObj.get("COD_TIPO_MAESTRO").toString()));
//                    bet.setDES_TIPO_MAESTRO(jsonObj.get("DES_TIPO_MAESTRO").toString());









                } catch (JSONException e) {
                    // If there is an error then output this to the logs.
                    Log.e("Volley", "Invalid JSON Object.");
                }

            }


        /*Tipos*/



        mostrarprincipal();

//            ArrayAdapter<StringWithTag> arrayAdapter = new ArrayAdapter<StringWithTag>(this,
//                    android.R.layout.simple_dropdown_item_1line, list);


//            cboGenero.setAdapter (arrayAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(InicialActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }



//    public void validarPermisiosencola(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (permissionsList.size() > 0) {
//                if (permissionsNeeded.size() > 0) {
//                    // Need Rationale
//                    String message = "You need to grant access to " + permissionsNeeded.get(0);
//                    for (int i = 1; i < permissionsNeeded.size(); i++)
//                        message = message + ", " + permissionsNeeded.get(i);
//                    showMessageOKCancel(message,
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                        requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
//                                                REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
//                                    }
//                                }
//                            });
//                    return;
//                }
//
//
//                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
//                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
//                return;
//            }
//        }
//
//
//
//    }



//
//    public void onRequestPermissionsResult(int requestCode,
//                                           String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case MY_PERMISSION_ACCESS_FINE_LOCATION: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    PERMISO_LOCATION = true;
//
//
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//
//                } else {
//                    PERMISO_LOCATION = false;
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
////                return;
//            }
//
//
//
//            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    PERMISO_STORAGE = true;
//                    // permission was granted, yay! Do the
//                    // contacts-related task you need to do.
//
//                } else {
//                    PERMISO_STORAGE = false;
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                }
////                return;
//            }
//
//
//            // other 'case' lines to check for other
//            // permissions this app might request
//        }
//    }


    public void mostrarprincipal(){

        SharedPreferences sharedpreferences =    sharedpreferences = getSharedPreferences(PREFS_NAME,
                this.MODE_PRIVATE);;
//            int codusu = SessionPrefs.get(this).getcodigo()//            int codusu = SessionPrefs.get(this).getcodigo();


        beanUsuario obtenerusu = new beanUsuario();
        obtenerusu.setCOD_USUARIO(Integer.parseInt(sharedpreferences.getString("COD_USUARIO","")));
        obtenerusu.setDES_USUARIO(sharedpreferences.getString("DES_USUARIO",""));
        obtenerusu.setLOG_USUARIO(sharedpreferences.getString("LOG_USUARIO",""));
        obtenerusu.setESTADO(sharedpreferences.getString("ESTADO",""));
        obtenerusu.setCOD_TIPO_USUARIO(sharedpreferences.getString("COD_TIPO_USUARIO",""));
        obtenerusu.setFEC_REGISTRO(sharedpreferences.getString("FEC_REGISTRO",""));
        obtenerusu.setFEC_MODIFICACION(sharedpreferences.getString("FEC_MODIFICACION",""));
        obtenerusu.setCOD_USUARIO_REGISTRO(Integer.parseInt(sharedpreferences.getString("COD_USUARIO_REGISTRO","")));







            Intent intent = new Intent(InicialActivity.this,MenuPrincipalActivity.class);
            Bundle bun = new Bundle();
            intent.putExtra("beanusuario", obtenerusu);
            System.out.println("SALIOOOOOOOOOOO");
            startActivity(intent);
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Redirección al Login
        if (SessionPrefs.get(this).isLoggedIn()) {

//            int x = SessionPrefs.get(this).getcodigo();
//SessionPrefs.get(this).getClass().mPrefs


//            SharedPreferences prefs = SessionPrefs.get(this).


//                    this.getSharedPreferences("general_settings", Context.MODE_PRIVATE);
//            String lanSettings = prefs.getString("language", null);
//
//            String xx  = SessionPrefs.get(this).g;
//
//
//            System.out.println("LLEGO ACA: codigox "+xx);

//            System.out.println("ONCREATE");
//            Intent intent;
//            intent = new Intent(InicialActivity.this,MainActivity.class);
//            startActivity(intent);

             mostrarprincipal();
        }else {


            if(checkAndRequestPermissions()) {
                // carry on the normal flow, as the case of  permissions  granted.
            }


//            if (!addPermission(permissionsList, Manifest.permission.READ_EXTERNAL_STORAGE))
//                permissionsNeeded.add("android.permission.READ_EXTERNAL_STORAGE");
//
//
//            if (permissionsList.size() > 0) {
//validarPermisiosencola();
//
//            }





//            validarPermisos();

                    setContentView(R.layout.activity_inicial);

                    btnnuevo = (Button)findViewById(R.id.btnnuevo);
                    btnLogin = (Button)findViewById(R.id.btningresar);


                    btnnuevo.setOnClickListener(this);
                    btnLogin.setOnClickListener(this);










        }

    }




    public  boolean checkAndRequestPermissions() {
        int permissionSendMessage = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int locationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (locationPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (permissionSendMessage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

//    public void validarPermisos(){
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//
//
//
//                        /*Permiso para presición de la locación*/
//            if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                solicitar_permisosLocation();
////                            return;
//            }
//
//
//
//                                                /*Permiso para presición de la locación*/
//            if (checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                solicitar_permisosstorage();
////                            return;
//            }
//
//
//
//                        /*Permiso para presición el storage*/
//
//
//
//
//
//
//
//        }
//    }



//    public void solicitar_permisosLocation(){
//        ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
//                MY_PERMISSION_ACCESS_FINE_LOCATION );
//    }
//
//    public void solicitar_permisosstorage(){
//        ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.READ_EXTERNAL_STORAGE  },
//                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE );
//    }

    public static  final long DURATION = 2000;

    @Override
    public void onClick(View v) {
        Intent intent;
//        showDialog_st("PERMISO LOCATION : " + MY_PERMISSION_ACCESS_FINE_LOCATION,"",this);
//        showDialog_st("PERMISO STORAGE : " + MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE,"",this);

//        System.out.println("PERMISO LOCATION :: "+PERMISO_LOCATION);
//        System.out.println("PERMISO STORAGE :: "+PERMISO_STORAGE);
//
//        if (PERMISO_LOCATION == false || PERMISO_STORAGE == false){
//           showDialog_st("Advertencia","Si brindo todos los permisos , favor de seleccionar si desea Iniciar Sesión o Registrarse como Nuevo(a) ",this);
//
//            if (PERMISO_LOCATION == false){
//                solicitar_permisosLocation();
//            }
//
//            if (PERMISO_STORAGE == false){
//                solicitar_permisosstorage();
//            }
//        }

            if (checkAndRequestPermissions()) {

                switch (v.getId()) {
                    case R.id.btnnuevo:


                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                            tr  = new Slide(Gravity.START);


//                            Slide s = new Slide(Gravity.START);
//                            tr = new Fade(Fade.OUT);

//                            trset.addTransition(slide);
//                            trset.addTransition(tr);

                            tr.setDuration(DURATION);
                            tr.setInterpolator(new DecelerateInterpolator());

                          getWindow().setExitTransition(tr);

                            intent = new Intent(this,Registro_CliActivity.class);
                            startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());

                        }











                        break;
                    case R.id.btningresar:
                        intent = new Intent(InicialActivity.this,MainActivity.class);
                        startActivity(intent);


                        break;
                }


            }else{

                showDialog_st("Advertencia","Seleccione su opción de ingreso de nuevo , No olvide que debio haber otorgado todos los permisos",this);
            };


    }





    public void showDialog_st(final String title,final String msg, final Context context
    ) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle(title);
        alertBuilder.setMessage(msg);
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {





                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }


}
