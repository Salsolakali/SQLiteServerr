package com.example.fernana6.sqliteserverr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity  implements Response.Listener<String>, Response.ErrorListener{

    private EditText email, password;
    public String contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.mailLoggin);
        password = findViewById(R.id.passwordLoggin);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.registrarBtn:
                Intent registry = new Intent(MainActivity.this, RegistryActivity.class);
                startActivity(registry);
                break;
            case R.id.loggearBtn:
                //Intent show = new Intent(MainActivity.this, ShowUserActivity.class);
                //startActivity(show);
                contra = password.getText().toString();
                checkUser(contra);
                break;
        }
    }

    public void checkUser(final String contra){
        StringRequest sr = new StringRequest(Request.Method.POST, "http://192.168.1.59/ejemplo/login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray jArray = object.getJSONArray("users");

                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jUser = jArray.getJSONObject(i);
                        //Log.d("My App", jUser.getString("name"));
                        Log.d("password",jUser.getString("password"));
                        Log.d("email",jUser.getString("email"));
                        //email.setText(jUser.getString("email"));
                        //name.setText(jUser.getString("name"));
                        //password.setText(jUser.getString("password"));
                        //Log.d("pwInput", password.getText().toString());
                        if(contra.equals(jUser.getString("password"))){
                            Toast.makeText(MainActivity.this,"Loggin correcto",Toast.LENGTH_SHORT).show();
                            Intent loggeado = new Intent(MainActivity.this, IndexActivity.class);
                            startActivity(loggeado);
                        }
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
                params.put("email", email.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(MainActivity.this).add(sr);

    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {

    }
}
