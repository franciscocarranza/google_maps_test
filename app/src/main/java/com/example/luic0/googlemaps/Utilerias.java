package com.example.luic0.googlemaps;

import android.widget.EditText;

public class Utilerias {

    public static boolean hasText(EditText editText, String message) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length: 0 means there is no text
        if (text.length() == 0) {
            editText.setError(message);
            return false;
        }

        return true;
    }
}
