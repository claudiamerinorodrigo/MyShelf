package es.upm.etsisi.cumn.grupoc.myshelf.ui.bookshelf.shelfitem;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import es.upm.etsisi.cumn.grupoc.myshelf.BookShelfListFragment;
import es.upm.etsisi.cumn.grupoc.myshelf.BookShelfListFragmentDirections;
import es.upm.etsisi.cumn.grupoc.myshelf.R;
import es.upm.etsisi.cumn.grupoc.myshelf.REST.BookResponse;
import es.upm.etsisi.cumn.grupoc.myshelf.REST.OpenBooksAdapter;
import es.upm.etsisi.cumn.grupoc.myshelf.databinding.ActivityNavBinding;
import es.upm.etsisi.cumn.grupoc.myshelf.databinding.FragmentBookshelfItemBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BookshelfItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookshelfItemFragment extends Fragment {

    private FragmentBookshelfItemBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private EBookShelfItem mParam1;
    public BookshelfItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookshelfItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookshelfItemFragment newInstance(String param1, String param2) {
        BookshelfItemFragment fragment = new BookshelfItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    BookShelfItemModel bookShelfItemModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBookshelfItemBinding.inflate(inflater, container, false);
        apply();

        return binding.getRoot();
    }

    public void apply() {
        if (getArguments() != null) {
            mParam1 = (EBookShelfItem) getArguments().getSerializable(ARG_PARAM1);
        }
        if (mParam1 == null)
            return;

        binding.textView2.setText(mParam1.getDisplayName());

        bookShelfItemModel = new BookShelfItemModel(mParam1);

        bookShelfItemModel.getBookResponseList().observe(getViewLifecycleOwner(), (o) -> {
            binding.linearLayout.removeAllViews();
            for (MutableLiveData<BookResponse> bookResponse : o) {
                ImageView imageView = new ImageView(getContext());
                LinearLayout.LayoutParams viewParamsCenter = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                imageView.setLayoutParams(viewParamsCenter);

                imageView.setImageResource(R.mipmap.book_shelf_display);


                binding.linearLayout.addView(imageView);

                bookResponse.observe(getViewLifecycleOwner(), (o2) -> {
                    String cover = o2.getCover();
                    if (cover != null)
                        Picasso.get().load("https://covers.openlibrary.org/b/id/" + cover +"-L.jpg")
                                .resize(0, 300)
                                .centerCrop().into(imageView);
                });

            }
        });

        binding.button2.setOnClickListener(this::onClickAddBook);

        binding.getRoot().setOnClickListener(this::onClickShowBooks);
    }

    private void onClickAddBook(View view) {
        NavHostFragment.findNavController(this).navigate(BookShelfListFragmentDirections.actionBookShelfListFragmentToBookAddFragment(bookShelfItemModel.getType()));
    }

    public void onClickShowBooks(View view) {
        NavHostFragment.findNavController(this).navigate(BookShelfListFragmentDirections.actionBookShelfListFragmentToBookLisitingFragment(bookShelfItemModel));
    }
}