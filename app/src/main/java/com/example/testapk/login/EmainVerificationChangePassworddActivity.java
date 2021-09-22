package com.example.testapk.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapk.R;
import com.example.testapk.register.MailAPI;

import java.util.Random;

import static com.example.testapk.data.Data.forgot_password_user;

public class EmainVerificationChangePassworddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.email_verifiation_activity);

        Context context = this;

        Random random = new Random();

        int code = Math.abs(random.nextInt() % 10000);

        System.out.println(code);

        Button submit_buutton = findViewById(R.id.email_verification_activity_enter_code_button);
        EditText code_input = findViewById(R.id.email_verification_activity_enter_code_field);


        sendEmail(forgot_password_user.getEmail(), code);

        submit_buutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codde = code_input.getText().toString();
                if (codde.equals(String.valueOf(code)))
                {
                    System.out.println("Dobry numer");

                    Intent intent = new Intent(context, ChangePasswordActivity.class);
                    startActivity(intent);
                }
                else Toast.makeText(context, "Wrong code", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void sendEmail(String email, int code)
    {
        String mail_subject = "TestAPK - Change password email";
        String mail_messagge = "We are sending you this email to let you change your password. Please enter this code in app: " + code;

        MailAPI mailAPI = new MailAPI(this, email, mail_subject, mail_messagge);
        mailAPI.execute();
    }
}