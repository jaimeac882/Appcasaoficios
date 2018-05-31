package com.casaoficios.appcasaoficios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.android.volley.toolbox.Volley;
import com.casaoficios.appcasaoficios.interfaces.RegistroCliUsuClient;
import com.casaoficios.appcasaoficios.beans.Resultado;
import com.casaoficios.appcasaoficios.beansInterface.IbeanCliUsu;
import com.casaoficios.appcasaoficios.beans.beanUbigeo;
import com.casaoficios.appcasaoficios.beans.beanUsuario;
import com.casaoficios.appcasaoficios.util.Constans;
import com.casaoficios.appcasaoficios.util.StringWithTag;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jaime on 17/10/2017.
 */
public class Registro_Cli2Activity extends AppCompatActivity implements View.OnClickListener {


    String[] SPINNERLIST = {"Seleccionar Distrito"};
    String baseUrldistritos = "";
    String codigocbo = "";
    IbeanCliUsu beancliusuario = new IbeanCliUsu();

    ArrayList<beanUbigeo> ArraybeanUbigeo;
    ArrayList<String> arrayBeanTiposCombo;

String strcodigo ;
   beanUsuario usuariofi ;

    EditText txttelf;
    EditText txtdire;
    Button btnreg;

    List<StringWithTag> list = new ArrayList<StringWithTag>();

    RequestQueue requestQueue;
    MaterialBetterSpinner combodistr;
    String baseUrlUsuario;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_cliente2);
        setTitle("Contacto");

        combodistr = (MaterialBetterSpinner) findViewById(R.id.cbodistrito);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);

        combodistr.setAdapter(arrayAdapter);

        txttelf = (EditText) findViewById(R.id.campo_telefono2);

        Bundle b = getIntent().getExtras();

        beancliusuario = b.getParcelable("beancliusu");

        txtdire = (EditText) findViewById(R.id.campo_nombre);
