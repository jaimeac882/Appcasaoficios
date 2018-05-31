package com.casaoficios.appcasaoficios;

import android.*;
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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.casaoficios.appcasaoficios.adapters.ImageListAdapter;
import com.casaoficios.appcasaoficios.beans.beanImagen;
import com.casaoficios.appcasaoficios.beans.beanSolicituddetrabajo;
import com.casaoficios.appcasaoficios.beans.beanTip_Averia;
import com.casaoficios.appcasaoficios.beans.beanUsuario;
import com.casaoficios.appcasaoficios.util.Constans;
import com.casaoficios.appcasaoficios.util.StringWithTag;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Jaime on 28/09/2017.
 */
public class Registro_averiaActivity extends AppCompatActivity  implements View.OnClickListener , AdapterView.OnItemClickListener {

    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    String baseUrl = "";
    RequestQueue requestQueue;
    String url = "";
String codigocbo = "";
    ImageListAdapter adapter;
EditText ltxtaveria ;


    ArrayList<beanImagen> belist = new  ArrayList<beanImagen>();

    private static final int GALLERY_INENT = 1;

Button mbtn_sig_con2;
//    private final Activity context;
//    private final String[] itemname;
//    private final Integer[] integers;

    beanUsuario beanus;



    Button mbtnurge;
    Button mbtnmedi;
    Button mbtnbaja;

    String codigourgencia="";


    beanSolicituddetrabajo beanSolici;

    ArrayList<beanTip_Averia> ArraybeanTiposAve;
    ArrayList<String> arrayBeanTiposCombo;
    List<StringWithTag> list = new ArrayList<StringWithTag>();
    MaterialBetterSpinner materialDesignSpinner;

    String[] SPINNERLIST = {"Android Material Design", "Material Design Spinner", "Spinner Using Material Library", "Material Spinner Example"};
Button mbtnadd;



ListView mlinlist;

    private void setRepoListText(String str) {
        // This is used for setting the text of our repo list box to a specific string.
        // We will use this to write a "No repos found" message if the user doens't have any.
//        this.tvRepoList.setText(str);
    }


    public void metodo_inte(){


        if (belist.size()<= 2) {

            Intent iNT = new Intent(Intent.ACTION_PICK);
            //"Todas las imagenes
            iNT.setType("image/*");
            startActivityForResult(iNT, GALLERY_INENT);
        }else{
            showDialog("Advertencia","Solo puede agregar 3 imagenes , si desea agregar mas imagenes favor de eliminar una",Registro_averiaActivity.this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();


        materialDesignSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StringWithTag s = (StringWithTag) parent.getItemAtPosition(position);

                Object tag = s.tag;
                codigocbo = tag.toString();
//                System.out.println("ENVIAMOS TAG x: "+);
            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GALLERY_INENT && resultCode == RESULT_OK) {

                Uri ur = data.getData();



                beanImagen beim = new beanImagen();
                beim.setCodigo(ur.getLastPathSegment());
                beim.setNombre(ur.getLastPathSegment());
                beim.setPath_ruta(ur.getPath());
                beim.setStruri(ur);

                if (adapter == null){
                    belist.add(beim);
                    adapter=new ImageListAdapter(this,belist);
                    mlinlist.setAdapter(adapter);
                }else{
                    adapter.add(beim);
                }





//                belist.add(beim);
//                actualizaradapter();



    }



        }

    private void clearRepoList() {
        // This will clear the repo list (set it as a blank string).

//        String[] SPINNERLIST = "";
//
//        this.tvRepoList.setText("");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_averia);
        setTitle("Describe la averia");

        Bundle bund = getIntent().getExtras();
mbtn_sig_con2 = (Button)findViewById(R.id.btn_sig_con2);

        mbtnurge = (Button)findViewById(R.id.btnurge);
        mbtnmedi = (Button)findViewById(R.id.btnmedi);
        mbtnbaja = (Button)findViewById(R.id.btnbaja);




        mbtn_sig_con2.setOnClickListener(this);
        mbtnurge.setOnClickListener(this);
        mbtnmedi.setOnClickListener(this);
        mbtnbaja.setOnClickListener(this);

        mlinlist = (ListView)findViewById(R.id.lstlistx);
        mlinlist.setOnItemClickListener(this);
        beanus = bund.getParcelable("beanusuario");
        beanSolici = bund.getParcelable("beansolici");
//        beanSolici = bund.getParcelable("beansolici");
//        this.context=context;
//        this.itemname=itemname;
//        this.integers=integers;

        requestQueue = Volley.newRequestQueue(this);


        mbtnadd = (Button)findViewById(R.id.btnadd);

        mbtnadd.setOnClickListener(this);

        ltxtaveria = (EditText)findViewById(R.id.txtaveria);

//
        materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.spinner_averia);

//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
//
//        materialDesignSpinner.setAdapter(arrayAdapter);



        /*Llenado Inicial*/
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);

        materialDesignSpinner.setAdapter(arrayAdapter);

         String hosturl =  getString(R.string.ip);;


//        this.url =    hosturl + "proyecto_casa_oficios/wstipaveria/averias";

        this.baseUrl = hosturl + "/proyecto_casa_oficios/wstipaveria/averias";

