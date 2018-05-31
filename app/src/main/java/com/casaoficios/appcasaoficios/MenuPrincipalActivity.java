package com.casaoficios.appcasaoficios;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.casaoficios.appcasaoficios.beans.beanUsuario;
import com.casaoficios.appcasaoficios.fragmentscos.fragment_listasolicitud;
import com.casaoficios.appcasaoficios.util.Constans;
import com.casaoficios.appcasaoficios.util.SessionPrefs;

/**
 * Created by Jaime on 7/09/2017.
 */
public class MenuPrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , View.OnClickListener ,fragment_listasolicitud.OnFragmentInteractionListener{

    LinearLayout layout;

    TextView ltxt2;
    TextView ltxt1;

    TextView mtxtulltimaaveria;

    beanUsuario beanusu;

    LinearLayout mlinearlayoutcontent;
//    NavigationView navigationView;

    Fragment fragment = null;
    boolean fragmenttransaction = false;


    Button btnaceptar;

    NavigationView navigationView;


    @Override
    protected void onResume() {
        super.onResume();





    }

    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();




        if (id == R.id.nav_averia) {
            fragment = new fragment_listasolicitud();
            fragmenttransaction = true;

            if (fragmenttransaction){
                ocultarelementoslinear();
                getSupportFragmentManager().beginTransaction().replace(R.id.content_main,fragment)
                        .commit();

                item.setCheckable(true);
                getSupportActionBar().setTitle("Mis Averias Reportadas");


            }


        }else if(id == R.id.nav_home) {

            if (fragment != null){
                getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                mostrarelementoslinear();
                item.setCheckable(true);
                getSupportActionBar().setTitle("Casa Oficios");
            }



        }else if (id == R.id.nav_salir) {
            Intent intent = new Intent(MenuPrincipalActivity.this,InicialActivity.class);
//Open next activity
            startActivity(intent);
//Close this activity
            System.exit(0);
        }








        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;



    }
    public void ocultarelementoslinear(){

        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            child.setVisibility(View.INVISIBLE);
        }
    }


    public void mostrarelementoslinear(){

        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            child.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_principal);


        Bundle bund = getIntent().getExtras();


        beanusu = bund.getParcelable("beanusuario");

        Constans.cod_usu = beanusu.getCOD_USUARIO();


        layout = (LinearLayout) findViewById(R.id.linearlayoutcontent);
        btnaceptar = (Button) findViewById(R.id.boton_aceptar);


        btnaceptar.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //Recien puedes recuperar elementos de nav
        View header = navigationView.getHeaderView(0);
        ltxt1 = (TextView)header.findViewById(R.id.txt1);
        ltxt2 = (TextView)header.findViewById(R.id.txt2);



        mtxtulltimaaveria = (TextView) findViewById(R.id.txtulltimaaveria) ;



        if (Constans.lastcode != "0"){

            mtxtulltimaaveria.setText("El ultimo codigo de averia generado es : " + Constans.lastcode);
        }


        ltxt1.setText(beanusu.getDES_USUARIO());
        ltxt2.setText(beanusu.getLOG_USUARIO());

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        /*EL MENU DE LAS OPCIONES DE LOS TRES PUNTOS*/

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    public boolean onCreateOptionsMenu(Menu menu) {

        /*EL MENU DE LOS TRES PUNTOS*/

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.boton_aceptar:
                Intent intent = new Intent(MenuPrincipalActivity.this,Content_pasosActivity.class);
                intent.putExtra("beanusuario", beanusu);
                startActivity(intent);
                break;


        }

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
