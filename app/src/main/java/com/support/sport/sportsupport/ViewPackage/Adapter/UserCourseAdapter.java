package com.support.sport.sportsupport.ViewPackage.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.support.sport.sportsupport.Controller.CourseController;
import com.support.sport.sportsupport.Model.Course;
import com.support.sport.sportsupport.ViewPackage.R;

import java.util.List;

public class UserCourseAdapter extends RecyclerView.Adapter<UserCourseAdapter.ViewHolder> {

    private List<Course> courses;

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
            name = itemView.findViewById(R.id.course_list_nameU);
            quota = itemView.findViewById(R.id.course_list_quotaU);
            lecdayfreq = itemView.findViewById(R.id.course_list_next_lecU);
            enddate = itemView.findViewById(R.id.course_list_end_dateU);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public UserCourseAdapter(List<Course> myDataset) {
        courses = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public UserCourseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_course_list_item, parent, false);

        UserCourseAdapter.ViewHolder vh = new UserCourseAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(UserCourseAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Course c = courses.get(position);
        holder.name.setText(c.getName()+" Class");
        holder.quota.setText("Quota: "+c.getAvailableQuota()+"/"+c.getQuota());
        holder.lecdayfreq.setText("Every Week");
        holder.enddate.setText("Ends at: "+(c.getEndDate().split("T"))[0]);



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return courses.size();
    }
}
