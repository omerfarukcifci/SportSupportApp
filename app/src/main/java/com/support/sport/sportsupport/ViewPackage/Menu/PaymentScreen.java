package com.support.sport.sportsupport.ViewPackage.Menu;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.support.sport.sportsupport.Controller.ProfileController;
import com.support.sport.sportsupport.Model.Member;

import com.support.sport.sportsupport.ViewPackage.R;


public class PaymentScreen extends AppCompatActivity {
    private EditText cardHolder,cardNumber,cvv2number,exprationDate;
    private Button pay;
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_screen);
        cardHolder = findViewById(R.id.EDTcardHolderName);
        cardNumber = findViewById(R.id.EDTcardNumber);
        cvv2number = findViewById(R.id.EDTcvv2number);
        exprationDate = findViewById(R.id.EDTexprationDate);
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

        // alert dialog başlığını tanımlıyoruz.
          alertDialogBuilder.setTitle("Are you sure?");

        // alert dialog özelliklerini oluşturuyoruz.
        alertDialogBuilder
                .setMessage("Product name:PLATIN MEMBERSHIP\nTotal Cost:\t 66TL \n")
                .setCancelable(false)
         //       .setIcon(R.mipmap.ic_launcher_round)
                // Evet butonuna tıklanınca yapılacak işlemleri buraya yazıyoruz.
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(PaymentScreen.this, "Payment Succesful", Toast.LENGTH_LONG).show();
                    }
                })
                // İptal butonuna tıklanınca yapılacak işlemleri buraya yazıyoruz.
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        // alert dialog nesnesini oluşturuyoruz
        AlertDialog alertDialog = alertDialogBuilder.create();

        // alerti gösteriyoruz
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
