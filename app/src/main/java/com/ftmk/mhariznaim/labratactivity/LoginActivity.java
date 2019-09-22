package com.ftmk.mhariznaim.labratactivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText edtUname, edtPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUname = findViewById(R.id.edtuname);
        edtPwd = findViewById(R.id.edtPwd);

        sharedPreferences = this.getSharedPreferences("myExpenses", Context.MODE_PRIVATE);

    }


    public void fnLogin(View view)
    {

        String strPwd = edtPwd.getText().toString();
        String uname = edtUname.getText().toString();

        editor = sharedPreferences.edit();

        editor.putString("uname", uname);
        editor.commit();

        Intent intent = new Intent(this,MainActivityExpenses.class);
        startActivity(intent);
        finish();

    }
}
