package com.support.sport.sportsupport.ViewPackage.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.ViewPackage.MainActivity;
import com.support.sport.sportsupport.ViewPackage.Management.AddActivityPlanScreen;
import com.support.sport.sportsupport.ViewPackage.Management.ShowActivityPlanScreen;
import com.support.sport.sportsupport.ViewPackage.Management.TraineeManagementScreen;
import com.support.sport.sportsupport.ViewPackage.R;

/**
 * Created by Faruk on 2.05.2018.
 */

public class TraineeAdapter extends RecyclerView.Adapter<TraineeAdapter.ViewHolder> {

    private Member[] members;


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView memberName;
        public TextView memberSurname;
        public TextView memberUsername;
        public TextView memberStatue;
        public TextView memberStatus;
        public ImageButton updateScheduleButton;
        public ImageButton aboutTraineeButton;

        public ViewHolder(View itemView) {
            super(itemView);
            memberName= itemView.findViewById(R.id.trainee_name);
            memberSurname= itemView.findViewById(R.id.trainee_surname);
            memberUsername= itemView.findViewById(R.id.trainee_username);
            memberStatue = itemView.findViewById(R.id.trainee_statue);
            memberStatus = itemView.findViewById(R.id.trainee_status);
            updateScheduleButton = itemView.findViewById(R.id.trainee_update_schedule_button);
            updateScheduleButton.setOnClickListener(this);
            aboutTraineeButton = itemView.findViewById(R.id.about_trainee_button);
            aboutTraineeButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (view.getId() == updateScheduleButton.getId()){
                Intent i = new Intent(view.getContext(), AddActivityPlanScreen.class);
                view.getContext().startActivity(i);

            }else if(view.getId() == aboutTraineeButton.getId()){
                Intent i = new Intent(view.getContext(), ShowActivityPlanScreen.class);
                view.getContext().startActivity(i);

            }
        }
    }

    public TraineeAdapter(Member[] myDataset) {
        members = myDataset;
    }



    @Override
    public TraineeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trainee_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TraineeAdapter.ViewHolder holder, int position) {

        Member m = members[position];

        holder.memberName.setText(m.getName());
        holder.memberSurname.setText(m.getSurname());
        holder.memberUsername.setText("Username: "+m.getUsername());
        holder.memberStatue.setText("Statue : "+m.getStatue());
        holder.memberStatus.setText("Status : "+m.getStatus());

    }

    @Override
    public int getItemCount() {
        return members.length;
    }


}
