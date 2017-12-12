package com.hard.light.buywatermelondear;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hard.light.buywatermelondear.activity.BaseActivity;
import com.hard.light.buywatermelondear.activity.CategoryActivity;

import com.hard.light.buywatermelondear.database.DBManager;
import com.hard.light.buywatermelondear.helper.NotificationService;
import com.hard.light.buywatermelondear.models.Category;
//import com.android.volley.Request;
import com.hard.light.buywatermelondear.models.Product;

import android.widget.AdapterView.OnItemClickListener;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    ArrayList<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categories = new ArrayList<>();


        initObjects();
        startService(new Intent(this, NotificationService.class));
    }

    public void initByData(){

        ListView categoriesView = (ListView) findViewById(R.id.categories);


        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this,
                android.R.layout.simple_list_item_1, this.categories);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.clickclack);

        categoriesView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                mp.start();
                Bundle b = new Bundle();
                b.putSerializable("category", categories.get(position));
                intent.putExtras(b);

                startActivity(intent);
                finish();
            }
        });

        categoriesView.setAdapter(adapter);
    }


    // temporarily
    public void initObjects(){

//        RequestQueue queue = Volley.newRequestQueue(this);
//        String url ="http://10.0.2.2:8000/storage/categories/";
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
//                Request.Method.GET,
//                url,
//                null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//
//                        try{
//                            // Loop through the array elements
//                            for(int i=0;i<response.length();i++){
//                                // Get current json object
//                                JSONObject category = response.getJSONObject(i);
//                                String name = category.getJSONObject("fields").getString("name");
//                                int id = category.getInt("pk");
//                                categories.add(new Category(name, id));
//                            }
//                            initByData();
//                        }catch (JSONException e){
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener(){
//                    @Override
//                    public void onErrorResponse(VolleyError error){

                        Product apple = new Product("apple","http://gifts4geeks.ru/linux_pinguin.jpg",new BigDecimal("29"), "Tasty apple. Очень вкусное яблочко дорогой. Купи не пожалешь. Вся семья ест радуется какие вкусные яболчки. Ай объедениеце. Больше бери дорогой. Ай дорогой можешь ещё к брату зайти там от такие арбузы. Я те скидочку сразу выпишу, а дорогой?", 1);
                        Product strawberry = new Product("stawberry","https://smokeit-live.storage.googleapis.com/upload/www.smokeitlondon.com/other/strawberry-10ml-e-juice.jpg", new BigDecimal("9"), "Forests strawberry", 1);
                        Product watermelon = new Product("WaterMelon","https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Watermelon_slices_BNC.jpg/220px-Watermelon_slices_BNC.jpg", new BigDecimal("11"), "Tasty waterMelon", 1);

                        Product bigShaurma = new Product("bigShaurma", "http://www.cookforfun.ru/images/shaurma/mainphoto-big.jpg", new BigDecimal("10"), "Big shava", 2);
                        Product smallShaurma = new Product("smallShaurma", "https://www.menu.am/resources/default/img/restaurant_products/big/1449757626,3754.jpeg", new BigDecimal("5"), "Small shava", 2);

                        ArrayList<Product> fruits= new ArrayList<Product>();
                        fruits.add(apple);
                        fruits.add(strawberry);
                        fruits.add(watermelon);

                        ArrayList<Product> shaumas = new ArrayList<Product>();
                        shaumas.add(bigShaurma);
                        shaumas.add(smallShaurma);

                        categories.add(new Category("Fruits", fruits));
                        categories.add(new Category("Shaurmas", shaumas));

                        DBManager dbManager = new DBManager(getApplicationContext());
                        dbManager.addCategory(categories.get(0));
                        dbManager.addCategory(categories.get(1));

                        initByData();
//                    }
//                }
//        );

//            queue.add(jsonArrayRequest);





    }

}
