package com.testing.epub;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Created by mikhail.kutuzov on 03.04.2016.
 */
public class GetRidOffXhtmlComments implements Function<String, String> {
    private String soc = "<!--";
    private String eoc = "-->";

    @Override
    public String apply(String s) {
        if (s.startsWith(soc)) {
            throw new InvalidXhtmlFormatException("The document is started with " + soc);
        }
        if (s.endsWith(soc)) {
            throw new InvalidXhtmlFormatException("The document is finished on " + soc);
        }

        String[] splitWithSoc = s.split(soc);

        if(splitWithSoc.length > 1) {
            return Stream.of(splitWithSoc).skip(1).reduce(splitWithSoc[0], (a, b) -> {
                String[] splitWithEoc = b.split(eoc);
                if (splitWithEoc.length == 2) {
                    return a + splitWithEoc[1];
                } else if (splitWithEoc.length == 1) {
                    return a;
                } else {
                    throw new InvalidXhtmlFormatException("The document does not have an opening comment tag but has a closing one");
                }
            });
        } else {
            return s;
        }
    }
}
