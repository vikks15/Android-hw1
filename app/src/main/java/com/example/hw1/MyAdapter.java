package com.example.hw1;

import android.app.Activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    final String LOG_TAG = "myLogs";
    private Context mContext;
    TextView mTextView;


    public MyViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.itemText);
        //mContext = itemView.getContext();
        mContext = context;
        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View itemView) {
        Toast.makeText(mContext, "Test click " + String.valueOf(this.getAdapterPosition()), Toast.LENGTH_SHORT).show();

        ItemFragment itemPage = new ItemFragment();

        Bundle args = new Bundle();
        args.putString("num",  String.valueOf(this.getAdapterPosition()));
        args.putInt("numColor", mTextView.getCurrentTextColor());
        itemPage.setArguments(args);

        FragmentManager fragmentManager = ((AppCompatActivity)mContext).getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frgmCont, itemPage);
        fragmentTransaction.addToBackStack(null);

        //не получилось отсюда
        //TextView textView = (TextView)((Activity)itemView.getContext()).findViewById(R.id.itemOnPageText);
        //textView.setText(String.valueOf(this.getAdapterPosition()));
        //((TextView)itemPage.getView().findViewById(R.id.itemOnPageText)).setText(this.getAdapterPosition());

        fragmentTransaction.commit();
    }
}


class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context mContext;
    private List<String> mData;

    public MyAdapter(Context context, List<String> data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.list_item, viewGroup, false);

        final MyViewHolder vHolder = new MyViewHolder(mContext,v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        String str = mData.get(i);
        myViewHolder.mTextView.setText(str);

        if ( Integer.parseInt(str) % 2 == 0) {
            myViewHolder.mTextView.setTextColor(Color.parseColor("#d82222"));
        } else {
            myViewHolder.mTextView.setTextColor(Color.parseColor("#1200e0"));
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
