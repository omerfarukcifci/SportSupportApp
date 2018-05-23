package com.support.sport.sportsupport.ViewPackage.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.support.sport.sportsupport.Model.Cost;
import com.support.sport.sportsupport.Model.Fee;
import com.support.sport.sportsupport.ViewPackage.Menu.PaymentScreen;
import com.support.sport.sportsupport.ViewPackage.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Merve on 23.05.2018.
 */

public class MembershipAdapter extends RecyclerView.Adapter<MembershipAdapter.ViewHolder>{

    private ArrayList<Cost> feeList;
    private Context context;
    private int branchId;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView pri_one;
        public TextView pri_two;
        public TextView pri_three;
        public TextView cost;
        public TextView type;
        public Button pay;

        public ViewHolder(View itemView) {
            super(itemView);
            pri_one = itemView.findViewById(R.id.membership_item_pri_one);
            pri_two = itemView.findViewById(R.id.membership_item_pri_two);
            pri_three = itemView.findViewById(R.id.membership_item_pri_three);
            cost = itemView.findViewById(R.id.membership_item_fee);
            type = itemView.findViewById(R.id.membership_item_type);
            pay = itemView.findViewById(R.id.membership_item_pay_button);
        }
    }

    public MembershipAdapter(ArrayList<Cost> f, int branch, Context con){
        feeList = f;
        context = con;
        branchId = branch;
    }

    public void setFee(ArrayList<Cost> f){
        feeList = f;
    }

    @Override
    public MembershipAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_membership, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MembershipAdapter.ViewHolder holder, int position) {
        final Cost c = feeList.get(position);
        holder.type.setText(c.getType()+" Membership");
        holder.cost.setText(c.getTotalcost()+" TL");
        holder.pri_one.setText(c.getP1());
        holder.pri_two.setText(c.getP2());
        holder.pri_three.setText(c.getP3());
        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(v.getContext(), PaymentScreen.class);
               intent.putExtra("cost",c.getTotalcost());
               intent.putExtra("product", c.getType());
               intent.putExtra("branch",branchId);
               v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feeList.size();
    }
}
