package com.testing;

import com.testing.downloader.HttpClientDownloader;
import com.testing.epub.*;
import com.testing.out.BookErrorOut;
import com.testing.out.JsonBookErrorPrinter;

import java.net.URI;
import java.util.stream.Collectors;

/**
 * Created by mikhail.kutuzov on 06.04.2016.
 */
public class BookTestingManager {
    public static void main(String[] args) {

        String url = args[0];

        URI uri = URI.create(url);

        BookValidator validator = new ComplexValidator(new BookTitleValidator(), new NoImageFoundValidator(new GetRidOffXhtmlComments(),new GetSourceOfImgTag()));

        BookErrorOut out = new JsonBookErrorPrinter();

        String report = out.combine(new HttpClientDownloader()
                .download(uri)
                .parallel()
                .map(b -> out.out(b.getTitle(), validator.validate(b)))
                .collect(Collectors.toList()));
    }
}
