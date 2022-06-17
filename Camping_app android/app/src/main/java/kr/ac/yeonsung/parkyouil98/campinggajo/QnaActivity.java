package kr.ac.yeonsung.parkyouil98.campinggajo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QnaActivity extends AppCompatActivity {
    private ListView qna_list;

    private SharedPreferences appData;
    ArrayList<Qna> qna = new ArrayList<Qna>();
    Button inq_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qna);
        qna_list = findViewById(R.id.qna_list);
        setTitle("문의 목록");
        appData = getSharedPreferences("appData", MODE_PRIVATE);
        String user_id = appData.getString("ID", "");

        inq_btn = findViewById(R.id.inquiry_btn);
        inq_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(QnaActivity.this, InquiryActivity.class);
                startActivity(intent2);
                finish();
            }
        });

        Response.Listener<String> resposneListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int length = jsonObject.length();
                    for (int i = 0; i < length - 1; i++) {
                        JSONObject test = jsonObject.getJSONObject(Integer.toString(i));

                        String num = test.getString("inquiry_id");
                        String title = test.getString("inquiry_title");
                        String content = test.getString("inquiry_content");
                        String date = test.getString("inquiry_date");
                        String ans  = test.getString("ans");

                        qna.add(new QnaActivity.Qna(title,content,date,ans,num));
                    }
                    QnaActivity.CustomQnaAdapter adapter = new QnaActivity.CustomQnaAdapter(QnaActivity.this, qna);

                    qna_list.setAdapter(adapter);
                    qna_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            QnaActivity.Qna qna = (QnaActivity.Qna) adapter.getItem(position);
                            Intent intent = new Intent(QnaActivity.this, Qna_ReadActivity.class);
                            intent.putExtra("num", qna.getNum());
                            intent.putExtra("title", qna.getTitle());
                            intent.putExtra("content",qna.getContent());
                            intent.putExtra("date",qna.getDate());
                            intent.putExtra("ans",qna.getAns());

                            startActivity(intent);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
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
        QnaRequest qnaRequest = new QnaRequest(user_id, resposneListener, errorListener);
        qnaRequest.setShouldCache(false);
        RequestQueue queue = Volley.newRequestQueue(QnaActivity.this);
        queue.add(qnaRequest);

    }
    public class Qna {
        private String title;
        private String content;
        private String date;
        private String ans;
        private String num;

        public Qna(String title,String content, String date, String ans , String num) {
            this.title = title;
            this.content = content;
            this.date = date;
            this.ans = ans;
            this.num = num;
        }

        public String getTitle(){
            return title;
        }
        public String getContent(){
            return content;
        }
        public String getDate(){
            return date;
        }
        public String getAns(){
            return ans;
        }
        public String getNum(){
            return num;
        }
    }

    public class CustomQnaAdapter extends ArrayAdapter {
        public CustomQnaAdapter(Context context, ArrayList camps) {
            super(context, 0, camps);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_qna, parent, false);
            }

            Qna qna = (Qna) getItem(position);

            TextView listAns = (TextView) convertView.findViewById(R.id.list_ans);
            TextView listTitle = (TextView) convertView.findViewById(R.id.list_title);
            TextView listDate = (TextView) convertView.findViewById(R.id.list_date);
            if(qna.getAns().equals("1")){listAns.setVisibility(View.VISIBLE);}
            else {listAns.setVisibility(View.GONE);}
            listTitle.setText(qna.getTitle());
            listDate.setText(qna.getDate());

            return convertView;
        }
    }
}

