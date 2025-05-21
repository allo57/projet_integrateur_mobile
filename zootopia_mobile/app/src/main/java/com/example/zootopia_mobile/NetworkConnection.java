/***************************************************
 *
 * Fichier : NetworkConnection
 * Auteur : Jacob Perreault
 * Fonctionnalité : Objet qui permet de faire la vérification du statut de connexion à l'internet
 * Date : 9 mai 2025
 *
 ***************************************************/
package com.example.zootopia_mobile;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

public class NetworkConnection {
    public boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }

        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork == null) {
            return false;
        }

        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);

        return networkCapabilities != null;
    }
}
