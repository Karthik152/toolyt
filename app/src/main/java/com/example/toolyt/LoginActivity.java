package com.example.toolyt;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private AlertDialog OtpDialog;
    EditText phone;
    private EditText otp1, otp2, otp3, otp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ImageView toolbarBackButton = findViewById(R.id.toolbarBackButton);

        toolbarBackButton.setOnClickListener(view -> {
            onBackPressed();
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        });
        TextView toolBarTitle=findViewById(R.id.toolBarTitle);
        toolBarTitle.setText("Login");
        setTitle("Login");




        phone=findViewById(R.id.edttxtphone);

        Button getstart=findViewById(R.id.getstart);
        getstart.setOnClickListener(v ->{
            if (phone.getText().length() >= 10){
                ShowOTPPopup(String.valueOf(phone.getText().toString()));
            }else {
                phone.setError("Enter  10 digit valid number");
            }

        });

        //Your confirmation code is below — enter it  and we'll help you get signed in.

    }

    private void ShowOTPPopup(String phonenum) {

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this, 0);
        View mV = getLayoutInflater().inflate(R.layout.dailog_enter_otp, null);
        ImageView btnClose = mV.findViewById(R.id.closebtn);
        Button submitbtn = mV.findViewById(R.id.submitbtn);
        TextView showotp=mV.findViewById(R.id.Showotp);
//        String passnumer=phone.getText().toString();

        otp1 = mV.findViewById(R.id.otp1);
        otp2 = mV.findViewById(R.id.otp2);
        otp3 = mV.findViewById(R.id.otp3);
        otp4 = mV.findViewById(R.id.otp4);



        builder.setView(mV);

        String otpToSet;

        if (phonenum.length() >= 4) { // Ensure the string has at least 4 characters
            String firstTwo = phonenum.substring(0, 2); // First 2 characters
            String lastTwo = phonenum.substring(phonenum.length() - 2); // Last 2 characters
            otpToSet = firstTwo + lastTwo; // Combine them
        } else {
            otpToSet = phonenum; // Handle cases where the string is too short
        }
        showotp.setText(""+otpToSet);

        if (otpToSet.length() == 4) {
            String[] parts = otpToSet.split("");
            if (otp1 != null) {
                otp1.setText(parts[0]);
                otp2.setText(parts[1]);
                otp3.setText(parts[2]);
                otp4.setText(parts[3]);

            }
        }
        OtpDialog = builder.create();
        OtpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if (!OtpDialog.isShowing()) {
            OtpDialog.show();
        }
        OtpDialog.setCancelable(false);
        btnClose.setOnClickListener(v -> OtpDialog.dismiss());

        submitbtn.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, verifyotp.class);
            intent.putExtra("mobile",phone.getText().toString());
            intent.putExtra("otp",otpToSet);

            startActivity(intent);
        });
    }


}