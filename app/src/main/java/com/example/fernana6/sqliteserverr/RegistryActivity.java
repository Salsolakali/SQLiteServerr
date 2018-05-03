package com.example.fernana6.sqliteserverr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegistryActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener{

    EditText email, name, password;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);

        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);

        register = findViewById(R.id.registrar);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest sr = new StringRequest(Request.Method.POST, "http://192.168.1.59/ejemplo/registro.php", new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d("Registry", response);
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Registry", error.getMessage());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", email.getText().toString());
                        params.put("name", name.getText().toString());
                        params.put("password", password.getText().toString());

                        return params;
                    }
                };
                Volley.newRequestQueue(RegistryActivity.this).add(sr);
            }

        });
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onResponse(String response) {

    }
}


