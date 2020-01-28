package com.example.mst_news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetailsBookmarks extends AppCompatActivity {
    TextView textViewIcerik,textViewBaslik,textViewTarih,textViewAd;
    ImageView image;
    int id;
    String baslik,icerik,tarih,ad,imageUrl,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details_bookmarks);
        textViewBaslik = findViewById(R.id.textViewBaslik);
        textViewTarih = findViewById(R.id.textViewTarih);
        textViewIcerik = findViewById(R.id.textViewIcerik);
        textViewAd = findViewById(R.id.textViewAd);
        image = findViewById(R.id.imageView);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        baslik=intent.getStringExtra("baslik");
        icerik=intent.getStringExtra("icerik");
        tarih = intent.getStringExtra("tarih");
        url = intent.getStringExtra("url");
        imageUrl = intent.getStringExtra("imageUrl");
        ad = intent.getStringExtra("kaynak");
        String [] seperatedKaynak = ad.split("\\.");
        String [] seperatedBaslik = baslik.split("-");
        String [] seperatedTarih = tarih.split("T");
        String [] seperate = seperatedTarih[1].split("Z");
        String [] seperatedTarih2 = seperatedTarih[0].split("-");
        String [] seperate2 = seperate[0].split(":");
        String TarihSaat = seperatedTarih2[2] + "." + seperatedTarih2[1] + "." + seperatedTarih2[0] + "\n" + "    "+  seperate2[0]+ ":" + seperate2[1];
        Picasso.get()
                .load(imageUrl)
                .fit()
                .into(image);
        textViewIcerik.setText(icerik);
        textViewTarih.setText(TarihSaat);
        textViewBaslik.setText(seperatedBaslik[0]);
        textViewAd.setText(seperatedKaynak[0]);


    }

    public void HabereGit(View view) {
        Intent haber_url = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(haber_url);
    }

    public void Sil(View view) {
        SqLite db = new SqLite(NewsDetailsBookmarks.this);
        db.HaberSil(id+1);
    }
}
