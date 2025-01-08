package com.example.firebaseapka;

public class Dolekzurl {
    private int IdDolka;
    private int IdPola;
    private String NazwaDolka;
    private int NumerDolka;
    private String imageAdress;

    public Dolekzurl(int idDolka, int idPola, String nazwaDolka, int numerDolka, String imageAdress) {
        IdDolka = idDolka;
        IdPola = idPola;
        NazwaDolka = nazwaDolka;
        NumerDolka = numerDolka;
        this.imageAdress = imageAdress;
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

    public String getNazwaDolka() {
        return NazwaDolka;
    }

    public void setNazwaDolka(String nazwaDolka) {
        NazwaDolka = nazwaDolka;
    }

    public int getNumerDolka() {
        return NumerDolka;
    }

    public void setNumerDolka(int numerDolka) {
        NumerDolka = numerDolka;
    }

    public String getImageAdress() {
        return imageAdress;
    }

    public void setImageAdress(String imageAdress) {
        this.imageAdress = imageAdress;
    }

    @Override
    public String toString() {
        return "Dolekzurl{" +
                "IdDolka=" + IdDolka +
                ", IdPola=" + IdPola +
                ", NazwaDolka='" + NazwaDolka + '\'' +
                ", NumerDolka=" + NumerDolka +
                ", imageAdress='" + imageAdress + '\'' +
                '}';
    }
}
