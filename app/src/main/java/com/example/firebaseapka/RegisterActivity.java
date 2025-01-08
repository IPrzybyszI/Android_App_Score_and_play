package com.example.firebaseapka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextImie,editTextNazwisko;
    private Button buttonRegister, buttonpowrotdolog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicjalizacja Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        //Inicjacja widokow
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextImie = findViewById(R.id.editTextImie);
        editTextNazwisko = findViewById(R.id.editTextNazwisko);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonpowrotdolog=findViewById(R.id.buttonwroczrejestracji);

        // Obsługa kliknięcia przycisku rejestracji
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pobierz adres e-mail i hasło z pól tekstowych
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String imie=editTextImie.getText().toString().trim();
                String nazwisko=editTextNazwisko.getText().toString().trim();
                // Sprawdź, czy adres e-mail i hasło nie są puste
                if (email.isEmpty() || password.isEmpty() || imie.isEmpty() || nazwisko.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Wprowadź ponownie dane. Nie wpisales wszystkich", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Rejestracja użytkownika w Firebase Authentication
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    DatabaseReference uzytkownicyref= FirebaseDatabase.getInstance().getReference().child("Uzytkownicy");

                                    uzytkownicyref.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            long liczbaElemntow=snapshot.getChildrenCount();
                                            liczbaElemntow++;

                                            Map<String,Object> nowyUzytkownik=new HashMap<>();
                                            nowyUzytkownik.put("Email",email);
                                            nowyUzytkownik.put("Imie",imie);
                                            nowyUzytkownik.put("ListaZnajomych","");
                                            nowyUzytkownik.put("Nazwisko",nazwisko);
                                            nowyUzytkownik.put("IdUzytkownika",liczbaElemntow);

                                            uzytkownicyref.child(String.valueOf(liczbaElemntow)).setValue(nowyUzytkownik)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            // Pomyślnie dodano wynik do bazy danych
                                                            System.out.println("Udało się dodać do bazy danych");
                                                            Toast.makeText(RegisterActivity.this, "Dodano nowy wynik!", Toast.LENGTH_SHORT).show();


                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            // Nie udało się dodać wyniku do bazy danych
                                                            Toast.makeText(RegisterActivity.this, "Błąd podczas dodawania wyniku.", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });





                                    // Rejestracja zakończona sukcesem

                                    Toast.makeText(RegisterActivity.this, "Rejestracja udana!", Toast.LENGTH_SHORT).show();
                                    Intent intent= new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    // Rejestracja zakończona niepowodzeniem
                                    Toast.makeText(RegisterActivity.this, "Błąd rejestracji: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        //Powrot do LoginActivity
        buttonpowrotdolog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}