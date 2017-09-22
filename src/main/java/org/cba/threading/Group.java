package org.cba.threading;

import java.util.Arrays;

/**
 * Created by adam on 9/22/2017.
 */
public class Group {
    private String[] authors;
    private String number;
    private String className;

    public Group(String authors, String number, String className) {
        this.authors = authors.split(",");
        Arrays.parallelSetAll(this.authors, (i) -> this.authors[i].trim());
        this.number = number;
        this.className = className;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
