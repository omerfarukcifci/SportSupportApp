package com.support.sport.sportsupport.ViewPackage.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.support.sport.sportsupport.Model.Branch;
import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.ViewPackage.R;

/**
 * Created by Faruk on 2.05.2018.
 */

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.ViewHolder> {

    private Branch[] branches;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView branchName;
        public TextView branchQuota;
        public TextView branchPhone;
        public TextView branchCity;
        public TextView branchDistrict;
        public TextView branchAdress;

        public ViewHolder(View itemView) {
            super(itemView);
            branchName = (TextView) itemView.findViewById(R.id.branch_name);
            branchQuota = (TextView) itemView.findViewById(R.id.branch_quota);
            branchPhone = (TextView) itemView.findViewById(R.id.branch_phone);
            branchCity = (TextView) itemView.findViewById(R.id.branch_city);
            branchDistrict = (TextView) itemView.findViewById(R.id.branch_district);
            branchAdress = (TextView) itemView.findViewById(R.id.branch_adress);
        }

    }

    public BranchAdapter(Branch[] myDataset) {
        branches = myDataset;
    }


    @Override
    public BranchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.branch_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BranchAdapter.ViewHolder holder, int position) {

        Branch b = branches[position];

        holder.branchName.setText(b.getName());
        holder.branchQuota.setText("Quota : " + b.getQuota());
        holder.branchPhone.setText("Phone: "+((int) b.getPhoneNumber()));
        holder.branchCity.setText(b.getCity());
        holder.branchDistrict.setText(b.getDistrict());
        holder.branchAdress.setText(b.getAdress());



    }

    @Override
    public int getItemCount() {
        return branches.length;
    }
}
