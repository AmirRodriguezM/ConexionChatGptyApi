package Logica;

import Presentancion.FormularioPrincipal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConsultaApiPaises extends FormularioPrincipal {
    private String pais;
    public ConsultaApiPaises(){

        this.pais = pais;

    }
    private static final String BASE_URL = "https://restcountries.com/v3.1/name/";
    public String getPaisInfo(String pais) throws IOException {
        String encodedURL = pais.replace(" ","%20");
        URL url = new URL(BASE_URL + encodedURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }
}
