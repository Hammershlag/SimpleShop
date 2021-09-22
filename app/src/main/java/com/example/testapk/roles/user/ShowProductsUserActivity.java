package com.example.testapk.roles.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapk.R;
import com.example.testapk.product.ProductDTO;
import com.example.testapk.product.ProductsDatabaseHandler;

import java.util.LinkedList;
import java.util.List;

import static com.example.testapk.data.Data.current_product;

public class ShowProductsUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.show_products_activity);

        Context context = this;

        ListView products_listview = findViewById(R.id.show_products_admin_activity_products_listview);

        ProductsDatabaseHandler pdb = new ProductsDatabaseHandler(context);

        List<ProductDTO> productsList = pdb.getAllProducts();

        List<ProductDTO> final_products = new LinkedList<ProductDTO>();

        for (int i = 0; i < productsList.size(); i++)
        {
            if (productsList.get(i).getStatus().equals("APPROVED"))
            final_products.add(productsList.get(i));
        }

        String[] product_names = new String[final_products.size()];

        for (int i = 0; i < final_products.size(); i++)
        {
            product_names[i] = final_products.get(i).getName();
        }

        ArrayAdapter<String> arr = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, product_names);
        products_listview.setAdapter(arr);


        products_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                current_product = productsList.get(i);

                Intent intent = new Intent(context, ProductUserActivity.class);
                startActivity(intent);
            }
        });

    }
}