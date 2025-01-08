package com.example.firebaseapka;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PokazDolkowAdapter extends RecyclerView.Adapter<PokazDolkowAdapter.ViewHolder> {



    private static final String TAG="DolkiRecView";
    private ArrayList<Dolekzurl> doleczki = new ArrayList<>();
    private Context mContext;

    private String EmailUzytkownika;
    private String Miejscowosc;
    private String WyborOpcji;
    private String NazwaPola;

    public String getNazwaPola() {
        return NazwaPola;
    }

    public void setNazwaPola(String nazwaPola) {
        NazwaPola = nazwaPola;
    }

    public String getWyborOpcji() {
        return WyborOpcji;
    }

    public void setWyborOpcji(String wyborOpcji) {
        WyborOpcji = wyborOpcji;
    }

    public String getMiejscowosc() {
        return Miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        Miejscowosc = miejscowosc;
    }

    public String getEmailUzytkownika() {
        return EmailUzytkownika;
    }

    public void setEmailUzytkownika(String emailUzytkownika) {
        EmailUzytkownika = emailUzytkownika;
    }

    public PokazDolkowAdapter(Context mContext){
        this.mContext=mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kartadolkawynikowego, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Log.d(TAG, "OnBindViewHolder: Called");
        holder.NazwaPola.setText(doleczki.get(position).getNazwaDolka());
        holder.Miejscowosc.setText(getMiejscowosc());
        holder.Liczbadolkow.setText(String.valueOf(doleczki.get(position).getNumerDolka()));
        Glide.with(mContext)
                .asBitmap()
                .load(doleczki.get(position).getImageAdress())
                .into(holder.imgBook);


        holder.parentkarta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, doleczki.get(position).getNazwaDolka()+" Selected", Toast.LENGTH_SHORT).show();


                Context context = v.getContext();
                System.out.println("Z Adaptera Id Dolka:" + doleczki.get(position).getIdDolka());
                System.out.println("Z Adaptera Email:" + EmailUzytkownika);
                System.out.println("Z Adaptera NazwaDolka:" + doleczki.get(position).getNazwaDolka());

                if(WyborOpcji.equals("1")){
                    Intent Dowynikow= new Intent(context, PokazWynikowUzytkownika.class);
                    Dowynikow.putExtra("IdDolka", String.valueOf(doleczki.get(position).getIdDolka()));
                    Dowynikow.putExtra("EmailUzytkownika", EmailUzytkownika);
                    Dowynikow.putExtra("NazwaDolka",doleczki.get(position).getNazwaDolka());
                    context.startActivity(Dowynikow);


                } else if (WyborOpcji.equals("2")) {
                    Intent DoDodania= new Intent(context, DodajWynik.class);
                    DoDodania.putExtra("IdPola",String.valueOf(doleczki.get(position).getIdPola()));
                    DoDodania.putExtra("IdDolka", String.valueOf(doleczki.get(position).getIdDolka()));
                    DoDodania.putExtra("EmailUzytkownika", EmailUzytkownika);
                    DoDodania.putExtra("NazwaPola", NazwaPola);
                    DoDodania.putExtra("NazwaDolka", doleczki.get(position).getNazwaDolka());
                    context.startActivity(DoDodania);


                }


            }

        });
    }

    @Override
    public int getItemCount() {
        return doleczki.size();
    }

    public void setDoleczki(ArrayList<Dolekzurl> doleczki) {
        this.doleczki =doleczki;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parentkarta2;
        private ImageView imgBook;

        private TextView NazwaPola;
        private TextView Miejscowosc;
        private TextView Liczbadolkow;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            parentkarta2=itemView.findViewById(R.id.parentkarta2);
            imgBook=itemView.findViewById(R.id.imgBook);
            NazwaPola=itemView.findViewById(R.id.txtDolekTytulPola);
            Miejscowosc=itemView.findViewById(R.id.txtNazwaDolka);
            Liczbadolkow=itemView.findViewById(R.id.txtDolki);
        }
    }
}
