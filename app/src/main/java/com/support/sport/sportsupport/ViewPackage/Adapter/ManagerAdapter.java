package com.support.sport.sportsupport.ViewPackage.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.support.sport.sportsupport.Controller.ManagerManagementController;
import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.SpecialOffer;
import com.support.sport.sportsupport.ViewPackage.R;

import java.util.List;

/**
 * Created by Faruk on 27.04.2018.
 */

public class ManagerAdapter extends RecyclerView.Adapter<ManagerAdapter.ViewHolder> {

    private List<Manager> managers;

    public static class ViewHolder extends RecyclerView.ViewHolder{



        public TextView managerName;
        public TextView managerSurname;
        public TextView managerUsername;
        public TextView managerBranchId;
        public ImageButton deleteManagerButton;


        public ViewHolder(View itemView) {
            super(itemView);
            managerName= itemView.findViewById(R.id.manager_name);
            managerSurname= itemView.findViewById(R.id.manager_surname);
            managerUsername= itemView.findViewById(R.id.manager_username);
            managerBranchId = itemView.findViewById(R.id.manager_branch_id);
            deleteManagerButton = itemView.findViewById(R.id.manager_delete_button);
        }

    }

    public ManagerAdapter(List<Manager> myDataset) {
        managers = myDataset;
    }

    public void setList(List<Manager> myDataset) {
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

       final Manager m = managers.get(position);

        holder.managerName.setText(m.getName());
        holder.managerSurname.setText(m.getSurname());
        holder.managerUsername.setText("Username: "+m.getUsername());
        holder.managerBranchId.setText("Branch: "+m.getBrancName());

        holder.deleteManagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                alertDialog.setTitle("Delete Manager");
                alertDialog.setCancelable(true);
                alertDialog.setMessage("This Manager will delete, Are you sure?");
                alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new ManagerManagementController().deleteManager(m.getId());
                        managers.remove(m);
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


    }

    @Override
    public int getItemCount() {
        return managers.size();
    }
}
