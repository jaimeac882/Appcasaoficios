package com.casaoficios.appcasaoficios.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jaime on 27/10/2017.
 */

public class beanSolicituddetrabajo implements Parcelable {
    int COD_SOLICITUD;
    int COD_CLIENTE;
    String CORDENADAS_REGISTRO;
    String  CORDENADAS_UBICACION;
    int COD_TIPO_AVERIA;
    int       COD_TIPO_PRIORIDAD;
    String NOMBRE;
    String     EMAIL;
    String TELEFONO;
    String        DESCRIPCION;
    int ESTADO;
    String        PRECIO_PRESUPUESTO;
    String PRECIO_FINAL;
    int        COD_TIPO_REGISTRO;
    String FEC_REGISTRO;
    String        FEC_MODIFICACION;
    String COD_USUARIO_REGISTRO;
    String       COD_UBIGEO;
    String DIRECCION;
    String DES_ESTADO;
      byte      FOTO;
    int COD_OFICIO;

    public String getDES_ESTADO() {
        return DES_ESTADO;
    }

    public void setDES_ESTADO(String DES_ESTADO) {
        this.DES_ESTADO = DES_ESTADO;
    }

    public int getCOD_SOLICITUD() {
        return COD_SOLICITUD;
    }

    public void setCOD_SOLICITUD(int COD_SOLICITUD) {
        this.COD_SOLICITUD = COD_SOLICITUD;
    }

    public int getCOD_CLIENTE() {
        return COD_CLIENTE;
    }

    public void setCOD_CLIENTE(int COD_CLIENTE) {
        this.COD_CLIENTE = COD_CLIENTE;
    }

    public String getCORDENADAS_REGISTRO() {
        return CORDENADAS_REGISTRO;
    }

    public void setCORDENADAS_REGISTRO(String CORDENADAS_REGISTRO) {
        this.CORDENADAS_REGISTRO = CORDENADAS_REGISTRO;
    }

    public String getCORDENADAS_UBICACION() {
        return CORDENADAS_UBICACION;
    }

    public void setCORDENADAS_UBICACION(String CORDENADAS_UBICACION) {
        this.CORDENADAS_UBICACION = CORDENADAS_UBICACION;
    }

    public int getCOD_TIPO_AVERIA() {
        return COD_TIPO_AVERIA;
    }

    public void setCOD_TIPO_AVERIA(int COD_TIPO_AVERIA) {
        this.COD_TIPO_AVERIA = COD_TIPO_AVERIA;
    }

    public int getCOD_TIPO_PRIORIDAD() {
        return COD_TIPO_PRIORIDAD;
    }

    public void setCOD_TIPO_PRIORIDAD(int COD_TIPO_PRIORIDAD) {
        this.COD_TIPO_PRIORIDAD = COD_TIPO_PRIORIDAD;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getESTADO() {
        return ESTADO;
    }

    public void setESTADO(int ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getPRECIO_PRESUPUESTO() {
        return PRECIO_PRESUPUESTO;
    }

    public void setPRECIO_PRESUPUESTO(String PRECIO_PRESUPUESTO) {
        this.PRECIO_PRESUPUESTO = PRECIO_PRESUPUESTO;
    }

    public String getPRECIO_FINAL() {
        return PRECIO_FINAL;
    }

    public void setPRECIO_FINAL(String PRECIO_FINAL) {
        this.PRECIO_FINAL = PRECIO_FINAL;
    }

    public int getCOD_TIPO_REGISTRO() {
        return COD_TIPO_REGISTRO;
    }

    public void setCOD_TIPO_REGISTRO(int COD_TIPO_REGISTRO) {
        this.COD_TIPO_REGISTRO = COD_TIPO_REGISTRO;
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

    public String getCOD_USUARIO_REGISTRO() {
        return COD_USUARIO_REGISTRO;
    }

    public void setCOD_USUARIO_REGISTRO(String COD_USUARIO_REGISTRO) {
        this.COD_USUARIO_REGISTRO = COD_USUARIO_REGISTRO;
    }

    public String getCOD_UBIGEO() {
        return COD_UBIGEO;
    }

    public void setCOD_UBIGEO(String COD_UBIGEO) {
        this.COD_UBIGEO = COD_UBIGEO;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public byte getFOTO() {
        return FOTO;
    }

    public void setFOTO(byte FOTO) {
        this.FOTO = FOTO;
    }

    public int getCOD_OFICIO() {
        return COD_OFICIO;
    }

    public void setCOD_OFICIO(int COD_OFICIO) {
        this.COD_OFICIO = COD_OFICIO;
    }

    public beanSolicituddetrabajo(){

    }

    public beanSolicituddetrabajo(Parcel in) {
        COD_SOLICITUD = in.readInt();
        COD_CLIENTE = in.readInt();
        CORDENADAS_REGISTRO = in.readString();
        CORDENADAS_UBICACION = in.readString();
        COD_TIPO_AVERIA = in.readInt();
        COD_TIPO_PRIORIDAD = in.readInt();
        NOMBRE = in.readString();
        EMAIL = in.readString();
        TELEFONO = in.readString();
        DESCRIPCION = in.readString();
        ESTADO = in.readInt();
        PRECIO_PRESUPUESTO = in.readString();
        PRECIO_FINAL = in.readString();
        COD_TIPO_REGISTRO = in.readInt();
        FEC_REGISTRO = in.readString();
        FEC_MODIFICACION = in.readString();
        COD_USUARIO_REGISTRO = in.readString();
        COD_UBIGEO = in.readString();
        DIRECCION = in.readString();
        FOTO = in.readByte();
        COD_OFICIO = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(COD_SOLICITUD);
        dest.writeInt(COD_CLIENTE);
        dest.writeString(CORDENADAS_REGISTRO);
        dest.writeString(CORDENADAS_UBICACION);
        dest.writeInt(COD_TIPO_AVERIA);
        dest.writeInt(COD_TIPO_PRIORIDAD);
        dest.writeString(NOMBRE);
        dest.writeString(EMAIL);
        dest.writeString(TELEFONO);
        dest.writeString(DESCRIPCION);
        dest.writeInt(ESTADO);
        dest.writeString(PRECIO_PRESUPUESTO);
        dest.writeString(PRECIO_FINAL);
        dest.writeInt(COD_TIPO_REGISTRO);
        dest.writeString(FEC_REGISTRO);
        dest.writeString(FEC_MODIFICACION);
        dest.writeString(COD_USUARIO_REGISTRO);
        dest.writeString(COD_UBIGEO);
        dest.writeString(DIRECCION);
        dest.writeByte(FOTO);
        dest.writeInt(COD_OFICIO);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<beanSolicituddetrabajo> CREATOR = new Parcelable.Creator<beanSolicituddetrabajo>() {
        @Override
        public beanSolicituddetrabajo createFromParcel(Parcel in) {
            return new beanSolicituddetrabajo(in);
        }

        @Override
        public beanSolicituddetrabajo[] newArray(int size) {
            return new beanSolicituddetrabajo[size];
        }
    };


}
