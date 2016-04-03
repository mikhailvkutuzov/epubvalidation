package com.testing.epub;

import nl.siegmann.epublib.domain.Book;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
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
        return book.getSpine().getSpineReferences().stream()
                .map(r -> r.getResource())
                .filter(r -> r.getMediaType().getName().equals("application/xhtml+xml"))
                .map(r -> {
                    try {
                        String xhtml = contentNormalizer.apply(new String(r.getData(), r.getInputEncoding()));
                        List<String> strings = Stream.of(xhtml.split(imgTag))
                                .skip(1)
                                .map(s -> srcFromImageTag.apply(s.substring(0, s.indexOf(">"))))
                                .filter(s -> !book.getResources().getResourceMap().containsKey(s))
                                .collect(Collectors.toList());

                        if (strings.size() > 0) {
                            return new NoImageFound(r.getId(), strings);
                        } else {
                            return null;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException();
                    }
                })
                .filter(e -> e != null)
                .collect(Collectors.toList());
    }


}
