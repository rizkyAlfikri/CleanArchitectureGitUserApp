package com.alfikri.rizky.domain.exception

interface ErrorBundle {
    fun getException(): Exception?
    fun getErrorMessage(): String
}