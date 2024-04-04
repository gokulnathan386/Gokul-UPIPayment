package com.example.upipaymentgateway;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.upipaymentgateway.DBHepler.DBHelperSqlite;
import com.example.upipaymentgateway.databinding.ActivityHomeBinding;


public class HomeActivity extends AppCompatActivity {


    ActivityHomeBinding binding;
    Boolean bankenable = false;
    String bankName = "";
    String bankUpiName = "";
    private DBHelperSqlite dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelperSqlite(HomeActivity.this);


        binding.hdfcBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bankenable = true;
                bankName = "Hdfc bank";

                binding.hdfclayout.setBackgroundResource(R.drawable.redcolorbackground);

                binding.icicilayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.sbilayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.kotkalayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.axislayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.punjablayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.bankofboarderLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.indianoversesLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.indlusLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);


            }
        });



        binding.icicBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bankenable = true;
                bankName = "Icici Bank";
                binding.hdfclayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.icicilayout.setBackgroundResource(R.drawable.redcolorbackground);

                binding.sbilayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.kotkalayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.axislayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.punjablayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.bankofboarderLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.indianoversesLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.indlusLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

            }
        });

        binding.sbiBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bankenable = true;
                bankName = "Sbi Bank";

                binding.hdfclayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.icicilayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.sbilayout.setBackgroundResource(R.drawable.redcolorbackground);

                binding.kotkalayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.axislayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.punjablayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.bankofboarderLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.indianoversesLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.indlusLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

            }
        });



        binding.kotkaBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bankenable = true;
                bankName = "Kotak Bank";

                binding.hdfclayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.icicilayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.sbilayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.kotkalayout.setBackgroundResource(R.drawable.redcolorbackground);

                binding.axislayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.punjablayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.bankofboarderLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.indianoversesLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.indlusLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

            }
        });


        binding.aixsBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bankenable = true;
                bankName = "Axis Bank";

                binding.hdfclayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.icicilayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.sbilayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.kotkalayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.axislayout.setBackgroundResource(R.drawable.redcolorbackground);

                binding.punjablayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.bankofboarderLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.indianoversesLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.indlusLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

            }
        });

        binding.punjaBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bankenable = true;
                bankName = "Punjab Bank";

                binding.hdfclayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.icicilayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.sbilayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.kotkalayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.axislayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.punjablayout.setBackgroundResource(R.drawable.redcolorbackground);

                binding.bankofboarderLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.indianoversesLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.indlusLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

            }
        });

        binding.bankOfBorderBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bankenable = true;
                bankName = "Bank of Baroda Bank";

                binding.hdfclayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.icicilayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.sbilayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.kotkalayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.axislayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.punjablayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.bankofboarderLayout.setBackgroundResource(R.drawable.redcolorbackground);
                binding.indianoversesLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.indlusLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

            }
        });

        binding.indianoverBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bankenable = true;
                bankName = "Indian Overseas Bank";

                binding.hdfclayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.icicilayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.sbilayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.kotkalayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.axislayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.punjablayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.bankofboarderLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.indianoversesLayout.setBackgroundResource(R.drawable.redcolorbackground);

                binding.indlusLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

            }
        });

        binding.induslandBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bankenable = true;
                bankName = "Induslnd Bank";

                binding.hdfclayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.icicilayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.sbilayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.kotkalayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.axislayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.punjablayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.bankofboarderLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);
                binding.indianoversesLayout.setBackgroundResource(R.drawable.dashboard_rect_shape);

                binding.indlusLayout.setBackgroundResource(R.drawable.redcolorbackground);

            }
        });



        binding.upiCreateDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(bankenable == false){

                    Toast.makeText(HomeActivity.this,"Choose your bank",Toast.LENGTH_LONG).show();

                }else if(binding.holderNameTxt.getText().toString().isEmpty()){

                    Toast.makeText(HomeActivity.this,"Please Enter your name",Toast.LENGTH_LONG).show();

                }else{




                    Dialog sucesspopup = new Dialog(HomeActivity.this);
                    sucesspopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    sucesspopup.setContentView(R.layout.upicreatesuccesspopup);
                    sucesspopup.setCanceledOnTouchOutside(false);
                    sucesspopup.setCancelable(false);

                    RelativeLayout donee = sucesspopup.findViewById(R.id.donee);

                    donee.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                               if(bankName.equalsIgnoreCase("Hdfc bank")){

                                   bankUpiName = "@hdfcbank";

                               }else if(bankName.equalsIgnoreCase("Icici Bank")){

                                   bankUpiName = "@icic";

                               }else if(bankName.equalsIgnoreCase("Sbi Bank")){

                                   bankUpiName = "@sbi";

                               }else if(bankName.equalsIgnoreCase("Kotak Bank")){

                                   bankUpiName = "@Kotak";

                               }else if(bankName.equalsIgnoreCase("Axis Bank")){

                                   bankUpiName = "@axisbank";

                               }else if(bankName.equalsIgnoreCase("Punjab Bank")){

                                   bankUpiName = "@psb";

                               }else if(bankName.equalsIgnoreCase("Bank of Baroda Bank")){

                                   bankUpiName = "@barodampay";

                               }else if(bankName.equalsIgnoreCase("Indian Overseas Bank")){

                                   bankUpiName = "@iob";

                               }else if(bankName.equalsIgnoreCase("Induslnd Bank")){

                                   bankUpiName = "@indus";

                               }

                            SharedPreferences sh = getSharedPreferences("UPIPayment", MODE_PRIVATE);
                            String phoneno = sh.getString("phone", "");

                            String upinumber = phoneno+bankUpiName;

                            dbHelper.updateUpiId(phoneno, upinumber,binding.holderNameTxt.getText().toString());

                            Intent i = new Intent(getApplicationContext(), DashboardScreenActivity.class);
                            startActivity(i);
                            sucesspopup.dismiss();
                        }
                    });

                    sucesspopup.show();
                    sucesspopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    sucesspopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    sucesspopup.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                    sucesspopup.getWindow().setGravity(Gravity.BOTTOM);

                }

            }
        });


    }
}