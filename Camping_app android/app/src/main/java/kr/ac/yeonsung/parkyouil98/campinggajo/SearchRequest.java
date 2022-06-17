package kr.ac.yeonsung.parkyouil98.campinggajo;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SearchRequest extends StringRequest {
    final static private String URL = "http://campinggajo.dothome.co.kr/selectTable.php";
    private Map<String, String> map;

    public SearchRequest(String location, String kind, String shower, String sink , Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("location", location);
        map.put("kind", kind);
        map.put("shower",shower);
        map.put("sink",sink);
    }
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}