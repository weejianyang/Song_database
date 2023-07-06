package sg.edu.rp.c346.id22027500.songdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText etTitle;
    EditText etSinger;
    EditText etYear;
    RadioGroup rgStar;
    Button btnInsert;
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgStar = findViewById(R.id.rgStar);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper db = new dbHelper(MainActivity.this);

                int checkedRadioId = rgStar.getCheckedRadioButtonId();

                //get checked radio button id and store in variable radioButton
                RadioButton radioButton = rgStar.findViewById(checkedRadioId);
                int star = Integer.parseInt(radioButton.getText().toString());

                db.insertSong(etTitle.getText().toString(), etSinger.getText().toString(), Integer.parseInt(etYear.getText().toString()), star);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Songlv.class);
                startActivity(intent);

            }
        });
    }
}