//        txttelf.setText(beancliusuario.getP_nom_cliente());

        btnreg = (Button) findViewById(R.id.boton_regcli);

        btnreg.setOnClickListener(this);

        this.baseUrldistritos = getString(R.string.ip) + "/proyecto_casa_oficios/wsubigeo/distritos/";





        requestQueue = Volley.newRequestQueue(this);

        obtenerdistritos();

    }

    private void obtenerdistritos() {


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, this.baseUrldistritos,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        mTextView.setText("Response is: "+ response.substring(0,500));

                        llenar_distritos(response);
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


        combodistr.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StringWithTag s = (StringWithTag) parent.getItemAtPosition(position);

                Object tag = s.tag;
                codigocbo = tag.toString();
//                System.out.println("ENVIAMOS TAG x: "+);
            }
        });


    }

    private void llenar_distritos(String response) {
        // This will add a new repo to our list.
        // It combines the repoName and lastUpdated strings together.
        // And then adds them followed by a new line (\n\n make two new lines).
//        String strRow = repoName + " / " + lastUpdated;
//        String currentText = tvRepoList.getText().toString();
//        this.tvRepoList.setText(currentText + "\n\n" + strRow);

//        System.out.println("123456:  " + response);

//        Log.d("LLEGAMO2S", response);

        ArraybeanUbigeo = new ArrayList<beanUbigeo>();


        try {
            JSONObject jsonData = new JSONObject(response);

            JSONArray jsonarray = new JSONArray(jsonData.getString(Constans.APIresponse));
            int number = jsonarray.length();

            arrayBeanTiposCombo = new ArrayList<String>();


            for (int i = 0; i < number; i++) {
                try {

                    JSONObject jsonObj = jsonarray.getJSONObject(i);


                    beanUbigeo bet = new beanUbigeo();

                    bet.setCOD_UBIGEO(jsonObj.get("COD_UBIGEO").toString());
                    bet.setDES_UBIGEO(jsonObj.get("DES_UBIGEO").toString());
                    ArraybeanUbigeo.add(bet);

                    // Populate spinner with country names

                    System.out.println("NOMBRE:  " + jsonObj.get("DES_UBIGEO").toString());



                    list.add(new StringWithTag(jsonObj.get("DES_UBIGEO").toString(), jsonObj.get("COD_UBIGEO").toString()));





                } catch (JSONException e) {
                    // If there is an error then output this to the logs.
                    Log.e("Volley", "Invalid JSON Object.");
                }

            }


        /*Tipos*/


            ArrayAdapter<StringWithTag> arrayAdapter = new ArrayAdapter<StringWithTag>(this,
                    android.R.layout.simple_dropdown_item_1line, list);


            combodistr.setAdapter(arrayAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private void obtenerUsuario(String codigo) {


        //http://localhost:8081/proyecto_casa_oficios/wsusuario/usuario/codigo/1

        this.baseUrlUsuario = getString(R.string.ip)+"/proyecto_casa_oficios/wsusuario/usuario/codigo/"+codigo;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, this.baseUrlUsuario,
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

    private void llenar_usuario(String response) {


        try {


//
//            System.out.println(response);

            JSONObject jsonData = new JSONObject(response);

            JSONArray jsonarray = new JSONArray(jsonData.getString(Constans.APIresponse));
            int number = jsonarray.length();



            for (int i = 0; i < number; i++) {
                try {
                    // For each repo, add a new line to our repo list.



                    JSONObject jsonObj = jsonarray.getJSONObject(i);

//
                    usuariofi= new beanUsuario();

                    usuariofi.setCOD_USUARIO(Integer.parseInt(jsonObj.get("COD_USUARIO").toString()));
                    usuariofi.setDES_USUARIO(jsonObj.get("DES_USUARIO").toString());
                    usuariofi.setLOG_USUARIO(jsonObj.get("LOG_USUARIO").toString());
                    usuariofi.setPASS_USUARIO(jsonObj.get("PASS_USUARIO").toString());
                    usuariofi.setCOD_TIPO_USUARIO(jsonObj.get("COD_TIPO_USUARIO").toString());
                    usuariofi.setFEC_REGISTRO(jsonObj.get("FEC_REGISTRO").toString());
                    usuariofi.setFEC_MODIFICACION(jsonObj.get("FEC_MODIFICACION").toString());
                    usuariofi.setCOD_USUARIO_REGISTRO(Integer.parseInt(jsonObj.get("COD_USUARIO_REGISTRO").toString()));

                    Log.d("RESULTADO BEAN", usuariofi.getDES_USUARIO());



                    mostrarmenuprincipal();
//                    ArraybeanUbigeo.add(bet);

                    // Populate spinner with country names

//                    System.out.println("NOMBRE:  " + jsonObj.get("DES_UBIGEO").toString());

//                    arrayBeanTiposCombo.add(jsonObj.get("DES_UBIGEO").toString());


//                    list.add(new StringWithTag(jsonObj.get("DES_UBIGEO").toString(), jsonObj.get("COD_UBIGEO").toString()));


                } catch (JSONException e) {
                    // If there is an error then output this to the logs.
                    Log.e("Volley", "Invalid JSON Object.");
                }

            }


        /*Tipos*/


            ArrayAdapter<StringWithTag> arrayAdapter = new ArrayAdapter<StringWithTag>(this,
                    android.R.layout.simple_dropdown_item_1line, list);


            combodistr.setAdapter(arrayAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public  void mostrarmenuprincipal(){
        Intent intent = new Intent(Registro_Cli2Activity.this,MenuPrincipalActivity.class);

        intent.putExtra("beanusuario", usuariofi);
        startActivity(intent);
    }
    public void guardarcli(IbeanCliUsu be){



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




        RegistroCliUsuClient registroCliUsuClient = retrofit.create(RegistroCliUsuClient.class);



        final Call<Resultado> call = registroCliUsuClient.guardarcli(be);
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

                        obtenerUsuario(strcodigo);


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




    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.boton_regcli:



//                String dato = combodistr.getTag().toString();
//
//                System.out.println("ENVIAMOS TAG : " + dato);
//
//                if (dato == "" || dato == null){
//
//
//                }else{
//
//
//                }

                if (codigocbo == "" || codigocbo == null){
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(this.getApplicationContext(),"Por favor , seleccionar Genero", duration);
                    toast.show();
                    break;
                }else{
                    beancliusuario.setP_cod_ubigeo(codigocbo);
                    beancliusuario.setP_direccion(txtdire.getText().toString());
                    beancliusuario.setP_cel_1(txttelf.getText().toString());
                    beancliusuario.setP_cel_2("");
                    beancliusuario.setP_cuenta_facebook("");
                    beancliusuario.setP_cuenta_gmail("");



                    guardarcli(beancliusuario);



                    break;
                }






        }


    }
}
