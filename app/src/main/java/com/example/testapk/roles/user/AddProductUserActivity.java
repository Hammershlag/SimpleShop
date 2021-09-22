package com.example.testapk.roles.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapk.R;
import com.example.testapk.product.ProductDTO;
import com.example.testapk.product.ProductsDatabaseHandler;

import static com.example.testapk.data.Data.current_user_user;

public class AddProductUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.addd_product_user_activity);

        Context context = this;

        Button submit = findViewById(R.id.add_product_user_activity_submit_button);
        EditText name = findViewById(R.id.add_product_user_activity_name_input);
        EditText description = findViewById(R.id.add_product_user_activity_description_input);
        EditText price = findViewById(R.id.add_product_user_activity_price_input);

        submit.setOnClickListener(view ->
        {
            String name_ = name.getText().toString();
            String description_ = description.getText().toString();
            int price_ = Integer.valueOf(price.getText().toString());

            ProductDTO productDTO = new ProductDTO(name_, description_, price_, current_user_user.getUsername());

            ProductsDatabaseHandler pdb = new ProductsDatabaseHandler(context);
            if (name_ != null && description_ != null && price_ > 0)
            {
                pdb.addProduct(productDTO);
                Intent intent = new Intent(context, MainUserActivity.class);
                startActivity(intent);
            }
            else Toast.makeText(context, "Input valid data", Toast.LENGTH_SHORT).show();
        });
    }
}