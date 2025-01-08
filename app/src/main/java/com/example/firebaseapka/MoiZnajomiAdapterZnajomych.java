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

public class MoiZnajomiAdapterZnajomych extends RecyclerView.Adapter<MoiZnajomiAdapterZnajomych.ViewHolder> {



    private static final String TAG="MoiZnajomiRecView";
    private ArrayList<UzytkownikzUrl> uzytkownicy = new ArrayList<>();
    private Context mContext;

    public MoiZnajomiAdapterZnajomych(Context mContext){
        this.mContext=mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kartaznajomego, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Log.d(TAG, "OnBindViewHolder: Called");
        holder.Imie.setText(uzytkownicy.get(position).getImie());
        holder.Nazwisko.setText(uzytkownicy.get(position).getNazwisko());
        Glide.with(mContext)
                .asBitmap()
                .load(uzytkownicy.get(position).getImageAdress())
                .into(holder.imgBook);


        holder.parentkarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=v.getContext();
                Intent intent= new Intent(context, mojewyniki.class);
                intent.putExtra("EmailUzytkownika", uzytkownicy.get(position).getEmail());
                intent.putExtra("WyborOpcji","1");
                context.startActivity(intent);
            }

        });
    }

    @Override
    public int getItemCount() {
        return uzytkownicy.size();
    }

    public void setUzytkownicy(ArrayList<UzytkownikzUrl> uzytkownicy) {
        this.uzytkownicy = uzytkownicy;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parentkarta;
        private ImageView imgBook;

        private TextView Imie;
        private TextView Nazwisko;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            parentkarta=itemView.findViewById(R.id.parentkartaZnajomy);
            imgBook=itemView.findViewById(R.id.imgBook);
            Imie=itemView.findViewById(R.id.txtImieUzytkownika);
            Nazwisko=itemView.findViewById(R.id.txtNazwiskoUzytkownika);
        }
    }
}