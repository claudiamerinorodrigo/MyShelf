package es.upm.etsisi.cumn.grupoc.myshelf.ui.bookshelf.BookListing;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import es.upm.etsisi.cumn.grupoc.myshelf.R;
import es.upm.etsisi.cumn.grupoc.myshelf.REST.BookResponse;
import es.upm.etsisi.cumn.grupoc.myshelf.databinding.BookInfoBinding;

public class BookInfoAdapter extends RecyclerView.Adapter<BookInfoAdapter.ViewHolder>{


    private List<BookResponse> bookList;

    public BookInfoAdapter(List<BookResponse> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(BookInfoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BookResponse bookResponse = bookList.get(position);
        BookInfoBinding binding = holder.getBinding();

        if (bookResponse != null) {
            String cover = bookResponse.getCover();

            binding.imageView12.setImageResource(R.mipmap.book_shelf_display);

            if (cover != null)
                Picasso.get().load("https://covers.openlibrary.org/b/id/" + cover +"-L.jpg")
                        .resize(150, 300)
                        .centerCrop().into(binding.imageView12);

            binding.bookTitle.setText(bookResponse.getTitle());


            binding.button3.setOnClickListener((l) -> {
                //TODO REMOVE
                Toast.makeText(l.getContext(), "NO IMPLEMENTADO", Toast.LENGTH_LONG).show();
            });
        }
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private BookInfoBinding binding;
        public ViewHolder(BookInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public BookInfoBinding getBinding() {
            return binding;
        }
    }
}
