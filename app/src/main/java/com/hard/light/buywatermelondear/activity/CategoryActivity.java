package com.hard.light.buywatermelondear.activity;

import android.content.Intent;
import android.support.annotation.ArrayRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hard.light.buywatermelondear.MainActivity;
import com.hard.light.buywatermelondear.R;
import com.hard.light.buywatermelondear.adapters.MyProductListAdapter;
import com.hard.light.buywatermelondear.fragments.DetailProductFragment;
import com.hard.light.buywatermelondear.models.Category;
import com.hard.light.buywatermelondear.models.Product;

import java.util.ArrayList;

public class CategoryActivity extends BaseActivity {

    private Category category;

    private DetailProductFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intent =  getIntent();
        category = (Category) intent.getSerializableExtra("category");

        ListView productsView = (ListView) findViewById(R.id.products);

        MyProductListAdapter adapter = new MyProductListAdapter(this, category.getProducts());

        FragmentManager fragmentManager = getSupportFragmentManager();

        detailFragment = (DetailProductFragment) fragmentManager
                .findFragmentById(R.id.product_fragment);

        if (!(category.getProducts().isEmpty())) {
            detailFragment.setProduct(category.getProducts().get(0));
            detailFragment.setProducts(category.getProducts());
        }

        productsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Выводим нужную информацию
                if (detailFragment != null) {
                    detailFragment.setProduct(category.getProducts().get(position));
                    detailFragment.setProducts(category.getProducts());
                }


            }
        });

        productsView.setAdapter(adapter);
    }
}
