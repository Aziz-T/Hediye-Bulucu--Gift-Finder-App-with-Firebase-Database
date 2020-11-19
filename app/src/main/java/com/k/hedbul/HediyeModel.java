package com.k.hedbul;

public class HediyeModel {
    private String resimUrl;
    private String hediyeAdi;
    private String cinsiyet;
    private String yas;
    private String ilgi;
    private String ozel;
    public HediyeModel() { }
    public HediyeModel(String resimUrl, String hediyeAdi) {
        this.resimUrl = resimUrl;
        this.hediyeAdi = hediyeAdi;
    }

    public HediyeModel(String resimUrl, String hediyeAdi, String cinsiyet, String yas, String ilgi, String ozel) {
        this.resimUrl = resimUrl;
        this.hediyeAdi = hediyeAdi;
        this.cinsiyet = cinsiyet;
        this.yas = yas;
        this.ilgi = ilgi;
        this.ozel = ozel;
    }

    public String getResimUrl() {
        return resimUrl;
    }

    public void setResimUrl(String resimUrl) {
        this.resimUrl = resimUrl;
    }

    public String getHediyeAdi() {
        return hediyeAdi;
    }

    public void setHediyeAdi(String hediyeAdi) {
        this.hediyeAdi = hediyeAdi;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getYas() {
        return yas;
    }

    public void setYas(String yas) {
        this.yas = yas;
    }

    public String getIlgi() {
        return ilgi;
    }

    public void setIlgi(String ilgi) {
        this.ilgi = ilgi;
    }

    public String getOzel() {
        return ozel;
    }

    public void setOzel(String ozel) {
        this.ozel = ozel;
    }
}
