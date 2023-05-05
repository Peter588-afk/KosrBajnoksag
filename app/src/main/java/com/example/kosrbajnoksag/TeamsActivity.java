package com.example.kosrbajnoksag;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;

        import com.example.kosrbajnoksag.databinding.ActivityTeamsBinding;
        import com.google.android.material.bottomnavigation.BottomNavigationView;
        import com.google.firebase.firestore.FirebaseFirestore;


        import java.util.ArrayList;

public class TeamsActivity extends AppCompatActivity {

    ActivityTeamsBinding binding;
    ListAdapter listAdapter;
    ArrayList<ListData> dataArrayList = new ArrayList<>();
    ListData listData;

    FirebaseFirestore db;
    private TeamsService teamsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeamsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.teams);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.teams:
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

        int[] imageList = {R.drawable.laker, R.drawable.warriorslogo, R.drawable.nets};
        int[] playersList = {R.string.lakersPlayers, R.string.warriorsPlayers, R.string.netsPlayers};
        String[] nameList = {"Lakers", "Golden State Warriors", "Brooklyn Nets"};
        String[] coaches = {"Darwin Ham", "Steve Kerr", "Steve Nash"};

        for (int i = 0; i < imageList.length; i++){
            listData = new ListData(coaches[i], playersList[i], nameList[i],  imageList[i]);
            dataArrayList.add(listData);
        }
        listAdapter = new ListAdapter(TeamsActivity.this, dataArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);

        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TeamsActivity.this, TeamsDetailActivity.class);
                intent.putExtra("name", nameList[i]);
                intent.putExtra("coach", coaches[i]);
                intent.putExtra("players", playersList[i]);
                intent.putExtra("image", imageList[i]);
                startActivity(intent);
            }
        });

        /*db = FirebaseFirestore.getInstance();

        // Service inicializálása
        teamsService = new TeamsService();

        // Adatok lekérése a Service-ből
        dataArrayList = teamsService.getTeamData();

        // ListView adapter létrehozása és beállítása
        listAdapter = new ListAdapter(TeamsActivity.this, dataArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);

        // ListView kattintáskezelő beállítása
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TeamsActivity.this, TeamsDetailActivity.class);
                intent.putExtra("name", dataArrayList.get(i).getName());
                intent.putExtra("coach", dataArrayList.get(i).getCoach());
                intent.putExtra("players", dataArrayList.get(i).getPlayers());
                intent.putExtra("image", dataArrayList.get(i).getImage());
                startActivity(intent);
            }
        });*/
    }
}