package com.example.testapk.roles.user;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapk.R;

import static com.example.testapk.data.Data.current_user_user;


public class MainUserActivity extends AppCompatActivity {

    TextView username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.main_activity_user);

        username = findViewById(R.id.main_activity_username);
        password = findViewById(R.id.main_activity_password);

        username.setText(current_user_user.getUsername());
        password.setText(current_user_user.getPassword());

    }
}
