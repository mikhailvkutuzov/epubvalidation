package com.testing.out;

import com.testing.epub.BookError;

import java.util.List;

/**
 * Created by mikhail.kutuzov on 06.04.2016.
 */
public class JsonBookErrorPrinter implements BookErrorOut {
    @Override
    public String out(String name, List<? extends BookError> errors) {
        return null;
    }

    @Override
    public String combine(List<String> report) {
        return null;
    }
}
