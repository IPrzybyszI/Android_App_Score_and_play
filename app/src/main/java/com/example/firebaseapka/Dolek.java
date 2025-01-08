package com.example.firebaseapka;

public class Dolek {
    private int IdDolka;
    private int IdPola;
    private String NazwaDolka;
    private int NumerDolka;


    public Dolek(int idDolka, int idPola, String nazwaDolka, int numerDolka) {
        IdDolka = idDolka;
        IdPola = idPola;
        NazwaDolka = nazwaDolka;
        NumerDolka = numerDolka;
    }

    public Dolek() {
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

    @Override
    public String toString() {
        return "Dolek{" +
                "IdDolka=" + IdDolka +
                ", IdPola=" + IdPola +
                ", NazwaDolka='" + NazwaDolka + '\'' +
                ", NumerDolka=" + NumerDolka +
                '}';
    }
}
