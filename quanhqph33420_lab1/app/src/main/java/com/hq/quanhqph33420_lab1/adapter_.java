package com.hq.quanhqph33420_lab1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class adapter_ extends RecyclerView.Adapter<adapter_.rcv_holder> {
    private List<UserModel> list;
    private MainActivity context;

    public adapter_(List<UserModel> list, MainActivity context) {
        this.list = list;
        this.context = context;
    }

    static class rcv_holder extends RecyclerView.ViewHolder {
        TextView txt_id;
        TextView txt_user;
        TextView txt_pass;
        Button btn_del;
        Button btn_up;

        public rcv_holder(@NonNull View itemView) {
            super(itemView);
            txt_id = itemView.findViewById(R.id.id_);
            txt_user = itemView.findViewById(R.id.user_);
            txt_pass = itemView.findViewById(R.id.pass_);
            btn_del = itemView.findViewById(R.id.del_);
            btn_up = itemView.findViewById(R.id.up_);
        }
    }

    @NonNull
    @Override
    public rcv_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_, null, false);
        return new rcv_holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull rcv_holder holder, int position) {
        holder.txt_id.setText(list.get(position).getId());
        holder.txt_user.setText(list.get(position).getUser());
        holder.txt_pass.setText(list.get(position).getPass());
        holder.btn_del.setOnClickListener(e -> {
            context.clickDel(list.get(position).getId());
        });
        holder.btn_up.setOnClickListener(e -> {
            context.clickUp(list.get(position).getId());
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
