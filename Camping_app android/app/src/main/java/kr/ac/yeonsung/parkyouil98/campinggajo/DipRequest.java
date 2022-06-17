package kr.ac.yeonsung.parkyouil98.campinggajo;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DipRequest extends StringRequest {
    private final static String URL = "http://campinggajo.dothome.co.kr/dip_select.php";
    private Map<String,String> map;

    public DipRequest(String user_id, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("user_id",user_id);

    }
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

