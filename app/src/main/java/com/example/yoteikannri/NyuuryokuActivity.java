package com.example.yoteikannri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import androidx.appcompat.app.AppCompatActivity;


public class NyuuryokuActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nyuuryoku_calendar);

        CalendarView calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(
                (calendarView, year, month, date) -> {
                    String message = year + "/" + (month + 1) + "/" + date;
                    NyuuryokuActivityGamen2(message);
                }
        );
    }


        public void NyuuryokuActivityGamen2(String s) {
        setContentView(R.layout.activity_nyuuryoku_youbi);



        View.OnClickListener event = view -> {
            Button b = (Button) view;
            String youbi = b.getText().toString();

            NyuuryokuActivityGamen3(youbi,s);
        };

        findViewById(R.id.buttonGetuyou).setOnClickListener(event);
        findViewById(R.id.buttonKayou).setOnClickListener(event);
        findViewById(R.id.buttonSuiyou).setOnClickListener(event);
        findViewById(R.id.buttonMokuyou).setOnClickListener(event);
        findViewById(R.id.buttonKinnyou).setOnClickListener(event);
        findViewById(R.id.buttonDoyou).setOnClickListener(event);

        }


    public void NyuuryokuActivityGamen3(String s, String m) {

        setContentView(R.layout.activity_nyuuryoku_kamoku);

        Button b1 = findViewById(R.id.button1);
        Button b2 = findViewById(R.id.button2);
        Button b3 = findViewById(R.id.button3);
        Button b4 = findViewById(R.id.button4);
        Button b5 = findViewById(R.id.button5);


        switch (s) {
            case "月曜":

                b1.setText("応用解析");
                b2.setText("電子回路");
                b3.setText("形状記憶");
                b4.setText("空");
                b5.setText("空");

                break;
            case "火曜":
                b1.setText("統計解析");
                b2.setText("熱力学");
                b3.setText("流体力学");
                b4.setText("英語4");
                b5.setText("空");
                break;
            case "水曜":
                b1.setText("数学1B");
                b2.setText("空");
                b3.setText("空");
                b4.setText("空");
                b5.setText("空");
                break;
            case "木曜":
                b1.setText("応用確率論");
                b2.setText("空");
                b3.setText("空");
                b4.setText("空");
                b5.setText("空");
                break;
            case "金曜":
                b1.setText("熱力学");
                b2.setText("流体力学");
                b3.setText("創造演習");
                b4.setText("哲学");
                b5.setText("空");
                break;
            case "土曜":
                b1.setText("ダイナミカル");
                b2.setText("空");
                b3.setText("空");
                b4.setText("空");
                b5.setText("空");
                break;
        }



        View.OnClickListener event = view -> {
            Button b = (Button) view;
            String finalText = m + "  " + b.getText().toString();
            Intent intent = new Intent(getApplication(), KakuninActivity.class);
            intent.putExtra("data", finalText);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        };

        b1.setOnClickListener(event);
        b2.setOnClickListener(event);
        b3.setOnClickListener(event);
        b4.setOnClickListener(event);
        b5.setOnClickListener(event);
    }
}
