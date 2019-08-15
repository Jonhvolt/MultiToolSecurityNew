package sample.connection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.beans.property.SimpleStringProperty;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import sample.beans.Client;
import sample.beans.Debetors;
import sample.beans.Note;
import sample.beans.ClientIntegrator;
import sample.beans.DebetorIntegrator;
import sample.beans.SimpleStringDeserializer;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ConnectToWEB implements Serializable {
    private String url;
    Type itemsListType;
    List listItemsDes;

    public List getConnectToWEB(String typeResponse) {

        if (typeResponse.equals("clients")) {
            url = "http://127.0.0.1:8080/allClients";
            itemsListType = new TypeToken<List<Client>>() {
            }.getType();
            listItemsDes = new ArrayList<Client>();
        }

        if (typeResponse.equals("debetors")) {
            url = "http://127.0.0.1:8080/allDebetors";
            itemsListType = new TypeToken<List<Debetors>>() {
            }.getType();
            listItemsDes = new ArrayList<Debetors>();
        }

        HttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost request = new HttpPost(url);
            HttpResponse response = httpClient.execute(request);
            HttpEntity httpEntity = response.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpEntity.getContent()));

            StringBuilder stringBuilder = new StringBuilder();
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                stringBuilder.append(currentLine);
            }

            reader.close();
            String strResp = stringBuilder.toString();

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(SimpleStringProperty.class, new SimpleStringDeserializer());
            Gson gson = gsonBuilder.create();

            listItemsDes = gson.fromJson(strResp, itemsListType);
        }  catch (IOException e) {
            System.out.println(e);
        }
        return listItemsDes;
    }

    public void deleteConnectToWEB(Object object) {
        ContentType contentType = ContentType.create("application/json", Charset.forName("UTF-8"));
        StringEntity postingString = null;
        if (object instanceof Client) {
            url = "http://127.0.0.1:8080/deleteClient";
            ClientIntegrator clientIntegrator = new ClientIntegrator((Client) object);
            postingString = new StringEntity(new Gson().toJson(clientIntegrator), contentType);
        }

        if (object instanceof Debetors) {
           url = "http://127.0.0.1:8080/deleteDebetor";
           DebetorIntegrator debetorIntegrator = new DebetorIntegrator((Debetors) object);
           postingString = new StringEntity(new Gson().toJson(debetorIntegrator), contentType);
        }

        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost     post          = new HttpPost(url);
            post.setEntity(postingString);
            httpClient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void saveConnectToWEB(Object object) {
        ContentType contentType = ContentType.create("application/json", Charset.forName("UTF-8"));
        StringEntity postingString = null;

        if (object instanceof Client) {
            url = "http://127.0.0.1:8080/saveClient";
            ClientIntegrator clientIntegrator = new ClientIntegrator((Client) object);
            postingString = new StringEntity(new Gson().toJson(clientIntegrator), contentType);
        }

        if (object instanceof Debetors) {
            url = "http://127.0.0.1:8080/saveDebetor";
            DebetorIntegrator debetorIntegrator = new DebetorIntegrator((Debetors) object);
            postingString = new StringEntity(new Gson().toJson(debetorIntegrator), contentType);
        }

        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost post = new HttpPost(url);
            post.setEntity(postingString);
            httpClient.execute(post);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List getNotesToWEB() {
        url = "http://127.0.0.1:8080/allNotesToday";
        itemsListType = new TypeToken<List<Note>>() {
        }.getType();
        listItemsDes = new ArrayList<Note>();

        HttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost request = new HttpPost(url);
            HttpResponse response = httpClient.execute(request);
            HttpEntity httpEntity = response.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpEntity.getContent()));

            StringBuilder stringBuilder = new StringBuilder();
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                stringBuilder.append(currentLine);
            }

            reader.close();
            String strResp = stringBuilder.toString();
            listItemsDes = new Gson().fromJson(strResp, itemsListType);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }


        return listItemsDes;
        }

    public void deleteNoteToWEB(Note note) {
        url = "http://127.0.0.1:8080/deleteNote";
        ContentType contentType = ContentType.create("application/json", Charset.forName("UTF-8"));
        StringEntity postingString = new StringEntity(new Gson().toJson(note), contentType);

        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost     post          = new HttpPost(url);
            post.setEntity(postingString);
            httpClient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void saveNoteToWEB(Note note) {
        url = "http://127.0.0.1:8080/saveNote";
        ContentType contentType = ContentType.create("application/json", Charset.forName("UTF-8"));
        StringEntity postingString = new StringEntity(new Gson().toJson(note), contentType);

        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost     post          = new HttpPost(url);
            post.setEntity(postingString);
            httpClient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
