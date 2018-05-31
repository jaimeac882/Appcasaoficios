package com.casaoficios.appcasaoficios.beans;

/**
 * Created by Jaime on 16/10/2017.
 */
public class Resultado {


    String status;
    String mensaje;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


    @Override
    public String toString() {
        return "Result{" +
                "status='" + status + '\'' +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }


}
