/***************************************************
 *
 * Fichier : RecyclerReservation
 * Auteur : Jacob Perreault
 * Fonctionnalité : Ajoute le nom, le numéro de téléphone et le numéro de réservaton à une liste de réservation
 * Date : 9 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile.reservation;

import android.app.admin.NetworkEvent;
import android.content.Context;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zootopia_mobile.NetworkConnection;
import com.example.zootopia_mobile.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerReservation extends RecyclerView.Adapter<RecyclerReservation.MyViewHolder>{
    private List<Reservation> _reservations;
    private Context context;

    public RecyclerReservation(Context context, List<Reservation> reservations) {
        this.context = context;
        this._reservations = reservations != null ? reservations : new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.activity_recycler_reservation, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.numero.setText("Numéro de la réservation : " + this._reservations.get(position).get_id_reservation());
        StringBuilder no_tel = new StringBuilder("(" + this._reservations.get(position).get_no_tel());
        no_tel.insert(4,") ");
        no_tel.insert(9,"-");


        holder.nom.setText("Nom de la réservation : " + this._reservations.get(position).get_nom());
        holder.no_tel.setText("Numéro de la contact : " + no_tel.toString());
        holder.modifier.setImageResource(R.drawable.edit);
        holder.modifier.setBackground(null);
        holder.supprimer.setImageResource(R.drawable.delete);
        holder.supprimer.setBackground(null);

        holder.infoTransaction.setOnClickListener(v-> {
            Intent intent = new Intent(context, DetailReservtion.class);
            intent.putExtra("id_reservation", this._reservations.get(position).get_id_reservation());
            intent.putExtra("id_utilisateur", this._reservations.get(position).get_id_utilisateur());
            context.startActivity(intent);
        });

        holder.modifier.setOnClickListener(v-> {
            Intent intent = new Intent(context, ModifierReservation.class);
            intent.putExtra("id_reservation", this._reservations.get(position).get_id_reservation());
            intent.putExtra("id_utilisateur", this._reservations.get(position).get_id_utilisateur());
            context.startActivity(intent);
        });

        holder.supprimer.setOnClickListener(v-> {
            NetworkConnection network = new NetworkConnection();
            if (network.isConnected(context)) {
                Intent intent = new Intent(context, SupprimerReservation.class);
                intent.putExtra("id_reservation", this._reservations.get(position).get_id_reservation());
                intent.putExtra("id_utilisateur", this._reservations.get(position).get_id_utilisateur());
                context.startActivity(intent);
            }
            else {
                Toast.makeText(context, "Veuillez-vous connecter à l'internet pour pouvoir supprimer une réservation.", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    public int getItemCount() {
        return this._reservations.size();
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
