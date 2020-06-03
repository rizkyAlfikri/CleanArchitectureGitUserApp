package com.alfikri.rizky.cleanarchitecturegituserapp.exception

import android.content.Context
import com.alfikri.rizky.cleanarchitecturegituserapp.R
import com.alfikri.rizky.data.exception.NetworkConnectionException
import com.alfikri.rizky.data.exception.UserNotFoundException

class ErrorMessageFactory {

    companion object {
        fun create(context: Context?, exception: Exception?): String {

            return when (exception) {
                is NetworkConnectionException -> context?.getString(R.string.exception_message_no_internet)
                is UserNotFoundException -> context?.getString(R.string.exception_message_user_not_found)
                else -> context?.getString(R.string.exception_message_generic)
            }.toString()
        }
    }
}