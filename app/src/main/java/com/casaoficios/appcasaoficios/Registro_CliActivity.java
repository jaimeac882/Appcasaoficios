package com.casaoficios.appcasaoficios;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.casaoficios.appcasaoficios.beansInterface.IbeanCliUsu;
import com.casaoficios.appcasaoficios.beans.beanTipos;
import com.casaoficios.appcasaoficios.util.Constans;
import com.casaoficios.appcasaoficios.util.StringWithTag;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Jaime on 6/09/2017.
 */
public class Registro_CliActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<beanTipos> ArraybeanTipos;
    ArrayList<String> arrayBeanTiposCombo;
   String codigocbo = "";
    List<StringWithTag> list = new ArrayList<StringWithTag>();
EditText tcampo_correo;
    Button btnreg ;

EditText tclave;
//    String[] arraBeanTip_S;

    String[] SPINNERLIST = {"Seleccionar Genero"};

    beanTipos b = new beanTipos();

    MaterialBetterSpinner cboGenero;
    String baseUrl = "";
    RequestQueue requestQueue;
    String url = "";

String fechaactual = "";


    /*Datos del EditText*/

    EditText tcampo_nombre ;
    EditText tcampo_apepa;
    EditText tcampo_apema;


            EditText editfecha;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Fade facein = new Fade(Fade.IN);
        facein.setDuration(InicialActivity.DURATION);
        facein.setInterpolator(new DecelerateInterpolator());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(facein);
        }
        setContentView(R.layout.reg_cliente);




