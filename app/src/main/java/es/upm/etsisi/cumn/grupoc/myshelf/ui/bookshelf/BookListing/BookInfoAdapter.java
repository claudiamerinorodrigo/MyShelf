package es.upm.etsisi.cumn.grupoc.myshelf.ui.bookshelf.BookListing;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import es.upm.etsisi.cumn.grupoc.myshelf.Firebase_Utils;
import es.upm.etsisi.cumn.grupoc.myshelf.R;
import es.upm.etsisi.cumn.grupoc.myshelf.REST.BookResponse;
import es.upm.etsisi.cumn.grupoc.myshelf.databinding.BookInfoBinding;
import es.upm.etsisi.cumn.grupoc.myshelf.ui.bookshelf.shelfitem.EBookShelfItem;

public class BookInfoAdapter extends RecyclerView.Adapter<BookInfoAdapter.ViewHolder>{


    private List<BookResponse> bookList;
    private EBookShelfItem eBookShelfItem;

    public BookInfoAdapter(List<BookResponse> bookList, EBookShelfItem eBookShelfItem) {
        this.bookList = bookList;
        this.eBookShelfItem = eBookShelfItem;
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
                // Comprobar si el libro existe en Firebase
                Query query = Firebase_Utils.getRootFirebase().child(eBookShelfItem.name().toLowerCase()).orderByValue().equalTo(bookResponse.getKey());
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            // El libro está en la biblioteca, eliminarlo
                            for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                childSnapshot.getRef().removeValue();
                            }
                            Toast.makeText(l.getContext(), "El libro " + bookResponse.getTitle() + " ha sido eliminado de la biblioteca.", Toast.LENGTH_LONG).show();
                        } else {
                            // El libro no está en la biblioteca
                            Toast.makeText(l.getContext(), "El libro " + bookResponse.getTitle() + " no está en tu biblioteca.", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Error en la consulta a Firebase
                        Toast.makeText(l.getContext(), "Error al comprobar la biblioteca.", Toast.LENGTH_LONG).show();
                    }
                });
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
