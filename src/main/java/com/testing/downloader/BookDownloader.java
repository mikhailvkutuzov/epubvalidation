package com.testing.downloader;

import nl.siegmann.epublib.domain.Book;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by mikhail.kutuzov on 05.04.2016.
 */
public interface BookDownloader {

    Stream<Optional<Book>> download(URI uri);

}
