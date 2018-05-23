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
import java.util.List;
import java.util.Locale;

public class SofferAdapter extends RecyclerView.Adapter<SofferAdapter.ViewHolder> {


    private List<SpecialOffer> offers;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView offerName;
        public TextView endDate;
        public TextView startDate;
        public TextView discount;
        public TextView reference;
        public TextView attendance;

        public ViewHolder(View itemView) {
            super(itemView);
            offerName   = itemView.findViewById(R.id.soffer_nameS);
            discount = itemView.findViewById(R.id.soffer_discount);
            endDate = itemView.findViewById(R.id.soffer_endDate);
            startDate = itemView.findViewById(R.id.soffer_startDate);
            reference = itemView.findViewById(R.id.soffer_referenceLimit);
            attendance = itemView.findViewById(R.id.soffer_attendanceLimit);
        }

    }

    public SofferAdapter(List<SpecialOffer> myDataset) {
        offers = myDataset;
    }
    public void setList(List<SpecialOffer> myDataset) {
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
     final   SpecialOffer c = offers.get(position);
        holder.offerName.setText(c.getName());
        holder.endDate.setText("Ends at: "+ c.getFinishDate().substring(0,10));
        holder.startDate.setText("Starts at: "+ c.getStartDate().substring(0,10));
        holder.discount.setText("Discount : "+ c.getDiscount());
        holder.reference.setText("Reference Limit : " + c.getReferenceNumberLimit());
        holder.attendance.setText("Attendance Limit : " + c.getAttendanceLimit());
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }
}

