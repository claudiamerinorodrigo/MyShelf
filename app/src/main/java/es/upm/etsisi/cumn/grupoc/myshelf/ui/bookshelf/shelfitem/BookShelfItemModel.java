package es.upm.etsisi.cumn.grupoc.myshelf.ui.bookshelf.shelfitem;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.upm.etsisi.cumn.grupoc.myshelf.Firebase.FirebaseBook;
import es.upm.etsisi.cumn.grupoc.myshelf.Firebase.Firebase_Utils;
import es.upm.etsisi.cumn.grupoc.myshelf.REST.BookResponse;
import es.upm.etsisi.cumn.grupoc.myshelf.REST.OpenBooksAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookShelfItemModel implements Serializable {
    private EBookShelfItem type;
    private MutableLiveData<List<MutableLiveData<FirebaseBook>>> bookResponseList;

    public BookShelfItemModel() {

    }

    public BookShelfItemModel(EBookShelfItem type) {

        this.type = type;

        bookResponseList = new MutableLiveData<List<MutableLiveData<FirebaseBook>>>();

        List<MutableLiveData<FirebaseBook>> bookResponses = new ArrayList<>();

        Firebase_Utils.getRootFirebase().child(type.name().toLowerCase()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else if (task.getResult().exists()){
                    HashMap<String, String> hashMap = (HashMap<String, String>) task.getResult().getValue();

                    for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                        MutableLiveData<FirebaseBook> bookResponseMutableLiveData = new MutableLiveData<>();
                        bookResponses.add(bookResponseMutableLiveData);

                        Call<BookResponse> call = OpenBooksAdapter.getApiService().getBookById(entry.getValue());
                        call.enqueue(new Callback<BookResponse>() {
                            @Override
                            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                                BookResponse book = response.body();
                                bookResponseMutableLiveData.setValue(new FirebaseBook(entry.getKey(),entry.getValue(), type, book));
                            }

                            @Override
                            public void onFailure(Call<BookResponse> call, Throwable t) {
                                Log.i("TEST",  t.toString());
                            }
                        });
                    }

                }

                bookResponseList.postValue(bookResponses);
            }
        });
    }

    public MutableLiveData<List<MutableLiveData<FirebaseBook>>> getBookResponseList() {
        return bookResponseList;
    }

    public String getName() {
        return type.getDisplayName();
    }

    public EBookShelfItem getType() {
        return type;
    }


}
