package com.google.engedu.anagrams;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mansi on 27/2/18.
 */

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.MyViewHolder> {
    private ArrayList<Result> resultList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView res;

        public MyViewHolder(View view) {
            super(view);
            res = (TextView) view.findViewById(R.id.result);
        }
    }


    public ResultsAdapter(ArrayList<Result> resultList) {
        this.resultList = resultList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Result result = resultList.get(position);
        holder.res.setText(result.getAnswer());
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }


}
