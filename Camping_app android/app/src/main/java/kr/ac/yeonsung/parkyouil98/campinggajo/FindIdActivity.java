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

public class FindIdActivity extends AppCompatActivity {
    private EditText text_number;
    private Button find_id_btn;
    private TextView result_id;
    String success,result,str1;
    ImageView campimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findid);
        setTitle("ID찾기");

        text_number = findViewById(R.id.text_number);
        find_id_btn = findViewById(R.id.find_id_btn);
        result_id = findViewById(R.id.result_id);
        campimg = findViewById(R.id.campimg);
        campimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindIdActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


        find_id_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                find();
            }
        });
    }
    public void find(){
        String user_number = text_number.getText().toString();
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    success = jsonObject.getString("success");
                    if (success.equals("-1")) {
                        if(text_number.getText().toString().equals("")){
                            Toast.makeText(FindIdActivity.this,"전화번호를 입력해주세요",Toast.LENGTH_SHORT).show();
                        }else{
                            result_id.setText("가입하신 아이디가 없습니다.");
                            Intent intent = new Intent(FindIdActivity.this, FindidActivity2.class);
                            result = result_id.getText().toString();
                            intent.putExtra("result",result);
                            startActivity(intent);
                        }
                    } else {
                        result_id.setText("ID : " + success);
                        Intent intent = new Intent(FindIdActivity.this, FindidActivity2.class);
                        result = result_id.getText().toString();
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
        FindIdRequest findIdRequest = new FindIdRequest(user_number,responseListener,errorListener);
        findIdRequest.setShouldCache(false);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(findIdRequest);

    }
}