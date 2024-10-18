package com.app.storitest.core

sealed class Result<out T : Any?> {

    data class success<out T : Any?>(val data: T) : Result<T>()
    data class failure(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is success<*> -> "Success[data=$data]"
            is failure -> "Error[exception=$exception]"
        }
    }
}

fun <T : Any> Result<T>.error(message: String? = null) = (this as? Result.failure)?.exception ?: Exception(message ?: "unknown error")

fun <T : Any> Result<T>.error(exception: Exception) = (this as? Result.failure)?.exception ?: exception

fun <T : Any?> Result<T>.success() = (this as? Result.success)?.data
