package com.sneaker.shoeapp.Adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sneaker.shoeapp.R;
import com.sneaker.shoeapp.model.Order;

import java.util.List;

public class OrderAdapter extends  RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{
    List<Order> orderList;

    public OrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    public void setData(List<Order> orderList){
        this.orderList = orderList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order,parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.Order_ID.setText("Order ID: #"+order.getId());
        holder.Order_Date.setText("Date: "+order.getDate());
        holder.Order_Quantity.setText(order.getQuantity()+" Products");
        if(order.getStatus() == true){
            holder.Order_Status.setText("Delivered");
        }
        holder.Order_Value.setText("$"+order.getTotal_value());

    }

    @Override
    public int getItemCount() {
        if(orderList !=null){
            return orderList.size();
        }
        return 0;
    }



    public class OrderViewHolder extends RecyclerView.ViewHolder{
        TextView Order_ID,Order_Date, Order_Status, Order_Quantity, Order_Value;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            Order_ID = itemView.findViewById(R.id.Order_ID);
            Order_Date = itemView.findViewById(R.id.Order_Date);
            Order_Status = itemView.findViewById(R.id.Order_Status);
            Order_Quantity = itemView.findViewById(R.id.Order_Quantity);
            Order_Value = itemView.findViewById(R.id.Order_Value);
        }
    }
}

