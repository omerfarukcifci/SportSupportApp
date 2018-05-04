package com.support.sport.sportsupport.ViewPackage.Menu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.WelcomeScreen;

import java.text.DecimalFormat;
import java.util.Random;

public class CustomerNavigationMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private boolean viewIsAtHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        /*Menu menu = navigationView.getMenu();
        MenuItem nav_fullness = menu.findItem(R.id.nav_fullness);
        nav_fullness.setTitle("80%");*/

        navigationView.setNavigationItemSelectedListener(this);

        displayView(R.id.nav_my_profile);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        if (!viewIsAtHome) { //if the current view is not the News fragment
            displayView(R.id.nav_my_profile); //display the News fragment
        } else {
            moveTaskToBack(true);  //If view is in News fragment, exit application
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_update:
                Intent intent = new Intent(this, UpdateProfileScreen.class);
                startActivity(intent);
                return true;
            case R.id.action_cancel:
                Intent intent2 = new Intent(this, CancelMembershipScreen.class);
                startActivity(intent2);
                return true;
            case R.id.action_logout:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CustomerNavigationMenu.this);
                // Setting Dialog Title
                alertDialog.setTitle("Confirm Logout");
                alertDialog.setCancelable(true);
                // Setting Dialog Message
                alertDialog.setMessage("Are you sure you want to log out?");
                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        Intent intent3 = new Intent(CustomerNavigationMenu.this,WelcomeScreen.class);
                        startActivity(intent3);
                    }
                });
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        dialog.cancel();
                    }
                });
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        /*int id = item.getItemId();

        if (id == R.id.nav_my_profile) {
            Intent intent = new Intent(CustomerNavigationMenu.this,MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_courses) {
            Intent intent = new Intent(CustomerNavigationMenu.this,UpdateProfileScreen.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawer.closeDrawer(GravityCompat.START);*/
        displayView(item.getItemId());
        return true;
    }

    //This function controls our buttons in drawer menu. And we are creating new fragment falan filan
    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.nav_my_profile:
                fragment = new FragmentMyProfile();
                title  = "My Profile";
                viewIsAtHome = true;
                break;
            case R.id.nav_courses:
                fragment = new FragmentAllCourses();
                title = "Courses";
                viewIsAtHome = false;
                break;
            case R.id.nav_special_offer:
                fragment = new FragmentSpecialOffer();
                title = "Special Offers";
                viewIsAtHome = false;
                break;
            case R.id.nav_fullness:
                fullnessDialog();
                viewIsAtHome = false;
                break;

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    private void fullnessDialog(){
        double rangeMin=0;
        double rangeMax=100;
        Random r = new Random();
        double ratio = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this)
                .setCancelable(true);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.layout_fullness, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(true);

        TextView textView = dialogView.findViewById(R.id.txt_full_ratio);
        textView.setText(df.format(ratio)+"%");
        ProgressBar progressBar = dialogView.findViewById(R.id.fullness_bar_progress);
        progressBar.setProgress((int)ratio);
        Button button = dialogView.findViewById(R.id.full_dialog_ok);
        final AlertDialog alertDialog = dialogBuilder.create();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        alertDialog.show();
    }







}
