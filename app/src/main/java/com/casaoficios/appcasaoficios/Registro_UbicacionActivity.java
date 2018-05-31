package com.casaoficios.appcasaoficios;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomButtonsController;


import com.casaoficios.appcasaoficios.interfaces.RegistroSolicitudTrabajoClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.*;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.casaoficios.appcasaoficios.beans.beanCliente;
import com.casaoficios.appcasaoficios.beans.beanSolicituddetrabajo;
import com.casaoficios.appcasaoficios.beans.beanUsuario;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jaime on 4/10/2017.
 */
public class Registro_UbicacionActivity extends AppCompatActivity implements View.OnClickListener ,PlaceSelectionListener,OnMapReadyCallback,
                                          GoogleMap.OnCameraMoveListener,GoogleMap.OnCameraMoveCanceledListener,GoogleMap.OnCameraIdleListener{
    AlertDialog alert = null;
    String url = "";
    String baseUrl =  "";
    EditText etxtubicacion;
    TextView txtvresultado;
    Button btngetdireccion;
    LocationManager locationManager;
    ObtenerWebService hilodeconexion;
    Location location;
    LocationListener locationlistener;
    beanUsuario beanusuario;
    beanSolicituddetrabajo beansolici;
    beanCliente bcli ;
    RequestQueue requestQueue;
    Button lbtnsig_ubica;
    private GoogleMap mMap;

    LatLng direcc;

    private TextView mPlaceDetailsText;
    Geocoder geocoder;
    List<Address> addresses;


    private TextView mPlaceAttribution;
    public static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 99;
    public static final int MY_PERMISSIONS_ACCESS_COARSE_LOCATION = 99;
    public static final String TAG = "SampleActivityBase";
    private GPSTracker gpsTracker;
    private Location mLocation;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ubicacion_principal);

//        requestQueue = Volley.newRequestQueue(this);
//        etxtubicacion = (EditText) findViewById(R.id.ubicacion);
//        txtvresultado = (TextView) findViewById(R.id.resultado);

        setTitle("Ubicación");



                geocoder = new Geocoder(this, Locale.getDefault());




        lbtnsig_ubica = (Button)findViewById(R.id.btnsiguienteubi);
//
//        intent.putExtra("beanusuario", beanus);
//        intent.putExtra("beansolici", beanSolici);
        lbtnsig_ubica.setOnClickListener(this);

        Bundle bund = getIntent().getExtras();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapvista);
        mapFragment.getMapAsync(this);

        beanusuario = bund.getParcelable("beanusuario");

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.autocomplete_fragment);
//
//        mPlaceDetailsText = (TextView) findViewById(R.id.place_details);
//        mPlaceAttribution = (TextView) findViewById(R.id.place_attribution);


        autocompleteFragment.setOnPlaceSelectedListener(this);

//        ActivityCompat.requestPermissions((Activity) this,
//                new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
//                MY_PERMISSIONS_ACCESS_FINE_LOCATION );






//        btngetdireccion = (Button) findViewById(R.id.boton_getdireccion);

//        btngetdireccion.setOnClickListener(this);

          /*Inicio de clase que si o si va --------------------------------------------*/
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//
//
//        /****Mejora****/
        if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            AlertNoGps();
            return;
//            return;
        }
//        /********/
//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){



            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
