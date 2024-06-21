package id.my.lowscarlet.uts_native;

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
                new Intent(this, id.my.lowscarlet.uts_native.activity.user.Activity.class),
                "Users",
                "/api/rest/users"
        ));
        itemList.add(new Model(
                new Intent(this, id.my.lowscarlet.uts_native.activity.userProfile.Activity.class),
                "UserProfiles",
                "/api/rest/userProfiles"
        ));
        itemList.add(new Model(
                new Intent(this, id.my.lowscarlet.uts_native.activity.socialMedia.Activity.class),
                "SocialMedias",
                "/api/rest/socialMedias"
        ));
        itemList.add(new Model(
                new Intent(this, id.my.lowscarlet.uts_native.activity.institution.Activity.class),
                "Institutions",
                "/api/rest/institutions"
        ));
        itemList.add(new Model(
                new Intent(this, id.my.lowscarlet.uts_native.activity.degree.Activity.class),
                "Degrees",
                "/api/rest/degrees"
        ));
        itemList.add(new Model(
                new Intent(this, id.my.lowscarlet.uts_native.activity.department.Activity.class),
                "Departments",
                "/api/rest/departments"
        ));
        itemList.add(new Model(
                new Intent(this, id.my.lowscarlet.uts_native.activity.technicalSkill.Activity.class),
                "TechnicalSkills",
                "/api/rest/technicalSkills"
        ));
        itemList.add(new Model(
                new Intent(this, id.my.lowscarlet.uts_native.activity.company.Activity.class),
                "Companies",
                "/api/rest/companies"
        ));

        TextView tableTitle = findViewById(R.id.tableTitle);
        tableTitle.setText(String.format("Table List (%d):", itemList.size()));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainAdapter(itemList, this));
    }
}
