package com.hard.light.buywatermelondear.models;


import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    static ArrayList<Product> products  = new ArrayList<>();

    public static void add(Product p){
        products.add(p);
    }

    public static ArrayList<Product> getProducts(){
        return products;
    }
}
