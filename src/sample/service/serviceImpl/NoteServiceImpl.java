package sample.service.serviceImpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import sample.beans.Note;
import sample.service.NoteService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class NoteServiceImpl implements NoteService {
    private String url;
    Type itemsListType;

    @Override
    public List<Note> getNote() {
        url = "http://127.0.0.1:8080/allNotesToday";
        itemsListType = new TypeToken<List<Note>>() {
        }.getType();
        List<Note> listNotes = new ArrayList<>();

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
            listNotes = new Gson().fromJson(strResp, itemsListType);
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }


        return listNotes;
    }

    @Override
    public void saveNote(Note note) {
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

    @Override
    public void deleteNote(Note note) {
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
}
