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

        return Stream.of(s.split("soc")).reduce("", (a, b) -> {
            String[] split = b.split(eoc);
            if (split.length == 2) {
                return a + split[1];
            } else if (split.length == 1) {
                return a;
            } else {
                throw new InvalidXhtmlFormatException("The document does not have an opening comment tag but has a closing one");
            }
        });
    }
}
