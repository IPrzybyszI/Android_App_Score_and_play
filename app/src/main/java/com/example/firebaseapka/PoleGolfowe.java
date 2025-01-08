package com.example.firebaseapka;

public class PoleGolfowe {
    private int IdPola;
    private String NazwaPola;
    private String Miejscowosc;
    private int LiczbaDolkow;
    private String imageAdress;


    public PoleGolfowe(int idPola, String nazwaPola, String miejscowosc, int liczbaDolkow, String imageAdress) {

        IdPola = idPola;
        NazwaPola = nazwaPola;
        Miejscowosc = miejscowosc;
        LiczbaDolkow = liczbaDolkow;
        this.imageAdress = imageAdress;
    }

    public int getIdPola() {
        return IdPola;
    }

    public void setIdPola(int idPola) {
        IdPola = idPola;
    }

    public String getNazwaPola() {
        return NazwaPola;
    }

    public void setNazwaPola(String nazwaPola) {
        NazwaPola = nazwaPola;
    }

    public String getLiczbaDolkowStr() {
        String LiczbaDolkowString=String.valueOf(LiczbaDolkow);
        return LiczbaDolkowString;
    }
    public int getLiczbaDolkowInt(){
        return LiczbaDolkow;
    }

    public void setLiczbaDolkow(int liczbaDolkow) {
        LiczbaDolkow = liczbaDolkow;
    }

    @Override
    public String toString() {
        return "PoleGolfowe{" +
                "IdPola=" + IdPola +
                ", NazwaPola='" + NazwaPola + '\'' +
                ", Miejscowosc='" + Miejscowosc + '\'' +
                ", LiczbaDolkow=" + LiczbaDolkow +
                ", imageAdress='" + imageAdress + '\'' +
                '}';
    }

    public String getImageAdress() {
        return imageAdress;
    }

    public void setImageAdress(String imageAdress) {
        this.imageAdress = imageAdress;
    }

    public String getMiejscowosc() {
        return Miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        Miejscowosc = miejscowosc;
    }
}
