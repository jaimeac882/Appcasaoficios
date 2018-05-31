package com.casaoficios.appcasaoficios;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Switch;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.casaoficios.appcasaoficios.beans.beanSolicituddetrabajo;
import com.casaoficios.appcasaoficios.beans.beanTip_Averia;
import com.casaoficios.appcasaoficios.beans.beanUbigeo;
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
 * Created by Jaime on 15/11/2017.
 */

public class Registro_UbicacionDirActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,View.OnClickListener,AdapterView.OnItemClickListener,AdapterView.OnItemSelectedListener{
    String[] SPINNERLIST = {"Ninguno"};
    MaterialBetterSpinner materialDesignSpinner;
    RequestQueue requestQueue;
    String baseUrl = "";
    String url = "";
    ArrayList<String> arrayBeanTiposCombo;
    List<StringWithTag> list = new ArrayList<StringWithTag>();

    ArrayList<beanUbigeo> ArrayUbigeo;
EditText mtxtdirecc;
    Switch st;
//    Switch st2;


Intent intent;
    beanUsuario beanus;

    beanSolicituddetrabajo beanSolici;
String codigo;
    Button mbtn_sig_2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ubicacion_direccion);
        setTitle("Ubicación");

        Bundle bund = getIntent().getExtras();
        beanus = bund.getParcelable("beanusuario");
        beanSolici = bund.getParcelable("beansolici");


        codigo = bund.getString("opcion");


        requestQueue = Volley.newRequestQueue(this);

        materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.cb_averia);

        mtxtdirecc = (EditText)findViewById(R.id.txtdirecc);



        mbtn_sig_2 = (Button)findViewById(R.id.btn_sig_2);

        /*Llenado Inicial*/
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);

        materialDesignSpinner.setAdapter(arrayAdapter);


        this.baseUrl =  getString(R.string.ip) + "/proyecto_casa_oficios/wsubigeo/distritos";
        obtener_distritos();


        st = (Switch)findViewById(R.id.switch2);
//        st2 = (Switch)findViewById(R.id.switch3);




        materialDesignSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        materialDesignSpinner.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        materialDesignSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        st.setOnCheckedChangeListener(this);


        mbtn_sig_2.setOnClickListener(this);

        if (codigo.equals("SI")){
            st.setChecked(true);
        }else{
            st.setEnabled(false);
            st.setChecked(false);
        }


    }

    private void obtener_distritos() {
        getRepoList("");

        System.out.println("DATO");
    }





    @Override
    protected void onResume() {
        super.onResume();


//        materialDesignSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                StringWithTag s = (StringWithTag) parent.getItemAtPosition(position);
//
//                Object tag = s.tag;
//
////                materialDesignSpinner.setSelection(2);
////                codigocbo = tag.toString();
////                System.out.println("ENVIAMOS TAG x: "+);
//            }
//        });


//materialDesignSpinner.setSelection(1);
//        materialDesignSpinner.getNextFocusUpId();
//        materialDesignSpinner.notifyAll();

    }

    private void getRepoList(String s) {
        this.url = this.baseUrl;

        System.out.println("URL: "+this.baseUrl);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
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


    private void llenar_distritos(String response) {

        ArrayUbigeo = new ArrayList<beanUbigeo>();



        try {
            JSONObject jsonData = new JSONObject(response);

            JSONArray jsonarray = new JSONArray(jsonData.getString(Constans.APIresponse));
            int number = jsonarray.length();

            arrayBeanTiposCombo = new ArrayList<String>();
            System.out.println("CODIGOOOOOOOOOOOOOOOO ");

            for (int i = 0; i < number; i++) {
                try {
                    // For each repo, add a new line to our repo list.
                    JSONObject jsonObj = jsonarray.getJSONObject(i);


                    beanUbigeo bet = new beanUbigeo();

                    bet.setCOD_UBIGEO(jsonObj.get("COD_UBIGEO").toString());
                    bet.setDES_UBIGEO(jsonObj.get("DES_UBIGEO").toString());
                    ArrayUbigeo.add(bet);

                    // Populate spinner with country names

//                    System.out.println("NOMBRE:  " + jsonObj.get("DES_TIPO_MAESTRO").toString());
                    System.out.println("CODIGOOOOOOOOOOOOOOOO "+ bet.getCOD_UBIGEO());
                    arrayBeanTiposCombo.add(jsonObj.get("DES_UBIGEO").toString());


                    list.add(new StringWithTag(jsonObj.get("DES_UBIGEO").toString(),jsonObj.get("COD_UBIGEO").toString()));




                } catch (JSONException e) {
                    // If there is an error then output this to the logs.
                    Log.e("Volley", "Invalid JSON Object.");
                }

            }





        /*Tipos*/
            ArrayAdapter<StringWithTag> arrayAdapter = new ArrayAdapter<StringWithTag>(this,
                    android.R.layout.simple_dropdown_item_1line, list);



            materialDesignSpinner.setAdapter (arrayAdapter);
//            materialDesignSpinner.setSelection();





        } catch (JSONException e) {
            e.printStackTrace();
        }



    }


//    public static void selectSpinnerItemByValue(MaterialBetterSpinner spnr, long value) {
//        SimpleCursorAdapter adapter = (SimpleCursorAdapter) spnr.getAdapter();
//        for (int position = 0; position < adapter.getCount(); position++) {
//            if(adapter.getItemId(position) == value) {
//                spnr.setSelection(position);
//                return;
//            }
//        }
//    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btn_sig_2:
                //Cuando damos en el evento click del view .. o cualquier controlador que tenga el evento Onclick
                intent = new Intent(Registro_UbicacionDirActivity.this,Registro_averiaActivity.class);
                if (st.isChecked() == false){

                    if (codigocbo == ""){
                        showDialog("Advertencia","Por favor , debe seleccionar su distrito",this);
                        break;
                    }

                    beanSolici.setCOD_UBIGEO(codigocbo);
                    beanSolici.setDIRECCION(mtxtdirecc.getText().toString());
                }else{
                    beanSolici.setCOD_UBIGEO("001010104");
                    beanSolici.setDIRECCION("");
                }

                intent.putExtra("beanusuario", beanus);
                intent.putExtra("beansolici", beanSolici);


                startActivity(intent);
                break;


        }


    }

    String codigocbo="";

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StringWithTag s = (StringWithTag) parent.getItemAtPosition(position);

                Object tag = s.tag;

//                materialDesignSpinner.setSelection(2);
                codigocbo = tag.toString();

//        showDialog("INFO",codigocbo,this);




//        materialDesignSpinner.;
//        materialDesignSpinner.notifyAll();


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

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
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {

            case R.id.switch2:

                if (isChecked== true){

                materialDesignSpinner.setEnabled(false);
                mtxtdirecc.setEnabled(false);
            }
                else{

                    materialDesignSpinner.setEnabled(true);
                    mtxtdirecc.setEnabled(true);
                }
                break;

        }

    }
}
