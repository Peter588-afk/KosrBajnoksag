package com.example.kosrbajnoksag;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;

        import com.example.kosrbajnoksag.databinding.ActivityTeamsDetailBinding;

public class TeamsDetailActivity extends AppCompatActivity {

    ActivityTeamsDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeamsDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        if (intent != null){
            String name = intent.getStringExtra("name");
            String coach = intent.getStringExtra("Coach");
            int players = intent.getIntExtra("Players", R.string.lakersPlayers);
            int image = intent.getIntExtra("image", R.drawable.laker);

            binding.detailName.setText(name);
            binding.detailCoach.setText(coach);
            binding.detailPlayers.setText(players);
            binding.detailImage.setImageResource(image);
        }
    }
}