package com.casaoficios.appcasaoficios.beans;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jaime on 26/10/2017.
 */

public class beanImagen  implements  Parcelable{


    String nombre;
     String path_ruta;
     String codigo;
     Uri struri;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPath_ruta() {
        return path_ruta;
    }

    public void setPath_ruta(String path_ruta) {
        this.path_ruta = path_ruta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Uri getStruri() {
        return struri;
    }

    public void setStruri(Uri struri) {
        this.struri = struri;
    }


    public beanImagen() {

    }

    public beanImagen(Parcel in) {
        nombre = in.readString();
        path_ruta = in.readString();
        codigo = in.readString();
        struri = (Uri) in.readValue(Uri.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(path_ruta);
        dest.writeString(codigo);
        dest.writeValue(struri);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<beanImagen> CREATOR = new Parcelable.Creator<beanImagen>() {
        @Override
        public beanImagen createFromParcel(Parcel in) {
            return new beanImagen(in);
        }

        @Override
        public beanImagen[] newArray(int size) {
            return new beanImagen[size];
        }
    };


}
