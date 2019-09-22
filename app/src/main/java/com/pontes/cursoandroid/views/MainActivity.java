package com.pontes.cursoandroid.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
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
                .add(R.id.fragmentContainer, new MainFragment()).commit();

        fabMain = findViewById(R.id.fabMain);
        fabMain.setOnClickListener(this);

        fabEarnings = findViewById(R.id.fabEarnings);

        fabExpenses = findViewById(R.id.fabExpenses);
        fabExpenses.setOnClickListener(this);

        //ButterKnife.bind(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fabMain){
            showHideFabs();
        }else if (v.getId() == R.id.fabExpenses){
            loadExpenses();
        }
    }

    private void loadExpenses() {
        loadActionBar(R.string.expense_title_action_bar, true);
        getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragmentContainer, new ExpenseFormFragment()).commit();
        showHideFabs();
    }

    private void loadActionBar(int stringID, boolean isNotHome) {
        getSupportActionBar().setTitle(stringID);
        getSupportActionBar().setDisplayHomeAsUpEnabled(isNotHome);
        getSupportActionBar().setShowHideAnimationEnabled(isNotHome);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            backToHome();
        }
        return super.onOptionsItemSelected(item);
    }

    private void backToHome() {
        loadActionBar(R.string.app_name, false);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, new MainFragment())
                .commit();
    }

    @SuppressLint("RestrictedApi")
    public void showHideFabs(){

        AlphaAnimation fabAnimation = new AlphaAnimation(0,1);
        fabAnimation.setDuration(1000);
        fabAnimation.setStartOffset(100);

        if (fabsHide) {
            fabExpenses.setVisibility(View.INVISIBLE);
            fabEarnings.setVisibility(View.INVISIBLE);
            fabExpenses.setAnimation(fabAnimation);
            fabEarnings.setAnimation(fabAnimation);
        }else{
            fabExpenses.clearAnimation();
            fabEarnings.clearAnimation();
            fabExpenses.setVisibility(View.GONE);
            fabEarnings.setVisibility(View.GONE);
        }

        fabsHide = !fabsHide;
    }
}
