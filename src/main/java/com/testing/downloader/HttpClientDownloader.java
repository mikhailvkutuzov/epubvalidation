package com.testing.downloader;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by mikhail.kutuzov on 05.04.2016.
 */
public class HttpClientDownloader implements BookDownloader {
    private HttpClient client;

    @Override
    public Stream<Optional<Book>> download(URI uri) {
        int count = 0;

        return IntStream.range(0, count)
                .parallel()
                .mapToObj(i -> getReader(i, HttpClientBuilder.create().build()))
                .map(r -> create(r));
    }

    private InputStream getReader(Integer bookId, HttpClient loader) {
        return  null;
    }

    private Optional<Book> create(InputStream reader) {
        EpubReader r = new EpubReader();
        try {
            return Optional.of(r.readEpub(reader));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

}
