package com.bkozyrev.sbertech.mvp.model;

import com.bkozyrev.sbertech.interfaces.GetRssCallback;
import com.bkozyrev.sbertech.mvp.model.entities.Rss;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainModelImpl implements MainModel {

    private ExecutorService executorService;

    public MainModelImpl() {
        executorService = Executors.newSingleThreadExecutor();
    }

    @Override
    public void getRss(GetRssCallback callback) {
        if (callback != null) {
            executorService.execute(() -> {
                Rss rss = requestRss(callback);
                callback.onRssLoaded(rss);
            });
        }
    }

    private Rss requestRss(GetRssCallback callback) {
        HttpURLConnection connection = null;
        InputStreamReader inputStream = null;
        BufferedReader reader = null;
        StringBuilder message = new StringBuilder();
        Rss rss = null;

        try {
            URL url = new URL("https://habrahabr.ru/rss/hubs/all/");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.connect();

            inputStream = new InputStreamReader(connection.getInputStream());
            reader = new BufferedReader(inputStream);
            String line;
            while ((line = reader.readLine()) != null) {
                message.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.onRssFailed();
        } finally {
            if (connection != null) connection.disconnect();
            try {
                if (inputStream != null) inputStream.close();
                if (reader != null) reader.close();

            } catch (IOException e) {
                e.printStackTrace();
                callback.onRssFailed();
            }
        }

        Serializer serializer = new Persister();
        Reader stringReader = new StringReader(message.toString());

        try {
            rss = serializer.read(Rss.class, stringReader, false);
        } catch (Exception e) {
            e.printStackTrace();
            callback.onRssFailed();
        }

        return rss;
    }
}
