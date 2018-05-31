package com.casaoficios.appcasaoficios.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.casaoficios.appcasaoficios.beans.beanUsuario;


/**
 * Manejador de preferencias de la sesión del afiliado
 */
public class SessionPrefs {

    public static final String PREFS_NAME = "SALUDMOCK_PREFS";
    public static final String PREF_COD_USUARIO = "COD_USUARIO";
    public static final String PREF_DES_USUARIO = "DES_USUARIO";

    public static final String PREF_LOG_USUARIO = "LOG_USUARIO";


    public static final String PREF_ESTADO= "ESTADO";
    public static final String PREF_COD_TIPO_USUARIO = "COD_TIPO_USUARIO";

    public static final String PREF_FEC_REGISTRO= "FEC_REGISTRO";
    public static final String PREF_FEC_MODIFICACION = "FEC_MODIFICACION";

    public static final String PREF_COD_USUARIO_REGISTRO = "COD_USUARIO_REGISTRO";








    private final SharedPreferences mPrefs;
//    private final int codigousuer = 0;
    public beanUsuario be = new beanUsuario();

    private boolean mIsLoggedIn = false;

    private static SessionPrefs INSTANCE;

    public static SessionPrefs get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new SessionPrefs(context);
        }
        return INSTANCE;
    }

    private SessionPrefs(Context context) {
        mPrefs = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, context.MODE_PRIVATE);

        mIsLoggedIn = !TextUtils.isEmpty(mPrefs.getString(PREF_COD_USUARIO, null));
//        codigousuer = Integer.parseInt(COD_USUARIO);
    }



    public boolean isLoggedIn() {
        return mIsLoggedIn;
    }




public beanUsuario obtenerusu(){
return be;
}


    public void saveAffiliate(beanUsuario affiliate) {
        if (affiliate != null) {

            SharedPreferences.Editor editor = mPrefs.edit();

            editor.putString(PREF_COD_USUARIO, String.valueOf(affiliate.getCOD_USUARIO()));
            editor.putString(PREF_DES_USUARIO, affiliate.getDES_USUARIO());

            editor.putString(PREF_LOG_USUARIO, affiliate.getLOG_USUARIO());
            editor.putString(PREF_ESTADO, affiliate.getESTADO());
            editor.putString(PREF_COD_TIPO_USUARIO, affiliate.getCOD_TIPO_USUARIO());
            editor.putString(PREF_FEC_REGISTRO, affiliate.getFEC_REGISTRO());
            editor.putString(PREF_FEC_MODIFICACION, affiliate.getFEC_MODIFICACION());
            editor.putString(PREF_COD_USUARIO_REGISTRO,String.valueOf( affiliate.getCOD_USUARIO_REGISTRO()));
            editor.apply();

            mIsLoggedIn = true;
//            codigousuer = affiliate.getCOD_USUARIO();
        }
    }

    public void logOut(){
        mIsLoggedIn = false;
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(PREF_COD_USUARIO, null);
        editor.putString(PREF_DES_USUARIO, null);
        editor.putString(PREF_LOG_USUARIO, null);
        editor.putString(PREF_ESTADO, null);
        editor.putString(PREF_COD_TIPO_USUARIO, null);
        editor.putString(PREF_FEC_REGISTRO, null);
        editor.putString(PREF_FEC_MODIFICACION, null);
        editor.putString(PREF_COD_USUARIO_REGISTRO, null);

        editor.apply();
    }
}
