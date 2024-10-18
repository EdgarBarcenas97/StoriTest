package com.app.storitest.domain.models

import com.app.storitest.data.models.UserFirestore

data class UserRegister(val fistName: String,
                        val lastName: String,
                        val email: String,
                        val password: String,
                        var picture: String)

data class User(val firstName: String,
                val lastName: String,
                val email: String,
                val picture: String,
                val transactions: List<Transaction>)

fun UserRegister.toUserRegisterMap() = mapOf(
    FIRST_NAME to fistName,
    LAST_NAME to lastName,
    EMAIL_NAME to email,
    PICTURE to picture)

fun Result<UserFirestore?>.toResultUser() = map { it.toUser() }

private fun UserFirestore?.toUser() = User(
    firstName = this?.firstName.orEmpty(),
    lastName = this?.lastName.orEmpty(),
    email = this?.email.orEmpty(),
    picture = this?.picture.orEmpty(),
    transactions = this?.transactions.toTransactionList())

const val FIRST_NAME = "firstName"
const val LAST_NAME = "lastName"
const val EMAIL_NAME = "email"
const val PICTURE = "picture"