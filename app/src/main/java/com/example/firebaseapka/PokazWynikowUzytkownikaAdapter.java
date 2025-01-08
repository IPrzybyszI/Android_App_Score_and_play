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

public class PokazWynikowUzytkownikaAdapter extends RecyclerView.Adapter<PokazWynikowUzytkownikaAdapter.ViewHolder> {



    private static final String TAG="DolkiRecView";
    private ArrayList<WynikUzytkownikaZUrl> wynikiuzytkownika = new ArrayList<>();
    private Context mContext;

    private String EmailUzytkownika;
    private String NazwaDolka;

    public String getNazwaDolka() {
        return NazwaDolka;
    }

    public void setNazwaDolka(String nazwaDolka) {
        NazwaDolka = nazwaDolka;
    }

    public String getEmailUzytkownika() {
        return EmailUzytkownika;
    }

    public void setEmailUzytkownika(String emailUzytkownika) {
        EmailUzytkownika = emailUzytkownika;
    }

    public PokazWynikowUzytkownikaAdapter(Context mContext){
        this.mContext=mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kartawynikuuzytkownika, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokazWynikowUzytkownikaAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Log.d(TAG, "OnBindViewHolder: Called");
        holder.DataWyniku.setText(wynikiuzytkownika.get(position).getData());
        holder.NazwaDolka.setText(getNazwaDolka());
        holder.IloscUderzen.setText(String.valueOf(wynikiuzytkownika.get(position).getIloscUderzen()));
        Glide.with(mContext)
                .asBitmap()
                .load(wynikiuzytkownika.get(position).getImageAdress())
                .into(holder.imgBook);


        holder.kartawyniku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }

        });
    }

    @Override
    public int getItemCount() {
        return wynikiuzytkownika.size();
    }

    public void setWynikiuzytkownika(ArrayList<WynikUzytkownikaZUrl> wynikiuzytkownika) {
        this.wynikiuzytkownika = wynikiuzytkownika;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView kartawyniku;
        private ImageView imgBook;

        private TextView DataWyniku;
        private TextView IloscUderzen;
        private TextView NazwaDolka;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            kartawyniku=itemView.findViewById(R.id.parentkartaWynikuUzytk);
            imgBook=itemView.findViewById(R.id.imgBook);
            DataWyniku=itemView.findViewById(R.id.txtDataWyniku);
            IloscUderzen=itemView.findViewById(R.id.txtIloscUderzen);
            NazwaDolka=itemView.findViewById(R.id.txtWynikNazwaDolka);
        }
    }
}
