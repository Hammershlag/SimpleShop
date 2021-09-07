package com.example.testapk.data;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.testapk.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AuthorsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.authors_activity);

        TextView txtView = (TextView)findViewById(R.id.authors_activity_authors_text);

        InputStream inputStream = getResources().openRawResource(R.raw.authors);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        txtView.setText(byteArrayOutputStream.toString());
    }

}
