package com.example.testapk.loading;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapk.R;
import com.example.testapk.data.AuthorsActivity;
import com.example.testapk.login.LoginActivity;
import com.example.testapk.product.ProductDTO;
import com.example.testapk.product.ProductsDatabaseHandler;
import com.example.testapk.roles.admin.MainAdminActivity;
import com.example.testapk.roles.user.MainUserActivity;
import com.example.testapk.userData.UserDTO;
import com.example.testapk.userData.UserDatabaseHandler;

import java.util.List;

import static com.example.testapk.data.Data.current_user_user;
import static com.example.testapk.loading.Permissions.checkPermissions;

public class LoadingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.loading_activity);
        Context context = this;
        Activity activity = this;



        checkPermissions(context, activity);

        Intent myIntent;

        Intent mainUserActivity = new Intent(context, MainUserActivity.class);
        Intent mainAdminActivity = new Intent(context, MainAdminActivity.class);
        Intent authorsActivity = new Intent(context, AuthorsActivity.class);


        String PREFS_NAME = "Login";


        SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        myIntent = new Intent(this, LoginActivity.class);

        UserDatabaseHandler db = new UserDatabaseHandler(context);

        ProductsDatabaseHandler pdb = new ProductsDatabaseHandler(context);
        pdb.addProduct(new ProductDTO(null, null, null, -1, -1, -1, null));

        if (settings.getBoolean("stayLogged", false))
        {

            String user_username = settings.getString("userLogin", "");
            String user_password = settings.getString("userPassword", "");

            List<UserDTO> userDTOSList = db.getAllUsers();

            boolean exists = false;

            if (user_username.equals("authors") && user_password.equals("authors"))
                startActivity(authorsActivity);

            for (UserDTO user : userDTOSList)
            {
                if (user.getUsername().equals(user_username) && user.getPassword().equals(user_password)) {
                    current_user_user = user;

                    editor.putBoolean("stayLogged", true);

                    editor.apply();
                    exists = true;
                    if (user.getRole().equals("USER"))
                        startActivity(mainUserActivity);
                    else if (user.getRole().equals("ADMIN"))
                        startActivity(mainAdminActivity);

                }
            }

            if (!exists)
                Toast.makeText(context, "Check your input", Toast.LENGTH_LONG).show();
        }
        boolean admin_exists = false;

        for (UserDTO userDTO : db.getAllUsers())
        {

            if (userDTO.getUsername().equals("admin"))
                admin_exists = true;
        }

        if (!admin_exists)
        {
            db.addUser(new UserDTO(999999,"admin", "nie", "admin@admin.admin", "ADMIN"));
        }
        startActivity(myIntent);
    }
}