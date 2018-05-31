package com.casaoficios.appcasaoficios.util;

/**
 * Created by Jaime on 27/09/2017.
 */
public class StringWithTag {
    public String string;
    public Object tag;

    public StringWithTag(String stringPart, Object tagPart) {
        string = stringPart;
        tag = tagPart;
    }

    @Override
    public String toString() {
        return string;
    }
}