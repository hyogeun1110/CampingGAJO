package kr.ac.yeonsung.parkyouil98.campinggajo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity {
    ListView notice_listView;
    ArrayList<Notice> notices = new ArrayList<Notice>();
    String idx;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notice);
        setTitle("공지사항 목록");

        notice_listView = findViewById(R.id.notice_listview);

        Response.Listener<String> resposneListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int length = jsonObject.length();
                    for (int i=0; i<length-1; i++) {
                        JSONObject test = jsonObject.getJSONObject(Integer.toString(i));

                        idx = test.getString("idx");
                        String title = test.getString("title");
                        String writer = test.getString("writer");
                        String content = test.getString("content");
                        String date = test.getString("date");
                        String hit = test.getString("hit");

                        notices.add(new Notice(idx,title,writer,content,date,hit));
                    }
                    CustomCampsAdapter adapter = new CustomCampsAdapter(NoticeActivity.this, notices);
                    notice_listView.setAdapter(adapter);
                    notice_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            Notice notice = (Notice) adapter.getItem(position);
                            Intent intent = new Intent(NoticeActivity.this, Notice_ReadActivity.class);
                            intent.putExtra("title", notice.getTitle());
                            intent.putExtra("content",notice.getContent());
                            String idx0 = notice.getIdx();
                            hitPlus(idx0);

                            startActivity(intent);

                        }
                    });
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        NoticeRequest noticeRequest = new NoticeRequest(resposneListener);
        noticeRequest.setShouldCache(false);
        RequestQueue queue = Volley.newRequestQueue(NoticeActivity.this);
        queue.add(noticeRequest);
    }

    public class Notice{
        private String idx;
        private String title;
        private String writer;
        private String content;
        private String date;
        private String hit;


        public String getIdx() { return idx; }
        public String getTitle() { return title; }
        public String getWriter() { return writer; }
        public String getContent() { return content; }
        public String getDate() { return date; }
        public String getHit(){ return hit; }

        public Notice(String idx, String title, String writer, String content, String date,String hit) {
            this.idx = idx;
            this.title = title;
            this.writer = writer;
            this.content = content;
            this.date = date;
            this.hit = hit;
        }

    }
    public class CustomCampsAdapter extends ArrayAdapter {
        public CustomCampsAdapter(Context context, ArrayList notices) {
            super(context, 0, notices);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.notice_list, parent, false);
            }

            Notice notice = (Notice) getItem(position);
            TextView idx = (TextView) convertView.findViewById(R.id.notice_idx);
            TextView title = (TextView) convertView.findViewById(R.id.notice_title);
            TextView writer = (TextView) convertView.findViewById(R.id.notice_writer);
            TextView date = (TextView) convertView.findViewById(R.id.notice_date);
            TextView hit = (TextView) convertView.findViewById(R.id.notice_hit);

            idx.setText(notice.getIdx());
            title.setText(notice.getTitle());
            writer.setText(notice.getWriter());
            date.setText(notice.getDate());
            hit.setText("조회수:" + notice.getHit());

            return convertView;
        }
    }
    public void hitPlus(String idx0){
        Response.Listener<String> resposneListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject test = jsonObject.getJSONObject("success");
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        HitRequest hitRequest = new HitRequest(idx0, resposneListener);
        hitRequest.setShouldCache(false);
        RequestQueue queue = Volley.newRequestQueue(NoticeActivity.this);
        queue.add(hitRequest);

    }
}
