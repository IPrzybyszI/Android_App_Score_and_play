package com.example.firebaseapka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PokazWynikowUzytkownika extends AppCompatActivity {
    Button btnPowrot;
    private RecyclerView WynikiUztykownikaRecView;
    private PokazWynikowUzytkownikaAdapter  adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokaz_wynikow_uzytkownika);

        Intent DoWynikow = getIntent();
        //odbieranie z Intenta z PoleGolfowe

        if (DoWynikow != null) {
            String IdDolkatoString = DoWynikow.getStringExtra("IdDolka");
            String EmailUzytkownika = DoWynikow.getStringExtra("EmailUzytkownika");
            String NazwaDolka = DoWynikow.getStringExtra("NazwaDolka");
            System.out.println(IdDolkatoString);

            System.out.println("To jest Id Dolka: "+IdDolkatoString);
            int IdDolka;
            //proba przeksztalcenia IdDolkatoString na Integer
            try {
                IdDolka = Integer.valueOf(IdDolkatoString);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return;
            }

            initViews();
            btnPowrot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PokazWynikowUzytkownika.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            WynikiUztykownikaRecView = findViewById(R.id.RecViewWynikiUzytkownika);
            adapter = new PokazWynikowUzytkownikaAdapter(this);

            WynikiUztykownikaRecView.setAdapter(adapter);
            WynikiUztykownikaRecView.invalidate();
            WynikiUztykownikaRecView.setLayoutManager(new GridLayoutManager(this, 2));

            ArrayList<WynikUzytkownikaZUrl> wynikiuzytkownika=new ArrayList<>();

            DatabaseReference doleRef = FirebaseDatabase.getInstance().getReference().child("wyniki_uzytkownikow");


            doleRef.orderByChild("IdDolka").equalTo(IdDolka).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    boolean czyistniejewynik;
                    czyistniejewynik=false;
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            WynikUzytkownika wynik = snapshot.getValue(WynikUzytkownika.class);


                            if(wynik !=null && wynik.getEmailUzytkownika().equals(EmailUzytkownika)){
                                wynikiuzytkownika.add(new WynikUzytkownikaZUrl(wynik.getData(),wynik.getEmailUzytkownika(),wynik.getIdDolka(),wynik.getIdPola(),wynik.getIdWyniku(),wynik.getIloscUderzen(),""));
                                czyistniejewynik=true;
                            }
                            else {

                            }

                            System.out.println(wynik);

                        }
                        if(czyistniejewynik==false){
                            Toast.makeText(PokazWynikowUzytkownika.this,"Brak Wynikow z tego dolka",Toast.LENGTH_SHORT).show();

                        }
                        adapter.setNazwaDolka(NazwaDolka);
                        adapter.setWynikiuzytkownika(wynikiuzytkownika);
                        adapter.setEmailUzytkownika(EmailUzytkownika);
                    } else {
                        // Brak danych dla określonego IdPola
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Obsługa błędów odczytu danych
                }
            });

        }
        else{

        }
    }
    private void initViews(){
        btnPowrot=findViewById(R.id.btnPrzyciskPowrotuWynikiUzytkownika);
    }
}