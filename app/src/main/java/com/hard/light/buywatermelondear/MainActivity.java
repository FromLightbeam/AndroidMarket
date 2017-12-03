package com.hard.light.buywatermelondear;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.hard.light.buywatermelondear.activity.BaseActivity;
import com.hard.light.buywatermelondear.activity.CategoryActivity;
import com.hard.light.buywatermelondear.models.Category;
import com.hard.light.buywatermelondear.models.Product;
import android.widget.AdapterView.OnItemClickListener;
import java.math.BigDecimal;
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

        ListView categoriesView = (ListView) findViewById(R.id.categories);

        ArrayAdapter<Category> adapter = new ArrayAdapter<Category>(this,
                android.R.layout.simple_list_item_1, this.categories);

        categoriesView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);

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

    }

}
