package com.hard.light.buywatermelondear.models;


import android.view.View;
import android.view.View.OnClickListener;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;


public class Product implements Serializable {

    static AtomicInteger nextId = new AtomicInteger();
    private int id;

    String name;
    String pictureURL;
    BigDecimal price;
    String description;
    int category_id;
    int count = 1;

    public Product(String name, String url, BigDecimal price, String description, int category_id ){
        id = nextId.incrementAndGet();
        this.name = name;
        this.pictureURL = url;
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);;
        this.description = description;
        this.category_id = category_id;
    }

    public Product(int id, String name, String url, BigDecimal price, String description, int category_id ){
        this.id = id;
        this.name = name;
        this.pictureURL = url;
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);;
        this.description = description;
        this.category_id = category_id;
    }

    public Product(int id, String name, String url, String price, String description, int count, int category_id ){
        this.id = id;
        this.name = name;
        this.pictureURL = url;
        String value = price.split(Pattern.quote("$"))[0];
        BigDecimal decvalue = new BigDecimal(value);
        this.price = decvalue.setScale(2, RoundingMode.HALF_EVEN);;
        this.description = description;
        this.count = count;
        this.category_id = category_id;
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

    public int getCount(){
        return this.count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public int getCategoryId(){
        return category_id;
    }
}
