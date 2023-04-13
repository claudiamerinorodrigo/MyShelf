package es.upm.etsisi.cumn.grupoc.myshelf.REST;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenBooksService {

    //https://openlibrary.org/search.json?q=the+lord+of+the+rings
    @GET("search.json")
    Call<BookResponse> getBook(
            @Query("q") String name,
            @Query("fields") String fields,
            @Query("sort") String sort
    );




}
