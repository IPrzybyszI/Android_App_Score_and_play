package com.example.firebaseapka;

public class WynikUzytkownika {
    private String Data;
    private String EmailUzytkownika;
    private int IdDolka;
    private int IdPola;
    private int IdWyniku;
    private int IloscUderzen;

    public WynikUzytkownika(String data, String email, int idDolka, int idPola, int idWyniku, int iloscUderzen) {
        Data = data;
        EmailUzytkownika = email;
        IdDolka = idDolka;
        IdPola = idPola;
        IdWyniku = idWyniku;
        IloscUderzen = iloscUderzen;
    }

    public WynikUzytkownika() {
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getEmailUzytkownika() {
        return EmailUzytkownika;
    }

    public void setEmailUzytkownika(String emailUzytkownika) {
        EmailUzytkownika = emailUzytkownika;
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

    @Override
    public String toString() {
        return "WynikUzytkownika{" +
                "Data='" + Data + '\'' +
                ", Email='" + EmailUzytkownika + '\'' +
                ", IdDolka=" + IdDolka +
                ", IdPola=" + IdPola +
                ", IdWyniku=" + IdWyniku +
                ", IloscUderzen=" + IloscUderzen +
                '}';
    }

}
