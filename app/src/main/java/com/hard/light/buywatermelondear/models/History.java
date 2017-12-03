package com.hard.light.buywatermelondear.models;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class History {
    static ArrayList<Product> products = new ArrayList<>();

    static final int size = 3;

    public static void add(Product p){
        if (!(products.contains(p))) {
            products.add(0, p);
            if (products.size() > size)
                products.remove(products.size() - 1);
        }
    }

    public static ArrayList<Product> getProducts(){
        return products;
    }
}
