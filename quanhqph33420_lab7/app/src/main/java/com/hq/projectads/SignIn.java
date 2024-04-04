package com.hq.projectads;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.hq.projectads.model.User;
import com.hq.projectads.retrofit.RetrofitAPI;
import com.hq.projectads.val.ValLocal;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignIn extends AppCompatActivity {
    private TextInputEditText username;
    private TextInputEditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        username = findViewById(R.id.input_edt_username_SignIn);
        password = findViewById(R.id.input_edt_matkhau_signIn);


        Button dangnhap = findViewById(R.id.btn_signIn);
        dangnhap.setOnClickListener(e -> {
            String user = Objects.requireNonNull(username.getText()).toString();
            String pass = Objects.requireNonNull(password.getText()).toString();

            if (user.equals("") || pass.equals("")) {
                Toast.makeText(this, "Thông tin không được để trống!\nInformation empty!", Toast.LENGTH_SHORT).show();
            } else {
                dangnhap.setEnabled(false);
                dangnhap.setText("Loading...");

                User userObject = new User(user, pass);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ValLocal.urlLocal)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
                Call<Integer> call = retrofitAPI.signIn(userObject);

                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        dangnhap.setEnabled(true);
                        dangnhap.setText("Đăng nhập");

                        if (response.body() == 1) {
                            Toast.makeText(SignIn.this, "Đăng nhập thành công!\nLogin complete!", Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor = getSharedPreferences(ValLocal.containerUser, Context.MODE_PRIVATE).edit();
                            editor.putString(ValLocal.keyUser, user);
                            editor.apply();

                            Intent intent = new Intent(SignIn.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignIn.this, "Incorrect username or password!", Toast.LENGTH_SHORT).show();
                            Toast.makeText(SignIn.this, "Thông tin tài khoản mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Toast.makeText(SignIn.this, "Network error, please restart app!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(SignIn.this, "Lỗi kết nối mạng, vui lòng mở lại ứng dụng!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        });

        TextView signUp = findViewById(R.id.txt_signUp_signIn);
        signUp.setOnClickListener(e -> {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        });
    }
}