//                        this.MY_PERMISSION_ACCESS_COURSE_LOCATION );
//                return;

                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


            } else {
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }

        }else{
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

//
//
//        Mostrarlocalizacion(location);
//
//
//        locationlistener =  new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                // Es la funciona que se ira llamando siempre y cuando reciba una actualizacion del GPS
//                // el cual hira mostrandola
//                Mostrarlocalizacion(location);
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//            // Es cuando cambia el proveedor de estado -.. es decir que un proveedor pasa de un paso activo a pasivo
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//            // Cuando el proveedor se activa
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//                // Cuando el proveedor se desactiva
//            }
//        };
//
//        //(El location , El tiempo que quiero que se actualice , el intervalo en que se actualizara , el listener)
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationlistener);
//
//
//        /*Datos de los proveedores ------------INICIO--------------------------------*/
//
//       //List<String> lisaproveedores = locationManager.getAllProviders();
//
//        /*Variable de tipo proveedor de localicacion  == todos los dispositivos tienen el networkProvider*/
//
//       // LocationProvider provedorLo = locationManager.getProvider(lisaproveedores.get(0));
//
//        /*La precision del proveedor*/
//        //int Accury = provedorLo.getAccuracy();
//
//        //este proveedor tiene altitud ?
//        //boolean TimeAltitud = provedorLo.supportsAltitude();
//      //FIN---------------------------------------
//




        /*Establezco criterios para escoger un proveedor -----------------INICIO---------------------------*/

        /*Clase criteria donde se pasa un criterio en concreto el cual se puede filtrar los proveedores*/
        //Criteria criteria = new Criteria();
       // criteria.setAccuracy(Criteria.ACCURACY_FINE);
       //Criteria = el criterio que puse , si pongo true solo este proveedor
       // String mejorproveedor = locationManager.getBestProvider(criteria,true);




        gpsTracker = new GPSTracker(getApplicationContext());
        mLocation = gpsTracker.getLocation();

        latitude = mLocation.getLatitude();
        longitude = mLocation.getLongitude();





        System.out.println("LATITUTEEE:"+latitude);
        System.out.println("LANGITUDE:"+longitude);

    }


    @Override
    protected void onPause() {
        super.onPause();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//                return;
//            } else {
//                locationManager.removeUpdates(locationlistener);
//            }
//        } else {
//            locationManager.removeUpdates(locationlistener);
//        }

    }

    private void AlertNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El sistema GPS esta desactivado, ¿Desea activarlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
//                        dialog.cancel();


                        beansolici.setCORDENADAS_REGISTRO("");
                        beansolici.setCORDENADAS_UBICACION("");
                        beansolici.setDIRECCION("");

                        intent.putExtra("beanusuario", beanusuario);
                        intent.putExtra("beansolici", beansolici);

                        intent.putExtra("opcion", "NO");
                        startActivity(intent);

                    }
                });
        alert = builder.create();
        alert.show();
    }



    @Override
    protected void onResume() {
        super.onResume();

    }







    // Que pasa cuando mi programa pasa a pausa se debe parar el consumo

//    protected void onPause() {
//        super.onPause();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//
//
//
//            if (checkSelfPermission( Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission( Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//
//                return;
//            }else{
//                locationManager.removeUpdates(locationlistener);
//            }
//
//        }else{
//            locationManager.removeUpdates(locationlistener);
//        }
//
//    }

//    public void Mostrarlocalizacion(Location lo) {
//
//       if (lo != null){
//
////            hilodeconexion = new ObtenerWebService();
//            //Primero  longitud y luego latitud
////            hilodeconexion.execute(String.valueOf( location.getLatitude()),
////                    String.valueOf(location.getLongitude())
////            );   // Parámetros que recibe doInBackground
//
//
//
//           System.out.println("Latitude"+String.valueOf( location.getLatitude()));
//           System.out.println("Longitude"+String.valueOf( location.getLongitude()));
//
//
//
//        }
//
//
//
//    }

