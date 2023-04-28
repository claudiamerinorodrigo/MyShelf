package es.upm.etsisi.cumn.grupoc.myshelf.REST;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BookResponse implements Serializable {
    @SerializedName("key")
    String key;
    @SerializedName("title")
    String title;
    @SerializedName("isbn")
    List<String> isbn;
    @SerializedName("author_key")
    List<String> author_key;
    @SerializedName("author_name")
    List<String> author_name;

    @SerializedName("covers")
    List<String> covers;

    @SerializedName("authors")
    List<AuthorBookResponse> authors;

    public String getCover_i() {
        return cover_i;
    }

    @SerializedName("cover_i")
    String cover_i;
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

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getIsbn() {
        return isbn;
    }

    public List<String> getAuthor_key() {
        return author_key;
    }

    public List<String> getAuthor_name() {
        return author_name;
    }

    public Float getRatings_average() {
        return ratings_average;
    }

    public List<String> getCovers() {
        return covers;
    }

    public String getCover() {
        return getCovers() != null ? getCovers().get(getCovers().size() - 1) : getCover_i();
    }

    private static class AuthorBookResponse {

        @SerializedName("author")
        public AuthorKeyBookResponse author;

        private static class AuthorKeyBookResponse {
            @SerializedName("key")
            public String key;
        }
    }

    public String getAuthorKey() {
        return authors.get(0).author.key;
    }
}
