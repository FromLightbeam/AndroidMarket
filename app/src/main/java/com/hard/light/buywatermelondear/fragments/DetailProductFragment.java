package com.hard.light.buywatermelondear.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hard.light.buywatermelondear.R;
import com.hard.light.buywatermelondear.database.DBHelper;
import com.hard.light.buywatermelondear.database.DBManager;
import com.hard.light.buywatermelondear.helper.DownloadImagesTask;
import com.hard.light.buywatermelondear.helper.OnSwipeTouchListener;
import com.hard.light.buywatermelondear.models.History;
import com.hard.light.buywatermelondear.models.Product;
import com.hard.light.buywatermelondear.models.ShoppingCart;

import java.util.ArrayList;


public class DetailProductFragment extends Fragment {

    public DetailProductFragment() {
    }

    TextView nameView;
    TextView countView;
    ImageView imageView;
    TextView priceView;
    TextView descriptionView;
    Button buyButton;
    Button nextButton;
    Button plusButton;
    Button minusButton;
    View view;
    Product product;
    ArrayList<Product> products;

    DBManager dbManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detail_product, container, false);

        nameView = view.findViewById(R.id.name);
        countView = view.findViewById(R.id.count);
        imageView = view.findViewById(R.id.image);

        priceView = view.findViewById(R.id.price);
        descriptionView = view.findViewById(R.id.description);
        buyButton = view.findViewById(R.id.buy_button);
        nextButton = view.findViewById(R.id.next_button);
        plusButton = view.findViewById(R.id.inc_count_button);
        minusButton = view.findViewById(R.id.dec_count_button);

        dbManager = new DBManager(getContext().getApplicationContext());


//
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!(dbManager.getProducts().contains(product))) {
                    dbManager.addProduct(product);
                    ShoppingCart.setProducts(dbManager.getProducts());
                    buyButton.setText(getString(R.string.already_in_cart));
                }
                else{
                    dbManager.deleteProduct(product);
                    ShoppingCart.setProducts(dbManager.getProducts());
                    buyButton.setText(getString(R.string.buy));
                }

            }
        });
//
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (product != null) {
                    int count = product.getCount();
                    count++;
                    countView.setText(String.valueOf(count));
                    product.setCount(count);

                    dbManager.updateProduct(product);

                }
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (product != null) {
                    int count = product.getCount();
                    if (count > 1) {
                        count--;
                        countView.setText(String.valueOf(count));
                        product.setCount(count);

                        dbManager.updateProduct(product);

                    }
                }
            }
        });



        nextButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                String sDown = "";
                String sMove = "";
                String sUp = "";
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажатие
                        sDown = "Down: " + x + "," + y;
                        sMove = ""; sUp = "";
                        break;
                    case MotionEvent.ACTION_MOVE: // движение
                        sMove = "Move: " + x + "," + y;
                        break;
                    case MotionEvent.ACTION_UP: // отпускание
                    case MotionEvent.ACTION_CANCEL:
                        sMove = "";
                        sUp = "Up: " + x + "," + y;
                        if (products != null)
                            setProduct(nextProduct(products, product));
                        break;
                }
                Log.d("TouchLog", sDown + "\n" + sMove + "\n" + sUp);
                return true;
            }
        });

        imageView.setOnTouchListener(new OnSwipeTouchListener(getContext()) {
            public void onSwipeRight() {
            }
            public void onSwipeLeft() {
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

        DownloadImagesTask task = new DownloadImagesTask();
        task.execute(imageView);

        nameView.setText(product.getName());
        countView.setText(String.valueOf(product.getCount()));
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
