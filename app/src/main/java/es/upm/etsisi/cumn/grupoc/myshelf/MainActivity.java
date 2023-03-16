package es.upm.etsisi.cumn.grupoc.myshelf;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Intent signInIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initFirebaseLogin();

        if (FirebaseAuth.getInstance().getCurrentUser() == null)
            signInLauncher.launch(signInIntent);
        else {
            Intent intent = new Intent(this, NavActivity.class);
            startActivity(intent);
        }



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initFirebaseLogin() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Create and launch sign-in intent
        signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
    }

    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            this::onSignInResult
    );

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Logged Successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, NavActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Logged Failed", Toast.LENGTH_LONG).show();
            signInLauncher.launch(signInIntent);
        }
    }
}