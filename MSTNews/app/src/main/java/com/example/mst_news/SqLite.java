package com.example.mst_news;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SqLite extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MSTNEWS";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLO_HABERLER = "haberler";
    private static final String ROW_ID = "id";
    private static final String ROW_ICERIK = "icerik";
    private static final String ROW_BASLIK = "baslik";
    private static final String ROW_IMAGEURL = "imageurl";
    private static final String ROW_URL = "url";
    private static final String ROW_TARIH = "tarih";
    private static final String ROW_KAYNAK = "kaynak";
    public static Context context;
    int max_id;

    public SqLite(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLO_HABERLER + "("
                + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ROW_BASLIK + " TEXT NOT NULL, "
                + ROW_ICERIK + " TEXT NOT NULL, "
                + ROW_KAYNAK + " TEXT NOT NULL, "
                + ROW_IMAGEURL + " TEXT NOT NULL, "
                + ROW_TARIH + " TEXT NOT NULL, "
                + ROW_URL + " TEXT NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_HABERLER);
        onCreate(db);
    }
    public void HaberEkle(String baslik,String icerik,String url,String imageUrl,String kaynak,String tarih)
    {
            SQLiteDatabase dbwrite = getWritableDatabase();
            try {
                ContentValues cv = new ContentValues();
                cv.put(ROW_BASLIK, baslik);
                cv.put(ROW_ICERIK, icerik);
                cv.put(ROW_URL, url);
                cv.put(ROW_IMAGEURL, imageUrl);
                cv.put(ROW_KAYNAK, kaynak);
                cv.put(ROW_TARIH, tarih);
                dbwrite.insert(TABLO_HABERLER, null, cv);
                dbwrite.close();
            } catch (Exception e) {
            }
        }
    //}
    public List<Haberler> HaberListele(){
        List<Haberler> haberler = new ArrayList<Haberler>();
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            String [] sutunlar = {ROW_ID,ROW_BASLIK,ROW_ICERIK,ROW_TARIH,ROW_URL,ROW_IMAGEURL,ROW_KAYNAK};
            Cursor cursor = db.query(TABLO_HABERLER,sutunlar,null,null,null,null,null);
            while(cursor.moveToNext())
            {
                int id;
                String baslik,url,icerik,tarih,kaynak,imageUrl;
                id = cursor.getInt(0);
                baslik = cursor.getString(1);
                icerik = cursor.getString(2);
                tarih = cursor.getString(3);
                url = cursor.getString(4);
                imageUrl = cursor.getString(5);
                kaynak = cursor.getString(6);
                haberler.add(new Haberler(baslik,url,icerik,tarih,kaynak,imageUrl));
            }

        }catch (Exception e)
        {

        }
        db.close();
        return haberler;
    }
    public void Sil()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLO_HABERLER, null, null);
        db.close();
    }
    public void HaberSil(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        try{
            String where = ROW_ID +"=" + id;
            db.delete(TABLO_HABERLER,where,null);

        }catch (Exception e)
        {

        }
        db.close();

    }

}
