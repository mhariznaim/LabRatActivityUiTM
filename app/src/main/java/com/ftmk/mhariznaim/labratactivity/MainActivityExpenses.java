package com.ftmk.mhariznaim.labratactivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.ftmk.mhariznaim.labratactivity.model.ExpensesModel;
import com.ftmk.mhariznaim.labratactivity.sqlite.ExpensesDB;

import java.util.Calendar;

public class MainActivityExpenses extends AppCompatActivity  implements View.OnClickListener{

    EditText edtExpName, edtExpPrice,edtExpDate;
    int mYear,mMonth,mDay;
    ExpensesDB expensesDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_expenses);

        edtExpName = findViewById(R.id.edtExpName);
        edtExpPrice = findViewById(R.id.edtExpPrice);
        edtExpDate = findViewById(R.id.edtExpDate);

        edtExpDate.setOnClickListener(this);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myExpenses",
                Context.MODE_PRIVATE);
        String strUname = sharedPreferences.getString("uname",null) ;
        Toast.makeText(this,"Welcome " + strUname , Toast.LENGTH_SHORT).show();

        expensesDB  = new ExpensesDB(getApplicationContext());
    }

    @Override
    public void onClick(View view) {
        if(view == edtExpDate)
        {
            // Get Current Date
             final Calendar c = Calendar.getInstance();
             mYear = c.get(Calendar.YEAR);
             mMonth = c.get(Calendar.MONTH);
             mDay = c.get(Calendar.DAY_OF_MONTH);
             DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener()
             {
                 @Override
                 public void onDateSet(DatePicker view, int year,int monthOfYear, int dayOfMonth)
                 {
                     edtExpDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                 }
             }, mYear, mMonth, mDay);
             datePickerDialog.show();

        }
    }

    public void fnSave(View view)
    {
        ExpensesModel expensesModel = new ExpensesModel();
        expensesModel.setStrExpName(edtExpName.getText().toString());
        expensesModel.setDblExpPrice(Double.parseDouble(edtExpPrice.getText().toString()));
        String strDate = edtExpDate.getText().toString();
        expensesModel.setStrExpDate(edtExpDate.getText().toString());

        try {
            float retResult = expensesDB.fnInsertExpense(expensesModel);
        }catch (SQLiteException e)
        {
            Toast.makeText(this,"Error Saving into SQLite", Toast.LENGTH_SHORT).show();
        }

        float retResult = expensesDB.fnInsertExpense(expensesModel);
        System.out.print("result" + retResult);
        Toast.makeText(this,"Expense Saved!", Toast.LENGTH_SHORT).show();

    }
}
