package com.example.testapk.roles.user;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapk.R;
import com.example.testapk.product.ProductsDatabaseHandler;
import com.example.testapk.userData.UserDTO;
import com.example.testapk.userData.UserDatabaseHandler;

import java.util.List;

import static com.example.testapk.data.Data.current_product;
import static com.example.testapk.data.Data.current_user_user;

public class ProductUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.view_product_user_activity);

        TextView name = findViewById(R.id.view_product_user_activity_name_input);
        TextView description = findViewById(R.id.view_product_user_activity_description_input);
        TextView price = findViewById(R.id.view_product_user_activity_price_input);
        TextView rating = findViewById(R.id.view_product_user_activity_rating_input);
        TextView number_of_products = findViewById(R.id.view_product_user_activity_number_of_products_input);
        TextView author = findViewById(R.id.view_product_user_activity_author_input);

        Button buy_button = findViewById(R.id.view_product_admin_activity_buy_button);

        Context context = this;

        name.setText(current_product.getName());
        description.setText(current_product.getDescription());
        price.setText(String.valueOf(current_product.getPrice()));
        rating.setText(String.valueOf(current_product.getRating()));
        number_of_products.setText(String.valueOf(current_product.getNumber_of_products()));
        author.setText(String.valueOf(current_product.getAuthor()));

        UserDatabaseHandler udb = new UserDatabaseHandler(context);

        List<UserDTO> userDTOList = udb.getAllUsers();

        buy_button.setOnClickListener(view -> {
            boolean check = current_user_user.getUsername().equals(current_product.getAuthor());
            if (!check) {
                if (current_user_user.getBalance() >= current_product.getPrice()) {
                    current_user_user.setBalance((int) (current_user_user.getBalance() - current_product.getPrice()));
                    current_product.setNumber_of_products(current_product.getNumber_of_products() + 1);
                    String pr_b = current_user_user.getProducts_owned().equals("") ? current_product.getName() : current_user_user.getProducts_owned() + ", " + current_product.getName();
                    current_user_user.setProducts_owned(pr_b);
                    ProductsDatabaseHandler pdb = new ProductsDatabaseHandler(context);

                    for (UserDTO user : userDTOList) {
                        if (user.getUsername().equals(current_product.getAuthor())) {
                            user.setBalance((int) (user.getBalance() + current_product.getPrice()));
                            udb.updateUser(user);
                            break;
                        }
                    }

                    pdb.updateProduct(current_product);
                    udb.updateUser(current_user_user);
                    Toast.makeText(context, "You succesfully bought product " + current_product.getName(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "You can't buy this product, you don't have enough money", Toast.LENGTH_LONG).show();
                }
            }
            else Toast.makeText(context, "You can't buy your own products", Toast.LENGTH_LONG).show();
        });
    }
}