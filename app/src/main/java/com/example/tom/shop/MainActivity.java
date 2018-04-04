package com.example.tom.shop;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends Activity implements DatePickerDialog.OnDateSetListener {
    ArrayList<Product> products = new ArrayList<Product>();
    AdapterBox boxAdapter;
    Button b_pick;
    TextView tv_result;
    int day,month,year;
    int dayFinal,monthFinal,yearFinal;
    String display;

    /**
     * Called when the activity is first created.
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button swith1 = (Button) findViewById(R.id.btn_Cart);

        b_pick= (Button) findViewById(R.id.b_pick);
        tv_result=(TextView) findViewById(R.id.tv_result);


        // создаем адаптер
        fillData();
        boxAdapter = new AdapterBox(this, products);

        // настраиваем список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(boxAdapter);


        swith1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Cart.class));
            }});
    }

    // генерируем данные для адаптера
    void fillData() {
        // Добавление продукта
        for (int i = 1; i <= 20; i++) {
            products.add(new Product("Product " + i, i * 1000));
        }


    }

    public void onClickData(View v) {
        Calendar c= Calendar.getInstance();
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day=c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, MainActivity.this,year,month,day);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal=i;
        monthFinal=i1+1;
        dayFinal=i2;
        b_pick.setText(dayFinal+"/"+monthFinal+"/"+yearFinal);
        display = b_pick.getText().toString();


        Intent intent = new Intent(this,Cart.class);
        intent.putExtra("mydata",display);
        Toast.makeText(this, "Красавчик! Ты выбрал дату: " +"\n"+ display, Toast.LENGTH_SHORT).show();
    }
}