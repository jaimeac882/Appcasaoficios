package com.casaoficios.appcasaoficios.adapters;
import android.app.Activity;
import android.app.admin.SystemUpdatePolicy;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
        import android.widget.TextView;

import com.casaoficios.appcasaoficios.R;
import com.casaoficios.appcasaoficios.beans.beanImagen;

import java.util.ArrayList;

/**
 * Created by Cesar on 12/10/2015.
 */
public class ImageListAdapter extends ArrayAdapter<beanImagen> {

    Activity context;

    ArrayList<beanImagen> vrslista;
    TextView txtNombre;
    TextView txtcode;

    ImageView limgicon;
    public ImageListAdapter(Activity contet, ArrayList<beanImagen> vsr) {

      super(contet, R.layout.fila_lista,vsr);
        // TODO Auto-generated constructor stub
        // referenciamos parametros del constructor hacia
        // variables globales del adapter
        // parap

        this.vrslista=vsr;
        this.context=contet;
    }


    public void onClick(View view)
    {


//        view

    }


    public View getView(int posicion, View view, ViewGroup parent){


        View rowView = view;

//        LayoutInflater inflater=context.getLayoutInflater();

        if (rowView == null) {
//            rowView=inflater.inflate(R.layout.fila_lista,null,true);
            rowView=
            LayoutInflater
                    .from(context)
                    .inflate(R.layout.fila_lista,parent,false);
        }


         txtNombre = (TextView) rowView.findViewById(R.id.txtnombre);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img_lx);
        TextView txtRuta = (TextView) rowView.findViewById(R.id.txtruta);
         limgicon = (ImageView) rowView.findViewById(R.id.imgicon);


        final int posicionto = posicion;

//        Bitmap bmImg = BitmapFactory.decodeFile(vrslista.get(posicion).getPath_ruta());
        imageView.setImageURI(vrslista.get(posicion).getStruri());
        txtRuta.setText(vrslista.get(posicion).getCodigo());
//        imageView.setImageResource(integers[posicion]);
        txtNombre.setText("Nombre: "+vrslista.get(posicion).getCodigo());

//
        limgicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                txtcode = (TextView) v.findViewById(R.id.txtnombre);
                vrslista.remove(posicionto);
                notifyDataSetChanged();
                System.out.println("LADATA:::::::" + posicionto);
            }
        });



        return rowView;
    }



}