        obtener_averias();

    }



    public void obtener_averias(){

        getRepoList("");


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

                        llenar_averias(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
            }
        });


        requestQueue.add(stringRequest);
    }


    private void llenar_averias(String response) {
        // This will add a new repo to our list.
        // It combines the repoName and lastUpdated strings together.
        // And then adds them followed by a new line (\n\n make two new lines).
//        String strRow = repoName + " / " + lastUpdated;
//        String currentText = tvRepoList.getText().toString();
//        this.tvRepoList.setText(currentText + "\n\n" + strRow);

        System.out.println("123456:  " + response);

//        Log.d("LLEGAMO2S", response);

        ArraybeanTiposAve = new ArrayList<beanTip_Averia>();



        try {
            JSONObject jsonData = new JSONObject(response);

            JSONArray jsonarray = new JSONArray(jsonData.getString(Constans.APIresponse));
            int number = jsonarray.length();

            arrayBeanTiposCombo = new ArrayList<String>();


            for (int i = 0; i < number; i++) {
                try {
                    // For each repo, add a new line to our repo list.
                    JSONObject jsonObj = jsonarray.getJSONObject(i);


                    beanTip_Averia bet = new beanTip_Averia();

                    bet.setCOD_TIPAVERIA(Integer.parseInt(jsonObj.get("COD_TIPAVERIA").toString()));
                    bet.setDES_TIPO_AVERIA(jsonObj.get("DES_TIPO_AVERIA").toString());
                    ArraybeanTiposAve.add(bet);

                    // Populate spinner with country names

//                    System.out.println("NOMBRE:  " + jsonObj.get("DES_TIPO_MAESTRO").toString());

                    arrayBeanTiposCombo.add(jsonObj.get("DES_TIPO_AVERIA").toString());


                    list.add(new StringWithTag(jsonObj.get("DES_TIPO_AVERIA").toString(),jsonObj.get("COD_TIPAVERIA").toString()));




                } catch (JSONException e) {
                    // If there is an error then output this to the logs.
                    Log.e("Volley", "Invalid JSON Object.");
                }

            }





        /*Tipos*/
            ArrayAdapter<StringWithTag> arrayAdapter = new ArrayAdapter<StringWithTag>(this,
                    android.R.layout.simple_dropdown_item_1line, list);


            materialDesignSpinner.setAdapter (arrayAdapter);






        } catch (JSONException e) {
            e.printStackTrace();
        }


    }






    @Override
    public void onClick(View v) {




            switch (v.getId()) {

                case R.id.btn_sig_con2:

                    if (codigourgencia == ""){
                        showDialog("Advertencia","Debe seleccionar su prioridad de averia",this);
                        break;
                    }


            System.out.println("VAAA");
                    if (codigocbo == "" || codigocbo == null){
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(this.getApplicationContext(),"Por favor , seleccionar su tipo de averia", duration);
                        toast.show();
                        break;
                    }else{

                        Intent intent = new Intent(Registro_averiaActivity.this,Registro_contacActivity.class);

                        beanSolici.setDESCRIPCION(ltxtaveria.getText().toString());
                        beanSolici.setCOD_TIPO_AVERIA(Integer.parseInt( codigocbo));
                        beanSolici.setCOD_TIPO_PRIORIDAD(Integer.parseInt(codigourgencia));

                        intent.putExtra("beanusuario", beanus);
                        intent.putExtra("beansolici", beanSolici);

                        intent.putExtra("listarray", belist);

                        startActivity(intent);
                    }


                    break;


                case R.id.btnadd:
                    if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {
                        metodo_inte();
                    }

                    break;


                case R.id.btnurge:

                    settingbutons("urgente");
                    codigourgencia = "1";

                    break;

                case R.id.btnmedi:
                    settingbutons("medio");
                    codigourgencia = "2";
                    break;

                case R.id.btnbaja:
                    settingbutons("baja");
                    codigourgencia = "3";

                    break;



            }


    }


    void  settingbutons(String prioriodad){




        if (prioriodad == "urgente"){
            mbtnurge.setBackgroundColor(getResources().getColor(R.color.buton_color_red));
            mbtnmedi.setBackgroundColor(getResources().getColor(R.color.button_color_gris));
            mbtnbaja.setBackgroundColor(getResources().getColor(R.color.button_color_gris));

        }else if (prioriodad == "medio"){
            mbtnurge.setBackgroundColor(getResources().getColor(R.color.button_color_gris));
            mbtnmedi.setBackgroundColor(getResources().getColor(R.color.buton_color_celeste));
            mbtnbaja.setBackgroundColor(getResources().getColor(R.color.button_color_gris));


        }else if (prioriodad == "baja"){
            mbtnurge.setBackgroundColor(getResources().getColor(R.color.button_color_gris));
            mbtnmedi.setBackgroundColor(getResources().getColor(R.color.button_color_gris));
            mbtnbaja.setBackgroundColor(getResources().getColor(R.color.buton_color_green));

        }

    }




    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog_permisos("Permiso de acceso a almacenamiento externo", context,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { android.Manifest.permission.READ_EXTERNAL_STORAGE },
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



    public void showDialog_permisos(final String msg, final Context context,
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


    public void showDialog(final String title,final String msg, final Context context
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        System.out.println("POSICIONNNNNNNNN:"+position);
        System.out.println("POSICIONNNNNNNNN2:"+id);

    }
}
