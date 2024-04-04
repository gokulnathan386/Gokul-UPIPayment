package com.example.upipaymentgateway;

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
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.upipaymentgateway.DBHepler.DBHelperSqlite;
import com.example.upipaymentgateway.databinding.ActivityHomeBinding;
import com.example.upipaymentgateway.databinding.ActivityPinScreenBinding;

public class pinScreenActivity extends AppCompatActivity {

    ActivityPinScreenBinding binding;
    private DBHelperSqlite dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);


        binding = ActivityPinScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelperSqlite(pinScreenActivity.this);

       binding.showOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pinno = binding.pinview.getText().toString();

                 if(pinno.length() == 4){
                     SharedPreferences sh = getSharedPreferences("UPIPayment", MODE_PRIVATE);
                     String phoneno = sh.getString("phone", "");
                     dbHelper.updatePinId(phoneno, pinno);


                     Dialog sucesspopup = new Dialog(pinScreenActivity.this);
                     sucesspopup.requestWindowFeature(Window.FEATURE_NO_TITLE);
                     sucesspopup.setContentView(R.layout.upicreatesuccesspopup);
                     sucesspopup.setCanceledOnTouchOutside(false);
                     sucesspopup.setCancelable(false);

                     RelativeLayout donee = sucesspopup.findViewById(R.id.donee);
                     TextView success = sucesspopup.findViewById(R.id.success);
                     success.setText("UPI Pin Created Successfully!");

                     donee.setOnClickListener(new View.OnClickListener() {
                         @Override
                         public void onClick(View view) {
                             Intent i = new Intent(pinScreenActivity.this, HomeActivity.class);
                             startActivity(i);
                             Toast.makeText(pinScreenActivity.this, pinno, Toast.LENGTH_SHORT).show();
                             sucesspopup.dismiss();
                         }
                     });


                     sucesspopup.show();
                     sucesspopup.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                     sucesspopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                     sucesspopup.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
                     sucesspopup.getWindow().setGravity(Gravity.BOTTOM);


                 }else{
                     Toast.makeText(pinScreenActivity.this, "Please create UPI pin", Toast.LENGTH_SHORT).show();
                 }


            }
        });



    }
}