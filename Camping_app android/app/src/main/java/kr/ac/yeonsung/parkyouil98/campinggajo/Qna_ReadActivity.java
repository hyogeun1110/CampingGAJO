package kr.ac.yeonsung.parkyouil98.campinggajo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Qna_ReadActivity extends AppCompatActivity {

    EditText qna_title, qna_content, ans_content;
    String title,content,ans,num,success;
    LinearLayout ans_layout;
    Button del_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qna_read);

        qna_title = findViewById(R.id.qna_title);
        qna_content = findViewById(R.id.qna_content);

        ans_content = findViewById(R.id.ans_content);
        ans_layout = findViewById(R.id.ans_layout);

        del_btn = findViewById(R.id.del_btn);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        content = intent.getStringExtra("content");
        ans = intent.getStringExtra("ans");
        num = intent.getStringExtra("num");

        qna_title.setText(title);
        qna_content.setText(content);

        if (ans.equals("0")){
            ans_layout.setVisibility(View.GONE);
            del_btn.setVisibility(View.VISIBLE);
        }
        else {
            ans_layout.setVisibility(View.VISIBLE);
            del_btn.setVisibility(View.GONE);
        }

        //문의삭제
        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                del();
            }
        });


        //답변가져오기
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    success = jsonObject.getString("success");
                    if (success.equals("-1")) {
                        ans_content.setText("답변이 없습니다.");
                    } else {
                        ans_content.setText(success);
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

        // Volley 로 답변받아오기
        AnsRequest ansRequest = new AnsRequest(num,responseListener,errorListener);
        ansRequest.setShouldCache(false);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(ansRequest);



    }
    public void del(){
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String del = jsonObject.getString("del");
                    if (del.equals("1")) {
                        Toast.makeText(getApplicationContext(), "문의삭제 완료!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Qna_ReadActivity.this, QnaActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
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

        // Volley 삭제
        QnaDelRequest qnaDelRequest = new QnaDelRequest(num,responseListener,errorListener);
        qnaDelRequest.setShouldCache(false);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(qnaDelRequest);

    }

}
