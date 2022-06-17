package kr.ac.yeonsung.parkyouil98.campinggajo;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private final static String URL = "http://campinggajo.dothome.co.kr/camping_register_member.php";
    private Map<String, String> map;

    public RegisterRequest(String user_id, String user_password, String user_name,String user_number, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST,URL, listener, errorListener);

        map = new HashMap<>();
        map.put("user_id",user_id);
        map.put("user_password",user_password);
        map.put("user_name",user_name);
        map.put("user_number",user_number);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

