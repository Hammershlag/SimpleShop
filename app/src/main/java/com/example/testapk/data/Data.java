package com.example.testapk.data;

import com.example.testapk.userData.UserDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Data {

    public static String[] permission_list = {android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                              android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                              android.Manifest.permission.ACCESS_COARSE_LOCATION,
                                              android.Manifest.permission.ACCESS_FINE_LOCATION,
                                              android.Manifest.permission.INTERNET};

    public static int permission_code_list = 100;

    public static Map<String, String> login_hash_map = new HashMap();

    public static Pattern emailRegex = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+\\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$", Pattern.CASE_INSENSITIVE);;
    public static String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
    public static Pattern numbers_pattern = Pattern.compile("[0-9]");
    public static Pattern big_letters_pattern = Pattern.compile("[A-Z]");

    public static int password_required_big_letters = 1;
    public static int password_required_numbers = 2;
    public static int password_required_special_signs = 1;
    public static int password_required_letters = 8;

    public static UserDTO current_user_user = null;

    public static UserDTO reg_user = null;


}
