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

        //最初の画面で。カレンダーの日付をタップし、それの日付を画面2に渡す
        CalendarView calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(
                (calendarView, year, month, date) -> {
                    String message = year + "/" + (month + 1) + "/" + date;
                    NyuuryokuActivityGamen2(message);
                }
        );
    }


    public void NyuuryokuActivityGamen2(String message) {
        setContentView(R.layout.activity_nyuuryoku_youbi);

        //次の画面で、科目が行われる曜日をタップし、それと日付を画面3に渡す
        View.OnClickListener event = view -> {
            Button b = (Button) view;
            String youbi = b.getText().toString();

            NyuuryokuActivityGamen3(youbi, message);
        };

        findViewById(R.id.buttonGetuyou).setOnClickListener(event);
        findViewById(R.id.buttonKayou).setOnClickListener(event);
        findViewById(R.id.buttonSuiyou).setOnClickListener(event);
        findViewById(R.id.buttonMokuyou).setOnClickListener(event);
        findViewById(R.id.buttonKinnyou).setOnClickListener(event);
        findViewById(R.id.buttonDoyou).setOnClickListener(event);

    }


    public void NyuuryokuActivityGamen3(String youbi, String message) {

        setContentView(R.layout.activity_nyuuryoku_kamoku);

        Button b1 = findViewById(R.id.button1);
        Button b2 = findViewById(R.id.button2);
        Button b3 = findViewById(R.id.button3);
        Button b4 = findViewById(R.id.button4);
        Button b5 = findViewById(R.id.button5);

        //画面2でタップした曜日に応じて科目を表示する(自分用アプリだったので編集機能などは付けずに
        // 直接テキストを書いてました)
        switch (youbi) {
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

        //科目をクリックしたら、日付を合わせて、データベースに記録するデータを作る処理を作る
        View.OnClickListener event = view -> {
            Button b = (Button) view;
            //日付　科目という形になる
            String finalText = message + "  " + b.getText().toString();
            Intent intent = new Intent(getApplication(), KakuninActivity.class);
            //作ったデータを確認画面(KakuninActivity)に送る(確認画面でデータベースに記録したりする)
            intent.putExtra("data", finalText);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        };

        //それぞれのボタンに、処理を実装する
        b1.setOnClickListener(event);
        b2.setOnClickListener(event);
        b3.setOnClickListener(event);
        b4.setOnClickListener(event);
        b5.setOnClickListener(event);
    }
}
