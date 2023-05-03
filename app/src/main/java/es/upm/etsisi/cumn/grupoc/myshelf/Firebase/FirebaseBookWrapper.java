package es.upm.etsisi.cumn.grupoc.myshelf.Firebase;

import java.io.Serializable;

import es.upm.etsisi.cumn.grupoc.myshelf.REST.BookResponse;
import es.upm.etsisi.cumn.grupoc.myshelf.ui.bookshelf.shelfitem.EBookShelfItem;

public class FirebaseBookWrapper implements Serializable {
    private BookResponse bookResponse;

    private EBookShelfItem eBookShelfItem;

    private FirebaseBook2 firebaseBook2;

    public FirebaseBookWrapper(FirebaseBook2 firebaseBook2, EBookShelfItem type, BookResponse book) {
        this.eBookShelfItem = type;
        this.bookResponse = book;
        this.firebaseBook2 = firebaseBook2;
    }

    public BookResponse getBookResponse() {
        return bookResponse;
    }

    public EBookShelfItem geteBookShelfItem() {
        return eBookShelfItem;
    }

    public FirebaseBook2 getFirebaseBook2() {
        return firebaseBook2;
    }
}
