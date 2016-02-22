/**************************************************************************************
 * Projeto: FabricaNet
 * Descricao: classe responsavel pelo gerenciamento de resolucao de video e componentes
 * Autor: Rafael Carlos
 * Local : Palmas-TO
 **************************************************************************************/

//Pacote de recursos
package com.example.conversor.conexao;

//Pacotes utilizados

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * @author Rafael Carlos de Oliveira
 *         Faculdade Católica do Tocantins
 */
public class FabricaNet {


    private FabricaNet() {

    }

    public synchronized static String buscaDadosWebService(String ender) {
        String sJson;

        try {
            //Realiza a leitura dos dados no servidor via HTTP
            HttpClient vrCliente = new DefaultHttpClient();
            HttpGet vrGet = new HttpGet();

            URL vrURL = new URL(ender);
            HttpURLConnection vrConexaoHttp = (HttpURLConnection) vrURL.openConnection();
            vrConexaoHttp.setConnectTimeout(10000);
            vrGet.setURI(new URI(ender));
            HttpResponse vrResponce = vrCliente.execute(vrGet);
            HttpEntity vrEntity = vrResponce.getEntity();

            if (vrEntity != null) {
                InputStream vrInput = vrEntity.getContent();
                sJson = inputString(vrInput);
                vrInput.close();
                return sJson;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    //Metodo utilizado para o agrupamehto dos bytes vindos da operacao http
    private static String inputString(InputStream is) throws IOException {
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes)) > 0) {
            baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity == null) {
                return false;
            } else {
                NetworkInfo[] info = connectivity.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        } catch (SecurityException e) {
            Toast.makeText(context, "Por favor, verifique sua conexão de internet!", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected())
            return true;
        else
            return false;
    }
}