package org.cba.threading;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.concurrent.Callable;

/**
 * Created by adam on 9/22/2017.
 */
public class ScrapingCallable implements Callable<Group> {
    private final String url;

    public ScrapingCallable(String url) {
        this.url = url;
    }

    @Override
    public Group call() throws Exception {
        Document doc = Jsoup.connect(url).get();
        Elements authors = doc.select("#authors");
        Elements className = doc.select("#class");
        Elements number = doc.select("#group");
        return new Group(authors.text(),number.text(),className.text());
    }
}
