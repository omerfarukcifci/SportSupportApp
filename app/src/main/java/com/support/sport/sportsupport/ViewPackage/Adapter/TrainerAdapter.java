package com.support.sport.sportsupport.ViewPackage.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.support.sport.sportsupport.Controller.ManagerManagementController;
import com.support.sport.sportsupport.Controller.TrainerManagementController;
import com.support.sport.sportsupport.Model.Trainer;
import com.support.sport.sportsupport.ViewPackage.R;

/**
 * Created by Faruk on 2.05.2018.
 */

public class TrainerAdapter extends RecyclerView.Adapter<TrainerAdapter.ViewHolder> {

    private Trainer[] trainers;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView trainerName;
        public TextView trainerSurname;
        public TextView trainerUsername;
        public ImageButton trainerDeleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            trainerName= itemView.findViewById(R.id.trainer_name);
            trainerSurname= itemView.findViewById(R.id.trainer_surname);
            trainerUsername= itemView.findViewById(R.id.trainer_username);
            trainerDeleteButton = itemView.findViewById(R.id.trainer_delete_button);
        }


    }

    public TrainerAdapter(Trainer[] myDataset) {
        trainers = myDataset;
    }



    @Override
    public TrainerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trainer_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TrainerAdapter.ViewHolder holder, int position) {

        final Trainer t = trainers[position];

        holder.trainerName.setText(t.getName());
        holder.trainerSurname.setText(t.getSurname());
        holder.trainerUsername.setText("Username: "+t.getUsername());
        holder.trainerDeleteButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                        // Setting Dialog Title
                        alertDialog.setTitle("Delete Trainer");
                        alertDialog.setCancelable(true);
                        // Setting Dialog Message
                        alertDialog.setMessage("This Trainer will delete, Are you sure?");
                        // Setting Positive "Yes" Button
                        alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                TrainerManagementController trainerMC = new TrainerManagementController();
                                trainerMC.deleteTrainer(t.getId());
                                dialog.cancel();
                            }
                        });
                        alertDialog.show();
                    }
                }


        );

    }

    @Override
    public int getItemCount() {
        return trainers.length;
    }



}
