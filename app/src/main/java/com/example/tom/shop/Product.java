package com.example.tom.shop;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by Tom on 01.04.2018.
 */

public class Product implements Parcelable {
    final static String LOG_TAG = "myLogs";
    String name;
    int price;

      public Product(String _name, int _price) {
          Log.d(LOG_TAG, "MyObject(String _s, int _i)");
        name = _name;
        price = _price;
    }
    public int describeContents() {
        return 0;
    }

    // упаковываем объект в Parcel
    public void writeToParcel(Parcel parcel, int flags) {
        Log.d(LOG_TAG, "writeToParcel");
        parcel.writeString(name);
        parcel.writeInt(price);
    }

    public static final Parcelable.Creator<Product> CREATOR = new Parcelable.Creator<Product>() {
        // распаковываем объект из Parcel
        public Product createFromParcel(Parcel in) {
            Log.d(LOG_TAG, "createFromParcel");
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    // конструктор, считывающий данные из Parcel
    private Product(Parcel parcel) {
        Log.d(LOG_TAG, "MyObject(Parcel parcel)");
        name = parcel.readString();
        price = parcel.readInt();
    }

}
