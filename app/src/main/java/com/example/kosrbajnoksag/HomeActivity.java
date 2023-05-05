package com.example.kosrbajnoksag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;

    private ProfileService profileService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        profileService = new ProfileService();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    return true;
                case R.id.teams:
                    startActivity(new Intent(getApplicationContext(), TeamsActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.musor:
                    startActivity(new Intent(getApplicationContext(), WeeklyActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();
        userAdapter = new UserAdapter(this, userList);
        recyclerView.setAdapter(userAdapter);

        // Adatok betöltése a service-ből
        profileService.getAllUsers(new ProfileService.AllUserDataListener() {
            @Override
            public void onSuccess(List<User> users) {
                userList.addAll(users);
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e) {
                // Handle error
            }
        });

        Button descButton = findViewById(R.id.DescButton);
        descButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descUpdate();
            }
        });


        Button ascButton = findViewById(R.id.AscButton);
        ascButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ascUpdate();
            }
        });
        limitUpdate();
        Spinner limitSpinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.limit_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        limitSpinner.setAdapter(adapter);


        // Spinner választás figyelése
        limitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int limit = Integer.parseInt(parent.getItemAtPosition(position).toString());
                limitUpdate(limit); //
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void limitUpdate() {
        Spinner limitSpinner = findViewById(R.id.spinner);
        int limit = Integer.parseInt(limitSpinner.getSelectedItem().toString());

        profileService.getAllUsersLimited(limit, new ProfileService.AllUserDataListener() {
            @Override
            public void onSuccess(List<User> users) {
                userList.clear();
                userList.addAll(users);
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e) {
                // Handle error
            }
        });
    }
    private void limitUpdate(int limit) {
        profileService.getAllUsersLimited(limit, new ProfileService.AllUserDataListener() {
            @Override
            public void onSuccess(List<User> users) {
                userList.clear();
                userList.addAll(users);
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e) {
                // Handle error
            }
        });
    }

    private void descUpdate() {
        profileService.getAllUsersSortedByUserNameDescending(new ProfileService.AllUserDataListener() {
            @Override
            public void onSuccess(List<User> users) {
                userList.clear();
                userList.addAll(users);
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e) {
                // Handle error
            }
        });

    }

    private void ascUpdate() {
        profileService.getAllUsersSortedByUserNameAscending(new ProfileService.AllUserDataListener() {
            @Override
            public void onSuccess(List<User> users) {
                userList.clear();
                userList.addAll(users);
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e) {
                // Handle error
            }
        });

    }

}