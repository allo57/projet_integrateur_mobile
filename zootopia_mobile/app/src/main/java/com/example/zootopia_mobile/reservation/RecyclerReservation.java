package com.example.zootopia_mobile.reservation;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zootopia_mobile.R;

public class RecyclerReservation extends RecyclerView.Adapter<RecyclerReservation.MyViewHolder>{

    private String reservations[];
    private Context context;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_recycler_reservation, parent, false);

        return new MyViewHolder(view);
    }

    public RecyclerReservation(Context context, String programming_languages[]) {
        this.context = context;
        this.reservations = programming_languages;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.numero.setText("Numéro de la réservation : " + reservations[position]);
        holder.nom.setText("Nom de la réservation : " + reservations[position]);
        holder.no_tel.setText("Numéro de la contact : " + reservations[position]);
        //holder.modifier.setImageResource(R.drawable.edit);
        holder.modifier.setBackground(null);
        //holder.supprimer.setImageResource(R.drawable.delete);
        holder.supprimer.setBackground(null);

        holder.infoTransaction.setOnClickListener(v-> {
            Intent intent = new Intent(context, DetailReservtion.class);
            context.startActivity(intent);
        });

        holder.modifier.setOnClickListener(v-> {
            Intent intent = new Intent(context, ModifierReservation.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return reservations.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout infoTransaction;
        TextView numero;
        TextView nom;
        TextView no_tel;
        ImageButton modifier;
        ImageButton supprimer;


        public MyViewHolder(View itemView) {
            super(itemView);
            infoTransaction = itemView.findViewById(R.id.infoTransaction);
            numero = itemView.findViewById(R.id.numero);
            nom = itemView.findViewById(R.id.nom);
            no_tel = itemView.findViewById(R.id.no_tel);
            modifier = itemView.findViewById(R.id.modifier);
            supprimer = itemView.findViewById(R.id.supprimer);;
        }
    }
}
