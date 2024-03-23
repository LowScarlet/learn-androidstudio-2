package com.example.praktikum_1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.praktikum_1.db.models.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;

public class RegisterActivity extends AppCompatActivity {
    public static DatabaseReference usersDocument = UserModel.usersDocument;

    private EditText usernameEditText, emailEditText, passwordEditText;
    private Button registerButton;

    private void register(UserModel createdUser) {
        Query usernameQuery = usersDocument.orderByChild("username").equalTo(createdUser.getUsername());
        usernameQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(), "Username " + createdUser.getUsername() + " Telah Terdaftar!", Toast.LENGTH_SHORT).show();
                    return;
                }
                createdUser.AddOrUpdateUser();
                Toast.makeText(getApplicationContext(), "Username " + createdUser.getUsername() + " Berhasil Terdaftar!", Toast.LENGTH_SHORT).show();

                finishAffinity();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.putExtra("username", createdUser.getUsername());
                startActivity(intent);
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
        setContentView(R.layout.activity_register);

        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Semua field harus diisi!", Toast.LENGTH_SHORT).show();
            } else {
                UserModel createdUser = new UserModel(
                        username,
                        email,
                        password,
                        LocalDateTime.now(),
                        LocalDateTime.now()
                );
                register(createdUser);
            }
        });
    }
}
