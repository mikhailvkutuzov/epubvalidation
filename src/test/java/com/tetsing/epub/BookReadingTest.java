package com.tetsing.epub;

import com.testing.epub.*;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.epub.EpubReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikhail.kutuzov on 03.04.2016.
 */
public class BookReadingTest {
    private EpubReader reader;

    @Before
    public void before(){
        reader = new EpubReader();
    }



    @Test
    public void testTitle() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource("zdorov.epub").getFile());

        FileInputStream input = new FileInputStream(file);

        Book book =  reader.readEpub(input);

        Assert.assertEquals(0,   new BookTitleValidator().validate(book).size());
    }

    @Test
    public void testGetRidOfComments() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource("nocomments.html").getFile());

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String s = reader.lines().reduce("", (a, b) -> a + b);

        GetRidOffXhtmlComments normalizer = new GetRidOffXhtmlComments();

        Assert.assertEquals(s, normalizer.apply(s));

        file = new File(classLoader.getResource("comments.html").getFile());

        reader = new BufferedReader(new FileReader(file));
        s = reader.lines().reduce("", (a, b) -> a + b);

        Assert.assertNotEquals(-1, s.indexOf("<!--"));

        Assert.assertEquals(-1, normalizer.apply(s).indexOf("<!--"));

        Assert.assertEquals(-1, normalizer.apply(s).indexOf("-->"));
    }

    @Test
    public void testImagesAndTitle() throws IOException {

        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource("zdorov.epub").getFile());

        FileInputStream input = new FileInputStream(file);

        Book book =  reader.readEpub(input);


        List<BookValidator> validators = new ArrayList<>(2);
        validators.add(new BookTitleValidator());
        validators.add(new NoImageFoundValidator(new GetRidOffXhtmlComments(), new GetSourceOfImgTag()));

        List<? extends BookError> errors = new ComplexValidator(validators).validate(book);

        Assert.assertEquals(0, errors.size());
    }


    @Test
    public void testBrokenImage() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        File file = new File(classLoader.getResource("brokenzdorov.epub").getFile());

        FileInputStream input = new FileInputStream(file);

        Book book =  reader.readEpub(input);


        List<BookValidator> validators = new ArrayList<>(2);
        validators.add(new BookTitleValidator());
        validators.add(new NoImageFoundValidator(new GetRidOffXhtmlComments(), new GetSourceOfImgTag()));

        List<? extends BookError> errors = new ComplexValidator(validators).validate(book);

        Assert.assertEquals(1, errors.size());
    }

}
