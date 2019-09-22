package com.pontes.cursoandroid.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pontes.cursoandroid.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fabMain, fabExpenses, fabEarnings;
    private boolean fabsHide = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new ExpenseFormFragment()).commit();
        //.add(R.id.fragmentContainer, new MainFragment()).commit();

        fabMain = findViewById(R.id.fabMain);

        fabMain.setOnClickListener(this);

        fabEarnings = findViewById(R.id.fabEarnings);
        fabExpenses = findViewById(R.id.fabExpenses);

        //ButterKnife.bind(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fabMain){
            showHideFabs();
        }
    }

    @SuppressLint("RestrictedApi")
    public void showHideFabs(){

        AlphaAnimation fabAnimation = new AlphaAnimation(0,1);
        fabAnimation.setDuration(1000);
        fabAnimation.setStartOffset(100);

        if (fabsHide) {
            fabExpenses.setVisibility(View.VISIBLE);
            fabEarnings.setVisibility(View.VISIBLE);
            fabExpenses.setAnimation(fabAnimation);
            fabEarnings.setAnimation(fabAnimation);
        }else{
            fabExpenses.clearAnimation();
            fabEarnings.clearAnimation();
            fabExpenses.setVisibility(View.INVISIBLE);
            fabEarnings.setVisibility(View.INVISIBLE);
        }

        fabsHide = !fabsHide;
    }
}
