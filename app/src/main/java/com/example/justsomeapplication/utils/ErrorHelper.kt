package com.example.justsomeapplication.utils

import android.content.Context
import com.example.justsomeapplication.R
import retrofit2.Response
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
            is NotFoundException -> throwable.message!!
            else -> getString(R.string.unknown_error)
        }
    }

    fun <T> extractResponse(response: Response<T>): T {
        when {
            response.isSuccessful -> return response.body()!!
            response.code() == 404 -> throw NotFoundException(context.getString(R.string.nothing_found))
            else -> throw Exception()
        }
    }

    private fun getString(res: Int): String = context.getString(res)

    class NotFoundException(message: String) : Exception(message)

}
