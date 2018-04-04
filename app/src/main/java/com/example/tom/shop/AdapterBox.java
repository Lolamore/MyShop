package com.example.tom.shop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.Serializable;
import java.util.ArrayList;

import static android.R.attr.name;

/**
 * Created by Tom on 01.04.2018.
 */

public class AdapterBox extends BaseAdapter {
    Context ctx;
    LayoutInflater Inflater;
    ArrayList<Product> objects;
    ArrayList<Product> box = new ArrayList<Product>();
    CompoundButton buttonView;

    AdapterBox(Context context, ArrayList<Product> products) {
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
    Product getProduct ( int position){
        return ((Product) getItem(position));
    }

    // пункт списка
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {view = Inflater.inflate(R.layout.item, parent, false);
        }

        Product p = getProduct(position);

        // заполняем View в пункте списка данными из товаров: наименование, цена
        // и картинка
        ((TextView) view.findViewById(R.id.tvDescr)).setText(p.name);
        ((TextView) view.findViewById(R.id.tvPrice)).setText("очки: "+p.price);
        //((ImageView) view.findViewById(R.id.ivImage)).setImageResource(p.image);
        Button btn_cart = (Button) view.findViewById(R.id.cartto);
        btn_cart.setOnClickListener(oMyButton);
        btn_cart.setTag(String.valueOf(position));
        return view;
    }

    View.OnClickListener oMyButton = new View.OnClickListener() {

        public void onClick(View view) {
            Intent intent = new Intent(ctx, Cart.class);
            String a = view.getTag().toString();
            int posit = Integer.parseInt(a);
            Product obj = new Product(getProduct(posit).name, getProduct(posit).price);
             int u=getProduct(posit).price;
            String str = Integer.toString(u);
            intent.putExtra("name", getProduct(posit).name);
            intent.putExtra("price", str);
            Toast.makeText(ctx, "Добавлено действие: "+"\n" + getProduct(posit).name+"\n"+"Очки за действие : "+u, Toast.LENGTH_SHORT).show();
           ctx.startActivity(intent);//Открытие корзины
        }
    };
    }



