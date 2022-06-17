package kr.ac.yeonsung.parkyouil98.campinggajo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ConvenienceActivity extends AppCompatActivity {
    String location,kind,shower="0",sink="0";
    Button finish_btn;
    CheckBox shower_check,sink_check;
    Double lat,lon;
    ViewFlipper nomalcampFlipper,carFlipper,glampingFlipper,caravanFlipper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.convenience);
        setTitle("편의시설 선택");
        Intent get = getIntent();
        location = get.getStringExtra("location");
        kind = get.getStringExtra("kind");
        lat = get.getDoubleExtra("lat",0);
        lon = get.getDoubleExtra("lon",0);
        shower_check = findViewById(R.id.shower);
        sink_check = findViewById(R.id.sink);
        finish_btn = findViewById(R.id.finish);

        nomalcampFlipper = findViewById(R.id.nomalcampFlipper);
        glampingFlipper = findViewById(R.id.glampingFlipper);
        carFlipper = findViewById(R.id.carFlipper);
        caravanFlipper = findViewById(R.id.caravanFlipper);

        nomalcampFlipper.setVisibility(View.INVISIBLE);
        glampingFlipper.setVisibility(View.INVISIBLE);
        carFlipper.setVisibility(View.INVISIBLE);
        caravanFlipper.setVisibility(View.INVISIBLE);


        if(kind.equals("일반")){
            nomalcampFlipper.setVisibility(View.VISIBLE);
            nomalcampFlipper.startFlipping();
            nomalcampFlipper.setFlipInterval(3000);
        }else if(kind.equals("자동차")){
            carFlipper.setVisibility(View.VISIBLE);
            carFlipper.startFlipping();
            carFlipper.setFlipInterval(3000);
        }else if(kind.equals("글램핑")){
            glampingFlipper.setVisibility(View.VISIBLE);
            glampingFlipper.startFlipping();
            glampingFlipper.setFlipInterval(3000);
        }else if(kind.equals("카라반")){
            caravanFlipper.setVisibility(View.VISIBLE);
            caravanFlipper.startFlipping();
            caravanFlipper.setFlipInterval(3000);
        }

        shower_check.setOnCheckedChangeListener(checkedListener);
        sink_check.setOnCheckedChangeListener(checkedListener);
        finish_btn.setOnClickListener(finishListener);
    }
    CompoundButton.OnCheckedChangeListener checkedListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton checkbox, boolean checkid) {
            switch(checkbox.getId()){
                case R.id.shower:
                    if(checkid==true){
                        shower = "1";
                    }
                    else if(checkid==false){
                        shower = "0";
                    }
                    break;
                case R.id.sink:
                    if(checkid==true){
                        sink = "1";
                    }
                    else if(checkid==false){
                        sink = "0";
                    }
                    break;
            }
        }
    };

    View.OnClickListener finishListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ConvenienceActivity.this,SearchActivity.class);
            intent.putExtra("location",location);
            intent.putExtra("kind",kind);
            intent.putExtra("shower",shower);
            intent.putExtra("sink",sink);
            intent.putExtra("lat",lat);
            intent.putExtra("lon",lon);
            startActivity(intent);
        }
    };
}