//


    Intent intent;

    public void onClick(View v) {

        switch (v.getId()){

//            case  R.id.boton_getdireccion:
                //Cuando damos en el evento click del view .. o cualquier controlador que tenga el evento Onclick


//                break;
            case  R.id.btnsiguienteubi:
                //Cuando damos en el evento click del view .. o cualquier controlador que tenga el evento Onclick
                intent = new Intent(Registro_UbicacionActivity.this,Registro_UbicacionDirActivity.class);


                beansolici = new beanSolicituddetrabajo();
//
//                beanSolici.setCOD_CLIENTE(bcli.getCOD_CLIENTE());
//                beanSolici.setNOMBRE(txtnombre.getText().toString() + "" + txtApe.getText().toString());
//                beanSolici.setNOMBRE(beanus.getLOG_USUARIO());
//                beanSolici.setTELEFONO(Telf.getText().toString());

                String address="";

                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(this, Locale.getDefault());
                try {
                    addresses = geocoder.getFromLocation(direcc.latitude, direcc.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                     address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//                    String city = addresses.get(0).getLocality();
//                    String state = addresses.get(0).getAdminArea();
//                    String country = addresses.get(0).getCountryName();
//                    String postalCode = addresses.get(0).getPostalCode();
//                    String knownName = addresses.get(0).getFeatureName();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                String corregistro = "cordenasdireccion=(latitude="+String.valueOf(direcc.latitude)+",longitude="+String.valueOf(direcc.longitude)+")";
                String corubica = "dedondeseregistro=(latitude="+String.valueOf(direcc.latitude)+",longitude="+String.valueOf(direcc.longitude)+")";

                beansolici.setCORDENADAS_REGISTRO(corregistro);
                beansolici.setCORDENADAS_UBICACION(corubica);
                beansolici.setDIRECCION(address);

                intent.putExtra("beanusuario", beanusuario);
                intent.putExtra("beansolici", beansolici);

                metodo();


                break;
            default:
                break;


        }
    }

    @Override
    public void onPlaceSelected(Place place) {
        Log.i(TAG, "Place Selected: " + place.getName());

        // Format the returned place's details and display them in the TextView.
//        mPlaceDetailsText.setText(formatPlaceDetails(getResources(), place.getName(), place.getId(),
//                place.getAddress(), place.getPhoneNumber(), place.getWebsiteUri()));

//        CharSequence attributions = place.getAttributions();
//        if (!TextUtils.isEmpty(attributions)) {
////            mPlaceAttribution.setText(Html.fromHtml(attributions.toString()));
//        } else {
//            mPlaceAttribution.setText("");
//        }


        direcc = place.getLatLng();
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(direcc).title(place.getName().toString()).draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(direcc,20));


    }

    @Override
    public void onError(Status status) {
        Log.e(TAG, "onError: Status = " + status.toString());

        Toast.makeText(this, "Place selection failed: " + status.getStatusMessage(),
                Toast.LENGTH_SHORT).show();
    }

    private static Spanned formatPlaceDetails(Resources res, CharSequence name, String id,
                                              CharSequence address, CharSequence phoneNumber, Uri websiteUri) {
        Log.e(TAG, res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));
        return Html.fromHtml(res.getString(R.string.place_details, name, id, address, phoneNumber,
                websiteUri));

    }


    public float zoomca = 20;

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
        direcc = new LatLng(latitude, longitude);



//        m.position(direcc);
//        m.title("Dirección");

//        m.draggable(true);

//
//        Geocoder geocoder;
//        List<Address> addresses;
//        geocoder = new Geocoder(this, Locale.getDefault());
//
//        System.out.println("MOVIL");

        String direccst = "";

        try {
//            addresses.clear();
            addresses = geocoder.getFromLocation(direcc.latitude, direcc.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            direccst = addresses.get(0).getAddressLine(0);
//            System.out.println("ADRESS:" + addresses.get(0).getLocality());
//            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//                    String city = addresses.get(0).getLocality();
//                    String state = addresses.get(0).getAdminArea();
//                    String country = addresses.get(0).getCountryName();
//                    String postalCode = addresses.get(0).getPostalCode();
//                    String knownName = addresses.get(0).getFeatureName();
        } catch (IOException e) {
            e.printStackTrace();
        }


        MarkerOptions m = new MarkerOptions().position(direcc).title("Dirección").snippet(direccst);
        Marker sourceMarker = mMap.addMarker(m);

        mMap.addMarker(m);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(direcc,20));


        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);

//        mMap.setMinZoomPreference(6.0f);
//        mMap.setMaxZoomPreference(14.0f);
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);

        System.out.println("Ingreso metodo cons");


