package com.example.mst_news;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Explore extends AppCompatActivity {
    String intentfrom,intentfromName;
    public final List<Haberler> haberler = new ArrayList<Haberler>();
    private String TAG = Explore.class.getSimpleName();
    private ListView lv;
    String url;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        lv = findViewById(R.id.list);
        text = findViewById(R.id.textView);
        Intent urlFromIntent = getIntent();
        url = urlFromIntent.getStringExtra("baseurl");
        intentfromName = urlFromIntent.getStringExtra("name");
        text.setText(intentfromName);
        lv.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent newsDetails = new Intent(Explore.this,NewsDetails.class);
                newsDetails.putExtra("icerik",haberler.get(position).getIcerik());
                newsDetails.putExtra("baslik",haberler.get(position).getBaslik());
                newsDetails.putExtra("imageUrl",haberler.get(position).getImageUrl());
                newsDetails.putExtra("tarih",haberler.get(position).getTarih());
                newsDetails.putExtra("kaynak",haberler.get(position).getKaynak());
                newsDetails.putExtra("url",haberler.get(position).getUrl());
                startActivity(newsDetails);
            }
        });
        new GetContacts().execute();
    }

        public class GetContacts extends AsyncTask<Void, Void, Void> {
            @Override
            public void onPreExecute() {
                super.onPreExecute();
                Toast.makeText(Explore.this, "Haberler Yükleniyor...", Toast.LENGTH_SHORT).show();

            }

            @SuppressLint("WrongThread")
            @Override
            public Void doInBackground(Void... arg0) {
                HttpCall sh = new HttpCall();
                // Making a request to url and getting response
                String jsonStr = sh.makeServiceCall(url);

                Log.e(TAG, "Response from url: " + jsonStr);
                if (jsonStr != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(jsonStr);

                        // Getting JSON Array node
                        JSONArray contacts = jsonObj.getJSONArray("articles");

                        // looping through All Contacts
                        for (int i = 0; i < contacts.length(); i++) {
                            JSONObject c = contacts.getJSONObject(i);
                            String baslik = c.getString("title");
                            String icerik = c.getString("description");
                            String url_ = c.getString("url");
                            String imageUrl = c.getString("urlToImage");
                            String tarih = c.getString("publishedAt");

                            // Phone node is JSON Object
                            JSONObject source = c.getJSONObject("source");
                            String kaynak = source.getString("name");

                            haberler.add(new Haberler(baslik, url_, icerik, tarih, kaynak, imageUrl));
                        }
                    } catch (final JSONException e) {
                       /* Log.e(TAG, "Json parsing hatası: " + e.getMessage());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity().getApplicationContext(),
                                        "Json parsing hatası: " + e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        });*/
                    }
                } else {
                    /*Log.e(TAG, "Haberler servisine ulaşılamadı.");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Servise ulaşılamadı. İnternet bağlantınızı kontrol edin!",
                                    Toast.LENGTH_LONG).show();
                        }
                    });*/
                }

                return null;
            }

            @Override
            public void onPostExecute(Void result) {
                super.onPostExecute(result);
                CustomAdapter adapter = new CustomAdapter(Explore.this, haberler);
                lv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
}