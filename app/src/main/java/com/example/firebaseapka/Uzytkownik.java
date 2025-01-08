package com.example.firebaseapka;

public class Uzytkownik {
    private String Imie;
    private String Nazwisko;
    private String Email;
    private String ListaZnajomych;
    private int IdUzytkownika;

    public Uzytkownik(String imie, String Nazwisko, String email, String listaZnajomych, int idUzytkownika) {
        this.Imie = imie;
        this.Nazwisko = Nazwisko;
        Email = email;
        ListaZnajomych = listaZnajomych;
        IdUzytkownika = idUzytkownika;
    }

    public Uzytkownik() {
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
        this.Imie = imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }

    public String getListaZnajomych() {
        return ListaZnajomych;
    }

    public void setNazwisko(String nazwisko) {
        this.Nazwisko = nazwisko;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setListaZnajomych(String listaZnajomych) {
        ListaZnajomych = listaZnajomych;
    }

    @Override
    public String toString() {
        return "Uzytkownik{" +
                "Imie='" + Imie + '\'' +
                ", Nazwisko='" + Nazwisko + '\'' +
                ", Email='" + Email + '\'' +
                ", ListaZnajomych='" + ListaZnajomych + '\'' +
                ", IdUzytkownika=" + IdUzytkownika +
                '}';
    }
}
