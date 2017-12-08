package com.hard.light.buywatermelondear.models;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Category implements Serializable {
    static AtomicInteger nextId = new AtomicInteger();
    private int id;

    String name;
    ArrayList<Product> products = new ArrayList<>();

    public Category(String name){
        id = nextId.incrementAndGet();
        this.name = name;
    }

    public Category(String name, int id){
        this.id = id;
        this.name = name;
    }

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

    public void setProducts(ArrayList<Product> products){
         this.products = products;
    }

    public int getID(){
        return id;
    }

}
