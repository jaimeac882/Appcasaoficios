package com.casaoficios.appcasaoficios.beansInterface;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jaime on 28/10/2017.
 */

public class IbeanSolicituddetrabajo implements Parcelable {


    int p_cod_cliente;
    String p_cordenadas_registro   ;
    String p_cordenadas_ubicacion  ;
    int p_cod_tipo_averia  ;
    int p_cod_tipo_prioridad   ;
    String p_nombre    ;
    String p_email ;
    String p_telefono  ;
    String p_descripcion   ;
    String p_estado    ;
    double p_precio_presupuesto    ;
    double p_precio_final  ;
    int p_cod_tipo_registro    ;
    String p_fec_registro  ;
    String p_fec_modificacion  ;
    int p_cod_usuario_registro ;
    String p_cod_ubigeo    ;
    String p_direccion;
    int p_cod_oficio   ;


    public int getp_cod_cliente() {
        return p_cod_cliente;
    }

    public void setp_cod_cliente(int p_cod_cliente) {
        this.p_cod_cliente = p_cod_cliente;
    }

    public String getp_cordenadas_registro() {
        return p_cordenadas_registro;
    }

    public void setp_cordenadas_registro(String p_cordenadas_registro) {
        this.p_cordenadas_registro = p_cordenadas_registro;
    }

    public String getp_cordenadas_ubicacion() {
        return p_cordenadas_ubicacion;
    }

    public void setp_cordenadas_ubicacion(String p_cordenadas_ubicacion) {
        this.p_cordenadas_ubicacion = p_cordenadas_ubicacion;
    }

    public int getp_cod_tipo_averia() {
        return p_cod_tipo_averia;
    }

    public void setp_cod_tipo_averia(int p_cod_tipo_averia) {
        this.p_cod_tipo_averia = p_cod_tipo_averia;
    }

    public int getp_cod_tipo_prioridad() {
        return p_cod_tipo_prioridad;
    }

    public void setp_cod_tipo_prioridad(int p_cod_tipo_prioridad) {
        this.p_cod_tipo_prioridad = p_cod_tipo_prioridad;
    }

    public String getp_nombre() {
        return p_nombre;
    }

    public void setp_nombre(String p_nombre) {
        this.p_nombre = p_nombre;
    }

    public String getp_email() {
        return p_email;
    }

    public void setp_email(String p_email) {
        this.p_email = p_email;
    }

    public String getp_telefono() {
        return p_telefono;
    }

    public void setp_telefono(String p_telefono) {
        this.p_telefono = p_telefono;
    }

    public String getp_descripcion() {
        return p_descripcion;
    }

    public void setp_descripcion(String p_descripcion) {
        this.p_descripcion = p_descripcion;
    }

    public String getp_estado() {
        return p_estado;
    }

    public void setp_estado(String p_estado) {
        this.p_estado = p_estado;
    }

    public double getp_precio_presupuesto() {
        return p_precio_presupuesto;
    }

    public void setp_precio_presupuesto(double p_precio_presupuesto) {
        this.p_precio_presupuesto = p_precio_presupuesto;
    }

    public double getp_precio_final() {
        return p_precio_final;
    }

    public void setp_precio_final(double p_precio_final) {
        this.p_precio_final = p_precio_final;
    }

    public int getp_cod_tipo_registro() {
        return p_cod_tipo_registro;
    }

    public void setp_cod_tipo_registro(int p_cod_tipo_registro) {
        this.p_cod_tipo_registro = p_cod_tipo_registro;
    }

    public String getp_fec_registro() {
        return p_fec_registro;
    }

    public void setp_fec_registro(String p_fec_registro) {
        this.p_fec_registro = p_fec_registro;
    }

    public String getp_fec_modificacion() {
        return p_fec_modificacion;
    }

    public void setp_fec_modificacion(String p_fec_modificacion) {
        this.p_fec_modificacion = p_fec_modificacion;
    }

    public int getp_cod_usuario_registro() {
        return p_cod_usuario_registro;
    }

    public void setp_cod_usuario_registro(int p_cod_usuario_registro) {
        this.p_cod_usuario_registro = p_cod_usuario_registro;
    }

    public String getp_cod_ubigeo() {
        return p_cod_ubigeo;
    }

    public void setp_cod_ubigeo(String p_cod_ubigeo) {
        this.p_cod_ubigeo = p_cod_ubigeo;
    }

    public String getp_direccion() {
        return p_direccion;
    }

    public void setp_direccion(String p_direccion) {
        this.p_direccion = p_direccion;
    }

    public int getp_cod_oficio() {
        return p_cod_oficio;
    }

    public void setp_cod_oficio(int p_cod_oficio) {
        this.p_cod_oficio = p_cod_oficio;
    }


    public IbeanSolicituddetrabajo() {

    }

    protected IbeanSolicituddetrabajo(Parcel in) {
        p_cod_cliente = in.readInt();
        p_cordenadas_registro = in.readString();
        p_cordenadas_ubicacion = in.readString();
        p_cod_tipo_averia = in.readInt();
        p_cod_tipo_prioridad = in.readInt();
        p_nombre = in.readString();
        p_email = in.readString();
        p_telefono = in.readString();
        p_descripcion = in.readString();
        p_estado = in.readString();
        p_precio_presupuesto = in.readDouble();
        p_precio_final = in.readDouble();
        p_cod_tipo_registro = in.readInt();
        p_fec_registro = in.readString();
        p_fec_modificacion = in.readString();
        p_cod_usuario_registro = in.readInt();
        p_cod_ubigeo = in.readString();
        p_direccion = in.readString();
        p_cod_oficio = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(p_cod_cliente);
        dest.writeString(p_cordenadas_registro);
        dest.writeString(p_cordenadas_ubicacion);
        dest.writeInt(p_cod_tipo_averia);
        dest.writeInt(p_cod_tipo_prioridad);
        dest.writeString(p_nombre);
        dest.writeString(p_email);
        dest.writeString(p_telefono);
        dest.writeString(p_descripcion);
        dest.writeString(p_estado);
        dest.writeDouble(p_precio_presupuesto);
        dest.writeDouble(p_precio_final);
        dest.writeInt(p_cod_tipo_registro);
        dest.writeString(p_fec_registro);
        dest.writeString(p_fec_modificacion);
        dest.writeInt(p_cod_usuario_registro);
        dest.writeString(p_cod_ubigeo);
        dest.writeString(p_direccion);
        dest.writeInt(p_cod_oficio);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<IbeanSolicituddetrabajo> CREATOR = new Parcelable.Creator<IbeanSolicituddetrabajo>() {
        @Override
        public IbeanSolicituddetrabajo createFromParcel(Parcel in) {
            return new IbeanSolicituddetrabajo(in);
        }

        @Override
        public IbeanSolicituddetrabajo[] newArray(int size) {
            return new IbeanSolicituddetrabajo[size];
        }
    };
}

