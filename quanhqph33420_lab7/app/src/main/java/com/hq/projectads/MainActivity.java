package com.hq.projectads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.hq.projectads.model.User;
import com.hq.projectads.retrofit.RetrofitAPI;
import com.hq.projectads.val.ValLocal;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RewardedAd rewardedAd;
    private TextView loading;
    private Button btnAds;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loading = findViewById(R.id.loading_);
        sharedPreferences = getSharedPreferences(ValLocal.containerUser, MODE_PRIVATE);

        Button btnRutTien = findViewById(R.id.btnRutTienNH);
        btnRutTien.setOnClickListener(e -> {
            Intent intent = new Intent(this, GetMoney.class);
            startActivity(intent);
        });

        loadUser();
        loadAds();

        btnAds = findViewById(R.id.btnXemVideoQc);

        btnAds.setOnClickListener(e -> {
            if (rewardedAd != null) {
                Activity activityContext = MainActivity.this;
                rewardedAd.show(activityContext, rewardItem -> {
                    congTien();
                });
                rewardedAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdClicked() {
                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {

                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        loadAds();
                    }

                    @Override
                    public void onAdImpression() {
                        loadAds();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                    }
                });
            }
        });
    }

    public void congTien() {
        User user = new User(sharedPreferences.getString(ValLocal.keyUser, ""), ValLocal.earnMoney);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ValLocal.urlLocal)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<Integer> call = retrofitAPI.congTien(user);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() == 1) {
                    loadUser();

                } else {
                    Toast.makeText(MainActivity.this, "Network error, please restart app!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, "Lỗi kết nối mạng, vui lòng mở lại ứng dụng!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error, please restart app!", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Lỗi kết nối mạng, vui lòng mở lại ứng dụng!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadAds() {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(this, ValLocal.adsId,
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        rewardedAd = null;
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd ad) {
                        rewardedAd = ad;
                        loading.setText("Quảng cáo đã được tải...\nAds have been loaded");
                        btnAds.setEnabled(true);
                    }
                });
    }

    public void loadUser() {
        User user = new User(sharedPreferences.getString(ValLocal.keyUser, ""), "");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ValLocal.urlLocal)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<User> call = retrofitAPI.getUserObject(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                TextView textView = findViewById(R.id.txt_sotien);
                textView.setText(Objects.requireNonNull(response.body()).getSo_tien() + " Xu");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Network error, please restart app!", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, "Lỗi kết nối mạng, vui lòng mở lại ứng dụng!\nNetwork error, please restart app!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}