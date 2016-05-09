package net.cieplak.sqlitetest001;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SqlmainActivity extends AppCompatActivity {

    private TextView idProdukt;
    private EditText nazwaProdukt;
    private EditText iloscProdukt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlmain);

        idProdukt = (TextView) findViewById(R.id.productID);
        nazwaProdukt = (EditText) findViewById(R.id.productName);
        iloscProdukt = (EditText) findViewById(R.id.productQuantity);
    }

    public void dodajProdukt(View view){
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
        int ilosc = Integer.parseInt(iloscProdukt.getText().toString());
        Produkt produkt = new Produkt(nazwaProdukt.getText().toString(),ilosc);
        dbHandler.addProduct(produkt);
        nazwaProdukt.setText("");
        iloscProdukt.setText("");
    }

    public void znajdzProdukt(View view){
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
        Produkt produkt = dbHandler.findProduct(nazwaProdukt.getText().toString());

        if (produkt!=null){
            idProdukt.setText(String.valueOf(produkt.get_id()));
            iloscProdukt.setText(String.valueOf(produkt.get_ilosc()));
        }else {
            idProdukt.setText("Brak produktu");
        }
    }

    public void ususnProdukt(View view){
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
        boolean wynik = dbHandler.deleteProduct(nazwaProdukt.getText().toString());
        if (wynik){
            idProdukt.setText("Produkt usunięty");
            nazwaProdukt.setText("");
            iloscProdukt.setText("");
        }else
        {
            idProdukt.setText("Brak produktu");
        }
    }

    public void onClickBtn(View view){
        Intent i = new Intent(this,ListMainActivity.class);
        startActivity(i);
    }
}
