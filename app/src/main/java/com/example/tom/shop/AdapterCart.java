package com.example.tom.shop;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by ebalabanova on 02.04.18.
 */

public class AdapterCart extends BaseAdapter {
    Context ctx;
    LayoutInflater Inflater;
    ArrayList<Product> objects;

    AdapterCart(Context context, ArrayList<Product> products) {
        ctx = context;
        objects = products;
        Inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return objects.size();
    }

    // элемент по позиции
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    // товар по позиции
    Product getProduct(int position) {
        return ((Product) getItem(position));
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = Inflater.inflate(R.layout.item_cart, parent, false);
        }

        Product p = getProduct(position);
        ((TextView) view.findViewById(R.id.tvDescr)).setText(p.name);
        ((TextView) view.findViewById(R.id.tvPrice)).setText(p.price);
        return view;



    }
}



