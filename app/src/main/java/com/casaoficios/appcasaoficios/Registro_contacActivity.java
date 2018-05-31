package com.casaoficios.appcasaoficios;

import android.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import com.casaoficios.appcasaoficios.beans.Resultado;
import com.casaoficios.appcasaoficios.beans.beanCliente;
import com.casaoficios.appcasaoficios.beans.beanImagen;
import com.casaoficios.appcasaoficios.beans.beanSolicituddetrabajo;
import com.casaoficios.appcasaoficios.beans.beanUsuario;
import com.casaoficios.appcasaoficios.beansInterface.IbeanSolicituddetrabajo;
import com.casaoficios.appcasaoficios.interfaces.RegistroCliUsuClient;
import com.casaoficios.appcasaoficios.interfaces.RegistroSolicitudTrabajoClient;
import com.casaoficios.appcasaoficios.util.Constans;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jaime on 28/09/2017.
 */
public class Registro_contacActivity extends AppCompatActivity implements View.OnClickListener{


    RequestQueue requestQueue;
    private StorageReference mystorage;
    String url = "";

    beanCliente bcli ;
    String baseUrl =  "";
    EditText txtnombre;
    EditText txtApe;
    EditText Telf;
    Switch st ;
    beanUsuario beanus;
    String apellidos = "";
    beanSolicituddetrabajo beanSolici;
    String cadena;
    ArrayList<beanImagen> ArrayListBeIm = new ArrayList<beanImagen>();

    Button lbtn_sig_con;
    String strcodigo="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ref_contacto);

        mystorage = FirebaseStorage.getInstance().getReference();
        requestQueue = Volley.newRequestQueue(this);

        st = (Switch)findViewById(R.id.switch1);

        txtnombre = (EditText)findViewById(R.id.campo_nombre);
        txtApe = (EditText)findViewById(R.id.campo_nombrepat);
        Telf = (EditText)findViewById(R.id.campo_telefono);

        setTitle("Contacto");
        lbtn_sig_con = (Button)findViewById(R.id.btn_reg_ave);

        lbtn_sig_con.setOnClickListener(this);

        Bundle bund = getIntent().getExtras();


        beanus = bund.getParcelable("beanusuario");
        beanSolici = bund.getParcelable("beansolici");

        ArrayListBeIm =  (ArrayList<beanImagen>)getIntent().getSerializableExtra("listarray");

        String hosturl =  getString(R.string.ip);;

        this.baseUrl = hosturl + "/proyecto_casa_oficios/wscliente/clientexuserid/"+beanus.getCOD_USUARIO();

if (ArrayListBeIm != null){
//            showDialog("Registros : "+ArrayListBeIm.size(),Registro_contacActivity.this);
        }

    }



