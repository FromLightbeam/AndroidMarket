package com.hard.light.buywatermelondear.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hard.light.buywatermelondear.R;
import com.hard.light.buywatermelondear.adapters.MyProductListAdapter;
import com.hard.light.buywatermelondear.fragments.DetailProductFragment;
import com.hard.light.buywatermelondear.models.Category;
import com.hard.light.buywatermelondear.models.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CategoryActivity extends BaseActivity {

    private Category category;
    private ArrayList<Product> products = new ArrayList<>();
    private DetailProductFragment detailFragment;

    private ListView productsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intent =  getIntent();
        category = (Category) intent.getSerializableExtra("category");

        productsView = (ListView) findViewById(R.id.products);

        getProductFromServer();

    }

    private void initByData(){
        FragmentManager fragmentManager = getSupportFragmentManager();

        detailFragment = (DetailProductFragment) fragmentManager
                .findFragmentById(R.id.product_fragment);

        MyProductListAdapter adapter = new MyProductListAdapter(this, products);

        if (!(products.isEmpty())) {
            detailFragment.setProduct(products.get(0));
            detailFragment.setProducts(products);
        }

        productsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Выводим нужную информацию
                if (detailFragment != null) {
                    detailFragment.setProduct(products.get(position));
                    detailFragment.setProducts(products);
                }
            }
        });

        productsView.setAdapter(adapter);
    }

    private void getProductFromServer() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:8000/storage/products/?pk=" + category.getID();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try{
                            // Loop through the array elements
                            for(int i=0;i<response.length();i++){
                                // Get current json object
                                JSONObject category = response.getJSONObject(i);
                                JSONObject fields = category.getJSONObject("fields");
                                String name = fields.getString("name");
                                String pictureURL = fields.getString("pictureURL");
                                String descrip = fields.getString("description");
                                BigDecimal price = new BigDecimal(fields.getString("price"));
                                int id = category.getInt("pk");
                                products.add(new Product(id, name, pictureURL, price, descrip));
                                Log.d("prod", String.valueOf(products));
                                //                                Log.d("res", category.getString("fields"));
//                                String name = category.getJSONObject("fields").getString("name");

//                                categories.add(new Category(name, id));
                            }

                            initByData();

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        products = category.getProducts();
                        initByData();
                    }
                }
        );

        queue.add(jsonArrayRequest);
        Log.d("proda", String.valueOf(products));
    }
}
