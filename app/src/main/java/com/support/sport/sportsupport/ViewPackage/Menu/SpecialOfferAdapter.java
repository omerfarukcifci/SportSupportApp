package com.support.sport.sportsupport.ViewPackage.Menu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.Model.SpecialOffer;
import com.support.sport.sportsupport.ViewPackage.R;

import java.text.SimpleDateFormat;

/**
 * Created by Faruk on 26.04.2018.
 */

public class SpecialOfferAdapter extends RecyclerView.Adapter<SpecialOfferAdapter.ViewHolder> {


    private SpecialOffer[] offers;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView offerName;
        public TextView limit;
        public TextView startDate;
        public TextView endDate;

        public ViewHolder(View itemView) {
            super(itemView);
            offerName   = itemView.findViewById(R.id.offer_name);
            limit = itemView.findViewById(R.id.offer_limit);
            startDate = itemView.findViewById(R.id.offer_start_date);
            endDate = itemView.findViewById(R.id.offer_end_date);
        }

    }

    public SpecialOfferAdapter(SpecialOffer[] myDataset) {
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
        SpecialOffer c = offers[position];
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        String start = dateformat.format(c.getStartDate());
        String end = dateformat.format(c.getFinishDate());

        holder.offerName.setText(c.getName());
        holder.limit.setText("Limit: "+c.getAttendanceLimit());
        holder.startDate.setText("Starts at: "+start);
        holder.endDate.setText("Ends at: "+end);
    }

    @Override
    public int getItemCount() {
        return offers.length;
    }
}
