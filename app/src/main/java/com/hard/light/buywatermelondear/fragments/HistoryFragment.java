package com.hard.light.buywatermelondear.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hard.light.buywatermelondear.R;
import com.hard.light.buywatermelondear.adapters.MyProductListAdapter;
import com.hard.light.buywatermelondear.models.Category;
import com.hard.light.buywatermelondear.models.History;


public class HistoryFragment extends Fragment {
    private Category category;
    private View view;
    private DetailProductFragment detailFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_history, container, false);

        ListView productsView = view.findViewById(R.id.products_history);

        MyProductListAdapter adapter = new MyProductListAdapter(getActivity().getApplicationContext(), History.getProducts());
        productsView.setAdapter(adapter);
        return view;
    }
}
