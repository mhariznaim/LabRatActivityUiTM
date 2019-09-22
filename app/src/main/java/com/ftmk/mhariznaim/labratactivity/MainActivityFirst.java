package com.ftmk.mhariznaim.labratactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivityFirst extends AppCompatActivity {

    EditText edtUsername, edtCurrent;
    TextView tvGreet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_first);

        edtUsername = findViewById(R.id.edtUsername);
        edtCurrent = findViewById(R.id.edtDob);
        tvGreet = findViewById(R.id.txtVwGreet);

    }


    public void fnGreet(View view)
    {
        int year = Calendar.getInstance().get(Calendar.YEAR);

        int age = year - Integer.parseInt(edtCurrent.getText().toString());
        String strText = edtUsername.getText().toString();
        Toast.makeText(this,"Hello there " + strText, Toast.LENGTH_SHORT).show();
        tvGreet.setText("Your age is  " + age);
    }

    public void fnThreadedActivity(View view)
    {
        Intent intent = new Intent(this,ThreadedActivity.class);
        String strMsg = edtUsername.getText().toString();
        intent.putExtra("varStr1", strMsg);

        startActivity(intent);
    }
}
