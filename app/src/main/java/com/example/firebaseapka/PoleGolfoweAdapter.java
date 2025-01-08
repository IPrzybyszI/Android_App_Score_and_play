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

public class PoleGolfoweAdapter extends RecyclerView.Adapter<PoleGolfoweAdapter.ViewHolder> {

    private static final String TAG="PoleGolfoweRecView";
    private ArrayList<PoleGolfowe> polagolfowe = new ArrayList<>();
    private Context mContext;

    private String EmailUzytkownika;
    private String wyboropcji;

    public String getWyboropcji() {
        return wyboropcji;
    }

    public void setWyboropcji(String wyboropcji) {
        this.wyboropcji = wyboropcji;
    }

    public String getEmailUzytkownika() {
        return EmailUzytkownika;
    }

    public void setEmailUzytkownika(String emailUzytkownika) {
        EmailUzytkownika = emailUzytkownika;
    }

    public PoleGolfoweAdapter(Context mContext){
        this.mContext=mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kartapolagolfowego, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Log.d(TAG, "OnBindViewHolder: Called");
        holder.NazwaPola.setText(polagolfowe.get(position).getNazwaPola());
        holder.Miejscowosc.setText(polagolfowe.get(position).getMiejscowosc());
        holder.Liczbadolkow.setText(polagolfowe.get(position).getLiczbaDolkowStr());
        Glide.with(mContext)
                .asBitmap()
                .load(polagolfowe.get(position).getImageAdress())
                .into(holder.imgBook);


        holder.parentkarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String IdPolatoString=String.valueOf(polagolfowe.get(position).getIdPola());

                Toast.makeText(mContext, polagolfowe.get(position).getNazwaPola()+" Selected", Toast.LENGTH_SHORT).show();
                Context context = v.getContext();
                polagolfowe.get(position).getIdPola();
                Intent Dodolkow= new Intent(context, PokazDolkowWynikowych.class);
                Dodolkow.putExtra("IdPola", IdPolatoString);
                Dodolkow.putExtra("EmailUzytkownika", EmailUzytkownika);
                Dodolkow.putExtra("Miejscowosc",polagolfowe.get(position).getMiejscowosc());
                Dodolkow.putExtra("WyborOpcji",wyboropcji);
                Dodolkow.putExtra("NazwaPola",polagolfowe.get(position).getNazwaPola());
                context.startActivity(Dodolkow);
            }

        });
    }

    @Override
    public int getItemCount() {
        return polagolfowe.size();
    }

    public void setPolagolfowe(ArrayList<PoleGolfowe> polagolfowe) {
        this.polagolfowe = polagolfowe;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parentkarta;
        private ImageView imgBook;

        private TextView NazwaPola;
        private TextView Miejscowosc;
        private TextView Liczbadolkow;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            parentkarta=itemView.findViewById(R.id.parentkarta);
            imgBook=itemView.findViewById(R.id.imgBook);
            NazwaPola=itemView.findViewById(R.id.txtTytulPola);
            Miejscowosc=itemView.findViewById(R.id.txtMiejscowoscPola);
            Liczbadolkow=itemView.findViewById(R.id.txtDolki);
        }
    }



}
