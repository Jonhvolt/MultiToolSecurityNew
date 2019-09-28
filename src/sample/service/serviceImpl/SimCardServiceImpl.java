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
import sample.beans.SimCard;
import sample.beans.SimCardIntegrator;
import sample.beans.SimpleStringDeserializer;
import sample.service.SimCardService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class SimCardServiceImpl implements SimCardService {
    private String url;
    Type itemsListType;

    @Override
    public List<SimCard> getSimCard() {
        url = "http://127.0.0.1:8080/allSimCard";
        itemsListType = new TypeToken<List<SimCard>>() {}.getType();
        List<SimCard> listSimCard = new ArrayList<>();

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

            listSimCard = gson.fromJson(strResp, itemsListType);
        }  catch (IOException e) {
            System.out.println(e);
        }
        return listSimCard;
    }

    @Override
    public void saveSimCard(SimCard simCard) {
        ContentType contentType = ContentType.create("application/json", Charset.forName("UTF-8"));
        url = "http://127.0.0.1:8080/saveSimCard";
        SimCardIntegrator simCardIntegrator = new SimCardIntegrator(simCard);
        StringEntity postingString = new StringEntity(new Gson().toJson(simCardIntegrator), contentType);

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
    public void deleteSimCard(SimCard simCard) {
        ContentType contentType = ContentType.create("application/json", Charset.forName("UTF-8"));
        url = "http://127.0.0.1:8080/deleteSimCard";
        SimCardIntegrator simCardIntegrator = new SimCardIntegrator(simCard);
        StringEntity postingString = new StringEntity(new Gson().toJson(simCardIntegrator), contentType);

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
