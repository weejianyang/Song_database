package sg.edu.rp.c346.id22027500.songdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Songlv extends AppCompatActivity {
    ListView lv;
    ArrayAdapter<Song> aa;
    ArrayList<Song> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songlv);
        lv = findViewById(R.id.lv);

        al = new ArrayList<>();
        aa = new ArrayAdapter<>(Songlv.this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        Intent intentReceived = getIntent();

        dbHelper db = new dbHelper(Songlv.this);

        // retrieve all tasks from database
        al.addAll(db.getSong());
        aa.notifyDataSetChanged();

        db.close();











    }
}