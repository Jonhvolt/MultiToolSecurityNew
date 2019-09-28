package sample.service.serviceImpl;

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
import sample.beans.*;
import sample.service.DebetorService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DebetorServiceImpl implements DebetorService {
    private String url;
    Type itemsListType;

    @Override
    public List<Debetors> getDebetor() {
        url = "http://127.0.0.1:8080/allDebetors";
        itemsListType = new TypeToken<List<Debetors>>() {}.getType();
        List<Debetors> listDebetors = new ArrayList<>();

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

            listDebetors = gson.fromJson(strResp, itemsListType);
        }  catch (IOException e) {
            System.out.println(e);
        }
        return listDebetors;
    }

    @Override
    public void saveDebetor(Debetors debetor) {
        ContentType contentType = ContentType.create("application/json", Charset.forName("UTF-8"));
        url = "http://127.0.0.1:8080/saveDebetor";
        DebetorIntegrator debetorIntegrator = new DebetorIntegrator(debetor);
        StringEntity postingString = new StringEntity(new Gson().toJson(debetorIntegrator), contentType);

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

    @Override
    public void deleteDebetor(Debetors debetor) {
        ContentType contentType = ContentType.create("application/json", Charset.forName("UTF-8"));
        url = "http://127.0.0.1:8080/deleteDebetor";
        DebetorIntegrator debetorIntegrator = new DebetorIntegrator(debetor);
        StringEntity postingString = new StringEntity(new Gson().toJson(debetorIntegrator), contentType);

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
