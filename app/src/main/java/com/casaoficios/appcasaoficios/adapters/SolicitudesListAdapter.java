package com.casaoficios.appcasaoficios.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.casaoficios.appcasaoficios.R;
import com.casaoficios.appcasaoficios.beans.beanSolicituddetrabajo;

import java.util.ArrayList;

/**
 * Created by Jaime on 7/11/2017.
 */

public class SolicitudesListAdapter extends ArrayAdapter<beanSolicituddetrabajo> {

    Context vrcontext;
    ArrayList<beanSolicituddetrabajo> vrssoli;

    public SolicitudesListAdapter(Context context, int resource,
                               ArrayList<beanSolicituddetrabajo> listsoli) {



        super(context, resource, listsoli);
        // TODO Auto-generated constructor stub
        // referenciamos parametros del constructor hacia
        // variables globales del adapter
        // parap

        this.vrcontext = context;
        this.vrssoli = listsoli;



    }


    static class ViewHolder{

        public TextView tcodsoli;
        public TextView tdes_soli;
        public TextView tfec_soli;
        public TextView tdes_estado;

    }




    @Override
    public View getView(int position, View vistaActual, ViewGroup parent) {
        // TODO Auto-generated method stub

        if(vistaActual==null){
            vistaActual = LayoutInflater
                    .from(vrcontext)
                    .inflate(R.layout.item_list_soli,parent,false);


            ViewHolder viewHolder = new ViewHolder();

            viewHolder.tcodsoli = (TextView)vistaActual
                    .findViewById(R.id.codsoli);

            viewHolder.tdes_soli = (TextView)vistaActual
                    .findViewById(R.id.des_soli);

            viewHolder.tfec_soli = (TextView)vistaActual
                    .findViewById(R.id.fec_soli);


            viewHolder.tdes_estado = (TextView)vistaActual
                    .findViewById(R.id.des_estado);
            // guardamos la vista estatica en el .tag
            // para referenciarlo en memoria
            vistaActual.setTag(viewHolder);




        }



        // creamos una instancia del viewholder
        //en base al .tag de vistaactual

        ViewHolder holder = (ViewHolder)vistaActual.getTag();


        holder.tcodsoli
                .setText("Codigo de Averia : "+String.valueOf(vrssoli.get(position).getCOD_SOLICITUD()));

        holder.tdes_soli
                .setText(vrssoli.get(position).getDESCRIPCION());

        holder.tfec_soli
                .setText("Fecha : "+vrssoli.get(position).getFEC_REGISTRO());

        holder.tdes_estado
                .setText("Estado de Averia : "+vrssoli.get(position).getDES_ESTADO());






        return  vistaActual;
    }





}
