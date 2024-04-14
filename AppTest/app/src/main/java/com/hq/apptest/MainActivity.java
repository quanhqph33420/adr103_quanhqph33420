package com.hq.apptest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    itemList item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        reload();
        FloatingActionButton them = findViewById(R.id.them_list);

        them.setOnClickListener(e -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View v = LayoutInflater.from(this).inflate(R.layout.add, null, false);
            builder.setView(v);
            Dialog dialog = builder.create();

            EditText masach = v.findViewById(R.id.masach_add);
            EditText tieude = v.findViewById(R.id.tieude_add);
            EditText tacgia = v.findViewById(R.id.tacgia_add);
            EditText namxuatban = v.findViewById(R.id.namxuatban_add);
            EditText sotrang = v.findViewById(R.id.sotrang_add);
            EditText theloai = v.findViewById(R.id.theloai_add);
            Button thems = v.findViewById(R.id.them_add);
            Button thoat = v.findViewById(R.id.thoat_add);

            thems.setOnClickListener(m -> {
                String ms = masach.getText().toString();
                String td = tieude.getText().toString();
                String tg = tacgia.getText().toString();
                String nxb = namxuatban.getText().toString();
                String st = sotrang.getText().toString();
                String tl = theloai.getText().toString();

                sachModel sach = new sachModel(ms, td, tg, Integer.parseInt(nxb), Integer.parseInt(st), tl);
                RetrofitApi retrofitApi = RetrofitFunc.callRetrofit();
                Call<Integer> call = retrofitApi.addData(sach);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        if (response.body() == 1) {
                            Toast.makeText(MainActivity.this, "Add complete!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Add failed!", Toast.LENGTH_SHORT).show();

                        }
                        dialog.dismiss();
                        reload();
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {

                    }
                });
            });

            thoat.setOnClickListener(m -> {
                dialog.dismiss();
            });
            dialog.show();
        });
    }

    private void reload() {
        RetrofitApi retrofitApi = RetrofitFunc.callRetrofit();
        Call<List<sachModel>> call = retrofitApi.getData();
        call.enqueue(new Callback<List<sachModel>>() {
            @Override
            public void onResponse(Call<List<sachModel>> call, Response<List<sachModel>> response) {
                List<sachModel> list = response.body();
                item = new itemList(MainActivity.this, list);
                listView.setAdapter(item);
            }

            @Override
            public void onFailure(Call<List<sachModel>> call, Throwable t) {

            }
        });

    }

    public void delete(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xoa sach");
        builder.setMessage("Xac nhan xoa sach");
        builder.setPositiveButton("Co", (dialogInterface, i) -> {
            RetrofitApi retrofitApi = RetrofitFunc.callRetrofit();
            Call<Integer> call = retrofitApi.deleteData(id);
            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.body() == 1) {
                        Toast.makeText(MainActivity.this, "Xoa thanh cong!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Loi xoa!", Toast.LENGTH_SHORT).show();
                    }

                    reload();
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });
        }).setNegativeButton("Khong", (dialogInterface, i) -> {

        }).show();
    }

    public void update(sachModel sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v = LayoutInflater.from(this).inflate(R.layout.update, null, false);
        builder.setView(v);
        Dialog dialog = builder.create();

        EditText masach = v.findViewById(R.id.masach_sua);
        EditText tieude = v.findViewById(R.id.tieude_sua);
        EditText tacgia = v.findViewById(R.id.tacgia_sua);
        EditText namxuatban = v.findViewById(R.id.namxuatban_sua);
        EditText sotrang = v.findViewById(R.id.sotrang_sua);
        EditText theloai = v.findViewById(R.id.theloai_sua);

        masach.setText(sach.getMasach_ph33420());
        tieude.setText(sach.getTieude_ph33420());
        tacgia.setText(sach.getTacgia_ph33420());
        namxuatban.setText(sach.getNamxuatban_ph33420() + "");
        sotrang.setText(sach.getSotrang_ph33420() + "");
        theloai.setText(sach.getTheloai_ph33420());

        Button suas = v.findViewById(R.id.sua_sua);
        Button thoat = v.findViewById(R.id.thoat_sua);

        suas.setOnClickListener(m -> {
            String ms = masach.getText().toString();
            String td = tieude.getText().toString();
            String tg = tacgia.getText().toString();
            String nxb = namxuatban.getText().toString();
            String st = sotrang.getText().toString();
            String tl = theloai.getText().toString();

            sachModel sach1 = new sachModel(ms, td, tg, Integer.parseInt(nxb), Integer.parseInt(st), tl);
            RetrofitApi retrofitApi = RetrofitFunc.callRetrofit();
            Call<Integer> call = retrofitApi.updateData(sach.get_id(), sach1);
            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if (response.body() == 1) {
                        Toast.makeText(MainActivity.this, " Cap nhat thanh cong!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Loi cap nhat!", Toast.LENGTH_SHORT).show();

                    }
                    dialog.dismiss();
                    reload();
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });
        });

        thoat.setOnClickListener(m -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    public void show(sachModel sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View v = LayoutInflater.from(this).inflate(R.layout.show_item, null, false);
        builder.setView(v);
        Dialog dialog = builder.create();

        TextView masach = v.findViewById(R.id.masach_items);
        TextView tieude = v.findViewById(R.id.tieude_items);
        TextView tacgia = v.findViewById(R.id.tacgia_items);
        TextView namxuatban = v.findViewById(R.id.namxuatban_items);
        TextView sotrang = v.findViewById(R.id.sotrang_items);
        TextView theloai = v.findViewById(R.id.theloai_items);

        masach.setText("Ma sach: " + sach.getMasach_ph33420());
        tieude.setText("Tieu de: " + sach.getTieude_ph33420());
        tacgia.setText("Tac gia: " + sach.getTacgia_ph33420());
        namxuatban.setText("Nam xuat ban: " + sach.getNamxuatban_ph33420());
        sotrang.setText("So trang: " + sach.getSotrang_ph33420());
        theloai.setText("The loai: " + sach.getTheloai_ph33420());
        Button thoat = v.findViewById(R.id.thoat_items);

        thoat.setOnClickListener(m -> {
            dialog.dismiss();
        });
        dialog.show();
    }
}