package com.support.sport.sportsupport.ViewPackage.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.support.sport.sportsupport.Controller.BranchManagementController;
import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.ManagerManagementController;
import com.support.sport.sportsupport.Model.Branch;
import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.ViewPackage.Management.BranchManagementScreen;
import com.support.sport.sportsupport.ViewPackage.Management.FragmentBranchStats;
import com.support.sport.sportsupport.ViewPackage.R;

import java.util.List;

/**
 * Created by Faruk on 2.05.2018.
 */

public class BranchAdapter extends RecyclerView.Adapter<BranchAdapter.ViewHolder> {

    private List<Branch> branches;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView branchName;
        public TextView branchQuota;
        public TextView branchPhone;
        public TextView branchCity;
        public TextView branchAdress;
        public ImageButton deletebutton;
        public Button statsButton;

        public ViewHolder(View itemView) {
            super(itemView);
            branchName = (TextView) itemView.findViewById(R.id.branch_name);
            branchQuota = (TextView) itemView.findViewById(R.id.branch_quota);
            branchPhone = (TextView) itemView.findViewById(R.id.branch_phone);
            branchCity = (TextView) itemView.findViewById(R.id.branch_city);
            branchAdress = (TextView) itemView.findViewById(R.id.branch_adress);
            deletebutton = itemView.findViewById(R.id.branch_delete_button);
            statsButton = itemView.findViewById(R.id.branch_stats_button);
        }

    }

    public BranchAdapter(List<Branch> myDataset) {
        branches = myDataset;
    }

    public void setList(List<Branch> myDataset) {
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

        final Branch b = branches.get(position);

        holder.branchName.setText(b.getName());
        holder.branchQuota.setText("Quota : " + b.getQuota());
        holder.branchPhone.setText("Phone: "+(b.getPhoneNumber()));
        holder.branchCity.setText(b.getDistrict()+"/"+b.getCity());
        holder.branchAdress.setText("Address: "+b.getAdress());

        holder.deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                alertDialog.setTitle("Delete Manager");
                alertDialog.setCancelable(true);
                alertDialog.setMessage("This Manager will delete, Are you sure?");
                alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new BranchManagementController().deleteBranch(b.getId());
                        branches.remove(b);
                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton("CANCEL",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });

        holder.statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),FragmentBranchStats.class);
                intent.putExtra("SelectedBranch",b.getId());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return branches.size();
    }
}
