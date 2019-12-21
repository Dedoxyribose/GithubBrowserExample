package com.example.justsomeapplication.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.annotation.StringRes

fun EditText.setTextChangeListener(listener: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(value: CharSequence?, p1: Int, p2: Int, p3: Int) {
            listener(value.toString())
        }
    })
}

fun EditText.setError(@StringRes error: Int?) {
    if (error != null) {
        setError(context.getString(error))
    } else {
        setError(null)
    }
}