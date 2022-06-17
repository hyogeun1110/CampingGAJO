package kr.ac.yeonsung.parkyouil98.campinggajo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;
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

public class SurrondActivity extends AppCompatActivity {
    MapView mapView;
    RelativeLayout mapViewContainer;
    AlertDialog dialog1,dialog2;
    int ladius;
    Double main_lat,main_lon;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surrond);
        setTitle("주변");
        Button ten = findViewById(R.id.ten);
        Button fifteen = findViewById(R.id.fifteen);
        Button twenty = findViewById(R.id.twenty);
        Button thirty = findViewById(R.id.thirty);
        ten.setOnClickListener(btnListener);
        fifteen.setOnClickListener(btnListener);
        twenty.setOnClickListener(btnListener);
        thirty.setOnClickListener(btnListener);
        mapViewContainer = findViewById(R.id.map_view);
        mapView = new MapView(this);
        mapView.setZoomLevel(7, true);
        mapView.setCalloutBalloonAdapter(new CustomCalloutBalloonAdapter());
        mapView.setPOIItemEventListener(MarkerListener);
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
        mapView.setCurrentLocationEventListener(mCurrentLocationEventListener);
        mapViewContainer.addView(mapView);
    }
    MapView.CurrentLocationEventListener mCurrentLocationEventListener = new MapView.CurrentLocationEventListener() {
        @Override
        public void onCurrentLocationUpdate(MapView mapView, MapPoint mapPoint, float v) {
            Intent get = getIntent();
            ladius = get.getIntExtra("new_ladius",10000);
            MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
            main_lat = mapPointGeo.latitude;
            main_lon = mapPointGeo.longitude;
            Response.Listener<String> resposneListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");
                        int length = jsonObject.length();
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
                                if(calcDistance(main_lat,main_lon,lat,lon)<ladius){
                                    System.out.println(calcDistance(main_lat,main_lon,lat,lon));
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
                        }
                        else{
                            AlertDialog.Builder noresult = new AlertDialog.Builder(SurrondActivity.this);
                            dialog1 = noresult.setMessage("결과가 없습니다.").setNegativeButton("확인", null).create();
                            dialog1.show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        AlertDialog.Builder builder = new AlertDialog.Builder(SurrondActivity.this);
                        dialog2 = builder.setMessage("에러발생.").setNegativeButton("확인", null).create();
                        dialog2.show();
                    }
                }
            };
            SurrondRequest surrondRequest = new SurrondRequest(resposneListener);
            surrondRequest.setShouldCache(false);
            RequestQueue queue = Volley.newRequestQueue(SurrondActivity.this);
            queue.add(surrondRequest);
        }

        @Override
        public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

        }

        @Override
        public void onCurrentLocationUpdateFailed(MapView mapView) {

        }

        @Override
        public void onCurrentLocationUpdateCancelled(MapView mapView) {

        }
    };

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int new_ladius=0;
            switch (view.getId()){
                case R.id.ten:
                    new_ladius=10000;
                    break;
                case R.id.fifteen:
                    new_ladius=15000;
                    break;
                case R.id.twenty:
                    new_ladius=20000;
                    break;
                case R.id.thirty:
                    new_ladius=30000;
                    break;
            }
            mapViewContainer.removeAllViews();
            finish();
            overridePendingTransition(0, 0);
            Intent intent = new Intent(SurrondActivity.this,SurrondActivity.class);
            intent.putExtra("new_ladius",new_ladius);
            startActivity(intent);
            overridePendingTransition(0, 0);
        }
    };


    public static Double calcDistance(double lat1, double lon1, double lat2, double lon2){
        double EARTH_R, Rad, radLat1, radLat2, radDist;
        double distance, ret;

        EARTH_R = 6371000.0;
        Rad = Math.PI/180;
        radLat1 = Rad * lat1;
        radLat2 = Rad * lat2;
        radDist = Rad * (lon1 - lon2);

        distance = Math.sin(radLat1) * Math.sin(radLat2);
        distance = distance + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radDist);
        ret = EARTH_R * Math.acos(distance);

        Double result = ret;

        return result;
    }
    class CustomCalloutBalloonAdapter implements CalloutBalloonAdapter {
        View mCalloutBalloon;

        public CustomCalloutBalloonAdapter() {
            mCalloutBalloon = getLayoutInflater().inflate(R.layout.custom_callout_balloon, null);
        }

        @Override
        public View getCalloutBalloon(MapPOIItem poiItem) {
            String[] array = poiItem.getItemName().split("%");
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
            Intent intent = new Intent(SurrondActivity.this, PopupActivity.class);
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
}
