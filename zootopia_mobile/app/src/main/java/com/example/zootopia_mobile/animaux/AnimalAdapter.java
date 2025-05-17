package com.example.zootopia_mobile.animaux;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zootopia_mobile.R;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.widget.Button;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

    private List<Animal> animaux;

    private Context context;

    public AnimalAdapter(Context context, List<Animal> animaux) {
        this.context = context;
        this.animaux = animaux;
    }

    public void setAnimaux(List<Animal> animaux) {
        this.animaux = animaux;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_recyler_animaux, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal animal = animaux.get(position);
        holder.buttonNomAnimal.setText(animal.getNom());
        holder.descriptionTextView.setText(couperTexte(animal.getDescription(), 25));

        String imageFileName = animal.getImage();
        File localImageFile = new File(context.getFilesDir(), imageFileName);

        //ouverture première fois est rough, mais après ça va
        if (localImageFile.exists()) {
            Glide.with(context)
                    .load(localImageFile)
                    .placeholder(R.drawable.animal_default)
                    .into(holder.imageView);
        } else {
            String imageUrl = "http://10.0.2.2:8000/img/" + imageFileName;

            Glide.with(context)
                    .asBitmap()
                    .load(imageUrl)
                    .placeholder(R.drawable.animal_default)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, Transition<? super Bitmap> transition) {
                            holder.imageView.setImageBitmap(resource);
                            saveImageToInternalStorage(resource, imageFileName);
                        }

                        @Override
                        public void onLoadCleared(Drawable placeholder) {
                            holder.imageView.setImageDrawable(placeholder);
                        }
                    });
        }

        holder.buttonNomAnimal.setOnClickListener(v -> {
            Intent intent = new Intent(context, AffichageAnimalUnique.class);
            intent.putExtra("nom", animal.getNom());
            intent.putExtra("description", animal.getDescription());
            intent.putExtra("date_naissance", animal.getDate_naissance());
            intent.putExtra("etat", animal.getEtat());
            intent.putExtra("etat_desc", animal.getEtatDesc());
            intent.putExtra("image", animal.getImage());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return animaux.size();
    }

    private String couperTexte(String texte, int longueurMax) {
        return texte.length() > longueurMax ? texte.substring(0, longueurMax) + "..." : texte;
    }

    private void saveImageToInternalStorage(Bitmap bitmap, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        try (FileOutputStream out = new FileOutputStream(file)) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class AnimalViewHolder extends RecyclerView.ViewHolder {
        Button buttonNomAnimal;
        TextView descriptionTextView;
        ImageView imageView;

        AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonNomAnimal = itemView.findViewById(R.id.buttonNomAnimal);
            imageView = itemView.findViewById(R.id.imageViewAnimal);
            descriptionTextView = itemView.findViewById(R.id.description_animal);
        }
    }
}


