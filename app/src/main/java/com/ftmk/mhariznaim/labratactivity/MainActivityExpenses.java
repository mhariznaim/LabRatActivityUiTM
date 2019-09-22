package com.ftmk.mhariznaim.labratactivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ftmk.mhariznaim.labratactivity.model.ExpensesModel;
import com.ftmk.mhariznaim.labratactivity.sqlite.ExpensesDB;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivityExpenses extends AppCompatActivity  implements View.OnClickListener{

    EditText edtExpName, edtExpPrice,edtExpDate, edtExpTime;
    int mYear,mMonth,mDay;
    ExpensesDB expensesDB;

    String strUrl = "http://rku.utem.edu.my/webServiceJSON/LabTest.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_expenses);

        edtExpName = findViewById(R.id.edtExpName);
        edtExpPrice = findViewById(R.id.edtExpPrice);
        edtExpDate = findViewById(R.id.edtExpDate);
        edtExpTime = findViewById(R.id.edtTime);
        edtExpDate.setOnClickListener(this);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("myExpenses",
                Context.MODE_PRIVATE);
        String strUname = sharedPreferences.getString("uname",null) ;
        Toast.makeText(this,"Welcome " + strUname , Toast.LENGTH_SHORT).show();

        expensesDB  = new ExpensesDB(getApplicationContext());

        //accessing REST web service via Volley
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, strUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String strData = jsonObject.getString("usdRate");
                    edtExpTime.setText("Data from REST: " + strData);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params  = new HashMap<String,String>();
                params.put("selectLogic", "fnGetUSDRate");

                return params;
            }
        };
        requestQueue.add(stringRequest);
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
            Toast.makeText(this,"Expense Saved! Result " + retResult, Toast.LENGTH_SHORT).show();
        }catch (SQLiteException e)
        {
            Toast.makeText(this,"Error Saving into SQLite", Toast.LENGTH_SHORT).show();
        }

        float retResult = expensesDB.fnInsertExpense(expensesModel);
        System.out.print("result" + retResult);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.optionmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idMenu = item.getItemId();
        Intent intent = null;
        if(idMenu == R.id.expList)
        {
            intent = new Intent(this, ActivityExpList.class);
        }
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
