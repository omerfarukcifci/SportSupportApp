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
import com.support.sport.sportsupport.Controller.UserManagementController;
import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.ViewPackage.R;

/**
 * Created by Faruk on 2.05.2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Member[] members;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView memberName;
        public TextView memberSurname;
        public TextView memberUsername;
        public TextView memberStatue;
        public TextView memberStatus;

        public ImageButton memberDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            memberName= itemView.findViewById(R.id.user_name);
            memberSurname= itemView.findViewById(R.id.user_surname);
            memberUsername= itemView.findViewById(R.id.user_username);
            memberStatue = itemView.findViewById(R.id.user_statue);
            memberStatus = itemView.findViewById(R.id.user_status);
            memberDelete = itemView.findViewById(R.id.user_delete_button);
        }

    }

    public UserAdapter(Member[] myDataset) {
        members = myDataset;
    }



    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {

       final Member m = members[position];

        holder.memberName.setText(m.getName());
        holder.memberSurname.setText(m.getSurname());
        holder.memberUsername.setText("Username: "+m.getUsername());
        holder.memberStatue.setText("Statue : "+m.getStatue());
        holder.memberStatus.setText("Status : "+m.getStatus());

        holder.memberDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                // Setting Dialog Title
                alertDialog.setTitle("Delete Member");
                alertDialog.setCancelable(true);
                // Setting Dialog Message
                alertDialog.setMessage("This Member will delete, Are you sure?");
                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        UserManagementController userMController = new UserManagementController();
                        userMController.deleteMember(Integer.toString(m.getId()));
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return members.length;
    }


}
