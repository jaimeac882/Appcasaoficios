package com.casaoficios.appcasaoficios.beans;

/**
 * Created by Jaime on 26/09/2017.
 */
public class beanTipos {

    public  int COD_TIPO_MAESTRO;
    public  int  COD_USUARIO_REGISTRO;
    public  String  DES_TIPO_MAESTRO;
    public  String   FEC_MODIFICACION;
    public  String FEC_REGISTRO;

    public int getCOD_TIPO_MAESTRO() {
        return COD_TIPO_MAESTRO;
    }

    public void setCOD_TIPO_MAESTRO(int COD_TIPO_MAESTRO) {
        this.COD_TIPO_MAESTRO = COD_TIPO_MAESTRO;
    }

    public int getCOD_USUARIO_REGISTRO() {
        return COD_USUARIO_REGISTRO;
    }

    public void setCOD_USUARIO_REGISTRO(int COD_USUARIO_REGISTRO) {
        this.COD_USUARIO_REGISTRO = COD_USUARIO_REGISTRO;
    }

    public String getDES_TIPO_MAESTRO() {
        return DES_TIPO_MAESTRO;
    }

    public void setDES_TIPO_MAESTRO(String DES_TIPO_MAESTRO) {
        this.DES_TIPO_MAESTRO = DES_TIPO_MAESTRO;
    }

    public String getFEC_MODIFICACION() {
        return FEC_MODIFICACION;
    }

    public void setFEC_MODIFICACION(String FEC_MODIFICACION) {
        this.FEC_MODIFICACION = FEC_MODIFICACION;
    }

    public String getFEC_REGISTRO() {
        return FEC_REGISTRO;
    }

    public void setFEC_REGISTRO(String FEC_REGISTRO) {
        this.FEC_REGISTRO = FEC_REGISTRO;
    }
}
