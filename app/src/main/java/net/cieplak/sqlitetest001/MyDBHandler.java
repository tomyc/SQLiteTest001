package net.cieplak.sqlitetest001;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Tomasz on 18.04.2016.
 */
public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "produktDB.db";
    private static final String TABLE_PRODUCTS = "produkty";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAZWAPRODUCT = "nazwaprodukt";
    private static final String COLUMN_ILOSC = "ilosc";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE="CREATE TABLE "+TABLE_PRODUCTS+"("+COLUMN_ID+" INTEGER PRIMARY KEY,"+
                COLUMN_NAZWAPRODUCT+" TEXT,"+COLUMN_ILOSC+" INTEGER"+")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCTS);
        onCreate(db);
    }

    public void addProduct(Produkt produkt){
        ContentValues wartosci = new ContentValues();
        wartosci.put(COLUMN_NAZWAPRODUCT,produkt.get_nazwaprodukt());
        wartosci.put(COLUMN_ILOSC,produkt.get_ilosc());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS,null,wartosci);
        db.close();
    }
    public Produkt findProduct(String nazwaprodukt){
        String zapytanie = "SELECT * FROM "+TABLE_PRODUCTS+" WHERE "+COLUMN_NAZWAPRODUCT+" =\""+nazwaprodukt+"\"";
        SQLiteDatabase  db = this.getWritableDatabase();
        Cursor wskaznik = db.rawQuery(zapytanie,null);
        Produkt produkt = new Produkt();

        if (wskaznik.moveToFirst()){
            wskaznik.moveToFirst();
            produkt.set_id(Integer.parseInt(wskaznik.getString(0)));
            produkt.set_nazwaprodukt(wskaznik.getString(1));
            produkt.set_ilosc(Integer.parseInt(wskaznik.getString(2)));
        }else {
            produkt=null;
        }
        db.close();
        return produkt;
    }

    public boolean deleteProduct(String nazwaprodukt){
        boolean wynik = false;
        String zapytanie = "SELECT * FROM "+TABLE_PRODUCTS+" WHERE "+COLUMN_NAZWAPRODUCT+" =\""+nazwaprodukt+"\"";
        SQLiteDatabase  db = this.getWritableDatabase();
        Cursor wskaznik = db.rawQuery(zapytanie,null);
        Produkt produkt = new Produkt();
        if (wskaznik.moveToFirst()) {
            produkt.set_id(Integer.parseInt(wskaznik.getString(0)));
            db.delete(TABLE_PRODUCTS,COLUMN_ID+" = ?",new String[]{String.valueOf(produkt.get_id())});
            wskaznik.close();
            wynik=true;
        }
        db.close();
        return wynik;
    }

    public ArrayList<Produkt> getAllProducts(){
        ArrayList<Produkt> produkty = new ArrayList<Produkt>();
        String zapytanie = "SELECT * FROM "+TABLE_PRODUCTS;
        SQLiteDatabase  db = this.getWritableDatabase();
        Cursor wskaznik = db.rawQuery(zapytanie,null);
        wskaznik.moveToFirst();
        while (!wskaznik.isAfterLast()){
            Produkt produktTM = cursorToProdukt(wskaznik);
            produkty.add(produktTM);
            wskaznik.moveToNext();
        }
        db.close();
        return produkty;
    }

    private Produkt cursorToProdukt(Cursor cursor){
        Produkt produktC = new Produkt();
        produktC.set_id(cursor.getInt(0));
        produktC.set_nazwaprodukt(cursor.getString(1));
        produktC.set_ilosc(cursor.getInt(2));
        return produktC;
    }
}
