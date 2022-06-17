package kr.ac.yeonsung.parkyouil98.campinggajo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class InquiryActivity extends AppCompatActivity {

    EditText inq_title, inq_content;
    private SharedPreferences appData;
    private AlertDialog dialog;
    String id,success,title,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquiry);
        setTitle("문의 하기");
        inq_title = findViewById(R.id.inquiry_title);
        inq_content = findViewById(R.id.inquiry_content);


        appData = getSharedPreferences("appData", MODE_PRIVATE);
        // SharedPreferences에 저장된 id 값 가져오기 없을경우 빈값("") 가져옴


        Button inq_btn = findViewById(R.id.inquiry_btn);
        inq_btn.setOnClickListener(ClickListener);
    }
    View.OnClickListener ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            inquiry();
        }
    };

    public void inquiry(){
        id = appData.getString("ID", "");
        title = inq_title.getText().toString();
        content = inq_content.getText().toString();
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    success = jsonObject.getString("success");
                    if (success.equals("1")) {
                        Toast.makeText(getApplicationContext(), "문의작성 완료!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(InquiryActivity.this, QnaActivity.class);
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
        // Volley 웹전송
        InquiryRequest inquiryRequest = new InquiryRequest(id,title,content,responseListener,errorListener);
        inquiryRequest.setShouldCache(false);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(inquiryRequest);
    }
}
