package kr.ac.yeonsung.parkyouil98.campinggajo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FindPwActivity extends AppCompatActivity {
    private EditText text_number,text_name, text_id;
    private Button find_pw_btn;
    private TextView result_pw;
    String result;
    ImageView campimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findpw);
        setTitle("PW찾기");

        text_number = findViewById(R.id.text_number);
        text_name = findViewById(R.id.text_name);
        text_id = findViewById(R.id.text_id);
        find_pw_btn = findViewById(R.id.find_pw_btn);
        result_pw = findViewById(R.id.result_pw);
        campimg = findViewById(R.id.campimg);
        campimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindPwActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        find_pw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                find();
            }
        });
    }

    public void find(){
        String user_id = text_id.getText().toString();
        String user_number = text_number.getText().toString();
        String user_name = text_name.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("-1")) {
                        if(text_id.getText().toString().equals("")||text_number.getText().toString().equals("")||text_name.getText().toString().equals("")){
                            Toast.makeText(FindPwActivity.this,"정보를 다 입력해주세요",Toast.LENGTH_SHORT).show();
                        }else{
                            result_pw.setText("비밀번호를 찾을수 없습니다.");
                            Intent intent = new Intent(FindPwActivity.this, FindPwActivity2.class);
                            result = result_pw.getText().toString();
                            intent.putExtra("result",result);
                            startActivity(intent);
                        }
                    } else {
                        result_pw.setText("PW : "+success);
                        Intent intent = new Intent(FindPwActivity.this, FindPwActivity2.class);
                        result = result_pw.getText().toString();
                        intent.putExtra("result",result);
                        startActivity(intent);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"에러",Toast.LENGTH_SHORT).show();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"에러발생!",Toast.LENGTH_SHORT).show();
                return;
            }
        };

        // Volley 로 로그인 양식 웹전송
        FindPwRequest findPwRequest = new FindPwRequest(user_id,user_name,user_number,responseListener,errorListener);
        findPwRequest.setShouldCache(false);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(findPwRequest);

    }
}
