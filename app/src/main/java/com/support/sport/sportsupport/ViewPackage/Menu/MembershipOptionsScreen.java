package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.support.sport.sportsupport.ViewPackage.R;

public class MembershipOptionsScreen extends AppCompatActivity {

    Button buttonPayBecomeMemberGold,buttonPayBecomeMemberStandart,buttonPayBecomeMemberPlatin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_options_screen);

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
}
