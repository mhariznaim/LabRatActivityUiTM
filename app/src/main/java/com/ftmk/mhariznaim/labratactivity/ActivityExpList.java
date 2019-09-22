package com.ftmk.mhariznaim.labratactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.AbsoluteLayout;

import com.ftmk.mhariznaim.labratactivity.model.ExpensesModel;
import com.ftmk.mhariznaim.labratactivity.sqlite.ExpensesDB;

import java.util.ArrayList;

public class ActivityExpList extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ExpensesModel> expensesList;

    ExpensesDB expensesDB;
    CustomAdapterExpList customAdapterExpList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp_list);

        recyclerView = findViewById(R.id.recyclerListExp);
        // set the arraylist from the database query
        expensesDB = new ExpensesDB(getApplicationContext());
        expensesList = (ArrayList<ExpensesModel>) expensesDB.fnGetAllExpenses();
        // set the customAdapter with data
        customAdapterExpList = new CustomAdapterExpList(expensesDB.fnGetAllExpenses());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customAdapterExpList);
        customAdapterExpList.notifyDataSetChanged(); // Incase having data change,
    }
}
