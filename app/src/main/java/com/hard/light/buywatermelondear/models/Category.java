package com.hard.light.buywatermelondear.models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Category implements Serializable {
    static AtomicInteger nextId = new AtomicInteger();
    private int id;

    String name;
    ArrayList<Product> products;


    public Category(String name, ArrayList<Product> products){
        id = nextId.incrementAndGet();
        this.name = name;
        this.products = products;
    }

    String getName(){
        return name;
    }

    @Override
    public String toString(){
        return name;
    }

    public ArrayList<Product> getProducts(){
        return products;
    }

    public int getID(){
        return id;
    }

}
