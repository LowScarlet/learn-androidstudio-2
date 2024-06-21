package id.my.ardy.uts_native;

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
                new Intent(this, id.my.ardy.uts_native.activity.author.Activity.class),
                "Author",
                "/api/rest/author"
        ));
        itemList.add(new Model(
                new Intent(this, id.my.ardy.uts_native.activity.member.Activity.class),
                "member",
                "/api/rest/member"
        ));
        itemList.add(new Model(
                new Intent(this, id.my.ardy.uts_native.activity.publisher.Activity.class),
                "publisher",
                "/api/rest/publisher"
        ));
        itemList.add(new Model(
                new Intent(this, id.my.ardy.uts_native.activity.category.Activity.class),
                "category",
                "/api/rest/category"
        ));
        itemList.add(new Model(
                new Intent(this, id.my.ardy.uts_native.activity.book.Activity.class),
                "book",
                "/api/rest/book"
        ));

        TextView tableTitle = findViewById(R.id.tableTitle);
        tableTitle.setText(String.format("Table List (%d):", itemList.size()));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainAdapter(itemList, this));
    }
}
