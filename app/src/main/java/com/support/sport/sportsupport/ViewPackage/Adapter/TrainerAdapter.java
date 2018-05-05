package com.support.sport.sportsupport.ViewPackage.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        public ViewHolder(View itemView) {
            super(itemView);
            trainerName= itemView.findViewById(R.id.trainer_name);
            trainerSurname= itemView.findViewById(R.id.trainer_surname);
            trainerUsername= itemView.findViewById(R.id.trainer_username);
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

        Trainer t = trainers[position];

        holder.trainerName.setText(t.getName());
        holder.trainerSurname.setText(t.getSurname());
        holder.trainerUsername.setText("Username: "+t.getUsername());

    }

    @Override
    public int getItemCount() {
        return trainers.length;
    }



}
