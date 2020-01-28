package com.example.mst_news;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class BookmarkFragment extends Fragment {
    ListView lv;
    Button button;
    public List<Haberler> haberler = new ArrayList<Haberler>();
    CustomAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bookmarkfragment, container, false);
        button = (Button) view.findViewById(R.id.Button);
        lv = (ListView) view.findViewById(R.id.list);
        SqLite db = new SqLite(getActivity().getApplicationContext());
        haberler = db.HaberListele();
        adapter = new CustomAdapter(getActivity(), haberler);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent newsDetails = new Intent(getActivity().getApplicationContext(),NewsDetailsBookmarks.class);
                newsDetails.putExtra("id",position);
                newsDetails.putExtra("icerik",haberler.get(position).getIcerik());
                newsDetails.putExtra("baslik",haberler.get(position).getBaslik());
                newsDetails.putExtra("imageUrl",haberler.get(position).getImageUrl());
                newsDetails.putExtra("tarih",haberler.get(position).getTarih());
                newsDetails.putExtra("kaynak",haberler.get(position).getKaynak());
                newsDetails.putExtra("url",haberler.get(position).getUrl());
                startActivity(newsDetails);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqLite db = new SqLite(getActivity().getApplicationContext());
                db.Sil();
                haberler.clear();
                lv.setAdapter(null);
                adapter.notifyDataSetChanged();
            }
        });
        return view;

    }


}
