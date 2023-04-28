package es.upm.etsisi.cumn.grupoc.myshelf.Firebase;

import java.io.Serializable;

import es.upm.etsisi.cumn.grupoc.myshelf.REST.BookResponse;
import es.upm.etsisi.cumn.grupoc.myshelf.ui.bookshelf.shelfitem.EBookShelfItem;

public class FirebaseBook implements Serializable {
    private BookResponse bookResponse;

    private EBookShelfItem eBookShelfItem;
    private String firebaseKey;
    private String openBookskey;

    public FirebaseBook(String firebaseKey, String openBookskey) {
        this.firebaseKey = firebaseKey;
        this.openBookskey = openBookskey;
    }

    public FirebaseBook(String firebaseKey, String openBookskey, EBookShelfItem eBookShelfItem, BookResponse bookResponse) {
        this.firebaseKey = firebaseKey;
        this.openBookskey = openBookskey;
        this.bookResponse = bookResponse;
        this.eBookShelfItem = eBookShelfItem;
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
