package com.example.hw1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    final String LOG_TAG = "myLogs";
    private RecyclerView.Adapter mAdapter;
    ArrayList<String> items = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "Fr1 onCreateView");
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        if (items.size() == 0) { //for going back
            fillList(items);
        }

        Button addButton = (Button)view.findViewById(R.id.btnAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.add(Integer.toString(items.size()));
                mAdapter.notifyItemInserted(items.size() - 1);
            }
        });

        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.list_recycler_view);

        mAdapter = new MyAdapter(getActivity(), items);
        recyclerView.setAdapter(mAdapter);

        RecyclerView.LayoutManager layoutManager;

        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(getActivity(), 3);
        }
        else {
            layoutManager = new GridLayoutManager(getActivity(), 4);
        }

        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

    void fillList(List<String> toFill) {
        for (int i = 0; i < 100; i++) {
            toFill.add(i + "");
        }
    }
}

