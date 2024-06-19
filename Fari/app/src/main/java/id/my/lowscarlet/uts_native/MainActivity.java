package id.my.lowscarlet.uts_native;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // TODO Edit This Too
    Button userActivity;
    Button sumberActivity;
    Button pemasukanActivity;
    Button pengeluaranActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userActivity = findViewById(R.id.toUser);
        sumberActivity = findViewById(R.id.toSumber);
        pemasukanActivity = findViewById(R.id.toPemasukan);
        pengeluaranActivity = findViewById(R.id.toPengeluaran);

    }
}
