package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.Key;
import com.support.sport.sportsupport.Controller.PaymentController;
import com.support.sport.sportsupport.Controller.ProfileController;
import com.support.sport.sportsupport.Model.Member;

import com.support.sport.sportsupport.ViewPackage.R;
import com.support.sport.sportsupport.ViewPackage.RetrofitEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class PaymentScreen extends AppCompatActivity {
    private EditText cardHolder,cardNumber,cvv2number,exprationDate;
    private TextView paymentSummary;
    private Button pay;
    private String product;
    private int cost,branch;
    final Context context = this;

    @Subscribe
    public void onEvent(RetrofitEvent event) {

        if(event.isRetrofitCompleted){
            Toast.makeText(PaymentScreen.this, "Payment Successful", Toast.LENGTH_LONG).show();
            finish();
        }else{
            Toast.makeText(PaymentScreen.this, "Payment failed! Please try again.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_screen);
        Intent intent = getIntent();
        product = intent.getStringExtra("product");
        cost = intent.getIntExtra("cost",0);
        branch = intent.getIntExtra("branch",0);
        paymentSummary = findViewById(R.id.payment_summary);
        cardHolder = findViewById(R.id.EDTcardHolderName);
        cardNumber = findViewById(R.id.EDTcardNumber);
        cvv2number = findViewById(R.id.EDTcvv2number);
        exprationDate = findViewById(R.id.EDTexprationDate);
        paymentSummary.setText(product+" Membership\nTotal Cost: "+cost+" TL");
        pay = findViewById(R.id.BTNpay);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int blankController = 0;

                blankController = controlBlank(cardHolder);
                blankController += controlBlank(cardNumber);
                blankController += controlBlank(cvv2number);
                blankController += controlBlank(exprationDate);

                if(blankController == 0){
                    showDialog();
                }else{
                    blankController =0;
                }

            }
        });

    }

    public void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Are you sure?");
        alertDialogBuilder
                .setMessage("Product name:"+product+" Membership\nTotal Cost: "+cost+" TL")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new PaymentController().payNow(Key.cMember.getId(),"1997-07-07",branch,product.toLowerCase());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public int controlBlank(EditText edt){

        String strUserName = edt.getText().toString();
        if(TextUtils.isEmpty(strUserName)) {
            edt.setError("This field cannot be empty.");
            return 1;
        }
        return 0;
    }
}
