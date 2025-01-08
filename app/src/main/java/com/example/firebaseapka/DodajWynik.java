package com.example.firebaseapka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DodajWynik extends AppCompatActivity {

    ImageView ImgDodaj, ImgOdejmij, ImgDodajWynik;
    EditText editTextLiczbaStrzalow;

    TextView TextViewNazwaPola, TextViewNazwaDolka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wynik);

        initViews();

        Intent Zdolkow=getIntent();

        if(Zdolkow!=null){
            String IdPolatoString =Zdolkow.getStringExtra("IdPola");
            String IdDolkatoString=Zdolkow.getStringExtra("IdDolka");
            String EmailUzytkownikaaktualnego=Zdolkow.getStringExtra("EmailUzytkownika");
            String NazwaPola=Zdolkow.getStringExtra("NazwaPola");
            String NazwaDolka=Zdolkow.getStringExtra("NazwaDolka");
            int IdDolka=Integer.valueOf(IdDolkatoString);
            int IdPola=Integer.valueOf(IdPolatoString);

            AtomicInteger iloscUderzen = new AtomicInteger(0);

            TextViewNazwaPola.setText(String.valueOf(NazwaPola));
            TextViewNazwaDolka.setText(String.valueOf(NazwaDolka));



            ImgDodajWynik.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String wartoscEditText = editTextLiczbaStrzalow.getText().toString();
                    try {
                        int wartoscLiczba = Integer.parseInt(wartoscEditText);


                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                        if (currentUser != null) {
                            String userID = currentUser.getUid();

                            DatabaseReference wynikiRef = FirebaseDatabase.getInstance().getReference().child("wyniki_uzytkownikow");

                            wynikiRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    long liczbaElementow = dataSnapshot.getChildrenCount();
                                    liczbaElementow++;

                                    System.out.println("Liczba elementów w tabeli wyniki_uzytkownikow: " + liczbaElementow);

                                    String nowyIdWyniku = wynikiRef.push().getKey();
                                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd");
                                    LocalDate localDate = LocalDate.now();
                                    String DzisiejszaData= localDate.toString();

                                    // Przygotowujemy dane wyniku
                                    Map<String, Object> nowyWynik = new HashMap<>();
                                    nowyWynik.put("Data", DzisiejszaData);
                                    nowyWynik.put("EmailUzytkownika", EmailUzytkownikaaktualnego);
                                    nowyWynik.put("IdDolka", IdDolka);
                                    nowyWynik.put("IdPola", IdPola);
                                    nowyWynik.put("IdWyniku", liczbaElementow);
                                    nowyWynik.put("IloscUderzen",wartoscLiczba);

                                    // Dodajemy nowy wynik do bazy danych
                                    wynikiRef.child(String.valueOf(liczbaElementow)).setValue(nowyWynik)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    // Pomyślnie dodano wynik do bazy danych
                                                    System.out.println("Udało się dodać do bazy danych");
                                                    Toast.makeText(DodajWynik.this, "Dodano nowy wynik!", Toast.LENGTH_SHORT).show();

                                                    Intent intent= new Intent(DodajWynik.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    // Nie udało się dodać wyniku do bazy danych
                                                    Toast.makeText(DodajWynik.this, "Błąd podczas dodawania wyniku.", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    // Obsługa błędów odczytu danych
                                }
                            });
                        }




                    } catch (NumberFormatException e) {
                        // Obsłuż sytuację, gdy wprowadzony tekst nie jest liczbą całkowitą
                        Toast.makeText(DodajWynik.this, "Wprowadź poprawną liczbę Strzalow", Toast.LENGTH_SHORT).show();
                    }


                }
            });
            ImgDodaj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iloscUderzen.incrementAndGet();
                    editTextLiczbaStrzalow.setText(String.valueOf(iloscUderzen));


//                    String wartoscEditText = editTextLiczbaStrzalow.getText().toString();
//                    if (wartoscEditText==null){
//
//                        try {
//                            int number = Integer.parseInt(wartoscEditText);
//                            System.out.println("String można zamienić na int.");
//                            if(iloscUderzen < number){
//
//                            }
//                        } catch (NumberFormatException e) {
//                            System.out.println("String nie można zamienić na int.");
//                            Toast.makeText(DodajWynik.this, "Liczbe uderzen nie mozna zmienic", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                    }
//                    else{
//                        iloscUderzen.incrementAndGet();
//                        editTextLiczbaStrzalow.setText(String.valueOf(iloscUderzen));
//                    }



                }
            });

            ImgOdejmij.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (iloscUderzen.get() > 0) {
                        iloscUderzen.decrementAndGet();
                        editTextLiczbaStrzalow.setText(String.valueOf(iloscUderzen));
                    } else {
                        Toast.makeText(DodajWynik.this, "Nie można mieć mniej niż 0 Uderzeń", Toast.LENGTH_SHORT).show();
                    }
                }

            });

    }
        else{

        }











    }
    private void initViews() {
        ImgDodaj=findViewById(R.id.ImgDodaj);
        ImgOdejmij=findViewById(R.id.ImgOdejmij);
        ImgDodajWynik=findViewById(R.id.ImgDodajWynik);
        editTextLiczbaStrzalow=findViewById(R.id.editTextLiczbaStrzalow);
        TextViewNazwaDolka=findViewById(R.id.TextViewNazwaDolka);
        TextViewNazwaPola=findViewById(R.id.TextViewNazwaPola);


    }

}