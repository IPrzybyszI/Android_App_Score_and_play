package com.example.firebaseapka;

public class PoleGolfoweOdRealTima {
    private int IdPola;
    private int LiczbaDolkow;
    private String Miejscowosc;
    private String NazwaPola;

    public PoleGolfoweOdRealTima(int idPola, int liczbaDolkow, String miejscowosc, String nazwaPola) {
        IdPola = idPola;
        LiczbaDolkow = liczbaDolkow;
        Miejscowosc = miejscowosc;
        NazwaPola = nazwaPola;
    }

    public PoleGolfoweOdRealTima() {
    }

    public int getIdPola() {
        return IdPola;
    }

    public void setIdPola(int idPola) {
        IdPola = idPola;
    }

    public int getLiczbaDolkow() {
        return LiczbaDolkow;
    }

    public void setLiczbaDolkow(int liczbaDolkow) {
        LiczbaDolkow = liczbaDolkow;
    }

    public String getMiejscowosc() {
        return Miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        Miejscowosc = miejscowosc;
    }

    public String getNazwaPola() {
        return NazwaPola;
    }

    public void setNazwaPola(String nazwaPola) {
        NazwaPola = nazwaPola;
    }

    @Override
    public String toString() {
        return "PoleGolfoweOdRealTima{" +
                "IdPola=" + IdPola +
                ", LiczbaDolkow=" + LiczbaDolkow +
                ", Miejscowosc='" + Miejscowosc + '\'' +
                ", NazwaPola='" + NazwaPola + '\'' +
                '}';
    }
}
