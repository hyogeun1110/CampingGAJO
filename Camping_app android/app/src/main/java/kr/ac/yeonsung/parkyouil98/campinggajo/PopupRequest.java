package kr.ac.yeonsung.parkyouil98.campinggajo;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PopupRequest extends StringRequest {
    final static private String URL = "http://campinggajo.dothome.co.kr/dipInsert.php";
    private Map<String, String> map;

    public PopupRequest(String id, String name,String choice, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("choice", choice);
    }
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
