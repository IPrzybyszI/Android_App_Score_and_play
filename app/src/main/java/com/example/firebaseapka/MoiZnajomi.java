package com.example.firebaseapka;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MoiZnajomi extends AppCompatActivity {
    private RecyclerView MoiZnajomiRecView;

    Button btnPowrot, btnDodajZnajomego, btnUsunZnajomego;
    private MoiZnajomiAdapterZnajomych  adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moi_znajomi);


        //Pobranie instacji z Firebase
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userID = currentUser.getUid();
            String emailaktualnegouzytkownika =currentUser.getEmail();

            initViews();

            btnPowrot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MoiZnajomi.this, MainActivity.class);
                    startActivity(intent);
                }
            });

            btnDodajZnajomego.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCustomDialog(emailaktualnegouzytkownika,1);
                }
            });
            btnUsunZnajomego.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCustomDialog(emailaktualnegouzytkownika,2);
                }
            });



            MoiZnajomiRecView = findViewById(R.id.RecViewMoiZnajomi);
            MoiZnajomiRecView.setLayoutManager(new GridLayoutManager(this, 2));

            adapter = new MoiZnajomiAdapterZnajomych(this);
            MoiZnajomiRecView.setAdapter(adapter);

            MoiZnajomiRecView.invalidate();



            //Utworzenie odpowiedniej referencji do tabeli Uzytkownicy w pliku JSON
            DatabaseReference doleRef = FirebaseDatabase.getInstance().getReference().child("Uzytkownicy");
            //Utworzenie referencji do odpowiedniego emaila uzytkownika
            doleRef.orderByChild("Email").equalTo(emailaktualnegouzytkownika).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            Uzytkownik uzytkownik = snapshot.getValue(Uzytkownik.class);

                            //Wypisanie na czat uzytkownika. Funkcja sprawdzajaca poprawne dzialanie
                            System.out.println(uzytkownik);
                            uzytkownik.getListaZnajomych();

                            if(uzytkownik.getListaZnajomych()==null){
                                Toast.makeText(MoiZnajomi.this, "Nie posiadasz znajomych", Toast.LENGTH_SHORT).show();

                            }

                            //Z powodu tego, iż listaZnajomych dostarczana jest za pomocą stringa zawierającego listę emaili znajomych podzielonymi znakiem ',' następuje podzielenie
                            String splitnieListaZnajomych[]=uzytkownik.getListaZnajomych().split(",");

                            ArrayList<UzytkownikzUrl> znajomiuzytkownika=new ArrayList<>();
                            for(int i=0;i<splitnieListaZnajomych.length;i++){

                                doleRef.orderByChild("Email").equalTo(splitnieListaZnajomych[i]).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                Uzytkownik uzytkownik = snapshot.getValue(Uzytkownik.class);

                                                System.out.println(uzytkownik);
                                                znajomiuzytkownika.add(new UzytkownikzUrl(uzytkownik.getImie(),uzytkownik.getNazwisko(),uzytkownik.getEmail(),uzytkownik.getListaZnajomych(), uzytkownik.getIdUzytkownika(), ""));


                                            }
                                            System.out.println(znajomiuzytkownika);

                                            adapter.setUzytkownicy(znajomiuzytkownika);
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









                        }







                    }
                    else {
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
        btnPowrot = findViewById(R.id.btnPrzyciskPowrotuMoiZnajomi);
        btnDodajZnajomego=findViewById(R.id.btnDodajZnajomego);
        btnUsunZnajomego=findViewById(R.id.btnUsunZnajomego);
    }

    private void showCustomDialog(String emailaktualnegouzytkownika, int wyborusundodaj) {
        // Inicjalizacja widoku niestandardowego
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.formatka_dodaj_znajomego, null);

        // Zainicjowanie elementów formularza
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) EditText emailEditText = customView.findViewById(R.id.editTextFormEmailDodajZnajomego);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button saveButton = customView.findViewById(R.id.btnFormDoajZnajomego);

       if(wyborusundodaj==2){
           saveButton.setText("Usuń Znajomego");
       }

        // Stworzenie AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(customView);

        // Stworzenie i pokazanie AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Obsługa kliknięcia przycisku "Zapisz, badz usun w przypadku wyboru 2 opcji"
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailznajomego = emailEditText.getText().toString();
                System.out.println(emailEditText);

                if(emailznajomego.isEmpty()){
                    Toast.makeText(MoiZnajomi.this, "Nie posiadasz znajomych. Powrót do Menu", Toast.LENGTH_SHORT).show();
                }
                else {
                    DatabaseReference referencjasprawdzznajomego = FirebaseDatabase.getInstance().getReference().child("Uzytkownicy");
                    referencjasprawdzznajomego.orderByChild("Email").equalTo(emailznajomego).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                // Znaleziono znajomego o tym emailu
                                DatabaseReference referencjadodajznajomego = FirebaseDatabase.getInstance().getReference().child("Uzytkownicy");
                                System.out.println("To email:" + emailaktualnegouzytkownika);
                                referencjadodajznajomego.orderByChild("Email").equalTo(emailaktualnegouzytkownika).limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshotdodajznaj) {
                                        try {
                                            for (DataSnapshot dataSnapshot : snapshotdodajznaj.getChildren()) {
                                                Uzytkownik zmienianyuzytkownik = dataSnapshot.getValue(Uzytkownik.class);
                                                if (zmienianyuzytkownik != null) {
                                                    int iduzytkownika = zmienianyuzytkownik.getIdUzytkownika();
                                                    System.out.println("To klucz:" + iduzytkownika);
                                                    System.out.println("To jego email:" + zmienianyuzytkownik.getEmail());

                                                    Map<String, Object> danezmienianegouzytkownika = new HashMap<>();
                                                    danezmienianegouzytkownika.put("IdUzytkownika", zmienianyuzytkownik.getIdUzytkownika());
                                                    danezmienianegouzytkownika.put("Email", zmienianyuzytkownik.getEmail());
                                                    danezmienianegouzytkownika.put("Imie", zmienianyuzytkownik.getImie());
                                                    danezmienianegouzytkownika.put("Nazwisko", zmienianyuzytkownik.getNazwisko());

                                                    String sprawdzanystring = zmienianyuzytkownik.getListaZnajomych();
                                                    if (sprawdzanystring == null) {
                                                        if(wyborusundodaj==1){
                                                            danezmienianegouzytkownika.put("ListaZnajomych", emailznajomego);
                                                        }
                                                        else {
                                                            Toast.makeText(MoiZnajomi.this,"Nie posiadasz znajomych do usuniecia",Toast.LENGTH_SHORT).show();
                                                        }

                                                    } else {
                                                        //w przypadku checi dodania uzytkownika
                                                        if (wyborusundodaj == 1) {


                                                        String splitnieListaZnajomych[] = zmienianyuzytkownik.getListaZnajomych().split(",");
                                                        boolean czyjestwliscieznajomych;
                                                        czyjestwliscieznajomych = false;
                                                        for (int i = 0; i < splitnieListaZnajomych.length; i++) {
                                                            if (splitnieListaZnajomych[i].equals(emailznajomego)) {
                                                                Toast.makeText(MoiZnajomi.this, "Masz juz w znajomych tego uzytkownika", Toast.LENGTH_SHORT).show();
                                                                czyjestwliscieznajomych = true;
                                                            } else {

                                                            }
                                                        }

                                                        System.out.println("Sprawdzanie prawdy" + czyjestwliscieznajomych);
                                                        if (czyjestwliscieznajomych == false) {
                                                            danezmienianegouzytkownika.put("ListaZnajomych", zmienianyuzytkownik.getListaZnajomych() + "," + emailznajomego);


                                                            referencjadodajznajomego.child(String.valueOf(iduzytkownika)).updateChildren(danezmienianegouzytkownika)
                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void unused) {

                                                                            Toast.makeText(MoiZnajomi.this, "Udało się dodać znajomego", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    })
                                                                    .addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {
                                                                            Toast.makeText(MoiZnajomi.this, "Wystapil problem", Toast.LENGTH_SHORT).show();
                                                                            System.out.println("Nie udalo sie dodac znajomego. Blad: " + e);
                                                                        }
                                                                    });

                                                        }

                                                        //w przypadku checi usuniecia uzytkownika
                                                    } else if (wyborusundodaj==2) {

                                                            String splitnieListaZnajomych[] = zmienianyuzytkownik.getListaZnajomych().split(",");
                                                            boolean czyjestwliscieznajomych;
                                                            czyjestwliscieznajomych = false;
                                                            String Listazupdatowanaznajomych;
                                                            Listazupdatowanaznajomych=null;

                                                            for (int i = 0; i < splitnieListaZnajomych.length; i++) {
                                                                if (splitnieListaZnajomych[i].equals(emailznajomego)) {
                                                                    czyjestwliscieznajomych = true;






                                                                } else {
                                                                    if(Listazupdatowanaznajomych==null){
                                                                        Listazupdatowanaznajomych=splitnieListaZnajomych[i];
                                                                    }
                                                                    else {
                                                                        Listazupdatowanaznajomych=Listazupdatowanaznajomych+","+  splitnieListaZnajomych[i];
                                                                    }

                                                                }
                                                            }
                                                            danezmienianegouzytkownika.put("ListaZnajomych",Listazupdatowanaznajomych);
                                                            if(czyjestwliscieznajomych==false){
                                                                Toast.makeText(MoiZnajomi.this,"Nie masz znajomego o tym emailu",Toast.LENGTH_SHORT).show();
                                                            }
                                                            else {
                                                                referencjadodajznajomego.child(String.valueOf(iduzytkownika)).updateChildren(danezmienianegouzytkownika)
                                                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                            @Override
                                                                            public void onSuccess(Void unused) {
                                                                                Toast.makeText(MoiZnajomi.this, "Udało się usunąć znajomego", Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        })
                                                                        .addOnFailureListener(new OnFailureListener() {
                                                                            @Override
                                                                            public void onFailure(@NonNull Exception e) {
                                                                                Toast.makeText(MoiZnajomi.this, "Wystapil problem", Toast.LENGTH_SHORT).show();
                                                                            }
                                                                        });

                                                            }


                                                        }
                                                    }


                                                }
                                            }
                                        } catch (DatabaseException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        Toast.makeText(MoiZnajomi.this, "Wystąpił błąd przy próbie dodania", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else {
                                // Nie znaleziono znajomego o tym emailu
                                Toast.makeText(MoiZnajomi.this, "Taki uzytkownik nie istnieje", Toast.LENGTH_SHORT).show();
                                System.out.println("Nie znaleziono znajomego o tym emailu");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });







                }




                // Zamknięcie dialogu po zapisaniu
                dialog.dismiss();
            }
        });
    }

}