package com.hard.light.buywatermelondear.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hard.light.buywatermelondear.R;
import com.hard.light.buywatermelondear.adapters.MyProductListAdapter;
import com.hard.light.buywatermelondear.adapters.PriceListAdapter;
import com.hard.light.buywatermelondear.database.DBManager;
import com.hard.light.buywatermelondear.models.History;
import com.hard.light.buywatermelondear.models.ShoppingCart;

public class PriceListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        ListView productsView = (ListView) findViewById(R.id.products);
        DBManager dbManager = new DBManager(getApplicationContext());

        PriceListAdapter adapter = new PriceListAdapter(this, dbManager.getPriceList());


        productsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(PriceListActivity.this, DetailProductActivity.class);

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
