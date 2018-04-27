package com.support.sport.sportsupport.ViewPackage.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.ViewPackage.R;

/**
 * Created by Merve on 25.04.2018.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private Course[] courses;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public TextView quota;
        public TextView lecdayfreq;
        public TextView enddate;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.course_list_name);
            quota = itemView.findViewById(R.id.course_list_quota);
            lecdayfreq = itemView.findViewById(R.id.course_list_next_lec);
            enddate = itemView.findViewById(R.id.course_list_end_date);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CourseAdapter(Course[] myDataset) {
        courses = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Course c = courses[position];
        holder.name.setText(c.getName()+" CLASS");
        holder.quota.setText("Quota: "+c.getAvailableQuota()+"/"+c.getQuota());
        holder.lecdayfreq.setText("Every "+c.getDeleteDay());
        holder.enddate.setText("Ends at: "+c.getDeleteDate());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return courses.length;
    }
}