package com.playbuzz.automation.core.rest;

import com.google.gson.Gson;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.io.IOException;

public class HttpClient implements Closeable {

    private CloseableHttpClient closableHTTPClient = HttpClients.createDefault();
    private CloseableHttpResponse response;
    private String responseText;
    private HttpClientContext context = HttpClientContext.create();
    private Gson gson = new Gson();

    private HttpClient() {}

    public static HttpClient create() {
        return new HttpClient();
    }

    private String makeRequest(HttpUriRequest httpUriRequest) {
        responseText = null;
        try {
            response = closableHTTPClient.execute(httpUriRequest, context);
            if (response.getEntity() != null) {
                responseText = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            }
        } catch (IOException e) {
            throw new RuntimeException("Couldn't make a request", e);
        }
        return responseText;
    }

    public String get(String requestURL) {
        return makeRequest(new HttpGet(requestURL));
    }

    public <T> T get(String requestURL, Class<T> clazz) {
        return gson.fromJson(makeRequest(new HttpGet(requestURL)), clazz);
    }

    @Override
    public void close() {
        try {
            EntityUtils.consume(response.getEntity());
            closableHTTPClient.close();
            response.close();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't close the connection", e);
        }
    }
}
