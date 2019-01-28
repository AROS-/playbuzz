package com.playbuzz.automation.buzzapp.endpoints;

import com.playbuzz.automation.buzzapp.models.Article;
import com.playbuzz.automation.buzzapp.models.Response;
import com.playbuzz.automation.core.rest.HttpClient;
import com.playbuzz.automation.core.utils.Config;

import java.util.List;

public class ArticleEndpoint {

    private static final String ARTICLES_ENDPOINT = Config.create().getApiUrl() + "Items";

    private HttpClient client;

    public ArticleEndpoint(HttpClient client) {
        this.client = client;
    }

    public List<Article> getArticles() {
        return client.get(ARTICLES_ENDPOINT, Response.class).getPayload().getItems();
    }

}
