package kr.ac.yeonsung.parkyouil98.campinggajo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UpdateActivity extends AppCompatActivity {
        private LinearLayout layout_pw, layout_info, layout_pw_chg ;
        private EditText chk_pw, info_name,info_num, basic_pw, new_pw, new_pw2;
        private Button chk_pw_btn, pw_chg_btn, cancel_btn, update_btn;

        private AlertDialog dialog;

        private SharedPreferences appData;

        private String password;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.updateinfo);
            setTitle("정보수정");

            layout_pw_chg = findViewById(R.id.layout_pw_chg);
            layout_info = findViewById(R.id.layout_info);
            layout_pw = findViewById(R.id.layout_pw);
            chk_pw = findViewById(R.id.chk_pw);
            info_name = findViewById(R.id.info_name);
            info_num = findViewById(R.id.info_num);
            pw_chg_btn = findViewById(R.id.pw_chg_btn);
            cancel_btn = findViewById(R.id.cancel_btn);
            update_btn = findViewById(R.id.update_btn);
            chk_pw_btn = findViewById(R.id.chk_pw_btn);
            basic_pw = findViewById(R.id.basic_pw);
            new_pw = findViewById(R.id.new_pw);
            new_pw2 = findViewById(R.id.new_pw2);

            pw_chg_btn.setOnClickListener(chgPwListener);
            update_btn.setOnClickListener(updateListener);

            cancel_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(UpdateActivity.this,MyPageActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            chk_pw_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chk_pw();
                }
            });
        }
        //비밀번호 변경버튼 리스너
        View.OnClickListener chgPwListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pw_chg_btn.getText().toString().equals("비밀번호 변경")) {
                    pw_chg_btn.setText("비밀번호 변경 취소");
                    layout_pw_chg.setVisibility(View.VISIBLE);
                }
                else {
                    pw_chg_btn.setText("비밀번호 변경");
                    basic_pw.setText("");
                    new_pw.setText("");
                    new_pw2.setText("");
                    layout_pw_chg.setVisibility(View.GONE);
                }
            }
        };
        //업데이트 리스너
        View.OnClickListener updateListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //업데이트전 확인 메서드
                update_chk();
            }
        };

        //비밀번호 확인 메서드
        public void chk_pw(){
            String user_password = chk_pw.getText().toString();

            Response.Listener<String> resposneListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        String user_name = jsonObject.getString("name");
                        String user_number = jsonObject.getString("number");
                        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                        if (success != null && success.equals("1")) {
                            layout_pw.setVisibility(View.GONE);
                            layout_info.setVisibility(View.VISIBLE);
                            info_name.setText(user_name);
                            info_num.setText(user_number);
                            password = user_password;
                        }
                        else {
                            dialog = builder.setMessage("비밀번호가 틀렸습니다").setNegativeButton("확인", null).create();
                            dialog.show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                        dialog = builder.setMessage("비밀번호가 다릅니다").setNegativeButton("확인", null).create();
                        dialog.show();
                    }
                }
            };
            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                    return;
                }
            };
            appData = getSharedPreferences("appData", MODE_PRIVATE);
            String user_id = appData.getString("ID", "");

            ChkPwRequest chkpwRequest = new ChkPwRequest(user_id, user_password, resposneListener, errorListener);
            chkpwRequest.setShouldCache(false);
            RequestQueue queue = Volley.newRequestQueue(UpdateActivity.this);
            queue.add(chkpwRequest);
        }

        //업데이트전 확인 메서드
        public void update_chk(){
            if (pw_chg_btn.getText().toString().equals("비밀번호 변경")) {
                //비밀번호 제외 빈칸확인
                if (info_name.getText().toString().equals("") || info_num.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                    dialog = builder.setMessage("빈칸이 있습니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                }
                else {
                    update();
                }
            }
            else if(pw_chg_btn.getText().toString().equals("비밀번호 변경 취소")){
                    //비밀번호 포함 빈칸확인
                    if(info_name.getText().toString().equals("") || info_num.getText().toString().equals("") || basic_pw.getText().toString().equals("") || new_pw.getText().toString().equals("") || new_pw2.getText().toString().equals("") ){
                        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                        dialog = builder.setMessage("빈칸이 있습니다.").setPositiveButton("확인", null).create();
                        dialog.show();
                    }
                    else {
                        if(!basic_pw.getText().toString().equals(password)){
                            AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                            dialog = builder.setMessage("기존 비밀번호가 다릅니다").setPositiveButton("확인", null).create();
                            dialog.show();
                        }
                        else{
                            if(!new_pw.getText().toString().equals(new_pw2.getText().toString())){
                                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                                dialog = builder.setMessage("새로운 비밀번호가 다릅니다").setPositiveButton("확인", null).create();
                                dialog.show();
                            }
                            else{
                                update_pw();
                            }
                        }
                    }
                }
        }

    //업데이트 메서드
    public void update(){
        appData = getSharedPreferences("appData", MODE_PRIVATE);
        String user_id = appData.getString("ID", "");
        String user_name = info_name.getText().toString();
        String user_number = info_num.getText().toString();

        Response.Listener<String> resposneListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String update_suc = jsonObject.getString("update_suc");
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                    if (update_suc != null && update_suc.equals("1")) {
                        SharedPreferences.Editor editor = appData.edit();
                        editor.remove("NAME");
                        editor.commit();
                        editor.putString("NAME", info_name.getText().toString().trim());
                        editor.apply();

                        Intent intent = new Intent(UpdateActivity.this, MyPageActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        dialog = builder.setMessage("에러발생").setNegativeButton("확인", null).create();
                        dialog.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                    dialog = builder.setMessage("익셉션 에러").setNegativeButton("확인", null).create();
                    dialog.show();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                return;
            }
        };

        UpdateRequest updateRequest = new UpdateRequest(user_id, user_name, user_number, resposneListener, errorListener);
        updateRequest.setShouldCache(false);
        RequestQueue queue = Volley.newRequestQueue(UpdateActivity.this);
        queue.add(updateRequest);
    }
    public void update_pw(){
        appData = getSharedPreferences("appData", MODE_PRIVATE);
        String user_id = appData.getString("ID", "");
        String user_name = info_name.getText().toString();
        String user_number = info_num.getText().toString();
        String user_password = new_pw.getText().toString();

        Response.Listener<String> resposneListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String update_suc = jsonObject.getString("update_suc");
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                    if (update_suc != null && update_suc.equals("1")) {
                        SharedPreferences.Editor editor = appData.edit();
                        editor.remove("ID");
                        editor.remove("NAME");
                        editor.commit();

                        Intent intent = new Intent(UpdateActivity.this,MyPageActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        dialog = builder.setMessage("에러발생").setNegativeButton("확인", null).create();
                        dialog.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
                    dialog = builder.setMessage("익셉션 에러").setNegativeButton("확인", null).create();
                    dialog.show();
                }
            }
        };
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                return;
            }
        };

        UpdatePwRequest updatepwRequest = new UpdatePwRequest(user_id,user_password, user_name, user_number, resposneListener, errorListener);
        updatepwRequest.setShouldCache(false);
        RequestQueue queue = Volley.newRequestQueue(UpdateActivity.this);
        queue.add(updatepwRequest);
    }
}
