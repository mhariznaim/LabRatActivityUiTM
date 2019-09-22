package com.ftmk.mhariznaim.labratactivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ftmk.mhariznaim.labratactivity.model.ExpensesModel;
import com.ftmk.mhariznaim.labratactivity.sqlite.ExpensesDB;

import java.util.List;

/**
 * Created by mhariznaim on 22/9/2019.
 */

public class CustomAdapterExpList extends RecyclerView.Adapter<CustomAdapterExpList.ViewHolder>
{

    List<ExpensesModel> expensesModelList;

    public CustomAdapterExpList(List<ExpensesModel> expensesModelList) {
        this.expensesModelList = expensesModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
.inflate(R.layout.list_expenses_recycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExpensesModel expensesModel = expensesModelList.get(position);
        holder.txtVwExpName.setText(expensesModel.getStrExpName());
        holder.txtVwExpPrice.setText(expensesModel.getDblExpPrice().toString());
        holder.txtVwExpDate.setText(expensesModel.getStrExpDate());
    }

    @Override
    public int getItemCount() {
        return expensesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtVwExpName,txtVwExpPrice,txtVwExpDate;
        public ViewHolder(View itemView) {
            super(itemView);

            txtVwExpName = itemView.findViewById(R.id.tvListExpName);
            txtVwExpPrice = itemView.findViewById(R.id.tvListExpPrice);
            txtVwExpDate = itemView.findViewById(R.id.tvListExpDate);
        }
    }
}
