package com.example.kosrbajnoksag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.kosrbajnoksag.Calender;
import com.example.kosrbajnoksag.Event;

public class WeeklyActivity extends AppCompatActivity implements View.OnClickListener{
    private List<Calender> days;
    private ListView eventListView;
    //private CalendarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);
        /*days = createDays();
        buttons = new Button[7];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = findViewById();
        }*/

        /*BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.musor);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.login:
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.register:
                    startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.teams:
                    startActivity(new Intent(getApplicationContext(), TeamsActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                /*case R.id.tabella:
                    startActivity(new Intent(getApplicationContext(), TabellaActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.musor:
                    return true;
            }
            return false;
        });*/
        Button mon = findViewById(R.id.mon);
        Button tue = findViewById(R.id.tue);
        Button wed = findViewById(R.id.wed);
        Button thur = findViewById(R.id.thur);
        Button fri = findViewById(R.id.fri);
        Button sat = findViewById(R.id.sat);
        Button sun = findViewById(R.id.sun);

        mon.setOnClickListener(this);
        tue.setOnClickListener(this);
        wed.setOnClickListener(this);
        thur.setOnClickListener(this);
        fri.setOnClickListener(this);
        sat.setOnClickListener(this);
        sun.setOnClickListener(this);

        eventListView = findViewById(R.id.eventListView);
        /*adapter = new CalendarAdapter(this, new ArrayList<>());
        eventListView.setAdapter(adapter);*/
    }

    void initializedList(){

    }

    private List<Calender> createDays() {
        days.add(new Calender("Monday", Arrays.asList(
                new Event("Meeting with Joe", "10:00"),
                new Event("Meeting with Carlos", "11:00")
        )));

        days.add(new Calender("Tuesday", Arrays.asList(
                new Event("Meeting with Peter", "12:00"),
                new Event("Meeting with Anna", "13:00")
        )));

        days.add(new Calender("Wednesday", Arrays.asList(
                new Event("Lunch with colleagues", "12:30"),
                new Event("Meeting with clients", "15:00")
        )));

        days.add(new Calender("Thursday", Arrays.asList(
                new Event("Team building activity", "14:00"),
                new Event("Conference call with partners", "16:00")
        )));

        days.add(new Calender("Friday", Arrays.asList(
                new Event("Project meeting", "10:00"),
                new Event("Company event", "19:00")
        )));

        days.add(new Calender("Saturday", Arrays.asList(
                new Event("Family picnic", "11:00"),
                new Event("Movie night with friends", "20:00")
        )));

        days.add(new Calender("Sunday", Arrays.asList(
                new Event("Church service", "9:00"),
                new Event("Dinner with relatives", "18:00")
        )));

        ListView listView = findViewById(R.id.eventListView);
        /*CalendarAdapter adapter = new CalendarAdapter(this, days);
        listView.setAdapter(adapter);*/


        return days;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mon:
                //adapter.updateData(days.get(0).getEvents());
                Toast.makeText(this, "Button 1 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tue:
                /// adapter.updateData(days.get(2).getEvents());
                Toast.makeText(this, "Button 3 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.thur:
                //adapter.updateData(days.get(4).getEvents());
                Toast.makeText(this, "Button 4 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fri:
                //adapter.updateData(days.get(5).getEvents());
                Toast.makeText(this, "Button 5 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sat:
                //adapter.updateData(days.get(6).getEvents());
                Toast.makeText(this, "Button 5 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sun:
                //adapter.updateData(days.get(7).getEvents());
                Toast.makeText(this, "Button 5 clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}