package kr.ac.yeonsung.parkyouil98.campinggajo;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SurrondRequest extends StringRequest {
    final static private String URL = "http://campinggajo.dothome.co.kr/surrond.php";
    public SurrondRequest(Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
    }
}