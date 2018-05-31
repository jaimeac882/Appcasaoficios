package com.casaoficios.appcasaoficios.interfaces;


import com.casaoficios.appcasaoficios.beans.Resultado;
import com.casaoficios.appcasaoficios.beansInterface.IbeanCliUsu;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * Created by Jaime on 16/10/2017.
 */
public interface RegistroCliUsuClient{




    //"Como se llama nuestra pedition"
    @Headers({"Content-Type:application/json"})
   @POST("add_cliusu")

    Call<Resultado> guardarcli(@Body IbeanCliUsu be);

}
