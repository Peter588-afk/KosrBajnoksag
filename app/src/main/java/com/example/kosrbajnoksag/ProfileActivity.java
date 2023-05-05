package com.example.kosrbajnoksag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {
    private static final String LOG_TAG = ProfileActivity.class.getName();

    TextView profileEmail;

    TextView profileUserName;

    FirebaseFirestore fStore;

    String userID;

    private static final int SECRET_KEY = 99;

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    ProfileService profileService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                championship();
            }
        });

        Button editButton = findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileUpdate();
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = mAuth.getCurrentUser();

                profileService.deleteUser(userID);
                championship();
            }
        });
    }

    private void championship(/* registered used class */) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }
    private void profileUpdate(/* registered used class */) {
        Intent intent = new Intent(this, UpdateUserActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }
}