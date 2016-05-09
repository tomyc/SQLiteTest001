package net.cieplak.sqlitetest001;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Tomasz on 08.05.2016.
 */
public class ProduktAdapter extends ArrayAdapter<Produkt> {
    ArrayList<Produkt> produktArrayList;
    LayoutInflater view;
    int Resource;
    ViewHolder holder;


    public ProduktAdapter(Context context, int resource, ArrayList<Produkt> objects) {
        super(context, resource, objects);
        view = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        produktArrayList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v==null){
            holder = new ViewHolder();
            v=view.inflate(Resource,null);
            holder.prodID=(TextView)v.findViewById(R.id.ID);
            holder.prodName=(TextView)v.findViewById(R.id.NazwaProd);
            holder.prodQua=(TextView)v.findViewById(R.id.Ilość);
            v.setTag(holder);
        }else {
            holder=(ViewHolder)v.getTag();
        }

        holder.prodID.setText("Produkt ID: "+produktArrayList.get(position).get_id());
        holder.prodName.setText("Produkt Nazwa: "+produktArrayList.get(position).get_nazwaprodukt());
        holder.prodQua.setText("Produkt Ilość: "+produktArrayList.get(position).get_ilosc());

        return v;
    }

    static class ViewHolder {
        public TextView prodID;
        public TextView prodName;
        public TextView prodQua;
    }
}
