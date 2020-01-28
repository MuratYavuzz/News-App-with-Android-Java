package com.example.mst_news;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public final List<Haberler> haberler = new ArrayList<Haberler>();
    private String TAG = HomeFragment.class.getSimpleName();
    private ListView lv;
    String icerik,baslik,url_;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, container, false);
        lv = (ListView) view.findViewById(R.id.list);
        lv.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent newsDetails = new Intent(getActivity().getApplicationContext(),NewsDetails.class);
                newsDetails.putExtra("icerik",haberler.get(position).getIcerik());
                newsDetails.putExtra("baslik",haberler.get(position).getBaslik());
                newsDetails.putExtra("imageUrl",haberler.get(position).getImageUrl());
                newsDetails.putExtra("tarih",haberler.get(position).getTarih());
                newsDetails.putExtra("kaynak",haberler.get(position).getKaynak());
                newsDetails.putExtra("url",haberler.get(position).getUrl());
                startActivity(newsDetails);
            }
        });

        new HomeFragment.GetContacts().execute();

        return view;
    }

    public class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        public void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getActivity().getApplicationContext(), "Haberler Yükleniyor...", Toast.LENGTH_SHORT).show();

        }

        @SuppressLint("WrongThread")
        @Override
        public Void doInBackground(Void... arg0) {
            HttpCall sh = new HttpCall();
            // Making a request to url and getting response
            String url = "https://newsapi.org/v2/top-headlines?country=tr&apiKey=ecbb330402f1479f9c9dbcb22921c211";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray contacts = jsonObj.getJSONArray("articles");

                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);
                        baslik = c.getString("title");
                        icerik = c.getString("description");
                        url_ = c.getString("url");
                        String imageUrl = c.getString("urlToImage");
                        String tarih = c.getString("publishedAt");

                        JSONObject source = c.getJSONObject("source");
                        String kaynak = source.getString("name");
                        haberler.add(new Haberler(baslik, url_, icerik, tarih, kaynak, imageUrl));

                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing hatası: " + e.getMessage());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity().getApplicationContext(),
                                    "Json parsing hatası: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Haberler servisine ulaşılamadı.");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity().getApplicationContext(),
                                "Servise ulaşılamadı. İnternet bağlantınızı kontrol edin!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        public void onPostExecute(Void result) {
            super.onPostExecute(result);
            CustomAdapter adapter = new CustomAdapter(getActivity(), haberler);
            lv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}

