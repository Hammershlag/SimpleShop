package com.example.testapk.roles.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapk.R;
import com.example.testapk.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.testapk.data.Data.current_user_user;


public class MainUserActivity extends AppCompatActivity {

    TextView username, balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.main_activity_user);

        Context context = this;

        String PREFS_NAME = "Login";

        SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        FloatingActionButton user_icon = findViewById(R.id.main_activity_user_user_icon);

        Button log_out = findViewById(R.id.main_activity_user_log_out_button);
        Button products_list = findViewById(R.id.main_activity_user_products_list_button);
        Button add_product = findViewById(R.id.main_activity_user_add_prodduct_button);


        Intent login_activity = new Intent(context, LoginActivity.class);
        Intent products_list_activity = new Intent(context, ShowProductsUserActivity.class);

        username = findViewById(R.id.main_activity_username);
        balance = findViewById(R.id.main_activity_user_balance_input);

        username.setText(current_user_user.getUsername());
        balance.setText(String.valueOf(current_user_user.getBalance()));

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current_user_user = null;
                editor.putString("userLogin", null);
                editor.putString("userPassword", null);
                editor.putBoolean("stayLogged", false);
                editor.apply();
                startActivity(login_activity);
            }
        });

        products_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(products_list_activity);
            }
        });

        add_product.setOnClickListener(view ->
        {
            Intent intent = new Intent(context, AddProductUserActivity.class);
            startActivity(intent);
        });

        user_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SettingsUserActivity.class);
                startActivity(intent);
            }
        });

    }
}
