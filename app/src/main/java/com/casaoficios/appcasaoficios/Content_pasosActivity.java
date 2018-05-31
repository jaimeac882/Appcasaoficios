package com.casaoficios.appcasaoficios;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.casaoficios.appcasaoficios.beans.beanUsuario;

/**
 * Created by Jaime on 19/10/2017.
 */
public class Content_pasosActivity extends AppCompatActivity implements View.OnClickListener{


    Button btn_con;

    beanUsuario beanus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_pasos);


        btn_con = (Button)findViewById(R.id.boton_conr);

        btn_con.setOnClickListener(this);
        setTitle("Sigue estos pasos");



    }

    @Override
    protected void onResume() {
        super.onResume();


        Bundle bund = getIntent().getExtras();


        beanus = bund.getParcelable("beanusuario");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.boton_conr:
                Intent intent = new Intent(Content_pasosActivity.this,Registro_UbicacionActivity.class);

                intent.putExtra("beanusuario", beanus);
                startActivity(intent);
                break;

        }
    }
}
