package com.example.mst_news;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SearchFragment extends Fragment {
    public final List<Haberler> haberler = new ArrayList<Haberler>();
    private String TAG = SearchFragment.class.getSimpleName();
    private ListView lv;
    public EditText editText;
    public TextView textView;
    public boolean clicker = false;
    CustomAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searchfragment, container, false);
        lv = (ListView) view.findViewById(R.id.list);
        editText = (EditText) view.findViewById(R.id.etSearch);
        textView = (TextView) view.findViewById(R.id.txtView);

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
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN)&& keyCode == KeyEvent.KEYCODE_ENTER )
                {
                    new SearchFragment.GetContacts().execute();
                }
                String tmp = editText.getText().toString();
                textView.setText("Dünya Genelinden Güncel Haberler\n"+"Arama Kelimesi:"+ tmp);
                clicker = true;
                return true;
            }
        });
        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (clicker = true)
                {
                    editText.setOnKeyListener(new View.OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                            if((event.getAction() == KeyEvent.ACTION_DOWN)&& keyCode == KeyEvent.KEYCODE_ENTER )
                            {
                                lv.setAdapter(null);
                                haberler.clear();
                                new SearchFragment.GetContacts().execute();
                            }
                            String tmp = editText.getText().toString();
                            textView.setText("Dünya Genelinden Güncel Haberler\n"+"Arama Kelimesi:"+ tmp);
                            clicker = true;
                            return true;
                        }
                    });
                }
            }
        });
        return view;
    }


    public class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        public void onPreExecute() {
            super.onPreExecute();
            //Toast.makeText(getActivity().getApplicationContext(), "Arama sonucu yükleniyor...", Toast.LENGTH_SHORT).show();
        }

        @SuppressLint("WrongThread")
        @Override
        public Void doInBackground(Void... arg0) {
            HttpCall sh = new HttpCall();
            // Making a request to url and getting response
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -1);
            String date = dateFormat.format(cal.getTime());

            String aranan = editText.getText().toString();
            String url = "https://newsapi.org/v2/everything?q=" + aranan +"&from="+ date + "&to= "+ date +"&sortBy=popularity&apiKey=ecbb330402f1479f9c9dbcb22921c211";
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
            adapter = new CustomAdapter(getActivity(), haberler);
            lv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}

