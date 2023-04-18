package es.upm.etsisi.cumn.grupoc.myshelf.ui.bookshelf.AddBook;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

import es.upm.etsisi.cumn.grupoc.myshelf.Firebase_Utils;
import es.upm.etsisi.cumn.grupoc.myshelf.R;
import es.upm.etsisi.cumn.grupoc.myshelf.REST.BookResponse;
import es.upm.etsisi.cumn.grupoc.myshelf.databinding.AddBookTileBinding;
import es.upm.etsisi.cumn.grupoc.myshelf.databinding.BookInfoBinding;
import es.upm.etsisi.cumn.grupoc.myshelf.ui.bookshelf.shelfitem.EBookShelfItem;

public class AddBookTileAdapter extends RecyclerView.Adapter<AddBookTileAdapter.ViewHolder>{


    private List<BookResponse> bookList;
    private EBookShelfItem eBookShelfItem;

    public AddBookTileAdapter(List<BookResponse> bookList, EBookShelfItem eBookShelfItem) {
        this.bookList = bookList;
        this.eBookShelfItem = eBookShelfItem;
    }

    @NonNull
    @Override
    public AddBookTileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(AddBookTileBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookResponse bookResponse = bookList.get(position);
        AddBookTileBinding binding = holder.getBinding();

        if (bookResponse != null) {
            String cover = bookResponse.getCover();

            binding.imageView12.setImageResource(R.mipmap.book_shelf_display);

            if (cover != null)
                Picasso.get().load("https://covers.openlibrary.org/b/id/" + cover +"-L.jpg")
                        .resize(150, 300)
                        .centerCrop().into(binding.imageView12);

            binding.bookTitle.setText(bookResponse.getTitle());


            binding.button3.setOnClickListener((l) -> {
                Task task = Firebase_Utils.getRootFirebase().child(eBookShelfItem.name().toLowerCase()).push().setValue(bookResponse.getKey());
                Toast.makeText(l.getContext(), "Se ha añadido el libro " + bookResponse.getTitle() + " con exito.", Toast.LENGTH_LONG).show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AddBookTileBinding binding;
        public ViewHolder(AddBookTileBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public AddBookTileBinding getBinding() {
            return binding;
        }
    }
}
