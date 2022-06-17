package kr.ac.yeonsung.parkyouil98.campinggajo;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class InquiryRequest extends StringRequest {
    private final static String URL = "http://campinggajo.dothome.co.kr/inquiry.php";
    private Map<String,String> map;

    public InquiryRequest(String id,String title, String content , Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("id",id);
        map.put("title",title);
        map.put("content",content);
    }
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
