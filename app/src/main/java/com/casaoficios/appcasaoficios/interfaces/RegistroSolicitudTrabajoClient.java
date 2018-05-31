package com.casaoficios.appcasaoficios.interfaces;

import com.casaoficios.appcasaoficios.beans.Resultado;
import com.casaoficios.appcasaoficios.beansInterface.IbeanSolicituddetrabajo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Jaime on 28/10/2017.
 */

public interface RegistroSolicitudTrabajoClient {



    //"Como se llama nuestra pedition"
    @Headers({"Content-Type:application/json"})
    @POST("add_soltrab")
    Call<Resultado> guardarsoli(@Body IbeanSolicituddetrabajo be);


}