//    public void get_cli(){
//
//        ();
//
//
//    }






    private void getRepoList() {
        // First, we insert the username into the repo url.
        // The repo url is defined in GitHubs API docs (https://developer.github.com/v3/repos/).



        this.url = this.baseUrl;

        // Next, we create a new JsonArrayRequest. This will use Volley to make a HTTP request
        // that expects a JSON Array Response.
        // To fully understand this, I'd recommend readng the office docs: https://developer.android.com/training/volley/index.html
      /*  JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                       System.out.println("RESPUESTAAAAAAAA :"+ response.toString());
                        Log.e("RESPUESTAAA ",response.toString());

                        // Check the length of our response (to see if the user has any repos)
                        if (response.length() > 0) {
                            // The user does have repos, so let's loop through them all.
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    // For each repo, add a new line to our repo list.
                                    JSONObject jsonObj = response.getJSONObject(i);
                                    String repoName = jsonObj.get("COD_TIPO_MAESTRO").toString();
                                    String lastUpdated = jsonObj.get("DES_TIPO_MAESTRO").toString();
                                    addToRepoList(repoName, lastUpdated);
                                } catch (JSONException e) {
                                    // If there is an error then output this to the logs.
                                    Log.e("Volley", "Invalid JSON Object.");
                                }

                            }
                        } else {
                            // The user didn't have any repos.
                            setRepoListText("No repos found.");
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // If there a HTTP error then add a note to our repo list.
                        setRepoListText("Error while calling REST API");
                        Log.e("Volley", error.toString());
                    }
                }
        );
        // Add the request we just defined to our request queue.
        // The request queue will automatically handle the request as soon as it can.

*/


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        mTextView.setText("Response is: "+ response.substring(0,500));

                        obtener_cliente(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
            }
        });


        requestQueue.add(stringRequest);
    }



    public void obtener_cliente(String response){


        System.out.println("123456:  " + response);

//        Log.d("LLEGAMO2S", response);

        bcli  = new beanCliente();



        try {
            JSONObject jsonData = new JSONObject(response);

            JSONArray jsonarray = new JSONArray(jsonData.getString(Constans.APIresponse));
            int number = jsonarray.length();
//
//            arrayBeanTiposCombo = new ArrayList<String>();


            for (int i = 0; i < number; i++) {
                try {
                    // For each repo, add a new line to our repo list.
                    JSONObject jsonObj = jsonarray.getJSONObject(i);



                    bcli.setCOD_CLIENTE(Integer.parseInt(jsonObj.get("COD_CLIENTE").toString()));
                    bcli.setCOD_TIPO_DOCUMENTO(Integer.parseInt(jsonObj.get("COD_TIPO_DOCUMENTO").toString()));


                    bcli.setCOD_TIPO_GENERO(Integer.parseInt(jsonObj.get("COD_TIPO_GENERO").toString()));
                    bcli.setCOD_USUARIO(Integer.parseInt(jsonObj.get("COD_USUARIO").toString()));
                    bcli.setCOD_TIPO_CANAL_CONTACTO(Integer.parseInt(jsonObj.get("COD_TIPO_CANAL_CONTACTO").toString()));
                    bcli.setCOD_USUARIO_REGISTRO(Integer.parseInt(jsonObj.get("COD_USUARIO_REGISTRO").toString()));
                    bcli.setESTADO(Integer.parseInt(jsonObj.get("ESTADO").toString()));

                    bcli.setNOM_CLIENTE(jsonObj.get("NOM_CLIENTE").toString());
                    bcli.setAPE_PATERNO(jsonObj.get("APE_PATERNO").toString());
                    bcli.setAPE_MATERNO(jsonObj.get("APE_MATERNO").toString());
                    bcli.setNUM_DOCUMENTO(jsonObj.get("NUM_DOCUMENTO").toString());

                    bcli.setFEC_MODIFICACION(jsonObj.get("FEC_MODIFICACION").toString());
                    bcli.setFEC_REGISTRO(jsonObj.get("FEC_REGISTRO").toString());
                    bcli.setCOD_UBIGEO(jsonObj.get("COD_UBIGEO").toString());
                    bcli.setDIRECCION(jsonObj.get("DIRECCION").toString());
                    bcli.setCEL_1(jsonObj.get("CEL_1").toString());
                    bcli.setCEL_2(jsonObj.get("CEL_2").toString());


                    bcli.setCUENTA_FACEBOOK(jsonObj.get("CUENTA_FACEBOOK").toString());
                    bcli.setCUENTA_GMAIL(jsonObj.get("CUENTA_GMAIL").toString());
                    bcli.setFEC_NACIMIENTO(jsonObj.get("FEC_NACIMIENTO").toString());


//                    ArraybeanTiposAve.add(bet);

                    // Populate spinner with country names

//                    System.out.println("NOMBRE:  " + jsonObj.get("DES_TIPO_MAESTRO").toString());

//                    arrayBeanTiposCombo.add(jsonObj.get("DES_TIPO_AVERIA").toString());


//                    list.add(new StringWithTag(jsonObj.get("DES_TIPO_AVERIA").toString(),jsonObj.get("COD_TIPAVERIA").toString()));


                } catch (JSONException e) {
                    // If there is an error then output this to the logs.
                    Log.e("Volley", "Invalid JSON Object.");
                }

            }


        /*Tipos*/
//            ArrayAdapter<StringWithTag> arrayAdapter = new ArrayAdapter<StringWithTag>(this,
//                    android.R.layout.simple_dropdown_item_1line, list);
//
//
//            materialDesignSpinner.setAdapter (arrayAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


    private void disableEditText(EditText editText) {
//        editText.setFocusable(false);
        editText.setEnabled(false);
//        editText.setCursorVisible(false);
////        editText.setKeyListener(null);
//        editText.setBackgroundColor(Color.TRANSPARENT);
    }


    private void enableEditText(EditText editText) {
//        editText.setFocusable(true);
        editText.setEnabled(true);
////        editText.setCursorVisible(true);
//////        editText.setKeyListener(null);
////        editText.setBackgroundColor(Color.WHITE);
    }


    @Override
    protected void onResume() {
        super.onResume();
//    Context c = getApplicationContext();


        getRepoList();

        st.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked == true && bcli.getNOM_CLIENTE() != null) {

                    apellidos = "";
                    txtnombre.setText(bcli.getNOM_CLIENTE());

                    apellidos = bcli.getAPE_PATERNO() + " " +  bcli.getAPE_MATERNO() ;

                    txtApe.setText(apellidos);
                    Telf.setText(bcli.getCEL_1());


                    disableEditText(txtnombre);
                    disableEditText(txtApe);
                    disableEditText(Telf);



                }else if (bcli.getNOM_CLIENTE() != null){

                    txtnombre.setText("");
                    txtApe.setText("");
                    Telf.setText("");

                    enableEditText(txtnombre);
                    enableEditText(txtApe);
                    enableEditText(Telf);

                }


//                Log.v("Switch State=", "" + isChecked);
            }

        });


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_reg_ave:

                Intent intent = new Intent(Registro_contacActivity.this,Registro_UbicacionActivity.class);



