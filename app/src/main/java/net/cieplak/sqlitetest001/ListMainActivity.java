package net.cieplak.sqlitetest001;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListMainActivity extends AppCompatActivity {
    ArrayList<Produkt> produktArrayList;
    ProduktAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_main);
        MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);
        produktArrayList = dbHandler.getAllProducts();
        ListView listView = (ListView)findViewById(R.id.listView);
        adapter = new ProduktAdapter(this,R.layout.wiersz_fragment,produktArrayList);
        listView.setAdapter(adapter);
    }
}
