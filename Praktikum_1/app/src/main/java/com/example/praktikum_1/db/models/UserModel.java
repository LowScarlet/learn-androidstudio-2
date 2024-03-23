package com.example.praktikum_1.db.models;

import com.example.praktikum_1.utils.Utils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDateTime;

public class UserModel {
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference usersDocument = database.getReference().child("users");

    public void AddOrUpdateUser() {
        DatabaseReference userDocument = usersDocument.child(getUsername());
        userDocument.child("username").setValue(getUsername());
        userDocument.child("email").setValue(getEmail());
        userDocument.child("password").setValue(getPassword());
        userDocument.child("updatedAt").setValue(Utils.localDateTimeToUnix(getUpdatedAt()));
        userDocument.child("createdAt").setValue(Utils.localDateTimeToUnix(getCreatedAt()));
    }

    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserModel(String username, String email, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
