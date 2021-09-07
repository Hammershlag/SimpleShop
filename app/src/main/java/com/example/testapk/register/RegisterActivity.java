package com.example.testapk.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapk.R;
import com.example.testapk.login.LoginActivity;
import com.example.testapk.user.UserDTO;
import com.example.testapk.user.UserDatabaseHandler;

import static com.example.testapk.data.Data.reg_user;
import static com.example.testapk.register.RegisterFunctions.canRegister;

public class RegisterActivity extends AppCompatActivity {

    Context context = this;
    UserDatabaseHandler db = new UserDatabaseHandler(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.register_activity);

        Button registerButton = findViewById(R.id.register_activity_register_button);

        EditText username = findViewById(R.id.register_activity_username_field);
        EditText email = findViewById(R.id.register_activity_email_field);
        EditText password = findViewById(R.id.register_activity_password_field);
        EditText password_confirmation = findViewById(R.id.register_activity_password_confirmation_field);
        CheckBox regulations_checkbox = findViewById(R.id.register_activity_accept_regulations_checkbox);
        TextView reguulations_text = findViewById(R.id.register_activity_accept_reulations_text);


        Context context = this;
        Intent loginActivity = new Intent(context, LoginActivity.class);
        Intent regulationsActivity = new Intent(context, RegulationsActivity.class);
        Intent emailVerificationActivity = new Intent(context, EmailVerificationActivity.class);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (canRegister(username.getText().toString(), email.getText().toString(), password.getText().toString(), password_confirmation.getText().toString(), regulations_checkbox, context)) {
                    reg_user = new UserDTO(username.getText().toString(), password.getText().toString(), email.getText().toString());
                    //db.addUser(reg_user);
                    startActivity(emailVerificationActivity);
                    //startActivity(loginActivity);
                }
            }
        });

        reguulations_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(regulationsActivity);
            }
        });

    }

}
