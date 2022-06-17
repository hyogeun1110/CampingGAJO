package kr.ac.yeonsung.parkyouil98.campinggajo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FindidActivity2 extends Activity {
    TextView result,check_id;
    String success,result_value;
    Button login_btn,find_pw_btn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.findid2);
        check_id = findViewById(R.id.check_id);
        check_id.setTextSize(17);

        find_pw_btn = findViewById(R.id.find_pw_btn);
        find_pw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindidActivity2.this,FindPwActivity.class);
                startActivity(intent);
                finish();
            }
        });
        login_btn = findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(login_btn.getText().toString().equals("회원가입")){
                    Intent intent = new Intent(FindidActivity2.this,RegisterActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(FindidActivity2.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        result = findViewById(R.id.result);
        Intent intent = getIntent();
        result_value = intent.getStringExtra("result");
        result.setTextSize(20);
        result.setTextColor(Color.BLACK);
        result.setText(result_value);
        if(result_value.equals("가입하신 아이디가 없습니다.")){
            find_pw_btn.setVisibility(View.GONE);
            check_id.setVisibility(View.GONE);
            login_btn.setText("회원가입");
        }
    }
}