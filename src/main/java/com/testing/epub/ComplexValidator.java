package com.testing.epub;

import nl.siegmann.epublib.domain.Book;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mikhail.kutuzov on 03.04.2016.
 */
public class ComplexValidator implements BookValidator {
    private List<BookValidator> validators;

    public ComplexValidator(List<BookValidator> validators) {
        this.validators = validators;
    }

    public  ComplexValidator(BookValidator... validators) {
        this.validators = Arrays.asList(validators);
    }

    @Override
    public List<? extends BookError> validate(Book book) {
        return validators.stream()
                .flatMap(v -> v.validate(book).stream())
                .collect(Collectors.toList());
    }
}
