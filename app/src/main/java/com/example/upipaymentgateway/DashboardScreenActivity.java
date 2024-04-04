package com.example.upipaymentgateway;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.example.upipaymentgateway.DBHepler.DBHelperSqlite;
import com.example.upipaymentgateway.databinding.ActivityDashboardScreenBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


public class DashboardScreenActivity extends AppCompatActivity  {

    ActivityDashboardScreenBinding binding;
    final  int UPI_PAYMENT = 0;
    private DBHelperSqlite dbHelper;
    String UPIPIN="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelperSqlite(DashboardScreenActivity.this);

        SharedPreferences sh = getSharedPreferences("UPIPayment", MODE_PRIVATE);
        String phoneno = sh.getString("phone", "");
        ArrayList<HashMap<String, String>> phoneNumber = dbHelper.getPhoneByName(phoneno);
        for(int i=0;i<phoneNumber.size();i++){
             UPIPIN = phoneNumber.get(i).get("UPI_PIN");
            binding.upiIDName.setText("Name :"+phoneNumber.get(i).get("UPI_NAME") + " UPI Id " + phoneNumber.get(i).get("UPI_NUMBER"));

        }

        binding.transitionHistoryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DashboardScreenActivity.this,TransitionHistoryActivity.class);
                startActivity(intent);

            }
        });

        binding.idBtnMakePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String amountUpiTxt =  binding.amountUPI.getText().toString();
                String upiIdTxt = binding.upiId.getText().toString();
                String amountCreditName = binding.amountCreditName.getText().toString();
                String descriptionTxt = binding.descriptionTxt.getText().toString();

                if(amountUpiTxt.isEmpty()){
                    Toast.makeText(DashboardScreenActivity.this,"Enter your amount",Toast.LENGTH_SHORT).show();
                }else if(upiIdTxt.isEmpty()){
                    Toast.makeText(DashboardScreenActivity.this,"Enter your UPI Id",Toast.LENGTH_SHORT).show();
                }else if(amountCreditName.isEmpty()){
                    Toast.makeText(DashboardScreenActivity.this,"Enter your name",Toast.LENGTH_SHORT).show();
                }else if(descriptionTxt.isEmpty()){
                    Toast.makeText(DashboardScreenActivity.this,"Enter your Description",Toast.LENGTH_SHORT).show();
                }else if(Integer.parseInt(amountUpiTxt) >= 50001){
                    Toast.makeText(DashboardScreenActivity.this,"Per transition below 50000 thousand only",Toast.LENGTH_SHORT).show();
                }else{

                    SharedPreferences sh = getSharedPreferences("UPIPayment", MODE_PRIVATE);
                    String phoneno = sh.getString("phone", "");

                    Calendar calendar = Calendar.getInstance();
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH) + 1;
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    String  currentdata = year + "-" + month + "-" + day;

                    Double totalAmount = dbHelper.getTotalAmountForDate(phoneno ,currentdata);

                    if(totalAmount + Integer.parseInt(amountUpiTxt) <= 100000){





                        Dialog sucesspopup = new Dialog(DashboardScreenActivity.this);
                        sucesspopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        sucesspopup.setContentView(R.layout.activity_pin_screen);
                        sucesspopup.setCanceledOnTouchOutside(false);
                        sucesspopup.setCancelable(false);

                        Button donee = sucesspopup.findViewById(R.id.show_otp);
                        PinView pinview = sucesspopup.findViewById(R.id.pinview);



                        donee.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if(pinview.getText().toString().equalsIgnoreCase(UPIPIN)){
                                    payUsingUpi(amountUpiTxt,upiIdTxt,amountCreditName,descriptionTxt);
                                    sucesspopup.dismiss();
                                }else{
                                    Toast.makeText(getApplicationContext(),"Wrong Pin, Please try again",Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


                        sucesspopup.show();
                        sucesspopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        sucesspopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        sucesspopup.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                        sucesspopup.getWindow().setGravity(Gravity.BOTTOM);


                    }else{
                        Toast.makeText(DashboardScreenActivity.this,"Pre day cross limited amount",Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });

    }

    private void payUsingUpi(String amountUpiTxt, String upiIdTxt, String amountCreditName, String descriptionTxt) {

        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa",upiIdTxt)
                .appendQueryParameter("pn",amountCreditName)
                .appendQueryParameter("tn",descriptionTxt)
                .appendQueryParameter("am",amountUpiTxt)
                .appendQueryParameter("cu","INR").build();

        Intent upi_payment = new Intent(Intent.ACTION_VIEW);
        upi_payment.setData(uri);
        Intent chooser = Intent.createChooser(upi_payment,"Pay with");
        if(null != chooser.resolveActivity(getPackageManager())){

            startActivityForResult(chooser,UPI_PAYMENT);

        }else{
            Toast.makeText(this, "No UPI app found, please install one to continue", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upipaymentdataoperation(dataList);
                    } else {
                        Log.d("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upipaymentdataoperation(dataList);;
                    }
                } else {
                    Log.d("UPI", "onActivityResult: " + "Return data is null"); //when user simply back without payment
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upipaymentdataoperation(dataList);
                }
                break;
        }


}

    private void upipaymentdataoperation(ArrayList<String> dataLst) {

        if (isConnectionAvaliable(DashboardScreenActivity.this)) {
            String str = dataLst.get(0);
            Log.d("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");

            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");

                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();

                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];

                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user.";

                }
            }


            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String  currentdata = year + "-" + month + "-" + day;


            SharedPreferences sh = getSharedPreferences("UPIPayment", MODE_PRIVATE);
            String phoneno = sh.getString("phone", "");


            if (status.equals("success")) {

                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                dbHelper.insertUPITransitionHistory(phoneno,  binding.amountUPI.getText().toString(),binding.upiId.getText().toString(),
                        binding.amountCreditName.getText().toString() ,binding.descriptionTxt.getText().toString(),mydate,"1",currentdata);

                Toast.makeText(DashboardScreenActivity.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                Log.d("UPI", "responseStr: "+approvalRefNo);
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {

                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                dbHelper.insertUPITransitionHistory(phoneno,  binding.amountUPI.getText().toString(),binding.upiId.getText().toString(),
                        binding.amountCreditName.getText().toString() ,binding.descriptionTxt.getText().toString(),mydate,"0",currentdata);


                Toast.makeText(DashboardScreenActivity.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
            }
            else {

                String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

                dbHelper.insertUPITransitionHistory(phoneno,  binding.amountUPI.getText().toString(),binding.upiId.getText().toString(),
                        binding.amountCreditName.getText().toString() ,binding.descriptionTxt.getText().toString(),mydate,"0",currentdata);


                Toast.makeText(DashboardScreenActivity.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(DashboardScreenActivity.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean isConnectionAvaliable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }

}
