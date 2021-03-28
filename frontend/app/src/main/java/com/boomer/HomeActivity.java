package com.boomer;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.boomer.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, new HomeFragment())
                .commit();
        binding.bottomNavView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.homeFragment:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment, new HomeFragment())
                            .commit();
                    break;
                case R.id.storeFragment:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment, new StoreFragment())
                            .commit();
                    break;
                case R.id.notificationFragment:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment, new NotificationFragment())
                            .commit();
                    break;
                case R.id.settingsFragment:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment, new SettingsFragment())
                            .commit();
                    break;
                default:
                    return true;
            }
            return true;
        });
    }
}