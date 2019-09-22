package com.ftmk.mhariznaim.labratactivity.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ftmk.mhariznaim.labratactivity.model.ExpensesModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mhariznaim on 21/9/2019.
 */

public class ExpensesDB extends SQLiteOpenHelper {

    public static final String dbName = "dbMyExpense2" ;
    //first table
    public static final String tblNameExp = "expenses";
    public static final String colExpName = "expenses_name";
    public static final String colExpPrice = "expenses_price";
    public static final String colExpDate = "expenses_date";
    public static final String colExpId = "expenses_id";

    //second table
    public static final String tblNameUsers = "users";
    public static final String colUserName = "user_name";
    public static final String colUserDOB = "user_dob";
    public static final String colUserGender= "user_gender";
    public static final String colUserStat = "user_stat";

    public static final String strCreateTblExp = "CREATE TABLE "+ tblNameExp +
            " ("+ colExpId +" INTEGER PRIMARY KEY, " + colExpName +" TEXT, " +
            colExpPrice +" REAL,"+ colExpDate +" DATE)";
    public static final String strDropTable = "DROP TABLE IF EXISTS "+ tblNameExp ;

    public ExpensesDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public ExpensesDB(Context context)
    {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {

        sqLiteDatabase.execSQL(strCreateTblExp);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(strDropTable);
        onCreate(sqLiteDatabase);
    }

    public float fnInsertExpense(ExpensesModel expensesModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(colExpName, expensesModel.getStrExpName());
        values.put(colExpPrice,expensesModel.getDblExpPrice());
        values.put(colExpDate,expensesModel.getStrExpDate());

        return db.insert(tblNameExp,null,values);
    }

    public ExpensesModel fnGetExpenses(int intId)
    {
        ExpensesModel expensesModel = new ExpensesModel();
        String strQry = "Select  * from " + tblNameExp + "where " + colExpId + " = " + intId;
        Cursor cursor = this.getReadableDatabase().rawQuery(strQry,null);

        if(cursor!=null)
        {
           cursor.moveToFirst();
        }
        expensesModel.setStrExpName(cursor.getString(cursor.getColumnIndex(colExpName)));
        expensesModel.setDblExpPrice(cursor.getDouble(cursor.getColumnIndex(colExpPrice)));
        expensesModel.setStrExpDate(cursor.getString(cursor.getColumnIndex(colExpDate)));

        return expensesModel;
    }

    public List<ExpensesModel> fnGetAllExpenses()
    {
        List<ExpensesModel> listExp = new ArrayList<>();
        String strQry = "Select * from " + tblNameExp;
        Cursor cursor = this.getReadableDatabase().rawQuery(strQry,null);

        if(cursor.moveToFirst())
        {
            do {
                ExpensesModel expensesModel = new ExpensesModel();
                expensesModel.setStrExpDate(cursor.getString(cursor.getColumnIndex(colExpDate)));
                expensesModel.setDblExpPrice(cursor.getDouble(cursor.getColumnIndex(colExpPrice)));
                expensesModel.setStrExpName(cursor.getString(cursor.getColumnIndex(colExpName)));
                listExp.add(expensesModel);
            }while (cursor.moveToNext());
        }
        return  listExp;
    }
}
