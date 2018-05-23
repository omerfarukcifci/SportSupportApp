package com.support.sport.sportsupport.ViewPackage.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.ManagerManagementController;
import com.support.sport.sportsupport.Controller.UserManagementController;
import com.support.sport.sportsupport.Model.Manager;
import com.support.sport.sportsupport.Model.Member;
import com.support.sport.sportsupport.ViewPackage.Management.UserManagementScreen;
import com.support.sport.sportsupport.ViewPackage.Management.UserUpdateScreen;
import com.support.sport.sportsupport.ViewPackage.Menu.FragmentCourse;
import com.support.sport.sportsupport.ViewPackage.Menu.MyCoursesScreen;
import com.support.sport.sportsupport.ViewPackage.R;

import java.util.List;

/**
 * Created by Faruk on 2.05.2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private Context context;
    private List<Member> members;
    public static Context mcon;
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView memberName;
        public TextView memberSurname;
        public TextView memberUsername;
        public TextView memberStatue;
        public TextView memberStatus;

        public ImageButton memberDelete;
        public ImageButton memberUpdate;
        public ImageButton banUserBTN;

        public ViewHolder(View itemView) {
            super(itemView);
            memberName= itemView.findViewById(R.id.user_name);
            memberSurname= itemView.findViewById(R.id.user_surname);
            memberUsername= itemView.findViewById(R.id.user_username);
            memberStatue = itemView.findViewById(R.id.user_statue);
            memberStatus = itemView.findViewById(R.id.user_status);
            memberDelete = itemView.findViewById(R.id.user_delete_button);
            memberUpdate = itemView.findViewById(R.id.user_update_BTN);
            banUserBTN = itemView.findViewById(R.id.userBanUMBTN);
        }

    }

    public UserAdapter(List<Member> myDataset) {
        members = myDataset;
    }

    public void setList(List<Member> myDataset) {
        members = myDataset;
    }


    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       final View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {

       final Member m = members.get(position);

        holder.memberName.setText(m.getName());
        holder.memberSurname.setText(m.getSurname());
        holder.memberUsername.setText("Username: "+m.getUsername());
        holder.memberStatue.setText("Statue : "+m.getStatue());
        holder.memberStatus.setText("Status : "+m.getStatus());

        holder.memberDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                alertDialog.setTitle("Delete Member");
                alertDialog.setCancelable(true);
                alertDialog.setMessage("This Member will delete, Are you sure?");
                alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new UserManagementController().deleteMember(m.getId());
                        members.remove(m);
                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });


        holder.banUserBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                alertDialog.setTitle("Ban Member");
                alertDialog.setCancelable(true);
                alertDialog.setMessage("This Member banned from sport center, Are you sure?");
                alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        new UserManagementController().banMember(m.getId());

                        dialog.cancel();
                    }
                });
                alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });


        holder.memberUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final  View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                alertDialog.setTitle("Update Member");
                alertDialog.setCancelable(true);
                alertDialog.setMessage("Do you want to update this user's informations");
                alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(v.getContext(),UserUpdateScreen.class);

                        intent.putExtra("MemberName",m.getName() );
                        intent.putExtra("MemberId",m.getId() );
                        intent.putExtra("MemberSurname",m.getSurname() );
                        intent.putExtra("MemberMail",m.getMail() );
                        intent.putExtra("MemberPassword",m.getPassword() );
                        intent.putExtra("MemberUsername",m.getUsername() );
                        intent.putExtra("MemberName",m.getName() );
                        v.getContext().startActivity(intent);

                    }
                });
                alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return members.size();
    }


}
