package com.example.justsomeapplication.utils

import android.content.Context
import com.example.justsomeapplication.R
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.net.ssl.SSLException

class ErrorHelper @Inject constructor(private val context: Context) {

    fun handleError(throwable: Throwable): String {
        throwable.printStackTrace()
        return when (throwable) {
            is UnknownHostException, is ConnectException, is SSLException -> {
                getString(R.string.connection_error)
            }
            else -> getString(R.string.unknown_error)
        }
    }

    private fun getString(res: Int): String = context.getString(res)

}
