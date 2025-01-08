package com.example.firebaseapka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PokazDolkowWynikowych extends AppCompatActivity {

    private RecyclerView DolkiRecView;
    Button btnPowrot;
    private PokazDolkowAdapter  adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokaz_dolkow_wynikowych);


        Intent Dodolkow = getIntent();
        //odbieranie z Intenta z PoleGolfowe

        if (Dodolkow != null) {
            String IdPolatoString = Dodolkow.getStringExtra("IdPola");
            String EmailUzytkownika = Dodolkow.getStringExtra("EmailUzytkownika");
            String Miejscowosc =Dodolkow.getStringExtra("Miejscowosc");
            String wyboropcji=Dodolkow.getStringExtra("WyborOpcji");
            String NazwaPola=Dodolkow.getStringExtra("NazwaPola");
            int IdPola=Integer.valueOf(IdPolatoString);


            System.out.println(IdPola);


            initViews();

            btnPowrot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PokazDolkowWynikowych.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            DolkiRecView = findViewById(R.id.poleRecViewDolki);
            adapter = new PokazDolkowAdapter(this);


            DolkiRecView.setAdapter(adapter);
            DolkiRecView.invalidate();
            DolkiRecView.setLayoutManager(new GridLayoutManager(this, 2));


            ArrayList<Dolekzurl> dolki = new ArrayList<>();

            DatabaseReference doleRef = FirebaseDatabase.getInstance().getReference().child("Dolki");

            doleRef.orderByChild("IdPola").equalTo(IdPola).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Dolek dolek = snapshot.getValue(Dolek.class);
                            dolki.add(new Dolekzurl(dolek.getIdDolka(),dolek.getIdPola(),dolek.getNazwaDolka(),dolek.getNumerDolka(),""));
                            System.out.println(dolek);

                        }
                        adapter.setWyborOpcji(wyboropcji);
                        adapter.setMiejscowosc(Miejscowosc);
                        adapter.setDoleczki(dolki);
                        adapter.setNazwaPola(NazwaPola);
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
       else {

        }


    }

    private void initViews() {
        btnPowrot=findViewById(R.id.btnPrzyciskPowrotuDolki);

    }

}