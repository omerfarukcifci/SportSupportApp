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

import com.support.sport.sportsupport.Controller.SpecialOfferController;
import com.support.sport.sportsupport.Model.SpecialOffer;
import com.support.sport.sportsupport.ViewPackage.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SofferAdapter extends RecyclerView.Adapter<SofferAdapter.ViewHolder> {


    private SpecialOffer[] offers;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView offerName;
        public TextView endDate;
        public TextView startDate;
        public TextView discount;
        public TextView branchId;
        public TextView reference;
        public TextView attendance;
        public ImageButton delete;

        public ViewHolder(View itemView) {
            super(itemView);
            offerName   = itemView.findViewById(R.id.soffer_nameS);
            discount = itemView.findViewById(R.id.soffer_discount);
            endDate = itemView.findViewById(R.id.soffer_endDate);
            startDate = itemView.findViewById(R.id.soffer_startDate);
            branchId = itemView.findViewById(R.id.soffer_branchId);
            reference = itemView.findViewById(R.id.soffer_referenceLimit);
            attendance = itemView.findViewById(R.id.soffer_attendanceLimit);
         //   endDate = itemView.findViewById(R.id.offer_end_date);
            delete = itemView.findViewById(R.id.soffer_delete_button);
        }

    }

    public SofferAdapter(SpecialOffer[] myDataset) {
        offers = myDataset;
    }

    @Override
    public SofferAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.soffer_item, parent, false);

        SofferAdapter.ViewHolder vh = new SofferAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SofferAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
     final   SpecialOffer c = offers[position];
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
      //  Date date = format.parse(c.getFinishDate());
        String start =c.getStartDate();
        String end = c.getFinishDate();

        holder.offerName.setText(c.getName());
      //  holder.startDate.setText("Starts at: "+start);StringUtils.substring( c.getFinishDate(), 0, 10)
        holder.endDate.setText("Ends at: "+ c.getFinishDate().substring(0,10));
        holder.startDate.setText("Starts at: "+ c.getStartDate().substring(0,10));
        holder.discount.setText("Discount : "+ c.getDiscount());
        holder.branchId.setText("BranchId : "+ c.getBranchId());
        holder.reference.setText("Reference Limit : " + c.getReferenceNumberLimit());
        holder.attendance.setText("Attendance Limit : " + c.getAttendanceLimit());
        holder.delete.setOnClickListener(new View.OnClickListener() {
           /* @Override
            public void onClick(View v) {
                final Snackbar mySnackbar = Snackbar.make(v, "You applied the offer successfully!", Snackbar.LENGTH_LONG);
                mySnackbar.setAction("OK!", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mySnackbar.dismiss();
                    }
                });
                mySnackbar.show();
            }
        });*/

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                // Setting Dialog Title
                alertDialog.setTitle("Special Offer");
                alertDialog.setCancelable(true);
                // Setting Dialog Message
                alertDialog.setMessage("This special offer will delete, Are you sure?");
                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        SpecialOfferController soController = new SpecialOfferController();
                      soController.deleteSpecialOffer(c.getId());

                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return offers.length;
    }
}

