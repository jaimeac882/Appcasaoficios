package com.casaoficios.appcasaoficios.fragmentscos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.casaoficios.appcasaoficios.R;
import com.casaoficios.appcasaoficios.adapters.SolicitudesListAdapter;
import com.casaoficios.appcasaoficios.beans.beanSolicituddetrabajo;
import com.casaoficios.appcasaoficios.beans.beanUbigeo;
import com.casaoficios.appcasaoficios.custom.CustomListView;
import com.casaoficios.appcasaoficios.util.Constans;
import com.casaoficios.appcasaoficios.util.RequestAsynctask;
import com.casaoficios.appcasaoficios.util.StringWithTag;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//import municipalidves.dectersoluciones.com.adapter.TramitesListAdapter;
//import municipalidves.dectersoluciones.com.custom.CustomListView;
//import municipalidves.dectersoluciones.com.datamyx.MainActivity;
//import municipalidves.dectersoluciones.com.datamyx.R;
//import municipalidves.dectersoluciones.com.model.beanTramiteList;
//import municipalidves.dectersoluciones.com.model.beanUsuario;
//import municipalidves.dectersoluciones.com.util.Constans;
//import municipalidves.dectersoluciones.com.util.FragmentAsyncTask;


public class fragment_listasolicitud extends Fragment {

    RequestQueue requestQueue;
//    FragmentAsyncTask fr ;
beanSolicituddetrabajo be = new beanSolicituddetrabajo();
    public static CustomListView listatramites;

    String variable;
    public static Context context ;
    public static ArrayList<beanSolicituddetrabajo> elementosoli;
String dataurl = "";

 View rootView;
    public static SolicitudesListAdapter adapter;

    private OnFragmentInteractionListener mListener;

    public fragment_listasolicitud() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


         rootView = inflater.inflate(R.layout.fragment_solicitudes, container, false);
  /*inicializando el elemento de la lista personalizada*/
        listatramites = (CustomListView) rootView.findViewById(R.id.flujosolicitudes);

        context = getActivity();

        return rootView;



    }

    @Override
    public void onResume() {
        super.onResume();


       // request = new RequestAsynctask(getActivity());



//        http://localhost:8081/proyecto_casa_oficios/wssolicitudestrabajo/solicitrab/codigo/63


        dataurl =  getString(R.string.ip) + "/proyecto_casa_oficios/wssolicitudestrabajo/solicitrab/codigo/" +   Constans.cod_usu;


System.out.println("LLEGAMOSSSSSSSSSSSSSSS "+Constans.cod_usu);
        requestQueue = Volley.newRequestQueue(getActivity());


        obtenersolicitudes();
        //request.validaUsuario(dataurl);

/*
        elementolistatramite = new ArrayList<beanTramiteList>();
        elementolistatramite.add(new beanTramiteList("abc","abc1","abc2","abc3"));
        elementolistatramite.add(new beanTramiteList("abc","abc1","abc2","abc2"));


        adapter = new TramitesListAdapter(getActivity(),
                R.layout.item_list_tramite,
                elementolistatramite);

        listatramites.setAdapter(adapter);*/



    }


    public void obtenersolicitudes(){


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, this.dataurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        mTextView.setText("Response is: "+ response.substring(0,500));

                        llenar_solicitudes(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                mTextView.setText("That didn't work!");
            }
        });


        requestQueue.add(stringRequest);
    }


    private void llenar_solicitudes(String response) {

        elementosoli = new ArrayList<beanSolicituddetrabajo>();


//        ArraybeanUbigeo = new ArrayList<beanUbigeo>();


        try {
            JSONObject jsonData = new JSONObject(response);

            JSONArray jsonarray = new JSONArray(jsonData.getString(Constans.APIresponse));
            int number = jsonarray.length();

//            arrayBeanTiposCombo = new ArrayList<String>();


            for (int i = 0; i < number; i++) {
                try {

                    JSONObject jsonObj = jsonarray.getJSONObject(i);


                    beanSolicituddetrabajo bet = new beanSolicituddetrabajo();

                    bet.setCOD_SOLICITUD(Integer.parseInt(jsonObj.get("cod_solicitud").toString()));
                    bet.setDESCRIPCION(jsonObj.get("descripcion").toString());
                    bet.setFEC_REGISTRO(jsonObj.get("fec_registro").toString());
                    bet.setDES_ESTADO(jsonObj.get("desestado").toString());
                    elementosoli.add(bet);

                    // Populate spinner with country names

//                    System.out.println("NOMBRE:  " + jsonObj.get("DES_UBIGEO").toString());



//                    list.add(new StringWithTag(jsonObj.get("DES_UBIGEO").toString(), jsonObj.get("COD_UBIGEO").toString()));





                } catch (JSONException e) {
                    // If there is an error then output this to the logs.
                    Log.e("Volley", "Invalid JSON Object.");
                }

            }


        /*Tipos*/


            adapter = new SolicitudesListAdapter(context,
                    R.layout.item_list_soli,
                    elementosoli);

            listatramites.setAdapter(adapter);


            Constans.listaSolicitudes = new  ArrayList<beanSolicituddetrabajo>();
            Constans.listaSolicitudes = elementosoli;


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }





    /*AsyncTASK*/

/*
    public class FragmentAsynTask extends AsyncTask<String, Void, String> {
        ProgressDialog mensaje;

        private  fragment_listatramite  frg;


        public FragmentAsynTask(fragment_listatramite fr) {
            this.frg = fr;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mensaje = new ProgressDialog(getActivity());
            mensaje.setCancelable(false);
            mensaje.setMessage("Listando Tramites");
            mensaje.show();
        }


        protected String doInBackground(String... args) {

            String response = "";
            try {
                //Connect to WS, recieve a JSON/XML Response
                //Place it somewhere I can use it.


                Log.d("ABCD",args.toString());

                for (String dataurl : args) {
                    try {
                        response = RESTClient.connectAndReturnResponse(dataurl);






                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }




            } catch (Exception e) {
                return "";
            }
            return response;
        }

        protected void onPostExecute(String result) {


            super.onPostExecute(result);
            mensaje.dismiss();
            Log.d("ingresamos al result",result);
            frg.validardatos(result);



        }
    }

*/



}


