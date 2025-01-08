package com.example.firebaseapka;

public class UzytkownikzUrl {
    private String Imie;
    private String nazwisko;
    private String Email;
    private String ListaZnajomych;

    private int IdUzytkownika;
    private String imageAdress;


    public UzytkownikzUrl(String imie, String nazwisko, String email, String listaZnajomych, int idUzytkownika, String imageAdress) {
        Imie = imie;
        this.nazwisko = nazwisko;
        Email = email;
        ListaZnajomych = listaZnajomych;
        IdUzytkownika = idUzytkownika;
        this.imageAdress = imageAdress;
    }

    public int getIdUzytkownika() {
        return IdUzytkownika;
    }

    public void setIdUzytkownika(int idUzytkownika) {
        IdUzytkownika = idUzytkownika;
    }

    public String getImie() {
        return Imie;
    }

    public void setImie(String imie) {
        Imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getListaZnajomych() {
        return ListaZnajomych;
    }

    public void setListaZnajomych(String listaZnajomych) {
        ListaZnajomych = listaZnajomych;
    }

    public String getImageAdress() {
        return imageAdress;
    }

    public void setImageAdress(String imageAdress) {
        this.imageAdress = imageAdress;
    }

    @Override
    public String toString() {
        return "UzytkownikzUrl{" +
                "Imie='" + Imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", Email='" + Email + '\'' +
                ", ListaZnajomych='" + ListaZnajomych + '\'' +
                ", IdUzytkownika=" + IdUzytkownika +
                ", imageAdress='" + imageAdress + '\'' +
                '}';
    }
}
