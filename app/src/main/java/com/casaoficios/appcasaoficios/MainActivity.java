package com.casaoficios.appcasaoficios;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.casaoficios.appcasaoficios.beans.beanUsuario;
import com.casaoficios.appcasaoficios.fragmentscos.fragment_listasolicitud;
import com.casaoficios.appcasaoficios.util.Constans;
import com.casaoficios.appcasaoficios.util.RequestAsynctask;
import com.casaoficios.appcasaoficios.util.SessionPrefs;


import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity  implements  View.OnClickListener {
 //Para crear la instancia a facebook SDK
    private CallbackManager callbackManager;
    RequestAsynctask request;
    // Para manejar la autenticacion del usuario
    private AccessTokenTracker accessTokenTracker;
    // Para manejar el perfil del usuario
    private ProfileTracker  profileTracker;


    public static final String PREFS_NAME = "SALUDMOCK_PREFS";

   LoginButton lg;
    TextView tx ;
   Button bt;
    EditText clave;


    EditText ltxt1;
    EditText ltxt2;

    AutoCompleteTextView correo;


    final beanUsuario be = new beanUsuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ONCREATE MAIN ACTIVITY");


//        if (SessionPrefs.get(this).isLoggedIn()) {
//            SharedPreferences sharedpreferences;
//            sharedpreferences = getSharedPreferences(PREFS_NAME,
//                    this.MODE_PRIVATE);
//
////            int codusu = SessionPrefs.get(this).getcodigo();
//String Codigo = sharedpreferences.getString("PREF_USER_ID","");
//
////            System.out.println("CODIGO LOGIN : "+codusu);
//            System.out.println("CODIGO LOGIN2 : "+Codigo);
//        }


        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        setTitle("Bienvenido(a)");





        callbackManager = CallbackManager.Factory.create();
        lg=(LoginButton)findViewById(R.id.login_button);
        tx = (TextView)findViewById(R.id.lbl1);
        correo = (AutoCompleteTextView)findViewById(R.id.email);
        clave = (EditText)findViewById(R.id.clave);
        bt = (Button)findViewById(R.id.email_sign_in_button);


//        bt.setFocusable(true);







//        System.out.println("KeyHashes"+KeyHashes());
    }



    private  void Datos(Profile perfil){

        if (perfil!=null){
            String nom=perfil.getName();
            tx.setText("Hola :"+ nom);
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
//
//        System.out.println("ONRESUME MAIN ACTIVITY");
//        if (SessionPrefs.get(this).isLoggedIn()) {
//
//
////            int codusu = SessionPrefs.get(this).getcodigo();
//
//
////            System.out.println("CODIGO LOGIN : "+codusu);
//        }



     /* METODO PARA HACER EL ENLACE CON FACEBOOK
        Profile profile = Profile.getCurrentProfile();
    */

        bt.setFocusable(true);
        request = new RequestAsynctask(this);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = correo.getText().toString();
                String password = clave.getText().toString();

                email = email.replace("@","%40");
//  http://localhost:8081/proyecto_casa_oficios/wsvalidarlogin/validar/usuario/desco%40gmail.com/pass/admin

                String IP = getString(R.string.ip);

                String dataurl =  IP + "/proyecto_casa_oficios/wsvalidarlogin/validar/usuario/" +
                        email.toString() +"/pass/" +
                        password;



                System.out.println("DATOSSSSSSSSS ::  " + dataurl);


                request.validaUsuario(dataurl);


            }
        });




        lg.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //Con el accesos token sabemos si el usuario acepto que nuestra aplicacion utilice su facebook
             AccessToken accessToken = loginResult.getAccessToken();

                Profile profile= Profile.getCurrentProfile();
                Datos(profile);

                accessTokenTracker= new AccessTokenTracker() {
                    @Override
                    protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

                    }
                };

                profileTracker= new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                        Datos(currentProfile);
                    }
                };

                accessTokenTracker.startTracking();
                profileTracker.startTracking();



                // Mas sobre permisos en google : Reference Facebook Login..
                lg.setReadPermissions("user_friends");
                lg.setReadPermissions("public_profile");


            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


    }

    public static String inputStreamToString(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        StringBuilder sb = new StringBuilder();

        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();
    }


    public void validacionterminada(String result) {

System.out.println("LLEGAMOSSSSSSSSSSSSSSS");

        try {
            JSONObject jsonData = new JSONObject(result);

            Log.d("LLEGAMO2S", result);


            JSONArray jsonarray = new JSONArray(jsonData.getString(Constans.APIresponse));
            int number = jsonarray.length();


            if (number > 0){

                JSONObject dato = jsonarray.getJSONObject(0);

                Log.d("LLEGAMO2S", dato.toString());

            /*    cod_user
                        des_user
                nom_user
                        clave_user
                tip_user
                        cod_empleado
                cod_administrado
                        estado
                INDICADOR_SUPERUSUARIO*/



                be.setCOD_USUARIO(dato.getInt("COD_USUARIO"));
                be.setDES_USUARIO(dato.getString("DES_USUARIO"));
                be.setLOG_USUARIO(dato.getString("LOG_USUARIO"));
                be.setPASS_USUARIO(dato.getString("PASS_USUARIO"));
                be.setESTADO(dato.getString("ESTADO"));
                be.setCOD_TIPO_USUARIO(dato.getString("COD_TIPO_USUARIO"));
                be.setFEC_REGISTRO(dato.getString("FEC_REGISTRO"));
                be.setFEC_MODIFICACION(dato.getString("FEC_MODIFICACION"));
                be.setCOD_USUARIO_REGISTRO(dato.getInt("COD_USUARIO_REGISTRO"));


                System.out.println("SE GUARDAAAAAAAAAAAAAAAA");
                SessionPrefs.get(MainActivity.this).saveAffiliate(be);



                mostrarmenuprincipal();
            }else{
                Toast.makeText(this, "Usuario o Clave incorrectas", Toast.LENGTH_LONG).show();
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    public  void mostrarmenuprincipal(){
        Intent intent = new Intent(MainActivity.this,MenuPrincipalActivity.class);
        System.out.println("LLEGOOOOOOOOOOOOOOOOOOO");
        Bundle bun = new Bundle();
        intent.putExtra("beanusuario", be);
        System.out.println("SALIOOOOOOOOOOO");
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onClick(View v) {

    }


}




