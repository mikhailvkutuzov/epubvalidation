package com.testing.epub;

import java.util.List;

/**
 * Created by mikhail.kutuzov on 03.04.2016.
 */
public class NoImageFound extends BookError{
    private String spinItem;
    private List<String> imageReferences;

    public NoImageFound(String spinItem, List<String> imageReferences) {
        this.spinItem = spinItem;
        this.imageReferences = imageReferences;
    }

    public String getSpinItem() {
        return spinItem;
    }

    public List<String> getImageReferences() {
        return imageReferences;
    }
}
