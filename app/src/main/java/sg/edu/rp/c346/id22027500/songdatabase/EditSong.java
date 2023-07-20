package sg.edu.rp.c346.id22027500.songdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditSong extends AppCompatActivity {
    EditText etID, ettitle, etsinger, etyear;
    RadioGroup rgstar;
    Button btnUpdate, btnDelete, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);
        etID = findViewById(R.id.etID);
        ettitle = findViewById(R.id.ettitle);
        etsinger = findViewById(R.id.etsinger);
        etyear = findViewById(R.id.etyear);
        rgstar = findViewById(R.id.rgstar);
        btnUpdate = findViewById(R.id.btnupdate);
        btnDelete = findViewById(R.id.btndelete);
        btnCancel = findViewById(R.id.btncancel);

        Intent intent = getIntent();
        String titlerec = intent.getStringExtra("Title");
        ettitle.setText(titlerec);
        Integer idrec = intent.getIntExtra("ID", 0);
        etID.setText(idrec);
        String singerrec = intent.getStringExtra("Singers");
        etsinger.setText(singerrec);
        Integer yearrec = intent.getIntExtra("Year", 0);



        Integer starsrec = intent.getIntExtra("Stars", 1);
        RadioButton radioButton = (RadioButton) rgstar.getChildAt(starsrec -1);
        radioButton.setChecked(true);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper db = new dbHelper(EditSong.this);
                int  radiobuttonId = rgstar.getCheckedRadioButtonId();
                RadioButton radiobutton = rgstar.findViewById(radiobuttonId);
                int stars = Integer.parseInt(radiobutton.getText().toString());
                Song data = new Song(Integer.parseInt(etID.getText().toString()), ettitle.getText().toString(), etsinger.getText().toString(), Integer.parseInt(etyear.getText().toString()), stars);
                db.updateSong(data);
                db.close();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper db = new dbHelper(EditSong.this);
                db.deleteSong(Integer.parseInt(etID.getText().toString()));
                db.close();

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditSong.this, Songlv.class);
                startActivity(intent);
            }
        });





    }
}