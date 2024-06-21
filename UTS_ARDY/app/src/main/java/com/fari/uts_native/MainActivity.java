package com.fari.uts_native;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        List<Model> itemList = new ArrayList<>();

        // TODO Edit This Too
        itemList.add(new Model(
                new Intent(this, com.fari.uts_native.activity.pengguna.Activity.class),
                "Pengguna",
                "/api/rest/pengguna"
        ));
        itemList.add(new Model(
                new Intent(this, com.fari.uts_native.activity.sumber.Activity.class),
                "Sumber",
                "/api/rest/sumber"
        ));
        itemList.add(new Model(
                new Intent(this, com.fari.uts_native.activity.pengeluaran.Activity.class),
                "Pengeluaran",
                "/api/rest/pengeluaran"
        ));
        itemList.add(new Model(
                new Intent(this, com.fari.uts_native.activity.pemasukan.Activity.class),
                "Pemasukan",
                "/api/rest/Pemasukan"
        ));

        TextView tableTitle = findViewById(R.id.tableTitle);
        tableTitle.setText(String.format("Table List (%d):", itemList.size()));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainAdapter(itemList, this));
    }
}