//        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
//
//            @Override
//            public View getInfoWindow(Marker arg0) {
//                return null;
//            }
//
//            @Override
//            public View getInfoContents(Marker marker) {
//
//                Context context = getApplicationContext(); //or getActivity(), YourActivity.this, etc.
//
//                LinearLayout info = new LinearLayout(context);
//                info.setOrientation(LinearLayout.VERTICAL);
//
//                TextView title = new TextView(context);
//                title.setTextColor(Color.BLACK);
//                title.setGravity(Gravity.CENTER);
//                title.setTypeface(null, Typeface.BOLD);
//                title.setText(marker.getTitle());
//
//                TextView snippet = new TextView(context);
//                snippet.setTextColor(Color.GRAY);
//                snippet.setText(marker.getSnippet());
//
//                info.addView(title);
//                info.addView(snippet);
//
//                return info;
//            }
//        });

        sourceMarker.showInfoWindow();
//
//        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
//            @Override
//            public void onMapLongClick(LatLng latLng) {
//                mMap.addMarker(new MarkerOptions()
////                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.avion))
//                        .anchor(0.0f, 1.0f)
//                        .position(latLng));
//
//            }
//        });

//        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//            @Override
//            public boolean onMarkerClick(Marker marker) {
//                bomove = false;
//                movem = "CLICK";
//                System.out.println("onMarkerClick:::"+bomove);
//
////                Toast.makeText(getApplicationContext(),"Has pulsado una marca", Toast.LENGTH_LONG).show();
//                return false;
//            }
//        });




//        mMap.setOnMarkerDragListener(this);
        mMap.setOnCameraMoveListener(this);
//        mMap.setOnCameraMoveCanceledListener(this);

        mMap.setOnCameraIdleListener(this);


    }




