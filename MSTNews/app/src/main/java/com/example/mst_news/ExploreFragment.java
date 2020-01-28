package com.example.mst_news;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ExploreFragment extends Fragment {
    ImageButton bbcnews, wallstreet, techcrunch, cnbc, foxnews, cnn, usa, england, germany, china, japan, russia;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.explorefragment, container, false);
        bbcnews = (ImageButton) view.findViewById(R.id.bbcnews);
        wallstreet = (ImageButton) view.findViewById(R.id.wallstreet);
        techcrunch = (ImageButton) view.findViewById(R.id.techcrunch);
        cnbc = (ImageButton) view.findViewById(R.id.cnbc);
        foxnews = (ImageButton) view.findViewById(R.id.foxnews);
        cnn = (ImageButton) view.findViewById(R.id.cnn);
        usa = (ImageButton) view.findViewById(R.id.usa);
        england = (ImageButton) view.findViewById(R.id.england);
        germany = (ImageButton) view.findViewById(R.id.germany);
        china = (ImageButton) view.findViewById(R.id.china);
        japan = (ImageButton) view.findViewById(R.id.japan);
        russia = (ImageButton) view.findViewById(R.id.russia);

        bbcnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent withWallStreet = new Intent(getActivity().getApplicationContext(),Explore.class);
                withWallStreet.putExtra("baseurl","https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=ecbb330402f1479f9c9dbcb22921c211");
                withWallStreet.putExtra("name","BBC News");
                startActivity(withWallStreet);
            }
        });
        wallstreet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent withWallStreet = new Intent(getActivity().getApplicationContext(),Explore.class);
                withWallStreet.putExtra("baseurl","https://newsapi.org/v2/top-headlines?sources=the-wall-street-journal&apiKey=ecbb330402f1479f9c9dbcb22921c211");
                withWallStreet.putExtra("name","Wall Street Journal");
                startActivity(withWallStreet);

            }
        });
        techcrunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent withWallStreet = new Intent(getActivity().getApplicationContext(),Explore.class);
                withWallStreet.putExtra("baseurl","https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=ecbb330402f1479f9c9dbcb22921c211");
                withWallStreet.putExtra("name","Tech Crunch News");
                startActivity(withWallStreet);
            }
        });
        cnbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent withWallStreet = new Intent(getActivity().getApplicationContext(),Explore.class);
                withWallStreet.putExtra("baseurl","https://newsapi.org/v2/top-headlines?sources=cnbc&apiKey=ecbb330402f1479f9c9dbcb22921c211");
                withWallStreet.putExtra("name","CNBC News");
                startActivity(withWallStreet);
            }
        });
        foxnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent withWallStreet = new Intent(getActivity().getApplicationContext(),Explore.class);
                withWallStreet.putExtra("baseurl","https://newsapi.org/v2/top-headlines?sources=fox-news&apiKey=ecbb330402f1479f9c9dbcb22921c211");
                withWallStreet.putExtra("name","Fox News");
                startActivity(withWallStreet);
            }
        });
        cnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent withWallStreet = new Intent(getActivity().getApplicationContext(),Explore.class);
                withWallStreet.putExtra("baseurl","https://newsapi.org/v2/top-headlines?sources=cnn&apiKey=ecbb330402f1479f9c9dbcb22921c211");
                withWallStreet.putExtra("name","CNN News");
                startActivity(withWallStreet);
            }
        });
        usa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent withWallStreet = new Intent(getActivity().getApplicationContext(),Explore.class);
                withWallStreet.putExtra("baseurl","https://newsapi.org/v2/top-headlines?country=us&apiKey=ecbb330402f1479f9c9dbcb22921c211\n");
                withWallStreet.putExtra("name","UNITED STATES");
                startActivity(withWallStreet);
            }
        });
        england.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent withWallStreet = new Intent(getActivity().getApplicationContext(),Explore.class);
                withWallStreet.putExtra("baseurl","https://newsapi.org/v2/top-headlines?country=gb&apiKey=ecbb330402f1479f9c9dbcb22921c211\n");
                withWallStreet.putExtra("name","UNITED KINGDOM");
                startActivity(withWallStreet);
            }
        });
        germany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent withWallStreet = new Intent(getActivity().getApplicationContext(),Explore.class);
                withWallStreet.putExtra("baseurl","https://newsapi.org/v2/top-headlines?country=de&apiKey=ecbb330402f1479f9c9dbcb22921c211\n");
                withWallStreet.putExtra("name","GERMANY");
                startActivity(withWallStreet);
            }
        });
        china.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent withWallStreet = new Intent(getActivity().getApplicationContext(),Explore.class);
                withWallStreet.putExtra("baseurl","https://newsapi.org/v2/top-headlines?country=cn&apiKey=ecbb330402f1479f9c9dbcb22921c211\n");
                withWallStreet.putExtra("name","CHINA");
                startActivity(withWallStreet);
            }
        });
        japan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent withWallStreet = new Intent(getActivity().getApplicationContext(),Explore.class);
                withWallStreet.putExtra("baseurl","https://newsapi.org/v2/top-headlines?country=jp&apiKey=ecbb330402f1479f9c9dbcb22921c211\n");
                withWallStreet.putExtra("name","JAPAN");
                startActivity(withWallStreet);
            }
        });
        russia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent withWallStreet = new Intent(getActivity().getApplicationContext(),Explore.class);
                withWallStreet.putExtra("baseurl","https://newsapi.org/v2/top-headlines?country=ru&apiKey=ecbb330402f1479f9c9dbcb22921c211\n");
                withWallStreet.putExtra("name","RUSSIA");
                startActivity(withWallStreet);
            }
        });
        return view;
    }

}
