package com.testing.downloader;

import nl.siegmann.epublib.domain.Book;
import sun.net.www.http.HttpClient;

import java.net.URI;
import java.util.stream.Stream;

/**
 * Created by mikhail.kutuzov on 05.04.2016.
 */
public class HttpClientDownloder implements BookDownloader {
    private HttpClient client;

    @Override
    public Stream<Book> download(URI uri) {


        return null;
    }
}
