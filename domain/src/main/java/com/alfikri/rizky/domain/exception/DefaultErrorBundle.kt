package com.alfikri.rizky.domain.exception

class DefaultErrorBundle (private val exception: Exception): ErrorBundle {

    companion object{
        private const val DEFAULT_ERROR_MSG = "Unknown Error"
    }

    override fun getException(): Exception? {
        return exception
    }

    override fun getErrorMessage(): String {
        return exception.message ?: DEFAULT_ERROR_MSG
    }
}