package kr.ac.yeonsung.parkyouil98.campinggajo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class PopupActivity extends Activity {
    TextView new_name,new_kind,new_address,new_shower,new_sink,new_etc;
    String name,kind,address,shower,sink,id,etc;
    int num;
    SharedPreferences appData;
    int[] imageNum = {R.drawable.camp1,R.drawable.camp2,R.drawable.camp3,R.drawable.camp4,R.drawable.camp5,
    R.drawable.camp6,R.drawable.camp7,R.drawable.camp8,R.drawable.camp9,R.drawable.camp10};
    ImageView image;
    Random rand = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup);
        appData = getSharedPreferences("appData", MODE_PRIVATE);
        id = appData.getString("ID", "");
        image = findViewById(R.id.image1);
        final int j = rand.nextInt(10);
        /*image.setImageResource(imageNum[j]);*/
        Button btn_close = findViewById(R.id.btn_close);
        Button btn_dip = findViewById(R.id.btn_dip);
        if(id=="")
            btn_dip.setVisibility(View.GONE);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Response.Listener<String> resposneListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success != null && success.equals("1")) {
                        Toast.makeText(getApplicationContext(),"찜 성공!",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"찜 삭제",Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                }
            }
        };
        Response.Listener<String> validateListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    System.out.println(success);
                    if (success.equals("1")) { 
                        btn_dip.setText("찜 하기");
                    } else {
                        btn_dip.setText("찜 해제");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                }
            }
        };
        btn_dip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String choice;
                Button dip = findViewById(view.getId());
                if(dip.getText().toString() == "찜 하기"){
                    choice = "1";
                    dip.setText("찜 해제");
                }
                else{
                    choice = "0";
                    dip.setText("찜 하기");
                }
                PopupRequest popupRequest = new PopupRequest(id,name,choice,resposneListener);
                popupRequest.setShouldCache(false);
                RequestQueue queue = Volley.newRequestQueue(PopupActivity.this);
                queue.add(popupRequest);
            }
        });
        new_name = findViewById(R.id.camp_name);
        new_kind = findViewById(R.id.camp_kind);
        new_address = findViewById(R.id.camp_address);
        new_shower = findViewById(R.id.camp_shower);
        new_sink = findViewById(R.id.camp_sink);
        new_etc = findViewById(R.id.camp_etc);

        Intent intent = getIntent();
        num = intent.getIntExtra("num",0);
        name = intent.getStringExtra("name");
        kind = intent.getStringExtra("kind");
        address = intent.getStringExtra("address");
        shower = intent.getStringExtra("shower");
        sink = intent.getStringExtra("sink");
        etc = intent.getStringExtra("etc");

        new_name.setText(name);
        new_kind.setText("종류 : "+kind);
        new_address.setText("주소 : "+address);
        new_shower.setText("샤워실 : "+shower);
        new_sink.setText("싱크대 : "+sink);
        new_etc.setText("기타시설 : "+etc);

        PopupValidate popupValidate = new PopupValidate(id,name,validateListener);
        popupValidate.setShouldCache(false);
        RequestQueue queue2 = Volley.newRequestQueue(PopupActivity.this);
        queue2.add(popupValidate);

        switch (num % 10){
            case 0:
                image.setImageResource(imageNum[0]);
                break;
            case 1:
                image.setImageResource(imageNum[1]);
                break;
            case 2:
                image.setImageResource(imageNum[2]);
                break;
            case 3:
                image.setImageResource(imageNum[3]);
                break;
            case 4:
                image.setImageResource(imageNum[4]);
                break;
            case 5:
                image.setImageResource(imageNum[5]);
                break;
            case 6:
                image.setImageResource(imageNum[6]);
                break;
            case 7:
                image.setImageResource(imageNum[7]);
                break;
            case 8:
                image.setImageResource(imageNum[8]);
                break;
            case 9:
                image.setImageResource(imageNum[9]);
                break;
        }
    }
}
