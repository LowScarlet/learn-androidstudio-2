package com.example.praktikum_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.praktikum_1.db.models.UserModel;
import com.example.praktikum_1.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MainActivity extends AppCompatActivity {
    public static DatabaseReference usersDocument = UserModel.usersDocument;

    private TextView usernameTextView, emailTextView, createdAtTextView, updatedAtTextView;
    private Button logoutButton;

    private String getTimeAgo(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        long diffInMinutes = ChronoUnit.MINUTES.between(dateTime, now);
        long diffInHours = ChronoUnit.HOURS.between(dateTime, now);

        if (diffInMinutes < 60) {
            return diffInMinutes + " Minutes Ago";
        } else {
            return diffInHours + " Hours Ago";
        }
    }

    private void setView(String username) {
        Query usernameQuery = usersDocument.orderByChild("username").equalTo(username);
        usernameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(), "User Belum Terdaftar!", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String username = userSnapshot.child("username").getValue(String.class);
                    String email = userSnapshot.child("email").getValue(String.class);
                    Long createdAt = userSnapshot.child("createdAt").getValue(Long.class);
                    Long updatedAt = userSnapshot.child("updatedAt").getValue(Long.class);

                    String createdAtText = getTimeAgo(Utils.unixToLocalDateTime(createdAt));
                    String updatedAtText = getTimeAgo(Utils.unixToLocalDateTime(updatedAt));

                    usernameTextView.setText("Username: " + username);
                    emailTextView.setText("Email: " + email);
                    createdAtTextView.setText("Created At: " + createdAtText);
                    updatedAtTextView.setText("Updated At: " + updatedAtText);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.toException().printStackTrace();
                Toast.makeText(getApplicationContext(), "Kesalahan DatabaseError", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameTextView = findViewById(R.id.usernameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        createdAtTextView = findViewById(R.id.createdAtTextView);
        updatedAtTextView = findViewById(R.id.updatedAtTextView);
        logoutButton = findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(v -> {
            finishAffinity();
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        });

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        setView(username);
    }
}