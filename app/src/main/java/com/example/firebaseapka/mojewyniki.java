package com.example.firebaseapka;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class mojewyniki extends AppCompatActivity {

    private RecyclerView PoleGolfoweRecView;
    private PoleGolfoweAdapter adapter;
    private UserSessionManager userSessionManager;
    private DatabaseReference databaseReference;

    Button btnPowrot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mojewyniki);

        initViews();
        Intent ZActivity = getIntent();
        //odbieranie z Intenta z PoleGolfowe

        if (ZActivity != null) {
            String emailuzytkownikaaktualnego = ZActivity.getStringExtra("EmailUzytkownika");
            String wyborcopcji = ZActivity.getStringExtra("WyborOpcji");

        btnPowrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mojewyniki.this, MainActivity.class);
                startActivity(intent);
            }
        });

        PoleGolfoweRecView = findViewById(R.id.poleRecView);
        adapter = new PoleGolfoweAdapter(this);
        PoleGolfoweRecView.setAdapter(adapter);
        PoleGolfoweRecView.setLayoutManager(new GridLayoutManager(this, 2));

        // Inicjalizacja Firebase
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userID = currentUser.getUid();
            System.out.println("To user ID:" + userID);

            //Utworzenie referencji do tabeli polagolfowe
            databaseReference = FirebaseDatabase.getInstance().getReference("/polagolfowe");

            // Dodawanie Listenera do odczytu danych
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        ArrayList<PoleGolfowe> polagolfowe = new ArrayList<>();

                        // Pobieranie danych z bazy Firebase
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            PoleGolfoweOdRealTima poleGolfowe = snapshot.getValue(PoleGolfoweOdRealTima.class);
                            System.out.println(poleGolfowe);

                            polagolfowe.add(new PoleGolfowe(poleGolfowe.getIdPola(), poleGolfowe.getNazwaPola(), poleGolfowe.getMiejscowosc(), poleGolfowe.getLiczbaDolkow(), "\\https://m.media-amazon.com/images/I/91khrk365jL._AC_UF894,1000_QL80_.jpg"));
                        }
                        Log.d("FirebaseData", "After fetching data from Firebase");


                        // Aktualizacja widoku RecyclerView
                        adapter.setPolagolfowe(polagolfowe);
                        adapter.setWyboropcji(wyborcopcji);
                        adapter.setEmailUzytkownika(emailuzytkownikaaktualnego);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Obsługa błędów odczytu danych
                }
            });
        }
    }
        else{

        }
    }

    private void initViews() {
        btnPowrot = findViewById(R.id.btnPrzyciskPowrotu);
    }
}
