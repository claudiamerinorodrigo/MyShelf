package es.upm.etsisi.cumn.grupoc.myshelf;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.HashMap;

import es.upm.etsisi.cumn.grupoc.myshelf.REST.BookResponse;
import es.upm.etsisi.cumn.grupoc.myshelf.REST.BookResponseSearch;
import es.upm.etsisi.cumn.grupoc.myshelf.REST.OpenBooksAdapter;
import es.upm.etsisi.cumn.grupoc.myshelf.databinding.ActivityNavBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NavActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityNavBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        View view = binding.navView.getHeaderView(0);
        TextView textView = view.findViewById(R.id.userEmail);
        textView.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());


        TextView userTextView = view.findViewById(R.id.userName);
        userTextView.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

        setSupportActionBar(binding.appBarNav.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.bookShelfListFragment)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_nav);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}