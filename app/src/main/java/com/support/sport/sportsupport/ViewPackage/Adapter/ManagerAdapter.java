package com.support.sport.sportsupport.ViewPackage.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.SpecialOffer;
import com.support.sport.sportsupport.ViewPackage.R;

/**
 * Created by Faruk on 27.04.2018.
 */

public class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.ViewHolder> {

    private Manager[] managers;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView managerName;
        public TextView managerSurname;
        public TextView managerUsername;
        public TextView managerBranchId;

        public ViewHolder(View itemView) {
            super(itemView);
            managerName= itemView.findViewById(R.id.manager_name);
            managerSurname= itemView.findViewById(R.id.manager_surname);
            managerUsername= itemView.findViewById(R.id.manager_username);
            managerBranchId = itemView.findViewById(R.id.manager_branch_id);
        }

    }

    public ManagerAdapter(Manager[] myDataset) {
        managers = myDataset;
    }



    @Override
    public ManagerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.manager_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ManagerAdapter.ViewHolder holder, int position) {

        Manager m = managers[position];

        holder.managerName.setText(m.getName());
        holder.managerSurname.setText(m.getSurname());
        holder.managerUsername.setText("Username: "+m.getUsername());
        holder.managerBranchId.setText("Branch Id :"+m.getBranchId());
    }

    @Override
    public int getItemCount() {
        return managers.length;
    }
}