package com.playbuzz.automation;

import com.playbuzz.automation.buzzapp.endpoints.ArticleEndpoint;
import com.playbuzz.automation.buzzapp.models.Article;
import com.playbuzz.automation.core.rest.RestBaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.regex.Pattern;

public class ArticlesTest extends RestBaseTest {

    private static final String IMAGE_URL = "//cdn.playbuzz.com/cdn/";
    private static final String IMAGE_GUID_REGEX = "(?i)\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}";
    private static final String IMAGE_NAME_REGEX = "(?i).*(\\.jpg)+(\\.png)+";

    private Pattern imagePattern = Pattern.compile(IMAGE_URL + IMAGE_GUID_REGEX + "/" + IMAGE_NAME_REGEX);

    private ArticleEndpoint articleEndpoint;

    @Test
    public void checkImageString() {

        articleEndpoint = new ArticleEndpoint(httpClient);

        List<Article> articles = articleEndpoint.getArticles();

        Assert.assertTrue(articles.stream().allMatch(item ->
                imagePattern.matcher(item.getImageLarge()).matches()), "ImageLarge property has a wrong value");
        Assert.assertTrue(articles.stream().allMatch(item ->
                imagePattern.matcher(item.getImageMedium()).matches()), "ImageMedium property has a wrong value");

    }

    @Test
    public void checkTags() {

        articleEndpoint = new ArticleEndpoint(httpClient);

        List<Article> articles = articleEndpoint.getArticles();

        Assert.assertTrue(articles.stream().allMatch(item ->
                        item.getTags().stream().allMatch(tag -> tag.getName() != null && tag.getWeight() != null)),
                "Tag does not have 'name' or 'weight' property");

    }

    @Test
    public void checkItemAlias() {

        articleEndpoint = new ArticleEndpoint(httpClient);

        List<Article> articles = articleEndpoint.getArticles();

        Assert.assertTrue(articles.stream().allMatch(item -> item.getItemAlias()
                        .equals(item.getChannelAlias() + "/" + item.getTitleAlias())),
                "Item alias should be equal to {channel alias}/{title alias}");

    }

    @Test
    public void checkTitle() {

        articleEndpoint = new ArticleEndpoint(httpClient);

        List<Article> articles = articleEndpoint.getArticles();

        Assert.assertTrue(articles.stream().allMatch(item -> item.getTitle() != null),
                "Title should not be null");

    }


}
