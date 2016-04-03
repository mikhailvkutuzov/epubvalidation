package com.testing.epub;

import java.util.function.Function;

/**
 * Created by mikhail.kutuzov on 03.04.2016.
 */
public class GetSourceOfImgTag implements Function<String, String> {
    private static final String srcAttribute = " src";

    @Override
    public String apply(String s) {
        if (s.indexOf("<") != -1) {
            throw new InvalidXhtmlFormatException();
        }
        String[] srcAndOther = s.split(srcAttribute);
        if (srcAndOther.length == 1) {
            srcAndOther = s.split(srcAttribute.trim());
            if (srcAndOther.length == 1) {
                throw new InvalidXhtmlFormatException();
            }
        }
        String[] hereAreSourcePath = srcAndOther[1].split("\"");
        return hereAreSourcePath[1];
    }
}
