package com.support.sport.sportsupport.ViewPackage.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.support.sport.sportsupport.Model.SpecialOffer;
import com.support.sport.sportsupport.ViewPackage.Menu.CustomerNavigationMenu;
import com.support.sport.sportsupport.ViewPackage.Menu.MyCoursesScreen;
import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.WelcomeScreen;

import java.text.SimpleDateFormat;

/**
 * Created by Faruk on 26.04.2018.
 */

public class SpecialOfferAdapter extends RecyclerView.Adapter<SpecialOfferAdapter.ViewHolder> {


    private SpecialOffer[] offers;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView offerName;
        public TextView startDate;
        public TextView endDate;
        public Button apply;

        public ViewHolder(View itemView) {
            super(itemView);
            offerName   = itemView.findViewById(R.id.offer_name);
            startDate = itemView.findViewById(R.id.offer_start_date);
            endDate = itemView.findViewById(R.id.offer_end_date);
            apply = itemView.findViewById(R.id.offer_apply);
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
        holder.startDate.setText("Starts at: "+start);
        holder.endDate.setText("Ends at: "+end);
        holder.apply.setOnClickListener(new View.OnClickListener() {
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
                alertDialog.setMessage("This offer is not available for you. Visit here later for more!");
                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
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
