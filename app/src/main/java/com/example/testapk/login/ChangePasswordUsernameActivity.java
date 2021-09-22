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
import com.example.testapk.userData.UserDTO;
import com.example.testapk.userData.UserDatabaseHandler;

import java.util.List;

import static com.example.testapk.data.Data.forgot_password_user;

public class ChangePasswordUsernameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.forgot_password_username_activity);

        Context context = this;

        EditText username = findViewById(R.id.forgot_password_username_activity_username_input);
        Button submit_username = findViewById(R.id.forgot_password_username_activity_submit_button);

        submit_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_username = username.getText().toString();

                UserDatabaseHandler udb = new UserDatabaseHandler(context);
                List<UserDTO> userDTOList = udb.getAllUsers();

                boolean exists = false;

                for (UserDTO userDTO : userDTOList) {
                    if (user_username.equals(userDTO.getUsername()))
                    {
                        forgot_password_user = userDTO;
                        Intent intent = new Intent(context, EmainVerificationChangePassworddActivity.class);
                        startActivity(intent);
                        exists = true;
                        break;
                    }
                }
                if (!exists)
                    Toast.makeText(context, "This user doesn't exist", Toast.LENGTH_SHORT).show();
            }
        });
    }
}