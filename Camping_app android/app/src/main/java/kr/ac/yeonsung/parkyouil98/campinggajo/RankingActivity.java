package kr.ac.yeonsung.parkyouil98.campinggajo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RankingActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Camp> camps = new ArrayList<Camp>();
    int[] imageNum = {R.drawable.camp1,R.drawable.camp2,R.drawable.camp3,R.drawable.camp4,R.drawable.camp5,
            R.drawable.camp6,R.drawable.camp7,R.drawable.camp8,R.drawable.camp9,R.drawable.camp10};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);
        setTitle("추천");
        listView = findViewById(R.id.listview);
        Response.Listener<String> resposneListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    int length = jsonObject.length();
                    for (int i = 0; i < length - 1; i++) {
                        JSONObject test = jsonObject.getJSONObject(Integer.toString(i));
                        String name = test.getString("name");
                        int rank_no =i+1;
                        int num = test.getInt("num");
                        String address = test.getString("address");
                        String camping_kind = test.getString("camping_kind");
                        String camping_shower = test.getString("shower");
                        String camping_sink = test.getString("sink");
                        String etc = test.getString("etc");
                        int imagenum = 0;
                        if(camping_shower.equals("1"))
                            camping_shower="있음";
                        else
                            camping_shower="없음";
                        if(camping_sink.equals("1"))
                            camping_sink="있음";
                        else
                            camping_sink="없음";
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
                        camps.add(new Camp(imagenum,rank_no,name, camping_kind,address,camping_shower,camping_sink,etc,num));
                    }
                    CustomCampsAdapter adapter = new CustomCampsAdapter(RankingActivity.this, camps);

                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            Camp camp = (Camp) adapter.getItem(position);
                            Intent intent = new Intent(RankingActivity.this, PopupActivity.class);
                            intent.putExtra("name", camp.getName());
                            intent.putExtra("kind",camp.getKind());
                            intent.putExtra("address",camp.getAddress());
                            intent.putExtra("shower",camp.getShower());
                            intent.putExtra("sink",camp.getSink());
                            intent.putExtra("etc",camp.getEtc());
                            intent.putExtra("num",camp.getNum());
                            startActivity(intent);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        RankingRequest rankingRequest = new RankingRequest(resposneListener);
        rankingRequest.setShouldCache(false);
        RequestQueue queue = Volley.newRequestQueue(RankingActivity.this);
        queue.add(rankingRequest);
    }
    public class Camp {
        private int rank_no;
        private int num;
        private String name;
        private String kind;
        private String address;
        private String shower;
        private String sink;
        private String etc;
        private int imagenum;
        public Camp(int imagenum,int rank_no, String name, String kind,String address, String shower, String sink, String etc, int num) {
            this.name = name;
            this.kind = kind;
            this.address = address;
            this.shower = shower;
            this.sink = sink;
            this.etc = etc;
            this.num = num;
            this.imagenum = imagenum;
            this.rank_no = rank_no;
        }
        public String getName(){
            return name;
        }
        public String getKind(){
            return kind;
        }
        public String getAddress(){
            return address;
        }
        public String getShower(){
            return shower;
        }
        public String getSink(){
            return sink;
        }
        public String getEtc(){
            return etc;
        }
        public int getNum(){
            return num;
        }
        public int getImagenum(){return imagenum;}
        public int getRank_no(){return rank_no;}
    }

    public class CustomCampsAdapter extends ArrayAdapter {
        public CustomCampsAdapter(Context context, ArrayList camps) {
            super(context, 0, camps);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.rankinglist, parent, false);
            }

            Camp camp = (Camp) getItem(position);
            ImageView listimage = (ImageView) convertView.findViewById(R.id.list_image);
            TextView listName = (TextView) convertView.findViewById(R.id.list_Name);
            TextView listKind = (TextView) convertView.findViewById(R.id.list_kind);
            listimage.setImageResource(camp.getImagenum());
            listName.setText(camp.getRank_no()+"위 "+camp.getName());
            listKind.setText(camp.getKind());

            return convertView;
        }
    }
}
