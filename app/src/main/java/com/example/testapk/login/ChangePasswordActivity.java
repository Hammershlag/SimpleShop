package com.example.testapk.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapk.R;
import com.example.testapk.userData.UserDatabaseHandler;

import static com.example.testapk.data.Data.forgot_password_user;
import static com.example.testapk.data.SHA256.encrypt;
import static com.example.testapk.register.RegisterFunctions.passwordMatchesPasswordConfirmation;
import static com.example.testapk.register.RegisterFunctions.passwordMatchesRequirements;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.forgot_password_change_password_activity);

        Context context = this;

        Intent loginActivity = new Intent(context, LoginActivity.class);

        Button submit_change_pass = findViewById(R.id.forgot_password_change_password_submit_button);
        EditText new_password = findViewById(R.id.forgot_password_change_password_new_password_input);
        EditText repeat_password = findViewById(R.id.forgot_password_change_password_repeat_password_input);

        submit_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordMatchesPasswordConfirmation(new_password.getText().toString(), repeat_password.getText().toString(), context) && passwordMatchesRequirements(new_password.getText().toString(), context))
                {
                    forgot_password_user.setPassword(encrypt(new_password.getText().toString()));
                    System.out.println(forgot_password_user.getPassword());
                    UserDatabaseHandler udb = new UserDatabaseHandler(context);
                    udb.updateUser(forgot_password_user);
                    startActivity(loginActivity);
                }
            }
        });
    }

}