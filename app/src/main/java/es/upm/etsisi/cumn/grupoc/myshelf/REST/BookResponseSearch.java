package es.upm.etsisi.cumn.grupoc.myshelf.REST;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookResponseSearch {
    @SerializedName("numFound")
    int numFound;
    @SerializedName("docs")
    List<BookResponse> docs;

    public int getNumFound() {
        return numFound;
    }

    public List<BookResponse> getDocs() {
        return docs;
    }
}
