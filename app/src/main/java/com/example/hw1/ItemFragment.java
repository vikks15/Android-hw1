package com.example.hw1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ItemFragment extends Fragment {
    final String LOG_TAG = "myLogs";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item_page, null);

        String strNum = getArguments().getString("num");
        TextView textview = view.findViewById(R.id.itemOnPageText);
        textview.setText(strNum);
        textview.setTextColor(getArguments().getInt("numColor"));

        return view;
    }
}
