package es.upm.etsisi.cumn.grupoc.myshelf.ui.bookshelf.AddBook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import es.upm.etsisi.cumn.grupoc.myshelf.databinding.FragmentAddBookBinding;
import es.upm.etsisi.cumn.grupoc.myshelf.ui.bookshelf.shelfitem.EBookShelfItem;

public class AddBookFragment extends Fragment {
    private FragmentAddBookBinding binding;
    private AddBookViewModel mViewModel;

    public static AddBookFragment newInstance() {
        return new AddBookFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAddBookBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(this).get(AddBookViewModel.class);


        Bundle bundle = getArguments();
        EBookShelfItem bookShelfType = es.upm.etsisi.cumn.grupoc.myshelf.ui.bookshelf.AddBook.AddBookFragmentArgs.fromBundle(bundle).getMyArg();
        binding.buttonSearch.setOnClickListener(this::onSearch);

        mViewModel.getBookResponseList().observe(getViewLifecycleOwner(), (o) -> {
            binding.listBook.setAdapter(new AddBookTileAdapter(o, bookShelfType));
        });


        return binding.getRoot();
    }

    private void onSearch(View view) {
        mViewModel.search(binding.inputSearch.getEditText().getText().toString());
    }


}