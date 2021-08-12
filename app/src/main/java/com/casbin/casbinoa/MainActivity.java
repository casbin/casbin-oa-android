package com.casbin.casbinoa;

import android.content.Intent;
import android.os.Bundle;

import com.casbin.casdoor.CasdoorConfig;
import com.casbin.casdoor.CasdoorLoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.casbin.casbinoa.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CasdoorConfig.InitConfig(this, "http://**.**.**.**", "36b6f56926f8e1b6a485",
                "0128cf637306889c64439b0c6a5c37c374d8d69c",
                "asdsaodihasodasdsadiuhwaidunsxasdsaodihasodasdsadiuhwaidunsxasdsaodihasodasdsadiuhwaidunsxasdsaodihasodasdsadiuhwaidunsxasdsaodihasodasdsadiuhwaidunsx",
                "built-in");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_mine)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}