//                beanSolici = new beanSolicituddetrabajo();

                beanSolici.setCOD_CLIENTE(bcli.getCOD_CLIENTE());
                beanSolici.setNOMBRE(txtnombre.getText().toString() + "" + txtApe.getText().toString());
                beanSolici.setNOMBRE(beanus.getLOG_USUARIO());
                beanSolici.setTELEFONO(Telf.getText().toString());



                beanSolici.setCOD_OFICIO(1);



                guardar_averia(beanSolici);



//
//                intent.putExtra("beanusuario", beanus);
//                intent.putExtra("beansolici", beanSolici);


//                startActivity(intent);
                break;


        }
    }



    public void showDialog(final String msg, final Context context) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Codigo generado");
        alertBuilder.setMessage(msg + "");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }


    public void showDialog_final(final String msg, final Context context) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Información");
        alertBuilder.setMessage(msg + "");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mostrarpantallaprincipal();
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }



    void mostrarpantallaprincipal(){
        Intent in = new Intent(Registro_contacActivity.this,MenuPrincipalActivity.class);
        in.putExtra("beanusuario",beanus);
        startActivity(in);
    }





    public void guardar_averia(beanSolicituddetrabajo be)
    {


        /*Set los parametros*/

        IbeanSolicituddetrabajo ibe = new IbeanSolicituddetrabajo();


        ibe.setp_cod_cliente(be.getCOD_CLIENTE());
        ibe.setp_cordenadas_registro(be.getCORDENADAS_REGISTRO());
        ibe.setp_cordenadas_ubicacion(be.getCORDENADAS_UBICACION());
        ibe.setp_cod_tipo_averia(be.getCOD_TIPO_AVERIA());
        ibe.setp_cod_tipo_prioridad(1);
        ibe.setp_nombre(be.getNOMBRE());
        ibe.setp_email(be.getEMAIL());
        ibe.setp_telefono(be.getTELEFONO());
        ibe.setp_descripcion(be.getDESCRIPCION());
        ibe.setp_estado("1");
        ibe.setp_precio_presupuesto(0.0);
        ibe.setp_precio_final(0.0);
        ibe.setp_cod_tipo_registro(2);
        ibe.setp_fec_registro("2017-10-10");
        ibe.setp_fec_modificacion("2017-10-10");
        ibe.setp_cod_usuario_registro(beanus.getCOD_USUARIO());

//        if (be.getCOD_UBIGEO() == ""){
//            ibe.setp_cod_ubigeo("001010104");
//            }else{
            ibe.setp_cod_ubigeo(be.getCOD_UBIGEO());
//        }


        ibe.setp_direccion(be.getDIRECCION());
        ibe.setp_cod_oficio(1);



        OkHttpClient.Builder okhht = new OkHttpClient.Builder();


        HttpLoggingInterceptor httinter = new HttpLoggingInterceptor();
        httinter.setLevel(HttpLoggingInterceptor.Level.BODY) ;
        okhht.addInterceptor(httinter);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        String hosturl =  getString(R.string.ip);;

        String url = hosturl + "/proyecto_casa_oficios/index.php/wsmetodospost/";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                // .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okhht.build())
                .build();




        RegistroSolicitudTrabajoClient rSolicitudCli = retrofit.create(RegistroSolicitudTrabajoClient.class);





        final Call<Resultado> call = rSolicitudCli.guardarsoli(ibe);
        call.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, retrofit2.Response<Resultado> response) {
                //"Si hay un resultado"

                if (response == null){

                    Log.d("result","URL errada");
                }else{

                    Resultado r = response.body();
                    if (r.getMensaje()!="");{
//                        beanUsuario bean = new beanUsuario();

                        strcodigo = r.getMensaje().toString();

//                        obtenerUsuario(strcodigo);

                       if (strcodigo != "" || strcodigo != null){

                           for (beanImagen b: ArrayListBeIm) {

                               Uri ur = b.getStruri();


                               cadena = "";
                               cadena = "tb_solicitud_trabajo_documento/"+strcodigo;

                               StorageReference filepath = mystorage.child(cadena).child(ur.getLastPathSegment());

                               filepath.putFile(ur).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                   @Override
                                   public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                       Toast.makeText(PruebaFirebaseActivity.this,"Se Cargo correctamente la imagen", Toast.LENGTH_SHORT)
//                                               .show();
                                   }


                               });
                           }

                           Constans.lastcode = strcodigo;
                           showDialog_final("Su codigo de averia es : "+ strcodigo, Registro_contacActivity.this);


                       }


                    }

                    Log.d("result", r.getMensaje());
                    Log.d("result",response.body().toString());



                }


            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                // "Si existe algun error";


                Log.d("error",t.toString());

            }
        });






    }




}
