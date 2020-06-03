package com.alfikri.rizky.data.exception

import com.alfikri.rizky.domain.exception.ErrorBundle

class RepositoryErrorBundle(private val exception: Exception?) : ErrorBundle {

    override fun getException(): Exception? {
        return exception
    }

    override fun getErrorMessage(): String {
        var message = ""
        exception?.let { message = "${it.message}" }

        return message
    }
}