package com.app.storitest.data.exception

sealed class AuthException(message: String) : Exception(message) {
    data class SignUpException(override val message: String = "Invalid Credentials at SingUp") : Exception(message)
    data class UserAlreadyExistException(override val message: String = "User al already exist in Firestore") : Exception(message)
    data class SignInException(override val message: String = "Error happened with the SignIn") : Exception(message)
}