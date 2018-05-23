package com.support.sport.sportsupport.ViewPackage.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.ManagerManagementController;
import com.support.sport.sportsupport.Controller.SpecialOfferController;
import com.support.sport.sportsupport.Model.SpecialOffer;
import com.support.sport.sportsupport.ViewPackage.Management.TrainerAddScreen;
import com.support.sport.sportsupport.ViewPackage.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Faruk on 26.04.2018.
 */

public class SpecialOfferAdapter extends RecyclerView.Adapter<SpecialOfferAdapter.ViewHolder> {


    private List<SpecialOffer> offers;
    Date timeStamp = Calendar.getInstance().getTime();

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView offerName;
        public TextView limit;
        public TextView startDate;
        public TextView endDate;
        public Button apply;

        public ViewHolder(View itemView) {
            super(itemView);
            offerName   = itemView.findViewById(R.id.offer_name);
            limit = itemView.findViewById(R.id.offer_limit);
            startDate = itemView.findViewById(R.id.offer_start_date);
            endDate = itemView.findViewById(R.id.offer_end_date);
            apply = itemView.findViewById(R.id.offer_apply_but);
        }

    }

    public SpecialOfferAdapter(List<SpecialOffer> myDataset) {
        offers = myDataset;
    }

    @Override
    public SpecialOfferAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.special_offer_list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SpecialOfferAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final  SpecialOffer c = offers.get(position);
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        String start =c.getStartDate();
        String end = c.getFinishDate();

        holder.offerName.setText(c.getName());
        holder.startDate.setText("Starts at: "+start.substring(0,10));
        try {
            if(timeStamp.before(new SimpleDateFormat("yyyy-MM-dd").parse(c.getFinishDate()))) {
                holder.endDate.setText("Ends at: " + (c.getFinishDate().split("T"))[0]);
            }else{
                holder.endDate.setText("CLOSED!");
                holder.apply.setEnabled(false);
                holder.apply.setBackgroundColor(Color.GRAY);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.limit.setText("Attendance Limit: " + c.getAttendanceLimit());
        SpecialOfferController soController = new SpecialOfferController();
        soController.controlSpecialOffer(c.getId(),Key.cMember.getId());

        holder.apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(timeStamp.before(new SimpleDateFormat("yyyy-MM-dd").parse(c.getStartDate()))) {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                        alertDialog.setTitle("Apply Special Offer");
                        alertDialog.setCancelable(true);
                        alertDialog.setMessage("This offer is not opened yet. Please come back at start date.");
                        alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        alertDialog.show();
                    }else{
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(view.getContext());
                        alertDialog.setTitle("Apply Special Offer");
                        alertDialog.setCancelable(true);
                        alertDialog.setMessage("Do you want to  apply this special offer ?");
                        alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                SpecialOfferController soController = new SpecialOfferController();
                                soController.applySpecialOffer(c.getId(),Key.cMember.getId());
                                Key.membersSpecialOffer.remove(c);
                                notifyDataSetChanged();
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
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }
}
