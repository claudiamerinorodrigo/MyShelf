package es.upm.etsisi.cumn.grupoc.myshelf.Firebase;

import java.io.Serializable;

import es.upm.etsisi.cumn.grupoc.myshelf.REST.BookResponse;
import es.upm.etsisi.cumn.grupoc.myshelf.ui.bookshelf.shelfitem.EBookShelfItem;

public class FirebaseBookWrapper implements Serializable {
    private BookResponse bookResponse;

    private EBookShelfItem eBookShelfItem;
    private String firebaseKey;
    private String openBookskey;


    public FirebaseBookWrapper(FirebaseBook2 firebaseBook2, EBookShelfItem type, BookResponse book) {
        this.eBookShelfItem = type;
        this.bookResponse = book;
    }

    public BookResponse getBookResponse() {
        return bookResponse;
    }

    public EBookShelfItem geteBookShelfItem() {
        return eBookShelfItem;
    }

    public String getFirebaseKey() {
        return firebaseKey;
    }

    public String getOpenBookskey() {
        return openBookskey;
    }
}
