package kr.ac.yeonsung.parkyouil98.campinggajo;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class KindActivity extends AppCompatActivity {
    String kind, location;
    Double lat, lon;
    Button regular_btn, car_btn, glamping_btn, caravan_btn;
    ImageView regularimg,carimg,glampingimg,caravanimg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kind);
        setTitle("종류 선택");
        Intent get = getIntent();
        location = get.getStringExtra("location");
        lat = get.getDoubleExtra("lat",0);
        lon = get.getDoubleExtra("lon",0);

        regularimg = findViewById(R.id.regularimg);
        carimg = findViewById(R.id.carimg);
        glampingimg = findViewById(R.id.glampingimg);
        caravanimg = findViewById(R.id.caravanimg);

        regularimg.setOnClickListener(kindimgListener);
        carimg.setOnClickListener(kindimgListener);
        glampingimg.setOnClickListener(kindimgListener);
        caravanimg.setOnClickListener(kindimgListener);

        regular_btn = findViewById(R.id.regular);
        car_btn = findViewById(R.id.car);
        glamping_btn = findViewById(R.id.glamping);
        caravan_btn = findViewById(R.id.caravan);

        regular_btn.setOnClickListener(kindListener);
        car_btn.setOnClickListener(kindListener);
        glamping_btn.setOnClickListener(kindListener);
        caravan_btn.setOnClickListener(kindListener);
    }
    View.OnClickListener kindimgListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.regularimg:
                    kind = "일반";
                    break;
                case R.id.carimg:
                    kind = "자동차";
                    break;
                case R.id.glampingimg:
                    kind = "글램핑";
                    break;
                case R.id.caravanimg:
                    kind = "카라반";
                    break;
            }
            Intent intent = new Intent(KindActivity.this,ConvenienceActivity.class);
            intent.putExtra("location",location);
            intent.putExtra("kind",kind);
            intent.putExtra("lat",lat);
            intent.putExtra("lon",lon);
            startActivity(intent);
        }
    };
    View.OnClickListener kindListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button choice2 = findViewById(view.getId());
            kind = choice2.getText().toString();
            Intent intent = new Intent(KindActivity.this,ConvenienceActivity.class);
            intent.putExtra("location",location);
            intent.putExtra("kind",kind);
            intent.putExtra("lat",lat);
            intent.putExtra("lon",lon);
            startActivity(intent);
        }
    };
}
