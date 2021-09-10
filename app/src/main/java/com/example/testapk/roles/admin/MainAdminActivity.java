package com.example.testapk.roles.admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapk.R;

import static com.example.testapk.data.Data.current_user_user;

public class MainAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.main_activity_admin);

        Context context = this;

        Button show_user_database = findViewById(R.id.main_activity_admin_show_users_database_button);
        Button show_products_database = findViewById(R.id.main_activity_admin_show_products_database_button);
        Button show_products_to_review_database = findViewById(R.id.main_activity_admin_show_products_to_review_database_button);

        TextView logged_as = findViewById(R.id.main_activity_admin_logged_with_role_text);
        logged_as.setText(current_user_user.getRole());

        show_user_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent user = new Intent(context, ShowUsersAdminActivity.class);
                startActivity(user);
            }
        });
        show_products_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent user = new Intent(context, ShowProductsAdminActivity.class);
                startActivity(user);
            }
        });
        show_products_to_review_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent user = new Intent(context, ShowProductsToReviewAdminActivity.class);
                startActivity(user);
            }
        });

    }
}