//    @Override
    Boolean bomove = false;
    String movem = "CLICK";


    Boolean acti = false;

    public void onCameraMove() {


//        float zoom = mMap.getCameraPosition().zoom;
//
//

//            bomove = true;
    CameraPosition position = mMap.getCameraPosition();
//        direcc = position.target;
if (zoomca == position.zoom){


            direcc = position.target;


            mMap.clear();
            mMap.addMarker(new MarkerOptions()
                    .position(direcc)
                    .title("Dirección"));

//    mMap.setOnMarkerClickListener(mMap.);

    movem = "";


}

        System.out.println("onCameraMove::"+bomove);
    }

    @Override
    public void onCameraMoveCanceled() {
        System.out.println("Stop");
        Toast.makeText(getBaseContext(),"Termino de Moverse" ,Toast.LENGTH_SHORT).show();
    }




    @Override
    public void onCameraIdle() {


//        if (bomove == true && movem != "CLICK"){
//
//        Log.d(TAG, "onCameraIdle: ");
        CameraPosition position = mMap.getCameraPosition();
            if (movem!= "CLICK"){

//
//
//                        //
                                String direccst = "";
//
//                                geocoder = new Geocoder(this, Locale.getDefault());
//
                                direcc = position.target;
                                try {
                        //            addresses.clear();
                                    addresses = geocoder.getFromLocation(direcc.latitude,direcc.longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                                    direccst = addresses.get(0).getAddressLine(0);
                        //            System.out.println("ADRESS:" + addresses.get(0).getLocality());
                        //            address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                        //                    String city = addresses.get(0).getLocality();
                        //                    String state = addresses.get(0).getAdminArea();
                        //                    String country = addresses.get(0).getCountryName();
                        //                    String postalCode = addresses.get(0).getPostalCode();
                        //                    String knownName = addresses.get(0).getFeatureName();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                MarkerOptions m = new MarkerOptions()
                        .position( direcc )
                        .title("Dirección").snippet(direccst);

                                mMap.clear();
                                mMap.addMarker(m);

                Marker sourceMarker = mMap.addMarker(m);
                // Este metodo hace que se vea lo que pusiste en la marca
                sourceMarker.showInfoWindow();




            }else{
//                movem = "";
            }
        zoomca = position.zoom;
        System.out.println("onCameraIdle:::"+bomove);
        System.out.println("MOVIMIENTO:::"+movem);

    }

//    @Override
//    public void onVisibilityChanged(boolean visible) {
//
//    }
//
//    @Override
//    public void onZoom(boolean zoomIn) {
//
//        System.out.println("ZOOM"+zoomIn);
//
//    }


//    @Override
//    public void onCameraChange(CameraPosition cameraPosition) {
//        mMap.clear();
//        mMap.addMarker( new MarkerOptions()
//                .position( cameraPosition.target )
//                .title( cameraPosition.toString() )
//        );
//    }


    public class ObtenerWebService extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {

            String cadena = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";

            //http://maps.googleapis.com/maps/api/geocode/json?latlng=38.404593,-0.529534&sensor=false
            cadena = cadena + params[0];
            cadena = cadena + ",";
            cadena = cadena + params[1];
            cadena = cadena + "&sensor=false";


            String devuelve = "";

            URL url = null; // Url de donde queremos obtener información
            try {
                url = new URL(cadena);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //Abrir la conexión
                connection.setRequestProperty("User-Agent", "Mozilla/5.0" +
                        " (Linux; Android 1.5; es-ES) Ejemplo HTTP");
                //connection.setHeader("content-type", "application/json");

                int respuesta = connection.getResponseCode();
                StringBuilder result = new StringBuilder();

                if (respuesta == HttpURLConnection.HTTP_OK){


                    InputStream in = new BufferedInputStream(connection.getInputStream());  // preparo la cadena de entrada

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));  // la introduzco en un BufferedReader

                    // El siguiente proceso lo hago porque el JSONOBject necesita un String y tengo
                    // que tranformar el BufferedReader a String. Esto lo hago a traves de un
                    // StringBuilder.

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);        // Paso toda la entrada al StringBuilder
                    }

                    //Creamos un objeto JSONObject para poder acceder a los atributos (campos) del objeto.
                    JSONObject respuestaJSON = new JSONObject(result.toString());   //Creo un JSONObject a partir del StringBuilder pasado a cadena
                    //Accedemos al vector de resultados
                    JSONArray resultJSON = respuestaJSON.getJSONArray("results");   // results es el nombre del campo en el JSON

                    //Vamos obteniendo todos los campos que nos interesen.
                    //En este caso obtenemos la primera dirección de los resultados.
                    String direccion="SIN DATOS PARA ESA LONGITUD Y LATITUD";
                    if (resultJSON.length()>0){
                        direccion = resultJSON.getJSONObject(0).getString("formatted_address");    // dentro del results pasamos a Objeto la seccion formated_address
                    }
                    devuelve = "Dirección: " + direccion;   // variable de salida que mandaré al onPostExecute para que actualice la UI

                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return devuelve;
        }

        @Override
        protected void onCancelled(String aVoid) {
            super.onCancelled(aVoid);
        }

        @Override
        protected void onPostExecute(String aVoid) {
//            txtvresultado.setText(aVoid);
            Log.i("GPS:", aVoid);
            //super.onPostExecute(aVoid);
        }

        @Override
        protected void onPreExecute() {
//            txtvresultado.setText("");
//            super.onPreExecute();
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }


    public CharSequence[] items={"google","Apple","Kaye"};
    public boolean[] checkedItems=new boolean[items.length];



public void metodo(){
    AlertDialog.Builder alertDialog= new AlertDialog.Builder(this);
            alertDialog.setTitle("Informes");
    alertDialog.setMessage("Encontro la dirección que buscaba");
            alertDialog.setPositiveButton("SI", new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            // TODO Auto-generated method stub
//            Toast.makeText(getBaseContext(),"ok ive wrote this 'ok' here" ,Toast.LENGTH_SHORT).show();
            intent.putExtra("opcion", "SI");
            startActivity(intent);
//            dato = true;
        }
    });
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            // TODO Auto-generated method stub
//            Toast.makeText(getBaseContext(), "cancel ' comment same as ok'", Toast.LENGTH_SHORT).show();
            intent.putExtra("opcion", "NO");
            startActivity(intent);
//            dato = false;
        }
    });
//            alertDialog.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
//
//        @Override
//        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//            // TODO Auto-generated method stub
//            Toast.makeText(getBaseContext(), items[which] +(isChecked?"clicked'again i've wrrten this click'":"unchecked"),Toast.LENGTH_SHORT).show();
//
//        }
//    });
            alertDialog.show();

}

}
