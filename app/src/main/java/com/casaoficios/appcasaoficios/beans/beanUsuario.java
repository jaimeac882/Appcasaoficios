package com.casaoficios.appcasaoficios.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jaime on 17/08/2017.
 */
public class beanUsuario implements Parcelable {

    public  int COD_USUARIO	;
    public  String DES_USUARIO;
    public  String LOG_USUARIO;
    public  String PASS_USUARIO;
    public  String ESTADO;
    public  String  COD_TIPO_USUARIO;
    public String FEC_REGISTRO;
    public  String FEC_MODIFICACION;
    public  int COD_USUARIO_REGISTRO;


    public int getCOD_USUARIO() {
        return COD_USUARIO;
    }

    public void setCOD_USUARIO(int COD_USUARIO) {
        this.COD_USUARIO = COD_USUARIO;
    }

    public String getDES_USUARIO() {
        return DES_USUARIO;
    }

    public void setDES_USUARIO(String DES_USUARIO) {
        this.DES_USUARIO = DES_USUARIO;
    }

    public String getLOG_USUARIO() {
        return LOG_USUARIO;
    }

    public void setLOG_USUARIO(String LOG_USUARIO) {
        this.LOG_USUARIO = LOG_USUARIO;
    }

    public String getPASS_USUARIO() {
        return PASS_USUARIO;
    }

    public void setPASS_USUARIO(String PASS_USUARIO) {
        this.PASS_USUARIO = PASS_USUARIO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getCOD_TIPO_USUARIO() {
        return COD_TIPO_USUARIO;
    }

    public void setCOD_TIPO_USUARIO(String COD_TIPO_USUARIO) {
        this.COD_TIPO_USUARIO = COD_TIPO_USUARIO;
    }

    public String getFEC_REGISTRO() {
        return FEC_REGISTRO;
    }

    public void setFEC_REGISTRO(String FEC_REGISTRO) {
        this.FEC_REGISTRO = FEC_REGISTRO;
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

    public beanUsuario(){

    }

    private beanUsuario(Parcel in){





        this.COD_USUARIO = in.readInt();

        this.DES_USUARIO = in.readString();
        this.LOG_USUARIO = in.readString();
        this.PASS_USUARIO = in.readString();
        this.ESTADO = in.readString();
        this.COD_TIPO_USUARIO = in.readString();
        this.FEC_REGISTRO = in.readString();
        this.FEC_MODIFICACION = in.readString();
        this.COD_USUARIO_REGISTRO = in.readInt();


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.COD_USUARIO);
        dest.writeString(this.DES_USUARIO);
        dest.writeString(this.LOG_USUARIO);
        dest.writeString(this.PASS_USUARIO);
        dest.writeString(this.ESTADO);
        dest.writeString(this.COD_TIPO_USUARIO);
        dest.writeString(this.FEC_REGISTRO);
        dest.writeString(this.FEC_MODIFICACION);
        dest.writeInt(this.COD_USUARIO_REGISTRO);
    }


    public static final Creator<beanUsuario> CREATOR = new Creator<beanUsuario>() {
        public beanUsuario createFromParcel(Parcel in) {
            return new beanUsuario(in);
        }

        public beanUsuario[] newArray(int size) {
            return new beanUsuario[size];
        }

    };

}
