package com.hard.light.buywatermelondear.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hard.light.buywatermelondear.R;
import com.hard.light.buywatermelondear.activity.ShoppingCartActivity;
import com.hard.light.buywatermelondear.helper.DownloadImagesTask;
import com.hard.light.buywatermelondear.models.Category;
import com.hard.light.buywatermelondear.models.History;
import com.hard.light.buywatermelondear.models.Product;
import com.hard.light.buywatermelondear.models.ShoppingCart;

import java.math.BigDecimal;
import java.util.ArrayList;


public class DetailProductFragment extends Fragment {

    public DetailProductFragment() {
    }

    TextView nameView;
    ImageView imageView;
    TextView priceView;
    TextView descriptionView;
    Button buyButton;
    Button nextButton;
    View view;
    Product product;
    ArrayList<Product> products;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_product, container, false);

        nameView = view.findViewById(R.id.name);
        imageView = view.findViewById(R.id.image);
        priceView = view.findViewById(R.id.price);
        descriptionView = view.findViewById(R.id.description);
        buyButton = view.findViewById(R.id.buy_button);
        nextButton = view.findViewById(R.id.next_button);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(ShoppingCart.getProducts().contains(product))) {
                    ShoppingCart.add(product);
                    buyButton.setText(getString(R.string.already_in_cart));
                }
                else{
                    ShoppingCart.getProducts().remove(product);
                    buyButton.setText(getString(R.string.buy));
                }
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (products != null)
                    setProduct(nextProduct(products, product));
            }
            });

        return view;
    }

    public static Product nextProduct(ArrayList<Product> prodList, Product p){
        int index = prodList.indexOf(p);
        if (index == prodList.size() - 1)
            return prodList.get(0);
        else
            return prodList.get(index + 1);
    }

    public void setProducts(ArrayList pl){
        products = pl;
    }

    public void setProduct(Product p){
        product = p;
        History.add(product);
        imageView.setTag(product.getPictureURL());

        new DownloadImagesTask().execute(imageView);
        nameView.setText(product.getName());
        priceView.setText(product.getPrice());
        descriptionView.setText(product.getDescription());

        if ((ShoppingCart.getProducts().contains(product))) {
            buyButton.setText(getString(R.string.already_in_cart));
        }
        else{
            buyButton.setText(getString(R.string.buy));
        }
    }
}
