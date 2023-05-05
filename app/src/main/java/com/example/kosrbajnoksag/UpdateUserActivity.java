package com.example.kosrbajnoksag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UpdateUserActivity extends AppCompatActivity {

    private static final String LOG_TAG = ProfileActivity.class.getName();

    EditText profileEmail;

    EditText profileUserName;

    FirebaseFirestore fStore;

    String userID;

    private static final int SECRET_KEY = 99;

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    ProfileService profileService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();

        profileEmail = findViewById(R.id.profileEmail);
        profileUserName = findViewById(R.id.profileUsername);

        profileService = new ProfileService();

        profileService.getUserData(userID, new ProfileService.UserDataListener() {
            @Override
            public void onSuccess(User user) {
                Log.d(LOG_TAG, "User name: " + user.getUserName());
                profileEmail.setText(user.getEmail());
                profileUserName.setText(user.getUserName());
            }

            @Override
            public void onFailure(Exception e) {
                Log.d(LOG_TAG, "Hiba történt a Firestore adatbázishoz való kapcsolódáskor.", e);
            }
        });

        Button editButton = findViewById(R.id.saveButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = profileEmail.getText().toString();
                String userName = profileUserName.getText().toString();
                profileService.updateProfile(userID, email, userName);
                goProfile();
            }
        });
    }

    private void goProfile(/* registered used class */) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }
}