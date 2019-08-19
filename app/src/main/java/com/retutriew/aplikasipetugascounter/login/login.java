package com.retutriew.aplikasipetugascounter.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.retutriew.aplikasipetugascounter.R;
import com.retutriew.aplikasipetugascounter.pilihan.pilihan;
import com.retutriew.aplikasipetugascounter.sessionManager.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    private EditText username, password;
    private Button login_submit;
    private ProgressBar loading;
    private static String URL_LOGIN="http://pulsa.cikarastudio.com/Android/login.php";
    SessionManager sessionmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        sessionmanager = new SessionManager(this);

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        loading = findViewById(R.id.loading);
        login_submit = findViewById(R.id.login_submit);

        login_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mUsername= username.getText().toString().trim();
                String mPassword = password.getText().toString().trim();

                if(!mUsername.isEmpty() & !mPassword.isEmpty()){
                Login(mUsername,mPassword);
                }
                else {
                    username.setError("Masukkan Username");
                    password.setError("Masukkan Password");
                }
            }
        });
    }


    private void Login(final String username, final String password){
        loading.setVisibility(View.VISIBLE);
        login_submit.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")){
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object= jsonArray.getJSONObject(i);

                                    String name = object.getString("name").trim();
                                    String username = object.getString("username").trim();

                                    sessionmanager.createSession(name,username);

                                    Toast.makeText(login.this,
                                            "Login Berhasil.\n Username : "
                                                    +username+"\n Nama : "
                                                    +name, Toast.LENGTH_SHORT)
                                            .show();

                                    Intent intent = new Intent(login.this, pilihan.class);
                                    intent.putExtra("name",name);
                                    intent.putExtra("username",username);
                                    startActivity(intent);
                                    finish();

                                    loading.setVisibility(View.GONE);

                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.setVisibility(View.GONE);
                            login_submit.setVisibility(View.VISIBLE);
                            Toast.makeText(login.this, "e"+e.toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.setVisibility(View.GONE);
                        login_submit.setVisibility(View.VISIBLE);
                        Toast.makeText(login.this,"error"+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
