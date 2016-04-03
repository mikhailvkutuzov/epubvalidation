package com.testing.epub;

import nl.siegmann.epublib.domain.Book;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by mikhail.kutuzov on 03.04.2016.
 */
public class NoImageFoundValidator implements BookValidator {
    private static final String imgTag = "<img ";
    private Function<String, String> contentNormalizer;
    private Function<String, String> srcFromImageTag;


    public NoImageFoundValidator(Function<String, String> contentNormalizer, Function<String, String> srcFromImageTag) {
        this.contentNormalizer = contentNormalizer;
        this.srcFromImageTag = srcFromImageTag;
    }

    @Override
    public List<? extends BookError> validate(Book book) {

        book.getContents().stream()
                .filter(r -> r.getMediaType().getName().equals("application/xhtml+xml"))
                .map(r -> {
                    try {
                        String xhtml = contentNormalizer.apply(new String(r.getData(), r.getInputEncoding()));
                        Stream.of(xhtml.split(imgTag)).skip(1).filter(s -> {
                            String path = srcFromImageTag.apply(s.substring(0, s.indexOf(">")));


                        });

                    } catch (IOException e) {
                        throw new RuntimeException();
                    }
                })
                .


    }




}
