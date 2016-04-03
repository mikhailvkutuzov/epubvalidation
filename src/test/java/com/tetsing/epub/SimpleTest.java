package com.tetsing.epub;

import org.junit.Test;

import java.io.IOException;

/**
 * Created by mikhail.kutuzov on 03.04.2016.
 */
public class SimpleTest {



    @Test
    public void getPathFromHtml() throws IOException {
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<img src=\"smiley.gif\" alt=\"Smiley face\" width=\"42\" height=\"42\">\n" +
                "\n" +
                "</body>\n" +
                "</html>";

        String img = html.split("<img ")[1];
    }


}
