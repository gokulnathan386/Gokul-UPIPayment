package com.example.upipaymentgateway.adapter;


import static java.lang.Double.valueOf;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.upipaymentgateway.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListTransitionHistoryAdapter extends RecyclerView.Adapter<ListTransitionHistoryAdapter.ViewHolder> {

    private Context mContext;


    private ArrayList<HashMap<String, String>> orderhistory;

    // RecyclerView recyclerView;
    public ListTransitionHistoryAdapter(Context mContext, ArrayList<HashMap<String, String>> orderhistory) {


        this.orderhistory =orderhistory;
        this.mContext = mContext;
    }

    @Override
    public ListTransitionHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.payment_mode_list_layout, parent, false);
        ListTransitionHistoryAdapter.ViewHolder viewHolder = new ListTransitionHistoryAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListTransitionHistoryAdapter.ViewHolder holder, int position) {

        holder.phoneno.setText(orderhistory.get(position).get("UPI_PHONE_NO1"));
        holder.dataTimeTxt.setText(orderhistory.get(position).get("UPI_DATE_TIME"));
        holder.upiIdTxt.setText(orderhistory.get(position).get("UPI_NUMBER_HISTORY"));
        holder.amount.setText("\u20B9" + " " +orderhistory.get(position).get("UPI_PAID_AMOUNT"));



            if(orderhistory.get(position).get("UPI_TRANSCTION_STATUS").toString().equalsIgnoreCase("1")){
               holder.payment_status.setImageResource(R.drawable.tik_icon);
            }else{
                holder.payment_status.setImageResource(R.drawable.wrong_icon);
            }

    }


    @Override
    public int getItemCount() {
        return orderhistory.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView phoneno,dataTimeTxt,amount,upiIdTxt;
        ImageView payment_status;


        public ViewHolder(View itemView) {
            super(itemView);
            this.phoneno = itemView.findViewById(R.id.phoneno);
            this.dataTimeTxt = itemView.findViewById(R.id.dataTimeTxt);
            this.amount = itemView.findViewById(R.id.amount);
            this.upiIdTxt = itemView.findViewById(R.id.upiIdTxt);
            this.payment_status = itemView.findViewById(R.id.payment_status);

        }
    }
}