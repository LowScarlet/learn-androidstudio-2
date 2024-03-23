package com.example.praktikum_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;

import com.example.praktikum_1.db.models.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;

public class LoginActivity extends AppCompatActivity {
    public static DatabaseReference usersDocument = UserModel.usersDocument;

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;

    private void login(String username, String password) {
        Query usernameQuery = usersDocument.orderByChild("username").equalTo(username);
        usernameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(), "User Belum Terdaftar!", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String storedPassword = userSnapshot.child("password").getValue(String.class);
                    if (storedPassword.equals(password)) {
                        Toast.makeText(getApplicationContext(), "Berhasil Masuk!", Toast.LENGTH_SHORT).show();

                        finishAffinity();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                        return;
                    }
                    Toast.makeText(getApplicationContext(), "Password Salah!", Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
            } else {
                login(username, password);
            }
        });
    }
}