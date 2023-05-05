package com.example.kosrbajnoksag;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class TeamsService extends Service {

    private final IBinder binder = new LocalBinder();
    private FirebaseFirestore db;
    private ArrayList<ListData> dataArrayList = new ArrayList<>();

    public class LocalBinder extends Binder {
        TeamsService getService() {
            return TeamsService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = FirebaseFirestore.getInstance();
    }

    public ArrayList<ListData> getTeamData() {
        db = FirebaseFirestore.getInstance();
        int[] imageList = {R.drawable.laker, R.drawable.warriorslogo, R.drawable.nets};
        db.collection("Teams")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (int i = 0; i < task.getResult().size(); i++) {
                            String coach = (String) task.getResult().getDocuments().get(i).get("Coach");
                            ArrayList<String> players = (ArrayList<String>) task.getResult().getDocuments().get(i).get("Players");
                            String name = (String) task.getResult().getDocuments().get(i).get("name");

                            int imageIndex = i % imageList.length;
                            int image = imageList[imageIndex];

                            ListData listData = new ListData(coach, players.size(), name, image);
                            dataArrayList.add(listData);
                        }
                    } else {
                        Log.w("Firestore", "Error getting documents.", task.getException());
                    }
                });

        return dataArrayList;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
