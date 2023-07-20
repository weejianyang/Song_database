package sg.edu.rp.c346.id22027500.songdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Songlv extends AppCompatActivity {
    ListView lv;
    Button btnSongs;
    //ArrayAdapter<Song> aa;
    CustomAdapter adapter;
    ArrayList<Song> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songlv);
        lv = findViewById(R.id.lv);
        btnSongs = findViewById(R.id.btnSongs);

        dbHelper db = new dbHelper(Songlv.this);
        al = new ArrayList<>();
        //aa = new ArrayAdapter<Song>(Songlv.this, android.R.layout.simple_list_item_1, al);
        adapter = new CustomAdapter(Songlv.this, R.layout.row, al);
        lv.setAdapter(adapter);

        ArrayList<Song> songs = db.getSong();
        ArrayList<Song>  filterstar = db.getfilterstars();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = filterstar.get(position);
                Toast.makeText(Songlv.this, song.toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Songlv.this, EditSong.class);
                intent.putExtra("ID", song.getId());
                intent.putExtra("Title", song.getTitle());
                intent.putExtra("Singers", song.getSingers());
                intent.putExtra("Year", song.getYear());
                intent.putExtra("Stars", song.getStar());
                startActivity(intent);
            }
        });

        btnSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                al.clear();
                for (Song song : filterstar) {
                    al.add(song);
                }
                adapter.notifyDataSetChanged();
            }
        });







        // retrieve all tasks from database
        al.addAll(db.getSong());
        adapter.notifyDataSetChanged();

        db.close();











    }
}