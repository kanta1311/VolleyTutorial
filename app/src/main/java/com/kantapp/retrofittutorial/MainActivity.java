package com.kantapp.retrofittutorial;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RequestQueue queue;
    ArrayList<Movie> postList = new ArrayList<Movie>();
    MovieAdapter adapter;
    Context mContext;
    String a,b;
    ProgressBar simpleProgressBar;
    int c;
    Sqlite sqlite;
    public static String url = "http://gomcineplex.com/data/anime/sd_android.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlite = new Sqlite(getApplicationContext());
    //    postList = (ArrayList<Movie>) sqlite.getAllContacts();
        mContext = getApplicationContext();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        simpleProgressBar = findViewById(R.id.simpleProgressBar);
        simpleProgressBar.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(layoutManager);
        //ArrayList contact = prepareData();
       adapter = new MovieAdapter(getApplicationContext(), postList);
        recyclerView.setAdapter(adapter);
        queue = Volley.newRequestQueue(this);
      //  RequestQueue rq = Volley.newRequestQueue(this);

        //Volley's inbuilt class to make Json array request
         JsonArrayRequest newsReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
              Toast.makeText(mContext, "aaaa" + response, Toast.LENGTH_LONG).show();
                simpleProgressBar.setVisibility(View.GONE);
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                         a = obj.getString("Title");
                         b = obj.getString("Genres");
                   //      c = obj.getInt("age");
                        Movie feeds = new Movie();
                        feeds.setFirstname(a);
                        feeds.setLastname(b);
                        feeds.setage(String.valueOf(c));
                  //      Toast.makeText(mContext, "" + a + b, Toast.LENGTH_SHORT).show();
                        postList.add(feeds);
                        adapter = new MovieAdapter(getApplicationContext(), postList);
                        recyclerView.setAdapter(adapter);
                        sqlite.addPeople(new Movie(c,a,b));
                        Log.d("aaaaa",  a +b);
                        // adding movie to movies array
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    } finally {
                        //Notify adapter about data changes
                        adapter.notifyItemChanged(i);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        //Adding JsonArrayRequest to Request Queue
        queue.add(newsReq);
    }
}
