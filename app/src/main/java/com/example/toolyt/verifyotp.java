package com.example.toolyt;

import static com.example.toolyt.utils.utils.HideKeyboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class verifyotp extends AppCompatActivity implements TextWatcher {
    String Phonenumber = "",otp="";

    Button editnumber,verify;
    TextView Resend;
    EditText enteredotp;
    private EditText otp1, otp2, otp3, otp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_verifyotp);

        Resend=findViewById(R.id.Resend);
        ImageView toolbarBackButton=findViewById(R.id.toolbarBackButton);
        toolbarBackButton.setOnClickListener(view -> {
            onBackPressed();
        });

        TextView toolBarTitle=findViewById(R.id.toolBarTitle);
        toolBarTitle.setText("Verify OTP");
        setTitle("Verify OTP");
        editnumber=findViewById(R.id.btnSubmit);
        verify=findViewById(R.id.verifyotp);
        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);

//
        otp1.addTextChangedListener(this);
        otp2.addTextChangedListener(this);
        otp3.addTextChangedListener(this);
        otp4.addTextChangedListener(this);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("mobile")) {
                Phonenumber = bundle.getString("mobile");
                System.out.println("Phonenumber"+Phonenumber);
                editnumber.setText(Phonenumber);

            }
            if (bundle.containsKey("otp")) {
                otp = bundle.getString("otp");
                System.out.println("otp"+otp);
//                editnumber.setText(otp);

            }
        }

        verify.setOnClickListener(v -> {

            String o1 = otp1.getText().toString().trim();
            String o2 = otp2.getText().toString().trim();
            String o3 = otp3.getText().toString().trim();
            String o4 = otp4.getText().toString().trim();

            String finalOTP = o1 + o2 + o3 + o4 ;

            if (finalOTP.equals(otp)){
                Intent intent= new Intent(verifyotp.this,RegisterActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Enter Valid otp", Toast.LENGTH_SHORT).show();
            }


        });


        Resend.setOnClickListener(v -> {
            Intent intent= new Intent(verifyotp.this,LoginActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.length() == 1) {
            if (otp1.length() == 1) {
                otp2.requestFocus();
            }
            if (otp2.length() == 1) {
                otp3.requestFocus();
            }
            if (otp3.length() == 1) {
                otp4.requestFocus();
            }
            if (otp4.length() == 1) {
                HideKeyboard(verifyotp.this);
            }

        } else if (editable.length() == 0) {

            if (otp4.length() == 0) {
                otp3.requestFocus();
            }
            if (otp3.length() == 0) {
                otp2.requestFocus();
            }
            if (otp2.length() == 0) {
                otp1.requestFocus();
            }
        }

    }


}