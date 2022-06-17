package kr.ac.yeonsung.parkyouil98.campinggajo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import net.daum.mf.map.api.CalloutBalloonAdapter;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapPolyline;
import net.daum.mf.map.api.MapView;

import org.json.JSONException;
import org.json.JSONObject;

public class SearchActivity extends AppCompatActivity {
    String location,kind,shower,sink;
    Double first_lat,first_lon;
    Button zoomin,zoomout,first_btn;
    TextView count;
    private AlertDialog dialog1,dailog2;
    MapView mapView;
    RelativeLayout mapViewContainer;
    int[] imageNum = {R.drawable.camp1,R.drawable.camp2,R.drawable.camp3,R.drawable.camp4,R.drawable.camp5,
            R.drawable.camp6,R.drawable.camp7,R.drawable.camp8,R.drawable.camp9,R.drawable.camp10};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_list);
        setTitle("검색 결과");
        Intent get = getIntent();
        location = get.getStringExtra("location");
        kind = get.getStringExtra("kind");
        shower = get.getStringExtra("shower");
        sink = get.getStringExtra("sink");
        first_lat = get.getDoubleExtra("lat",0);
        first_lon = get.getDoubleExtra("lon",0);
        mapViewContainer = findViewById(R.id.map_view);
        mapView = new MapView(this);
        mapViewContainer.addView(mapView);
        mapView.setCalloutBalloonAdapter(new CustomCalloutBalloonAdapter());
        mapView.setPOIItemEventListener(MarkerListener);
        if(location.equals("all")) {
            mapView.setZoomLevel(11, true);
        }
        else {
            mapView.setZoomLevel(10, true);
        }
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(first_lat, first_lon), true);
        zoomin = findViewById(R.id.zoomin);
        zoomout = findViewById(R.id.zoomout);
        count = findViewById(R.id.count);
        first_btn = findViewById(R.id.first);
        zoomin.setOnClickListener(zoomListener);
        zoomout.setOnClickListener(zoomListener);
        first_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
        Response.Listener<String> resposneListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    int length = jsonObject.length();
                    int length2=length-1;
                    count.setText("검색 결과 개수 : "+length2);
                    if(success.equals("1")){
                        for (int i = 0; i<length-1; i++){
                            JSONObject test = jsonObject.getJSONObject(Integer.toString(i));
                            String name=test.getString("name");
                            double lat = test.getDouble("lat");
                            double lon = test.getDouble("lon");
                            int num = test.getInt("num");
                            String address = test.getString("address");
                            String camping_kind = test.getString("camping_kind");
                            String camping_shower = test.getString("shower");
                            String camping_sink = test.getString("sink");
                            String etc = test.getString("etc");
                            if(camping_shower.equals("1"))
                                camping_shower="있음";
                            else
                                camping_shower="없음";
                            if(camping_sink.equals("1"))
                                camping_sink="있음";
                            else
                                camping_sink="없음";
                            String result = name+"%"+camping_kind+"%"+address+"%"+camping_shower+"%"+camping_sink+"%"+etc+"%"+num;
                            MapPOIItem marker = new MapPOIItem();
                            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(lat, lon);
                            marker.setItemName(result);
                            marker.setTag(0);
                            marker.setMapPoint(mapPoint);
                            marker.setCustomImageAnchor(0.5f, 1.0f);
                            marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
                            marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
                            mapView.addPOIItem(marker);
                        }
                    }
                    else{
                        AlertDialog.Builder noresult = new AlertDialog.Builder(SearchActivity.this);
                        dialog1 = noresult.setMessage("결과가 없습니다.").setNegativeButton("확인", null).create();
                        dialog1.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);
                    dailog2 = builder.setMessage("에러발생.").setNegativeButton("확인", null).create();
                    dailog2.show();
                }
            }
        };
        SearchRequest searchRequest = new SearchRequest(location,kind,shower,sink, resposneListener);
        searchRequest.setShouldCache(false);
        RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
        queue.add(searchRequest);
    }
    class CustomCalloutBalloonAdapter implements CalloutBalloonAdapter {
        View mCalloutBalloon;

        public CustomCalloutBalloonAdapter() {
            mCalloutBalloon = getLayoutInflater().inflate(R.layout.custom_callout_balloon, null);
        }

        @Override
        public View getCalloutBalloon(MapPOIItem poiItem) {
            String[] array = poiItem.getItemName().split("%");
            int num = Integer.parseInt(array[6]);
            int imagenum=0;
            switch (num % 10){
                case 0:
                    imagenum = imageNum[0];
                    break;
                case 1:
                    imagenum = imageNum[1];
                    break;
                case 2:
                    imagenum = imageNum[2];
                    break;
                case 3:
                    imagenum = imageNum[3];
                    break;
                case 4:
                    imagenum = imageNum[4];
                    break;
                case 5:
                    imagenum = imageNum[5];
                    break;
                case 6:
                    imagenum = imageNum[6];
                    break;
                case 7:
                    imagenum = imageNum[7];
                    break;
                case 8:
                    imagenum = imageNum[8];
                    break;
                case 9:
                    imagenum = imageNum[9];
                    break;
            }
            ((ImageView) mCalloutBalloon.findViewById(R.id.badge)).setImageResource(imagenum);
            ((TextView) mCalloutBalloon.findViewById(R.id.title)).setText(array[0]);
            ((TextView) mCalloutBalloon.findViewById(R.id.camping_kind)).setText("종류 : "+array[1]);
            ((TextView) mCalloutBalloon.findViewById(R.id.address)).setText("주소 : "+array[2]);
            return mCalloutBalloon;
        }

        @Override
        public View getPressedCalloutBalloon(MapPOIItem poiItem) {
            return null;
        }
    }
    MapView.POIItemEventListener MarkerListener = new MapView.POIItemEventListener() {
        @Override
        public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {

        }

        @Override
        public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

        }

        @Override
        public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
            String[] list = mapPOIItem.getItemName().split("%");
            String name = list[0];
            String kind = list[1];
            String address = list[2];
            String shower = list[3];
            String sink = list[4];
            String etc = list[5];
            int num = Integer.parseInt(list[6]);
            Intent intent = new Intent(SearchActivity.this, PopupActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("kind",kind);
            intent.putExtra("address",address);
            intent.putExtra("shower",shower);
            intent.putExtra("sink",sink);
            intent.putExtra("etc",etc);
            intent.putExtra("num",num);
            startActivity(intent);
        }

        @Override
        public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

        }
    };
    View.OnClickListener zoomListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.zoomin:
                    mapView.zoomIn(true);
                    break;
                case R.id.zoomout:
                    mapView.zoomOut(true);
            }
        }
    };
}
