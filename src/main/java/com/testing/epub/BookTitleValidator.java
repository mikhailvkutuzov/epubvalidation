package com.testing.epub;

import nl.siegmann.epublib.domain.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikhail.kutuzov on 03.04.2016.
 */
public class BookTitleValidator implements BookValidator {
    @Override
    public List<? extends BookError> validate(Book book) {
        if(book.getTitle() == null || book.getTitle().trim() == ""){
            List<BookError> errors = new ArrayList<>(1);
            BookError error = new NoBookTitle();
            errors.add(error);
            return  errors;
        }
        return emptyErrors;
    }
}
