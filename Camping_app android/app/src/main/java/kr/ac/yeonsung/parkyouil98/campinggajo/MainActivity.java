package kr.ac.yeonsung.parkyouil98.campinggajo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {


    Button my_btn, search_btn, ranking_btn, surrond_btn;
    ImageView mapsearch, surroundings,rank, notice_img, my_img;
    ViewFlipper eventFlipper;
    TextView notice;
    LinearLayout notice_layout;
    Animation flowAnim;
    String success, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        notice_img = findViewById(R.id.notice_img);
        notice_img.setOnClickListener(clickListener);

        mapsearch = findViewById(R.id.mapsearch);
        mapsearch.setOnClickListener(clickListener);

        surroundings = findViewById(R.id.surroundings);
        surroundings.setOnClickListener(clickListener);

        rank = findViewById(R.id.rank);
        rank.setOnClickListener(clickListener);

        my_img = findViewById(R.id.my_img);
        my_img.setOnClickListener(clickListener);

        my_btn = findViewById(R.id.my_btn);
        my_btn.setOnClickListener(clickListener);

        search_btn = findViewById(R.id.search_btn);
        search_btn.setOnClickListener(clickListener);

        ranking_btn = findViewById(R.id.ranking);
        ranking_btn.setOnClickListener(clickListener);

        eventFlipper = findViewById(R.id.eventFlipper);
        eventFlipper.startFlipping();
        eventFlipper.setFlipInterval(2300);
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("키해시는 :", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        my_btn = findViewById(R.id.my_btn);
        search_btn = findViewById(R.id.search_btn);
        ranking_btn = findViewById(R.id.ranking);
        surrond_btn = findViewById(R.id.surrond);

        notice = findViewById(R.id.notice);
        //공지 애니메이션
        flowAnim = AnimationUtils.loadAnimation(this, R.anim.text_slide);
        Animation animTreanTop = AnimationUtils.loadAnimation(getApplication(), R.anim.text_slide);
        notice.startAnimation(animTreanTop);

        notice_layout = findViewById(R.id.notice_layout);
        notice_layout.setOnClickListener(clickListener);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    success = jsonObject.getString("success");
                    content = jsonObject.getString("content");
                    if (success.equals("-1")) {
                        notice.setText("공지가 없습니다.");
                    } else {
                        notice.setText(success);
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
        NoticeOneRequest noticeoneRequest = new NoticeOneRequest(responseListener,errorListener);
        noticeoneRequest.setShouldCache(false);

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(noticeoneRequest);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.my_btn:
                    Intent my_intent = new Intent(MainActivity.this,MyPageActivity.class);
                    startActivity(my_intent);
                    break;
                case R.id.my_img:
                    Intent my1_intent = new Intent(MainActivity.this,MyPageActivity.class);
                    startActivity(my1_intent);
                    break;
                case R.id.mapsearch:
                    Intent s1_intent = new Intent(MainActivity.this, LocationActivity.class);
                    startActivity(s1_intent);
                    break;
                case R.id.search_btn:
                    Intent s_intent = new Intent(MainActivity.this, LocationActivity.class);
                    startActivity(s_intent);
                    break;
                case R.id.surrond:
                    Intent o_intent = new Intent(MainActivity.this, SurrondActivity.class);
                    startActivity(o_intent);
                    break;
                case R.id.surroundings:
                    Intent o_intent1 = new Intent(MainActivity.this, SurrondActivity.class);
                    startActivity(o_intent1);
                    break;
                case R.id.rank:
                    Intent r1_intent = new Intent(MainActivity.this, RankingActivity.class);
                    startActivity(r1_intent);
                    break;
                case R.id.ranking:
                    Intent r2_intent = new Intent(MainActivity.this, RankingActivity.class);
                    startActivity(r2_intent);
                    break;
                case R.id.notice_img:
                    Intent notice_intent = new Intent(MainActivity.this, NoticeActivity.class);
                    startActivity(notice_intent);
                    break;
                case R.id.notice_layout:
                    Intent notice_intent2 = new Intent(MainActivity.this, Notice_ReadActivity.class);
                    notice_intent2.putExtra("title", success);
                    notice_intent2.putExtra("content", content);
                    startActivity(notice_intent2);
                    break;
            }
        }
    };
}