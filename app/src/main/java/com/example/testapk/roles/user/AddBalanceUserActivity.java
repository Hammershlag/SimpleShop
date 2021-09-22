package com.example.testapk.roles.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testapk.R;
import com.example.testapk.userData.UserDatabaseHandler;

import static com.example.testapk.data.Data.current_user_user;
import static com.example.testapk.roles.user.CreditCardValidation.LuhnAlgo;

public class AddBalanceUserActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int[] values = {10, 20, 50, 100, 200, 500, 1000};
    String[] values_str = {"10", "20", "50", "100", "200", "500", "1000"};
    int val = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.add_balance_user_activity);

        SeekBar add_balance_seek_bar = findViewById(R.id.add_balance_user_activity_seek_bar);
        TextView phone_number = findViewById(R.id.add_balance_user_activity_phone_number_text);
        EditText credit_card_number = findViewById(R.id.add_balance_user_activity_credit_card_number_input);
        Button submit = findViewById(R.id.add_balance_user_activity_submit_button);
        Spinner val_spinner = (Spinner) findViewById(R.id.add_balance_user_activity_value_spinner);
        val_spinner.setOnItemSelectedListener(this);

        UserDatabaseHandler udb = new UserDatabaseHandler(this);
        Context context = this;
        add_balance_seek_bar.setMax(999999999);




        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                values_str);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        val_spinner.setAdapter(ad);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ccNumber = credit_card_number.getText().toString();
                if (LuhnAlgo(ccNumber))
                {
                    System.out.println(current_user_user.getBalance());
                    Toast.makeText(getApplicationContext(), val + " balance added", Toast.LENGTH_SHORT).show();
                    current_user_user.setBalance(current_user_user.getBalance() + val);
                    udb.updateUser(current_user_user);
                    System.out.println(current_user_user.getBalance());

                    Intent intent = new Intent(context, MainUserActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "bad credit cardd number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        add_balance_seek_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar add_balance_seek_bar, int progress,
                                          boolean fromUser) {
                String pr = String.valueOf(progress).substring(0,3);
                String pr2 = String.valueOf(progress).substring(3,6);
                String pr3 = String.valueOf(progress).substring(6,9);
                phone_number.setText(pr + " " + pr2 + " " + pr3);
                System.out.println(progress);

                //Toast.makeText(getApplicationContext(), "seekbar progress: " + progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar add_balance_seek_bar) {
                //Toast.makeText(getApplicationContext(), "seekbar touch started!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar add_balance_seek_bar) {
                //Toast.makeText(getApplicationContext(), "seekbar touch stopped!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        val = values[position];
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
