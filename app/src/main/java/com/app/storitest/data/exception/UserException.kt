package com.app.storitest.data.exception

sealed class UserException(message: String) : Exception(message) {
    data class SavePictureUserException(override val message: String = "Some error happened when save picture user") : Exception(message)
    data class SaveUserException(override val message: String = "Some error happened when save User") : Exception(message)
    data class GetUserException(override val message: String = "Some error happened when get User") : Exception(message)
}