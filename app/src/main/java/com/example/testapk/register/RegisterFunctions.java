package com.example.testapk.register;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.Toast;
import com.example.testapk.userData.UserDTO;
import com.example.testapk.userData.UserDatabaseHandler;

import java.util.List;
import java.util.regex.Matcher;

import static com.example.testapk.data.Data.*;

public class RegisterFunctions {

    public static boolean canRegister(String username, String email, String password, String password_confirmation, CheckBox checkBox, Context context)
    {
        UserDTO userDTO = new UserDTO(username, password, email);
        return isUsernameFree(userDTO, context) && isEmailValid(email, context) && passwordMatchesRequirements(password, context) && passwordMatchesPasswordConfirmation(password, password_confirmation, context) && isCheckboxChecked(checkBox, context);
    }

    public static boolean isUsernameFree(UserDTO userDTO, Context context)
    {
        boolean exists = false;

        UserDatabaseHandler db = new UserDatabaseHandler(context);

        List<UserDTO> users = db.getAllUsers();

        for (UserDTO user : users)
        {
            if (user.getUsername().equals(userDTO.getUsername()))
                exists = true;
        }

        if (exists == true)
            Toast.makeText(context, "Username exists", Toast.LENGTH_LONG).show();

        return !exists;
    }

    public static boolean passwordMatchesPasswordConfirmation(String password, String password_confirmation, Context context)
    {
        if (password.equals(password_confirmation) == true)
            Toast.makeText(context, "Passwords doesn't match", Toast.LENGTH_LONG).show();

        return password.equals(password_confirmation);
    }

    public static boolean passwordMatchesRequirements(String password, Context context)
    {
        int big_letters = 0;
        int letters = password.length();
        int special_signs = 0;
        int numbers = 0;
        char[] pass_char = password.toCharArray();
        for (char ch : pass_char)
        {
            String c = String.valueOf(ch);

            Matcher n = numbers_pattern.matcher(c);
            Matcher b = big_letters_pattern.matcher(c);

            if (n.matches()) {
                numbers++;
            }
            else if (specialChars.contains(c))
            {
                special_signs++;
            }
            else if (b.matches())
            {
                big_letters++;
            }
        }

        if (!(password_required_letters <= letters && password_required_big_letters <= big_letters && password_required_numbers <= numbers && password_required_special_signs <= special_signs) == true)
            Toast.makeText(context, "Password doesn't match requirements", Toast.LENGTH_LONG).show();

        return password_required_letters <= letters && password_required_big_letters <= big_letters && password_required_numbers <= numbers && password_required_special_signs <= special_signs;
    }

    public static boolean isEmailValid(String email, Context context)
    {
        Matcher e = emailRegex.matcher(email);

        if (!e.matches() == true)
            Toast.makeText(context, "Input corrent email", Toast.LENGTH_LONG).show();

        return e.matches();
    }

    public static boolean isCheckboxChecked(CheckBox checkBox, Context context)
    {

        if (!checkBox.isChecked() == true)
            Toast.makeText(context, "Please read regulations", Toast.LENGTH_LONG).show();

        return checkBox.isChecked();
    }

}
