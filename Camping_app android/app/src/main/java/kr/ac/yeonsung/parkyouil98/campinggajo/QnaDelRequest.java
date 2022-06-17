package kr.ac.yeonsung.parkyouil98.campinggajo;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class QnaDelRequest extends StringRequest {
    private final static String URL = "http://campinggajo.dothome.co.kr/qna_del.php";
    private Map<String,String> map;

    public QnaDelRequest(String num, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, listener, errorListener);

        map = new HashMap<>();
        map.put("num",num);

    }
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}
