package com.hq.projectads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class SignUp extends AppCompatActivity {
    private TextInputEditText username_ip;
    private TextInputEditText password_ip;
    private TextInputEditText rePassword_ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        username_ip = findViewById(R.id.input_edt_username_SignUp);
        password_ip = findViewById(R.id.input_edt_matkhau_signUp);
        rePassword_ip = findViewById(R.id.input_edt_rematkhau_signUp);

        Button dangki = findViewById(R.id.btn_signUp);
        TextView signIn = findViewById(R.id.txt_signIn_signUp);

        signIn.setOnClickListener(e -> {
            Intent intent = new Intent(this, SignIn.class);
            startActivity(intent);
        });

        dangki.setOnClickListener(e -> {

            String username = Objects.requireNonNull(username_ip.getText()).toString();
            String password = Objects.requireNonNull(password_ip.getText()).toString();
            String repassword = Objects.requireNonNull(rePassword_ip.getText()).toString();

            if ((username.equals("") || password.equals("") || repassword.equals(""))) {
                Toast.makeText(this, "Thông tin không được để trống!\nInformation empty!", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(repassword)) {
                Toast.makeText(this, "Hai mật khẩu không khớp nhau!\nTwo password not match!", Toast.LENGTH_SHORT).show();
            } else {
                dangki.setEnabled(false);
                dangki.setText("Loading...");

                User user = new User(username, password);
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(ValLocal.urlLocal)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
                Call<Integer> call = retrofitAPI.signUp(user);
                call.enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(Call<Integer> call, Response<Integer> response) {
                        dangki.setEnabled(true);
                        dangki.setText("Đăng kí");

                        if (response.body() == 1) {
                            Toast.makeText(SignUp.this, "Đăng kí thành công!\nSign up complete!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUp.this, "Tài khoản đã tồn tại!\nUsername exist!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Integer> call, Throwable t) {
                        Toast.makeText(SignUp.this, "Network error, please restart app!", Toast.LENGTH_SHORT).show();
                        Toast.makeText(SignUp.this, "Lỗi kết nối mạng, vui lòng mở lại ứng dụng!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}