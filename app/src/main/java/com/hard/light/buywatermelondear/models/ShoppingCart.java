package com.hard.light.buywatermelondear.models;


import java.util.ArrayList;

public class ShoppingCart {

    static ArrayList<Product> products  = new ArrayList<>();

    public static void add(Product p){
        products.add(p);
    }

    public static ArrayList<Product> getProducts(){
        return products;
    }

    public static void setProducts(ArrayList<Product> ps){
        products = ps;
    }

    public static void delete(Product p){
        products.remove(p);
    }


}
