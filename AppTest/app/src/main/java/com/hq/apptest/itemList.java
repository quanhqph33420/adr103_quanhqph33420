package com.hq.apptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class itemList extends ArrayAdapter<sachModel> {
    MainActivity mainActivity;
    List<sachModel> list;

    public itemList(@NonNull MainActivity context, @NonNull List<sachModel> sachModels) {
        super(context, R.layout.item, sachModels);
        mainActivity = context;
        list = sachModels;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = LayoutInflater.from(mainActivity).inflate(R.layout.item, null, false);
        TextView masach = v.findViewById(R.id.masach_item);
        TextView tieude = v.findViewById(R.id.tieude_item);
        TextView tacgia = v.findViewById(R.id.tacgia_item);
        TextView namxuatban = v.findViewById(R.id.namxuatban_item);
        TextView sotrang = v.findViewById(R.id.sotrang_item);
        TextView theloai = v.findViewById(R.id.theloai_item);

        Button sua = v.findViewById(R.id.sua_item);
        Button xoa = v.findViewById(R.id.xoa_item);

        xoa.setOnClickListener(e -> {
            mainActivity.delete(list.get(position).get_id());
        });

        v.setOnClickListener(e -> {
            sachModel sach = new sachModel(
                    list.get(position).get_id(),
                    list.get(position).getMasach_ph33420(),
                    list.get(position).getTieude_ph33420(),
                    list.get(position).getTacgia_ph33420(),
                    list.get(position).getNamxuatban_ph33420(),
                    list.get(position).getSotrang_ph33420(),
                    list.get(position).getTheloai_ph33420());
            mainActivity.show(sach);
        });

        masach.setText("Ma sach: " + list.get(position).getMasach_ph33420());
        tieude.setText("Tieu de: " + list.get(position).getTieude_ph33420());
        tacgia.setText("Tac gia: " + list.get(position).getTacgia_ph33420());
        namxuatban.setText("Nam xuat ban: " + list.get(position).getNamxuatban_ph33420());
        sotrang.setText("So trang: " + list.get(position).getSotrang_ph33420());
        theloai.setText("The loai: " + list.get(position).getTheloai_ph33420());

        sua.setOnClickListener(e -> {
            sachModel sach = new sachModel(
                    list.get(position).get_id(),
                    list.get(position).getMasach_ph33420(),
                    list.get(position).getTieude_ph33420(),
                    list.get(position).getTacgia_ph33420(),
                    list.get(position).getNamxuatban_ph33420(),
                    list.get(position).getSotrang_ph33420(),
                    list.get(position).getTheloai_ph33420());
            mainActivity.update(sach);
        });
        return v;
    }
}
