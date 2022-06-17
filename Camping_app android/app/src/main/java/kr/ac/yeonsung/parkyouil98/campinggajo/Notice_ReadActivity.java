package kr.ac.yeonsung.parkyouil98.campinggajo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Notice_ReadActivity extends AppCompatActivity {

    EditText notice_read_title, notice_content;
    String title,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice_read);
        setTitle("공지사항");

        notice_read_title = findViewById(R.id.notice_read_title);
        notice_content = findViewById(R.id.notice_content);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");

        notice_read_title.setText(title);
        notice_content.setText(content);


        // 메인페이지로 이동
        Button btn_home = findViewById(R.id.notice_read_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Notice_ReadActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

}
