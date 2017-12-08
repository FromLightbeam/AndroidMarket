package com.hard.light.buywatermelondear.activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hard.light.buywatermelondear.R;
import com.hard.light.buywatermelondear.fragments.DetailProductFragment;
import com.hard.light.buywatermelondear.helper.DownloadImagesTask;
import com.hard.light.buywatermelondear.helper.OnSwipeTouchListener;
import com.hard.light.buywatermelondear.models.Category;
import com.hard.light.buywatermelondear.models.Product;
import com.hard.light.buywatermelondear.models.ShoppingCart;

public class DetailProductActivity extends BaseActivity {

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        Intent intent =  getIntent();
        product = (Product) intent.getSerializableExtra("product");
//

        FragmentManager fragmentManager = getSupportFragmentManager();
        DetailProductFragment detailFragment = (DetailProductFragment) fragmentManager
                .findFragmentById(R.id.product_fragment);


        detailFragment.setProduct(product);
        detailFragment.setProducts(ShoppingCart.getProducts());





    }
//
//    public void clickBuy(View view) {
//        if ((ShoppingCart.getProducts().contains(product))) {
//            buyButton.setText(getString(R.string.already_in_cart));
//        }
//        else{
//            buyButton.setText(getString(R.string.buy));
//        }
//    }
}
