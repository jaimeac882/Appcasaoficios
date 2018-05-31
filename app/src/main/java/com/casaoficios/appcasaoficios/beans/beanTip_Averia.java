package com.casaoficios.appcasaoficios.beans;

/**
 * Created by Jaime on 28/09/2017.
 */
public class beanTip_Averia {

    public String FEC_REGISTRO;
    public String FEC_MODIFICACION;
    public String        DES_TIPO_AVERIA;
    public int COD_USUARIO_REGISTRO;
    public int  COD_TIPAVERIA;

    public String getFEC_REGISTRO() {
        return FEC_REGISTRO;
    }

    public void setFEC_REGISTRO(String FEC_REGISTRO) {
        this.FEC_REGISTRO = FEC_REGISTRO;
    }

    public String getDES_TIPO_AVERIA() {
        return DES_TIPO_AVERIA;
    }

    public void setDES_TIPO_AVERIA(String DES_TIPO_AVERIA) {
        this.DES_TIPO_AVERIA = DES_TIPO_AVERIA;
    }

    public String getFEC_MODIFICACION() {
        return FEC_MODIFICACION;
    }

    public void setFEC_MODIFICACION(String FEC_MODIFICACION) {
        this.FEC_MODIFICACION = FEC_MODIFICACION;
    }

    public int getCOD_USUARIO_REGISTRO() {
        return COD_USUARIO_REGISTRO;
    }

    public void setCOD_USUARIO_REGISTRO(int COD_USUARIO_REGISTRO) {
        this.COD_USUARIO_REGISTRO = COD_USUARIO_REGISTRO;
    }

    public int getCOD_TIPAVERIA() {
        return COD_TIPAVERIA;
    }

    public void setCOD_TIPAVERIA(int COD_TIPAVERIA) {
        this.COD_TIPAVERIA = COD_TIPAVERIA;
    }
}
