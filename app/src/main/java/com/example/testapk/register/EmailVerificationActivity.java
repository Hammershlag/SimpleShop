package com.example.testapk.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapk.R;
import com.example.testapk.login.LoginActivity;
import com.example.testapk.userData.UserDatabaseHandler;

import java.util.Random;

import static com.example.testapk.data.Data.reg_user;

public class EmailVerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen

        setContentView(R.layout.email_verifiation_activity);
        //TODO email verification
        Random random = new Random();

        int code = Math.abs(random.nextInt() % 10000);

        System.out.println(code);

        Context context = this;
        TextView code_text = findViewById(R.id.email_verification_activity_code_display_text);
        Button submit_buutton = findViewById(R.id.email_verification_activity_enter_code_button);
        TextView code_input = findViewById(R.id.email_verification_activity_enter_code_field);

        Intent loginActivity = new Intent(context, LoginActivity.class);

        sendEmail(reg_user.getEmail(), code);

        //code_text.setText(Integer.toString(code));

        submit_buutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (code_input.getText().toString().equals(code));
                {
                    System.out.println("Dobry numer");

                    UserDatabaseHandler db = new UserDatabaseHandler(context);
                    db.addUser(reg_user);

                    startActivity(loginActivity);
                }
            }
        });

    }

    private void sendEmail(String email, int code)
    {
        String mail_subject = "TestAPK Email verification";
        String mail_messagge = "We are sending you this email to verify your personal informaion in our apk. Please enter this code in app: " + code;

        MailAPI mailAPI = new MailAPI(this, email, mail_subject, mail_messagge);
        mailAPI.execute();
    }

}
