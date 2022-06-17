package kr.ac.yeonsung.parkyouil98.campinggajo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private String id;

    private SharedPreferences appData;

    private EditText login_id,login_pw;
    private Button login_btn, home_btn, find_id, find_pw;
    private LinearLayout logon_layout;
    private TextView txt_login,signup_txt;
    ImageView campimg;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        setTitle("로그인");

        login_id = findViewById(R.id.login_id);
        login_pw = findViewById(R.id.login_pw);
        login_btn = findViewById(R.id.login_btn);
        home_btn = findViewById(R.id.home_btn);
        signup_txt = findViewById(R.id.singup_txt);
        logon_layout = findViewById(R.id.logon_layout);
        find_id = findViewById(R.id.find_id);
        find_pw = findViewById(R.id.find_pw);
        campimg = findViewById(R.id.campimg);

        find_id.setOnClickListener(findListener);
        find_pw.setOnClickListener(findListener);

        appData = getSharedPreferences("appData", MODE_PRIVATE);

        campimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



        //회원가입 페이지로 이동
        signup_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent2);
                finish();
            }
        });


        //로그인 버튼
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    //아이디 비번 찾기 리스너
    View.OnClickListener findListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.find_id:
                    Intent intent_Fid = new Intent(LoginActivity.this,FindIdActivity.class);
                    startActivity(intent_Fid);
                    finish();
                    break;
                case R.id.find_pw:
                    Intent intent_Fpw = new Intent(LoginActivity.this,FindPwActivity.class);
                    startActivity(intent_Fpw);
                    finish();
                    break;
            }
        }
    };

    //로그인 메서드
    private void login() {
        final String user_id = login_id.getText().toString();
        String user_password = login_pw.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    name = jsonObject.getString("name");
                    if (success != null && success.equals("1")) {
                        Toast.makeText(getApplicationContext(),"로그인 성공!",Toast.LENGTH_SHORT).show();

                        //로그인 정보 SharedPreferences에 저장
                        save();

                        Intent intent = new Intent(LoginActivity.this,MyPageActivity.class);
                        startActivity(intent);
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(),"로그인 실패!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"로그인 처리시 에러발생!",Toast.LENGTH_SHORT).show();
                return;
            }
        };

        // Volley 로 로그인 양식 웹전송
        LoginRequest loginRequest = new LoginRequest(user_id,user_password,responseListener,errorListener);
        loginRequest.setShouldCache(false);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(loginRequest);

    }

    //SharedPreference에 저장하는 메서드
    private void save(){
        SharedPreferences.Editor editor = appData.edit();

        // 에디터객체.put타입( 저장시킬 이름, 저장시킬 값 )
        // 저장시킬 이름이 이미 존재하면 덮어씌움
        editor.putString("ID", login_id.getText().toString().trim());
        editor.putString("NAME", name.trim());

        // apply, commit 을 안하면 변경된 내용이 저장되지 않음
        editor.apply();
    }
}
