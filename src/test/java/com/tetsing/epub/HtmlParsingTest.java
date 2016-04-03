package com.tetsing.epub;

import com.testing.epub.GetSourceOfImgTag;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by mikhail.kutuzov on 03.04.2016.
 */
public class HtmlParsingTest {



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
        img = img.substring(0, img.indexOf(">"));
        GetSourceOfImgTag getSource = new GetSourceOfImgTag();
        String source = getSource.apply(img);
        Assert.assertEquals("smiley.gif", source);
    }


}
