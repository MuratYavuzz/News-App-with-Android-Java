package com.example.mst_news;

public class Haberler {

    private String baslik;
    private String url;
    private String icerik;
    private String tarih;
    private String kaynak;
    private String imageUrl;



    public Haberler(String baslik, String url, String icerik, String tarih, String kaynak, String imageUrl)
    {
        this.baslik=baslik;
        this.url=url;
        this.icerik=icerik;
        this.tarih=tarih;
        this.kaynak=kaynak;
        this.imageUrl=imageUrl;

    }


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getKaynak() {
        return kaynak;
    }

    public void setKaynak(String kaynak) {
        this.kaynak = kaynak;
    }
}
