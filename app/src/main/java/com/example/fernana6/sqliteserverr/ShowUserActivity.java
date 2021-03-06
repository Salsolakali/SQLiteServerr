package com.example.fernana6.sqliteserverr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ShowUserActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener {

    EditText idInput;
    TextView email, name, password;
    Button show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user);

        email = findViewById(R.id.emailShow);
        name = findViewById(R.id.nameShow);
        password = findViewById(R.id.passwordShow);

        idInput = findViewById(R.id.IdInsertShow);

        show = findViewById(R.id.showBTN);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest sr = new StringRequest(Request.Method.POST, "http://192.168.1.59/ejemplo/select.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONArray jArray = object.getJSONArray("users");
                            for (int i = 0; i < jArray.length(); i++) {
                                JSONObject jUser = jArray.getJSONObject(i);
                                Log.d("My App", jUser.getString("name"));
                                email.setText(jUser.getString("email"));
                                name.setText(jUser.getString("name"));
                                password.setText(jUser.getString("password"));
                            }
                        } catch (Throwable t) {
                            Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                        }
                        Log.d("show", response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("show", error.getMessage());
                    }

                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("id", idInput.getText().toString());
                        return params;
                    }
                };
                Volley.newRequestQueue(ShowUserActivity.this).add(sr);
            }
        });
    }
    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {

    }

}

