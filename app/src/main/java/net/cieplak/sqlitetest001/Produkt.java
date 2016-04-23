package net.cieplak.sqlitetest001;

/**
 * Created by Tomasz on 18.04.2016.
 */
public class Produkt {
    private int _id;
    private String _nazwaprodukt;
    private int _ilosc;

    public Produkt(){

    }

    public Produkt(int id, String nazwaprodukt, int ilosc){
        this._id=id;
        this._nazwaprodukt=nazwaprodukt;
        this._ilosc=ilosc;
    }

    public Produkt(String nazwaprodukt, int ilosc){
        this._nazwaprodukt=nazwaprodukt;
        this._ilosc=ilosc;
    }

    public Produkt(String nazwaprodukt){
        this._nazwaprodukt=nazwaprodukt;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_nazwaprodukt() {
        return _nazwaprodukt;
    }

    public void set_nazwaprodukt(String _nazwaprodukt) {
        this._nazwaprodukt = _nazwaprodukt;
    }

    public int get_ilosc() {
        return _ilosc;
    }

    public void set_ilosc(int _ilosc) {
        this._ilosc = _ilosc;
    }
}
