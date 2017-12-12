package com.hard.light.buywatermelondear.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.hard.light.buywatermelondear.MainActivity;
import com.hard.light.buywatermelondear.R;
import com.hard.light.buywatermelondear.models.ShoppingCart;


public class BaseActivity extends AppCompatActivity {

    final String TAG = "States";

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("About")
                        .setMessage("©App: Best asian internet market\n Author: I\n Version: 0.0.0.0.0.1")
                        .setCancelable(false)
                        .setNegativeButton("ОК",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;

            case R.id.action_price_list:
                Intent intentPrice = new Intent(this, PriceListActivity.class);
                startActivity(intentPrice);
                finish();
                return true;

            case R.id.action_shopping_cart:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent intent = new Intent(this, ShoppingCartActivity.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.action_home:
                Intent intentHome = new Intent(this, MainActivity.class);
                startActivity(intentHome);
                finish();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Log.d(TAG,  this.getClass().getSimpleName() + ": onCreate()");
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, this.getClass().getSimpleName() + ": onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, this.getClass().getSimpleName() + ": onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, this.getClass().getSimpleName() + ": onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, this.getClass().getSimpleName() + ": onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, this.getClass().getSimpleName() + ": onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, this.getClass().getSimpleName() + ": onDestroy()");
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topbar, menu);
        return true;
    }
}
