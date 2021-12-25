package com.example.api_prac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RequestQueue requestQueue;

    private List<Movie_Model> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = volleySingletonclass.getmInstance(this).getRequestQueue();

        movieList = new ArrayList<>();

        fetchMovie();
    }

    private void fetchMovie() {
        String url = "https://mocki.io/v1/588be1a7-9c66-4af1-bcc0-63fa249e5b9f";

        //jsonwork
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String title = jsonObject.getString("title");
                        String overview = jsonObject.getString("overview");
                        String poster = jsonObject.getString("poster");
                        Double rating = jsonObject.getDouble("rating");

                        Movie_Model movie = new Movie_Model(title, poster, overview, rating);
                        movieList.add(movie);


                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, "Catch method", Toast.LENGTH_SHORT).show();

                        e.printStackTrace();
                    }

                    Movie_Adapter adapter = new Movie_Adapter(MainActivity.this, movieList);
                    recyclerView.setAdapter(adapter);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonArrayRequest);

    }

}