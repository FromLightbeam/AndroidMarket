package com.hard.light.buywatermelondear.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hard.light.buywatermelondear.R;
import com.hard.light.buywatermelondear.adapters.MyProductListAdapter;
import com.hard.light.buywatermelondear.models.ShoppingCart;

public class ShoppingCartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        ListView productsView = (ListView) findViewById(R.id.products);

        MyProductListAdapter adapter = new MyProductListAdapter(this, ShoppingCart.getProducts());

        productsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ShoppingCartActivity.this, DetailProductActivity.class);

                Bundle b = new Bundle();
                b.putSerializable("product", ShoppingCart.getProducts().get(position));
                intent.putExtras(b);

                startActivity(intent);
                finish();
            }
        });

        productsView.setAdapter(adapter);

    }
}
