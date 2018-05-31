package com.casaoficios.appcasaoficios.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RESTClient {





  private static String convertStreamToString(InputStream is) {

    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    StringBuilder sb = new StringBuilder();

    String line = null;
    try {
      while ((line = reader.readLine()) != null) {
        sb.append(line + "\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return sb.toString();
  }


  public static String connectAndReturnResponse(String strurl) {


    StringBuilder result = new StringBuilder();

    URL urls = null;


    try {
      urls = new URL(strurl);


      HttpURLConnection urlConnection = (HttpURLConnection)urls.openConnection() ;
      InputStream in = new BufferedInputStream(urlConnection.getInputStream());

      BufferedReader reader = new BufferedReader(new InputStreamReader(in));

      String line;
      while ((line = reader.readLine()) != null) {
        result.append(line);
      }


    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }



    return result.toString();


  }


}
