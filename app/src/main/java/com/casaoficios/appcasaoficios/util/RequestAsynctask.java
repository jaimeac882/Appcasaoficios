package com.casaoficios.appcasaoficios.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.casaoficios.appcasaoficios.MainActivity;

/**
 * Created by Jaime on 21/03/2016.
 */
public class RequestAsynctask {

    Context contexto;


    public RequestAsynctask(Context contexto) {
        super();
        this.contexto = contexto;
    }


    public void validaUsuario(String url) {
        ValidaUsuario obj = new ValidaUsuario();
        obj.execute(url);


    }




    public class ValidaUsuario extends AsyncTask<String, Void, String> {
        ProgressDialog mensaje;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mensaje = new ProgressDialog(contexto);
            mensaje.setCancelable(false);
            mensaje.setMessage("Validando Usuario");
            System.out.println("METODO onPreExecute n");
           mensaje.show();

        }



        @Override
        protected String doInBackground(String... params) {
            System.out.println("METODO doInBackground");
            String response = "";
            System.out.println("METODO doInBackground::: 2");
            for (String url : params) {
                try {
                    response = RESTClient.connectAndReturnResponse(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println("METODO ONPOSTEX");
            super.onPostExecute(result);
            System.out.println("METODO ONPOSTEX:::2");
            mensaje.dismiss();
            ((MainActivity) contexto).validacionterminada(result);

        }




    }





}
