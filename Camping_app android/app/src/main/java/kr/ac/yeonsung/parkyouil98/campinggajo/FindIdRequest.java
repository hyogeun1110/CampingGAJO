package kr.ac.yeonsung.parkyouil98.campinggajo;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;


import java.util.HashMap;
import java.util.Map;

public class FindIdRequest extends StringRequest {
    private final static String URL = "http://campinggajo.dothome.co.kr/find_id.php";
    private Map<String,String> map;

    public FindIdRequest(String user_number, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("user_number",user_number);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
