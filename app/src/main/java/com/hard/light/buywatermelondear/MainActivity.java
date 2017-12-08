package com.hard.light.buywatermelondear;

import android.app.Activity;
import android.app.Notification;
import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hard.light.buywatermelondear.activity.BaseActivity;
import com.hard.light.buywatermelondear.activity.CategoryActivity;
import com.hard.light.buywatermelondear.helper.NotificationPusher;

import com.hard.light.buywatermelondear.helper.NotificationService;
import com.hard.light.buywatermelondear.models.Category;
//import com.android.volley.Request;
import com.hard.light.buywatermelondear.models.Product;
import android.widget.AdapterView.OnItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://10.0.2.2:8000/storage/categories/";

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
                                String name = category.getJSONObject("fields").getString("name");
                                int id = category.getInt("pk");
                                categories.add(new Category(name, id));
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

                        Product apple = new Product("apple","https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Red_Apple.jpg/200px-Red_Apple.jpg",new BigDecimal("29"), "Tasty apple. Очень вкусное яблочко дорогой. Купи не пожалешь. Вся семья ест радуется какие вкусные яболчки. Ай объедениеце. Больше бери дорогой. Ай дорогой можешь ещё к брату зайти там от такие арбузы. Я те скидочку сразу выпишу, а дорогой?");
                        Product strawberry = new Product("stawberry","https://smokeit-live.storage.googleapis.com/upload/www.smokeitlondon.com/other/strawberry-10ml-e-juice.jpg", new BigDecimal("9"), "Forests strawberry");
                        Product watermelon = new Product("WaterMelon","https://upload.wikimedia.org/wikipedia/commons/thumb/b/bf/Watermelon_slices_BNC.jpg/220px-Watermelon_slices_BNC.jpg", new BigDecimal("11"), "Tasty waterMelon");

                        Product bigShaurma = new Product("bigShaurma", "http://www.cookforfun.ru/images/shaurma/mainphoto-big.jpg", new BigDecimal("10"), "Big shava");
                        Product smallShaurma = new Product("smallShaurma", "https://www.menu.am/resources/default/img/restaurant_products/big/1449757626,3754.jpeg", new BigDecimal("5"), "Small shava");

                        ArrayList<Product> fruits= new ArrayList<Product>();
                        fruits.add(apple);
                        fruits.add(strawberry);
                        fruits.add(watermelon);

                        ArrayList<Product> shaumas = new ArrayList<Product>();
                        shaumas.add(bigShaurma);
                        shaumas.add(smallShaurma);

                        categories.add(new Category("Fruits", fruits));
                        categories.add(new Category("Shaurmas", shaumas));
                        initByData();
                    }
                }
        );

            queue.add(jsonArrayRequest);





    }

}
