package com.example.testapk.roles.admin;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapk.R;

import java.util.Date;

import static com.example.testapk.data.Data.current_product;

public class ProductAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.view_product_admin_activity);

        TextView name = findViewById(R.id.view_product_admin_activity_name_input);
        TextView description = findViewById(R.id.view_product_admin_activity_description_input);
        TextView price = findViewById(R.id.view_product_admin_activity_price_input);
        TextView rating = findViewById(R.id.view_product_admin_activity_rating_input);
        TextView number_of_products = findViewById(R.id.view_product_admin_activity_number_of_products_input);
        TextView author = findViewById(R.id.view_product_admin_activity_author_input);
        TextView status = findViewById(R.id.view_product_admin_activity_status_input);
        TextView check_time = findViewById(R.id.view_product_admin_activity_time_checked_input);
        TextView add_time = findViewById(R.id.view_product_admin_activity_time_added_input);

        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");

        Date result_checked = new Date(current_product.getTime_checked());
        Date result_added = new Date(current_product.getTime_created());

        name.setText(current_product.getName());
        description.setText(current_product.getDescription());
        price.setText(String.valueOf(current_product.getPrice()));
        rating.setText(String.valueOf(current_product.getRating()));
        number_of_products.setText(String.valueOf(current_product.getNumber_of_products()));
        author.setText(current_product.getAuthor());
        status.setText(current_product.getStatus());
        check_time.setText(String.valueOf(simple.format(result_checked)));
        add_time.setText(String.valueOf(simple.format(result_added)));

        System.out.println(current_product.getName());
    }
}