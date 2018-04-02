package com.example.gabrielgmacedo.buscacepapp;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by gabriel.gmacedo on 02/04/2018.
 */

public class HttpService extends AsyncTask<Void, Void, CEP> {

    private final String cep;

    public HttpService(String cep) {
        this.cep = cep;
    }

    @Override
    protected CEP doInBackground(Void... voids) {
        StringBuilder resposta = new StringBuilder();


        if (this.cep != null && this.cep.length() == 8) {

            try {
                URL url = new URL("http://matheuscastiglioni.com.br/ws/cep/find/" + this.cep + "/json");
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()){
                    resposta.append(scanner.next());
                }
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        return new Gson().fromJson(resposta.toString(), CEP.class);
    }
}
