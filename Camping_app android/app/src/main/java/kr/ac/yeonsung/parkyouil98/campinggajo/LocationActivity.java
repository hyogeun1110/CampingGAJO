package kr.ac.yeonsung.parkyouil98.campinggajo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LocationActivity extends AppCompatActivity {
    private String location;
    int[] btnNum = {R.id.busan,R.id.chungcheongbuk,R.id.chungcheongnam,R.id.daegu,R.id.daejeon,R.id.ganwon,R.id.gwangju,R.id.gyeonggi,R.id.gyeongsangbuk,R.id.gyeongsangnam,R.id.incheon,
            R.id.jeju,R.id.jeollabuk,R.id.jeollanam,R.id.sejong,R.id.seoul,R.id.ulsan,R.id.all};
    TextView[] txt = new TextView[btnNum.length];
    //Button[] btn = new Button[btnNum.length];
    Double lat, lon;
    int[] btn2 = {R.id.btn_gw,R.id.btn_gi,R.id.btn_gs,R.id.btn_ch,R.id.btn_jj};
    Button[] btnNum2 = new Button[btn2.length];
    LinearLayout gw1,gy1,gy2,gs1,gs2,cn1,cn2,jj1,jj2;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        setTitle("지역 선택");
        gw1 = findViewById(R.id.gw1);
        gy1 = findViewById(R.id.gy1);
        gy2 = findViewById(R.id.gy2);
        gs1 = findViewById(R.id.gs1);
        gs2 = findViewById(R.id.gs2);
        cn1 = findViewById(R.id.cn1);
        cn2 = findViewById(R.id.cn2);
        jj1 = findViewById(R.id.jj1);
        jj2 = findViewById(R.id.jj2);
        for(int i=0; i<txt.length; i++){
            txt[i] = findViewById(btnNum[i]);
            txt[i].setOnClickListener(locationListener);
        }
        /*for(int i=0; i<btn.length; i++){
            btn[i] = findViewById(btnNum[i]);
            btn[i].setOnClickListener(locationListener);
        }*/
        for(int i=0; i< btnNum2.length; i++){
            btnNum2[i] = findViewById(btn2[i]);
            btnNum2[i].setOnClickListener(btnListener);
        }
    }
    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            gw1.setVisibility(View.GONE);
            gy1.setVisibility(View.GONE);
            gy2.setVisibility(View.GONE);
            gs1.setVisibility(View.GONE);
            gs2.setVisibility(View.GONE);
            cn1.setVisibility(View.GONE);
            cn2.setVisibility(View.GONE);
            jj1.setVisibility(View.GONE);
            jj2.setVisibility(View.GONE);
            switch (view.getId()){
                case R.id.btn_gw:
                    gw1.setVisibility(View.VISIBLE);
                    break;
                case R.id.btn_gi:
                    gy1.setVisibility(View.VISIBLE);
                    gy2.setVisibility(View.VISIBLE);
                    break;
                case R.id.btn_gs:
                    gs1.setVisibility(View.VISIBLE);
                    gs2.setVisibility(View.VISIBLE);
                    break;
                case R.id.btn_ch:
                    cn1.setVisibility(View.VISIBLE);
                    cn2.setVisibility(View.VISIBLE);
                    break;
                case R.id.btn_jj:
                    jj1.setVisibility(View.VISIBLE);
                    jj2.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    View.OnClickListener locationListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.busan:
                    location = "busan";
                    lat = 35.20629821398307;
                    lon = 129.07380022545732;
                    break;
                case R.id.chungcheongbuk:
                    location = "chungcheongbuk";
                    lat = 36.7990484513822;
                    lon = 127.85967690074608;
                    break;
                case R.id.chungcheongnam:
                    location = "chungcheongnam";
                    lat = 36.23002828482088;
                    lon = 126.98205806520454;
                    break;
                case R.id.daegu:
                    location = "daegu";
                    lat = 35.84805309298658;
                    lon = 128.75605016389622;
                    break;
                case R.id.daejeon:
                    location = "daejeon";
                    lat = 36.33971775813721;
                    lon = 127.50101507031239;
                    break;
                case R.id.ganwon:
                    location = "ganwon";
                    lat = 37.616696805717154;
                    lon = 128.25173496421021;
                    break;
                case R.id.gwangju:
                    location = "gwangju";
                    lat = 35.168679150419834;
                    lon = 127.03287580187799;
                    break;
                case R.id.gyeonggi:
                    location = "gyeonggi";
                    lat = 37.30923157917295;
                    lon = 127.3110094914036;
                    break;
                case R.id.gyeongsangbuk:
                    location = "gyeongsangbuk";
                    lat = 36.3943775322239;
                    lon = 128.61989700976454;
                    break;
                case R.id.gyeongsangnam:
                    location = "gyeongsangnam";
                    lat = 35.29801061419978;
                    lon = 128.4851794957287;
                    break;
                case R.id.incheon:
                    location = "incheon";
                    lat = 37.399095837109726;
                    lon = 126.65507714475481;
                    break;
                case R.id.jeju:
                    location = "jeju";
                    lat = 33.34002516228429;
                    lon = 126.5371857756193;
                    break;
                case R.id.jeollabuk:
                    location = "jeollabuk";
                    lat = 35.75911272383346;
                    lon = 127.24824966393902;
                    break;
                case R.id.jeollanam:
                    location = "jeollanam";
                    lat = 34.910188195797765;
                    lon = 126.88710749803882 ;
                    break;
                case R.id.sejong:
                    location = "sejong";
                    lat = 36.58960724362158;
                    lon = 127.27376897846321;
                    break;
                case R.id.seoul:
                    location = "seoul";
                    lat = 37.52184775960737;
                    lon = 127.01071270914085;
                    break;
                case R.id.ulsan:
                    location = "ulsan";
                    lat = 35.525336078937784;
                    lon = 129.2907918857212;
                    break;
                case R.id.all:
                    lat = 36.327522792716024;
                    lon = 127.9799120707138;
                    location = "all";
                    break;
            }
            Intent intent = new Intent(LocationActivity.this, KindActivity.class);
            intent.putExtra("location",location);
            intent.putExtra("lat",lat);
            intent.putExtra("lon",lon);
            startActivity(intent);
        }
    };
}