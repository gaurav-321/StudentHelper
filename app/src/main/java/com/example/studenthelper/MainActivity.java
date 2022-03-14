package com.example.studenthelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

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
        int secondsDelayed = 3;
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

    public void showInfo(View v) {
        v.setBackgroundColor(Color.GRAY);
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("On this page you can maintain the attendance and update your current attendance. Easy gui that can be used to get the perfect management of the college/school life ")
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
     }
}
