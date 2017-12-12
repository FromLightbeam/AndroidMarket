package com.hard.light.buywatermelondear.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hard.light.buywatermelondear.R;
import com.hard.light.buywatermelondear.helper.DownloadImagesTask;
import com.hard.light.buywatermelondear.models.Product;

import java.util.ArrayList;



public class PriceListAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<String[]> objects;

    public PriceListAdapter(Context context, ArrayList<String[]> products) {
        ctx = context;
        objects = products;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.pricelist_item, parent, false);
        }
//
        String[] p = getProduct(position);

        ((TextView) view.findViewById(R.id.category_name)).setText(p[2] + ", ");
        ((TextView) view.findViewById(R.id.product_name)).setText(p[1]+ ", ");

        ImageView image = ((ImageView) view.findViewById(R.id.product_image));
        image.setTag(p[0]);
        new DownloadImagesTask().execute(image);

        return view;

    }

    // товар по позиции
    String[] getProduct(int position) {
        return ((String[]) getItem(position));
    }

}
