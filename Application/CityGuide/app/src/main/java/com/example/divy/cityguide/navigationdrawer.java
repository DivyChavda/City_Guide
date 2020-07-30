package com.example.divy.cityguide;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class navigationdrawer extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    LinearLayout appBarLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Fragment fragment;
    FragmentManager fragmentManager;

    TextView txtHomeUsername;
    public static final String PreferencesName = "AppPref";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigationdrawer);


        try {

            toolbar = (Toolbar) findViewById(R.id.ToolBar);
            setSupportActionBar(toolbar);

            drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
            appBarLayout = (LinearLayout) findViewById(R.id.appBarLayout);
            navigationView = (NavigationView) findViewById(R.id.navigationView);

            toggle = new ActionBarDrawerToggle(navigationdrawer.this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            };
            drawerLayout.setDrawerListener(toggle);
            toggle.syncState();

        } catch (Exception ex) {
            Toast.makeText(navigationdrawer.this, ex.toString(), Toast.LENGTH_LONG).show();
        }

        sharedPreferences = getSharedPreferences(PreferencesName, Context.MODE_PRIVATE);
        View headerView = navigationView.getHeaderView(0);
        txtHomeUsername = (TextView) headerView.findViewById(R.id.xtHomeUsername);
        if (sharedPreferences != null) {
            String userName = sharedPreferences.getString("Username", null);
            if (userName != null) {
                txtHomeUsername.setText(userName);
                txtHomeUsername.setVisibility(View.VISIBLE);
            }
        }

        HomeFragment();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                fragment = null;

                switch (item.getItemId()) {
                    case R.id.menuHome:
                        fragment = new home();
                        setTitle("Home");
                        break;


                    case R.id.menuFavorite:
                        fragment = new favorite();
                        setTitle("Favourite");
                        break;

                    case R.id.menuChangePassword:
                        fragment = new changepassword();
                        setTitle("ChangePassword");
                        break;

                    case R.id.menuAccount:
                        fragment = new account();
                        setTitle("Account");
                        break;

                    case R.id.menuAboutUs:
                        fragment = new about_us();
                        setTitle("About Us");
                        break;
                    case R.id.menuContactUs:
                        fragment = new contact_us();
                        setTitle("Contact Us");
                        break;

                    case R.id.menuLogin:
                        Intent i1 = new Intent(navigationdrawer.this, login.class);
                        startActivity(i1);
                        break;

                    case R.id.menuSign_Up:
                        Intent i2 = new Intent(navigationdrawer.this, singup.class);
                        startActivity(i2);
                        break;

                    case R.id.menuLogout:
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();

                        Intent i = new Intent(navigationdrawer.this, navigationdrawer.class);
                        startActivity(i);
                        break;

                    default:
                        Toast.makeText(navigationdrawer.this, "No Data", Toast.LENGTH_LONG).show();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                setFragmentManager();

                return true;
            }
        });

        Menu nav_Menu = navigationView.getMenu();
        MenuItem changepassword = nav_Menu.findItem(R.id.menuChangePassword);
        MenuItem menuLogout = nav_Menu.findItem(R.id.menuLogout);
        MenuItem menuAccount = nav_Menu.findItem(R.id.menuAccount);
        MenuItem menuFavorite = nav_Menu.findItem(R.id.menuFavorite);
        MenuItem menuLogin = nav_Menu.findItem(R.id.menuLogin);
        MenuItem menuSignup = nav_Menu.findItem(R.id.menuSign_Up);

        changepassword.setVisible(false);
        menuLogout.setVisible(false);
        menuAccount.setVisible(false);
        menuFavorite.setVisible(false);
        menuLogin.setVisible(true);
        menuSignup.setVisible(true);


        if (sharedPreferences != null) {
            String userName = sharedPreferences.getString("Username", null);
            if (userName != null) {
                changepassword.setVisible(true);
                menuLogout.setVisible(true);
                menuAccount.setVisible(true);
                menuFavorite.setVisible(true);
                menuLogin.setVisible(false);
                menuSignup.setVisible(false);
            }

        }
    }


    public void HomeFragment() {
        fragment = new home();
        setFragmentManager();
    }

    public void setFragmentManager() {
        if (fragment != null) {
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.replacableLayout, fragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
                        startActivity(intent);
                        finish();
                        System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionmenu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }
}
