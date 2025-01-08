package com.example.firebaseapka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private ImageView btnMojeWyniki,btnDodajWynik,btnMoiZnajomi,btnWyloguj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            initViews();


            String emailuzytkownikaaktualnego =currentUser.getEmail();

            // Użytkownik jest już zalogowany, przekierowanie do menu
            btnMojeWyniki.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, mojewyniki.class);
                    intent.putExtra("WyborOpcji","1");
                    intent.putExtra("EmailUzytkownika", emailuzytkownikaaktualnego);
                startActivity(intent);
                }
            });
            btnDodajWynik.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  Intent intent= new Intent(MainActivity.this, mojewyniki.class);
                  intent.putExtra("EmailUzytkownika", emailuzytkownikaaktualnego);
                  intent.putExtra("WyborOpcji","2");
                    startActivity(intent);
                }
            });
            btnMoiZnajomi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, MoiZnajomi.class);
                startActivity(intent);
                }
            });
            btnWyloguj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
               Intent intent= new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                }
            });
        }
        else {
            Intent intent= new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }


    }
    private void initViews() {
        btnMojeWyniki=findViewById(R.id.btnMojeWyniki);
        btnDodajWynik=findViewById(R.id.btnDodajWynik);
        btnMoiZnajomi=findViewById(R.id.btnmoiznajomi);
        btnWyloguj=findViewById(R.id.btnWyloguj);
    }
}