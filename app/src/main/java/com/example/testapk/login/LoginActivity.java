package com.example.testapk.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapk.R;
import com.example.testapk.data.AuthorsActivity;
import com.example.testapk.register.RegisterActivity;
import com.example.testapk.roles.admin.MainAdminActivity;
import com.example.testapk.roles.user.MainUserActivity;
import com.example.testapk.userData.UserDTO;
import com.example.testapk.userData.UserDatabaseHandler;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.testapk.data.Data.current_user_user;

public class LoginActivity extends AppCompatActivity {

    protected Button loginButton, registerButton;
    protected EditText username_field, password_field;
    protected CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.login_activity);
        Context context = this;
        Intent mainUserActivity = new Intent(context, MainUserActivity.class);
        Intent mainAdminActivity = new Intent(context, MainAdminActivity.class);
        Intent registerActivity = new Intent(context, RegisterActivity.class);
        Intent camelActivity = new Intent(context, AuthorsActivity.class);


        String PREFS_NAME = "Login";


        SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        checkBox = findViewById(R.id.login_activity_stay_logged_in_checkbox);
        loginButton = findViewById(R.id.login_activity_login_button);
        registerButton = findViewById(R.id.login_activity_register_button);
        username_field = findViewById(R.id.login_activity_username_field);
        password_field = findViewById(R.id.login_activity_password_field);

        String username = username_field.getText().toString();
        String password = password_field.getText().toString();

        String banned_regex    =   "^BANNED";


        Pattern banned_pattern =   Pattern.compile(banned_regex, Pattern.CASE_INSENSITIVE);


        loginButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UserDatabaseHandler db = new UserDatabaseHandler(context);

                List<UserDTO> userDTOSList = db.getAllUsers();

                boolean exists = false;

                boolean staylogged = false;

                if (username_field.getText().toString().equals("authors") && password_field.getText().toString().equals("authors"))
                    startActivity(camelActivity);

                for (UserDTO user : userDTOSList)
                {
                    if (user.getUsername().equals(username_field.getText().toString()) && user.getPassword().equals(password_field.getText().toString())) {
                        current_user_user = user;
                        if (checkBox.isChecked()) {

                            editor.putString("userLogin", user.getUsername());
                            editor.putString("userPassword", user.getPassword());

                            staylogged = true;
                        }
                        else
                        {
                            editor.putString("userLogin", null);
                            editor.putString("userPassword", null);
                        }
                        editor.putBoolean("stayLogged", staylogged);
                        Matcher banned_matcher = banned_pattern.matcher(user.getRole());
                        editor.apply();
                        exists = true;
                        if (banned_matcher.find())
                        {
                            String[] ban_reason = user.getRole().split(banned_regex);
                            Toast.makeText(context, "You have been banned for " + ban_reason[ban_reason.length-1], Toast.LENGTH_LONG).show();
                            //you are banned
                        }
                        else
                        {
                            switch (user.getRole()) {
                                case "USER":
                                    startActivity(mainUserActivity);
                                    break;
                                case "ADMIN":
                                    startActivity(mainAdminActivity);
                                    break;

                            }

                        }
//                        if (user.getRole().equals("USER"))
//                            startActivity(mainUserActivity);
//                        else if (user.getRole().equals("ADMIN"))
//                            startActivity(mainAdminActivity);


                    }
                }

                if (!exists)
                    Toast.makeText(context, "Check your input", Toast.LENGTH_LONG).show();

            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(registerActivity);
            }
        });

    }
}
