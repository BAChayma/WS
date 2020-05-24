package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import java.sql.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.List;

import javax.faces.application.FacesMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class webserviceTest {
    public webserviceTest() {
        super();
    }

    public static void main(String[] args) {


        try {


            URL urlAllContribuable =
                new URL("http://localhost:7101/AppTeleServiceS2V1-ViewController-context-root/resources/contribuableWs/AllContribuable");
            
            HttpURLConnection conn = (HttpURLConnection) urlAllContribuable.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            System.out.println("Connection valide .... \n" + conn);
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            System.out.println("BR valide .... \n" + br);
            String result=null;
            String msg=null;
            JSONObject jsonObject;
            String output;
            System.out.println("before while .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(" ----after while ---\n" + output);
                System.out.println(" ----affichage objet  json---");
                try {
                    jsonObject = new JSONObject(output);
                    List listItr = null;
                    JSONObject jsonItr = null;
                    result=(String)jsonObject.get("etat");
                    msg=(String)jsonObject.get("msg");
                    System.out.println("etat:  "+result);
                    System.out.println("message:  "+msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            conn.disconnect();
            System.out.println(" +++  appel WS terminé");
        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
