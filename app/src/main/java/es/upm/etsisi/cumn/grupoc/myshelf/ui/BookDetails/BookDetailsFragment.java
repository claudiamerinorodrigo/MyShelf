package es.upm.etsisi.cumn.grupoc.myshelf.ui.BookDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import es.upm.etsisi.cumn.grupoc.myshelf.Firebase.FirebaseBook;
import es.upm.etsisi.cumn.grupoc.myshelf.REST.AuthorResponse;
import es.upm.etsisi.cumn.grupoc.myshelf.REST.BookResponse;
import es.upm.etsisi.cumn.grupoc.myshelf.REST.OpenBooksAdapter;
import es.upm.etsisi.cumn.grupoc.myshelf.databinding.FragmentBookDetailsBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentBookDetailsBinding binding;

    public BookDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBookDetailsBinding.inflate(inflater, container, false);
        Bundle bundle = getArguments();
        FirebaseBook firebaseBook = BookDetailsFragmentArgs.fromBundle(bundle).getMyArg();

        Picasso.get().load("https://covers.openlibrary.org/b/id/" + firebaseBook.getBookResponse().getCover() +"-L.jpg")
                .resize(150, 300)
                .centerCrop().into(binding.bookCover);

        binding.bookTitle.setText(firebaseBook.getBookResponse().getTitle());



        Call<AuthorResponse> call = OpenBooksAdapter.getApiService().getAuthorById(firebaseBook.getBookResponse().getAuthorKey());
        call.enqueue(new Callback<AuthorResponse>() {
            @Override
            public void onResponse(Call<AuthorResponse> call, Response<AuthorResponse> response) {
                binding.bookAuthor.setText(response.body().getName());
            }

            @Override
            public void onFailure(Call<AuthorResponse> call, Throwable t) {

            }
        });
        //binding.bookIsbn.setText(firebaseBook.getBookResponse().getIsbn().get(0));

        return binding.getRoot();
    }
}