package com.testing.epub;

import nl.siegmann.epublib.domain.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikhail.kutuzov on 03.04.2016.
 */
public interface BookValidator {
    ArrayList<? extends BookError> emptyErrors = new ArrayList<>(0);

    List<? extends BookError> validate(Book book);
}
