package com.example.elishevak.training;

/**
 * Created by elishevak on 4/8/2016.
 */
public class Fact {
    private String header;
    private String content;
    private int image;

    public Fact(String header, String content, int image) {
        this.header = header;
        this.content = content;
        this.image = image;
    }

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }

    public int getImage() {
        return image;
    }
}
