package es.upm.etsisi.cumn.grupoc.myshelf.REST;

import com.google.gson.annotations.SerializedName;

public class BookResponse {
    @SerializedName("key")
    String key;
    @SerializedName("title")
    String title;
    @SerializedName("isbn")
    String isbn;
    @SerializedName("author_key")
    String author_key;
    @SerializedName("author_name")
    String author_name;
    @SerializedName("ratings_average")
    Float ratings_average;

    @Override
    public String toString() {
        return "BookResponse{" +
                "key='" + key + '\'' +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author_key='" + author_key + '\'' +
                ", author_name='" + author_name + '\'' +
                ", ratings_average=" + ratings_average +
                '}';
    }
}
