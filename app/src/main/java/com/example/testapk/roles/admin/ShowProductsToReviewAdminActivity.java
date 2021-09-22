package com.example.testapk.roles.admin;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapk.R;
import com.example.testapk.product.ProductDTO;
import com.example.testapk.product.ProductsDatabaseHandler;

import java.util.List;

import static com.example.testapk.data.Data.current_product_review_id;

public class ShowProductsToReviewAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen


        setContentView(R.layout.show_products_to_review_admin_activity);

        Context context = this;
        Button deny = findViewById(R.id.show_products_to_review_admin_activity_deny_product_button);
        Button submit = findViewById(R.id.show_products_to_review_admin_activity_submit_product_button);
        Button showNext = findViewById(R.id.show_products_to_review_admin_activity_show_next_product_button);

        TextView name_view = findViewById(R.id.show_products_to_review_admin_activity_product_name_input_text);
        TextView description_view = findViewById(R.id.show_products_to_review_admin_activity_product_description_input_text);
        TextView price_view = findViewById(R.id.show_products_to_review_admin_activity_product_price_input_text);
        TextView author_view = findViewById(R.id.show_products_to_review_admin_activity_product_author_input_text);

        ProductsDatabaseHandler pdb = new ProductsDatabaseHandler(context);
        List<ProductDTO> productDTOList = pdb.getAllProducts();

        if (productDTOList.size() < current_product_review_id)
        {
            current_product_review_id = 1;
        }

//        TODO last product not showing



        deny.setEnabled(true);
        submit.setEnabled(true);
        showNext.setEnabled(true);

        ProductDTO current_product;
        do {

            if (current_product_review_id >= productDTOList.size())
            {
                current_product = null;
                deny.setEnabled(false);
                submit.setEnabled(false);
                showNext.setEnabled(false);
                break;
            }

            current_product = productDTOList.get(current_product_review_id);
            current_product_review_id++;

        }
        while(!current_product.getStatus().equals("WAITING"));

        if (current_product != null) {
            name_view.setText(current_product.getName());
            description_view.setText(current_product.getDescription());
            price_view.setText(String.valueOf(current_product.getPrice()));
            author_view.setText(current_product.getAuthor());
        }

        //TODO
        //submit - submit and show next product
        //deny - deny and show next product
        //show next - doesn't delete from database, for later review, shows next product

        ProductDTO finalCurrent_product = current_product;
        deny.setOnClickListener(view -> {
            finalCurrent_product.setStatus("DENIED");
            finalCurrent_product.setTime_checked(System.currentTimeMillis());
            pdb.updateProduct(finalCurrent_product);
            recreate();
        });

        submit.setOnClickListener(view -> {
            finalCurrent_product.setStatus("APPROVED");
            finalCurrent_product.setTime_checked(System.currentTimeMillis());
            pdb.updateProduct(finalCurrent_product);
            recreate();
        });

        showNext.setOnClickListener(view -> {
            recreate();
        });



    }

}