//        setContentView(R.layout.reg_cliente);
        setTitle("Datos Personales");

        tcampo_correo = (EditText)findViewById(R.id.campo_correo);
        tclave =  (EditText)findViewById(R.id.clave);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        requestQueue = Volley.newRequestQueue(this);


        tcampo_nombre = (EditText)findViewById(R.id.campo_nom_cli);
        tcampo_apepa = (EditText)findViewById(R.id.campo_nombrepat);
        tcampo_apema = (EditText)findViewById(R.id.campo_nombremat);


        cboGenero = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner);


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);

        cboGenero.setAdapter(arrayAdapter);

        btnreg = (Button)findViewById(R.id.boton_regcli);

        editfecha = (EditText)findViewById(R.id.campo_fecha);

        btnreg.setOnClickListener(this);

        editfecha.setOnClickListener(this);



        this.baseUrl =    getString(R.string.ip) + "/proyecto_casa_oficios/wsgenero/generos/";



        Calendar fecha = new GregorianCalendar();
        //Obtenemos el valor del año, mes, día,
        //hora, minuto y segundo del sistema
        //usando el método get y el parámetro correspondiente
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);

       Log.d("Mes",String.valueOf(mes));

        mes  = mes + 1;
        String strmes = "";
        String strdia = "";

        if (mes <= 9) {
            strmes = "0" + mes;
        }else{

            strmes =  String.valueOf(mes);
        }

        if (dia <= 9) {

            strdia = "0" + dia;
        }else{

            strdia =  String.valueOf(dia);

        }


        fechaactual = anio + "-" + strmes +"-"+ strdia ;


        editfecha.setText(strdia + "/" + strmes + "/" +anio);
        obtener_generos();






        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
    }



    public void obtener_generos(){

//        try {
//            URL httpbinEndpoint = new URL("https://httpbin.org/post");
//
//            HttpsURLConnection myConnection
//                    = (HttpsURLConnection) httpbinEndpoint.openConnection();
//
//
//            myConnection.setRequestMethod("POST");
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        getRepoList("");


    }

    public void invocaCalendario(){
        Calendar mcurrentDate=Calendar.getInstance();
        int anio =mcurrentDate.get(Calendar.YEAR);
        int mes =mcurrentDate.get(Calendar.MONTH);
        int dia=mcurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker=new DatePickerDialog(Registro_CliActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {

                // Tu código
                //
                String Fecha = "";
                String mes = "";
                String dia = "";


                int imes = selectedmonth + 1;

                if (imes <= 9) {
                    mes = "0" + imes;
                }else{

                    mes = String.valueOf(imes);
                }

                if (selectedday <= 9) {

                    dia = "0" + selectedday;
                }else{

                    dia = String.valueOf(selectedday);
                }


//                Fecha = selectedyear +"-"+ mes +"-"+ dia;

                Fecha = dia + "/" + mes +"/"+ selectedyear;

                editfecha.setText(Fecha);
            }
        },anio, mes, dia);
        mDatePicker.setTitle("Selecciona Fecha");
        mDatePicker.show();


    }






    private void clearRepoList() {
        // This will clear the repo list (set it as a blank string).

//        String[] SPINNERLIST = "";
//
//        this.tvRepoList.setText("");
    }

    private void llenar_datosgeneros(String response) {
        // This will add a new repo to our list.
        // It combines the repoName and lastUpdated strings together.
        // And then adds them followed by a new line (\n\n make two new lines).
//        String strRow = repoName + " / " + lastUpdated;
//        String currentText = tvRepoList.getText().toString();
//        this.tvRepoList.setText(currentText + "\n\n" + strRow);

        System.out.println("123456:  " + response);

//        Log.d("LLEGAMO2S", response);

        ArraybeanTipos = new ArrayList<beanTipos>();



        try {
            JSONObject jsonData = new JSONObject(response);

            JSONArray jsonarray = new JSONArray(jsonData.getString(Constans.APIresponse));
            int number = jsonarray.length();

            arrayBeanTiposCombo = new ArrayList<String>();


            for (int i = 0; i < number; i++) {
                try {
                    // For each repo, add a new line to our repo list.
                    JSONObject jsonObj = jsonarray.getJSONObject(i);


                    beanTipos bet = new beanTipos();

                    bet.setCOD_TIPO_MAESTRO(Integer.parseInt(jsonObj.get("COD_TIPO_MAESTRO").toString()));
                    bet.setDES_TIPO_MAESTRO(jsonObj.get("DES_TIPO_MAESTRO").toString());
                    ArraybeanTipos.add(bet);

                    // Populate spinner with country names

                    System.out.println("NOMBRE:  " + jsonObj.get("DES_TIPO_MAESTRO").toString());

                    arrayBeanTiposCombo.add(jsonObj.get("DES_TIPO_MAESTRO").toString());


                    list.add(new StringWithTag(jsonObj.get("DES_TIPO_MAESTRO").toString(),jsonObj.get("COD_TIPO_MAESTRO").toString()));




                } catch (JSONException e) {
                    // If there is an error then output this to the logs.
                    Log.e("Volley", "Invalid JSON Object.");
                }

            }


        /*Tipos*/



            ArrayAdapter<StringWithTag> arrayAdapter = new ArrayAdapter<StringWithTag>(this,
                    android.R.layout.simple_dropdown_item_1line, list);


            cboGenero.setAdapter (arrayAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }



    private void setRepoListText(String str) {
        // This is used for setting the text of our repo list box to a specific string.
        // We will use this to write a "No repos found" message if the user doens't have any.
//        this.tvRepoList.setText(str);
    }


    @Override
    protected void onResume() {
        super.onResume();





        cboGenero.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StringWithTag s = (StringWithTag) parent.getItemAtPosition(position);

                Object tag = s.tag;
                codigocbo = tag.toString();
//                System.out.println("ENVIAMOS TAG x: "+);
            }
        });


    }

    private void getRepoList(String username) {
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

                        llenar_datosgeneros(response);
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
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.boton_regcli:

                IbeanCliUsu be = new IbeanCliUsu();


//                Calendar c = Calendar.getInstance();
//                System.out.println("Current time => " + c.getTime());
//
//                SimpleDateFormat df = new SimpleDateFormat("yyyyy-MMM-dd");
//                String formattedDate = df.format(c.getTime());



                be.setP_log_usuario(tcampo_correo.getText().toString());
                be.setP_pass_usuario(tclave.getText().toString());
                be.setP_estado(1);//Estado Usuario
                be.setP_cod_tipo_usuario("2"); // Tipo usuario 2 =
                be.setP_fec_registro(fechaactual);
                be.setP_fec_modificacion(fechaactual);
                be.setP_cod_usuario_registro(2);
                be.setP_nom_cliente(tcampo_nombre.getText().toString());

                String strfec_na = "";
                String[] items = editfecha.getText().toString().split("/");

                strfec_na = items[2] + "-" + items[1] + "-"+   items[0];


                be.setP_ape_paterno(tcampo_apema.getText().toString());
                be.setP_ape_materno(tcampo_apepa.getText().toString());
                be.setP_cod_tipo_documento(0);
                be.setP_num_documento("");
                be.setP_fecha_nacimiento(strfec_na);
                be.setP_cod_tipo_canal_contacto(3);
                be.setP_estado_cli("1");
                be.setP_fec_modificacion_cli(fechaactual);
                be.setP_fec_registro_cli(fechaactual);

                be.setP_cod_usuario_registro_cli(1);
//                String dato = cboGenero.getTag().toString();

//                System.out.println("ENVIAMOS TAG : " + dato);

//



                if (codigocbo == "" || codigocbo == null){
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(this.getApplicationContext(),"Por favor , seleccionar Genero", duration);
                toast.show();

                }else{
                    be.setP_cod_tipo_genero(Integer.parseInt(codigocbo));

                    Intent in = new Intent(Registro_CliActivity.this,Registro_Cli2Activity.class);
                    in.putExtra("beancliusu",be);
                    startActivity(in);
                    break;
                }


//                be.setP_cod_ubigeo("001000000");
//                be.setP_direccion("");
//                be.setP_cel_1("");
//                be.setP_cel_2("");
//                be.setP_cuenta_facebook("");
//                be.setP_cuenta_gmail("");


            case R.id.campo_fecha:
                invocaCalendario();

            default:
                break;

        }


    }








}
