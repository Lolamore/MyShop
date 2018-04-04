package com.example.tom.shop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewStructure;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import io.paperdb.Paper;

/**
 * Created by Tom on 01.04.2018.
 */

public class Cart extends Activity {
    private TextView Screen;
    SharedPreferences sPref;
    final String SAVED_TEXT = "saved_text";
    AdapterCart cartAdapter;
    ArrayList<Product> products = new ArrayList<Product>();
    double Total_price;
    String show = "";
    private SharedPreferences sPrefs;
    EditText editText;
    private int mCounter=0;
    int a;
    private SharedPreferences mSettings;
    private static final String PREFERENCES_NAME = "Statistics";
    ArrayList<String> nameEvent = new ArrayList();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        Screen = (TextView) findViewById(R.id.tvDescr);
        cartAdapter = new AdapterCart(this, products);
        sPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String json = new Gson().toJson(products);
        Paper.init(this);
        mSettings = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        final ArrayList<String> nameEvent = loadArrayList("nameEvent");

        Intent intent = getIntent();
        String mydata = intent.getStringExtra("data");
        ListView listView = (ListView) findViewById(R.id.lvcart);
        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nameEvent);
        listView.setAdapter(adapter);


        if (getIntent() == null) {
            Toast.makeText(this, "Вернись обратно и выбери уже хоть что-нибудь, ленивчек", Toast.LENGTH_SHORT).show();

        } else {
           if (intent.getStringExtra("name") == null) {

                String name = intent.getStringExtra("name");
                String price = intent.getStringExtra("price");
                a = Integer.parseInt(price); //Переводим в число строку, значение на кнопке
                mCounter = mCounter + a;
                nameEvent.add(name);
                adapter.notifyDataSetChanged();

                saveArrayList("nameEvent", nameEvent);
            }

            Screen.setText("Итого очков: " + mCounter);

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(Cart.this, "Ты уже долго на меня жмакаешь, это больно", Toast.LENGTH_LONG).show();
                    // Возвращает "истину", чтобы завершить событие клика, чтобы
                    // onListItemClick больше не вызывался
                    adapter.notifyDataSetChanged();
                    saveArrayList("nameEvent", nameEvent);
                    return true;
                }
            });
        }

        }

        //Cохранение данных mcounter
        @Override
        protected void onResume () {
            super.onResume();
            if (mSettings.contains(PREFERENCES_NAME)) {
                // Получаем число из настроек
                mCounter = mSettings.getInt(PREFERENCES_NAME, 0);
                // Выводим на экран данные из настроек
                mCounter = mCounter + a;
                Screen.setText("Итого: " + mCounter);
            }
        }
        @Override
        protected void onPause () {
            super.onPause();
            // Запоминаем данные
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putInt(PREFERENCES_NAME, mCounter);
            editor.apply();
        }

    private void saveArrayList(String name, ArrayList<String> list) {
        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        StringBuilder sb = new StringBuilder();
        for (String s : list) sb.append(s).append("<s>");
        sb.delete(sb.length() - 3, sb.length());
        editor.putString(name, sb.toString()).apply();
    }

    private ArrayList<String> loadArrayList(String name) {
        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        String[] strings = prefs.getString(name, "").split("<s>");
        ArrayList<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(strings));
        return list;
    }

    }









