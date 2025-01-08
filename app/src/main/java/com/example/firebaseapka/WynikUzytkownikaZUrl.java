package com.example.firebaseapka;

public class WynikUzytkownikaZUrl {

    private String Data;
    private String Email;
    private int IdDolka;
    private int IdPola;
    private int IdWyniku;
    private int IloscUderzen;
    private String ImageAdress;

    public WynikUzytkownikaZUrl(String data, String email, int idDolka, int idPola, int idWyniku, int iloscUderzen, String imageAdress) {
        Data = data;
        Email = email;
        IdDolka = idDolka;
        IdPola = idPola;
        IdWyniku = idWyniku;
        IloscUderzen = iloscUderzen;
        ImageAdress = imageAdress;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getIdDolka() {
        return IdDolka;
    }

    public void setIdDolka(int idDolka) {
        IdDolka = idDolka;
    }

    public int getIdPola() {
        return IdPola;
    }

    public void setIdPola(int idPola) {
        IdPola = idPola;
    }

    public int getIdWyniku() {
        return IdWyniku;
    }

    public void setIdWyniku(int idWyniku) {
        IdWyniku = idWyniku;
    }

    public int getIloscUderzen() {
        return IloscUderzen;
    }

    public void setIloscUderzen(int iloscUderzen) {
        IloscUderzen = iloscUderzen;
    }

    public String getImageAdress() {
        return ImageAdress;
    }

    public void setImageAdress(String imageAdress) {
        ImageAdress = imageAdress;
    }

    @Override
    public String toString() {
        return "WynikUzytkownikaZUrl{" +
                "Data='" + Data + '\'' +
                ", Email='" + Email + '\'' +
                ", IdDolka=" + IdDolka +
                ", IdPola=" + IdPola +
                ", IdWyniku=" + IdWyniku +
                ", IloscUderzen=" + IloscUderzen +
                ", ImageAdress=" + ImageAdress +
                '}';
    }
}
