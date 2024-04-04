package com.example.upipaymentgateway;

import static java.lang.Integer.parseInt;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.upipaymentgateway.DBHepler.DBHelperSqlite;
import com.example.upipaymentgateway.adapter.ListTransitionHistoryAdapter;
import com.example.upipaymentgateway.databinding.ActivityTransitionHistoryBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class TransitionHistoryActivity extends AppCompatActivity {

    ActivityTransitionHistoryBinding binding;
    private DBHelperSqlite dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityTransitionHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbHelper = new DBHelperSqlite(TransitionHistoryActivity.this);

        SharedPreferences sh = getSharedPreferences("UPIPayment", MODE_PRIVATE);
        String phoneno = sh.getString("phone", "");

        ArrayList<HashMap<String, String>> getTransitionHistory = dbHelper.getUpitransition(phoneno);

        if(getTransitionHistory.size() != 0){

            binding.transitionListLayout.setVisibility(View.VISIBLE);
            binding.noDataFound.setVisibility(View.GONE);
            ListTransitionHistoryAdapter listTransitionHistoryAdapter = new ListTransitionHistoryAdapter(TransitionHistoryActivity.this,getTransitionHistory);
            LinearLayoutManager layoutManagerpayment = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            binding.transitionListLayout.setLayoutManager(layoutManagerpayment);
            binding.transitionListLayout.setAdapter(listTransitionHistoryAdapter);

        }else{
            binding.transitionListLayout.setVisibility(View.GONE);
            binding.noDataFound.setVisibility(View.VISIBLE);
        }






    }
}