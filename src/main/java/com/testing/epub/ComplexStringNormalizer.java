package com.testing.epub;

import java.util.List;
import java.util.function.Function;

/**
 * Created by mikhail.kutuzov on 03.04.2016.
 */
public class ComplexStringNormalizer implements Function<String, String> {
    private Function<String, String> function;

    public ComplexStringNormalizer(List<Function<String, String>> normalizers) {
        function = normalizers.stream().skip(1).reduce(normalizers.get(0), (a, b) -> a.andThen(b));
    }

    @Override
    public String apply(String s) {
        return function.apply(s);
    }
}
