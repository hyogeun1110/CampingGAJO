package kr.ac.yeonsung.parkyouil98.campinggajo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;

public class RegisterActivity extends AppCompatActivity {

    private EditText signup_id, signup_pw, signup_pw_overlap, signup_name, signup_number;
    private Button signup_overlap_btn, signup_join_btn,home_btn;
    private AlertDialog dialog;
    private boolean validate = false;
    TextView text123;
    ImageView campimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singup);
        setTitle("회원가입");

        text123 = findViewById((R.id.text123)) ;
        campimg = findViewById(R.id.campimg);
        campimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        signup_id = findViewById(R.id.signup_id);
        signup_pw = findViewById(R.id.signup_pw);
        signup_pw_overlap = findViewById(R.id.signup_pw_overlap);
        signup_name = findViewById(R.id.signup_name);
        signup_number = findViewById(R.id.signup_number);

        signup_overlap_btn = findViewById(R.id.signup_overlap_btn);

        signup_join_btn = findViewById(R.id.signup_join_btn);


        home_btn = findViewById(R.id.home_btn);
        home_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        signup_overlap_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(signup_id.getText().toString().equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("아이디를 입력하세요.").setPositiveButton("확인",null).create();
                    dialog.show();
                    return;
                }
                else{
                    checkId();
                }
            }
        });

        signup_join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //빈칸확인
                if(signup_id.getText().toString().equals("") || signup_pw.getText().toString().equals("") || signup_pw_overlap.getText().toString().equals("") || signup_number.getText().toString().equals("")) {
                    Toast.makeText(RegisterActivity.this, "빈칸이 있습니다.", Toast.LENGTH_LONG).show();
                }

                // 패스워드 일치 확인
                else if (!signup_pw.getText().toString().equals(signup_pw_overlap.getText().toString())){
                    Toast.makeText(RegisterActivity.this, "비밀번호가 일치 하지 않습니다.", Toast.LENGTH_LONG).show();
                }
                else {
                    //회원가입
                    register();
                }
            }
        });
    }
    private void checkId(){
        String user_id = signup_id.getText().toString();

        Response.Listener<String> resposneListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    if (success != null && success.equals("1")) {
                        dialog = builder.setMessage("사용할 수 있는 아이디입니다.").setPositiveButton("확인", null).create();
                        dialog.show();
                        //signup_id.setEnabled(false); //아이디값 고정
                        signup_overlap_btn.setBackgroundColor(getResources().getColor(R.color.black));
                    }
                    else {
                        dialog = builder.setMessage("이미 존재하는 아이디입니다.").setNegativeButton("확인", null).create();
                        dialog.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("에러발생.").setNegativeButton("확인", null).create();
                    dialog.show();
                }
            }
        };
        ValidateRequest validateRequest = new ValidateRequest(user_id, resposneListener);
        validateRequest.setShouldCache(false);
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        queue.add(validateRequest);
    }

    private void register() {
        String user_id = signup_id.getText().toString();
        String user_password = signup_pw.getText().toString();
        String user_name = signup_name.getText().toString();
        String user_number = signup_number.getText().toString();

        Response.Listener<String> resposneListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success != null && success.equals("1")) {  // 회원가입 완료
                        Toast.makeText(getApplicationContext(),"회원가입 성공!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else if(success.equals("-2")){
                        Toast.makeText(getApplicationContext(),"이미 사용중인 전화번호 입니다.",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"회원가입 실패!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"회원가입 처리시 에러발생!",Toast.LENGTH_SHORT).show();
                return;
            }
        };

        // Volley 로 회원양식 웹으로 전송
        RegisterRequest registerRequest = new RegisterRequest(user_id, user_password, user_name, user_number,resposneListener,errorListener);
        registerRequest.setShouldCache(false);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(registerRequest);
    }
}

