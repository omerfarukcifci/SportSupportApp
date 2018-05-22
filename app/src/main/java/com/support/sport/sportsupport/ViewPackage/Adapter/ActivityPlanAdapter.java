package com.support.sport.sportsupport.ViewPackage.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Model.ActivityPlan;
import com.support.sport.sportsupport.Model.Move;
import com.support.sport.sportsupport.ViewPackage.R;

import java.util.ArrayList;

/**
 * Created by Faruk on 7.05.2018.
 */

public class ActivityPlanAdapter extends RecyclerView.Adapter<ActivityPlanAdapter.ViewHolder> {

    private ActivityPlan[] plans;
    private Move[] moves;
    private int[] layouts;
    private int layoutPosition;
    private int targetLayout;


    public static class ViewHolder extends RecyclerView.ViewHolder implements CompoundButton.OnCheckedChangeListener {

        public CheckBox moveCheckbox;
        public TextView moveName;
        public EditText setNumber;

        public TextView moveNameShow;
        public TextView setNumShow;


        public ViewHolder(final View itemView) {
            super(itemView);

            Key.selectedMovements = new ArrayList<Move>();
            moveCheckbox = (CheckBox) itemView.findViewById(R.id.add_schedule_checkbox);
            moveCheckbox.setOnCheckedChangeListener(this);
            moveName = (TextView) itemView.findViewById(R.id.add_schedule_move_name);
            setNumber = (EditText) itemView.findViewById(R.id.add_schedule_set_number);

            moveNameShow = (TextView) itemView.findViewById(R.id.show_schedule_move_name);
            setNumShow = (TextView) itemView.findViewById(R.id.show_schedule_set_number);

        }


        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            int adapterPosition = getAdapterPosition();
            if(b==true){
                Move m = new Move(Integer.valueOf(setNumber.getText().toString()),moveName.getText().toString());

                Key.selectedMovements.add(m);
            }else {
                //Key.selectedMovements.remove();
            }

        }
    }

    public ActivityPlanAdapter(ActivityPlan[] myDataset,int pos) {

        plans = myDataset;
        this.layoutPosition=pos;
        this.layouts = new int[3];
        this.layouts[0]=R.layout.activity_plan_item;
        this.layouts[1]=R.layout.activity_plan_item_show;
        this.targetLayout = this.layouts[pos];
    }
    public ActivityPlanAdapter(Move[] myDataset, int pos) {

        moves = myDataset;
        this.layoutPosition=pos;
        this.layouts = new int[3];
        this.layouts[0]=R.layout.activity_plan_item;
        this.layouts[1]=R.layout.activity_plan_item_show;
        this.targetLayout = this.layouts[pos];
    }



    @Override
    public ActivityPlanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(targetLayout, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ActivityPlanAdapter.ViewHolder holder, int position) {



        if(layoutPosition==0){
            Move movement = moves[position];
            holder.moveCheckbox.setChecked(false);
            holder.moveName.setText(""+movement.getName());
            holder.setNumber.setText("0");

        }
        if(layoutPosition==1){
            ActivityPlan m = plans[position];
            holder.moveNameShow.setText(""+m.getName());
            holder.setNumShow.setText(""+m.getSets());
        }


    }

    @Override
    public int getItemCount() {
        int ret=0;
        if(plans!=null){
            ret = plans.length;
        }
        if(moves!=null){
            ret = moves.length;
        }
        return ret;
    }


}
