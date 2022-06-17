package kr.ac.yeonsung.parkyouil98.campinggajo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MyPageActivity extends AppCompatActivity {
    private TextView user_txt,btn_text_user,btn_text_dip,btn_text_qna,signup_txt;
    private Button logout_btn, home_btn;
    private LinearLayout layout_login,layout_my;
    private ImageView btn_icon_user, btn_icon_dip, btn_icon_qna;

    private SharedPreferences appData;

    private EditText login_id,login_pw;
    private Button login_btn, home_btn1, signup_btn, find_id, find_pw;
    private LinearLayout logon_layout;
    private TextView txt_login;
    public String name;

    private AlertDialog dialog;

    String id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);
        setTitle("마이페이지");

        layout_login = findViewById(R.id.layout_login);
        layout_my = findViewById(R.id.layout_my);
        user_txt = findViewById(R.id.user_txt);
        logout_btn = findViewById(R.id.logout_btn);
        home_btn = findViewById(R.id.home_btn);

        login_id = findViewById(R.id.login_id);
        login_pw = findViewById(R.id.login_pw);
        login_btn = findViewById(R.id.login_btn);
        //home_btn1 = findViewById(R.id.home_btn1);
        signup_txt = findViewById(R.id.singup_txt);
        logon_layout = findViewById(R.id.logon_layout);
        find_id = findViewById(R.id.find_id);
        find_pw = findViewById(R.id.find_pw);

        btn_text_user = findViewById(R.id.btn_text_user);
        btn_text_dip = findViewById(R.id.btn_text_dip);
        btn_text_qna = findViewById(R.id.btn_text_qna);
        btn_icon_user = findViewById(R.id.btn_icon_user);
        btn_icon_dip = findViewById(R.id.btn_icon_dip);
        btn_icon_qna = findViewById(R.id.btn_icon_qna);

        appData = getSharedPreferences("appData", MODE_PRIVATE);

        // SharedPreferences에 저장된 id 값 가져오기 없을경우 빈값("") 가져옴
        id = appData.getString("ID", "");
        String call_name = appData.getString("NAME", "");

        find_id.setOnClickListener(findListener);
        find_pw.setOnClickListener(findListener);

        btn_text_user.setOnClickListener(updateListener);
        btn_icon_user.setOnClickListener(updateListener);

        btn_text_dip.setOnClickListener(DipListener);
        btn_icon_dip.setOnClickListener(DipListener);

        btn_text_qna.setOnClickListener(QnaListener);
        btn_icon_qna.setOnClickListener(QnaListener);
        // 로그인 상태 확인
        if(id.equals("")){
            //id 값이 없으면 로그인 페이지 VISIBLE
            layout_login.setVisibility(View.VISIBLE);
            layout_my.setVisibility(View.GONE);
        }
        else{
            //id 값이 있을경우 반대로
            layout_login.setVisibility(View.GONE);
            layout_my.setVisibility(View.VISIBLE);
            user_txt.setText(call_name+"님의 마이페이지");
        }

        //로그인 -> 회원가입 페이지로 이동버튼
        signup_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MyPageActivity.this,RegisterActivity.class);
                startActivity(intent2);
                finish();
            }
        });

        //로그인 -> 메인페이지로 이동버튼
        /*home_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyPageActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });*/

        //홈버튼
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MyPageActivity.this,MainActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent2);
                finish();
            }
        });

        //로그아웃 버튼
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
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
    //마이페이지 -> qna
    View.OnClickListener QnaListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent_qna = new Intent(MyPageActivity.this,QnaActivity.class);
            startActivity(intent_qna);
        }
    };

    //마이페이지 -> 찜목록 리스너
    View.OnClickListener DipListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent_dip = new Intent(MyPageActivity.this,DipActivity.class);
            startActivity(intent_dip);
        }
    };

    //마이페이지 -> 유저정보수정 리스너
    View.OnClickListener updateListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent_update = new Intent(MyPageActivity.this,UpdateActivity.class);
            startActivity(intent_update);
        }
    };
    //마이페이지 -> 찜목록 리스너
    View.OnClickListener dipListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent_dip = new Intent(MyPageActivity.this,DipActivity.class);
            startActivity(intent_dip);
        }
    };

    //아이디 비번 찾기 리스너
    View.OnClickListener findListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.find_id:
                    Intent intent_Fid = new Intent(MyPageActivity.this,FindIdActivity.class);
                    startActivity(intent_Fid);
                    break;
                case R.id.find_pw:
                    Intent intent_Fpw = new Intent(MyPageActivity.this,FindPwActivity.class);
                    startActivity(intent_Fpw);
                    break;
            }
        }
    };

    //로그아웃 메서드
    private void logout(){
        //SharedPreferences.Editor 불러오기
        SharedPreferences.Editor editor = appData.edit();

        //ID 값 삭제
        editor.remove("ID");
        editor.remove("NAME");
        editor.commit();

        //페이지 새로고침
        finish();//인텐트 종료
        overridePendingTransition(0, 0);//인텐트 효과 없애기
        Intent intent = getIntent(); //인텐트
        startActivity(intent); //액티비티 열기
        overridePendingTransition(0, 0);//인텐트 효과 없애기
    }

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

                        //페이지 새로고침
                        finish();//인텐트 종료
                        overridePendingTransition(0, 0);//인텐트 효과 없애기
                        Intent intent = getIntent(); //인텐트
                        startActivity(intent); //액티비티 열기
                        overridePendingTransition(0, 0);//인텐트 효과 없애기

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
