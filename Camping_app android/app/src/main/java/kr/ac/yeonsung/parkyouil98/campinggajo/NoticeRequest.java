package kr.ac.yeonsung.parkyouil98.campinggajo;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class NoticeRequest extends StringRequest {
    final static private String URL = "http://campinggajo.dothome.co.kr/notice_select.php";
    public NoticeRequest(Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
    }
}
