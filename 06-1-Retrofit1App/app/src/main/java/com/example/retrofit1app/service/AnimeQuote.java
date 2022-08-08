package com.example.retrofit1app.service;

import com.google.gson.annotations.SerializedName;

public class AnimeQuote {

    @SerializedName("anime")
    private String anime;
    @SerializedName("character")
    private String character;
    @SerializedName("quote")
    private String quote;

    public String getAnime() {
        return anime;
    }

    public String getCharacter() {
        return character;
    }

    public String getQuote() {
        return quote;
    }

    @Override
    public String toString() {
        return "AnimeQuote{" +
                "anime='" + anime + '\'' +
                ", character='" + character + '\'' +
                ", quote='" + quote + '\'' +
                '}';
    }
}
