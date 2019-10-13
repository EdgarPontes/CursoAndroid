package com.pontes.cursoandroid.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.pontes.cursoandroid.R;
import com.pontes.cursoandroid.controllers.BankController;
import com.pontes.cursoandroid.controllers.CreditCardController;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.fabMain)
    FloatingActionButton fabMain;
    @BindView(R.id.fabEarnings)
    FloatingActionButton fabEarnings;
    @BindView(R.id.fabExpenses)
    FloatingActionButton fabExpenses;

    //private FloatingActionButton fabMain, fabExpenses, fabEarnings;
    private boolean fabsHide = true;
    private Toolbar mToolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = findViewById(R.id.navigationMenu);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();

        ButterKnife.bind(this);

        mToolbar = findViewById(R.id.mainToolbar);
        setSupportActionBar(mToolbar);

        drawerLayout = findViewById(R.id.drawer);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(
                this, drawerLayout, mToolbar, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new MainFragment()).commit();

    }

    @OnClick(R.id.fabMain)
    public void fabMain(View view){
        showHideFabs();
    }
    @OnClick(R.id.fabEarnings)
    public void fabEarnings(View view){
        loadEarnings();
    }
    @OnClick(R.id.fabExpenses)
    public void fabExpenses(View view){
        loadExpenses();
    }

    private void loadEarnings() {
        loadActionBar(R.string.earning_form_header, true);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, new EarningsFormFragment()).commit();
        showHideFabs();
    }

    private void loadExpenses() {
        loadActionBar(R.string.expense_title_action_bar, true);
        getSupportFragmentManager().beginTransaction()
        .replace(R.id.fragmentContainer, new ExpenseFormFragment()).commit();
        showHideFabs();
    }

    private void loadActionBar(int stringID, boolean isNotHome) {
        getSupportActionBar().setTitle(stringID);
       /* getSupportActionBar().setDisplayHomeAsUpEnabled(isNotHome);
        getSupportActionBar().setShowHideAnimationEnabled(isNotHome);*/
    }

    private void closeDrawer(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.drawer_menu_main:
                backToHome();
                break;
            case R.id.drawer_menu_bank:
                if (BankController.get() != null){
                    getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragmentContainer, new BankInfoFragment()).
                            commit();
                }else {
                    getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragmentContainer, new NoBankFragment()).
                            commit();
                }
                break;
            case R.id.drawer_menu_credit:
                if (CreditCardController.get() != null){
                    getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragmentContainer, new CreditCardInfoFragment()).
                            commit();
                }else{
                    getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.fragmentContainer, new NoCreditCardFragment()).
                            commit();
                }
                break;
            case R.id.drawer_menu_invoices:
                getSupportFragmentManager().
                        beginTransaction().
                        replace(R.id.fragmentContainer, new BillListFragment()).
                        commit();
                break;
            case R.id.drawer_menu_installments:
                break;
        }
        closeDrawer();
        return false;
    }
}
