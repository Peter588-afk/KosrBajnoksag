package com.example.kosrbajnoksag;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ProfileService {

    private static final String LOG_TAG = ProfileService.class.getName();

    private FirebaseFirestore fStore;
    private FirebaseAuth mAuth;
    private static final int SECRET_KEY = 99;

    public ProfileService() {
        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public void getAllUsers(final AllUserDataListener listener) {
        fStore.collection("Users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<User> userList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                userList.add(user);
                            }
                            listener.onSuccess(userList);
                        } else {
                            listener.onFailure(task.getException());
                        }
                    }
                });
    }

    public interface AllUserDataListener {
        void onSuccess(List<User> userList);
        void onFailure(Exception e);
    }

    public void getUserData(String userId, final UserDataListener listener) {
        DocumentReference userRef = fStore.collection("Users").document(userId);
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document != null && document.exists()) {
                        User user = document.toObject(User.class);
                        listener.onSuccess(user);
                    } else {
                        listener.onFailure(new Exception("Nem található felhasználó!"));
                    }
                } else {
                    listener.onFailure(task.getException());
                }
            }
        });
    }

    public interface UserDataListener {
        void onSuccess(User user);

        void onFailure(Exception e);
    }

    public void deleteUser(String userId) {

        FirebaseUser user = mAuth.getCurrentUser();

        fStore.collection("Users").document(userId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(LOG_TAG, "Felhasználó adatai törölve az adatbázisból.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(LOG_TAG, "Hiba történt a felhasználó adatainak törlésekor az adatbázisból.", e);
                    }
                });

        assert user != null;
        user.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(LOG_TAG, "Felhasználó sikeresen törölve az autentikációs rendszerből.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(LOG_TAG, "Hiba történt a felhasználó törlésekor az autentikációs rendszerből.", e);
                    }
                });
    }

    public void updateProfile(String userID, String email, String userName) {
        if (email.isEmpty()) {
            Log.d(LOG_TAG, "Email nem lehet üres");

            return;
        }

        if (userName.isEmpty()) {
            Log.d(LOG_TAG, "Felhasználnév nem lehet üres");

            return;
        }

        fStore.collection("Users").document(userID)
                .update("email", email, "userName", userName)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(LOG_TAG, "Felhasználói adatok frissítve.");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(LOG_TAG, "Hiba történt az adatok frissítésekor.", e);
                    }
                });
    }

    public void getAllUsersSortedByUserNameAscending(final AllUserDataListener listener) {
        fStore.collection("Users")
                .orderBy("userName", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<User> userList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                userList.add(user);
                            }
                            listener.onSuccess(userList);
                        } else {
                            listener.onFailure(task.getException());
                        }
                    }
                });
    }

    public void getAllUsersSortedByUserNameDescending(final AllUserDataListener listener) {
        fStore.collection("Users")
                .orderBy("userName", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<User> userList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                userList.add(user);
                            }
                            listener.onSuccess(userList);
                        } else {
                            listener.onFailure(task.getException());
                        }
                    }
                });
    }


    public interface UserRepository {
        // ...
        void getAllUsersSortedByUserNameAscending(AllUserDataListener listener);
        void getAllUsersSortedByUserNameDescending(AllUserDataListener listener);
    }

    public void getAllUsersLimited(int limit, AllUserDataListener listener) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Users")
                .orderBy("userName")
                .limit(limit)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<User> userList = new ArrayList<>();
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                        User user = documentSnapshot.toObject(User.class);
                        userList.add(user);
                    }
                    listener.onSuccess(userList);
                })
                .addOnFailureListener(e -> listener.onFailure(e));
    }




}
