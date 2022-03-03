package com.example.studenthelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.studenthelper.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fragmentReplacement(new SplashScreen());
        ConstraintLayout main = findViewById(R.id.main_activity);
        BottomNavigationView btmview = findViewById(R.id.bottomNavigationView);
        btmview.setVisibility(View.GONE);
        main.setBackgroundColor(getResources().getColor(R.color.theme_1));
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                fragmentReplacement(new AttedanceFragment());
                main.setBackgroundColor(getResources().getColor(R.color.white));
                btmview.setVisibility(View.VISIBLE);
            }
        }, secondsDelayed * 1000);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.attendance_menu:
                    fragmentReplacement(new AttedanceFragment());
                    break;
                case R.id.todo_menu:
                    fragmentReplacement(new TodoFragment());
                    break;
                case R.id.image_menu:
                    fragmentReplacement(new FolderFragment());
                    break;


            }
            return true;
        });
    }

    public void fragmentReplacement(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fadeout);
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

    }
}