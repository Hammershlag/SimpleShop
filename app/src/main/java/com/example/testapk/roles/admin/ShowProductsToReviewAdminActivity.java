package com.example.testapk.roles.admin;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapk.R;

public class ShowProductsToReviewAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen


        setContentView(R.layout.show_products_to_review_admin_activity);

        Button deny = (Button) findViewById(R.id.show_products_to_review_admin_activity_deny_product_button);
        Button submit = (Button) findViewById(R.id.show_products_to_review_admin_activity_submit_product_button);
        Button showNext = (Button) findViewById(R.id.show_products_to_review_admin_activity_show_next_product_button);

        //TODO
        //submit - submit and show next product
        //deny - deny and show next product
        //show next - doesn't delete from database, for later review, shows next product

        deny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        showNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }

}
