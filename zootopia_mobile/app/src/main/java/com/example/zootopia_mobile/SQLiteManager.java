package com.example.zootopia_mobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteManager extends SQLiteOpenHelper
{
    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME = "Zootopia";
    private static final int DATABASE_VERSION = 3;

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

    public void ajoutBillet(long id_billet, String nom, String description, double prix) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_billet", id_billet);
        values.put("nom", nom);
        values.put("description", description);
        values.put("prix", prix);
        db.insert("billets", null, values);
    }

    public void ajoutReservation(long id_reservation, String nom, long no_tel, String date_heure, int nb_personnes, String note, String description, int id_etatReservation, long id_utilisateur) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_reservation", id_reservation);
        values.put("nom", nom);
        values.put("no_tel", no_tel);
        values.put("date_heure", date_heure);
        values.put("nb_personnes", nb_personnes);
        values.put("note", note);
        values.put("description", description);
        values.put("id_etat_reservation", id_etatReservation);
        values.put("id_utilisateur", id_utilisateur);
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
}
