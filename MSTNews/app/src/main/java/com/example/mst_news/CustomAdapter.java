package com.example.mst_news;

import android.content.Context;
import android.view.LayoutInflater;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class CustomAdapter extends BaseAdapter {
    public LayoutInflater userInflater;
    public List<Haberler> haberList;

    public CustomAdapter(Activity activity, List<Haberler> haberList) {
        userInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.haberList = haberList;
    }

    @Override
    public int getCount() {
        return haberList.size();
    }

    @Override
    public Object getItem(int i) {
        return haberList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View lineView;
        lineView = userInflater.inflate(R.layout.list_item, null);
        TextView textViewbaslik = (TextView) lineView.findViewById(R.id.baslik);
        //TextView textViewicerik = (TextView) lineView.findViewById(R.id.icerik);
        TextView textViewkaynak = (TextView) lineView.findViewById(R.id.kaynak);
        TextView textViewtarih = (TextView) lineView.findViewById(R.id.tarih);
        ImageView imgView = (ImageView) lineView.findViewById(R.id.imageView);


        Haberler haber = haberList.get(i);
        String currentBaslik = haber.getBaslik();
        String [] seperatedBaslik = currentBaslik.split("-");
        textViewbaslik.setText(seperatedBaslik[0]);
        String currentTarih = haber.getTarih();
        String [] seperatedTarih = currentTarih.split("T");
        String [] seperate = seperatedTarih[1].split("Z");
        String [] seperatedTarih2 = seperatedTarih[0].split("-");
        String [] seperate2 = seperate[0].split(":");
        String TarihSaat = seperatedTarih2[2] + "." + seperatedTarih2[1] + "." + seperatedTarih2[0] + "  " + seperate2[0]+ ":" + seperate2[1];
        textViewtarih.setText(TarihSaat);
        String currentKaynak = haber.getKaynak();
        String [] seperatedKaynak = currentKaynak.split("\\.");
        textViewkaynak.setText(seperatedKaynak[0]);
        //textViewicerik.setText(haber.getIcerik());
        Picasso.get()
                .load(haber.getImageUrl())
                .fit()
                .centerCrop()
                .into(imgView);


        return lineView;
    }
}
