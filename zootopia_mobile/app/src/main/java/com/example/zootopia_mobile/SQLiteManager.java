package com.example.zootopia_mobile;

import static java.time.MonthDay.now;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.zootopia_mobile.billets.Billet;
import com.example.zootopia_mobile.magasin.BilletPanier;
import com.example.zootopia_mobile.reservation.Reservation;

import java.util.ArrayList;
import java.util.List;

public class SQLiteManager extends SQLiteOpenHelper
{
    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME = "Zootopia";
    private static final int DATABASE_VERSION = 5;

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context)
    {
        if(sqLiteManager == null)
            sqLiteManager = new SQLiteManager(context);

        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createBilletsTable = "CREATE TABLE billets (" +
                "id_billet INTEGER PRIMARY KEY," +
                "nom TEXT," +
                "description TEXT," +
                "prix REAL" +
                ");";

        String createUsersTable = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT," +
                "code_postal TEXT," +
                "no_tel TEXT," +
                "id_role INTEGER," +
                "email TEXT," +
                "password TEXT" +
                ");";

        String createReservationsTable = "CREATE TABLE reservations (" +
                "id_reservation INTEGER PRIMARY KEY," +
                "nom TEXT," +
                "no_tel INTEGER," +
                "date_heure TIMESTAMP," +
                "nb_personnes INTEGER," +
                "note TEXT," +
                "description TEXT," +
                "id_etat_reservation INTEGER," +
                "id_utilisateur INTEGER," +
                "FOREIGN KEY(id_etat_reservation) REFERENCES etat_reservations(id_etat_reservation)," +
                "FOREIGN KEY(id_utilisateur) REFERENCES users(id)" +
                ");";

        String createTransactionsTable = "CREATE TABLE transactions (" +
                "id_transaction INTEGER PRIMARY KEY," +
                "date_heure TIMESTAMP," +
                "id_utilisateur INTEGER," +
                "FOREIGN KEY(id_utilisateur) REFERENCES users(id)" +
                ");";

        String createBilletsTransactionsTable = "CREATE TABLE billets_transactions (" +
                "id INTEGER PRIMARY KEY," +
                "id_transaction INTEGER," +
                "id_billet INTEGER," +
                "quantity INTEGER," +
                "FOREIGN KEY(id_transaction) REFERENCES transactions(id_transaction)," +
                "FOREIGN KEY(id_billet) REFERENCES billets(id_billet)" +
                ");";

        String createUtilisateurReservationTable = "CREATE TABLE utilisateur_reservation (" +
                "id INTEGER PRIMARY KEY," +
                "id_utilisateur INTEGER," +
                "id_reservation INTEGER," +
                "FOREIGN KEY(id_utilisateur) REFERENCES users(id)," +
                "FOREIGN KEY(id_reservation) REFERENCES reservations(id_reservation)" +
                ");";

        String createEtatReservationsTable = "CREATE TABLE etat_reservations (" +
                "id_etat_reservation INTEGER PRIMARY KEY," +
                "nom TEXT," +
                "description TEXT" +
                ");";

        sqLiteDatabase.execSQL(createBilletsTable);
        sqLiteDatabase.execSQL(createUsersTable);
        sqLiteDatabase.execSQL(createReservationsTable);
        sqLiteDatabase.execSQL(createTransactionsTable);
        sqLiteDatabase.execSQL(createBilletsTransactionsTable);
        sqLiteDatabase.execSQL(createUtilisateurReservationTable);
        sqLiteDatabase.execSQL(createEtatReservationsTable);
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS utilisateur_reservation;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS billets_transactions;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS transactions;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS reservations;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS users;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS billets;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS etat_reservations;");

        onCreate(sqLiteDatabase);
    }

    public void ajoutUser(String name, String code_postal, String no_tel, int id_role, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("code_postal", code_postal);
        values.put("no_tel", no_tel);
        values.put("id_role", id_role);
        values.put("email", email);
        values.put("password", password);
        db.insert("users", null, values);
    }
    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;
        String[] selectionArgs = { String.valueOf(id) };

        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE id = ?", selectionArgs);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int userId = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String username = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String code_postal = cursor.getString(cursor.getColumnIndexOrThrow("code_postal"));
                String no_tel = cursor.getString(cursor.getColumnIndexOrThrow("no_tel"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));

                user = new User(userId, code_postal, no_tel, username, email, password);
            }
            cursor.close();
        }

        return user;
    }

    public void updateUser(int id, String nom, String tel, String email, String postal, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", nom);
        values.put("code_postal", postal);
        values.put("no_tel", tel);
        values.put("email", email);
        values.put("password", password);

        db.update("users", values, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public int getIdUser(String courriel, String motdepasse) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {courriel, motdepasse};
        Cursor cursor = db.rawQuery("SELECT id FROM users WHERE email = ? AND password = ?", selectionArgs);

        int id = -1;
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }
        cursor.close();
        return id;
    }
    public boolean verifierUtilisateur(String courriel, String motdepasse) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {courriel, motdepasse};

        try (Cursor result = db.rawQuery(
                "SELECT * FROM users WHERE email = ? AND password = ?", selectionArgs)) {
            return result.moveToFirst();
        } catch (Exception e) {
            return false;
        }
    }

    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String stringid = String.valueOf(id);
        db.delete("users", "id = ?", new String[]{stringid});
    }

    public void ajoutBillet(long id_billet, String nom, String description, double prix) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        boolean existeDeja = false;
        Cursor cursor = db.rawQuery("SELECT * FROM billets WHERE id_billet = ?", new String[]{String.valueOf(id_billet)});
        if (cursor.moveToFirst()) {
            existeDeja = true;
        }
        cursor.close();

        if (!existeDeja){
            values.put("id_billet", id_billet);
            values.put("nom", nom);
            values.put("description", description);
            values.put("prix", prix);
            db.insert("billets", null, values);
        }

    }

    public void ajoutReservation(Reservation reservation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_reservation", reservation.get_id_reservation());
        values.put("nom", reservation.get_nom());
        values.put("no_tel", reservation.get_no_tel());
        values.put("date_heure", reservation.get_date_heure());
        values.put("nb_personnes", reservation.get_nb_personnes());
        values.put("note", reservation.get_note());
        values.put("id_etat_reservation", reservation.get_id_reservation());
        values.put("id_utilisateur", reservation.get_id_utilisateur());
        db.insert("reservations", null, values);
    }

    public void ajoutTransaction(long idTransaction, String date_heure, long id_utilisateur) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_transaction", idTransaction);
        values.put("date_heure", date_heure);
        values.put("id_utilisateur", id_utilisateur);
        db.insert("transactions", null, values);
    }

    public void ajoutBilletTransaction(long id, long id_transaction, long id_billet, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("id_transaction", id_transaction);
        values.put("id_billet", id_billet);
        values.put("quantity", quantity);
        db.insert("billets_transactions", null, values);
    }

    public void ajoutUtilisateurReservation(long id, long id_utilisateur, long id_reservation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("id_utilisateur", id_utilisateur);
        values.put("id_reservation", id_reservation);
        db.insert("utilisateur_reservation", null, values);
    }

    public ArrayList<Reservation> getReservations(int user_id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selectionArg[] = {String.valueOf(user_id)};

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM reservations WHERE id_utilisateur = ?", selectionArg)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id_reservation = result.getInt(0);
                    String nom = result.getString(1);
                    int no_tel = result.getInt(2);
                    String date_heure = result.getString(3);
                    int nb_personnes = result.getInt(4);
                    String note = result.getString(5);
                    int id_utilisateur = result.getInt(8);

                    Reservation newReservation = new Reservation();
                    newReservation.set_id_reservation(id_reservation);
                    newReservation.set_nom(nom);
                    newReservation.set_no_tel(no_tel);
                    newReservation.set_date_heure(date_heure);
                    newReservation.set_nb_personnes(nb_personnes);
                    newReservation.set_note(note);
                    newReservation.set_id_utilisateur(id_utilisateur);
                    Reservation.reservationArrayList.add(newReservation);
                }
                return Reservation.reservationArrayList;
            }
        }
        return null;
    }

    public Reservation getReservation(int reservation_id, int user_id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selectionArg[] = {String.valueOf(user_id), String.valueOf(reservation_id)};
        Reservation newReservation = new Reservation();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM reservations WHERE id_utilisateur = ? and id_reservation = ?", selectionArg)) {
            if (result.moveToFirst()) {
                int id_reservation = result.getInt(0);
                String nom = result.getString(1);
                int no_tel = result.getInt(2);
                String date_heure = result.getString(3);
                int nb_personnes = result.getInt(4);
                String note = result.getString(5);
                int id_utilisateur = result.getInt(8);

                newReservation.set_id_reservation(id_reservation);
                newReservation.set_nom(nom);
                newReservation.set_no_tel(no_tel);
                newReservation.set_date_heure(date_heure);
                newReservation.set_nb_personnes(nb_personnes);
                newReservation.set_note(note);
                newReservation.set_id_utilisateur(id_utilisateur);
                return newReservation;
            }
        }
        return null;
    }

    public void updateReservations(Reservation reservation) {
        SQLiteDatabase db = this.getWritableDatabase();
        String clause = "id_reservation = ? and id_utilisateur = ?";
        String selectionArg[] = {String.valueOf(reservation.get_id_reservation()), String.valueOf(reservation.get_id_utilisateur())};
        ContentValues values = new ContentValues();
        values.put("nom", reservation.get_nom());
        values.put("no_tel", reservation.get_no_tel());
        values.put("date_heure", reservation.get_date_heure());
        values.put("nb_personnes", reservation.get_nb_personnes());
        values.put("note", reservation.get_note());

        db.update("reservations", values, clause, selectionArg);
    }

    public void deleteReservation(int id_reservation) {
        SQLiteDatabase db = this.getReadableDatabase();
        String clause = "id_reservation = ?";
        String selectionArg[] = {String.valueOf(id_reservation)};

        db.delete("reservations", clause, selectionArg);
    }

    public List<BilletPanier> getBilletsPourTransaction(long idTransaction, int idUtilisateur) {
        SQLiteDatabase db = this.getWritableDatabase();
        List<BilletPanier> billetsPanier = new ArrayList<>();

        String query = "SELECT b.id_billet, b.nom, b.description, b.prix, bt.quantity " +
                "FROM billets b JOIN billets_transactions bt ON b.id_billet = bt.id_billet " +
                "WHERE bt.id_transaction = ?";

        Cursor cursor = db.rawQuery(query, new String[]{ String.valueOf(idTransaction) });

        if (cursor.moveToFirst()) {
            do {
                int idBillet = cursor.getInt(cursor.getColumnIndex("id_billet"));
                String nom = cursor.getString(cursor.getColumnIndex("nom"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                double prix = cursor.getDouble(cursor.getColumnIndex("prix"));
                int quantite = cursor.getInt(cursor.getColumnIndex("quantity"));

                Billet billet = new Billet(idBillet, nom, description, prix);
                BilletPanier billetPanier = new BilletPanier(billet, quantite);
                billetsPanier.add(billetPanier);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return billetsPanier;
    }

    public long getTransactionUtilisateur(int id_utilisateur) {
        long id_transaction = 0;
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT id_transaction FROM transactions WHERE id_utilisateur = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id_utilisateur)});

        if (cursor.moveToFirst()) {
            id_transaction = cursor.getLong(0);
        }

        cursor.close();
        return id_transaction;
    }

    public void supprimerBilletTransaction(long idTransaction, int idBillet) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "id_transaction = ? AND id_billet = ?";
        String[] whereArgs = new String[]{String.valueOf(idTransaction), String.valueOf(idBillet)};
        db.delete("billets_transactions", whereClause, whereArgs);
        db.close();
    }

    /**
     * Ajoute un billet au panier de l'utilisateur
     *
     * @param userId ID de l'utilisateur
     * @param billetId ID du billet à ajouter
     * @param quantite Nombre de billets à ajouter
     * @return true si l'opération a réussi, false sinon
     */
    public boolean ajouterBilletAuPanier(int userId, int billetId, int quantite) {
        // Vérifier si l'utilisateur est connecté
        if (userId == -1) {
            return false;
        }

        // Obtenir l'ID de transaction actuelle pour l'utilisateur
        long idTransaction = getTransactionUtilisateur(userId);

        // Si aucune transaction n'existe, en créer une nouvelle
        if (idTransaction == 0) {
            idTransaction = creerNouvelleTransaction(userId);
            if (idTransaction == -1) {
                return false; // Échec de la création de transaction
            }
        }

        // Vérifier si le billet existe déjà dans le panier
        BilletPanier billetExistant = getBilletDansPanier(idTransaction, billetId);

        if (billetExistant != null) {
            // Le billet existe déjà, mettre à jour la quantité
            int nouvelleQuantite = billetExistant.getQuantite() + quantite;
            return updateQuantiteBillet(idTransaction, billetId, nouvelleQuantite);
        } else {
            // Ajouter un nouveau billet au panier en utilisant la méthode existante
            ajouterTransaction(idTransaction, billetId, quantite, userId);
            return true;
        }
    }

    /**
     * Récupère un billet spécifique dans le panier
     * @param idTransaction ID de la transaction
     * @param billetId ID du billet
     * @return L'objet BilletPanier s'il existe, null sinon
     */
    public BilletPanier getBilletDansPanier(long idTransaction, int billetId) {
        SQLiteDatabase db = this.getReadableDatabase();
        BilletPanier billetPanier = null;

        String query = "SELECT b.id_billet, b.nom, b.description, b.prix, bt.quantity " +
                "FROM billets b JOIN billets_transactions bt ON b.id_billet = bt.id_billet " +
                "WHERE bt.id_transaction = ? AND b.id_billet = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(idTransaction), String.valueOf(billetId)});

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex("id_billet"));
            String nom = cursor.getString(cursor.getColumnIndex("nom"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            double prix = cursor.getDouble(cursor.getColumnIndex("prix"));
            int quantite = cursor.getInt(cursor.getColumnIndex("quantity"));

            Billet billet = new Billet(id, nom, description, prix);
            billetPanier = new BilletPanier(billet, quantite);
        }

        cursor.close();
        db.close();

        return billetPanier;
    }

    /**
     * Met à jour la quantité d'un billet dans le panier
     * @param idTransaction ID de la transaction
     * @param billetId ID du billet
     * @param nouvelleQuantite Nouvelle quantité du billet
     * @return true si la mise à jour est réussie, false sinon
     */
    public boolean updateQuantiteBillet(long idTransaction, int billetId, int nouvelleQuantite) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("quantity", nouvelleQuantite);

        int rowsAffected = db.update(
                "billets_transactions",
                values,
                "id_transaction = ? AND id_billet = ?",
                new String[]{String.valueOf(idTransaction), String.valueOf(billetId)}
        );

        db.close();
        return rowsAffected > 0;
    }

    /**
     * Crée une nouvelle transaction pour un utilisateur
     * @param userId ID de l'utilisateur
     * @return ID de la nouvelle transaction
     */
    public long creerNouvelleTransaction(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        long idTransaction = System.currentTimeMillis(); // Génère un ID unique basé sur le temps

        ContentValues values = new ContentValues();
        values.put("id_transaction", idTransaction);
        values.put("date_heure", String.valueOf(System.currentTimeMillis()));
        values.put("id_utilisateur", userId);

        long result = db.insert("transactions", null, values);
        db.close();

        return (result != -1) ? idTransaction : -1;
    }

    public void ajouterTransaction(long idTransaction, int idBillet, int quantite, int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("quantity", quantite);

        int rowsUpdated = db.update("billets_transactions", values,
                "id_transaction = ? AND id_billet = ?",
                new String[]{String.valueOf(idTransaction), String.valueOf(idBillet)});

        if (rowsUpdated == 0) {
            values.put("id_transaction", idTransaction);
            values.put("id_billet", idBillet);
            db.insert("billets_transactions", null, values);
        }

        boolean existeDeja = false;
        Cursor cursor = db.rawQuery("SELECT * FROM transactions WHERE id_transaction = ?", new String[]{String.valueOf(idTransaction)});
        if (cursor.moveToFirst()) {
            existeDeja = true;
        }
        cursor.close();

        if (!existeDeja) {
            ContentValues values1 = new ContentValues();
            values1.put("id_transaction", idTransaction);
            values1.put("date_heure", String.valueOf(now()));
            values1.put("id_utilisateur", id);
            db.insert("transactions", null, values1);
        }
    }
}
