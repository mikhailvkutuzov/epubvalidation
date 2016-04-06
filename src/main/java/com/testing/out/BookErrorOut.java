package com.testing.out;

import com.testing.epub.BookError;

import java.util.List;

/**
 * Created by mikhail.kutuzov on 06.04.2016.
 */
public interface BookErrorOut {
    String out(String name, List<? extends BookError> errors);

    String combine(List<String> report);
}
