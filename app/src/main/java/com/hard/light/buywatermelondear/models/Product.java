package com.hard.light.buywatermelondear.models;


import android.view.View;
import android.view.View.OnClickListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicInteger;


public class Product implements Serializable {

    static AtomicInteger nextId = new AtomicInteger();
    private int id;

    String name;
    String pictureURL;
    BigDecimal price;
    String description;

    public Product(String name, String url, BigDecimal price, String description ){
        id = nextId.incrementAndGet();
        this.name = name;
        this.pictureURL = url;
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);;
        this.description = description;
    }

    public Product(int id, String name, String url, BigDecimal price, String description ){
        this.id = id;
        this.name = name;
        this.pictureURL = url;
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return name;
    }

    public String getPrice(){

        return price + "$";
    }

    public String getPictureURL(){
        return pictureURL;
    }

    public String getDescription(){
        return description;
    }

    public int getID(){
        return id;
    }


    @Override
    public boolean equals(Object v) {
        boolean retVal = false;

        if (v instanceof Product){
            Product ptr = (Product) v;
            retVal = ptr.name.equals(this.name);
        }

        return retVal;
    }
}
