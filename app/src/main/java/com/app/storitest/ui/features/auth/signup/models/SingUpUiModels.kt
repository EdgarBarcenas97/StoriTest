package com.app.storitest.ui.features.auth.signup.models

import com.app.storitest.domain.models.UserRegister

data class UserRegisterUi(val fistName: String,
                          val lastName: String,
                          val email: String,
                          val password: String,
                          val pictureIdentification: String)

fun UserRegisterUi.toUserRegister() = UserRegister(
    fistName = fistName,
    lastName = lastName,
    email = email,
    password = password,
    picture = pictureIdentification)
