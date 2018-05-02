package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.support.sport.sportsupport.ViewPackage.R;

import java.util.ArrayList;
import java.util.List;

public class MembershipOptionsScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button buttonPayBecomeMemberGold,buttonPayBecomeMemberStandart,buttonPayBecomeMemberPlatin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_options_screen);

        Spinner spinner = (Spinner) findViewById(R.id.branch_spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Eryaman");
        categories.add("Çukurambar");
        categories.add("Ulus");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        buttonPayBecomeMemberGold = (Button) findViewById(R.id.pay_become_member_button_gold);
        buttonPayBecomeMemberStandart = (Button) findViewById(R.id.pay_become_member_button_standard);
        buttonPayBecomeMemberPlatin = (Button) findViewById(R.id.pay_become_member_button_platin);
        buttonPayBecomeMemberGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MembershipOptionsScreen.this,PaymentScreen.class);
                startActivity(intent);
            }
        });
        buttonPayBecomeMemberStandart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MembershipOptionsScreen.this,PaymentScreen.class);
                startActivity(intent);
            }
        });
        buttonPayBecomeMemberPlatin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MembershipOptionsScreen.this,PaymentScreen.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final Snackbar mySnackbar = Snackbar.make(view, "Membership Options Refreshed!", Snackbar.LENGTH_SHORT);
        mySnackbar.show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
