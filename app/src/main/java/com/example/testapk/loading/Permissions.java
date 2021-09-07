package com.example.testapk.loading;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;

import static com.example.testapk.data.Data.permission_code_list;
import static com.example.testapk.data.Data.permission_list;

public class Permissions {




    public static void checkPermissions(Context context, Activity activity)
    {

        askForPermission(activity, permission_list, permission_code_list);

        for (int i = 0; i < permission_list.length; i++) {
            int checkVal = context.checkCallingOrSelfPermission(permission_list[i]);
            if (checkVal== PackageManager.PERMISSION_GRANTED){
                Toast.makeText(context, "Permissions for " + permission_list[i] + " already granted", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(context, "Permissions for " + permission_list[i] + " not granted", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private static void askForPermission(Activity activity, String[] requiredPermission, int permission_code) {

        ActivityCompat.requestPermissions(activity, requiredPermission, permission_code);

    }

}
